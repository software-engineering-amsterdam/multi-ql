package sc.ql.check;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sc.ql.ast.Expression;
import sc.ql.ast.Expression.VariableExpr;
import sc.ql.ast.Form;
import sc.ql.ast.Statement.ComputedQuestion;
import sc.ql.ast.TopDown;
import sc.ql.check.CyclicReferences.CyclicReference;
import sc.ql.check.CyclicReferences.ReferenceTable.Reference;
import sc.ql.check.CyclicReferences.ReferenceTable.ReferencePath;

public class CyclicReferences
    implements Iterable<CyclicReference>
{
  private final Set<CyclicReference> cyclicReferences;

  private CyclicReferences()
  {
    cyclicReferences = new HashSet<>();
  }

  /**
   * Collect all cyclic references in the questionnaire.
   * 
   * @param questionnaire
   *          the questionnaire to collect all cyclic references from.
   * @return the cyclic references in the questionnaire.
   */
  public static CyclicReferences collect(Form form)
  {
    CyclicReferences cyclicReferences;
    ReferenceTable rt;

    rt = new ReferenceTable();

    form.accept(new TopDown<Void, Void>()
                {
                  @Override
                  public Void visit(ComputedQuestion node, Void unused)
                  {
                    rt.add(node,
                           freeVariables(node.computation()));
                    return null;
                  }
                },
                null);

    cyclicReferences = new CyclicReferences();

    rt.getReferences().forEach(reference -> {
      for (ReferencePath path : reference.getCyclicPaths())
      {
        cyclicReferences.add(new CyclicReference(reference,
                                                 path));
      }
    });

    return cyclicReferences;
  }

  /**
   * Collect all free variables in specified expressions.
   * 
   * @param expr
   *          the expression to collect all free variables from.
   * @return the free variables used in the expression.
   */
  public static Set<String> freeVariables(Expression expr)
  {
    Set<String> variables;

    variables = new HashSet<>();

    expr.accept(new TopDown<Void, Void>()
                {
                  @Override
                  public Void visit(VariableExpr node, Void unused)
                  {
                    variables.add(node.getVariableName());

                    return null;
                  }
                },
                null);

    return variables;
  }

  private void add(CyclicReference cyclicReference)
  {
    assert cyclicReference.getPath().hasCycle() : "Specified CyclicReference does not have a cycle!";
    cyclicReferences.add(cyclicReference);
  }

  @Override
  public Iterator<CyclicReference> iterator()
  {
    return Collections.unmodifiableSet(cyclicReferences).iterator();
  }

  public static class CyclicReference
  {
    private final Reference reference;
    private final ReferencePath path;

    private CyclicReference(Reference reference, ReferencePath path)
    {
      this.reference = reference;
      this.path = path;
    }

    public Reference getReference()
    {
      return reference;
    }

    public ReferencePath getPath()
    {
      return path;
    }
  }

  public static class ReferenceTable
  {
    private final Map<String, Reference> referenceMapById;

    private ReferenceTable()
    {
      referenceMapById = new HashMap<>();
    }

    public void add(ComputedQuestion question, Set<String> variables)
    {
      Reference reference;

      reference = getReference(question.name());

      variables.forEach(variable -> reference.addDependency(variable));
    }

    private List<Reference> getReferences()
    {
      return new ArrayList<>(referenceMapById.values());
    }

    private Reference getReference(String id)
    {
      return referenceMapById.computeIfAbsent(id,
                                              f -> new Reference(id));
    }

    public static class ReferencePath
    {
      private final List<Reference> referenceList;

      public ReferencePath()
      {
        referenceList = new ArrayList<>();
      }

      public ReferencePath(ReferencePath referencePath)
      {
        referenceList = new ArrayList<>(referencePath.referenceList);
      }

      /**
       * Return a copy of this {@code ReferencePath} that is extended with the specified reference. Extending means that
       * the reference is appended to the path.
       * 
       * @param r
       *          the {@code Reference} to add.
       * @return a copy of this {@code ReferencePath} with the the supplied reference appended.
       */
      public ReferencePath extend(Reference r)
      {
        ReferencePath copy;

        copy = new ReferencePath(this);
        copy.referenceList.add(r);

        return copy;
      }

      public List<Reference> getPath()
      {
        return Collections.unmodifiableList(referenceList);
      }

      /**
       * Returns the path as a string in the form of 'a -> b -> c', where the letters are the id's of the references,
       * and a refers to b, and b refers to c.
       * 
       * @return the path as a string.
       */
      public String getPathString()
      {
        StringBuilder sb;

        sb = new StringBuilder();

        // Make a String in the form of 'a -> b -> c'
        referenceList.stream().forEachOrdered(ref -> {
          if (sb.length() > 0)
          {
            sb.append(" -> ");
          }
          sb.append(ref.id);
        });

        return sb.toString();
      }

      /**
       * Returns true if this path contains the specified reference.
       * 
       * @param r
       *          reference whose presence in this list is to be tested
       * @return {@code true} if this path contains the specified reference
       */
      public boolean contains(Reference r)
      {
        return referenceList.contains(r);
      }

      /**
       * Returns true if this path contains a cycle.
       * 
       * @return {@code true} if this path contains a cycle, {@code false} otherwise.
       */
      public boolean hasCycle()
      {
        for (Reference r : referenceList)
        {
          if (hasCycle(r))
          {
            return true;
          }
        }

        return false;
      }

      /**
       * Returns true if this path contains a cycle with specified reference.
       * 
       * @param r
       *          the reference to check.
       * @return {@code true} if this path contains a cycle with reference {@code r}, {@code false} otherwise.
       * 
       * @throws NullPointerException
       *           if {@code r} is {@code null}.
       */
      public boolean hasCycle(Reference r)
      {
        return frequency(r) > 1;
      }

      /**
       * Returns how many times the specified reference occurs in this ReferencePath.
       *
       * @param r
       *          the reference whose frequency is to be determined
       * @return the number of elements in this path equal to {@code r}
       * @throws NullPointerException
       *           if {@code r} is null
       */
      private int frequency(Reference r)
      {
        return Collections.frequency(referenceList,
                                     r);
      }

      /**
       * Calling toString on {@code ReferencePath} will return the same String that is returned by
       * {@link #getPathString()}.
       * 
       * @see #getPathString()
       */
      @Override
      public String toString()
      {
        return getPathString();
      }
    }

    public class Reference
    {
      private final String id;
      private final List<Reference> dependencies = new ArrayList<>();

      private Reference(String id)
      {
        this.id = id;
      }

      public String getId()
      {
        return id;
      }

      private void addDependency(String id)
      {
        dependencies.add(getReference(id));
      }

      public List<Reference> getDependencies()
      {
        return Collections.unmodifiableList(dependencies);
      }

      /**
       * Returns the reference paths that contain a cycle.
       * 
       * @return the reference paths that contain a cycle.
       */
      public List<ReferencePath> getCyclicPaths()
      {
        List<ReferencePath> cyclicPaths;

        cyclicPaths = new ArrayList<>();

        getPaths().forEach(path -> {
          if (path.hasCycle())
          {
            cyclicPaths.add(path);
          }
        });

        return Collections.unmodifiableList(cyclicPaths);
      }

      /**
       * Returns all the {@code ReferencePath}'s for this {@code References}.
       * 
       * @return the {@code ReferencesPath}'s. for this {@code Reference}.
       */
      public List<ReferencePath> getPaths()
      {
        return getPaths(new ReferencePath());
      }

      /**
       * Returns all the {@code ReferencePath}'s for this {@code References}, appended to the specified
       * {@code parentPath}.
       * 
       * @param parentPath
       *          the path to use as a root.
       * 
       * @return the {@code ReferencesPath}'s. for this {@code Reference}.
       */
      public List<ReferencePath> getPaths(ReferencePath parentPath)
      {
        List<ReferencePath> paths;
        ReferencePath myPath;

        myPath = parentPath.extend(this);

        // We've arrived at the end of a path.
        if (isLeaf())
        {
          return Collections.singletonList(myPath);
        }

        // Found a cycle. Continuing will cause endless recursion.
        if (myPath.hasCycle(this))
        {
          return Collections.singletonList(myPath);
        }

        paths = new ArrayList<>();
        for (Reference reference : dependencies)
        {
          paths.addAll(reference.getPaths(myPath));
        }

        return Collections.unmodifiableList(paths);
      }

      /**
       * Returns whether this reference is a leaf. A reference is a leaf iff the reference has no referents.
       * 
       * @return {@code true} if this reference is a leaf, {@code false} otherwise.
       */
      private boolean isLeaf()
      {
        return hasDependencies();
      }

      /**
       * Returns whether this reference has any referents.
       * 
       * @return {@code true} if this reference has referents, {@code false} otherwise.
       */
      private boolean hasDependencies()
      {
        return dependencies.isEmpty();
      }

      @Override
      public final boolean equals(Object obj)
      {
        Reference other;

        if (!(obj instanceof Reference))
        {
          return false;
        }

        other = (Reference) obj;

        return id.equals(other.id) && dependencies.equals(other.dependencies);
      }

      @Override
      public final int hashCode()
      {
        return id.hashCode();
      }

      @Override
      public String toString()
      {
        return id;
      }
    }
  }
}

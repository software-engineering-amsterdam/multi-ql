package sc.ql.check;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sc.ql.ast.Expression;
import sc.ql.ast.Statement.ComputedQuestion;

public class ReferenceTable
{
  private final Map<String, Reference> referenceMapById;

  ReferenceTable()
  {
    referenceMapById = new HashMap<>();
  }

  public Set<Reference> getReferences()
  {
    return Collections.unmodifiableSet(new HashSet<Reference>(referenceMapById.values()));
  }

  public void add(ComputedQuestion question)
  {
    Expression computation;
    Reference reference;

    reference = getReference(question.name());

    computation = question.computation();
    for (String freeVariable : computation.freeVariables())
    {
      reference.addDependency(freeVariable);
    }
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

    public ReferencePath(ReferenceTable.ReferencePath referencePath)
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
    public ReferenceTable.ReferencePath extend(Reference r)
    {
      ReferenceTable.ReferencePath copy;

      copy = new ReferencePath(this);
      copy.referenceList.add(r);

      return copy;
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
     * Returns the path as a string in the form of 'a -> b -> c', where the letters are the id's of the references, and
     * a refers to b, and b refers to c.
     * 
     * @return the path as a string.
     */
    @Override
    public String toString()
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
  }

  public class Reference
  {
    private final String id;
    private final List<Reference> dependencies = new ArrayList<>();

    private Reference(String id)
    {
      this.id = id;
    }

    private void addDependency(String id)
    {
      dependencies.add(getReference(id));
    }

    /**
     * Returns the reference paths that contain a cycle.
     * 
     * @return the reference paths that contain a cycle.
     */
    public List<ReferenceTable.ReferencePath> getCyclicPaths()
    {
      List<ReferenceTable.ReferencePath> cyclicPaths;

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
    private List<ReferenceTable.ReferencePath> getPaths()
    {
      return getPaths(new ReferencePath());
    }

    /**
     * Returns all the {@code ReferencePath}'s for this {@code References}, appended to the specified {@code parentPath}
     * .
     * 
     * @param parentPath
     *          the path to use as a root.
     * 
     * @return the {@code ReferencesPath}'s. for this {@code Reference}.
     */
    private List<ReferenceTable.ReferencePath> getPaths(ReferenceTable.ReferencePath parentPath)
    {
      List<ReferenceTable.ReferencePath> paths;
      ReferenceTable.ReferencePath myPath;

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
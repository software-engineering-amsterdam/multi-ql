package sc.qls.ast;

import java.awt.Color;
import java.awt.Font;

import sc.ql.ast.ASTNode;
import sc.ql.ast.Literal.IntegerLiteral;
import sc.ql.ast.Literal.StringLiteral;

public abstract class Property
    extends ASTNode
{

  public abstract <T, U> T accept(PropertyVisitor<T, U> visitor, U context);

  public static class ColorProperty
      extends Property
  {
    private final StringLiteral value;

    public ColorProperty(StringLiteral value)
    {
      this.value = value;
    }

    public Color value()
    {
      int red, green, blue;
      String[] ar_color;

      ar_color = value.value().toString().split(",");
      red = Integer.parseInt(ar_color[0]);
      green = Integer.parseInt(ar_color[1]);
      blue = Integer.parseInt(ar_color[2]);

      return new Color(red,
                       green,
                       blue);
    }

    @Override
    public <T, U> T accept(PropertyVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class HeightProperty
      extends Property
  {
    private final IntegerLiteral value;

    public HeightProperty(IntegerLiteral value)
    {
      this.value = value;
    }

    public int value()
    {
      return value.value().getValue();
    }

    @Override
    public <T, U> T accept(PropertyVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class WidthProperty
      extends Property
  {
    private final IntegerLiteral value;

    public WidthProperty(IntegerLiteral value)
    {
      this.value = value;
    }

    public int value()
    {
      return value.value().getValue();
    }

    @Override
    public <T, U> T accept(PropertyVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class FontNameProperty
      extends Property
  {
    private final StringLiteral value;

    public FontNameProperty(StringLiteral value)
    {
      this.value = value;
    }

    public String value()
    {
      return value.value().getValue();
    }

    @Override
    public <T, U> T accept(PropertyVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class FontSizeProperty
      extends Property
  {
    private final IntegerLiteral value;

    public FontSizeProperty(IntegerLiteral value)
    {
      this.value = value;
    }

    public int value()
    {
      return value.value().getValue();
    }

    @Override
    public <T, U> T accept(PropertyVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class FontStyleProperty
      extends Property
  {
    private final StringLiteral value;

    public FontStyleProperty(StringLiteral value)
    {
      this.value = value;
    }

    public int value()
    {
      int style;
      String styleName;

      style = 0;

      styleName = value.value().getValue().toLowerCase();

      if (styleName.equals("bolditalic"))
      {
        style = Font.BOLD | Font.ITALIC;
      }
      else if (styleName.equals("italic"))
      {
        style = Font.ITALIC;
      }
      else if (styleName.equals("bold"))
      {
        style = Font.BOLD;
      }
      else if (styleName.equals("plain"))
      {
        style = Font.PLAIN;
      }

      return style;
    }

    @Override
    public <T, U> T accept(PropertyVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }
}

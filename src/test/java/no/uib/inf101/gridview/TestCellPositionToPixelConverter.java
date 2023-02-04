package no.uib.inf101.gridview;

import no.uib.inf101.colorgrid.CellColor;
import no.uib.inf101.colorgrid.CellPosition;
import no.uib.inf101.colorgrid.GridDimension;
import no.uib.inf101.colorgrid.IColorGrid;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCellPositionToPixelConverter {

  @Test
  public void illustratedSample() {
    IColorGrid grid = newGridFromString(String.join("\n",
        "R--B",
        "----",
        "Y--G")
    );
    CellPositionToPixelConverter converter = getConverter(
        grid, new Rectangle2D.Double(30, 30, 340, 240), 30);

    Rectangle2D expected = new Rectangle2D.Double(215, 130, 47.5, 40);
    assertEquals(expected, getBoundsForCell(converter, new CellPosition(1, 2)));
  }

  /////////////////////////////
  // Helper methods
  /////////////////////////////

  static Rectangle2D getBoundsForCell(CellPositionToPixelConverter converter, CellPosition cp) {
    try {
      Method method = CellPositionToPixelConverter.class.getMethod("getBoundsForCell", CellPosition.class);
      // Check that the method is public
      assertFalse(Modifier.isPrivate(method.getModifiers()),
          "The method getBoundsForCell(CellPosition) in the CellPositionToPixelConverter class should not be private");

      Object result = method.invoke(converter, cp);
      assertInstanceOf(Rectangle2D.class, result,
          "The method getBoundsForCell(CellPosition) in the CellPositionToPixelConverter class should return a Rectangle2D");
      return (Rectangle2D) result;
    } catch (NoSuchMethodException e) {
      fail("Could not find the method getBoundsForCell(CellPosition) in the CellPositionToPixelConverter class");
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
    throw new IllegalStateException("Should not be possible to reach this point");
  }


  static CellPositionToPixelConverter getConverter(GridDimension grid, Rectangle2D rect, double margin) {
    try {
      Constructor<?> constructor = CellPositionToPixelConverter.class.getConstructor(
          GridDimension.class, Rectangle2D.class, double.class
      );

      // Check that the constructor is public
      assertTrue(Modifier.isPublic(constructor.getModifiers()),
          "The constructor CellPositionToPixelConverter(IColorGrid, Rectangle2D, double)"
              + " should be public");

      // Create a new object using the constructor and return it
      return (CellPositionToPixelConverter) constructor.newInstance(grid, rect, margin);
    } catch (NoSuchMethodException e) {
      fail("Could not find the constructor CellPositionToPixelConverter(IColorGrid, Rectangle2D, " +
          "double) in the CellPositionToPixelConverter class");
    } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    throw new IllegalStateException("Should not be possible to reach this point");
  }

  static IColorGrid newGridFromString(String stringGrid) {
    return new IColorGrid() {
      private String[] lines = stringGrid.split("\n");

      @Override
      public int rows() {
        return this.lines.length;
      }

      @Override
      public int cols() {
        return this.lines[0].length();
      }

      @Override
      public Color get(CellPosition pos) {
        if (pos.row() < 0 || pos.row() >= this.rows()) {
          throw new IllegalArgumentException("Row out of bounds");
        }
        if (pos.col() < 0 || pos.col() >= this.cols()) {
          throw new IllegalArgumentException("Column out of bounds");
        }
        return switch (this.lines[pos.row()].charAt(pos.col())) {
          case 'R' -> Color.RED;
          case 'G' -> Color.GREEN;
          case 'B' -> Color.BLUE;
          case 'Y' -> Color.YELLOW;
          default -> null;
        };
      }

      @Override
      public void set(CellPosition pos, Color color) {
        // ignore
      }

      @Override
      public java.util.List<CellColor> getCells() {
        List<CellColor> a = new ArrayList<>();
        for (int r = 0; r < this.rows(); r++) {
          for (int c = 0; c < this.cols(); c++) {
            Color color = this.get(new CellPosition(r, c));
            a.add(new CellColor(new CellPosition(r, c), color));
          }
        }
        return a;
      }
    };
  }
}

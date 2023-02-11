package no.uib.inf101.gridview;

// NOTE: This file does NOT demonstrate a good way to write a test!
// Most tests in this file are written in a way that makes it possible
// to test HOW the problem is solved instead of testing simply THAT
// the problem is solved. This is done to be able to test that the
// detailed instructions for the assignment are followed, and could
// provide hints in an educational setting in case something is off.
//
// In a real project, you should not test private methods, but instead
// test public or package-private methods. Test *what* the code does,
// not *how* it does it (like we do here).

import no.uib.inf101.colorgrid.*;
import org.junit.jupiter.api.Test;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class TestGridView {

  @Test
  public void preferredSize() {
    GridView view = newGridView(null);
    if ((Object) view instanceof JPanel panel) {
      assertNotNull(panel, "Unable to create a new GridView object");
      assertNotNull(panel.getPreferredSize(), "PreferredSize should not be null");
      assertEquals(400, panel.getPreferredSize().width);
      assertEquals(300, panel.getPreferredSize().height);
    } else {
      fail("The GridView class should extend JPanel");
    }
  }

  @Test
  public void drawCellsIllustratedSample() {
    // Sample case from assignment text
    IColorGrid sampleGrid = TestCellPositionToPixelConverter.newGridFromString(String.join("\n",
        "R--B",
        "----",
        "Y--G"
    ));
    Rectangle2D rect = new Rectangle2D.Double(30, 30, 340, 240);
    double margin = 30;
    RecordGraphics2D record = singleDrawCellsRun(sampleGrid, rect, margin);

    // Check that the correct number of calls were made
    assertEquals(12, record.getFillRecordShapes().size(),
        "The drawCells method call draw 12 rectangles in the illustrated sample case");

    assertColorIsDrawnOnceAt(record, Color.RED, new Rectangle2D.Double(60, 60, 47.5, 40));
    assertColorIsDrawnOnceAt(record, Color.BLUE, new Rectangle2D.Double(292.5, 60, 47.5, 40));
    assertColorIsDrawnOnceAt(record, Color.YELLOW, new Rectangle2D.Double(60, 200, 47.5, 40));
    assertColorIsDrawnOnceAt(record, Color.GREEN, new Rectangle2D.Double(292.5, 200, 47.5, 40));
  }

  @Test
  public void drawCellsIllustratedSampleDifferentColors() {
    // Sample case from assignment text
    IColorGrid sampleGrid = TestCellPositionToPixelConverter.newGridFromString(String.join("\n",
        "Y--R",
        "----",
        "G--B"
    ));
    Rectangle2D rect = new Rectangle2D.Double(30, 30, 340, 240);
    double margin = 30;
    RecordGraphics2D record = singleDrawCellsRun(sampleGrid, rect, margin);

    // Check that the correct number of calls were made
    assertEquals(12, record.getFillRecordShapes().size(),
        "The drawCells method call draw 12 rectangles in the illustrated sample case");

    assertColorIsDrawnOnceAt(record, Color.YELLOW, new Rectangle2D.Double(60, 60, 47.5, 40));
    assertColorIsDrawnOnceAt(record, Color.RED, new Rectangle2D.Double(292.5, 60, 47.5, 40));
    assertColorIsDrawnOnceAt(record, Color.GREEN, new Rectangle2D.Double(60, 200, 47.5, 40));
    assertColorIsDrawnOnceAt(record, Color.BLUE, new Rectangle2D.Double(292.5, 200, 47.5, 40));
  }

  @Test
  public void drawCellsIllustratedSampleTweak() {
    // Sample case from assignment text
    IColorGrid sampleGrid = TestCellPositionToPixelConverter.newGridFromString(String.join("\n",
        "R--B",
        "----",
        "Y--G"
    ));
    Rectangle2D rect = new Rectangle2D.Double(40, 20, 342, 243);
    double margin = 30;
    RecordGraphics2D record = singleDrawCellsRun(sampleGrid, rect, margin);

    // Check that the correct number of calls were made
    assertEquals(12, record.getFillRecordShapes().size());

    assertColorIsDrawnOnceAt(record, Color.RED, new Rectangle2D.Double(70, 50, 48, 41));
    assertColorIsDrawnOnceAt(record, Color.BLUE, new Rectangle2D.Double(304, 50, 48, 41));
    assertColorIsDrawnOnceAt(record, Color.YELLOW, new Rectangle2D.Double(70, 192, 48, 41));
    assertColorIsDrawnOnceAt(record, Color.GREEN, new Rectangle2D.Double(304, 192, 48, 41));
  }

  @Test
  public void drawCellsSingleCell() {
    // Sample case from assignment text
    IColorGrid sampleGrid = TestCellPositionToPixelConverter.newGridFromString("R");
    Rectangle2D rect = new Rectangle2D.Double(20, 30, 40, 50);
    double margin = 10;
    RecordGraphics2D record = singleDrawCellsRun(sampleGrid, rect, margin);

    // Check that the correct number of calls were made
    assertEquals(1, record.getFillRecordShapes().size());
    assertColorIsDrawnOnceAt(record, Color.RED, new Rectangle2D.Double(30, 40, 20, 30));
  }

  /////////////////////////////
  // Helper methods
  /////////////////////////////

  private void assertColorIsDrawnOnceAt(RecordGraphics2D record, Color color, Rectangle2D expectedRectangle) {
    int count = 0;
    for (int i=0; i<record.getFillRecordColors().size(); i++) {
      if (record.getFillRecordColors().get(i).equals(color)) {
        assertEquals(expectedRectangle, record.getFillRecordShapes().get(i),
            "Incorrect bounds for color "+ color
        );
        count++;
      }
    }
    assertEquals(1, count, "There should be exactly one rectangle with color "+ color);
  }

  static GridView newGridView(IColorGrid grid) {
    try {
      Constructor<?> constructor = GridView.class.getConstructor(IColorGrid.class);

      // Check that the constructor is not private
      assertFalse(Modifier.isPrivate(constructor.getModifiers()),
          "The constructor GridView(IColorGrid) should not be private");

      // Create a new object using the constructor and return it
      return (GridView) constructor.newInstance(grid);
    } catch (NoSuchMethodException e) {
      if (grid != null) {
        fail("Could not find the constructor GridView(IColorGrid) in the GridView class");
      }
      try {
        Constructor<?> constructor = GridView.class.getConstructor();
        return (GridView) constructor.newInstance();
      } catch (NoSuchMethodException ex) {
        fail("Could not find the constructor GridView() or GridView(IColorGrid) in the GridView class");
      } catch (InvocationTargetException | InstantiationException | IllegalAccessException ex) {
        throw new RuntimeException(ex);
      }
    } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    throw new IllegalStateException("Should not be possible to reach this point");
  }

  public static RecordGraphics2D singleDrawCellsRun(
      IColorGrid grid, Rectangle2D rect, double margin) {
    // Get the "drawCells" method from the GridView class and invoke
    // the method with the fake Graphics2D object
    try {
      Method drawCell = GridView.class.getDeclaredMethod("drawCells",
          Graphics2D.class,
          CellColorCollection.class,
          CellPositionToPixelConverter.class
      );

      // Test that the method is static
      assertTrue(Modifier.isStatic(drawCell.getModifiers()),
          "The drawCells method should be static, and it should not use any instance variables");

      // Test that the method is private
      assertTrue(Modifier.isPrivate(drawCell.getModifiers()),
          "The drawCells method should be private");

      // Make the method accessible in case it is private
      drawCell.setAccessible(true);

      // Preparing a "fake" Graphics2D object that records stuff that
      // happens to it instead of actually drawing anything
      RecordGraphics2D g2 = new RecordGraphics2D();

      // Invoke the method
      drawCell.invoke(null, g2, grid, TestCellPositionToPixelConverter.getConverter(rect, grid, margin));
      return g2;
    } catch (NoSuchMethodException e) {
      fail("Could not find the method drawCells(Graphics2D, CellColorCollection,"
          + " CellPositionToPixelConverter) in the GridView class");
    } catch (InvocationTargetException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    throw new IllegalStateException("Should not reach this point");
  }
}

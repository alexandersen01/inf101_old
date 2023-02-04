package no.uib.inf101.colorgrid;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class TestColorGrid {


  @Test
  public void testSize() {
    IColorGrid grid = createGrid(10, 20);
    assertEquals(10, grid.rows());
    assertEquals(20, grid.cols());

    grid = createGrid(1, 1);
    assertEquals(1, grid.rows());
    assertEquals(1, grid.cols());
  }

  @Test
  public void testGetDefaultValue() {
    IColorGrid grid = createGrid(10, 20);
    assertNull(grid.get(new CellPosition(0, 0)));
    assertNull(grid.get(new CellPosition(2, 3)));
  }

  @Test
  public void testSetGetInCorners() {
    IColorGrid grid = createGrid(10, 20);
    // Set color in corners
    grid.set(new CellPosition(0, 0), Color.RED);
    grid.set(new CellPosition(0, 19), Color.GREEN);
    grid.set(new CellPosition(9, 0), Color.BLUE);
    grid.set(new CellPosition(9, 19), Color.YELLOW);

    // Get color in corners
    assertEquals(Color.RED, grid.get(new CellPosition(0, 0)));
    assertEquals(Color.GREEN, grid.get(new CellPosition(0, 19)));
    assertEquals(Color.BLUE, grid.get(new CellPosition(9, 0)));
    assertEquals(Color.YELLOW, grid.get(new CellPosition(9, 19)));
  }

  @Test
  public void testIndexOutOfBoundsException() {
    IColorGrid grid = createGrid(10, 20);

    // Test out of bounds for get
    assertThrows(IndexOutOfBoundsException.class, () -> grid.get(new CellPosition(-1, 0)));
    assertThrows(IndexOutOfBoundsException.class, () -> grid.get(new CellPosition(0, -1)));
    assertThrows(IndexOutOfBoundsException.class, () -> grid.get(new CellPosition(10, 0)));
    assertThrows(IndexOutOfBoundsException.class, () -> grid.get(new CellPosition(0, 20)));

    // Test out of bounds for set
    assertThrows(IndexOutOfBoundsException.class, () -> grid.set(new CellPosition(-1, 0), Color.RED));
    assertThrows(IndexOutOfBoundsException.class, () -> grid.set(new CellPosition(0, -1), Color.RED));
    assertThrows(IndexOutOfBoundsException.class, () -> grid.set(new CellPosition(10, 0), Color.RED));
    assertThrows(IndexOutOfBoundsException.class, () -> grid.set(new CellPosition(0, 20), Color.RED));
  }


  /**
   * Create a new ColorGrid with the given rows and cols. This method
   * will only work if you have implemented the ColorGrid class with
   * the correct parameters (two int's), otherwise the test will fail
   * when calling this method.
   *
   * @param rows  number of rows in the colorgrid to create
   * @param cols  number of columns in the colorgrid to create
   * @return    a new ColorGrid
   */
  public IColorGrid createGrid(int rows, int cols) {
    try {
      Constructor<?> c = ColorGrid.class.getConstructor(int.class, int.class);
      Object o = c.newInstance(rows, cols);
      if (o instanceof IColorGrid grid) {
        return grid;
      }
      fail("Constructor did not return an IColorGrid. This could be "
          + "because you forgot to implement the IColorGrid interface.");
    } catch (NoSuchMethodException e) {
      fail("Could not find constructor ColorGrid(int, int)");
    } catch (InvocationTargetException e) {
      fail("Constructor crashed: " + e);
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      fail("Constructor is not public: " + e);
    }
    return null;
  }
}

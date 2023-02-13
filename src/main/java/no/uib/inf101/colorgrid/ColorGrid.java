package no.uib.inf101.colorgrid;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorGrid implements IColorGrid {

  private int rows;
  private int cols;
  //TODO: create instance variable for color
  private Color color;

  /**
   * @param rows
   * @param cols
   */
  public ColorGrid(int rows, int cols) {
    //set standard values
    this.rows = rows;
    this.cols = cols;
    
}

  @Override
  public int rows() {
    return this.rows;
  }

  @Override
  public int cols() {
    return this.cols;
  }

  @Override
  public List<CellColor> getCells() {

    //create a list of lists
    List<CellColor> list = new ArrayList<CellColor>();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        list.add(new CellColor(new CellPosition(i, j), get(new CellPosition(i, j))));
      }
    }
    return list;
    
  }

  @Override
  public Color get(CellPosition pos) {
    int row = pos.row();
    int col = pos.col();
    return color;
  }

  @Override
  public void set(CellPosition pos, Color color) {
    int row = pos.row();
    int col = pos.col();

  }
}
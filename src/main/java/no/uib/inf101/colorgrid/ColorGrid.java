package no.uib.inf101.colorgrid;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorGrid implements IColorGrid {

  private int rows;
  private int cols;

  private List<List<Color>> grid;
  


  /**
   * @param rows
   * @param cols
   */
  public ColorGrid(int rows, int cols) {
    //set standard values
    this.rows = rows;
    this.cols = cols;

    //initialize the grid
    this.grid = new ArrayList<>();
    //for every row, add arraylist to this.grid and add "cols" number of null
    for (int i = 0; i < rows; i++) {
      List<Color> row = new ArrayList<>();
      for (int j = 0; j < cols; j++) {
        row.add(null);
      }
      this.grid.add(row);
    }
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

      //return a list of CellColor
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

    return this.grid.get(pos.row()).get(pos.col());
  }

  @Override
  public void set(CellPosition pos, Color color) {
    int row = pos.row();
    int col = pos.col();

    this.grid.get(row).set(col, color);

  }
}

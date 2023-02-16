package no.uib.inf101.gridview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

import no.uib.inf101.colorgrid.CellColor;
import no.uib.inf101.colorgrid.CellColorCollection;
import no.uib.inf101.colorgrid.IColorGrid;

public class GridView extends JPanel {

  //Create an IColor instance variable called grid
  private IColorGrid grid;
  static final double OUTERMARGIN = 30;
  private static final Color MARGINCOLOR = Color.LIGHT_GRAY;


  //set standard window size in pixels
  public GridView(IColorGrid grid) {
    this.setPreferredSize(new Dimension(400, 300));
    this.grid = grid;
  }

@Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGrid(g2);
  }

  //create a drawGrid method with a Graphics2D object as parameter
  private void drawGrid(Graphics2D g) {
    Graphics2D g2 = (Graphics2D) g;
    double margin = GridView.OUTERMARGIN;
    Color color = GridView.MARGINCOLOR;
    double x = margin;
    double y = margin;
    double width = this.getWidth() - (2 * margin);
    double height = this.getHeight() - (2 * margin);
    //create a Rectangle2D object with the margin as parameter
    Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
    g2.setColor(color);
    g2.fill(rect);
    
    //create a CellPositionToPixelConverter object 
    CellPositionToPixelConverter cps = new CellPositionToPixelConverter(rect, grid, margin);

    drawCells(g, grid, cps);
  }

  private static void drawCells(Graphics2D g, CellColorCollection grid, CellPositionToPixelConverter cps) {

    List<CellColor> cells = grid.getCells();
    for (CellColor cell : cells){
      Rectangle2D rect = cps.getBoundsForCell(cell.cellPosition());
      Color color = cell.color();
      if (cell.color() == null){
        color = Color.DARK_GRAY;
      }
      g.setColor(color);
      g.fill(rect);
    }
    
  }
}
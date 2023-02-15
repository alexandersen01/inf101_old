package no.uib.inf101.gridview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import no.uib.inf101.colorgrid.CellColor;
import no.uib.inf101.colorgrid.CellColorCollection;
import no.uib.inf101.colorgrid.CellPosition;
import no.uib.inf101.colorgrid.GridDimension;
import no.uib.inf101.colorgrid.IColorGrid;
import no.uib.inf101.gridview.CellPositionToPixelConverter;
import no.uib.inf101.colorgrid.ColorGrid;

public class GridView extends JPanel {

  //Create an IColor instance variable called grid
  private IColorGrid grid;
  static final double OUTERMARGIN = 30;
  private static final Color MARGINCOLOR = Color.LIGHT_GRAY;
  private static final Rectangle2D Rectangle2D = null;
  private static final GridDimension GridDimension = null;
  //create a CellPositionToPixelConverter object called cps
  private CellPositionToPixelConverter cps;



  //set standard window size in pixels
  public GridView(IColorGrid grid) {
    this.setPreferredSize(new Dimension(400, 300));
    this.grid = grid;

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawGrid(g);
    
}

  //create a drawGrid method with a Graphics2D object as parameter
  private void drawGrid(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    double margin = GridView.OUTERMARGIN;
    Color color = GridView.MARGINCOLOR;
    double x = margin;
    double y = margin;
    double width = this.getWidth() - 2 * margin;
    double height = this.getHeight() - 2 * margin;
    //create a Rectangle2D object with the margin as parameter
    g2.setColor(color);
    g2.fill(new Rectangle2D.Double(x, y, width, height));
    
    //create a CellPositionToPixelConverter object 
    CellPositionToPixelConverter cps = new CellPositionToPixelConverter(Rectangle2D, GridDimension, margin);


    drawCells(g, grid, cps);
  }

  private void drawCells(Graphics g, CellColorCollection grid, CellPositionToPixelConverter cps) {
    Graphics2D g2 = (Graphics2D) g;
    for (CellColor cell : grid.getCells()) {
      if (cell.color() == null) {
        g2.setColor(Color.DARK_GRAY);
      } else {
        g.setColor(cell.color());

      }
      Rectangle2D rect = cps.getBoundsForCell(cell.cellPosition());
      g2.fill(rect);
    }
  }
}
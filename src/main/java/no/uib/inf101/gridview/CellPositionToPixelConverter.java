package no.uib.inf101.gridview;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import no.uib.inf101.colorgrid.CellPosition;
import no.uib.inf101.colorgrid.GridDimension;

public class CellPositionToPixelConverter {
  //create instance variable Rectangle2D object box
  private Rectangle2D box;
  private GridDimension gd;
  private double margin; 
  private double cellWidth;
  private double cellX;
  private double cellHeight;
  private double cellY;

  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }
  


  //create helper method with parameters: cellPosition
  public Rectangle2D getBoundsForCell(CellPosition cellPosition) {

    this.cellWidth =  (box.getWidth() - ((gd.cols() + 1) * box.getX())) / gd.cols();
    this.cellHeight = (box.getHeight() - ((gd.rows() + 1) * box.getY())) / gd.rows();
    //box.getHeight() / (gd.rows() * 2);
    this.cellX = gd.cols() * margin + cellPosition.col() * cellWidth;
    this.cellY = gd.rows() * margin + cellPosition.row() * cellHeight;

    return new Rectangle2D.Double(this.cellX, this.cellY, this.cellWidth, this.cellHeight);
  }
  
}

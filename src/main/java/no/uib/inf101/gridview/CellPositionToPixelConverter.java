package no.uib.inf101.gridview;
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

    this.cellWidth = (box.getWidth() - (margin * (gd.cols() + 1))) / gd.cols();
    this.cellHeight = (box.getHeight() - (margin * (gd.rows() + 1))) / gd.rows();

    this.cellX = box.getX() + cellPosition.col() * (cellWidth + margin) + margin;
    this.cellY = box.getY() + cellPosition.row() * (cellHeight + margin) + margin;
  

    return new Rectangle2D.Double(this.cellX, this.cellY, this.cellWidth, this.cellHeight);
  }
  
}

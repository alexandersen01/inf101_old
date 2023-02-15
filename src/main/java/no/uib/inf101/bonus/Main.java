package no.uib.inf101.bonus;

import no.uib.inf101.colorgrid.CellPosition;
import no.uib.inf101.colorgrid.ColorGrid;
import no.uib.inf101.colorgrid.GridDimension;
import no.uib.inf101.colorgrid.IColorGrid;
import no.uib.inf101.gridview.CellPositionToPixelConverter;
import no.uib.inf101.gridview.GridView;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Main {
  public static void main(String[] args) {
    // Kopier inn main-metoden fra kursnotatene om grafikk her,
    // men tilpass den slik at du oppretter et BeautifulPicture -objekt
    // som lerret.
    ColorGrid grid = new ColorGrid(3, 4);
    grid.set(new CellPosition(0, 0), Color.RED);
    grid.set(new CellPosition(0, 3), Color.BLUE);
    grid.set(new CellPosition(2, 0), Color.YELLOW);
    grid.set(new CellPosition(2, 3), Color.GREEN);

    // Rectangle2D shape = new Rectangle2D.Float();
    // shape.setFrame(100, 150, 200,100);

    // GridDimension dim = new GridDimension(3, 4);
  }
}

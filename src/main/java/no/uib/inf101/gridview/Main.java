package no.uib.inf101.gridview;

import javax.swing.JFrame;
import java.awt.Color;
import no.uib.inf101.colorgrid.CellPosition;
import no.uib.inf101.colorgrid.ColorGrid;
import no.uib.inf101.colorgrid.IColorGrid;

public class Main {
  public static void main(String[] args) {
    IColorGrid grid = new ColorGrid(3, 4);

    grid.set(new CellPosition(0, 0), Color.RED);
    grid.set(new CellPosition(0, 3), Color.BLUE);
    grid.set(new CellPosition(2, 0), Color.YELLOW);
    grid.set(new CellPosition(2, 3), Color.GREEN);


    GridView view = new GridView(grid);
    JFrame frame = new JFrame();
    frame.setTitle("INF101");
    frame.setContentPane(view);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}

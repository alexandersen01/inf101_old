package no.uib.inf101.bonus;

import no.uib.inf101.colorgrid.CellPosition;
import no.uib.inf101.colorgrid.ColorGrid;
import no.uib.inf101.colorgrid.IColorGrid;
import java.awt.Color;

public class Main {
  public static void main(String[] args) {
    // Kopier inn main-metoden fra kursnotatene om grafikk her,
    // men tilpass den slik at du oppretter et BeautifulPicture -objekt
    // som lerret.
    // Check that we can set a new value and retrieve it again
    IColorGrid grid = new ColorGrid(3, 4);
    System.out.println(grid.rows()); // forventer 3
    System.out.println(grid.cols()); // forventer 4

    // Sjekk at standard-verdien er null      
    System.out.println(grid.get(new CellPosition(1, 2))); // forventer null

    // Sjekk at vi kan endre verdien på en gitt posisjon        
    grid.set(new CellPosition(1, 2), Color.RED);
    System.out.println(grid.get(new CellPosition(1, 2))); // forventer rød
    System.out.println(grid.get(new CellPosition(2, 1))); // forventer null
  }
}

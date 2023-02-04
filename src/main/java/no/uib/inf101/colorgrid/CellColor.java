package no.uib.inf101.colorgrid;

import java.awt.Color;

/**
 * A CellColor contains a CellPosition and a Color.
 *
 * @param cellPosition  the position of the cell
 * @param color        the color of the cell
 */
public record CellColor(CellPosition cellPosition, Color color) { }

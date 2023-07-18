package main.util;

import java.util.Objects;

/**
 * Coordinate location specified by (row, column) coordinate or (a,b,c) coordinate
 * Adapted from the starter code provided by @author at258
 */

public class Coord {
	private final int row;
	private final int col;
	private final int dir; // direction of the triangle, 0 if upward, 1 if downward

	public Coord(int row,int column) {
		this.row = row;
		this.col = column;
		dir = (row + column) % 2;
	}

	public Coord(int a, int b, int c) {
		row = -a;
		dir = row - b + c;
		col = 2*b + dir - row;
	}

	public boolean isUpwardTriangle() {
		return dir == 0;
	}

	public int getManhattanDist(Coord coord) {
		int aDist = Math.abs(getACoord() - coord.getACoord());
		int bDist = Math.abs(getBCoord() - coord.getBCoord());
		int cDist = Math.abs(getCCoord() - coord.getCCoord());
		return aDist + bDist + cDist;
	}

	public int getACoord() {
		return -row;
	}

	public int getBCoord() {
		return (row + col - dir) / 2;
	}

	public int getCCoord() {
		return (row + col - dir) / 2 - row + dir;
	}

	public String toString() {
		return "("+row+","+col+")";
	}

	public int getR() {
		return row;
	}
	public int getC() {
		return col;
	}

	public int getDir() {
		return dir;
	}

	@Override
	public boolean equals(Object o) {

		Coord coord=(Coord) o;
		return coord.row == row && coord.col == col;

	}

	@Override
	public int hashCode() {
		return Objects.hash(row, col);
	}

}

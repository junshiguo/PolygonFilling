package com.raysmond.algorithm;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JPanel;

import com.raysmond.util.Util;
/**
 * Scan-Line Filling Algorithm implementation
 * @author Raysmond
 *
 */
public class ScanLineFillPolygon {

	private Polygon polygon = new Polygon();

	private int ymin = Integer.MAX_VALUE;
	private int ymax = Integer.MAX_VALUE;

	// Initial new edges lists
	private HashMap<Integer, List<INode>> newEdges = new HashMap<Integer, List<INode>>();
	
	// Active edges table
	private List<INode> aet = new ArrayList<INode>();
	
	// Marked points to fill from the polygon
	private List<Point> points = null;

	public List<Point> getFillPoints() {
		return points;
	}

	/**
	 * Fill a polygon using Scan-Line Filling algorithm
	 */
	public void fillPolygon() {
		ymin = Util.getMin(polygon.ypoints);
		ymax = Util.getMax(polygon.ypoints);
		points = new ArrayList<Point>();
		aet = new ArrayList<INode>();

		System.out.println("ymin = " + ymin + "  ymax = " + ymax);
		for (int i = 0; i < polygon.npoints; i++) {
			System.out.println("polygon1.addPoint(" + polygon.xpoints[i] + ","
					+ polygon.ypoints[i] + ");");
		}

		initNewLineTable();
		horizonEdgeFill();
		scanEdgeFill();
	}

	/**
	 * Fill horizontal edges
	 */
	private void horizonEdgeFill() {
		for (int i = 0; i < polygon.npoints; ++i) {
			if (polygon.ypoints[i] == polygon.ypoints[(i + 1) % polygon.npoints]) {
				int x1 = polygon.xpoints[i];
				int x2 = polygon.xpoints[(i + 1) % polygon.npoints];
				if (x1 == x2)
					continue;
				if (x1 > x2) {
					x1 = x2;
					x2 = polygon.xpoints[i];
				}
				for (int x = x1; x < x2; ++x) {
					points.add(new Point(x, polygon.ypoints[i]));
				}
			}
		}
	}

	/**
	 * Scan y from ymin to ymax and fill(select) the points inside the polygon
	 */
	private void scanEdgeFill() {
		for (int y = ymin; y < ymax; y++) {
			for (INode node : newEdges.get(y)) {
				aet.add(node);
			}
			Collections.sort(aet);
			// fill line
			boolean inside = false;
			for (int i = 0; i < aet.size() - 1; i++) {
				inside = !inside;
				if (inside) {
					int startX = (int) aet.get(i).getX();
					int endX = (int) aet.get(i + 1).getX();
					for (; startX < endX; startX++) {
						points.add(new Point(startX, y));
					}
				}
			}

			// remove nodes whose ymax=y
			List<INode> newAet = new ArrayList<INode>();
			for (INode node : aet) {
				if (node.getYmax() != y) {
					node.setX(node.getX() + node.getIncx());
					newAet.add(node);
				}
			}
			aet = newAet;
		}
	}

	/**
	 * Initialize the new edges table. Set each line (ymin=<y<ymax) a list of
	 * nodes whose y is equal to 'ymin' of the line.
	 */
	private void initNewLineTable() {
		for (int y = ymin; y < ymax; ++y) {
			newEdges.put(y, new ArrayList<INode>());
		}
		for (int i = 0; i < polygon.npoints; ++i) {
			Point p1 = getPoint(i);
			Point p2 = getPoint((i + 1) % polygon.npoints);
			Point p3 = getPoint((i - 1) % polygon.npoints);
			Point p4 = getPoint((i + 2) % polygon.npoints);

			if (p1.y != p2.y) { // ignore horizontal lines
				INode node = new INode();
				node.setIncx((double) (p1.x - p2.x) / (double) (p1.y - p2.y));
				if (p2.y > p1.y) {
					node.setX(p1.x);
					if (p4.y >= p2.y)
						node.setYmax(p2.y - 1);
					else
						node.setYmax(p2.y);
					newEdges.get(p1.y).add(node);
				} else {
					node.setX(p2.x);
					if (p3.y >= p1.y)
						node.setYmax(p1.y - 1);
					else
						node.setYmax(p1.y);
					newEdges.get(p2.y).add(node);
				}
			}
		}
		// printNewEdges();
	}

	/**
	 * Print initial new edges
	 */
	private void printNewEdges() {
		System.out.println("----------------------------\nNew Edges:");
		for (int y = ymin; y < ymax; ++y) {
			if (null != newEdges.get(y) && newEdges.get(y).size() > 0) {
				System.out.println("y=" + y + ":");
				for (INode node : newEdges.get(y)) {
					System.out.println("(" + node.getX() + "," + node.getIncx()
							+ "," + node.getYmax() + ")");
				}
			}
		}
		System.out.println("----------------------------");
	}

	/**
	 * Get Point of the polygon
	 * 
	 * @param index
	 *            index of points of the polygon
	 * @return a Point object
	 */
	private Point getPoint(int index) {
		if (index >= 0 && index < polygon.npoints) {
			return new Point(polygon.xpoints[index], polygon.ypoints[index]);
		}
		return null;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
}

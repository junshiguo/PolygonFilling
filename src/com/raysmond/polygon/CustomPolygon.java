package com.raysmond.polygon;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class CustomPolygon extends JPanel {

	private static final long serialVersionUID = 4116281357999400795L;

	private List<Point> points = new ArrayList<Point>();
	private Polygon polygon;
	private Color fillColor = Color.GREEN;

	private List<Point> tmpPoints = new ArrayList<Point>();
	private boolean fillPolygon = true;

	public CustomPolygon() {

	}

	public CustomPolygon(List<Point> points, Polygon polygon, Color color) {
		this.points = points;
		this.polygon = polygon;
		this.fillColor = color;
	}

	/**
	 * Instead of using Graphics API to fill the polygon, here we just use the
	 * API to draw a point with a color.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(fillColor);
		if (polygon == null) {
			return;
		}
		g.drawPolygon(polygon);
		if (fillPolygon) {
			for (Point point : points) {
				g.fillRect((int) point.getX(), (int) point.getY(), 1, 1);
			}
		}
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	public void finishDrawPolygon() {
		repaint();
		tmpPoints.clear();
		points.clear();
		fillPolygon = true;
		if (this.getMouseListeners() != null
				&& this.getMouseListeners().length > 0)
			this.removeMouseListener(this.getMouseListeners()[0]);
	}

	public void reset() {
		fillPolygon = true;

		if (this.getMouseListeners() != null
				&& this.getMouseListeners().length > 0)
			this.removeMouseListener(this.getMouseListeners()[0]);

		tmpPoints.clear();
		points.clear();
		polygon.reset();

	}

	public void enableDrawPolygon() {
		fillPolygon = false;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Point p = e.getPoint();
				polygon.addPoint(e.getX(), e.getY());
				tmpPoints.add(p);
				if (tmpPoints.size() > 1) {
					repaint();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

}

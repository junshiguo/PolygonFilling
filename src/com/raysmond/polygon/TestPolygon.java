package com.raysmond.polygon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class TestPolygon extends JPanel {

	private static final long serialVersionUID = 7753519818685684452L;

	private Polygon polygon;

	public TestPolygon() {

	}

	public TestPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	public void reset() {
		polygon.reset();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.drawPolygon(polygon);
		g.fillPolygon(polygon);
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	public Polygon getPolygon() {
		return polygon;
	}

}

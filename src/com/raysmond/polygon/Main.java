package com.raysmond.polygon;

import java.awt.Polygon;

import com.raysmond.algorithm.ScanLineFillPolygon;

public class Main {
	public static void main(String[] args) {
		Frame frame = new Frame();
		
		// generate initial test polygon
		Polygon polygon1 = generateTestPolygon();
		
		// fill the polygon using Scan-Line Filling algorithm
		ScanLineFillPolygon fill = new ScanLineFillPolygon();
		fill.setPolygon(polygon1);
		fill.fillPolygon();
		
		CustomPolygon polygon = new CustomPolygon();
		polygon.setPoints(fill.getFillPoints());
		polygon.setPolygon(fill.getPolygon());
		
		frame.init(polygon);

		// fill the polygon using JAVA graphics API
		TestPolygon testpolygon = new TestPolygon(polygon1);
		frame.setTestPolygon(testpolygon);
		frame.initTestPolygon();
		
		frame.setVisible(true);
	}
	
	
	public static Polygon generateTestPolygon(){
		Polygon polygon1 = new Polygon();

		polygon1.addPoint(117, 89);
		polygon1.addPoint(112, 119);
		polygon1.addPoint(79, 152);
		polygon1.addPoint(49, 197);
		polygon1.addPoint(46, 228);
		polygon1.addPoint(39, 286);
		polygon1.addPoint(69, 277);
		polygon1.addPoint(69, 216);
		polygon1.addPoint(78, 197);
		polygon1.addPoint(93, 205);
		polygon1.addPoint(91, 248);
		polygon1.addPoint(91, 269);
		polygon1.addPoint(91, 286);
		polygon1.addPoint(96, 297);
		polygon1.addPoint(130, 301);
		polygon1.addPoint(128, 261);
		polygon1.addPoint(111, 221);
		polygon1.addPoint(124, 225);
		polygon1.addPoint(135, 245);
		polygon1.addPoint(152, 286);
		polygon1.addPoint(178, 311);
		polygon1.addPoint(197, 281);
		polygon1.addPoint(146, 212);
		polygon1.addPoint(173, 210);
		polygon1.addPoint(206, 239);
		polygon1.addPoint(242, 271);
		polygon1.addPoint(224, 213);
		polygon1.addPoint(162, 171);
		polygon1.addPoint(123, 166);
		polygon1.addPoint(154, 125);
		polygon1.addPoint(136, 70);
		polygon1.addPoint(87, 58);
		polygon1.addPoint(78, 80);
		polygon1.addPoint(75, 109);
		polygon1.addPoint(73, 130);
		polygon1.addPoint(73, 138);
		
		return polygon1;
	}
}

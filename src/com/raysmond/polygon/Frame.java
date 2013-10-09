package com.raysmond.polygon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.raysmond.algorithm.ScanLineFillPolygon;

public class Frame extends JFrame {

	private static final long serialVersionUID = -7781721579473773351L;

	// Custom polygon is filled using custom algorithms
	private CustomPolygon polygon = new CustomPolygon();
	
	// Test polygon is filled using JAVA graphics API
	private TestPolygon testPolygon = null;
	
	private JPanel northPane = new JPanel();
	private JButton butClear = new JButton("Clear");
	private JButton butStart = new JButton("Start");
	private JButton butEnd = new JButton("Finish");
	

	public Frame() {
		setTitle("多边形填充 v1.0");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initNorthPane();
		addListeners();
	}

	public void init(CustomPolygon p) {
		setLayout(null);
		polygon = p;
		polygon.setBounds(0, 50, 400, 550);
		add(polygon);
	}

	private void initNorthPane() {
		northPane.setBounds(0, 0, 800, 50);
		northPane.setLayout(null);
		butClear.setBounds(10, 10, 80, 30);
		butStart.setBounds(100, 10, 80, 30);
		butEnd.setBounds(200, 10, 80, 30);
		northPane.add(butClear);
		northPane.add(butStart);
		northPane.add(butEnd);

		butEnd.setEnabled(false);

		add(northPane);

	}

	private void addListeners() {
		butClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				polygon.reset();
				polygon.repaint();

				butStart.setEnabled(true);
				butEnd.setEnabled(false);
				
				testPolygon.reset();
			}
		});
		butStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				polygon.reset();
				polygon.repaint();
				polygon.enableDrawPolygon();
				butStart.setEnabled(false);
				butEnd.setEnabled(true);
			}
		});

		butEnd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				polygon.finishDrawPolygon();
				ScanLineFillPolygon fill = new ScanLineFillPolygon();
				fill.setPolygon(polygon.getPolygon());
				fill.fillPolygon();
				polygon.setPoints(fill.getFillPoints());
				polygon.repaint();

				// Fill the polygon with JAVA Graphics API
				testPolygon.setPolygon(polygon.getPolygon());
				testPolygon.repaint();

				butStart.setEnabled(true);
				butEnd.setEnabled(false);
			}
		});
	}

	public void initTestPolygon() {
		testPolygon.setBounds(400, 50, 400, 550);
		add(testPolygon);
	}

	public TestPolygon getTestPolygon() {
		return testPolygon;
	}

	public void setTestPolygon(TestPolygon testPolygon) {
		this.testPolygon = testPolygon;
	}
}

package com.raysmond.algorithm;

public class INode implements Comparable<INode>{
	private double x;
	private double incx;
	private int ymax;
	
	public INode(){
		
	}
	
	public INode(int x,double incx,int ymax){
		this.x = x;
		this.incx = incx;
		this.ymax = ymax;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double d) {
		this.x = d;
	}
	public double getIncx() {
		return incx;
	}
	public void setIncx(double incx) {
		this.incx = incx;
	}
	public int getYmax() {
		return ymax;
	}
	public void setYmax(int ymax) {
		this.ymax = ymax;
	}

	@Override
	public int compareTo(INode other) {
		if((this.x-other.x)>0.0) return 1;
		else if((this.x-other.x)<0.0) return -1;
		else return 0;
	}

}

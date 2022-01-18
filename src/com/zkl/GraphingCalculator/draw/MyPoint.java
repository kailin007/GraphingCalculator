package com.zkl.GraphingCalculator.draw;

public class MyPoint {
	public double x = 0;
	public double y = 0;
	
	public MyPoint(double x,double y)
	{
		this.x = x;
		this.y = y;
	}
	public MyPoint(){}
	public boolean SetPoint(double x,double y)
	{
		this.x = x;
		this.y = y;
		return true;
	}
}

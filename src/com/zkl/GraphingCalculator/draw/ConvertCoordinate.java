package com.zkl.GraphingCalculator.draw;

import android.R.integer;

public class ConvertCoordinate {
	int originX = 0;
	int originY = 0;
	//real resolution / cartesian resolution(example:720/10=72)
	float convertRatio = 0;
	//constructor
	public ConvertCoordinate(int originX, int originY,float convertRatio)
	{
		this.originX = originX;
		this.originY = originY;
		this.convertRatio = convertRatio;
	}
	public MyPoint ToRealCoordinate(MyPoint cartesianPoint)
	{
		MyPoint realPoint = new MyPoint();
		realPoint.x = cartesianPoint.x * convertRatio + originX;
		realPoint.y = originY - cartesianPoint.y * convertRatio;
		return realPoint;
	}
	//Overloading
	public MyPoint[] ToRealCoordinate(MyPoint[] cartesianPoint)
	{
		MyPoint[] realPoint = new MyPoint[cartesianPoint.length];
		for (int i = 0; i < cartesianPoint.length; i++) {
			realPoint[i].x = cartesianPoint[i].x * convertRatio + originX;
			realPoint[i].y = originY - cartesianPoint[i].y * convertRatio;
		}
		return realPoint;
	}
	
	public float XToRealCoordinate(float x)
	{
		float realX = 0;
		realX = x * convertRatio + originX;
		return realX;
	}
	//Overloading
	public float[] XToRealCoordinate(float[] x)
	{
		float[] realX = new float[x.length];
		for(int i=0;i<x.length;i++){
			realX[i] = x[i] * convertRatio + originX;
		}
		return realX;
	}
	
	public float YToRealCoordinate(float y)
	{
		float realY = 0;
		realY = originY - y * convertRatio;
		return realY;
	}
	//Overloading
	public float[] YToRealCoordinate(float[] y)
	{
		float[] realY = new float[y.length];
		for(int i=0;i<y.length;i++){
			realY[i] = originY - y[i] * convertRatio;
		}
		return realY;
	}
	
	public MyPoint ToCartesianCoordinate(MyPoint realPoint)
	{
		MyPoint cartesianPoint = new MyPoint();
		cartesianPoint.x = (realPoint.x - originX) / convertRatio;
		cartesianPoint.y = (originY - realPoint.y) / convertRatio;
		return cartesianPoint;
	}
	//Overloading
	public MyPoint[] ToCartesianCoordinate(MyPoint[] realPoint)
	{
		MyPoint[] cartesianPoint = new MyPoint[realPoint.length];
		for (int i = 0; i < realPoint.length; i++) {
			cartesianPoint[i].x = (realPoint[i].x - originX) / convertRatio;
			cartesianPoint[i].y = (originY - realPoint[i].y) / convertRatio;
		}
		return cartesianPoint;
	}
	public float XToCartesianCoordinate(float x)
	{
		float cartesianX = 0;
		cartesianX = (x - originX) / convertRatio;
		return cartesianX;
	}
	//Overloading
	public float[] XToCartesianCoordinate(float[] x)
	{
		float[] cartesianX = new float[x.length];
		for (int i = 0; i < x.length; i++) {
			cartesianX[i] = (x[i] - originX) / convertRatio;
		}
		return cartesianX;
	}
	public float YToCartesianCoordinate(float y)
	{
		float cartesianY = 0;
		cartesianY = (originY - y) / convertRatio;
		return cartesianY;
	}
	//Overloading
	public float[] YToCartesianCoordinate(float[] y)
	{
		float[] cartesianY = new float[y.length];
		for (int i = 0; i < y.length; i++) {
			cartesianY[i] = (originY - y[i]) / convertRatio;
		}
		return cartesianY;
	}
	public float LenthToRealCoordinate(float lenth)
	{
		float realLenth = 0;
		realLenth = lenth * convertRatio;
		return realLenth;
	}
	public float LenthToCartesianCoordinate(float lenth)
	{
		float cartesianLenth = 0;
		cartesianLenth = lenth / convertRatio;
		return cartesianLenth;
	}
	
	public boolean[] MoveXY(boolean[] functionPoints,int oldOriginX,int oldOriginY,int newOriginX,int newOriginY,int screenW,int screenH){
		int xDValue = newOriginX - oldOriginX;
		int yDValue = newOriginY - oldOriginY;
		boolean[] newFunctionPoints = new boolean[functionPoints.length];
		for (int i = 0; i < screenW; i++) {
			for (int j = 0; j < screenH; j++) {
				if (functionPoints[i+j*screenW]) {
					if (i+xDValue+(j+yDValue)*screenW >= 0 && i+xDValue+(j+yDValue)*screenW < newFunctionPoints.length) {
						newFunctionPoints[i+xDValue+(j+yDValue)*screenW] = true;
					}
				}
			}
		}
		return newFunctionPoints;
	}
	
	//getter&setter
	public float getConvertRatio() {
		return convertRatio;
	}
	public void setConvertRatio(float convertRatio) {
		this.convertRatio = convertRatio;
	}
	public void set(int originX, int originY,float convertRatio)
	{
		this.originX = originX;
		this.originY = originY;
		this.convertRatio = convertRatio;
	}
}

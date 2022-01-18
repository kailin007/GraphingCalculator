package com.zkl.GraphingCalculator.draw;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.GraphingCalculator.R;
import com.sunbx.GraphingCalculator.calculate.FinalCalculate;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.Layout.Alignment;
import android.util.FloatMath;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class MainUI extends SurfaceView implements Callback, Runnable {
	final int coordinateColor = Color.BLACK;
	final int backgroundColor = Color.WHITE;
	final int gridColor = Color.GRAY;
	final int imformationColor = Color.argb(255, 128, 0, 128);
	final int functionColor = Color.argb(255, 128, 0, 128);
	final int chooseColor = Color.argb(128, 255, 0, 0);
	final int defaultPointsDensity = 10;
	final int defaultLineThickness = 3;
	final int defaultUnitNum = 1;
	final int nodeNumRatio_enlarge = 1;
	final int nodeNumRatio_shrink = -1;
	final int defaultTextSize = 20;
	//SurfaceView controller
	private SurfaceHolder sfh = null;
	//Thread
	private Thread th = null;
	//Flag for thread
	public boolean isThreadAlive = true;
	//Canvas
	private Canvas canvas = null;
	//paint
	Paint paint = null;
	//Screen resolution
	int screenW,screenH;
	//Middle point 
	int originX=0,originY=0;
	int newOriginX = 0, newOriginY = 0;
	//Short side resolution / cartesian resolution(example:720/10=72)
	float convertRatio = 0;
	//Source expresssion string
	String source = null;
	//CartesianLenth(cartesian coordinate)
	int initialCartesianLenth = 9;
	float cartesianLenth = initialCartesianLenth;
	float tempCartesianLenth =initialCartesianLenth;
	//Extra lenth
	int extraComputeLenthInRealCoordinate = 0;
	//Old x
	float oldX = 0;
	//Old 
	float oldCartesianLenth = cartesianLenth;
	//CoordinateConvert
	ConvertCoordinate convert = null;
	//Point
	int allPointNum = 0;
	float[] realX = null;
	float[] realY = null;
	float[] cartesianX = null;
	float[] cartesianY = null;
	int[] realX_int = null;
	int[] realY_int = null;
	int[] realX_int_temp = null;
	int[] realY_int_temp = null;
	//Calculator
	FinalCalculate cl = null;
	//Flag
	public boolean isFunctionNeedReCalculate = true;
	public boolean isScreenNeedRedefine = true;

	//font
	String fontPath = "fonts/STHeiti-Light.ttc";
	
	//node
	float unitLenth = 1;
	float nodeLenth = 0;
	float nodeNumLenth = 0;
	float unitNodeNum = 1;
	int nodeNumRatio = 0;
	boolean shrinkFlag = true;
	boolean isNodeLenthNeedRecalculate = true;
	//
	int shortSide = 0;
	int longSide = 0;
	//Text size
	public float myTextSize = defaultTextSize;
	public boolean isNeedGetInitialTextSize = true;
	//Choose point
	public float chooseX = 0;
	public float chooseY = 0;
	public boolean isChoosePoint = false;
	boolean isChoosePointNeedRecalculate = true;
	public boolean isPointOnFunction =false;
	//	flag
	boolean isOnMultiTouchEvent = false;
	//Thickness
	int coordinateThickness = defaultLineThickness;
	int nodeThickness = 5;
	int gridThickness = 1;
	public int functionThickness = defaultLineThickness;
	//context
	Context context = null;
	//System configuration
	public boolean isShowGrid = true;
	public boolean isShowInformation = true;
	public boolean isShowFunctionEquation = true;
	public boolean isShowOriginPointPosition = true;
	public boolean isShowCartesianLenth = true;
	public int pointsDensity = defaultPointsDensity;
	public int accuracy = 2;
	
	boolean[] functionPoints = null;
	//TODO
	/**
	 * SurfaceView初始化函数
	 */
	public MainUI(Context context ,String source) {
		super(context);
		this.context = context;
		//实例SurfaceHolder
		sfh = this.getHolder();
		//为SurfaceView添加状态监听
		sfh.addCallback(this);

		this.source = source;
		paint = new Paint();
		paint.setColor(coordinateColor);
		paint.setTextSize(myTextSize);
		Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
		//paint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD_ITALIC));
		//设置焦点
		setFocusable(true);
	}
	/**
	 * SurfaceView视图创建，响应此函数
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		isThreadAlive = true;
		//实例线程
		th = new Thread(this);
		//启动线程
		th.start();
	}
//	long intertialOldTimeSlice = 0;
//	long intertialNewTimeSlice = 0;
	public void myDraw() {
		try {
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				//Assign value
				if (isScreenNeedRedefine) {
					screenW = this.getWidth();
					screenH = this.getHeight();
					originX = screenW / 2;
					originY = screenH /2;
					newOriginX = originX;
					newOriginY = originY;
					extraComputeLenthInRealCoordinate = screenW;
					oldX = originX;
					functionPoints = new boolean[screenW*screenH];
					if (screenW<screenH) {
						shortSide = screenW;
						longSide = screenH;
					} else {
						shortSide = screenH;
						longSide = screenW;
					}
					//text size = 24
					if(isNeedGetInitialTextSize){
						myTextSize = shortSide / 30.0f;
						if (myTextSize < 13.0f) {
							myTextSize = 13;
						}
					}
					paint.setTextSize(myTextSize);
					//cartesianLenth = 9
					initialCartesianLenth = shortSide/80;
					cartesianLenth = initialCartesianLenth;
					tempCartesianLenth =initialCartesianLenth;
				}
				//get origin x&y
				originX = newOriginX;
				originY = newOriginY;
				//get cartesianLenth
				cartesianLenth = tempCartesianLenth;
				//refresh convertRatio
				convertRatio = shortSide / cartesianLenth;
				//Convert Object
				convert = new ConvertCoordinate(originX, originY, convertRatio);
				if (isScreenNeedRedefine) {
					unitLenth = 1;
					nodeLenth = convert.LenthToRealCoordinate(unitLenth);
					nodeNumLenth = convert.LenthToRealCoordinate(unitLenth);
					isScreenNeedRedefine = false;
				}
//				//Intertial
//				if (currentSpeed > 0 && isOnMoveEvent == false) {123
//					float a = currentSpeed * -0.0001f;
//					if (a > -0.1f) {
//						a = -0.1f;
//					}
//					intertialNewTimeSlice = System.currentTimeMillis();
//					float xSpeed = currentSpeed * (float)Math.sin(moveAngel);
//					float ySpeed = currentSpeed * (float)Math.cos(moveAngel);
//					long timeDifference = intertialNewTimeSlice - intertialOldTimeSlice;
//					originX -= xSpeed * timeDifference;
//					originY -= ySpeed * timeDifference;
//					currentSpeed += a * timeDifference;
//				}
//				intertialOldTimeSlice = System.currentTimeMillis();
				//Paint part
				//Draw background color
				canvas.drawColor(backgroundColor);
				//Draw coordinate
				drawCoordinate(canvas);
				//Draw function
				drawFunction(canvas);
				//Draw node&grid
				drawNodeAndGrid(canvas);
				//Draw node&grid
				drawImformation(canvas);
				if (isChoosePoint) {
					drawChoosePoint(canvas);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null)
				sfh.unlockCanvasAndPost(canvas);
		}
	}
	/**
	 * 触屏事件监听
	 */
	//Touch event
	int mode = 0;
	float oldDist;
	float textSize = 0;
	int oldPointX = 0;
	int oldPointY = 0;
	boolean multitouch = false;
	boolean isClick = true;
	float disOldX = 0;
	float disOldY = 0;
	float disNewX = 0;
	float disNewY = 0;
	float midPointX = 0;
	float midPointY = 0;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			oldPointX = (int)event.getX();
			oldPointY = (int)event.getY();
			disOldX = oldPointX;
			disOldY = oldPointY;
			disNewX = oldPointX;
			disNewY = oldPointY;
			multitouch = false;
			mode = 1;
			isClick = true;
			break;
		case MotionEvent.ACTION_UP:
			mode = 0;
			if(isClick){
				isChoosePoint = true;
				isChoosePointNeedRecalculate = true;
				chooseX = convert.XToCartesianCoordinate((int)event.getX());
				chooseY = convert.YToCartesianCoordinate((int)event.getY());
			}
		isOnMultiTouchEvent = false;
			break;
		case MotionEvent.ACTION_POINTER_UP:
			mode -= 1;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = Spacing(event);
			multitouch = true;
			mode += 1;
			isClick = false;
			break;

		case MotionEvent.ACTION_MOVE:
			if (Math.abs(oldPointX - event.getX()) > 5 || Math.abs(oldPointY - event.getY()) > 5) {
				isClick = false;
			}
			
			//MutileTouch event
			if (mode >= 2) {
				//get current midpoint
				if (!isOnMultiTouchEvent) {
					midPointX = convert.XToCartesianCoordinate(screenW / 2);
					midPointY = convert.YToCartesianCoordinate(screenH / 2);
				}
				isOnMultiTouchEvent = true;
				//Recalculate function coordinate to adapt zoom
				if (oldCartesianLenth/cartesianLenth > 1.2 || oldCartesianLenth/cartesianLenth < 0.8) {
					isFunctionNeedReCalculate = true;
					//Refresh
					oldCartesianLenth = cartesianLenth;
					}
				float newDist = Spacing(event);
				if (newDist > oldDist + 1) {
					isNodeLenthNeedRecalculate = true;
					Zoom(newDist / oldDist);
					oldDist = newDist;
				}
				if (newDist < oldDist - 1) {
					isNodeLenthNeedRecalculate = true;
					Zoom(newDist / oldDist);
					oldDist = newDist;
				}
			}
			//Move event
			else if(mode == 1){
				if (Math.abs(oldX-originX) > extraComputeLenthInRealCoordinate*2/3) {
					isFunctionNeedReCalculate = true;
					oldX = originX;
				}
				if (!multitouch) {
					MoveEvent(event);
//					newTimeSlice = System.currentTimeMillis();
//					disNewX = event.getX();
//					disNewY = event.getY();
//					float distance = FloatMath.sqrt((disOldX - disNewX) * (disOldX - disNewX) + (disOldY - disNewY) * (disOldY - disNewY));
//					currentSpeed = distance / (newTimeSlice - oldTimeSlice);
//					oldTimeSlice = newTimeSlice;
//					//Get angel
//					float k1 = (disNewY - disOldY) / (disNewX - disOldX);
//					float k2 = 0;
//					if (disOldX == disNewX) {
//						moveAngel = (float)Math.PI / 2;
//					} else {
//						moveAngel = (float)Math.atan((k1-k2)/(1+k1*k2));
//						//Quadrant 1
//						if((disNewX - disOldX > 0 && disNewY - disOldY < 0)){
//							
//						}
//						//Quadrant 4
//						else if((disNewX - disOldX > 0 && disNewY - disOldY > 0)){
//							moveAngel = (float)Math.PI + moveAngel;
//						}
//						//Quadrant 2
//						else if(disNewX - disOldX < 0 && disNewY - disOldY < 0){
//							//moveAngel = (float)Math.PI - moveAngel;
//						}
//						//Quadrant 3
//						else{
//							moveAngel = (float)Math.PI + moveAngel;
//						}
//					}
//					disOldX = disNewX;
//					disOldY = disNewY;
				}
			}
			break;
		}
		return true;
	}
	/**
	 * 按键事件监听
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//Volume up is on click
				if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
					Zoom(1.2f);
					return true;
				}
				//Volume down is on click
				else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
					Zoom(0.8f);
					return true;
				}
		return super.onKeyDown(keyCode, event);
	}
	//Move event
		private void MoveEvent(MotionEvent event){
			int newPointX = (int)event.getX();
			int newPointY = (int)event.getY();
			newOriginX += newPointX - oldPointX;
			newOriginY += newPointY - oldPointY;
			oldPointX = newPointX;
			oldPointY = newPointY;
		}
		//Zoom
		private void Zoom(float f) {
			tempCartesianLenth /= f;
			//zoom surround midpoint
			newOriginX = (int)(screenW / 2 - midPointX * shortSide / tempCartesianLenth);
			newOriginY = (int)(screenH / 2 + midPointY * shortSide / tempCartesianLenth);
		}
		//Calculating distance between gesture
		private float Spacing(MotionEvent event) {
			float x = event.getX(0) - event.getX(1);
			float y = event.getY(0) - event.getY(1);
			return FloatMath.sqrt(x * x + y * y);
		}
		private void drawCoordinate(Canvas canvas){
			paint.setColor(coordinateColor);
			drawLine_XBold(originX, 0, originX, screenH-1, coordinateThickness, canvas, paint);
			drawLine_YBold(0, originY, screenW-1, originY, coordinateThickness, canvas, paint);
		}
		private void drawFunction(Canvas canvas){
			//Recalculate points
			if(isFunctionNeedReCalculate){
				cl = new FinalCalculate();
				//Assign value
				allPointNum = (screenW + 2 * extraComputeLenthInRealCoordinate) * pointsDensity;
				realX = new float[allPointNum];
				realY = new float[allPointNum];
				cartesianX = new float[allPointNum];
				cartesianY = new float[allPointNum];
				realX_int = new int[allPointNum];
				realY_int = new int[allPointNum];
				for (int i = 0; i < allPointNum; i++) {
					realX[i] = (i * 1.0f - extraComputeLenthInRealCoordinate*pointsDensity) / pointsDensity;
					cartesianX[i] = convert.XToCartesianCoordinate(realX[i]);
				}
				//temp**************************************************************
				double[] temp = new double[cartesianX.length];
				for (int i = 0; i < temp.length; i++) {
					temp[i] = cartesianX[i];
				}
				//*********************************************
				//cartesianY = cl.finalCalculate(source, cartesianX);
				//*********************************************
				temp = cl.finalCalculate(source, temp);
				for (int i = 0; i < temp.length; i++) {
					cartesianY[i] = (float)temp[i];
				}
				//**********************************************************************
				isFunctionNeedReCalculate = false;
			}
			//Get realX&realY every time
			realX = convert.XToRealCoordinate(cartesianX);
			realY = convert.YToRealCoordinate(cartesianY);
			//Draw function
			paint.setColor(functionColor);
			//clear
			for (int i = 0; i < functionPoints.length; i++) {
				functionPoints[i] = false;
			}
			for (int i = 0; i < allPointNum; i++) {
				if (realX[i] > -0.5f && realX[i] < screenW-0.5f && realY[i] > -0.5f && realY[i] < screenH-0.5f) {
					addPointsToFunction((int)Math.rint(realX[i]), (int)Math.rint(realY[i]), functionThickness);
				}
			}
			for (int i = 0; i < screenW; i++) {
				for (int j = 0; j < screenH; j++) {
					if (functionPoints[i+j*screenW]) {
						canvas.drawPoint(i, j, paint);
					}
				}
			}
		}
		private void addPointsToFunction(int x,int y, int thickness){
			for (int i = 0; i < thickness; i++) {
				if ((x - thickness/2 + i) + y*screenW<functionPoints.length && (x - thickness/2 + i) + y*screenW >= 0) {
					if (functionPoints[(x - thickness/2 + i) + y*screenW] == false) {
						functionPoints[(x - thickness/2 + i) + y*screenW] = true;
					}
				}
				if (x+(y - thickness/2 + i)*screenW<functionPoints.length && x+(y - thickness/2 + i)*screenW >= 0) {
					if(functionPoints[x+(y - thickness/2 + i)*screenW] == false){
						functionPoints[x+(y - thickness/2 + i)*screenW] = true;
					}
				}
			}
		}
		
		int XPointDValue = 0;
		int YPointDValue = 0;
		int XNodeLenthInterval = 0;
		int YNodeLenthInterval = 0;
		int XNodeNumLenthInterval = 0;
		int YNodeNumLenthInterval = 0;
		float nodeControlpoint = 0;
		int nodeCounter = 0;
		int shrinkTimes = 0;
		int enlargeTimes = 0;
		int currentStep = 1;
		private void drawNodeAndGrid(Canvas canvas){
			if(isNodeLenthNeedRecalculate){
				//Shrink
				//Step1
				if (unitLenth < (1.0f/(2*initialCartesianLenth))*cartesianLenth) {
					//logic
					if (currentStep == 1) {
						if (shrinkFlag) {
							changeNodeNumRatio(nodeNumRatio_enlarge);
							shrinkFlag = false;
						}
					}
					else if (currentStep == 3) {
						if (shrinkFlag) {
							changeNodeNumRatio(nodeNumRatio_enlarge);
							shrinkFlag = false;
						}
					}
					else if (currentStep == 4) {
						changeNodeNumRatio(nodeNumRatio_enlarge);
						
						if (!shrinkFlag) {
							changeNodeNumRatio(nodeNumRatio_shrink);
							shrinkFlag = true;
						}
						if (shrinkFlag) {
							changeNodeNumRatio(nodeNumRatio_enlarge);
							shrinkFlag = false;
						}
					}
					currentStep = 1;
					
					shrinkTimes = 0;
					while(unitLenth < (1.0f/(2*initialCartesianLenth))*cartesianLenth){
						if(shrinkTimes>0){
							if (shrinkFlag) {
								changeNodeNumRatio(nodeNumRatio_enlarge);
								shrinkFlag = false;
							}
						}
						unitLenth *= 2;
						nodeLenth = convert.LenthToRealCoordinate(unitLenth);
						nodeNumLenth = convert.LenthToRealCoordinate(unitLenth);
						shrinkFlag = true;
						shrinkTimes++;
					}
				} 
				//Step2
				else if(unitLenth >= (1.0f/(2*initialCartesianLenth))*cartesianLenth && unitLenth < (1.0f/initialCartesianLenth)*cartesianLenth){
					if (currentStep == 4) {
						if (!shrinkFlag) {
							changeNodeNumRatio(nodeNumRatio_shrink);
							shrinkFlag = true;
						}
					}
					currentStep = 2;
					
					nodeLenth = convert.LenthToRealCoordinate(unitLenth);
					nodeNumLenth = convert.LenthToRealCoordinate(unitLenth) * 2;
					if (shrinkFlag) {
						changeNodeNumRatio(nodeNumRatio_enlarge);
						shrinkFlag = false;
					}
				}
				//Enlarge
				//Step3
				else if(unitLenth >= (1.0f/initialCartesianLenth)*cartesianLenth && unitLenth < (2.0f/initialCartesianLenth)*cartesianLenth){
					if (currentStep == 1) {
						if (shrinkFlag) {
							changeNodeNumRatio(nodeNumRatio_enlarge);
							shrinkFlag = false;
						}
					}
					currentStep = 3;
					
					nodeLenth = convert.LenthToRealCoordinate(unitLenth) / 2;
					nodeNumLenth = convert.LenthToRealCoordinate(unitLenth);
					if (!shrinkFlag) {
						changeNodeNumRatio(nodeNumRatio_shrink);
						shrinkFlag = true;
					}
				}
				//Step4
				else if(unitLenth >= (2.0f/initialCartesianLenth)*cartesianLenth) {
					if (currentStep == 1) {
						//changeNodeNumRatio(nodeNumRatio_enlarge);
						if (shrinkFlag) {
							changeNodeNumRatio(nodeNumRatio_enlarge);
							shrinkFlag = false;
						}
						if (!shrinkFlag) {
							changeNodeNumRatio(nodeNumRatio_shrink);
							shrinkFlag = true;
						}
					}
					else if (currentStep == 2) {
						if (!shrinkFlag) {
							changeNodeNumRatio(nodeNumRatio_shrink);
							shrinkFlag = true;
						}
					}
					else if (currentStep == 4) {
						if (!shrinkFlag) {
							changeNodeNumRatio(nodeNumRatio_shrink);
							shrinkFlag = true;
						}
					}
					currentStep = 4;
					
					enlargeTimes = 0;
					while(unitLenth >= (2.0f/initialCartesianLenth)*cartesianLenth){
						if(enlargeTimes > 0){
							if (!shrinkFlag) {
								changeNodeNumRatio(nodeNumRatio_shrink);
								shrinkFlag = true;
							}
						}
						unitLenth /= 2;
						nodeLenth = convert.LenthToRealCoordinate(unitLenth);
						nodeNumLenth = convert.LenthToRealCoordinate(unitLenth);
						changeNodeNumRatio(nodeNumRatio_shrink);
						enlargeTimes++;
					}
				}
			}
//			int XPointDValue = originX - 0;
//			int YPointDValue = originY - 0;
//			int XNodeLenthInterval = (int)(XPointDValue / nodeLenth);
//			int YNodeLenthInterval = (int)(YPointDValue / nodeLenth);
//			int XNodeNumLenthInterval = (int)(XPointDValue / nodeNumLenth);
//			int YNodeNumLenthInterval = (int)(YPointDValue / nodeNumLenth);
//			float point = 0;
			XPointDValue = originX - 0;
			YPointDValue = originY - 0;
			XNodeLenthInterval = (int)(XPointDValue / nodeLenth);
			YNodeLenthInterval = (int)(YPointDValue / nodeLenth);
			XNodeNumLenthInterval = (int)(XPointDValue / nodeNumLenth);
			YNodeNumLenthInterval = (int)(YPointDValue / nodeNumLenth);
			
			//Draw Y axis node & X axis grid
			if (YNodeLenthInterval > 0) {
				nodeCounter=YNodeLenthInterval;
			} 
			else if(YNodeLenthInterval == 0){
				if (YPointDValue / nodeLenth > 0) {
					nodeCounter=YNodeLenthInterval;
				} else {
					nodeCounter=YNodeLenthInterval - 1;
				}
			}
			else {
				nodeCounter=YNodeLenthInterval - 1;
			}
			while (true) {
				nodeControlpoint = originY - nodeCounter * nodeLenth;
				if(nodeControlpoint >= 0 && nodeControlpoint < screenH){
					if (isShowGrid) {
						//draw grid
						paint.setColor(gridColor);
						drawLine_XBold(0, nodeControlpoint, screenW-1, nodeControlpoint, gridThickness, canvas, paint);
					}
					if(originX >= 0 && originX < screenW){
						//draw node
						paint.setColor(coordinateColor);
						drawPoint_XBold(originX, nodeControlpoint, coordinateThickness+nodeThickness*2, canvas, paint);
						drawPoint_XBold(originX, nodeControlpoint+1, coordinateThickness+nodeThickness*2, canvas, paint);
						drawPoint_XBold(originX, nodeControlpoint-1, coordinateThickness+nodeThickness*2, canvas, paint);
					}
				}
				else{
					break;
				}
				nodeCounter--;
			}
			//Draw Y num
			if (YNodeNumLenthInterval > 0) {
				nodeCounter=YNodeNumLenthInterval;
			}
			else if(YNodeNumLenthInterval == 0){
				if (YPointDValue / nodeNumLenth > 0) {
					nodeCounter=YNodeNumLenthInterval;
				} else {
					nodeCounter=YNodeNumLenthInterval - 1;
				}
			}
			else {
				nodeCounter=YNodeNumLenthInterval - 1;
			}
			paint.setColor(coordinateColor);
			while (true) {
				nodeControlpoint = originY - nodeCounter * nodeNumLenth;
				if(nodeControlpoint >=0 && nodeControlpoint < screenH && originX >= 0 && originX < screenW){
					if(getNodeNum(nodeCounter) != 0){
						//draw num
						//canvas.drawText(getNodeNum(i) + "", originX+nodeThickness+3, point+3, paint);
						canvas.drawText(getNodeNum(nodeCounter) + "", originX+nodeThickness*2, nodeControlpoint+nodeThickness, paint);
					}
				}
				else{
					break;
				}
				nodeCounter--;
			}
			//Draw X axis node & Y axis grid
			if (XNodeLenthInterval > 0) {
				nodeCounter=XNodeLenthInterval;
			} 
			else if(XNodeLenthInterval == 0){
				if (XPointDValue / nodeLenth > 0) {
					nodeCounter=XNodeLenthInterval;
				} else {
					nodeCounter=XNodeLenthInterval - 1;
				}
			}
			else {
				nodeCounter=XNodeLenthInterval - 1;
			}
			while (true) {
				nodeControlpoint = originX - nodeCounter * nodeLenth;
				if(nodeControlpoint >= 0 && nodeControlpoint < screenW){
					if(isShowGrid){
						//draw grid
						paint.setColor(gridColor);
						drawLine_YBold(nodeControlpoint, 0, nodeControlpoint, screenH, gridThickness, canvas, paint);
					}
					if(originY >= 0 && originY < screenH){
						//draw node
						paint.setColor(coordinateColor);
						drawPoint_YBold(nodeControlpoint, originY, coordinateThickness+nodeThickness*2, canvas, paint);
						drawPoint_YBold(nodeControlpoint+1, originY, coordinateThickness+nodeThickness*2, canvas, paint);
						drawPoint_YBold(nodeControlpoint-1, originY, coordinateThickness+nodeThickness*2, canvas, paint);
					}
				}
				else{
					break;
				}
				nodeCounter--;
			}
			//Draw X num
			if (XNodeNumLenthInterval > 0) {
				nodeCounter=XNodeNumLenthInterval;
			}
			else if(XNodeNumLenthInterval == 0){
				if (XPointDValue / nodeNumLenth > 0) {
					nodeCounter=XNodeNumLenthInterval;
				} else {
					nodeCounter=XNodeNumLenthInterval - 1;
				}
			}
			else {
				nodeCounter=XNodeNumLenthInterval - 1;
			}
			paint.setColor(coordinateColor);
			while (true) {
				nodeControlpoint = originX - nodeCounter * nodeNumLenth;
				if(nodeControlpoint >= 0 && nodeControlpoint < screenW && originY >= 0 && originY < screenH){
					if(getNodeNum(nodeCounter) != 0){
						//draw num
						//canvas.drawText(-getNodeNum(i) + "", point-8, originY+nodeThickness*2+10, paint);
						canvas.drawText(-getNodeNum(nodeCounter) + "", nodeControlpoint-nodeThickness*3, originY+nodeThickness*5, paint);
					}
					else{
						canvas.drawText("0", nodeControlpoint-nodeThickness*5, originY+nodeThickness*5, paint);
					}
				}
				else{
					break;
				}
				nodeCounter--;
			}
		}
		
		private void drawImformation(Canvas canvas){
			if(isShowInformation){
				String imformationString = context.getString(R.string.drawinformation_graph_information)+"\n";
				if(isShowFunctionEquation)
					imformationString += context.getString(R.string.drawinformation_function_equation)+"f(x)=" + source.substring(0, source.length()-1) + "\n";
				if(isShowOriginPointPosition)
					imformationString += context.getString(R.string.drawinformation_originpoint_position)+"(" + originX + "," + originY + ")\n";
				if(isShowCartesianLenth)
					imformationString += context.getString(R.string.drawinformation_x_axis_width) + convert.LenthToCartesianCoordinate(screenW)
							+ "\n" + context.getString(R.string.drawinformation_y_axis_width) + convert.LenthToCartesianCoordinate(screenH) + "\n";
				paint.setColor(imformationColor);
				TextPaint textPaint = new TextPaint(paint);
				StaticLayout layout = new StaticLayout(imformationString,textPaint,screenW,Alignment.ALIGN_NORMAL,1.0F,0,false);
				canvas.translate(shortSide/36,shortSide/36);
				layout.draw(canvas);
				canvas.translate(-shortSide/36,-shortSide/36);
			}
		}
		private void drawChoosePoint(Canvas canvas){
			if(isChoosePointNeedRecalculate){
				isChoosePointNeedRecalculate =false;
				isPointOnFunction =false;
				int  faultTolerantLenth = (int)(shortSide * 0.05);
				float faultTolerantLenth_cartesianCoordinate = convert.LenthToCartesianCoordinate(faultTolerantLenth);
				float[] x = new float[2*faultTolerantLenth+1];
				float[] y = new float[2*faultTolerantLenth+1];
				for (int i = 0; i < 2*faultTolerantLenth+1; i++) {
					x[i] = chooseX - faultTolerantLenth_cartesianCoordinate + convert.LenthToCartesianCoordinate(i);
				}
				//Calculate
				cl = new FinalCalculate();
				double[] temp = new double[2*faultTolerantLenth+1];
				for (int i = 0; i < temp.length; i++) {
					temp[i] = x[i];
				}
				temp = cl.finalCalculate(source, temp);
				for (int i = 0; i < temp.length; i++) {
					y[i] = (float)temp[i];
				}
				//compare
				if ((y[faultTolerantLenth]>chooseY-faultTolerantLenth_cartesianCoordinate)&&(y[faultTolerantLenth]<=chooseY+faultTolerantLenth_cartesianCoordinate)) {
					isPointOnFunction = true;
					chooseX = x[faultTolerantLenth];
					chooseY = y[faultTolerantLenth];
				} else {
					for (int i = 1; i <= (faultTolerantLenth-1)/2; i++) {
						if ((y[faultTolerantLenth+i]>chooseY-faultTolerantLenth_cartesianCoordinate)&&(y[faultTolerantLenth+i]<=chooseY+faultTolerantLenth_cartesianCoordinate)) {
							isPointOnFunction = true;
							chooseX = x[faultTolerantLenth+i];
							chooseY = y[faultTolerantLenth+i];
							break;
						}
						if ((y[faultTolerantLenth-i]>chooseY-faultTolerantLenth_cartesianCoordinate)&&(y[faultTolerantLenth-i]<=chooseY+faultTolerantLenth_cartesianCoordinate)) {
							isPointOnFunction = true;
							chooseX = x[faultTolerantLenth-i];
							chooseY = y[faultTolerantLenth-i];
							break;
						}
					}
				}
//				for (int i = 0; i < y.length-1; i++) {
//					if ((chooseY >= y[i] && chooseY < y[i+1]) || (chooseY >= y[i+1] && chooseY < y[i])) {
//						isPointOnFunction = true;
//						chooseX = x[i];
//						chooseY = y[i];
//						break;
//					}
//				}
			}
			if (isPointOnFunction) {
				//Draw point
				String imformation = null;
				if(accuracy == 0)
					imformation = context.getString(R.string.choosepoint_position) + "(" + (int)keepDecimalNum(chooseX, accuracy) +"," + (int)keepDecimalNum(chooseY, accuracy) + ")";
				else
					imformation = context.getString(R.string.choosepoint_position) + "(" + keepDecimalNum(chooseX, accuracy) +"," + keepDecimalNum(chooseY, accuracy) + ")";
				Paint p = new Paint();
				p.setColor(chooseColor);
				canvas.drawCircle(convert.XToRealCoordinate(chooseX), convert.YToRealCoordinate(chooseY), shortSide/72.0f, p);
				paint.setColor(imformationColor);
				TextPaint textPaint = new TextPaint(paint);
				StaticLayout layout = new StaticLayout(imformation,textPaint,screenW,Alignment.ALIGN_OPPOSITE,1.0F,0,false);
				canvas.translate(-shortSide/36,shortSide/36);
				layout.draw(canvas);
				canvas.translate(shortSide/36,-shortSide/36);
			} else {
				isChoosePoint = false;
			}
		}
		private void drawPoint_XBold(float x,float y,int thickness,Canvas canvas,Paint paint){
			for (int i = 0; i < thickness; i++) {
				canvas.drawPoint(x - thickness/2 + i, y, paint);
			}
		}
		private void drawPoint_YBold(float x,float y,int thickness,Canvas canvas,Paint paint){
			for (int i = 0; i < thickness; i++) {
				canvas.drawPoint(x, y - thickness/2 + i, paint);
			}
		}
		private void drawLine_XBold(float startX,float startY,float stopX,float stopY,int thickness,Canvas canvas,Paint paint){
			for (int i = 0; i < thickness; i++) {
				canvas.drawLine(startX - thickness/2 + i, startY, stopX - thickness/2 + i, stopY, paint);
			}
		}
		private void drawLine_YBold(float startX,float startY,float stopX,float stopY,int thickness,Canvas canvas,Paint paint){
			for (int i = 0; i < thickness; i++) {
				canvas.drawLine(startX, startY - thickness/2 + i, stopX, stopY - thickness/2 + i, paint);
			}
		}
		private  void changeNodeNumRatio(int shrinkOrEnlarge){
			//Enlarge
			if (shrinkOrEnlarge >= 0) {
				nodeNumRatio++;
//				if (nodeNumRatio > 0) {
//					nodeNumRatio *= 2;
//				} 
//				else if(nodeNumRatio == 0) {
//					nodeNumRatio = 1;
//				}
//				else if(nodeNumRatio == -1) {
//					nodeNumRatio = 0;
//				}
//				else if(nodeNumRatio < -1) {
//					nodeNumRatio /= 2;
//				}
			}
			//shrink
			else{
				nodeNumRatio--;
//				if (nodeNumRatio > 1) {
//					nodeNumRatio /= 2;
//				} 
//				else if(nodeNumRatio == 1) {
//					nodeNumRatio = 0;
//				}
//				else if(nodeNumRatio ==0) {
//					nodeNumRatio = -1;
//				}
//				else if(nodeNumRatio < 0) {
//					nodeNumRatio *= 2;
//				}
			}
		}
		private float getNodeNum(int ratio){
			double nodeNum = 0;
			BigDecimal bg = null;
			if (nodeNumRatio >= 0) {
				nodeNum = unitNodeNum * Math.pow(2, nodeNumRatio);
			} else {
				nodeNum = unitNodeNum * Math.pow(1.0/2, -nodeNumRatio);
			}
			bg = new BigDecimal(ratio*nodeNum);
			return bg.setScale(2, BigDecimal.ROUND_UP).floatValue();
		}
		private float keepDecimalNum(float num,int decimalNum){
//			if (decimalNum <= 0) {
//				return Math.round(num);
//			} else {
//				
//			}
			BigDecimal bg = null;
			bg = new BigDecimal(num);
			return bg.setScale(decimalNum, BigDecimal.ROUND_HALF_UP).floatValue();
		}
	/**
	 * 逻辑控制
	 */
	private void logic() {
	}
	@Override
	public void run() {
		while (isThreadAlive) {
			long start = System.currentTimeMillis();
			myDraw();
			logic();
			long end = System.currentTimeMillis();
			try {
				if (end - start < 3) {
					Thread.sleep(30 - (end - start));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void computeXByUserInput(float x){
		cl = new FinalCalculate();
		double[] tempX = new double[1];
		tempX[0] = x;
    	FinalCalculate finalCalculate = new FinalCalculate();
    	chooseX = x;
    	chooseY = (float)cl.finalCalculate(source, tempX)[0];
    	isChoosePoint = true;
    	isChoosePointNeedRecalculate = false;
    	isPointOnFunction = true;
    	MovePointToCenter(chooseX, chooseY);
	}
	private void MovePointToCenter(float x,float y){
		newOriginX = (int)(screenW / 2 - convert.LenthToRealCoordinate(x));
		newOriginY = (int)(screenH / 2 + convert.LenthToRealCoordinate(y));
		isFunctionNeedReCalculate = true;
	}
	public void resetCanvas(){
		isScreenNeedRedefine = true;
		isFunctionNeedReCalculate = true;
		isNodeLenthNeedRecalculate = true;
	}
	/**
	 * SurfaceView视图状态发生改变，响应此函数
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	/**
	 * SurfaceView视图消亡时，响应此函数
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		isThreadAlive = false;
	}
}

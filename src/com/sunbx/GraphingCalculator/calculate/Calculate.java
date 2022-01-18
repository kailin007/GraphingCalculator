package com.sunbx.GraphingCalculator.calculate;

import android.R.attr;
import android.R.integer;

import com.sunbx.GraphingCalculator.structer.Attribute;
import com.sunbx.GraphingCalculator.structer.ListNode;
import com.sunbx.GraphingCalculator.structer.MyArrayStack;
import com.sunbx.GraphingCalculator.structer.MyLinkList;
import com.sunbx.GraphingCalculator.structer.Type;

public class Calculate {

	int result;
	int sequenceLength;
	double[] calculatetemp;
	double[] squencetemp;
	double[] stackPopTemp;
	Type tp = new Type();
	ListNode<Attribute> p;
	MyArrayStack<Attribute> mystack = new MyArrayStack<Attribute>();
	MyArrayStack<double[]> calculateResultTemp = new MyArrayStack<double[]>();
	Attribute atrtemp;
	boolean isFirstNode = true;
	
	/**
	 * 
	 * 两个数都是数字时直接计算
	 * @return 
	 */
	double operaterCalculate(char op){
		Attribute resulttemp = new Attribute();
		Attribute xtemp = new Attribute();
		Attribute ytemp = new Attribute();
		double x = 0.0;
		double y = 0.0;
		double z = 0.0;
		ytemp = mystack.pop();
		xtemp = mystack.pop();
		y = Double.valueOf(ytemp.name);
		x = Double.valueOf(xtemp.name);
		switch (op) {
		case '+':z = x+y;break;
		case '-':z = x-y;break;
		case '*':z = x*y;break;
		case '/':z = x/y;break;
		case '^':z = Math.pow(x, y);break;
		default:break;
		}
		resulttemp.name = String.valueOf(z);
		resulttemp.setnumber();
		mystack.push(resulttemp);
		p = p.next;
		return z;
	}
	
	/**
	 * 
	 * @param op
	 * @param squence
	 * 运算符计算
	 */
	void operaterCalculate(char op,double[] squence){
		Attribute resulttemp = new Attribute();
		Attribute xtemp = new Attribute();
		Attribute ytemp = new Attribute();
		double x = 0.0;
		double y = 0.0;
		ytemp = mystack.pop();
		xtemp = mystack.pop();
		//如果取出的栈中两个元素有一个不是数字结点
		if(!(ytemp.getType().equals(tp.getNumber())) || !(xtemp.getType().equals(tp.getNumber()))) {
			//如果两个都是temp squence
			if(ytemp.getType().equals(tp.getcalculateresult()) && xtemp.getType().equals(tp.getcalculateresult())){
				//x是传入的数据序列，y是缓冲数组
				if (xtemp.name.equals("squence") && ytemp.name.equals("calculatetemp")) {
					calculatetemp = new double[sequenceLength];
					calculatetemp = calculateResultTemp.pop();
					for (int i = 0; i < squence.length; i++) {
						switch (op) {
						case '+':calculatetemp[i] = squence[i]+calculatetemp[i];break;
						case '-':calculatetemp[i] = squence[i]-calculatetemp[i];break;
						case '*':calculatetemp[i] = squence[i]*calculatetemp[i];break;
						case '/':calculatetemp[i] = squence[i]/calculatetemp[i];break;
						case '^':calculatetemp[i] = Math.pow(squence[i] , calculatetemp[i]);break;
						default:break;
						}
					}
					calculateResultTemp.push(calculatetemp);
				}
				//x是计算结果或π e 等缓冲数组，y是传入x序列
				else if (xtemp.name.equals("calculatetemp") && ytemp.name.equals("squence")) {
					calculatetemp = new double[sequenceLength];
					calculatetemp = calculateResultTemp.pop();
					for (int i = 0; i < squence.length; i++) {
						switch (op) {
						case '+':calculatetemp[i] = calculatetemp[i] + squence[i];break;
						case '-':calculatetemp[i] = calculatetemp[i] - squence[i];break;
						case '*':calculatetemp[i] = calculatetemp[i] * squence[i];break;
						case '/':calculatetemp[i] = calculatetemp[i] / squence[i];break;
						case '^':calculatetemp[i] = Math.pow(calculatetemp[i] , squence[i]);break;
						default:break;
						}
					}
					calculateResultTemp.push(calculatetemp);
				}
				//x是传入序列，y是传入序列
				else if (xtemp.name.equals("squence") && ytemp.name.equals("squence")) {
					calculatetemp = new double[sequenceLength];
					for (int i = 0; i < squence.length; i++) {
						switch (op) {
						case '+':calculatetemp[i] = squence[i] + squence[i];break;
						case '-':calculatetemp[i] = squence[i] - squence[i];break;
						case '*':calculatetemp[i] = squence[i] * squence[i];break;
						case '/':calculatetemp[i] = squence[i] / squence[i];break;
						case '^':calculatetemp[i] = Math.pow(squence[i], squence[i]);break;
						default:break;
						}
					}
					calculateResultTemp.push(calculatetemp);
				}
				//x y 都是计算的结果数组
				else if (xtemp.name.equals("calculatetemp") && ytemp.name.equals("calculatetemp")) {
					calculatetemp = new double[sequenceLength];
					stackPopTemp = new double[sequenceLength];
					stackPopTemp = calculateResultTemp.pop();
					calculatetemp = calculateResultTemp.pop();
					for (int i = 0; i < squence.length; i++) {
						switch (op) {
						case '+':calculatetemp[i] = calculatetemp[i] + stackPopTemp[i];break;
						case '-':calculatetemp[i] = calculatetemp[i] - stackPopTemp[i];break;
						case '*':calculatetemp[i] = calculatetemp[i] * stackPopTemp[i];break;
						case '/':calculatetemp[i] = calculatetemp[i] / stackPopTemp[i];break;
						case '^':calculatetemp[i] = Math.pow(calculatetemp[i], stackPopTemp[i]);break;
						default:break;
						}
					}
					calculateResultTemp.push(calculatetemp);
				}
			}
			
			
			//x是number，y是temp
			else if(ytemp.getType().equals(tp.getcalculateresult()) && xtemp.getType().equals(tp.getNumber())) {
				//y是计算结果或π e 等缓存数组
				if (ytemp.name.equals("calculatetemp")) {
					x = Double.valueOf(xtemp.name);
					calculatetemp = new double[sequenceLength];
					calculatetemp = calculateResultTemp.pop();
					for (int i = 0; i < squence.length; i++) {
						switch (op) {
						case '+':calculatetemp[i] = x + calculatetemp[i];break;
						case '-':calculatetemp[i] = x - calculatetemp[i];break;
						case '*':calculatetemp[i] = x * calculatetemp[i];break;
						case '/':calculatetemp[i] = x / calculatetemp[i];break;
						case '^':calculatetemp[i] = Math.pow(x , calculatetemp[i]);break;
						default:break;
						}
					}
					calculateResultTemp.push(calculatetemp);
				}
				//y 是传入的x 序列
				else if (ytemp.name.equals("squence")) {
					calculatetemp = new double[sequenceLength];
					x = Double.valueOf(xtemp.name);
					for (int i = 0; i < squence.length; i++) {
						switch (op) {
						case '+':calculatetemp[i] = x + squence[i];break;
						case '-':calculatetemp[i] = x - squence[i];break;
						case '*':calculatetemp[i] = x * squence[i];break;
						case '/':calculatetemp[i] = x / squence[i];break;
						case '^':calculatetemp[i] = Math.pow(x , squence[i]);break;
						default:break;
						}
					}
					calculateResultTemp.push(calculatetemp);
				}
			}
			//x是temp，y是number
			else if (ytemp.getType().equals(tp.getNumber()) && xtemp.getType().equals(tp.getcalculateresult())) {
				if (xtemp.name.equals("calculatetemp")) {
					y = Double.valueOf(ytemp.name);
					calculatetemp = new double[sequenceLength];
					calculatetemp = calculateResultTemp.pop();
					for (int i = 0; i < squence.length; i++) {
						switch (op) {
						case '+':calculatetemp[i] = calculatetemp[i] + y;break;
						case '-':calculatetemp[i] = calculatetemp[i] - y;break;
						case '*':calculatetemp[i] = calculatetemp[i] * y;break;
						case '/':calculatetemp[i] = calculatetemp[i] / y;break;
						case '^':calculatetemp[i] = Math.pow(calculatetemp[i] , y);break;
						default:break;
						}
					}
					calculateResultTemp.push(calculatetemp);
				}
				else if (xtemp.name.equals("squence")) {
					y = Double.valueOf(ytemp.name);
					calculatetemp = new double[sequenceLength];
					for (int i = 0; i < squence.length; i++) {
						switch (op) {
						case '+':calculatetemp[i] = squence[i] + y;break;
						case '-':calculatetemp[i] = squence[i] - y;break;
						case '*':calculatetemp[i] = squence[i] * y;break;
						case '/':calculatetemp[i] = squence[i] / y;break;
						case '^':calculatetemp[i] = Math.pow(squence[i], y);break;
						default:break;
						}
					}
					calculateResultTemp.push(calculatetemp);
				}
			}
			resulttemp.name = "calculatetemp";
			resulttemp.setcalculateresult();
			mystack.push(resulttemp);
			p = p.next;
		}
		//如果两个都是数字
		else if (ytemp.getType().equals(tp.getNumber()) && xtemp.getType().equals(tp.getNumber())) {
			mystack.push(xtemp);
			mystack.push(ytemp);
			double calculateresult;
			calculateresult = operaterCalculate(op);
			calculatetemp = new double[squence.length];
			for (int i = 0; i < calculatetemp.length; i++) {
				calculatetemp[i] = calculateresult;
			}
		}
	}
	/**
	 * 
	 * @param sign
	 * 两个数都是数字的函数运算
	 */
	double functionCalculate(int sign){
		Attribute resulttemp = new Attribute();
		double x = 0.0;
		double z=0;
		x = Double.valueOf(mystack.pop().name);
		switch (sign) {
		//sin function
		case 1:z = Math.sin(x);break;
		//cos function
		case 2:z = Math.cos(x);break;
		//tan function
		case 3:z = Math.tan(x);break;
		//arcsin function
		case 4:z = Math.asin(x);break;
		//arccos function
		case 5:z = Math.acos(x);break;
		//arctan function	
		case 6:z = Math.atan(x);break;
		//log
		case 7:z = Math.log10(x);break;
		//ln
		case 8:z = Math.log(x);break;
		//√
		case 9:z = Math.sqrt(x);break;
		//abs
		case 10:z = Math.abs(x);break;
		//sgn
		case 11:z = Math.round(x);break;
		default:break;
		}
		resulttemp.name = String.valueOf(z);
		resulttemp.setnumber();
		mystack.push(resulttemp);
		p = p.next;
		return z;
	}
	/**
	 * 函数运算
	 * @param sign
	 * @param squence
	 */
	void functionCalculate(int sign,double squence[]){
		Attribute resulttemp = new Attribute();
		Attribute xtemp = new Attribute();
		xtemp = mystack.pop();
		//如果是temp
		if (xtemp.getType().equals(tp.getcalculateresult())) {
			
			//x
			if (xtemp.name.equals("squence")) {
				calculatetemp = new double[sequenceLength];
				for (int i = 0; i < squence.length; i++) {
					switch (sign) {
					//sin function
					case 1:calculatetemp[i] = Math.sin(squence[i]);break;
					//cos function
					case 2:calculatetemp[i] = Math.cos(squence[i]);break;
					//tan function
					case 3:calculatetemp[i] = Math.tan(squence[i]);break;
					//arcsin function
					case 4:calculatetemp[i] = Math.asin(squence[i]);break;
					//arccos function
					case 5:calculatetemp[i] = Math.acos(squence[i]);break;
					//arctan function	
					case 6:calculatetemp[i] = Math.atan(squence[i]);break;
					//log
					case 7:calculatetemp[i] = Math.log10(squence[i]);break;
					//ln
					case 8:calculatetemp[i] = Math.log(squence[i]);break;
					//√
					case 9:calculatetemp[i] = Math.sqrt(squence[i]);break;
					//abs
					case 10:calculatetemp[i] = Math.abs(squence[i]);break;
					//sgn
					case 11:calculatetemp[i] = Math.round(squence[i]);break;
					default:break;
					}
				}
				calculateResultTemp.push(calculatetemp);
			}
			else if (xtemp.name.equals("calculatetemp")) {
				stackPopTemp = new double[sequenceLength];
				stackPopTemp = calculateResultTemp.pop();
				calculatetemp = new double[sequenceLength];
				for (int i = 0; i < squence.length; i++) {
					switch (sign) {
					//sin function
					case 1:calculatetemp[i] = Math.sin(stackPopTemp[i]);break;
					//cos function
					case 2:calculatetemp[i] = Math.cos(stackPopTemp[i]);break;
					//tan function
					case 3:calculatetemp[i] = Math.tan(stackPopTemp[i]);break;
					//arcsin function
					case 4:calculatetemp[i] = Math.asin(stackPopTemp[i]);break;
					//arccos function
					case 5:calculatetemp[i] = Math.acos(stackPopTemp[i]);break;
					//arctan function	
					case 6:calculatetemp[i] = Math.atan(stackPopTemp[i]);break;
					//log
					case 7:calculatetemp[i] = Math.log10(stackPopTemp[i]);break;
					//ln
					case 8:calculatetemp[i] = Math.log(stackPopTemp[i]);break;
					//√
					case 9:calculatetemp[i] = Math.sqrt(stackPopTemp[i]);break;
					//abs
					case 10:calculatetemp[i] = Math.abs(calculatetemp[i]);break;
					//sgn
					case 11:calculatetemp[i] = Math.round(stackPopTemp[i]);break;
					default:break;
					}
				}
				calculateResultTemp.push(calculatetemp);
			}
			resulttemp.name = "calculatetemp";
			resulttemp.setcalculateresult();
			mystack.push(resulttemp);
			p = p.next;
		}
		//如果是number
		else if (xtemp.getType().equals(tp.getNumber())) {
			mystack.push(xtemp);
			double temp;
			temp = functionCalculate(sign);
			calculatetemp = new double[sequenceLength];
			for (int i = 0; i < calculatetemp.length; i++) {
				calculatetemp[i] = temp;
			}
			calculateResultTemp.push(calculatetemp);
		}
	}
	
	
	/**
	 * simple calculate
	 * @param resultlink
	 * @return
	 */
	public double calculate(MyLinkList<Attribute> resultlink) {
		System.out.println("in calculate method");
		p = resultlink.getHead().next;
			while (true) {
				boolean isEnd = false;
				if(p.next.getValue().name.equals("tail")){
					isEnd = true;
				}
				//如果是数字
				if (p.getValue().getType().equals("number")) {
					mystack.push(p.getValue());
					p = p.next;
				}
				//如果是操作符
				else if (p.getValue().getType().equals("operater")) {
					if (p.getValue().name.equals("+")) {
						operaterCalculate('+');
					}
					else if (p.getValue().name.equals("-")) {
						operaterCalculate('-');
					}
					else if (p.getValue().name.equals("*")) {
						operaterCalculate('*');
					}
					else if (p.getValue().name.equals("/")) {
						operaterCalculate('/');
					}
					else if(p.getValue().name.equals("^")){
						operaterCalculate('^');
					}
				}
				//如果是函数
				else if(p.getValue().getType().equals("function")){
					if (p.getValue().name.equals("sin")) {
						functionCalculate(1);
					}
					else if (p.getValue().name.equals("cos")) {
						functionCalculate(2);
					}
					else if (p.getValue().name.equals("tan")) {
						functionCalculate(3);
					}
					else if (p.getValue().name.equals("arcsin")) {
						functionCalculate(4);
					}
					else if (p.getValue().name.equals("arccos")) {
						functionCalculate(5);
					}
					else if (p.getValue().name.equals("arctan")) {
						functionCalculate(6);
					}
					else if (p.getValue().name.equals("log")) {
						functionCalculate(7);
					}
					else if (p.getValue().name.equals("ln")) {
						functionCalculate(8);
					}
					else if(p.getValue().name.equals("√")) {
						functionCalculate(9);
					}
					else if(p.getValue().name.equals("abs")) {
						functionCalculate(10);
					}
					else if (p.getValue().name.equals("round")) {
						functionCalculate(11);
					}
				}
				//如果是e
				else if (p.getValue().getType().equals("e")) {
					Attribute resulttemp = new Attribute();
					resulttemp.name = Math.E+"";
					resulttemp.setnumber();
					mystack.push(resulttemp);
					p = p.next;
				}
				//如果是π
				else if (p.getValue().getType().equals("π")) {
					Attribute resulttemp = new Attribute();
					resulttemp.name = Math.PI+"";
					resulttemp.setnumber();
					mystack.push(resulttemp);
					p = p.next;
				}
				if (isEnd == true) {
					break;
				}
			}
		return Double.valueOf(mystack.pop().name);
	}

	//when x is a sequence number
	public double[] calculate(MyLinkList<Attribute> resultlink,double sequence[]){
		sequenceLength = sequence.length;
		calculatetemp = new double[sequence.length];
		p = resultlink.getHead().next;
		while (true) {
			boolean isEnd = false;
			if(p.next.getValue().name.equals("tail")){
				isEnd = true;
			}
			//如果是数字
			if (p.getValue().getType().equals("number")) {
				mystack.push(p.getValue());
				p = p.next;
			}
			//when x
			else if (p.getValue().getType().equals("x")) {
				Attribute xresult = new Attribute();
				xresult.name = "squence";
				xresult.setcalculateresult();
				mystack.push(xresult);
				p = p.next;
			}
			//如果是e
			else if (p.getValue().getType().equals("e")) {
				Attribute resulttemp = new Attribute();
				resulttemp.name = Math.E+"";
				resulttemp.setnumber();
				mystack.push(resulttemp);
				p = p.next;
			}
			//如果是π
			else if (p.getValue().getType().equals("π")) {
				Attribute resulttemp = new Attribute();
				resulttemp.name = Math.PI+"";
				resulttemp.setnumber();
				mystack.push(resulttemp);
				p = p.next;
			}
			//如果是操作符
			else if (p.getValue().getType().equals("operater")) {
				if (p.getValue().name.equals("+")) {
					operaterCalculate('+',sequence);
				}
				else if (p.getValue().name.equals("-")) {
					operaterCalculate('-',sequence);
				}
				else if (p.getValue().name.equals("*")) {
					operaterCalculate('*',sequence);
				}
				else if (p.getValue().name.equals("/")) {
					operaterCalculate('/',sequence);
				}
				else if(p.getValue().name.equals("^")){
					operaterCalculate('^',sequence);
				}
			}
			//如果是函数
			else if(p.getValue().getType().equals("function")){
				if (p.getValue().name.equals("sin")) {
					functionCalculate(1,sequence);
				}
				else if (p.getValue().name.equals("cos")) {
					functionCalculate(2,sequence);
				}
				else if (p.getValue().name.equals("tan")) {
					functionCalculate(3,sequence);
				}
				else if (p.getValue().name.equals("arcsin")) {
					functionCalculate(4,sequence);
				}
				else if (p.getValue().name.equals("arccos")) {
					functionCalculate(5,sequence);
				}
				else if (p.getValue().name.equals("arctan")) {
					functionCalculate(6,sequence);
				}
				else if (p.getValue().name.equals("log")) {
					functionCalculate(7,sequence);
				}
				else if (p.getValue().name.equals("ln")) {
					functionCalculate(8,sequence);
				}
				else if(p.getValue().name.equals("√")) {
					functionCalculate(9,sequence);
				}
				else if(p.getValue().name.equals("abs")) {
					functionCalculate(10,sequence);
				}
				else if(p.getValue().name.equals("round")) {
					functionCalculate(11,sequence);
				}
			}
			if (isEnd == true) {
				Attribute xtemp;
				if (!mystack.isEmpty() && !mystack.getFirst().name.equals("calculatetemp")){
					if((xtemp = mystack.pop()).getType().equals(tp.getNumber())) {
						double x = Double.valueOf(xtemp.name);
						for (int i = 0; i < calculatetemp.length; i++) {
							calculatetemp[i] = x;
						}
					}
					else if (xtemp.name.equals("squence")) {
						for (int i = 0; i < sequence.length; i++) {
							calculatetemp[i] = sequence[i];
						}
					}
					return calculatetemp;
				}
				break;
			}
		}
	return calculateResultTemp.pop();
	}
}


package com.sunbx.GraphingCalculator.structer;

import com.sunbx.GraphingCalculator.sysprint.PrintList;

public class ParseString {
	public MyLinkList<Attribute> sourceList = new MyLinkList<Attribute>();
	Attribute atrhead = new Attribute();
	Attribute atrtail = new Attribute();
	
	boolean Isπ(char c){
		if (c==960) {return true;}
		return false;
	}
	
	boolean Ise(char c){
		if (c == 'e') {return true;}
		return false;
	}
	
	boolean IsNumber(char c){
		if(c>=48 && c<=57 || c=='.' || c=='E'){return true;}
		else return false;
	}
	
	boolean IsOP(char c){
		if(c=='+' || c=='-' || c=='*' || c=='/' || c=='^'){return true;}
		else return false;
	}
	
	boolean IsFunction(char c){
		if(c>=97 && c<=122 && c != 'x' && c != 'e' || c=='√'){
			return true;
		}
		else {
			return false;
		}
	}
	
	public MyLinkList<Attribute> parse(String str){
		System.out.println("in parse mehtod");
		atrhead.name="head";
		atrhead.settag();
		atrtail.name="tail";
		atrtail.settag();
		sourceList.addToHead(atrhead,atrtail);
		int i = 0;


		while (true) {
			if (str.charAt(i) == '#') {
				break;
			}
			//如果是π，存进
			else if (Isπ(str.charAt(i))) {
				String temp = "";
				Attribute atrtemp = new Attribute();
				temp = "π";
				atrtemp.name = temp;
				atrtemp.setπ();
				sourceList.addLast(atrtemp);
				i++;
			}
			//如果是e，存进
			else if (Ise(str.charAt(i))) {
				String temp = "";
				Attribute atrtemp = new Attribute();
				temp = "e";
				atrtemp.name = temp;
				atrtemp.sete();
				sourceList.addLast(atrtemp);
				i++;
			}
			//如果是数字，判断有多少位，一起存进
			else if (IsNumber(str.charAt(i))) {
				String temp = "";
				Attribute atrtemp = new Attribute();
				while (true) {
					temp += str.charAt(i++);
					if (str.charAt(i) == '#') {
						atrtemp.name = temp;
						atrtemp.setnumber();
						sourceList.addLast(atrtemp);
						break;
					}
					else if ((! IsNumber(str.charAt(i)))) {
						atrtemp.name = temp;
						atrtemp.setnumber();
						System.out.println(i);
						sourceList.addLast(atrtemp);
						break;
					}
				}
			}
			//如果是操作符
			else if(IsOP(str.charAt(i))){
				Attribute atrtemp = new Attribute();
				atrtemp.name = String.valueOf(str.charAt(i));
				atrtemp.setOperater();
				sourceList.addLast(atrtemp);
				i++;
			}
			//如果是函数
			else if (IsFunction(str.charAt(i))) {
				String temp = "";
				Attribute atrtemp = new Attribute();
				while (true) {
					temp += str.charAt(i++);
					if (! IsFunction(str.charAt(i))) {
						atrtemp.name = temp;
						atrtemp.setfuncition();
						sourceList.addLast(atrtemp);
						break;
					}
				}
			}
			//如果是括号
			else if (str.charAt(i)=='(' || str.charAt(i)==')') {
				Attribute atrtemp = new Attribute();
				atrtemp.name = String.valueOf(str.charAt(i));
				atrtemp.setbrackets();
				sourceList.addLast(atrtemp);
				i++;
			}
			//如果是x
			else if (str.charAt(i)=='x' || str.charAt(i)=='X') {
				Attribute atrtemp = new Attribute();
				atrtemp.name = String.valueOf(str.charAt(i));
				atrtemp.setx();
				sourceList.addLast(atrtemp);
				i++;
			}
		}
		PrintList pl = new PrintList();
		pl.print(sourceList);
		System.out.println();
		return sourceList;
	}

}

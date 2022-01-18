package com.sunbx.GraphingCalculator.rebuild;

import com.sunbx.GraphingCalculator.structer.Attribute;
import com.sunbx.GraphingCalculator.structer.ListNode;
import com.sunbx.GraphingCalculator.structer.MyLinkList;
import com.sunbx.GraphingCalculator.structer.Type;
import com.sunbx.GraphingCalculator.sysprint.PrintList;

public class ReBuild {

	Attribute atrtemp = new Attribute();
	ListNode<Attribute> p;
	ListNode<Attribute> temp = new ListNode<Attribute>(atrtemp);
	Type tp = new Type();

	
	void addNewOperater(String content){
		atrtemp = new Attribute();
		atrtemp.name=content;
		atrtemp.setOperater();
		temp = new ListNode<Attribute>(atrtemp);
		p.next.pre = temp;
		temp.next = p.next;
		p.next = temp;
		temp.pre = p;
		p = p.next;p = p.next;
	}
	void addNewZero(){
		atrtemp = new Attribute();
		atrtemp.name="0";
		atrtemp.setnumber();
		temp = new ListNode<Attribute>(atrtemp);
		ListNode<Attribute> q;
		q = p.pre;
		q.next = temp;
		temp.next = p;	
		p.pre = temp;
		temp.pre = q;
	}
	
	@SuppressWarnings("null")
	public MyLinkList<Attribute> rebuild(MyLinkList<Attribute> source){
		System.out.println("in rebuild method");
		p = source.getHead().next;
		while(true){
			if (p.getValue().name.equals("tail")) {
				break;
			}
			//.在开始
			else if (p.getValue().getType().equals(tp.getNumber()) && p.getValue().name.charAt(0) == '.') {
					p.getValue().name = "0"+p.getValue().name;
					System.out.println(". rebuild complete");
			}
		//数字加π
			else if (p.getValue().getType() == "number" && p.next.getValue().getType() == "π") {
				addNewOperater("*");
			}
		//数字加x
			else if (p.getValue().getType() == "number" && p.next.getValue().getType() == "x") {
				addNewOperater("*");
			}
		//数字加括号
			else if (p.getValue().getType() == "number" && p.next.getValue().getType() == "brackets") {
				if(!p.next.getValue().name.equals(")")) addNewOperater("*");
				else p = p.next;
			}
		//数字加函数
			else if (p.getValue().getType() == "number" && p.next.getValue().getType() == "function") {
				addNewOperater("*");
			}
		//数字加e
			else if (p.getValue().getType() == "number" && p.next.getValue().getType() == "e") {
				addNewOperater("*");
			}
		//π加括号
			else if (p.getValue().getType() == "π" && p.next.getValue().getType() == "brackets") {
				if(!p.next.getValue().name.equals(")")) addNewOperater("*");
				else p = p.next;
			}
		//π加x
			else if (p.getValue().getType() == "π" && p.next.getValue().getType() == "x") {
				addNewOperater("*");
			}
		//π加函数
			else if (p.getValue().getType() == "π" && p.next.getValue().getType() == "function") {
				addNewOperater("*");
			}
		//π加e
			else if (p.getValue().getType() == "π" && p.next.getValue().getType() == "e") {
				addNewOperater("*");
			}
		//e加括号
			else if (p.getValue().getType() == "e" && p.next.getValue().getType() == "brackets") {
				if(!p.next.getValue().name.equals(")")) addNewOperater("*");
				else p = p.next;
			}
		//e加x
			else if (p.getValue().getType() == "e" && p.next.getValue().getType() == "x") {
				addNewOperater("*");
			}
		//e加函数
			else if (p.getValue().getType() == "e" && p.next.getValue().getType() == "function") {
				addNewOperater("*");
			}
		//x加e
			else if (p.getValue().getType() == "x" && p.next.getValue().getType() == "e") {
				addNewOperater("*");
			}
		//x加π
			else if (p.getValue().getType() == "x" && p.next.getValue().getType() == "π") {
				addNewOperater("*");
			}
		//x加括号
			else if (p.getValue().getType() == "x" && p.next.getValue().getType() == "brackets") {
				if((p.next.getValue().name.equals("("))) addNewOperater("*");
				else p = p.next;
			}
		//x加函数
			else if (p.getValue().getType() == "x" && p.next.getValue().getType() == "function") {
				addNewOperater("*");
			}
		//括号加π
			else if (p.getValue().getType() == "brackets" && p.next.getValue().getType() == "π") {
				if(p.getValue().name.equals(")")) addNewOperater("*");
				else p = p.next;
			}
		//括号加x
			else if (p.getValue().getType() == "brackets" && p.next.getValue().getType() == "x") {
				if(!p.getValue().name.equals("(")) addNewOperater("*");
				else p = p.next;
			}
		//括号加函数
			else if (p.getValue().getType() == "brackets" && p.next.getValue().getType() == "function") {
				if((p.getValue().name.equals(")"))) addNewOperater("*");
				else p = p.next;
			}
			
		//负号是第一个
			else if (p.getValue().name.equals("-") && p.pre.getValue().name.equals("head")) {
				addNewZero();
			}
		//负号前面是括号
			else if (p.getValue().name.equals("-") && p.pre.getValue().name.equals("(")) {
				addNewZero();
			}
			else p = p.next;
		}
		PrintList pl = new PrintList();
		pl.print(source);
		System.out.println();
		return source;
	}
}

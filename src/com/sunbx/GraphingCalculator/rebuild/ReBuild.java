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
			//.�ڿ�ʼ
			else if (p.getValue().getType().equals(tp.getNumber()) && p.getValue().name.charAt(0) == '.') {
					p.getValue().name = "0"+p.getValue().name;
					System.out.println(". rebuild complete");
			}
		//���ּӦ�
			else if (p.getValue().getType() == "number" && p.next.getValue().getType() == "��") {
				addNewOperater("*");
			}
		//���ּ�x
			else if (p.getValue().getType() == "number" && p.next.getValue().getType() == "x") {
				addNewOperater("*");
			}
		//���ּ�����
			else if (p.getValue().getType() == "number" && p.next.getValue().getType() == "brackets") {
				if(!p.next.getValue().name.equals(")")) addNewOperater("*");
				else p = p.next;
			}
		//���ּӺ���
			else if (p.getValue().getType() == "number" && p.next.getValue().getType() == "function") {
				addNewOperater("*");
			}
		//���ּ�e
			else if (p.getValue().getType() == "number" && p.next.getValue().getType() == "e") {
				addNewOperater("*");
			}
		//�м�����
			else if (p.getValue().getType() == "��" && p.next.getValue().getType() == "brackets") {
				if(!p.next.getValue().name.equals(")")) addNewOperater("*");
				else p = p.next;
			}
		//�м�x
			else if (p.getValue().getType() == "��" && p.next.getValue().getType() == "x") {
				addNewOperater("*");
			}
		//�мӺ���
			else if (p.getValue().getType() == "��" && p.next.getValue().getType() == "function") {
				addNewOperater("*");
			}
		//�м�e
			else if (p.getValue().getType() == "��" && p.next.getValue().getType() == "e") {
				addNewOperater("*");
			}
		//e������
			else if (p.getValue().getType() == "e" && p.next.getValue().getType() == "brackets") {
				if(!p.next.getValue().name.equals(")")) addNewOperater("*");
				else p = p.next;
			}
		//e��x
			else if (p.getValue().getType() == "e" && p.next.getValue().getType() == "x") {
				addNewOperater("*");
			}
		//e�Ӻ���
			else if (p.getValue().getType() == "e" && p.next.getValue().getType() == "function") {
				addNewOperater("*");
			}
		//x��e
			else if (p.getValue().getType() == "x" && p.next.getValue().getType() == "e") {
				addNewOperater("*");
			}
		//x�Ӧ�
			else if (p.getValue().getType() == "x" && p.next.getValue().getType() == "��") {
				addNewOperater("*");
			}
		//x������
			else if (p.getValue().getType() == "x" && p.next.getValue().getType() == "brackets") {
				if((p.next.getValue().name.equals("("))) addNewOperater("*");
				else p = p.next;
			}
		//x�Ӻ���
			else if (p.getValue().getType() == "x" && p.next.getValue().getType() == "function") {
				addNewOperater("*");
			}
		//���żӦ�
			else if (p.getValue().getType() == "brackets" && p.next.getValue().getType() == "��") {
				if(p.getValue().name.equals(")")) addNewOperater("*");
				else p = p.next;
			}
		//���ż�x
			else if (p.getValue().getType() == "brackets" && p.next.getValue().getType() == "x") {
				if(!p.getValue().name.equals("(")) addNewOperater("*");
				else p = p.next;
			}
		//���żӺ���
			else if (p.getValue().getType() == "brackets" && p.next.getValue().getType() == "function") {
				if((p.getValue().name.equals(")"))) addNewOperater("*");
				else p = p.next;
			}
			
		//�����ǵ�һ��
			else if (p.getValue().name.equals("-") && p.pre.getValue().name.equals("head")) {
				addNewZero();
			}
		//����ǰ��������
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

package com.sunbx.GraphingCalculator.convert;

import com.sunbx.GraphingCalculator.structer.Attribute;
import com.sunbx.GraphingCalculator.structer.ListNode;
import com.sunbx.GraphingCalculator.structer.MyArrayStack;
import com.sunbx.GraphingCalculator.structer.MyLinkList;
import com.sunbx.GraphingCalculator.structer.MyStack;
import com.sunbx.GraphingCalculator.sysprint.PrintList;

public class ConvertExpression {
	char[][] chart = new char[][]{{'+','-','*','/','(','^'},{'1','1','2','2','0','3'}};
	MyLinkList<Attribute> source = new MyLinkList<Attribute>();
	MyLinkList<Attribute> result = new MyLinkList<Attribute>();
	MyStack<Attribute> myStack = new MyArrayStack<Attribute>();
	
	boolean IsNumber(ListNode<Attribute> ln){
		if(ln.getValue().getType() == "number") return true;
		else return false;
	}
	boolean Ise(ListNode<Attribute> ln){
		if(ln.getValue().getType() == "e")return true;
		else return false;
	}
	boolean Is��(ListNode<Attribute> ln){
		if(ln.getValue().getType() == "��") return true;
		return false;
	}
	boolean JudgePri(String wait,String top){
		for (int i = 0; i < chart[0].length; i++) {
			if (String.valueOf(chart[0][i]).equals(wait)) {
				for (int j = 0; j < chart[0].length; j++) {
					if (String.valueOf(chart[0][j]).equals(top)){
						if (chart[1][i] >= chart[1][j]) {
							//�������ȡ��Ԫ�ر�ս��Ԫ�ص����ȼ��߻���ȣ��򷵻�true
							return true;
						}
						else return false;
					}
				}
			}
		}
		return false;
	}
	boolean IsOP(ListNode<Attribute> ln){
		if(ln.getValue().name.equals("+") 
				|| ln.getValue().name.equals("-")
				|| ln.getValue().name.equals("*")
				|| ln.getValue().name.equals("/")
				|| ln.getValue().name.equals(")")
				|| ln.getValue().name.equals("(")
				|| ln.getValue().name.equals("^")){return true;}
		else return false;
	}
	boolean IsFunction(ListNode<Attribute> ln){
		if(ln.getValue().getType() == "function") return true;
		else return false;
	}
	
	
	public MyLinkList<Attribute> Convert(MyLinkList<Attribute> source){
		System.out.println("in convertexpression method");
		this.source = source;
		ListNode<Attribute> p;
		PrintList pl = new PrintList();
		p = source.getHead();
		p = p.next;
		Attribute atrhead = new Attribute();Attribute atrtail = new Attribute();
		atrhead.name="head";
		atrtail.name="tail";
		result.addToHead(atrhead,atrtail);

		while(true){
			System.out.println("stacksize:"+myStack.length());
			System.out.println("char:"+p.getValue().name);
			
			//Ϊ�գ����������ַ�
			if (p.getValue().name.equals("tail")) {
				while (!myStack.isEmpty()) {
					result.addLast(myStack.pop());
				}
				break;
			}
		//������һ����������ʱ�����̰����ŵ������
			else if(IsNumber(p) || p.getValue().name.equals("x") || Is��(p) || Ise(p)){
			result.addLast(p.getValue());
			p = p.next;
			}
		//�������һ�������ţ���ô�ͽ�ջԪ�ص���������ЩԪ��д������У�
		//ֱ������һ�������ţ��������ŵ������ǲ������
		else if (p.getValue().name.equals(")")) {
			while(true){
				if( !(myStack.getFirst().name.equals("("))){
				result.addLast(myStack.pop());
				}
				else {
					myStack.pop();
					if (!myStack.isEmpty() && myStack.getFirst().getType().equals("function")) {
						result.addLast(myStack.pop());
					}
					break;
				}
			}
			p = p.next;
		}
			//�����һ��������ֱ����ջ
		else if (IsFunction(p)) {
			myStack.push(p.getValue());
			p = p.next;
		}
		//�����Ƿ���һ��+ - * / (��ʱ�򣬴�ջ�е���Ԫ�أ�ֱ���������ȼ����͵�Ԫ��Ϊֹ��
		//���������������ٽ��µ�Ԫ��ѹ��ջ��
		//���ջ����Ԫ�رȵȴ�Ԫ�����ȼ���break������߻�Ⱦ�pop
		else if(IsOP(p)) {
			while(true){
				if(myStack.isEmpty()){
					break;
				}
				else if ((p.getValue().name.equals("("))){
					break;
				}
				else if (!JudgePri(myStack.getFirst().name,p.getValue().name)) {
					break;
				}
				else {
					result.addLast(myStack.pop());
				}
				}
			myStack.push(p.getValue());
			p = p.next;
			}
			}
		pl.print(result);
		System.out.println();
		return result;
		}
}

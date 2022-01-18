package com.sunbx.GraphingCalculator.structer;

import com.sunbx.GraphingCalculator.sysprint.PrintList;

public class ParseString {
	public MyLinkList<Attribute> sourceList = new MyLinkList<Attribute>();
	Attribute atrhead = new Attribute();
	Attribute atrtail = new Attribute();
	
	boolean Is��(char c){
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
		if(c>=97 && c<=122 && c != 'x' && c != 'e' || c=='��'){
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
			//����ǦУ����
			else if (Is��(str.charAt(i))) {
				String temp = "";
				Attribute atrtemp = new Attribute();
				temp = "��";
				atrtemp.name = temp;
				atrtemp.set��();
				sourceList.addLast(atrtemp);
				i++;
			}
			//�����e�����
			else if (Ise(str.charAt(i))) {
				String temp = "";
				Attribute atrtemp = new Attribute();
				temp = "e";
				atrtemp.name = temp;
				atrtemp.sete();
				sourceList.addLast(atrtemp);
				i++;
			}
			//��������֣��ж��ж���λ��һ����
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
			//����ǲ�����
			else if(IsOP(str.charAt(i))){
				Attribute atrtemp = new Attribute();
				atrtemp.name = String.valueOf(str.charAt(i));
				atrtemp.setOperater();
				sourceList.addLast(atrtemp);
				i++;
			}
			//����Ǻ���
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
			//���������
			else if (str.charAt(i)=='(' || str.charAt(i)==')') {
				Attribute atrtemp = new Attribute();
				atrtemp.name = String.valueOf(str.charAt(i));
				atrtemp.setbrackets();
				sourceList.addLast(atrtemp);
				i++;
			}
			//�����x
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

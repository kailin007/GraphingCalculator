package com.sunbx.GraphingCalculator.check;

import android.R.attr;

import com.sunbx.GraphingCalculator.structer.Attribute;
import com.sunbx.GraphingCalculator.structer.ListNode;
import com.sunbx.GraphingCalculator.structer.MyLinkList;
import com.sunbx.GraphingCalculator.structer.ParseString;
import com.sunbx.GraphingCalculator.structer.Type;

public class CheckError {
	Type tp = new Type();
	ParseString ps = new ParseString();
	boolean Check(MyLinkList<Attribute> sourceList){
		ListNode<Attribute> p;
		p = sourceList.getHead().next;
		while (true) {
			if (p.getValue().name.equals("tail")) {
				break;
			}
			//x+Êý×Ö
			if(p.getValue().getType().equals(tp.getX())/*p is x*/ 
					&& p.next.getValue().getType().equals(tp.getNumber())/*p's next is a number*/) {
				return false;
			}
			//e huo pai+Êý×Ö
			else if ( (p.getValue().getType().equals(tp.gete()) || p.getValue().getType().equals(tp.getX()) )
					&& p.next.getValue().getType().equals(tp.getNumber())) {
				return false;
			}
			else if ( (p.getValue().getType().equals(tp.gete()) || p.getValue().getType().equals(tp.get¦Ð())) && 
					(p.next.getValue().getType().equals(tp.gete()) || p.next.getValue().getType().equals(tp.get¦Ð()))) {
				return false;
			}
			//.+.
			else if (p.getValue().getType().equals(tp.getNumber())) {
				int pointNumber=0;
				for (int i = 0; i < p.getValue().name.length(); i++) {
					if (p.getValue().name.charAt(i) == '.') {
						if (++pointNumber == 2) {
							System.out.println(". error check complete");
							return false;
						}
					}
				}
			}
			//x+x
			else if (p.getValue().getType().equals(tp.getX())/*p is x*/ 
					&& p.next.getValue().getType().equals(tp.getX())/*p's next is a x*/) {
				return false;
			}
			//·ûºÅ+·ûºÅ
			else if (p.getValue().getType().equals(tp.getOperater())/*p is a operator*/
					&& p.next.getValue().getType().equals(tp.getOperater()/*p's next also is a operator*/)) {
				return false;
			}
			//·ûºÅÔÚ×îºóÒ»¸ö»òµÚÒ»¸ö
			else if ((p.getValue().getType().equals(tp.getOperater()) && p.next.getValue().getType().equals(tp.gettag()))
					&& (p.getValue().getType().equals(tp.getOperater()) && p.pre.getValue().getType().equals(tp.gettag()))) {
				return false;				
			}
			//×óÀ¨ºÅ¼ÓÓÒÀ¨ºÅ
			else if (p.getValue().getType().equals(tp.getBrackets()/*p is a bracket*/)
					&& p.next.getValue().getType().equals(tp.getBrackets())/*p's next is also a bracket*/) {
				if (p.getValue().name.equals("(") && p.next.getValue().name.equals(")")) {
					return false;
				}
			}
			//·ûºÅµÄ×ó±ßÊÇ×óÀ¨ºÅ »òÕßÓÒ±ßÊÇÓÒÀ¨ºÅ
			else if (p.getValue().getType().equals(tp.getOperater())) {
				if (p.pre.getValue().getType().equals(tp.getBrackets()) 
						&& p.pre.getValue().name.equals("(")) {
					if(!(p.getValue().name.equals("-") && (p.next.getValue().getType().equals(tp.gete())
							 || p.next.getValue().getType().equals(tp.get¦Ð()) || 
							 p.next.getValue().getType().equals(tp.getFunction()) || 
							 p.next.getValue().getType().equals(tp.getX()) || 
							 (p.next.getValue().getType().equals(tp.getBrackets()) && p.next.getValue().name.equals("("))
							 ))){
						return false;
					}
				}
				if (p.next.getValue().getType().equals(tp.getBrackets()) 
						&& p.next.getValue().name.equals(")")) {
					return false;
				}
			}
			p = p.next;
		}
		System.out.println("check error method end");
		return true;
	}
	
	public boolean IsHasError(String source){
		MyLinkList<Attribute> sourceLinkList;
		sourceLinkList = ps.parse(source);
		if (Check(sourceLinkList)) {
			return true;
		}
		else {
			return false;
		}
	}
}

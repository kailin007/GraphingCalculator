package com.sunbx.GraphingCalculator.sysprint;

import com.sunbx.GraphingCalculator.structer.Attribute;
import com.sunbx.GraphingCalculator.structer.ListNode;
import com.sunbx.GraphingCalculator.structer.MyLinkList;

public class PrintList {

	ListNode<Attribute> p ;
	public void print(MyLinkList<Attribute> result){
		p = result.getHead();
		while (true) {
			if (p.getValue().name=="tail") {
				System.out.print(p.getValue().name);
				break;
			}
			System.out.print(p.getValue().name+"  ");
			p = p.next;
		}
	}
	
}

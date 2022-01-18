package com.sunbx.GraphingCalculator.calculate;

import com.sunbx.GraphingCalculator.convert.ConvertExpression;
import com.sunbx.GraphingCalculator.rebuild.ReBuild;
import com.sunbx.GraphingCalculator.structer.Attribute;
import com.sunbx.GraphingCalculator.structer.MyLinkList;
import com.sunbx.GraphingCalculator.structer.ParseString;
import com.sunbx.GraphingCalculator.sysprint.PrintList;

public class FinalCalculate {
	ConvertExpression ce = new ConvertExpression();
	ParseString ps = new ParseString();
	ReBuild rb = new ReBuild();
	PrintList pl = new PrintList();
	Calculate cl = new Calculate(); 
	MyLinkList<Attribute> resultList = new MyLinkList<Attribute>();
	double[] result;
	
	public double[] finalCalculate(String resultString,double[] squence){
		result = new double[squence.length];
		//resultString = resultString.substring(5);
		//resultString+="#";
		resultList = ce.Convert(rb.rebuild(ps.parse(resultString)));
		result = cl.calculate(resultList, squence);
		return result;
	}
}

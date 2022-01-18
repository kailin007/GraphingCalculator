package com.sunbx.GraphingCalculator.structer;

public class Attribute {
	public String name;
	private String type = null;
	private Type tp = new Type();
	public String getType() {
		return type;
	}
	public void setcalculateresult(){
		this.type = tp.getcalculateresult();
	}
	public void settag(){
		this.type = tp.gettag();
	}
	public void set¦Ð(){
		this.type = tp.get¦Ð();
	}
	public void sete(){
		this.type = tp.gete();
	}
	public void setfuncition(){
		this.type = tp.getFunction();
	}
	public void setnumber(){
		this.type = tp.getNumber();
	}
	public void setbrackets(){
		this.type = tp.getBrackets();
	}
	public void setx(){
		this.type = tp.getX();
	}
	public void setOperater(){
		this.type = tp.getOperater();
	}
}

package com.sunbx.GraphingCalculator.structer;

public interface MyStack<T> {  
    /** 
     * �ж�ջ�Ƿ�Ϊ�� 
     */  
    boolean isEmpty();  
    /** 
     * ���ջ 
     */  
    void clear();  
    /** 
     * ջ�ĳ��� 
     */  
    int length();  
    /** 
     * ������ջ 
     */  
    boolean push(T data);  
    /** 
     * ���ݳ�ջ 
     */  
    T pop();
    
    T getFirst();
    
	boolean push(char c);
}  

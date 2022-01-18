package com.sunbx.GraphingCalculator.structer;

public class ListNode<T> {
    private T value; // 数据域
    public ListNode<T> next; // 指针域保存着下一节点的引用
    public ListNode<T> pre;
    public void setValue(T value){
    	this.value = value;
    }
    public ListNode(T value) {
        this.value = value;
    }
    public T getValue() {
		return value;
	}
}

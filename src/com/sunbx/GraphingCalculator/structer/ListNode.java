package com.sunbx.GraphingCalculator.structer;

public class ListNode<T> {
    private T value; // ������
    public ListNode<T> next; // ָ���򱣴�����һ�ڵ������
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

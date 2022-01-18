package com.sunbx.GraphingCalculator.structer;

public class MyLinkList <T> {

	private ListNode<T> head, tail;
	
	public ListNode<T> getHead() {
		return head;
	}
    
    public MyLinkList() {
        head = tail = null;
    }
    /**   
     * �ж������Ƿ�Ϊ��   
     */    
    public boolean isEmpty() {     
        return head == null;
    }
    /**   
     * ����ͷָ�룬�÷���ֻ��һ�Σ�   
     */    
    public void addToHead(T itemhead, T itemtail) {
        head = new ListNode<T>(itemhead);
        tail = new ListNode<T>(itemtail);
        	head.next = tail;
        	tail.pre= head;
    }
         
    /**   
     * ���βָ�룬�÷���ʹ�ö��   
     */    
    public void addToTail(T item) {
        if (!isEmpty()) { // ������ǿ���ô��βָ���next��ʹ��Ϊһ���µ�Ԫ��
            tail.next = new ListNode<T>(item); // Ȼ��βָ��ָ���������Լ�����һ��Ԫ��     
            tail.next.pre = tail;
            tail = tail.next;
        } else { // ���Ϊ���򴴽�һ���µģ�����ͷβͬʱָ����     
            head = tail = new ListNode<T>(item);           
        }     
    }
         
    /**   
     * ��ӡ�б�   
     */    
    public void printList() {     
        if (isEmpty()) {     
            System.out.println("null");     
        } else {     
            for(ListNode<T> p = head; p != null; p = p.next);  
                //System.out.println(p.value);     
        }     
    }     
         
    /**   
     * �ڱ�ͷ�����㣬Ч�ʷǳ���   
     */    
    public void addFirst(T item) {     
        ListNode<T> newNode = new ListNode<T>(item);     
        newNode.next = head;     
        head = newNode;     
    }     
         
    /**   
     * �ڱ�β�����㣬Ч�ʺܵ�   
     */    
    public void addLast(T item) {     
        ListNode<T> newNode = new ListNode<T>(item);     
        ListNode<T> p = tail.pre;
        p.next = newNode;
        newNode.pre = p;
        newNode.next = tail;
        tail.pre = newNode;
    }     
         
    public ListNode<T> getNode(T item){
    	ListNode<T> p = head;
    	int length=indexOf(item);
    	for (int i = 0; i < length; i++) {
			p = p.next;
		}
    	return p;
    }
    
    public void addBefore(ListNode<T> p, T finditem, T newitem){
    	ListNode<T> q;
    	q = getNode(finditem);
    	q.pre = p;
    	ListNode<T> newNode = new ListNode<T>(newitem);
    	p.next = newNode;
    	newNode.pre = p;
    	newNode.next = q;
    	q.pre = newNode;
    }
    /**   
     * �ڱ�ͷɾ����㣬Ч�ʷǳ���   
     */    
    public void removeFirst() {     
        if (!isEmpty()) head = head.next;     
        else System.out.println("The list have been emptied!");     
    }     
         
    /**   
     * �ڱ�βɾ����㣬Ч�ʺܵ�   
     */    
    public void removeLast() {     
        ListNode<T> prev = null, curr = head;     
        while(curr.next != null) {     
            prev = curr;     
            curr = curr.next;     
            if(curr.next == null) prev.next = null;     
        }     
    }
         
    /**   
     * ���ش��б����״γ��ֵ�ָ��Ԫ�ص�����������б��в�������Ԫ�أ��򷵻� -1.   
     */    
    public int indexOf(T item) {     
        int index = 0;     
        ListNode<T> p;     
        for(p = head; p != null; p = p.next) {     
            if(item.equals(p.getValue()))     
                return index;     
            index++;     
                     
        }     
        return -1;
    }     
         
    /**   
     * ������б����ָ��Ԫ�أ��򷵻� true��   
     */    
     public boolean contains(T item) {     
         return indexOf(item) != -1;     
     }
}

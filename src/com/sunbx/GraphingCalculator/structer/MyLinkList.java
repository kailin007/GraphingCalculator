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
     * 判断链表是否为空   
     */    
    public boolean isEmpty() {     
        return head == null;
    }
    /**   
     * 创建头指针，该方法只用一次！   
     */    
    public void addToHead(T itemhead, T itemtail) {
        head = new ListNode<T>(itemhead);
        tail = new ListNode<T>(itemtail);
        	head.next = tail;
        	tail.pre= head;
    }
         
    /**   
     * 添加尾指针，该方法使用多次   
     */    
    public void addToTail(T item) {
        if (!isEmpty()) { // 若链表非空那么将尾指针的next初使化为一个新的元素
            tail.next = new ListNode<T>(item); // 然后将尾指针指向现在它自己的下一个元素     
            tail.next.pre = tail;
            tail = tail.next;
        } else { // 如果为空则创建一个新的！并将头尾同时指向它     
            head = tail = new ListNode<T>(item);           
        }     
    }
         
    /**   
     * 打印列表   
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
     * 在表头插入结点，效率非常高   
     */    
    public void addFirst(T item) {     
        ListNode<T> newNode = new ListNode<T>(item);     
        newNode.next = head;     
        head = newNode;     
    }     
         
    /**   
     * 在表尾插入结点，效率很低   
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
     * 在表头删除结点，效率非常高   
     */    
    public void removeFirst() {     
        if (!isEmpty()) head = head.next;     
        else System.out.println("The list have been emptied!");     
    }     
         
    /**   
     * 在表尾删除结点，效率很低   
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
     * 返回此列表中首次出现的指定元素的索引，如果列表中不包含此元素，则返回 -1.   
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
     * 如果此列表包含指定元素，则返回 true。   
     */    
     public boolean contains(T item) {     
         return indexOf(item) != -1;     
     }
}

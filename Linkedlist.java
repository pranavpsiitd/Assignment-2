/**
 * Created by Pranav ps on 16-09-2015.
 */

class Node{
    protected Object data;
    protected Node next;
    public Node(Object value){
        data = value;
        next = null;
    }
    public Node(){
        this(null);
    }
    public Object getData(){
        return data;
    }
    public void setData(Object val){
        data = val;
    }
    public Node getNext(){
        return next;
    }
    public void setNext(Node temp){
        next = temp;
    }

}

public class Linkedlist {


    //implementation of Linkedlist
    protected Node head;
    protected Node tail;
    protected int size=0;
    public Linkedlist(){
        head = null;
        tail = null;
    }
    public Node getHead(){
        return head;
    }

    public void setHead(Node h){ head = h;}

    public void setTail(Node t){ tail = t;}

    public Node getTail(){
        return tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public Object removeFirst()throws Exception{
        if (isEmpty()) {
            throw new Exception("Linked List is Empty");
        }
        Object temp = head.getData();
        head = head.getNext();
        size--;
        if(size()==0){
            tail=null;
        }
        return temp;
    }

    public void addFirst (Object o){
        Node a = new Node(o);
        a.setNext(head);
        head = a;
        if(size()==0){
            tail = head;
        }
        size++;
    }

    public void addLast (Object o){
        Node a = new Node(o);
        if(size()==0){
            head = a;
        }
        else{
        tail.setNext(a);}
        tail = a;
        size++;
    }

}

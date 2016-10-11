/**
 * Created by Pranav ps on 18-09-2015.
 */
class ExchangeNode{
    protected Exchange data;
    protected ExchangeNode next;
    public ExchangeNode(Exchange e){
        data = e;
        next = null;
    }
    public ExchangeNode(){this(null);}

    public Exchange getData() {
        return data;
    }

    public ExchangeNode getNext() {
        return next;
    }

    public void setNext(ExchangeNode next) {
        this.next = next;
    }

    public void setData(Exchange data) {
        this.data = data;
    }
}

public class ExchangeList {
//    protected Linkedlist l = new Linkedlist();

    protected ExchangeNode head;
    protected ExchangeNode tail;
    protected int size=0;
    public ExchangeList(){
        head = null;
        tail = null;
    }
    public ExchangeNode getHead(){
        return head;
    }

    public void setHead(ExchangeNode h){ head = h;}

    public void setTail(ExchangeNode t){ tail = t;}

    public ExchangeNode getTail(){
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

    public Exchange removeFirst()throws Exception{
        if (isEmpty()) {
            throw new Exception("Linked List is Empty");
        }
        Exchange temp = head.getData();
        head = head.getNext();
        size--;
        if(size()==0){
            tail=null;
        }
        return temp;
    }

    public void addFirst (Exchange o){
        ExchangeNode a = new ExchangeNode(o);
        a.setNext(head);
        head = a;
        if(size()==0){
            tail = head;
        }
        size++;
    }

    public void addLast (Exchange o){
        ExchangeNode a = new ExchangeNode(o);
        if(size()==0){
            head = a;
        }
        else{
            tail.setNext(a);}
        tail = a;
        size++;
    }
}

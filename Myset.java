/**
 * Created by Pranav ps on 17-09-2015.
 */
public class Myset {
    protected Linkedlist l;


    public Myset(Linkedlist l){
        this.l = l;
    }

    public Myset(){ l = new Linkedlist(); }

    public Linkedlist Listused(){return l;}

    public void setLinkedlist(Linkedlist l){this.l = l;}

    public Boolean IsEmpty(){
        return l.isEmpty();
    }

    public int size(){
        return l.size();
    }

    public Boolean IsMember(Object o)  {
        Node a = l.getHead();
        
        while (a!=null){
            if(a.getData().equals(o))
                return true;
            a = a.getNext();
        }
        return false;
    }

    public void Insert (Object o){
        if(IsMember(o)==false)
            l.addLast(o);
    }

    public void Delete(Object o) throws Exception {
        Linkedlist l1 = new Linkedlist();
        Boolean flag = false;
        if(l.getHead()==null){
            throw new Exception ("Trying to access Empty Myset");
        }
        while(l.getHead()!=null){
            if(l.getHead().getData().equals(o)) {
                l.removeFirst();
                flag = true;
            }
            else{
                l1.addLast(l.removeFirst());
            }
            l.setHead(l.getHead().getNext());
        }
        l=l1;
        if(flag==false)
            throw new Exception ("Element not in the set");
    }



    public Myset Union(Myset a){
        Myset b = new Myset(l);
        Node hc = a.Listused().getHead();
        if(hc==null) return b ;
        while(hc!=null){
            b.Insert(hc.getData());
            hc = hc.getNext();
        }
        return b;
    }

    public Myset Intersection(Myset a){
        if(a.Listused().getHead()==null) return a ;
        Linkedlist l2 = l;
        if(l2.getHead()==null) return this;
        Linkedlist l1 = new Linkedlist();
        while(l2.getHead()!=null){
            if(a.IsMember(l2.getHead().getData()))
                l1.addLast(l2.getHead().getData());
            l2.setHead(l2.getHead().getNext());
        }
        Myset b = new Myset(l1);
        return b;
    }

}

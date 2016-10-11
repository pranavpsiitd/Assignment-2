/**
 * Created by Pranav ps on 18-09-2015.
 */
public class MobilePhoneSet {
    protected Myset m;
    public MobilePhoneSet(Myset mp){m=mp;}
    public MobilePhoneSet(){m = new Myset();}

    public Myset Mysetused(){return m;}

    public Boolean IsEmpty(){
        return m.IsEmpty();
    }

    public Boolean IsMember(MobilePhone mp)  {
        Node a = m.Listused().getHead();

        while (a!=null){
            MobilePhone mps = (MobilePhone)a.getData();
//            if(mps.equals(mp))
            if(mps.number()==mp.number())
                return true;
            a = a.getNext();
        }
        return false;
    }

    public void Insert (MobilePhone mp){

            m.Insert(mp);
    }
    public MobilePhone find(MobilePhone mp){
        Node l = m.Listused().getHead();

        while(l!=null) {
            MobilePhone o = (MobilePhone) l.getData();
            if (o.equals(mp)) return o;
            l = l.getNext();
        }
        return null;
    }
    public void Delete(MobilePhone mp) throws Exception {
        if(!IsMember(mp)){
            throw new Exception("Element not in the set");
        }
        Linkedlist l1 = new Linkedlist();
//        Boolean flag = false;
        Node n1 = m.Listused().getHead();
        if(n1==null){
            throw new Exception ("Trying to access Empty Myset");
        }
        while(n1!=null){
            MobilePhone tmp = (MobilePhone) n1.getData();
            if(tmp.number()==(mp.number())) {
//                flag = true;
            }
            else{
                l1.addLast(n1.getData());
            }
            n1=n1.getNext();
        }
        m.setLinkedlist(l1);
//        if(flag==false)
//            throw new Exception ("Element not in the set");
    }

    public int size(){
        return m.Listused().size();
    }


    public MobilePhoneSet Union(MobilePhoneSet mp){
       return new MobilePhoneSet(m.Union(mp.Mysetused()));

    }

    public MobilePhoneSet Intersection(MobilePhoneSet mp){
        return new MobilePhoneSet(m.Intersection(mp.Mysetused()));
    }

}

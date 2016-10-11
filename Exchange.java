/**
 * Created by Pranav ps on 18-09-2015.
 */
public class Exchange {
    protected int id;
    protected Exchange parent;
    protected ExchangeList children;
    protected MobilePhoneSet residentSet;

    public Exchange getParent() {
        return parent;
    }

    public int getId() {
        return id;
    }

    public ExchangeList getChildren() {
        return children;
    }

    public MobilePhoneSet getResidentSet() {
        return residentSet;
    }

    public void setParent(Exchange parent) {
        this.parent = parent;
    }

    public Exchange(int number){
        id = number;
        parent = null;
        children = new ExchangeList();
        residentSet = new MobilePhoneSet();
    }

    public Exchange(int number,Exchange parent,ExchangeList children ){
        this.id=number;
        this.parent = parent;
        this.children = children;
    }

    public Exchange(){ this(0);}

    public Exchange parent() {return parent;}

    public int numChildren(){
        return children.size();
    }

    public Exchange child(int i) throws Exception {
        if(i>numChildren()) throw new Exception("less than i children");
        if(i <=0) throw new Exception("Invalid Query");
        int j=0;
        ExchangeNode a = children.getHead();
        while(j<i-1){
        a = a.getNext();
        j++;
        }
        return a.getData();

    }

    public boolean isRoot(){
        if (parent==null) return true;
        else return false;
    }

    public RoutingMapTree subtree(int i) throws Exception {
        return new RoutingMapTree(child(i));
    }




    public MobilePhoneSet residentSet(){return residentSet;}


}

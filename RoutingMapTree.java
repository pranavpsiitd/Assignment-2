/**
 * Created by Pranav ps on 18-09-2015.
 */
public class RoutingMapTree {

    protected Exchange root;



    public RoutingMapTree(Exchange root){
        this.root=root;
    }

    public RoutingMapTree(){
        root = new Exchange(0);
    }

    public Exchange getRoot() {
        return root;
    }

    public boolean isExternal(Exchange b){
        return b.numChildren()==0;
    }

    public boolean containsNode(Exchange a){
        boolean flag = false;
        if(root.getId()==a.getId()) //Because of uniqueness of the identifier
        {
            flag = true;
        }
        ExchangeNode en = root.children.getHead();
        for(int i=0;i<root.numChildren();i++)
        {

            RoutingMapTree temp = new RoutingMapTree(en.getData());
            flag= flag|(temp.containsNode(a));
            en = en.getNext();
        }
        return flag;
    }

    public void switchOn(MobilePhone a,Exchange b) throws Exception {
            if(!containsNode(b))
                throw new Exception("No such Exchange in the Routing Map Tree");
            if(!isExternal(b)) throw new Exception("Exchange not a base Exchange"); //added extra
            if(root.getResidentSet().IsMember(a)){ //added extra to distinguish
               if(returnMobilePhonebyID(a.number()).status())
                   throw new Exception("Phone already on in some other Exchange");
                else {
                   switchOff(a);
                   a.setBaseStation(null);
                   deleteMP(a, returnbs(a));

                   a.switchOn();//added extra
                   a.setBaseStation(b);
                   addMP(a, b);

               }
            }
            else{
                a.switchOn();
                a.setBaseStation(b);
                addMP(a, b);
            }
    }

    public void switchOff(MobilePhone a) throws Exception {
        if(!root.getResidentSet().IsMember(a)){
            throw new Exception("Mobile does not Exist");
        }
        else{
            a.switchOff();
//            a.setBaseStation(null); //removed to distinguish
//            deleteMP(a, returnbs(a));
        }
    }

    public void addMP(MobilePhone a,Exchange b) throws Exception {
        if(!containsNode(b))
            throw new Exception("No such Exchange in the Routing Map Tree");


            Exchange temp = b;
            while(temp.getParent()!=null){
                temp.getResidentSet().Insert(a);
                temp = temp.getParent();
            }
        temp.getResidentSet().Insert(a);

    }

    public void deleteMP(MobilePhone a,Exchange b) throws Exception {
        if(!containsNode(b))
            throw new Exception("No such Exchange in the Routing Map Tree");
        if(!root.getResidentSet().IsMember(a)){
            throw new Exception("No such Mobile Phone");
        }
        else{
            Exchange temp = b;
            while(temp!=null){
                temp.getResidentSet().Delete(a);
                temp = temp.getParent();
            }
        }
    }

    public Exchange returnbs(MobilePhone mp) throws Exception {
        Exchange temporary = null;
        if(isExternal(root)&&(root.getResidentSet().IsMember(mp))){
            temporary = root;
        }
        else{
            for(int i=1;i<=root.numChildren();i++){
                RoutingMapTree temp = new RoutingMapTree(root.child(i));
                temporary = temp.returnbs(mp);
                if(temporary!=null) return temporary ;
            }
        }
        return temporary;
    }

    public boolean containsNodebyID(int id){
        Exchange a = new Exchange(id);
        return containsNode(a);
    }

    public Exchange returnExchangebyID(int id) throws Exception {
            Exchange temp=null;
        if(root.getId()==id) {
            temp = root;
            return temp;
        }
        else {
            for (int i = 1; i <= root.numChildren(); i++) {
                temp = root.subtree(i).returnExchangebyID(id);
                if(temp!=null) return temp;
            }
        }
        return temp;
    }

    public MobilePhone returnMobilePhonebyID(int a){
        MobilePhone temp = null;
        MobilePhone m = new MobilePhone(a);
        temp = root.getResidentSet().find(m);
        return temp;
    }

    public void addExchange(Exchange a,int b) throws Exception { //b to be added as a child of a
        if(!containsNode(a)){
            throw new IllegalStateException("No such node to be added exists");
        }
        else{
            if(containsNodebyID(b))
                throw new Exception("Node to be added already exists");
            else{
                Exchange b_new = new Exchange(b);
                b_new.setParent(a);
                a.getChildren().addLast(b_new);
            }
        }
    }

    public void addExchange(int a,int b) throws Exception {
        if(returnExchangebyID(a)==null){
            throw new Exception("No such Exchange exists");
        }
        addExchange(returnExchangebyID(a),b);
    }

    public Exchange containsMP(int id ) throws Exception {
        Exchange temp=null;
        MobilePhone mp = new MobilePhone(id);
        if(root.getResidentSet().IsMember(mp)){
           if(isExternal(root)) return root;
            else {
               for(int i=1;i<=root.numChildren();i++){
                   temp = root.subtree(i).containsMP(id);
                   if(temp!=null){
                       return temp;
                   }
               }
           }
        }
        return temp;
    }

    public void switchOnMobile(int a,int b) throws Exception {
        if(!containsNodebyID(b))
            throw new Exception("No such Exchange exists");
        if(containsMP(a)!=null){
                    switchOn(returnMobilePhonebyID(a),returnExchangebyID(b));//added extra
//            if(!returnMobilePhonebyID(a).status()) {
////                switchOffMobile(a);    // removed extra to distinguish
//
//                switchOnMobile(a, b);
//            }
//            else                //added extra
//                throw new Exception("Phone already on in some other Exchange");
        }
        else{
            MobilePhone mpa = new MobilePhone(a);
            switchOn(mpa, returnExchangebyID(b));
        }
    }

    public void switchOffMobile(int a) throws Exception {
        if(containsMP(a)==null) throw new Exception ("Mobile Phone does not exist");
        else{
            switchOff(returnMobilePhonebyID(a));
        }
    }

    public void queryNthChild(int a,int b) throws Exception {
        System.out.println(returnExchangebyID(a).child(b+1).getId());
    }

    public void queryMobilePhoneSet(int a) throws Exception {
        if(returnExchangebyID(a)==null) throw new Exception("No such Exchange");
        Node n = returnExchangebyID(a).getResidentSet().Mysetused().Listused().getHead();
        if(returnExchangebyID(a).getResidentSet().size()==0){
            throw new Exception("No mobile phones registered with this exchange");
        }
        int j=1;
        for(int i=1;i<=returnExchangebyID(a).getResidentSet().size();i++){
            MobilePhone m = (MobilePhone) n.getData();
            if(m.status()==true) {  //Added Extra to distinguish
                if (j == 1) {
                    System.out.print(m.number());
                    j++;
                }
                else {
                    System.out.print(", " + m.number());
                }
            }
            n = n.getNext();
        }
        System.out.println();
    }

    public void performAction(String actionMessage) throws Exception {
        String[] arr = actionMessage.split(" ");
//        int end = actionMessage.indexOf(' ');
//        String sString = actionMessage.substring(0,end);
        switch (arr[0]){
            case "addExchange" : try{addExchange(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));}
                            catch(Exception e){
                                System.out.print("Possible cases are : ");
                                System.out.println("Either the parent Exchange does not exist or Exchange to be added already exists");
                                System.out.println(e.toString());
                            }
                                    break;
            case "switchOnMobile": try{switchOnMobile(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));}
            catch(Exception e){
                System.out.print("Possible cases are : ");
                System.out.println("Exchange does not exist or Exchange not a base Exchange or Phone already on in some other Exchange ");
                System.out.println(e.toString());
            }
                                    break;
            case "switchOffMobile": try{switchOffMobile(Integer.parseInt(arr[1]));}
                                    catch(Exception e){
                                        System.out.print("Possible cases are : ");
                System.out.println("You are trying to switch off a mobile phone that does not exist");
                                        System.out.println(e.toString());
            }
                                    break;
            case "queryNthChild": try{System.out.print(actionMessage + ": ");
                                    queryNthChild(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));}
            catch(Exception e){
//                if(e instanceof NullPointerException){
//                    System.out.println("Invalid Query for the child");
//                }
                System.out.print("Possible cases are : ");
                System.out.println("Invalid Query for the child");
                System.out.println(e.toString());
            }
                                    break;
            case "queryMobilePhoneSet": System.out.print(actionMessage + ": ");
                                       try{ queryMobilePhoneSet(Integer.parseInt(arr[1]));}
                                       catch(Exception e){
                                           System.out.print("Possible cases are : ");
                                           System.out.println("Either No Mobile Phones Registered with this Exchange or No such exchange exists ");
                                        System.out.println(e.toString());
                                       }
                                        break;
            default: System.out.println("Invalid Input");
        }

    }
}

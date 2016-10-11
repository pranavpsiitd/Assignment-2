/**
 * Created by Pranav ps on 18-09-2015.
 */
public class MobilePhone implements Runnable {

    protected int id;
    protected boolean c_status;
    protected Exchange BaseStation;
    public boolean Oncall;

    public MobilePhone(int num){
        id = num;
        c_status=true;
        BaseStation = null;
        Oncall = false;
        Thread thread = new Thread(this,String.valueOf(id));
        thread.start();
    }
    public MobilePhone(){ this(0);}

    public int number(){return id;}

    public boolean status(){return c_status;}

    public void switchOn(){ c_status=true;}

    public void switchOff(){c_status=false;}

    public void setBaseStation(Exchange baseStation) {
        BaseStation = baseStation;
    }
    public boolean equals(MobilePhone mp){
        if (id==mp.number()) {
            return true;
        }
        else return false;
    }
    public Exchange location() throws Exception{
    if(c_status) return BaseStation;
    else throw new Exception("Phone is Off so no base station registered");
    }


    @Override
    public void run() {
        System.out.println("This is currently running on a separate thread, " + Thread.currentThread().getName()+
                " the id is: " + Thread.currentThread().getId());
    }
}

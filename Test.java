public class Test{
public static void main(String [] args) throws Exception {
 RoutingMapTree tr = new RoutingMapTree();

	tr.addExchange (0,1);
	tr.addExchange(0, 2);

	tr.addExchange(0, 3);
	tr.queryNthChild(0, 0);
	tr.queryNthChild(0, 2);
	tr.addExchange(1, 4);
	tr.addExchange(1, 5);
	tr.addExchange(2, 6);
	tr.addExchange(2, 7);
	tr.addExchange(2, 8);
	tr.addExchange(3, 9);
	tr.queryNthChild(2, 0);
	tr.queryNthChild(3, 0);
	tr.switchOnMobile(989, 4);
	tr.switchOnMobile(876, 4);
	tr.queryMobilePhoneSet(4);
	tr.queryMobilePhoneSet(1);
	tr.switchOnMobile(656, 5);
	tr.switchOnMobile(54, 5);
	tr.queryMobilePhoneSet(1);
//	MobilePhone tmp = new MobilePhone(54);
//	System.out.println(tr.getRoot().getResidentSet().IsMember(tmp));
	tr.switchOffMobile (656);
	tr.queryMobilePhoneSet (1);
	tr.switchOnMobile (213,6);
	tr.switchOnMobile (568,7);
	tr.switchOnMobile (897,8);
	tr.switchOnMobile (295,8);
	tr.switchOnMobile (346,9);
	tr.queryMobilePhoneSet (0);

}
}
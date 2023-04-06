package fp.app.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.app.*;

public class TestApp {
	
	public static void showApp(App a) {
		System.out.println(a);
		System.out.println("getName: "+a.getName());
		System.out.println("getPrice: "+a.getPrice());
		System.out.println("getRating: "+a.getRating());
		System.out.println("getReview: "+a.getReviews());
		System.out.println("getSize: "+a.getSize());
		System.out.println("getCategory: "+a.getCategory());
		System.out.println("getLastUpdate: "+a.getLastUpdate());
		System.out.println("getPremium: "+a.getPremium());
		System.out.println("getRecommend: "+a.getRecommended());
		System.out.println("getRestriction: " + a.getRestriction());
		System.out.println("getUser and User's toString: "+a.getUser());
	}
	
	public static void testConstructor1(String n, Set<String> cat, Double rat, Integer rev, Double s, Double p, Restrictions res, LocalDate lU, User tU) {
		
		try {
			App a = new App(n, cat, rat, rev,s,p,res,lU,tU);	
			showApp(a);
		} catch(IllegalArgumentException e) {
			System.out.println("Caught exception:\n   " + e);	
		} catch (Exception e) {
			System.out.println("Unexpected exception:\n   " + e);
		}

	}

	public static void testConstructor2(String n, Double p, Restrictions res) {
		try {
			App a = new App(n, p, res);	
			showApp(a);
			
		} catch(IllegalArgumentException e) {
			System.out.println("Caught exception:\n   " + e);	
		} catch (Exception e) {
			System.out.println("Unexpected exception:\n   " + e);
		}
		
	}

	public static void testEqualityAndCompareTo(String n, Double p, Restrictions res) {
		App a1 = new App(n, p, res);
		App a2 = new App(n, p, res);
		System.out.println("For two equal values, equals is: " + a1.equals(a2));
		System.out.println("For two equal values, compareTo is: " + a1.compareTo(a2));
		
		
		System.out.println("For two different values, equals is: " + a1.equals(a2));
		a2.setName("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
		System.out.println("For two values, when the name of the first is after the name of the second, compareTo is: " + a2.compareTo(a1));
		System.out.println("For two values, when the name of the first is before the name of the second, compareTo is: " + a1.compareTo(a2));
		a2.setPrice(p+0.2);
		System.out.println("For two values, when the price of the first is more than the price of the second, compareTo is: " + a2.compareTo(a1));
		System.out.println("For two values, when the price of the first is less than the price of the second, compareTo is: " + a1.compareTo(a2));
		a2.setRestriction(Restrictions.MATURE);
		System.out.println("For two values, when the restriction of the first is harder than the restriction of the second, compareTo is: " + a2.compareTo(a1));
		System.out.println("For two values, when the restriction of the first is lesser than the price of the second, compareTo is: " + a1.compareTo(a2));
	}
	
	public static void testAddCategory(App a, String s) {
		System.out.println("Original category value: " + a.getCategory());
		a.addCategory(s);
		System.out.println("Final category value: " + a.getCategory());
	}
	
	public static void testSetName(App a, String s) {
		try{
			System.out.println("Original name value: " + a.getName());
			a.setName(s);
			System.out.println("Final name value: " + a.getName());
		}catch(IllegalArgumentException e) {
			System.out.println("Caught exception:\n   " + e);	
		}
	}
	
	private static void testSetReviews(App a, int i) {
		try{
			System.out.println("Original reviews value: " + a.getReviews());
			a.setReviews(i);
			System.out.println("Final reviews value: " + a.getReviews());
		}catch(IllegalArgumentException e) {
			System.out.println("Caught exception:\n   " + e);	
		}
	}

	private static void testSetSize(App a, double d) {
		try{
			System.out.println("Original size value: " + a.getSize());
			a.setSize(d);
			System.out.println("Final size value: " + a.getSize());
		}catch(IllegalArgumentException e) {
			System.out.println("Caught exception:\n   " + e);	
		}
	}

	private static void testSetPrice(App a, double d) {
		try{
			System.out.println("Original price value: " + a.getPrice());
			a.setPrice(d);
			System.out.println("Final price value: " + a.getPrice());
		}catch(IllegalArgumentException e) {
			System.out.println("Caught exception:\n   " + e);	
		}
	}

	private static void testSetRestriction(App a, Restrictions r) {
		try{
			System.out.println("Original restriction value: " + a.getRestriction());
			a.setRestriction(r);
			System.out.println("Final restriction value: " + a.getRestriction());
		}catch(IllegalArgumentException e) {
			System.out.println("Caught exception:\n   " + e);	
		}
	}

	private static void testSetLastUpdate(App a, LocalDate lU) {
		try{
			System.out.println("Original last Update value: " + a.getLastUpdate());
			a.setLastUpdate(lU);
			System.out.println("Final last Update value: " + a.getLastUpdate());
		}catch(IllegalArgumentException e) {
			System.out.println("Caught exception:\n   " + e);	
		}
	}

	private static void testSetRating(App a, double d) {
		try{
			System.out.println("Original rating value: " + a.getRating());
			a.setRating(d);
			System.out.println("Final rating value: " + a.getRating());
		}catch(IllegalArgumentException e) {
			System.out.println("Caught exception:\n   " + e);	
		}
	}
	
	public static void main(String [] args) {
		HashSet<String> cat = new HashSet<String>(Set.of("Games", "Action"));
		LocalDate lUpdate = LocalDate.of(2018, 2, 13);
		User user = new User(Gender.FEMALE, 23, "ESP");
		
		//Initial case
		Integer i= 1;
		System.out.println("====================================");
		System.out.println("Constructor 1 - Trial " + i);
		testConstructor1("Subway surfers", cat, 4.5, 200000, 200.7, 0.0,Restrictions.EVERYONE, 
				lUpdate, user);
		
		//Case with null name
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 1 - Trial " + i);
		testConstructor1(null, cat, 4.5, 200000, 200.7, 0.0,Restrictions.EVERYONE, 
				lUpdate, user);
		
		//Case with price <0
		i++;
		System.out.println("====================================");
		System.out.println("====================================");
		System.out.println("Constructor 1 - Trial " + i);
		testConstructor1("Subway surfers", cat, 4.5, 200000, 200.7, -10.9,Restrictions.EVERYONE, 
				lUpdate, user);

		
		//Case with rating <0
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 1 - Trial " + i);
		testConstructor1("Subway surfers", cat, -4.5, 200000, 200.7, 0.0,Restrictions.EVERYONE, 
				lUpdate, user);


		//Case with rating >5
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 1 - Trial " + i);
		testConstructor1("Subway surfers", cat, 44.5, 200000, 200.7, 0.0,Restrictions.EVERYONE, 
				lUpdate, user);


		//Case with size <0
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 1 - Trial " + i);
		testConstructor1("Subway surfers", cat, 4.5, 200000, -3.0, 0.0,Restrictions.EVERYONE, 
				lUpdate, user);


		//Case with reviews<0
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 1 - Trial " + i);
		testConstructor1("Subway surfers", cat, 4.5, -200000, 200.7, 0.0,Restrictions.EVERYONE, 
				lUpdate, user);
		
		//Case with Last update date after today
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 1 - Trial " + i);
		testConstructor1("Subway surfers", cat, 4.5, -200000, 200.7, 0.0,Restrictions.EVERYONE, 
					LocalDate.now().plusYears(1), user);

		i= 1;
		System.out.println("====================================");
		System.out.println("Constructor 2 - Trial " + i);
		testConstructor2("Subway surfers", 0.0, Restrictions.EVERYONE);
		
		//Case with null name
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 2 - Trial " + i);
		testConstructor2(null, 0.0, Restrictions.EVERYONE);
		
		//Case with price<0
		i++;
		System.out.println("====================================");
		System.out.println("Constructor 2 - Trial " + i);
		testConstructor2("Subway Surfers", -1.0, Restrictions.EVERYONE);
		
		i= 1;
		System.out.println("====================================");
		System.out.println("Equals and compareTo - Trial " + i);
		testEqualityAndCompareTo("Subway Surfers", 0.0, Restrictions.EVERYONE);
		
		i= 1;
		System.out.println("====================================");
		System.out.println("addCategory - Trial " + i);
		App a = new App("Subway surfers", cat, 4.5, 200000, 200.7, 0.0,Restrictions.EVERYONE, 
				lUpdate, user);
		testAddCategory(a, "Fun");
		
		i= 1;
		System.out.println("====================================");
		System.out.println("addCategory - Trial " + i);
		testAddCategory(a, "Fun");
		
		i= 1;
		System.out.println("====================================");
		System.out.println("setName - Trial " + i);
		testSetName(a, "Jetpack Joyride");
		
		//Null name
		i++;
		System.out.println("====================================");
		System.out.println("setName - Trial " + i);
		testSetName(a, null);
		
		i= 1;
		System.out.println("====================================");
		System.out.println("setRating - Trial " + i);
		testSetRating(a, 5.0);
		
		//rating <0
		i++;
		System.out.println("====================================");
		System.out.println("setRating - Trial " + i);
		testSetRating(a, -3.0);
		
		//rating >5
		i++;
		System.out.println("====================================");
		System.out.println("setRating - Trial " + i);
		testSetRating(a, 6.0);		
		
		i=1;
		System.out.println("====================================");
		System.out.println("setReviews - Trial " + i);
		testSetReviews(a, 30000);
		
		//reviews <0
		i++;
		System.out.println("====================================");
		System.out.println("setReviews - Trial " + i);
		testSetReviews(a, -30000);
		
		i=1;
		System.out.println("====================================");
		System.out.println("setSize - Trial " + i);
		testSetSize(a, 9.8);
		
		//size <0
		i++;
		System.out.println("====================================");
		System.out.println("setSize - Trial " + i);
		testSetSize(a, -9.8);
		
		i=1;
		System.out.println("====================================");
		System.out.println("setPrice - Trial " + i);
		testSetPrice(a, 9.8);
		
		
		//price <0
		i++;
		System.out.println("====================================");
		System.out.println("setPrice - Trial " + i);
		testSetPrice(a, -9.8);
		
		i=1;
		System.out.println("====================================");
		System.out.println("setRestriction - Trial " + i);
		testSetRestriction(a, Restrictions.MATURE);
		
		i=1;
		System.out.println("====================================");
		System.out.println("setlastUpdate - Trial " + i);
		testSetLastUpdate(a, LocalDate.of(1999, 12, 2));
		
		// Date after today
		i++;
		System.out.println("====================================");
		System.out.println("setlastUpdate - Trial " + i);
		testSetLastUpdate(a, LocalDate.now().plusYears(1));	
	}
}

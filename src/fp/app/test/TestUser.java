package fp.app.test;

import fp.app.Gender;
import fp.app.User;

public class TestUser {

	public static void showUser(User u) {
		System.out.println(u);
		System.out.println("gender: "+u.gender());
		System.out.println("age: "+ u.age());
		System.out.println("getRating: "+u.nationality());
	}
	
	public static void testConstructor(Gender g, Integer a, String n) {	
		try {
			User u = new User(g, a, n);	
			showUser(u);
		} catch(IllegalArgumentException e) {
			System.out.println("Caught exception:\n   " + e);	
		} catch (Exception e) {
			System.out.println("Unexpected exception:\n   " + e);
		}
	}
	
	public static void main(String [] args) {
		//Initial case
		Integer i= 1;
		System.out.println("====================================");
		System.out.println("Constructor - Trial " + i);
		testConstructor(Gender.FEMALE, 20, "ESP");
		
		//Case with age<0
		i++;
		System.out.println("====================================");
		System.out.println("Constructor - Trial " + i);
		testConstructor(Gender.FEMALE, -20, "ESP");
		
	}
}

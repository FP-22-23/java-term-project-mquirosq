package fp.app.test;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import fp.app.App;
import fp.app.AppFactory;
import fp.app.Apps;
import fp.app.Rec;
import fp.app.Restrictions;

public class TestApps {

	public static void main(String[] args) {
		Apps apps = AppFactory.readApps("data/GooglePlayAppsData1.csv");
		
		System.out.println("\ntestContainsCategory -- EXISTS");
		System.out.println("================================");
		testContainsCategory(apps, "game"); //Expected true
		testContainsCategory(apps, "cooking"); //Expected false
		
		System.out.println("\ntestAveragePrice -- AVERAGE");
		System.out.println("================================");
		testAveragePrice(apps, 4.3);
		
		System.out.println("\ntestGetRecommendedApps -- Selection filtering");
		System.out.println("================================");
		testGetRecommendedApps(apps, Rec.MID);
		testGetRecommendedApps(apps, Rec.HIGH);
		
		System.out.println("\ntestGroupRestrictions");
		System.out.println("================================");
		testGroupRestrictions(apps, Restrictions.TEEN);
		testGroupRestrictions(apps, Restrictions.EVERYONE);
		
		System.out.println("\ntestReviewsByPrice");
		System.out.println("================================");
		testReviewsByPrice(apps, 0.00);
		testReviewsByPrice(apps, 1.99);
	
		System.out.println("\nExtra functions");
		
		System.out.println("\ntestCheckRestrictionCategoryRating -- FOR ALL");
		System.out.println("================================");
		testCheckRestrictionCategoryRating(apps, Restrictions.ADULT, "dating", 4.0);
		testCheckRestrictionCategoryRating(apps, Restrictions.EVERYONE, "dating", 4.0);
		
		System.out.println("\ntestCountFreeApps -- COUNTER");
		System.out.println("================================");
		testCountFreeApps(apps);
	}
	
	private static void testContainsCategory(Apps apps, String cat) {
		try {System.out.println("There are apps of the specified category (" + cat + "): " + apps.containsCategory(cat)+"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}		
	}
	
	private static void testAveragePrice(Apps apps, Double rating) {
		try {
			System.out.println("The average price of the apps with a rating higher to"
					+ " the given rating (" + rating + ") is: " + apps.averagePrice(rating)+"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}	
	}
	
	private static void testGetRecommendedApps(Apps apps, Rec rec){
		try {
			List<App> recApps = apps.getRecommendedApps(rec);
			System.out.println("The apps that are recommended in a level equal or higher"
					+ " than the given recommendation (" + rec + ") are " + recApps.size());
			System.out.println("Three of these apps are: " + recApps.subList(0, 2) +"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}
	}
	
	private static void testGroupRestrictions(Apps apps, Restrictions res) {
		try {
			Map<Restrictions,SortedSet<App>> restMap = apps.groupRestrictions();
			System.out.println("The set of restrictions that appear in the data set is : "  + restMap.keySet());
			System.out.println("The apps related to the key " + res + " are " + restMap.get(res).size() + 
					".\nThe first is: " + restMap.get(res).first() + " \nAnd the last is: " + restMap.get(res).last() + "\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}
	}
	
	private static void testReviewsByPrice(Apps apps, Double price) {
		try {
			Map<Double, Long> revMap = apps.reviewsByPrice();
			System.out.println("The set of prices that appear in the data set is : "  + revMap.keySet());
			System.out.println("The number of reviews related to the key " + price + " are " + revMap.get(price) + 
					".\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}
	}
	
	private static void testCheckRestrictionCategoryRating(Apps apps, Restrictions res, String cat, Double rate) {
		try {System.out.println("All the apps with the given category (" + cat + ") and rating (" + rate + ") "
				+ "have a restriction equal or higher that the given one (" + res + "): " 
				+ apps.checkRestrictionCategoryRating(res, cat, rate)+"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}
	}
		
	private static void testCountFreeApps(Apps apps) {
			try {
				System.out.println("The number of free apps is: " + apps.countFreeApps()+"\n");
			} 
			catch (Exception e) {
				System.err.println("Unexpected exception caught: " + e.getMessage());
			}	
		}

	
}

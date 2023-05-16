package fp.app.test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.stream.Collectors;

import fp.app.App;
import fp.app.AppFactory;
import fp.app.Apps;
import fp.app.Rec;
import fp.app.Restrictions;
import fp.app.User;

public class TestApps {

	public static void main(String[] args) {
		Apps apps = AppFactory.readApps("data/GooglePlayAppsData1.csv");
		
		System.out.println("\ntestContainsCategory -- EXISTS");
		System.out.println("================================");
		testContainsCategory(apps, "game"); //Expected true
		testContainsCategory(apps, "cooking"); //Expected false
		
		System.out.println("\ntestContainsCategoryStream -- EXISTS");
		System.out.println("================================");
		testContainsCategoryStream(apps, "game"); //Expected true
		testContainsCategoryStream(apps, "cooking"); //Expected false
		
		System.out.println("\ntestAveragePrice -- AVERAGE");
		System.out.println("================================");
		testAveragePrice(apps, 4.3);
		
		System.out.println("\ntestAveragePriceStream -- AVERAGE");
		System.out.println("================================");
		testAveragePriceStream(apps, 4.3);
		
		System.out.println("\ntestGetRecommendedApps -- Selection filtering");
		System.out.println("================================");
		testGetRecommendedApps(apps, Rec.MID);
		testGetRecommendedApps(apps, Rec.HIGH);
		
		System.out.println("\ntestGetRecommendedAppsStream -- Selection filtering");
		System.out.println("================================");
		testGetRecommendedAppsStream(apps, Rec.MID);
		testGetRecommendedAppsStream(apps, Rec.HIGH);
		
		System.out.println("\ntestGroupRestrictions");
		System.out.println("================================");
		testGroupRestrictions(apps, Restrictions.TEEN);
		testGroupRestrictions(apps, Restrictions.EVERYONE);
		
		System.out.println("\ntestReviewsByPrice");
		System.out.println("================================");
		testReviewsByPrice(apps, 0.00);
		testReviewsByPrice(apps, 1.99);
		
		System.out.println("\ntestReviewsByPriceStream");
		System.out.println("================================");
		testReviewsByPriceStream(apps, 0.00);
		testReviewsByPriceStream(apps, 1.99);
		
		System.out.println("\ntestGetBiggestAppOfCategory");
		System.out.println("================================");
		testGetBiggestAppOfCategory(apps, "game");
		
		System.out.println("\ntestGetNMostReviewsWithRestriction");
		System.out.println("================================");
		testGetNMostReviewsWithRestriction(apps, Restrictions.TEEN, 5L);
		
		System.out.println("\ntestUserByCategory");
		System.out.println("================================");
		Set<String> cat = new HashSet<String>();
		cat.add("dating");
		testUserByCategory(apps, cat);
	
		System.out.println("\ntestMinReviewsByRestriction");
		System.out.println("================================");
		testMinReviewsByRestriction(apps, Restrictions.TEEN);
		
		System.out.println("\ntestNMaxSizeByRestriction");
		System.out.println("================================");
		testNMaxSizeByRestriction(apps, 5L, Restrictions.TEEN);
		
		System.out.println("\ntestUserMaxAvPrice");
		System.out.println("================================");
		testUsermaxAvPrice(apps);
		
		
		System.out.println("\nExtra functions");
		
		System.out.println("\ntestCheckRestrictionCategoryRating -- FOR ALL");
		System.out.println("================================");
		testCheckRestrictionCategoryRating(apps, Restrictions.ADULT, "dating", 4.0);
		testCheckRestrictionCategoryRating(apps, Restrictions.EVERYONE, "dating", 4.0);
		
		System.out.println("\ntestCheckRestrictionCategoryRatingStream -- FOR ALL");
		System.out.println("================================");
		testCheckRestrictionCategoryRatingStream(apps, Restrictions.ADULT, "dating", 4.0);
		testCheckRestrictionCategoryRatingStream(apps, Restrictions.EVERYONE, "dating", 4.0);
		
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
	
	private static void testContainsCategoryStream(Apps apps, String cat) {
		try {System.out.println("There are apps of the specified category (" + cat + "): " + apps.containsCategoryStream(cat)+"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}		
	}
	
	private static void testAveragePriceStream(Apps apps, Double rating) {
		try {
			System.out.println("The average price of the apps with a rating higher to"
					+ " the given rating (" + rating + ") is: " + apps.averagePriceStream(rating)+"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}	
	}
	
	private static void testGetRecommendedAppsStream(Apps apps, Rec rec){
		try {
			List<App> recApps = apps.getRecommendedAppsStream(rec);
			System.out.println("The apps that are recommended in a level equal or higher"
					+ " than the given recommendation (" + rec + ") are " + recApps.size());
			System.out.println("Three of these apps are: " + recApps.subList(0, 2) +"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}
	}
	
	private static void testGetBiggestAppOfCategory(Apps apps, String cat) {
		try {
			System.out.println("The biggest app for the category " + cat + " is: " + apps.getBiggestAppOfCategory(cat) + ". With " + apps.getBiggestAppOfCategory(cat).getSize()+"Mb of storage needed.\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}	
	}
	
	private static void testGetNMostReviewsWithRestriction(Apps apps, Restrictions res, Long n) {
		try {
			System.out.println("The " + n + " apps with a restriction lower or equal to " + res + " with the most amount of reviews are: " + apps.getNMostReviewsWithRestriction(res, n)+"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}	
	}
	
	private static void testReviewsByPriceStream(Apps apps, Double price) {
		try {
			Map<Double, Long> revMap = apps.reviewsByPriceStream();
			System.out.println("The set of prices that appear in the data set is : "  + revMap.keySet());
			System.out.println("The number of reviews related to the key " + price + " are " + revMap.get(price) + 
					".\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}
	}
	
	private static void testUserByCategory(Apps apps, Set<String> cat) {
		try {
			Map<Set<String>, SortedSet<User>> revMap = apps.usersByCategory();
			System.out.println("The set of categories that appear in the data set is : "  + revMap.keySet());
			System.out.println("Some users related to the key " + cat + " are " + revMap.get(cat).stream().limit(3).collect(Collectors.toSet()) + 
					".\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}
	}
	
	private static void testMinReviewsByRestriction(Apps apps, Restrictions res) {
		try {
			Map<Restrictions, App> revMap = apps.minReviewsByRestriction();
			System.out.println("The set of restrictions that appear in the data set is : "  + revMap.keySet());
			System.out.println("The app related to the key " + res + " is " + revMap.get(res) + ".\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}
	}
	
	private static void testNMaxSizeByRestriction(Apps apps, Long n, Restrictions res) {
		try {
			SortedMap<Restrictions, List<App>> revMap = apps.nMaxSizeByRestriction(n);
			System.out.println("The set of restrictions that appear in the data set is : "  + revMap.keySet());
			System.out.println("The sizes of the " + n + " apps associated to the restriction " + res + " are: " + revMap.get(res).stream().map(App :: getSize).toList() +"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}
	}
	
	private static void testUsermaxAvPrice(Apps apps) {
		try {System.out.println("The nationality of the user with the highest average price is: " + apps.userMaxAvPrice()+"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}		
	}
	
	
	//EXTRA TESTS
	
	private static void testCheckRestrictionCategoryRating(Apps apps, Restrictions res, String cat, Double rate) {
		try {System.out.println("All the apps with the given category (" + cat + ") and rating (" + rate + ") "
				+ "have a restriction equal or higher that the given one (" + res + "): " 
				+ apps.checkRestrictionCategoryRating(res, cat, rate)+"\n");
		} 
		catch (Exception e) {
			System.err.println("Unexpected exception caught: " + e.getMessage());
		}
	}
	
	private static void testCheckRestrictionCategoryRatingStream(Apps apps, Restrictions res, String cat, Double rate) {
		try {System.out.println("All the apps with the given category (" + cat + ") and rating (" + rate + ") "
				+ "have a restriction equal or higher that the given one (" + res + "): " 
				+ apps.checkRestrictionCategoryRatingStream(res, cat, rate)+"\n");
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

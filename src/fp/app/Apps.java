package fp.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utils.Checkers;

public class Apps {
	
	private List<App> apps;

	/**
	 * Constructor that creates a container of type Apps with an empty list
	 */
	public Apps() {
		apps = new ArrayList<App>();
	}
	
	/**Constructor that creates a container from a Collection<App> parameter
	 * 
	 * @param sa
	 * 		Collection containing App objects
	 */
	public Apps(Collection<App> sa) {
		apps = new ArrayList<App>(sa);
	}
	
	/**
	 * Constructor that creates a container from a stream
	 * @param sa
	 * 		Stream containing App objects
	 */
	public Apps(Stream<App> sa) {
		apps = sa.toList();
	}
	
	//Getter
	public List<App> getApps() {
		return apps;
	}

	// String representation
	public String toString() {
		return "Apps [apps=" + apps + "]";
	}

	//Hash code
	public int hashCode() {
		return Objects.hash(apps);
	}

	//Equality criterion
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apps other = (Apps) obj;
		return Objects.equals(apps, other.apps);
	}
	
	/**
	 * Get number of items
	 * @return number of items in apps property
	 */
	
	public Integer getSize() {
		return apps.size();
	}
	
	/**
		Add an item to the apps property
		@param a
			App that will be added
	 */
	
	public void addApp(App a) {
		apps.add(a);
	}
	
	/**
	 * Add a collection of items to apps property
	 * @param a
	 * 		Collection of items that will be added
	 */
	
	public void addApps(Collection<App> a) {
		apps.addAll(a);
	}
	
	/**
	 * Delete an item from the apps property
	 * @param a
	 * 		App object that will be deleted
	 */
	
	public void removeApp(App a) {
		apps.remove(a);
	}

	
	/**
	 * EXISTS: Check if there exits an App with a certain category in the apps property using for loops
	 * @param cat
	 * 		Category that will be looked for.
	 * @return true if there exists an App, false if there does not.
	 */
	public Boolean containsCategory(String cat) {
		Boolean out = false;
		for (App a : apps) {
			if (a.getCategory().contains(cat)){		//check condition
				out = true;
				break;								//Stop if condition is met
			}
		}
		return out;
	}
	
	
	/**
	 * AVERAGE: Get the average price of the apps in the data set with a rating over a given value, implemented using for loops
	 * @param rating
	 * 		rating that will be used to filter the App objects
	 * @return
	 * 		The average price of the filtered apps
	 */
	public Double averagePrice(Double rating) {
		Double out = 0.0;
		Integer count = 0;
		for (App a : apps) {
			if (a.getRating()>rating) {			//Check condition
				count ++;						//Add one to the number of terms summed
				out += a.getPrice();			//Sum the term
			}
		}
		return out/count;						//Final average calculation
	}
	
	
	/**
	 * Selection with filtering: get a list of the apps that are recommended as stated in the parameter
	 * or more, implemented using for loops.
	 * @param rec
	 * 		Recommendation level that will be used to filter the App objects 
	 * @return
	 * 		List of the selected App objects
	 * 		
	 */
	public List<App> getRecommendedApps(Rec rec){
		List<App> out = new ArrayList<App>();
		for (App a : apps) {
			if (a.getRecommended().compareTo(rec)>=0) {		//Check condition
				out.add(a);									//Add to the list
			}
		}
		return out;
	}
	
	
	/**
	 * Grouping method implemented using for loops
	 * @return Map in which the keys are the Restrictions, and the 
	 * values a SortedSet of objects of type App sorted by the natural order.
	 */
	
	public Map<Restrictions,SortedSet<App>> groupRestrictions(){
		Map<Restrictions,SortedSet<App>> out = new HashMap<Restrictions,SortedSet<App>>();
		Restrictions k = null;
		for (App a : apps) {
			k = a.getRestriction();
			if (out.containsKey(k)) {		//Check already in map
				out.get(k).add(a);			//update pair
			}
			else {										//Not already in map:
				SortedSet<App> v = new TreeSet<App>();	
				v.add(a);								//Set value
				out.put(k, v);							//Add pair
			}
		}
		return out;
	}
	
	/**
	 * Grouping method implemented using for loops
	 * @return Map in which the keys are the different prices of the apps, and the 
	 * values are the sum of the number of reviews of the apps with said price.
	 */

	public Map<Double, Long> reviewsByPrice(){
		Map<Double, Long> out = new HashMap<Double, Long>();
		for (App a : apps) {
			if(out.containsKey(a.getPrice())) {						//Check if already in map
				Long v = out.get(a.getPrice()) + a.getReviews();	//Set value
				out.put(a.getPrice(), v);							//Update pair
			}
			else {														//Not in map
				out.put(a.getPrice(), Long.valueOf(a.getReviews()));	//Add pair
			}
		}
		return out;
	}
	
	
	//Delivery 3:
	//Part I
	
	/**
	 * EXISTS: Check if there exits an App with a certain category in the apps property using Streams
	 * @param cat
	 * 		Category that will be looked for.
	 * @return true if there exists an App, false if there does not.
	 */
	public Boolean containsCategoryStream(String cat) {
		return apps.stream()
				.anyMatch(a -> a.getCategory().contains(cat)); //Check for any match
	}
	
	/**
	 * AVERAGE: Get the average price of the apps in the data set with a rating over a given value, 
	 * implemented using for streams
	 * @param rating
	 * 		rating that will be used to filter the App objects
	 * @return
	 * 		The average price of the filtered apps
	 */
	public Double averagePriceStream(Double rating) {
		return apps.stream()
				.filter(a -> a.getRating()>rating)					//Apply condition
				.mapToDouble(a -> a.getPrice()).average()			//Get price values, average
				.orElse(0);											// If average cant't be computed return 0 as average
		
	}
	
	/**
	 * Selection with filtering: get a list of the apps that are recommended as stated in the parameter
	 * or more, implemented using streams.
	 * @param rec
	 * 		Recommendation level that will be used to filter the App objects 
	 * @return
	 * 		List of the selected App objects
	 * 		
	 */
	public List<App> getRecommendedAppsStream(Rec rec){
		return apps.stream()
				.filter(a -> a.getRecommended().compareTo(rec)>=0)		//Apply condition
				.toList();												//Return a list
	}
		
	
	/**
	 * Returns the App with the maximum price that is associated to a given category (Parameter cat)
	 * @param cat
	 * 		category the apps will have to contain (filtering)
	 * @return
	 * 		App with maximum price associated to the given category
	 */
	public App getBiggestAppOfCategory(String cat) {
		return apps.stream()
				.filter(a -> a.getCategory().contains(cat))					//Apply condition
				.max((a1, a2)-> a1.getSize().compareTo(a2.getSize()))		//Get maximum
				.orElse(null);												//If max can't be computed return null
	}
	
	
	/**
	 * Returns a List with the n apps with a restriction lower or equal to the given
	 * restriction (parameter res) sorted by number of reviews (n with maximum reviews)
	 * @param res
	 * 		Restriction that will be used to filter the App objects, they have to contain
	 * 		a restriction lower or equal to the given one.
	 * @param n
	 * 		Limits the output to n elements
	 * @return
	 * 		List of App objects containing the n filtered App objects with more reviews
	 */
	public List<App> getNMostReviewsWithRestriction(Restrictions res, Long n){
		checkLimit(n);
		return apps.stream()
				.filter(a -> a.getRestriction().compareTo(res)>=0)							//Apply condition
				.sorted(Comparator.comparing(a -> ((App) a).getReviews()).reversed())					//Sort by reviews
				.limit(n).toList();															//Limit to n elements and return a list
	}
	
	
	//Part II:
	
	
	/**
	 * Grouping method implemented using Streams
	 * @return Map in which the keys are the different prices of the apps, and the 
	 * values are the sum of the number of reviews of the apps with said price.
	 */

	public Map<Double, Long> reviewsByPriceStream(){
		return apps.stream()
				.collect(Collectors.groupingBy(							//Group in a map	
						a -> a.getPrice(), 								//Select keys
						Collectors.summingLong(a-> a.getReviews())));	//Select values by summing the reviews
	}
		
	
	/**
	 * Grouping method using Streams
	 * @return Map where the keys are the categories and the values are sorted 
	 * 			sets of the target users
	 */
	public Map<Set<String>, SortedSet<User>> usersByCategory(){
		return apps.stream()
				.collect(Collectors.groupingBy(								//Group in a map
						a -> a.getCategory(), 								//Select keys
						Collectors.mapping(App :: getUser, 					//Values are the users
								Collectors.toCollection(TreeSet :: new)))); //in a sorted Set
	}
	
	
	/**
	 * Grouping method using streams
	 * @return Map where the keys are the restriction values and the values are 
	 * the App objects with the minimum number of reviews for each key
	 */
	public Map<Restrictions, App> minReviewsByRestriction(){
		return apps.stream()
				.collect(Collectors.groupingBy(			//Group in a map
						a -> a.getRestriction(),		//Select keys
						Collectors.collectingAndThen(	//Select values
								Collectors.minBy(										//get maximum
										Comparator.comparing(a -> a.getReviews())),		//Compare by reviews
									opt -> opt.orElse(null))));		//return null if there is no maximum
	}
	
	/**
	 * Grouping method using streams
	 * 
	 * @param n
	 * 		Limits the size of the lists in the values
	 * @return SortedMap using the natural order where the keys are Restriction values 
	 * and the values are lists with the n elements with the lowest size
	 */
	
	public SortedMap<Restrictions, List<App>> nMaxSizeByRestriction(Long  n){
		checkLimit(n);
		return apps.stream()
				.collect(Collectors.groupingBy(			
						a -> a.getRestriction(), 		
						TreeMap :: new, 				//Sorted map
						Collectors.collectingAndThen(	//Select values
								Collectors.toList(), 	//values are a list
								l -> l.stream().sorted(Comparator.comparing(	//Sort the list
										a -> ((App) a).getSize()).reversed())	//Comparator
								.limit(n).toList())));							//Limit the list
	}
	
	/**
	 * @return target user nationality with the highest associated average price
	 */
	
	public String userMaxAvPrice() {
		Map<String, Double> aux = apps.stream()
				.collect(Collectors.groupingBy(								
						a -> a.getUser().nationality(), 
						Collectors.averagingDouble(a -> a.getPrice())));		//Get average of price as value
		return aux.entrySet().stream()
				.max(Comparator.comparing(es -> es.getValue()))					//Get the maximum pair according to map values
				.orElse(null)													//Get null pair if can't compute max
				.getKey();														//Get key of max pair, nationality
	}
	
	
	//EXTRA FUNCTIONS:
	
	/**
	 * FOR ALL: Check if all the apps from a certain category and with a rating higher or 
	 * equal to the given one, have a restriction more or equally restrictive (>=) than a 
	 * given one. Implemented using for loops.
	 * @param res
	 * 		Restriction used to filter, App objects should have a restriction more or equally restrictive
	 * @param cat
	 * 		Category used to filter, App objects should contain this category
	 * @param rate
	 * 		Rating used to filter, App objects should have a rating equal or higher
	 * @return true if all apps from the category and rating specified have the correct restriction
	 * 		false if they do not.
	 */
	public Boolean checkRestrictionCategoryRating(Restrictions res, String cat, Double rate) {
		Boolean out = true;
		for (App a : apps) {
			if (a.getCategory().contains(cat) && a.getRating().compareTo(rate)>=0 
					&& !(a.getRestriction().compareTo(res)>=0)){
					out = false;
					break;
			}
		}
		return out;
	}
	
	/**
	 * FOR ALL: Check if all the apps from a certain category and with a rating higher or 
	 * equal to the given one, have a restriction more or equally restrictive (>=) than a 
	 * given one. Implemented using Streams.
	 * @param res
	 * 		Restriction used to filter, App objects should have a restriction more or equally restrictive
	 * @param cat
	 * 		Category used to filter, App objects should contain this category
	 * @param rate
	 * 		Rating used to filter, App objects should have a rating equal or higher
	 * @return true if all apps from the category and rating specified have the correct restriction
	 * 		false if they do not.
	 */
	public Boolean checkRestrictionCategoryRatingStream(Restrictions res, String cat, Double rate) {
		return apps.stream()
				.filter(a -> a.getCategory().contains(cat) && a.getRating().compareTo(rate)>=0)
				.allMatch(a -> a.getRestriction().compareTo(res)>=0);
	}
	
	/**
	 * COUNTER: Get the number of free apps in the apps property
	 * @return number of Apps with premium = false in the apps property
	 */
	public Integer countFreeApps() {
		Integer out = 0;
		for (App a : apps) {
			if (!a.getPremium()) {
				out++;
			}
		}
		return out;
	}
	
	/**
	 * Check that the limit id not negative
	 * @param n
	 * 		Limit that will be checked
	 * @throws IllegalArgumentException if the restriction is not satisfied
	 */
	private void checkLimit(Long n) {
		Checkers.check("Invalid number of elements", n>=0);
	} 
	
}

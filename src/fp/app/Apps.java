package fp.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Apps {
	
	private List<App> apps;
	
	//Constructor that creates a container with an empty list
	public Apps() {
		apps = new ArrayList<App>();
	}
	
	// Constructor that creates a container from a Collection<App> parameter
	public Apps(Collection<App> sa) {
		apps = new ArrayList<App>(sa);
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

	//Equality criteria
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
	
	//Get number of items
	
	public Integer getSize() {
		return apps.size();
	}
	
	//Add an item
	
	public void addApp(App a) {
		apps.add(a);
	}
	
	//Add a collection of items
	
	public void addApps(Collection<App> a) {
		apps.addAll(a);
	}
	
	//Delete an item
	
	public void removeApp(App a) {
		apps.remove(a);
	}

	
	//EXISTS: Check if there exits an with a certain category (parameter)
	public Boolean containsCategory(String cat) {
		Boolean out = false;
		for (App a : apps) {
			if (a.getCategory().contains(cat)){
				out = true;
				break;
			}
		}
		return out;
	}
	
	//AVERAGE: Get the average price of the apps in the data set
	//with a rating over a given value
	public Double averagePrice(Double rating) {
		Double out = 0.0;
		Integer count = 0;
		for (App a : apps) {
			if (a.getRating()>rating) {
				count ++;
				out += a.getPrice();
			}
		}
		return out/count;
	}
	
	//Selection with filtering: get a list of the apps that are recommended
	// as stated in the parameter or more
	public List<App> getRecommendedApps(Rec rec){
		List<App> out = new ArrayList<App>();
		for (App a : apps) {
			if (a.getRecommended().compareTo(rec)>=0) {
				out.add(a);
			}
		}
		return out;
	}
	
	//Grouping method that returns a Map in which the keys are the Restrictions, 
	//and the values a SortedSet of objects of type App sorted by the natural order.
	
	public Map<Restrictions,SortedSet<App>> groupRestrictions(){
		Map<Restrictions,SortedSet<App>> out = new HashMap<Restrictions,SortedSet<App>>();
		Restrictions k = null;
		for (App a : apps) {
			k = a.getRestriction();
			if (out.containsKey(k)) {
				out.get(k).add(a);
			}
			else {
				SortedSet<App> v = new TreeSet<App>();
				v.add(a);
				out.put(k, v);
			}
		}
		return out;
	}
	
	//Grouping method that returns a Map in which the keys are the different prices of the apps, 
	//and the values are the sum of the number of reviews of the apps with said price.

	public Map<Double, Long> reviewsByPrice(){
		Map<Double, Long> out = new HashMap<Double, Long>();
		for (App a : apps) {
			if(out.containsKey(a.getPrice())) {
				Long v = out.get(a.getPrice()) + a.getReviews();
				out.put(a.getPrice(), v);
			}
			else {
				out.put(a.getPrice(), Long.valueOf(a.getReviews()));
			}
		}
		return out;
	}
	
	//EXTRA FUNCTIONS:
	
	//FOR ALL: Check if all the apps from a certain category and with a rating bigger or 
	//equal to the given one, have a restriction more or equally restrictive (>=) than a 
	//given one (parameter) 
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
	
	//COUNTER: Get the number of free apps in the data set
	
	public Integer countFreeApps() {
		Integer out = 0;
		for (App a : apps) {
			if (!a.getPremium()) {
				out++;
			}
		}
		return out;
	}
	
}

package fp.app;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import fp.utils.Checkers;

public class App implements Comparable<App>{
	private String name; //Name of the app
	private Double rating; //Rating of the app in the play store (from 0 to 5)
	private Integer reviews; // Number of reviews for the app in the play store
	private Double size; //The memory space needed to install the app
	private Double price; //The price of the app
	private Restrictions restriction; //The age restriction on the app. Ex. Everybody, Mature...
	private Set<String> category; //Tags the app may have. Ex. finance, game..
	private LocalDate lastUpdate; //Date of the last update made to the app
	private User targetUser;
	
	//Constructors
	
	//All parameters
	public App(String n, Set<String> cat, Double rat, Integer rev, Double s, Double p, Restrictions res, LocalDate lU, User tU) {
		checkName(n);
		checkPrice(p);
		checkRating(rat);
		checkSize(s);
		checkReviews(rev);
		checkLastUpdate(lU);
		name = n;
		category = cat;
		rating = rat;
		reviews = rev;
		size = s;
		price = p;
		restriction = res;
		lastUpdate = lU;
		targetUser = tU;
	}
	
	//Only name, price and restriction. The rest are null
	public App(String n, Double p, Restrictions res) {
		checkName(n);
		checkPrice(p);
		name = n;
		category = Set.of();
		rating = 0.0;
		reviews = 0;
		size = 0.0;
		price = p;
		restriction = res;
		lastUpdate = null;
		targetUser = null;
	}
	
	
	//Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		checkName(name);
		this.name = name;
	}

	public Set<String> getCategory() {
		return new HashSet<String>(category);
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		checkRating(rating);
		this.rating = rating;
	}

	public Integer getReviews() {
		return reviews;
	}

	public void setReviews(Integer reviews) {
		checkReviews(reviews);
		this.reviews = reviews;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		checkSize(size);
		this.size = size;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		checkPrice(price);
		this.price = price;
	}

	public Restrictions getRestriction() {
		return restriction;
	}

	public void setRestriction(Restrictions restriction) {
		this.restriction = restriction;
	}

	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		checkLastUpdate(lastUpdate);
		this.lastUpdate = lastUpdate;
	}
	
	public User getUser() {
		return targetUser;
	}

	//Getter for the derived property Recommended:
	/* Recommended takes a value that can be LOW, MID, HIGH or VERY_HIGH
	 * It represents the level of recommendation of the app
	 * The more reviews and rating, the more recommended it is.
	 * The lowest the number of reviews and the rating, the least recommended.
	 * 
	 * VERY_HIGH: reviews should be more than 10000 and rating more than 4
	 * HIGH: rating should be over 4 independent of number of reviews
	 * MID: more than 100000 and rating over 3 or more than 1000 reviews
	 * and rating over 2.5
	 * LOW: any other result.
	 */
	public Rec getRecommended() {
		Rec rec = null;
		if(reviews!=null && rating!=null) {
			rec = Rec.LOW;
		}
		if(reviews > 100000 && rating>4) {
				rec=Rec.VERY_HIGH;
		}
		else if(rating>4){
				rec = Rec.HIGH;
		}
		else if(reviews>100000 && rating >=3 || reviews<=1000 && rating>=2.5) {
				rec=Rec.MID;
		}
		else {
				rec = Rec.LOW;
		}
		return rec;
	}

	/*Getter for the derived property premium:
	*premium determines if you have to pay for the app:
	* True: The app must be purchased
	* False: The app is free to use
	*/
	public Boolean getPremium() {
		return price!=0.0;
	}

	/*String representation, as: Name (category): rating stars for reviews reviews. It is a premium app: premium (price), 
	restriction. Recommended: recommended */
	public String toString() {
		return name + " (" + category + "): " + rating + " stars for " + reviews +
				" reviews. It is a premium app: " + getPremium() + " (" + price + "$), " + restriction + ". Updated: " 
				+ lastUpdate + ". Recommended: " + getRecommended();
	}

	
	//Equality criterium:
	//Two apps are equal if the names, the prices and the sizes are the same 
	
	@Override
	public int hashCode() {
		return Objects.hash(name, price, restriction);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		App other = (App) obj;
		return Objects.equals(price, other.price) && Objects.equals(name, other.name)
				&& Objects.equals(restriction, other.restriction);
	}


	
	//Natural order: they will be ordered by their names, then 
	//their price and finally their size
	
	public int compareTo(App a) {
		int out = getName().compareTo(a.getName());
		if (out==0){
			out = getPrice().compareTo(a.getPrice());
			if (out==0){
				out = getRestriction().compareTo(a.getRestriction());
			}
		}
		return out;
	}
	
	//Constraints:
	
	//The rating must be between 0 and 5, both included:
	private void checkRating(Double rt) {
		Checkers.check("Invalid rating", rt>=0 && rt<=5);
	}
	
	//The name can't be null:
	private void checkName(String n) {
		Checkers.check("Invalid name", n!=null);
	}
	
	//The price can't be negative
	private void checkPrice(Double p) {
		Checkers.check("Invalid price", p>=0);
	}
	
	//The size can't be negative
	private void checkSize(Double s) {
		Checkers.check("Invalid size", s>=0);
	}
	
	//The number of reviews can't be negative
	private void checkReviews(Integer i) {
		Checkers.check("Invalid number of reviews", i>=0);
	}
	
	//The last update can take a value after today
	private void checkLastUpdate(LocalDate lU) {
		Checkers.check("Invalid numnber of reviews", lU.isBefore(LocalDate.now()) && lU.isEqual(LocalDate.now()));
	}
	
	//Other operations:
	
	//Add an element to the category set:
	public void addCategory(String s) {
		category.add(s);
	}
	
}

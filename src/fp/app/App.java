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
	private User targetUser; // Target user of the app (who the app is aimed for)
	
	//Constructors
	
	/**
	 * Constructor containing all the parameters
	 * 
	 * @param n
	 * 		Name that will be assigned to the new app
	 * @param cat
	 * 		Category that will be assigned to the new app
	 * @param rat
	 * 		Rating that will be assigned to the new app
	 * @param rev
	 * 		Review number that will be assigned to the new app
	 * @param s
	 * 		Size value that will be assigned to the new app
	 * @param p
	 * 		Price that will be assigned to the new app
	 * @param res
	 * 		Restriction that will be assigned to the new app
	 * @param lU
	 * 		Last update date that will be assigned to the new app
	 * @param tU
	 * 		target user User that will be assigned to the new app
	 */
	public App(String n, Set<String> cat, Double rat, Integer rev, Double s, Double p, Restrictions res, LocalDate lU, User tU) {
		//Check restrictions
		checkName(n);
		checkPrice(p);
		checkRating(rat);
		checkSize(s);
		checkReviews(rev);
		checkLastUpdate(lU);
		
		//Assign values
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
	
	/**
	 * Constructor that only receives some fields for the App object. The rest are null.
	 * 
	 * @param n
	 * 		Name that will be assigned to the new app
	 * @param p
	 * 		price that will be assigned to the new app
	 * @param res
	 * 		Restriction that will be assigned to the new app
	 */
	
	public App(String n, Double p, Restrictions res) {
		//Check restrictions
		checkName(n);
		checkPrice(p);
		
		//Assign values
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
		checkName(name);					//Check restriction
		this.name = name;
	}

	public Set<String> getCategory() {
		return new HashSet<String>(category);
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		checkRating(rating);				//Check restriction
		this.rating = rating;
	}

	public Integer getReviews() {
		return reviews;
	}

	public void setReviews(Integer reviews) {
		checkReviews(reviews);				//Check restriction
		this.reviews = reviews;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		checkSize(size);					//Check restriction
		this.size = size;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		checkPrice(price);					//Check restriction
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
		checkLastUpdate(lastUpdate);		//Check restriction
		this.lastUpdate = lastUpdate;
	}
	
	public User getUser() {
		return targetUser;
	}

	//Getter for the derived property Recommended:
	
	/** Recommended takes a value that can be LOW, MID, HIGH or VERY_HIGH.
	 * It represents the level of recommendation of the app.
	 * The more reviews and rating, the more recommended it is.
	 * The lowest the number of reviews and the rating, the least recommended.
	 * 
	 * VERY_HIGH: reviews should be more than 10000 and rating more than 4.
	 * HIGH: rating should be over 4 independent of number of reviews.
	 * MID: more than 100000 and rating over 3 or more than 1000 reviews
	 * and rating over 2.5.
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

	/**Getter for the derived property premium.
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

	
	//Equality criteria:
	//Two App objects are equal if the names, the prices and the sizes are the same 
	
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
	
	/**Checks that the rating must be between 0 and 5, both included:
	 * 
	 * @param rt
	 * 		rating that will be evaluated
	 * @throws IllegalArgumentException if the restriction is not satisfied
	 */
	private void checkRating(Double rt) {
		Checkers.check("Invalid rating", rt>=0 && rt<=5);
	}
	
	/**
	 * Checks that the name is not null:
	 * @param n
	 * 		name that will be evaluated
	 * @throws IllegalArgumentException if the restriction is not satisfied
	 */
	private void checkName(String n) {
		Checkers.check("Invalid name", n!=null);
	}
	
	/**
	 * Checks that the price is not negative
	 * @param p
	 * 		price that will be evaluated
	 * @throws IllegalArgumentException if the restriction is not satisfied
	 */
	private void checkPrice(Double p) {
		Checkers.check("Invalid price", p>=0);
	}
	
	/**
	 * Checks that the size is not negative
	 * @param s
	 * 		size that will be evaluated
	 * @throws IllegalArgumentException if the restriction is not satisfied
	 */
	private void checkSize(Double s) {
		Checkers.check("Invalid size", s>=0);
	}
	
	
	/**
	 * Checks that the number of reviews is not negative
	 * @param i
	 * 		number of reviews that will be evaluated
	 * @throws IllegalArgumentException if the restriction is not satisfied
	 */
	private void checkReviews(Integer i) {
		Checkers.check("Invalid number of reviews", i>=0);
	}
	
	//The last update can take a value after today
	/**
	 * Checks that the last update is not a value after today
	 * @param lU
	 * 		date that will be evaluated
	 * @throws IllegalArgumentException if the restriction is not satisfied
	 */
	private void checkLastUpdate(LocalDate lU) {
		Checkers.check("Invalid date", lU == null || lU.isBefore(LocalDate.now()) || lU.isEqual(LocalDate.now()));
	}
	
	
	/** Add an element to the category set.
	 * 
	 * @param s
	 * 		String that will be added to the category set
	 */
	public void addCategory(String s) {
		category.add(s);
	}
	
}

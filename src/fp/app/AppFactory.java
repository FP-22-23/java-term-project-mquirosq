package fp.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utils.Checkers;

public class AppFactory {
	
	
	/**
	 * 
	 * @param line
	 * 		String containing the data to construct an App object
	 * @return 
	 * 		An App object created from the information in the line parameter
	 */
	
	private static App parseApp(String line) {
		String[] fields = line.split(";");										//Split line in data fields
		Checkers.check("Invalid line: " + line, fields.length == 12);			//Check line has needed fields
		
		//Process data and get final version
		String name = fields[0].trim();
		Double rating = parseRating(fields[1].trim());
		Integer reviews = Integer.valueOf(fields[2].trim().replace("+", "").replace(",", ""));
		Double size = parseSize(fields[3]);
		Double price = Double.valueOf(fields[4].trim().replace("$", ""));
		Restrictions restriction = Restrictions.valueOf(fields[5].trim().toUpperCase());
		Set<String> cat = parseCategory(fields[6], fields[7]);
		LocalDate lu = parseDate(fields[8].trim());
		User user = parseUser(fields[11].trim(), fields[9].trim(), fields[10].trim());
		
		//Return final object with a constructor
		return new App(name, cat, rating, reviews, size, price, restriction, lu, user);
	}
	
	private static Double parseRating(String rt) {
		Double out = 0.0;
		if (!rt.equals("NaN")) {			//NaN will get rating = 0.0
			out = Double.valueOf(rt);
		}
		return out;
	}
	
	private static Double parseSize(String s) {
		Double out = 0.0;
		if (!s.contains("Varies with device")) {	//Exception will get size = 0.0
			out = Double.valueOf(s.trim().replace("M", "").replace("k", ""));	//Replace conflicting values
		}
		return out;
	}
	
	private static Set<String> parseCategory(String cat1, String cat2){
		Set<String> cat = new HashSet<String>();
		cat1 = cat1.toLowerCase().replace("_", " ").replace("AND", "&");	//Replace conflicting values
		cat2 = cat2.toLowerCase();
		cat.add(cat1);
		cat.add(cat2);
		return cat;
				
	}
	
	private static LocalDate parseDate(String date) {
		LocalDate out = null;
		String form = null;
		if (!date.trim().equals("") && !date.contains("Varies with device")){ //No date ad "varies with device" get out = null
			//Select format
			if(date.split(" ")[1].trim().replace(",", "").length() == 2) {	//Check number of digits on day
				form = "MMMM dd, yyyy"; //Two digit days format
			}
			else {
				form = "MMMM d, yyyy";	//One digit days format
			}
			out = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern(form, Locale.ENGLISH));		//Parse the date
		}
		return out;
	}
	
	private static User parseUser(String gender, String age, String nationality) {
		//Prepare data
		Gender g = Gender.valueOf(gender.toUpperCase());
		Integer ag = Integer.valueOf(age);
		return new User(g, ag, nationality);		//Final constructor
	}
		
	/**
	 * 
	 * @param file
	 * 		String with the name and path to the data file
	 * @return
	 * 		An Apps object containing the data in the file
	 */
	public static Apps readApps(String file) {
		Apps out = null;
		try {
			Stream<App> sa = Files.lines(Paths.get(file)).skip(1).map(AppFactory :: parseApp);	//Skip header and parse each line
			out = new Apps(sa.collect(Collectors.toList()));									//Construct new object from a list
		}
		catch(IOException e) {									//Reading error
			System.out.println("Error in file " + file);
			e.printStackTrace();
		}
		return out;
	}
	
	/**
	 * 
	 * @param file
	 * 		String with the name and path to the data file
	 * @return
	 * 		An Apps object containing the data in the file
	 */
	
	public static Apps readAppsStream(String file) {
		Apps out = null;
		try {
			Stream<App> sa = Files.lines(Paths.get(file)).skip(1).map(AppFactory :: parseApp);	//Skip header and parse each line
			out = new Apps(sa);																	//Construct new object from a stream
		}
		catch(IOException e) {									//Reading error
			System.out.println("Error in file " + file);
			e.printStackTrace();
		}
		return out;
	}

}

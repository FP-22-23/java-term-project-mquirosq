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
	
	
	private static App parseApp(String line) {
		String[] fields = line.split(";");
		Checkers.check("Invalid line: " + line, fields.length == 12);
		String name = fields[0].trim();
		Double rating = parseRating(fields[1].trim());
		Integer reviews = Integer.valueOf(fields[2].trim().replace("+", "").replace(",", ""));
		Double size = parseSize(fields[3]);
		Double price = Double.valueOf(fields[4].trim().replace("$", ""));
		Restrictions restriction = Restrictions.valueOf(fields[5].trim().toUpperCase());
		Set<String> cat = parseCategory(fields[6], fields[7]);
		LocalDate lu = parseDate(fields[8].trim());
		User user = parseUser(fields[11].trim(), fields[9].trim(), fields[10].trim());
		return new App(name, cat, rating, reviews, size, price, restriction, lu, user);
	}
	
	private static Double parseRating(String rt) {
		Double out = 0.0;
		if (!rt.equals("NaN")) {
			out = Double.valueOf(rt);
		}
		return out;
	}
	
	private static Double parseSize(String s) {
		Double out = 0.0;
		if (!s.contains("Varies with device")) {
			out = Double.valueOf(s.trim().replace("M", "").replace("k", ""));
		}
		return out;
	}
	
	private static Set<String> parseCategory(String cat1, String cat2){
		Set<String> cat = new HashSet<String>();
		cat1 = cat1.toLowerCase().replace("_", " ").replace("AND", "&");
		cat2 = cat2.toLowerCase();
		cat.add(cat1);
		cat.add(cat2);
		return cat;
				
	}
	
	private static LocalDate parseDate(String date) {
		LocalDate out = null;
		String form = null;
		if (!date.trim().equals("") && !date.contains("Varies with device")){
			if(date.split(" ")[1].trim().replace(",", "").length() == 2) {
				form = "MMMM dd, yyyy";
			}
			else {
				form = "MMMM d, yyyy";
			}
			out = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern(form, Locale.ENGLISH));
		}
		return out;
	}
	
	private static User parseUser(String gender, String age, String nationality) {
		Gender g = Gender.valueOf(gender.toUpperCase());
		Integer ag = Integer.valueOf(age);
		return new User(g, ag, nationality);
	}
		
	public static Apps readApps(String file) {
		Apps out = null;
		try {
			Stream<App> sa = Files.lines(Paths.get(file)).skip(1).map(AppFactory :: parseApp);
			out = new Apps(sa.collect(Collectors.toList()));
		}
		catch(IOException e) {
			System.out.println("Error in file " + file);
			e.printStackTrace();
		}
		return out;
	}

}

package fp.app.test;

import fp.app.AppFactory;
import fp.app.Apps;

public class TestAppFactory {
	
	public static void main(String[] args) {
		testReadApps("data/GooglePlayAppsData1.csv");
	}
	
	private static void testReadApps(String file) {
		System.out.println("\nTestReadApps =============");
		Apps apps = AppFactory.readApps(file);
		System.out.println(apps.getSize() + " apps have been read.");
		System.out.print("The first 3 apps are: " + apps.getApps().subList(0, 3));
	}
	

}

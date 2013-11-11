package typewriter;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class NameGenerator {

	
	
	public static String getStreetName() {
		String name = "";
		try {
			name = testingNames.streetName();
		} catch (Exception e) {};
		return name;
	}


	public static String getActorName() {
		Scanner file = null;
		try {
			file = new Scanner(new File("notes/namesMale.txt"));
		} catch (Exception e) {e.printStackTrace();}

		List<String> maleNames = new ArrayList<String>();

		while(file.hasNextLine()) {
			maleNames.add(file.nextLine());
		}
		try {
			file = new Scanner(new File("notes/namesFemale.txt"));
		} catch (Exception e) {e.printStackTrace();}

		List<String> femaleNames = new ArrayList<String>();
		
		while(file.hasNextLine()) {
			femaleNames.add(file.nextLine());
		}
		/*
		try {
			file = new Scanner(new File("notes/lastNames.txt"));
		} catch (Exception e) {e.printStackTrace();}

		List<String> lastNames = new ArrayList<String>();

		while(file.hasNextLine()) {
			lastNames.add(file.nextLine());
		}
		*/

		Random rd = new Random();
		
		if(rd.nextBoolean()) {
			return maleNames.get(rd.nextInt(maleNames.size()));//+" "+lastNames.get(rd.nextInt(lastNames.size()));
		} else {
			return femaleNames.get(rd.nextInt(femaleNames.size()));//+" "+lastNames.get(rd.nextInt(lastNames.size()));
		}
	}


		public static String getBuildingName() {
			String name = "";
			try {
				name = testingNames.locationName();
			} catch (Exception e) {}
			return name;

		}
	}





  

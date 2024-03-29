import java.io.*;

public class WebRegApp{

	public static void main(String[] args){

		Course[] catalog;
		//Me is the current user
		Student me = new Student("Joe", "Smith", 123456789, 2020);
		//Sam is another existing user, created to test the "Find common course" option
		Student Sam = new Student("Sam", "Tso", 123456788, 2020);
		Course a = new Course(198, 112, "Data Structures", 'M', 5, 4);
		Course b = new Course(560, 101, "Elementary Italian", 'M', 2, 4);
		Course cc = new Course(400, 105, "Facets of Food Science", 'H', 1, 1);
		Course d = new Course(198, 111, "Introduction to Computer Science", 'T', 7, 4);
		Course e = new Course(220, 301, "Money and Banking", 'F', 5, 3);
		Course f = new Course(965, 300, "New York Theater Experience", 'W', 6, 3);
		Course g = new Course(965, 315, "Playwriting",'W', 4, 3);
		Course h = new Course(776, 310, "Plant Propagation", 'H', 2, 3);
		Course j = new Course(560, 232, "Italian Culture", 'M', 5, 3);
		WebReg.addCourse(Sam, a);
		/*
		WebReg.addCourse(Sam, b);
		WebReg.addCourse(Sam, cc);
		WebReg.addCourse(Sam, d);
		WebReg.addCourse(Sam, e);
		WebReg.addCourse(Sam, f);
		WebReg.addCourse(me, b);
		WebReg.addCourse(me, cc);
		WebReg.addCourse(me, g);
		WebReg.addCourse(me, h);
		WebReg.addCourse(me, j);
		WebReg.addCourse(me, f);*/
		Course[] results;
		Course course;
		String catalogFilename, courseName;
		int dept, numCredits;
		boolean success;

		catalogFilename = "WebReg-master/catalog.txt";
		catalog = readFile(catalogFilename);
		if (catalog == null){
			System.err.println("Error reading " + catalogFilename + ":");
			return;
		}

		while (true){

			int choice = getMenuChoice();

			if (choice == 1){

				System.out.println("Enter name of course:");
				courseName = IO.readString();
				System.out.println();

				course = WebReg.lookupCourseByName(catalog, courseName);
				if (course != null){
					System.out.println(course);
				}
				else{
					System.out.println("No course found by that name.");
				}
			}
			else if (choice == 2){

				System.out.println("Enter number of department:");
				dept = IO.readInt();
				System.out.println();

				results = WebReg.lookupCoursesByDept(catalog, dept);
				if (results != null){
					printArray(results);
				}
				else{
					System.out.println("No courses found in that department.");
				}
			}
			else if (choice == 3){

				WebReg.sortByNumber(catalog);
				printArray(catalog);
			}
			else if (choice == 4){

				WebReg.sortByTime(catalog);
				printArray(catalog);
			}
			else if (choice == 5){

				System.out.println("Current schedule:");
				printArray(me.getSchedule());
			}
			else if (choice == 6){

				numCredits = WebReg.countCredits(me);
				System.out.println("Credits being taken: " + numCredits);
			}
			else if (choice == 7){

				System.out.println("Enter course name:");
				courseName = IO.readString();
				Course c = WebReg.lookupCourseByName(catalog, courseName);
				System.out.println();

				if(c != null){

					success = WebReg.addCourse(me, c);
					if (success){
						System.out.println("Successfully registered.");
					}
					else{
						System.out.println("Attempt to register failed.");
					}
				}
				else{
					System.out.println("No course found by that name.");
				}
			}
			else if (choice == 8){

				System.out.println("Enter course name:");
				courseName = IO.readString();
				Course c = WebReg.lookupCourseByName(catalog, courseName);
				System.out.println();

				if(c != null){

					success = WebReg.dropCourse(me, c);
					if (success){
						System.out.println("Successfully withdrawn.");
					}
					else{
						System.out.println("Attempt to withdraw failed.");
					}
				}
				else{
					System.out.println("No course found by that name.");
				}
			}
			else if (choice == 9){
				
				System.out.println("Enter course name:");
				courseName = IO.readString();
				Course c = WebReg.lookupCourseByName(catalog, courseName);
				System.out.println();

				if(c != null){
					printArray(c.getRoster());
				}
				else{
					System.out.println("No course found by that name.");
				}
			}
			
			else if(choice ==10){
				printArray(WebReg.commonCourses(me, Sam));
			}
		}
	}

	private static final String[] menuChoices = {
			"Look up course by name",
			"Look up courses by department",
			"View catalog, sorted by department/course number",
			"View catalog, sorted by scheduled day/period",
			"View my course schedule",
			"View my current credit load",
			"Register for a course",
			"Withdraw from a course",
			"View a courses' roster",
			"Find common course"
	};

	private static int getMenuChoice(){

		int numChoices = menuChoices.length+1;

		System.out.println();
		System.out.println("Menu:");

		for (int i = 0 ; i < menuChoices.length ; i++){
			System.out.println((i+1) + ". " + menuChoices[i]);
		}
		System.out.println(numChoices + ". Quit");

		System.out.println();
		System.out.println("Choice (1-" + numChoices + ")?");
		int choice = IO.readInt();
		while (choice < 1 || choice > numChoices){

			System.out.println();
			System.out.print("That is not a valid menu option. Pick 1-" + numChoices + ". ");
			choice = IO.readInt();
		}

		System.out.println();
		return choice;
	}

	private static Course[] readFile(String filename){

		BufferedReader file;
		int numEntries;
		Course[] entries;

		try{

			file = new BufferedReader(new FileReader(filename));
			numEntries = parseFile(file, false, null);
			entries = new Course[numEntries];
			file.close();
			file = new BufferedReader(new FileReader(filename));
			parseFile(file, true, entries);
			file.close();
			return entries;
		}
		catch (IOException e){
			e.printStackTrace();
			return null;
		}
		catch (IllegalArgumentException e){
			e.printStackTrace();
			return null;
		}
	}

	private static int parseFile(BufferedReader file, boolean readEntries, Course[] catalog) throws IOException{

		int numEntries;
		String line;

		numEntries = 0;
		while((line = file.readLine()) != null){

			line = line.trim();
			if(line.equals("")){
				continue;
			}

			if(readEntries){
				catalog[numEntries] = parseCourseLine(line);
			}
			numEntries++;
		}

		return numEntries;
	}

	private static Course parseCourseLine(String line){

		String remaining, part;
		int index;
		int dept, courseNum, timeSlot, credits;
		String name;
		char day;

		remaining = line.trim();

		// read dept
		index = remaining.indexOf(':');
		if (index == -1){
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		part = remaining.substring(0, index);
		try{

			dept = Integer.parseInt(part);
			if (dept < 0  ||  dept > 999){
				throw new IllegalArgumentException("invalid department number on line: " + line);
			}
		}
		catch (NumberFormatException e){
			throw new IllegalArgumentException("missing department number on line: " + line);
		}
		remaining = remaining.substring(index + 1);

		// read number
		index = remaining.indexOf(' ');
		if (index == -1){
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		part = remaining.substring(0, index);
		try{

			courseNum = Integer.parseInt(part);
			if (courseNum < 0  ||  courseNum > 999){
				throw new IllegalArgumentException("invalid course number on line: " + line);
			}
		}
		catch (NumberFormatException e){
			throw new IllegalArgumentException("missing course number on line: " + line);
		}
		remaining = remaining.substring(index + 1);

		// read name
		index = remaining.indexOf('[');
		if (index == -1){
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		remaining = remaining.substring(index + 1);
		index = remaining.indexOf(']');
		if (index == -1){
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		name = remaining.substring(0, index);
		remaining = remaining.substring(index + 1);

		// read day
		remaining = remaining.trim();
		if (remaining.length() < 2){
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		day = Character.toUpperCase(remaining.charAt(0));
		if (day != 'M'  &&  day != 'T'  &&  day != 'W'  &&  day != 'H'  &&  day != 'F'  &&  day != 'S'){
			throw new IllegalArgumentException("invalid day on line: " + line);
		}
		try{

			timeSlot = Integer.parseInt(remaining.substring(1,2));
			if (timeSlot < 1  ||  timeSlot > 9){
				throw new IllegalArgumentException("invalid class time slot on line: " + line);
			}
		}
		catch (NumberFormatException e){
			throw new IllegalArgumentException("missing class time slot on line: " + line);
		}
		remaining = remaining.substring(2);

		// read credits
		remaining = remaining.trim();
		if(!remaining.startsWith("credits:")){
			throw new IllegalArgumentException("badly formatted line in catalog file: " + line);
		}
		remaining = remaining.substring("credits:".length());
		try{

			credits = Integer.parseInt(remaining);
			if (credits < 1){
				throw new IllegalArgumentException("invalid number of credits on line: " + line);
			}
		}
		catch (NumberFormatException e){
			throw new IllegalArgumentException("missing or invalid number of credits on line: " + line);
		}

		Course c = new Course(dept, courseNum, name, day, timeSlot, credits);
		return c;
	}

	private static void printArray(Object[] array){
		if(array==null) {
			return;
		}
		for (int i = 0 ; i < array.length ; i++){
			if (array[i] == null){
				System.out.println("--Empty Space--");
			}
			else{
				System.out.println(array[i]);
			}
		}
	}
}


public class WebReg {
	public static Course lookupCourseByName(Course[] catalog, String courseName){
		for(int i = 0; i<=catalog.length-1; i++){
			if((catalog[i].getName()).toLowerCase().equals(courseName.toLowerCase())){
				return catalog[i];	
				}
			
		}
		return null;
		
	}
	
	public static Course[] lookupCoursesByDept(Course[] catalog, int deparment){
		for(int o = 0; o<= catalog.length-1; o++){
			if(catalog[o].getDepartment()== deparment){
				return removeItems(catalog, deparment);}
	}
		return null;}
	
	
	public static int countCredits(Student s){
		int total_credits = 0;
		for(int i = 0; i<=s.getSchedule().length-1; i++){
			if(s.getSchedule()[i]==null){
				total_credits+=0;
			}
			else{total_credits += s.getSchedule()[i].getCredits();}
		}
		return total_credits;
	}
	
	public static boolean addCourse(Student s, Course c){
		for(int i = 0; i<=s.getSchedule().length-1; i++){
			if(s.getSchedule()[i]==null){
				s.getSchedule()[i]= c;
				for(int g = 0; g<=c.getRoster().length-1; g++){
					if(c.getRoster()[g]==null){
						c.getRoster()[g]=s;
						break;
					}
				}
				return true;
			}
			else if((c.getPeriod()).compareTo(s.getSchedule()[i].getPeriod())==0){
				return false;
			}
			
		}
		
	return false;
			
	}
	
	public static boolean dropCourse(Student s, Course c){
		for(int i=0; i<= s.getSchedule().length-1; i++){
			
			if(s.getSchedule()[i]==null&&!s.getSchedule().equals(c)){
					return false;
				}
			
			else{
				
				if(s.getSchedule()[i].equals(c)&&s.getSchedule()[i]!=null){
					for(int j = i; j<= s.getSchedule().length-2;j++){
						s.getSchedule()[j]=s.getSchedule()[j+1];
						s.getSchedule()[s.getSchedule().length-1]=null;
						
					}
					s.getSchedule()[s.getSchedule().length-1]=null;
					

					for(int g=0; g<= c.getRoster().length-1; g++){
						if(c.getRoster()[g].equals(s)&&c.getRoster()[g]!=null){
							for(int k = g; k<= c.getRoster().length-2;k++){
								c.getRoster()[k]=c.getRoster()[k+1];
								c.getRoster()[c.getRoster().length-1]=null;
								
							}
							c.getRoster()[c.getRoster().length-1]=null;
							return true;
						}
					}
				}
			}
			}
			
			
				
			
	
		return false;
	}
		

	

	
	public static Course[] removeItems(Course[] a, int b){
		int counter =0;
		int start = 1;
		for(int i = 0; i<=a.length-1; i++){
			if(a[i].getDepartment()==b){
				counter++;
			}
		}
		Course[] result = new Course[counter];
		while(start <=counter){
			for(int g =0; g<=a.length-1; g++){
				if(a[g].getDepartment()==b){
					result[start-1]=a[g];
					start++;
				}
			}
		}
		
		return result;
	}  
	
	public static Course[] removeItems2(Student s, Course c){
		int counter =0;
		int start = 1;
		for(int i = 0; i<=s.getSchedule().length-1; i++){
			if(!s.getSchedule()[i].equals(c)){
				counter++;
			}
		}
		Course[] result = new Course[counter];
		while(start <=counter){
			for(int g =0; g<=s.getSchedule().length-1; g++){
				if(!s.getSchedule()[g].equals(c)){
					result[start-1]=s.getSchedule()[g];
					start++;
				}
			}
		}
			return result;
	}  
	
	public static Course[] selectionSort(Course[] arr) {
		for (int n=arr.length; n > 1; n--) {  // n is effective array size
			// find max from 0..n-1 and switch with n-1
			int maxIndex=0;  // set first item to be max
			for (int j=1; j < n; j++) { 
				if ((arr[j].getDepartment() > arr[maxIndex].getDepartment())) {
					maxIndex = j;
				}
			
				
			}
			// switch arr[maxIndex] with arr[n-1]
			Course temp = arr[n-1];
			arr[n-1] = arr[maxIndex];
			arr[maxIndex] = temp;
		}
		
		return arr;
		
		
		
	}
	
	public static Course[] selectionSort2(Course[] arr) {
		for (int n=arr.length; n > 1; n--) {  // n is effective array size
			// find max from 0..n-1 and switch with n-1
			int maxIndex=0;  // set first item to be max
			for (int j=1; j < n; j++) { 
				if (arr[j].getPeriod().compareTo(arr[maxIndex].getPeriod())==1) {
					maxIndex = j;
				}
			}
			// switch arr[maxIndex] with arr[n-1]
			Course temp = arr[n-1];
			arr[n-1] = arr[maxIndex];
			arr[maxIndex] = temp;
		}
		
		return arr;
		
		
		
	}
	
	public static Course[] selectionSort3(Course[] arr) {
		
		for (int n=arr.length; n > 1; n--) {  // n is effective array size
			// find max from 0..n-1 and switch with n-1
			int maxIndex=0;  // set first item to be max
			for (int j=1; j < n; j++) { 
				if ((arr[j].getDepartment() > arr[maxIndex].getDepartment())) {
					maxIndex = j;
				}
				
			
				
			}
			// switch arr[maxIndex] with arr[n-1]
			Course temp = arr[n-1];
			arr[n-1] = arr[maxIndex];
			arr[maxIndex] = temp;
		}
		for (int n=arr.length; n > 0; n--) {  // n is effective array size
			// find max from 0..n-1 and switch with n-1
			int maxIndex=0;  // set first item to be max
			for (int j=0; j < n; j++) { 
				if ((arr[j].getDepartment() == arr[maxIndex].getDepartment())&&(arr[j].getCourseNumber() > arr[maxIndex].getCourseNumber())) {
					maxIndex = j;
					continue;
				}
				if ((arr[j].getDepartment() != arr[maxIndex].getDepartment())&&(arr[j].getCourseNumber() != arr[maxIndex].getCourseNumber())) {
					maxIndex = j;
				}
			}
			// switch arr[maxIndex] with arr[n-1]
			Course temp = arr[n-1];
			arr[n-1] = arr[maxIndex];
			arr[maxIndex] = temp;
		}
		
		return arr;
		
	}
	
	
	public static int binarySearch(Course[] list, Course target) {
		int lo=0, hi=list.length-1;
		
		while (lo <= hi) {
			int mid = (lo+hi)/2;   // midpoint of lo..hi
			if (target.getDepartment() == list[mid].getDepartment()&&target.getCourseNumber()==list[mid].getCourseNumber()) {
				return 1;
			}
			if ((target.getCourseNumber() < list[mid].getCourseNumber())&&(target.getDepartment()==list[mid].getDepartment())||(target.getDepartment()<list[mid].getDepartment())) {
				hi = mid-1;
			} else {
				lo = mid+1;
			}
		}
		return -1;
	}
	
	public static Course[] commonCourses(Student one, Student two){
		int counter = 0;
		int start = 0;
		int start2 = 0;
		int start3 = 0;
		int counter2 = 0;
		int counter3 =0;
		for(int t = 0; t<=one.getSchedule().length-1;t++){
			if(one.getSchedule()[t]!=null){
				counter2++;
			}
		}
		Course[] dum = new Course[counter2];
		for(int pp = 0; pp<=two.getSchedule().length-1;pp++){
			if(two.getSchedule()[pp]!=null){
				counter3++;
			}
		}
		Course[] dum2 = new Course[counter3];
		
		for(int gg = 0; gg<=one.getSchedule().length-1; gg++){
			if(one.getSchedule()[gg]!=null){
				dum[start2]=one.getSchedule()[gg];
				start2++;
			}
		}
		
		for(int kk = 0; kk<=two.getSchedule().length-1; kk++){
			if(two.getSchedule()[kk]!=null){
				dum2[start3]=two.getSchedule()[kk];
				start3++;
			}
		}
		
		
		
		sortByNumber(dum);
		sortByNumber(dum2);
		for(int o = 0; o<= dum.length-1; o++){
			for(int g =0; g<= dum2.length-1; g++){
				if(dum[o]== dum2[g]){
					counter++;
				}
			}
			
	}
		if(counter==0) {
			System.out.println("You have no courses in common");
			return null;
		}
			
	
		Course[] result = new Course[counter];
		for(int i = 0; i<=dum.length-1; i++){
			if(binarySearch(dum2, dum[i])==1){
				result[start]=dum[i];
				start++;
				}
			
		}
		if(counter!=0){
			return result;
		}
		return null;
		
	}
	
	
	
	public static void sortByNumber(Course[] catalog){
		
		selectionSort(catalog);
		for(int i=0; i<=catalog.length-2; i++){
			if(catalog[i].getDepartment()==catalog[i+1].getDepartment()){
					selectionSort3(catalog);}
		}
}
	
	public static void sortByTime(Course[] catalog){
		selectionSort2(catalog);
	}
}

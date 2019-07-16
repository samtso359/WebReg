
public class Student {
	private String firstName;
	private String lastName;
	private int id;
	private int gradYear;
	private Course[] courses;
	
	public Student(String firstName, String lastName, int id, int gradYear){
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.gradYear = gradYear;
		Course[] courses = new Course[6];
		this.courses = courses;
		
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public int getId(){
		return id;
	}
	
	public int getGradYear(){
		return gradYear;
	}
	
	public Course[] getSchedule(){
		return courses;
	}
	
	public String toString(){
		return id+": "+lastName+", "+firstName+" - "+gradYear;
	}
	
	public boolean equals(Student other){
		if(this.id == other.id){
			return true;
		}
		else{return false;}
	}
}

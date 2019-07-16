
public class Course {
	private int department;
	private int courseNum;
	private String name;
	private char day;
	private int timeSlot;
	private int credits;
	private Period period;
	private Student[] roster;
	
	public Course(int department, int courseNum, String name, char day, int timeSlot, int credits){
			this.department = department;
			this.courseNum = courseNum;
			this.name = name;
			this.day = day;
			this.timeSlot = timeSlot;
			this.credits = credits;
			this.period = new Period(day, timeSlot);
			this.roster = new Student[20];
		}
	
	public int getDepartment(){
		return department;
	}
	
	public int getCourseNumber(){
		return courseNum;
	}
	
	public String getName(){
		return name;
	}
	
	public Period getPeriod(){
		return period;
	}
	
	public int getCredits(){
		return credits;
	}
	
	public Student[] getRoster(){
		return roster;
	}

	public String toString(){
		return department+":"+courseNum+" ["+name+"] "+period.getDay()+period.getTimeSlot()+" credits:"+credits;
	}
	
	public boolean equals(Course other){
		if(this.department==other.department && this.courseNum==other.courseNum){
			return true;
		}
		else{
			return false;
		}
	}
}

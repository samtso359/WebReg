
public class Period {
	private char day;
	private int timeSlot;
	
	public int dayToInt(){
		if(day == 'M'){
			return 1;
		}
		else if(day == 'T'){
			return 2;
		}
		else if(day == 'W'){
			return 3;
		}
		else if(day == 'H'){
			return 4;
		}
		else if(day == 'F'){
			return 5;
		}
		else if(day == 'S'){
			return 6;
		}
		else{return 0;}
	}
	
	public Period(char day, int timeSlot){
		this.day = day;
		this.timeSlot = timeSlot;
	}
	
	public char getDay(){
		return day;
	}
	
	public int getTimeSlot(){
		return timeSlot;
	}
	
	public String toString(){
		return ""+this.day+this.timeSlot;
	}
	
	public int compareTo(Period other){
		if(this.day==other.day && this.timeSlot == other.timeSlot){
			return 0;
		}
		else if(this.day!=other.day ||this.timeSlot!=other.timeSlot){
			if(dayToInt() < other.dayToInt()){
				return -1;
			}
			else if(dayToInt() > other.dayToInt()){
				return 1;
			}

			else if(this.day==other.day && this.timeSlot < other.timeSlot){
				return -1;
			}
			
			else if(this.day==other.day && this.timeSlot > other.timeSlot){
				return 1;
			}
			
			
		}
		return -2;
	}
}

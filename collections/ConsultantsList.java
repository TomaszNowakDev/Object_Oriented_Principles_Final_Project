package collections;

import java.io.Serializable;
import java.util.ArrayList;

import classes.Consultant;

public class ConsultantsList implements Serializable{
	
	
	private ArrayList<Consultant> people;

	public ConsultantsList() {
		people = new ArrayList<Consultant>();
	}

	public void addConsultant(Consultant c) {
		this.people.add(c);
	}

	public ArrayList<Consultant> getConsultantsList() {
		return this.people;
	}
}


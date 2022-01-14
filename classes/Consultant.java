package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Consultant extends Person implements Serializable {
	private ArrayList<Patient> patients;
	private String expertise;

	public Consultant(Name name, String id, String phone, ArrayList<Patient> patients, String expertise) {
		super(name, id, phone);
		this.patients = new ArrayList<Patient>();
		this.expertise = expertise;
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public void add(Patient p) {
		this.patients.add(p);
	}

	public void addVisit(Patient p, Visit v) {
		p.add(v);
	}

	public void showPatients() {
		for (int i = 0; i < patients.size(); i++) {
			System.out.println(i + 1 + ". " + patients.get(i).getName().getLastName() + ", "
					+ patients.get(i).getName().getFirstName());
		}
	}

	public void showPatientsAndVisits() {
		for (int i = 0; i < patients.size(); i++) {
			System.out.println(i + 1 + ". " + patients.get(i).getName().getLastName() + ", "
					+ patients.get(i).getName().getFirstName()+ " Visits: "+ patients.get(i).getVisits());
		}
	}
	
	public String toString() {
		String str = super.getName().getLastName() + ", " + super.getName().getFirstName()+ " Expertise: " + this.expertise
				+ ", Patients:" + this.patients;
		return str;
	}
}

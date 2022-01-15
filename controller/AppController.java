package controller;

import classes.Consultant;
import interfaces.Storage;
import collections.ConsultantsList;
import storage.SerialStorage;

public class AppController {
	private ConsultantsList prac;
	private Storage store;

	public AppController() {
		prac = new ConsultantsList();
		store = new SerialStorage();
	}

	public void save() {
		store.writeData(prac);
	}

	public ConsultantsList read() {
		if ((ConsultantsList) store.readData() == null) {
			return null;
		} else {
			return (ConsultantsList) store.readData();
		}
	}

	public void addConsultant(Consultant p) {
		this.prac.addConsultant(p);
	}
}

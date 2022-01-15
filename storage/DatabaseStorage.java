package storage;

import interfaces.Storage;
import collections.ConsultantsList;

public class DatabaseStorage implements Storage{

	@Override
	public boolean writeData(Object o) {
		ConsultantsList p = (ConsultantsList)o;
		System.out.println("DB code to be written for writing data");
		return false;
	}

	@Override
	public Object readData() {
		System.out.println("DB code to be written for reading data");
		return null;
	}

}

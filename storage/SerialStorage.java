package storage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import interfaces.Storage;

public class SerialStorage implements Storage {
	private final String fileName = "serialConsultant.ser";

	public boolean writeData(Object o) {
		boolean result = true;
		try {
			// Saving of object in a file
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);
			// Method for serialization of object
			out.writeObject(o);
			out.close();
			file.close();
			System.out.println("Object has been serialized");
		}
		catch (IOException ex) {
			System.out.println("IOException is caught");
			result = false;
		}
		return result;
	}

	@Override
	public Object readData() {
		Object outObj = null;
		try {
			// Reading the object from a file
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);
			// Method for deserialization of object
			outObj = in.readObject();
			in.close();
			file.close();
			System.out.println("Object has been deserialized ");
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		}
		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
		return outObj;
	}
}
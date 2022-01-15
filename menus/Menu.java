package menus;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Consultant;
import classes.Name;
import classes.Patient;
import classes.Practice;
import classes.SeverityOptions;
import classes.Visit;
import collections.ConsultantsList;
import controller.AppController;

public class Menu {
	private Scanner scanner;
	private int menuOption;
	private LocalDate now = LocalDate.now();
	private Practice practice;
	
	public Menu() {
		scanner = new Scanner(System.in);
		practice = new Practice();
		setupData();
		showMainMenu();
	}

	private void loadData() {
		AppController app = new AppController();
		ConsultantsList x = app.read();
		if (x != null) {
			practice.setConsultantList(x.getConsultantsList());
			System.out.println("Data loaded!");
		}
		
	}
	private void setupData() {
		// testing and setup if file with data is missing random names
			Consultant c1 = new Consultant(new Name("John", "Kowalski"), "1", "0700234123", null, "Cardiac Surgeon");
			Consultant c2 = new Consultant(new Name("Eddie", "Van Halen"), "2", "070234433", null, "Neurosurgeon");
			Consultant c3 = new Consultant(new Name("Rory", "Gallgher"), "3", "0702345623", null, "Orthopaedist");

			Patient p1 = new Patient(new Name("John", "Doe"), "12", "086234238", null, "Cancer", SeverityOptions.LOW);
			Patient p2 = new Patient(new Name("Kiki", "Williams"), "13", "0853243328", null, "Broken arm", SeverityOptions.MEDIUM);
			Patient p3 = new Patient(new Name("Iga", "Swiatek"), "14", "086111238", null, "twisted ankle", SeverityOptions.LOW);
			Patient p4 = new Patient(new Name("Michael", "Jordan"), "15", "083333328", null, "Headache", SeverityOptions.MEDIUM);
			c1.add(p1);
			c3.add(p2);
			c3.add(p3);
			c2.add(p4);
			p1.add(new Visit(now, "Still sick"));
			p1.add(new Visit(now, "Getting better"));
			p2.add(new Visit(now, "Yes it is broken"));
			p3.add(new Visit(now,"Bandaging the leg"));
			p4.add(new Visit(now,"Head tomography"));

			practice.add(c1);
			practice.add(c2);
			practice.add(c3);
			
		}
		
	private void showMainMenu() {
	// implementation of menu
		try {
			do {
				System.out.println("\n\t\tHospital Consultancy Options\n1. Record a Patient visit.");
				System.out.println("2. Display all patients of a particular Consultant.");
				System.out.println("3. Display all Consultants followed by their Patients followed by their visits.");
				System.out.println("4. Load information from a text file.");
				System.out.println("5. Quit.");
				System.out.println("Select option from 1 to 5");
				menuOption = scanner.nextInt();
				scanner.nextLine();
			} while (menuOption < 1 || menuOption > 5);
			MainMenuOption option = MainMenuOption.values()[menuOption - 1];
			switch (option) {
			case RecordVisit:
				System.out.println("Record a Patient visit.");
				System.out.println("Who is a patient consultant?");
				Consultant c = practice.find();
				System.out.println("Select a patient from the list");
				c.showPatients();
				int patientChoice = 0;
				boolean inputValidationP = false;
				while (!inputValidationP) {
					try {
						patientChoice = scanner.nextInt();
						if (patientChoice < 1 || patientChoice > c.getPatients().size()) {
							throw new InputMismatchException();
						}
						inputValidationP = true;
					} catch (InputMismatchException ex) {
						System.out.println("Please enter correct number of patient.");
					}
					scanner.nextLine();
				}
				Patient p = c.getPatients().get(patientChoice - 1);
				System.out.println("Add Notes to the visit: ");
				String notes = scanner.nextLine();
				c.addVisit(p, new Visit(now, notes));

				break;
			case DisplayAllPatientsofConsultant:
				System.out.println("Display all patients of a particular Consultant.");
				System.out.println("Choose a consultant");
				Consultant op2 = practice.find();
				op2.showPatients();
				break;
			case DisplayAllCon:
				System.out.println("Display all Consultants followed by their Patients followed by their visits.");
				practice.showDetails();
				break;
			case LoadInfo:
				System.out.println("Load information from a file");
				loadData();
				break;
			case Quit:
				System.out.println("Thank you, have a nice day");
				practice.saveConsultants();
				System.exit(0);
			}

			showMainMenu();
		} catch (InputMismatchException ex) {
			scanner.nextLine();
			System.out.println("Option is not valid!");
			showMainMenu();

		}
	}
}
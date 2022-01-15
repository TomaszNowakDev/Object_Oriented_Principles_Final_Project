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

				break;
			case Quit:
				System.out.println("Thank you, have a nice day");
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
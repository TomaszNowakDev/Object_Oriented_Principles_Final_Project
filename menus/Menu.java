package menus;

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

				break;
			case DisplayAllPatientsofConsultant:
				System.out.println("Display all patients of a particular Consultant.");

				break;
			case DisplayAllCon:
				System.out.println("Display all Consultants followed by their Patients followed by their visits.");

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
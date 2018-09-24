import java.io.IOException;
import java.util.Scanner;

public class choiceMenu {
	final int EXIT = 0;

	choiceMenu() throws IOException, ClassNotFoundException {
		PersonDatabase2 db = new PersonDatabase2();
		grades gr = new grades();

		Scanner input = new Scanner(System.in);
		int choice = 999;

		while (choice != 0) {
			System.out.println("Co chcesz zrobiæ ?  ");
			System.out.println("1) dodaj ludzi do bazy	");
			System.out.println("2) wyœwietl wszystkie rekordy	");
			System.out.println("3) wyœwietl jeden wybrany rekord	");
			System.out.println("4) usuwa rekord o podanym nazwisku");
			System.out.println("5) dodaje ocenę");
			System.out.println("6) zapisz liste do pliku");
			System.out.println("7) wgraj listę z pliku");
			System.out.println("8) usuń ocenę ");
			System.out.println("0) exit");

			choice = input.nextInt();
			switch (choice) {
			case 1:
				db.create();
				break;
			case 2:
				db.getAll();
				break;
			case 3:
				db.get1();
				break;
			case 4:
				db.remove();
				break;
			case 5:
				gr.addGrade();
				// db.addGrade(db.get1());
				break;
			case 6:
				db.saveToFile();
				break;
			case 7:
				db.loadFromFile();
				break;
			case 8:
				gr.removeGrade();
				break;
			}
		}
	}
}

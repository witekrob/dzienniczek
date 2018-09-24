import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PersonDatabase2 implements Readable {
	public static int a = 0;

	public static List<Person2> nowa;
	public static Scanner input = new Scanner(System.in);

	public PersonDatabase2() {
		nowa = new LinkedList<Person2>();

	}

	public boolean compare(Person2 p1, Person2 p2) {
		for (Person2 p3 : nowa) {
			p2 = p3;

			if (p1.getPesel().equals(p2.getPesel())) {
				System.out.println("już taki jest");
				return true;
			}
		}

		return false;
	}

	public void add(Person2 ppp) {

		Iterator iter = nowa.iterator();

		try {

			Person2 iterPers = (Person2) iter.next();

			if (!nowa.isEmpty()) {

				if (!compare(ppp, iterPers)) {
					nowa.add(a, ppp);
					a++;
				}

			}
		} catch (NoSuchElementException e) {
			System.out.println("nie ma nic w bazie do porównania");
			nowa.add(a, ppp);
			a++;
		}

	}

	public void create() {

		Person2 p = new Person2();
		List<Integer> grades = new LinkedList<Integer>();

		System.out.println("podaj imiê : ");
		String name = input.nextLine();
		p.setName(name);

		System.out.println("podaj nazwisko : ");
		String surname = input.nextLine();
		p.setSurname(surname);

		System.out.println("podaj pesel : ");
		String pesel = input.nextLine();
		p.setPesel(pesel);
		p.setOcenki(grades);

		add(p);
	}

	public void getAll() {
		Iterator<Person2> iter = nowa.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	public Person2 get1() {

		int choice = 99;
		int byNameAndSurname = 1;
		int byPesel = 2;
		int exit = 0;
		Person2 looked = null;
		Iterator iter = nowa.iterator();

		while (choice != 0) {
			System.out.println("W jaki sposób szukać?");
			System.out.println("1) wpisz imię i nazwisko");
			System.out.println("2) wpisz pesel studenta");
			System.out.println("0) przejdz poziom wyżej");
			choice = input.nextInt();
			switch (choice) {
			case 1:

				System.out.println("Podaj imiê osoby do wyœwietlenia");
				String name = input.next();
				System.out.println("Podaj nazwisko osoby do wyœwietlenia");
				String surname = input.next();

				int count = 0;

				for (Person2 look : nowa) {
					if (look.getName().equals(name) && look.getSurname().equals(surname)) {
						looked = look;
						count++;
						break;
					}
				}

				if (count == 1) {
					System.out.println("znalazłem");
					System.out.println(looked.toString());
					return looked;

				} else if (count == 0) {
					System.out.println("nie znaleziono studenta o takich danych");
					break;
				} else {

					System.out.println("znaleziono więcej wyników o takich danych");
					break;
				}

			case 2:
				System.out.println("Podaj pesel osoby do wyœwietlenia");
				String pesel = input.next();
				Person2 found = new Person2();

				for (Person2 look : nowa) {
					if (look.getPesel().equals(pesel)) {
						found = look;
						System.out.println("znalazłem : " + found.toString());
						return found;

					} else {
						System.out.println("nie znalazlem");
						break;
					}

				}
				if (found.getOcenki() != null) {
					System.out.println("a jego oceny to: ");
					for (int g : found.getOcenki()) {
						System.out.println(g);
						looked = found;
					}

				}
			}
			break;
		}

		return looked;
	}

	public void remove() {
		System.out.println("Podaj nazwisko osoby do usuniêcia z listy");
		String surname = input.next();
		System.out.println("Podaj imię osoby do usuniêcia z listy");
		String name = input.next();
		int counter = 0;

		Person2 szukana = null;

		for (int i = 0; i < nowa.size(); i++) {
			Person2 lValue = nowa.get(i);
			if (lValue.getName().equals(name)) {
				szukana = lValue;
				counter++;
			}
		}

		if (counter == 1) {
			nowa.remove(szukana);
			System.out.println("udano usunięcie");
		}

		else if (counter > 1) {
			System.out.println("znalaziono za dużo wyników");
		}
	}

	public void addGrade(Person2 p) {
		Person2 p1 = p;

		if (p1 != null) {

			System.out.println("Wpisz ocenę studenta : " + p1.getName() + "  " + p1.getSurname());
			int grade = input.nextInt();

			p.getOcenki().add(grade);

			System.out.println("Dodano ocenę");

		}
	}

	public void saveToFile() throws IOException {
		File file = new File("c:\\java\\lista.txt");

		try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos);) {

			for (Person2 pp : nowa) {
				oos.writeObject(pp);
			}
		}

	}

	public void loadFromFile() throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
		File file = new File("c:\\java\\lista.txt");
		Person2 pLoad = null;
		Person2 comparable = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {

			while (ois.read() != 0) {
				pLoad = (Person2) ois.readObject();
				if (!compare(pLoad, comparable)) {
					nowa.add(a, pLoad);
					a++;
					continue;
				}
			}

			System.out.println("załadowano");
		} catch (EOFException eof) {
			System.out.println("koniec pliku - wszystko wczytane");
		}
	}

	@Override
	public int read(CharBuffer cb) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}
}
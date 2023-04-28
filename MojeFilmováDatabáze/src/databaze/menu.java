package databaze;
import java.sql.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class menu {

	 
        
	public static int pouzeCelaCisla(Scanner sc) 
		{
			int cislo = 0;
			try
			{
				cislo = sc.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("Nastala vyjimka typu "+e.toString());
				System.out.println("zadejte prosim cele cislo ");
				sc.nextLine();
				cislo = pouzeCelaCisla(sc);
			}
			return cislo;
		}


	public static void main(String[] args) {
		     
		seznamFilmu seznam = new seznamFilmu();
		Scanner sc=new Scanner(System.in);
		int volba;
		boolean run=true;
		String nazev;
		String reziser;
		int rok;
		ArrayList<Hodnoceni>hodnoceni = new ArrayList<Hodnoceni>();
		connection.connect();
		Film film;
		Film duplikat;
		while(run)
		{
		
			
			System.out.println("\nVitejte ve filmove databazi. Vyberte akci:");
			System.out.println("1 .. pridat animovany film");
			System.out.println("2 .. pridat hrany film");
			System.out.println("3 .. upravit film");
			System.out.println("4 .. smazat film");
			System.out.println(" ");
			System.out.println("6 .. pridat hodnoceni filmu");
			System.out.println("7 .. vypsat filmy");
			System.out.println("8 .. vyhledat film");
			System.out.println("9 .. vypsat herce nebo animatory, kteri se podileli na vice filmech");
			System.out.println("10 .. vypis filmu daneho herce nebo animatora");
			System.out.println("11 .. ulozeni informaci o filmu do souboru");
			System.out.println("12 .. nacteni informaci o filmu ze souboru");
			System.out.println("13 .. ukonceni aplikace");
			volba=pouzeCelaCisla(sc);
			switch(volba)
			{
			
				case 1:
					ArrayList<String>seznamAnimatoru = new ArrayList<String>();
					sc.nextLine();
					System.out.println("Zadejte nazev filmu: ");
					nazev = sc.nextLine();
					
					System.out.println("Zadejte rezisera filmu: ");
					reziser = sc.nextLine();
					
					System.out.println("Zadejte rok natoceni filmu: ");
					rok = pouzeCelaCisla(sc);
					
					System.out.println("Zadejte kolik animatoru chcete zadat: ");
					int pocetAnimatoru = pouzeCelaCisla(sc);
					
					sc.nextLine();
					System.out.println("Zadejte jednotlive animatory: ");										
					for (int i = 0; i < pocetAnimatoru; i++) {
						String animator = sc.nextLine();
						seznamAnimatoru.add(animator);
					}
										
					System.out.println("Zadejte minimalni vek divaka: ");
					int minimalniVek = pouzeCelaCisla(sc);
					film = new animovanyFilm(nazev,reziser,rok,seznamAnimatoru,minimalniVek, hodnoceni);
					duplikat = seznamFilmu.filmyMap.get(nazev);  
					if (duplikat == null) {
						seznamFilmu.pridatFilm(film);
					} else {
						System.out.println("Film s tímto názvem již existuje. Upravte stávající film, nebo změňte název. ");	
					}					
					
					break;
				case 2:
					ArrayList<String> seznamHercu = new ArrayList<String>();
					sc.nextLine();
					System.out.println("Zadejte nazev filmu: ");
					nazev = sc.nextLine();
					//sc.nextLine();
					
					System.out.println("Zadejte rezisera filmu: ");
					reziser = sc.nextLine();

					System.out.println("Zadejte rok natoceni filmu: ");
					rok = pouzeCelaCisla(sc);
					
					System.out.println("Zadejte kolik hercu chcete zadat: ");
					int pocetHercu = pouzeCelaCisla(sc);

					sc.nextLine();
					System.out.println("Zadejte jednotlive herce: ");										
					for (int i = 0; i < pocetHercu; i++) {
						String herec = sc.nextLine();
						seznamHercu.add(herec);
					}
					film = new 	hranyFilm(nazev,reziser,rok,seznamHercu, hodnoceni);	
					duplikat = seznamFilmu.filmyMap.get(nazev);  
					if (duplikat == null) {
						seznamFilmu.pridatFilm(film);
					} else {
						System.out.println("Film s tímto názvem již existuje. Upravte stávající film, nebo změňte název. ");	
					}
					
					
					break;
				case 3:
					sc.nextLine();
					System.out.println("Zadejte nazev filmu, který chcete upravit: ");
					nazev = sc.nextLine();
					System.out.println("Zadejte, co chcete upravit: nazev filmu (1), rezisera (2), rok vydani (3), seznam hercu nebo animatoru (4), minimalni vek (5): ");
					int operace = pouzeCelaCisla(sc);
					seznamFilmu.upravitFilm(nazev, operace);

					break;
				case 4:
					sc.nextLine();
					System.out.println("Zadejte název filmu, který chcete smazat: ");					
					nazev = sc.nextLine();
					seznamFilmu.smazatFilm(nazev);
					break;
				case 5:
					//tady nic neni
					break;
				case 6:
					sc.nextLine();
					System.out.println("Zadejte název filmu, kterému chcete přidat hodnocení: ");
					nazev = sc.nextLine();
					//sc.next();
					System.out.println("Zadejte číselné hodnocení (pro hrané filmy 1-5, pro animované 1-10): ");
					int hodnoceniCislo = pouzeCelaCisla(sc);
					sc.nextLine();
					System.out.println("Zadejte volitelné slovní hodnocení: ");
					String slovniHodnoceni = sc.nextLine();
					
					seznamFilmu.pridejHodnoceni(nazev, hodnoceniCislo, slovniHodnoceni);
					
					break;
				case 7:
					seznamFilmu.vypsatFilmy();
					break;
				case 8:
					sc.nextLine();					
					System.out.println("Zadejte název filmu, o kterém chcete vypsat informace: ");
					nazev = sc.nextLine();
					seznamFilmu.informaceOFilmu(nazev);
					break;
				case 9:
					seznamFilmu.vypisSeznamSpolupraceViceFilmu();
					break;
				case 10:
					sc.nextLine();
					System.out.println("Zadejte jméno herce nebo animátora, jehož filmy chcete vypsat: ");
					String jmeno = sc.nextLine();
					seznamFilmu.vypisFilmyJednohoHerce(jmeno);
					break;
				case 11:
					sc.nextLine();
					System.out.println("Zadejte jméno filmu,který chcete uložit: ");
					nazev=sc.nextLine();
					System.out.println("Zadejte název souboru, do kterého chcete uložit čitelný záznam: ");
					String nazevSouboru=sc.nextLine();
				try {
					seznamFilmu.serializuj(nazev);
					seznamFilmu.ulozitDoSouboru(nazev, nazevSouboru);
				} catch (IOException e) {					
					e.printStackTrace();
				}					
					break;
				case 12:
					sc.nextLine();
					System.out.println("Zadejte jméno filmu,který chcete načíst ze souboru: ");
					nazev =sc.nextLine();
					duplikat = seznamFilmu.filmyMap.get(nazev);  
					if (duplikat == null) {
									try {
								seznamFilmu.deserializuj(nazev);
							} catch (ClassNotFoundException e) {								
								e.printStackTrace();
							} catch (IOException e) {	
								System.out.println("Tento film nebyl uložen do souboru. ");
								
							}
					} else {
						System.out.println("Film s tímto názvem již existuje. Upravte stávající film, nebo ho smažte. ");	
					}				
					break;
				case 13:
					connection.disconnect();
					run=false;
					break;
			}
		}
		
	}

}

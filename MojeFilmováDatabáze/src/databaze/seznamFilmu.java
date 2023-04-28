package databaze;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class seznamFilmu {
     static List<Film> filmy;
    static Map<String, Film> filmyMap;
    static Scanner sc=new Scanner(System.in);
    public seznamFilmu() {
        filmy = new ArrayList<>();
        filmyMap = new HashMap<>();
    }
    
    public static void pridatFilm(Film film) {
        filmy.add(film);
        filmyMap.put(film.getNazev(), film);
    }
   
    public static void upravitFilm(String nazev, int operace) {
        
    	Film film = filmyMap.get(nazev);
        
        if (film != null) {       	
        	
        	switch (operace) {
			case 1:
				sc.nextLine();
				System.out.println("Zadejte nazev filmu: ");
				String novyNazev = sc.nextLine();
				film.setNazev(novyNazev);
				break;
			case 2:
				sc.nextLine();
				System.out.println("Zadejte jmeno rezisera: ");
				String novyReziser = sc.nextLine();
				film.setReziser(novyReziser);
				break;
			case 3:
				System.out.println("Zadejte rok vydaní: ");
				int novyRokVydani = menu.pouzeCelaCisla(sc);				
			    film.setRokVydani(novyRokVydani);
				break;
			case 4:				
				 if (film instanceof hranyFilm) {
					 ArrayList<String>seznamHercu = new ArrayList<String>();
		            	System.out.println("Zadejte kolik herců chcete zadat: ");
						int pocetHercu = menu.pouzeCelaCisla(sc);	
						sc.nextLine();
						System.out.println("Zadejte jednotlive herce: ");										
						for (int i = 0; i < pocetHercu; i++) {
							String herec = sc.nextLine();
							seznamHercu.add(herec);
						}  
						film.setSeznamOsob(seznamHercu);
		            } else if (film instanceof animovanyFilm) {
		            	ArrayList<String>seznamAnimatoru = new ArrayList<String>();
		            	System.out.println("Zadejte kolik animatoru chcete zadat: ");
						int pocetAnimatoru = menu.pouzeCelaCisla(sc);	
						sc.nextLine();
						System.out.println("Zadejte jednotlive animatory: ");										
						for (int i = 0; i < pocetAnimatoru; i++) {
							String animator = sc.nextLine();
							seznamAnimatoru.add(animator);
						}	
						film.setSeznamOsob(seznamAnimatoru);
		            }
				break;
			case 5:
				if (film instanceof hranyFilm) {
					System.out.println("U hraného filmu nelze zadat minimální věk! ");
		            } else if (film instanceof animovanyFilm) {
		            	System.out.println("Zadejte minimalní věk: ");
						int novyVek = menu.pouzeCelaCisla(sc);	
						film.setVekovaHranice(novyVek);
		            }
				break;
			default:
				System.out.println("Neplatné číslo! ");
				break;
			}
        } else {
        	System.out.println("Film s daným názvem neexistuje! ");
        	}
     }
    
    
    public static void smazatFilm(String nazev) {
        Film film = filmyMap.get(nazev);
        if (film != null) {
            filmy.remove(film);
            filmyMap.remove(nazev);
            System.out.println("Film byl smazán");
        }else {
        System.out.println("Film s daným názvem neexistuje");}
    }

    
    public static void vypsatFilmy() {
        for (Film film : filmy) {
            System.out.println(film.getInfo());
        }
    }
    
    public void vyhledatFilm(String nazev) {
        Film film = filmyMap.get(nazev);
        if (film != null) {
            System.out.println(film.toString());
        } else {
            System.out.println("Film nebyl nalezen.");
        }
    }
    
    public static void pridejHodnoceni(String nazev, int hodnoceni, String komentar) {
    	Film film = filmyMap.get(nazev);  
    	

    	if (film != null) {
    		
    		if ((film instanceof hranyFilm) && (hodnoceni<=5)&&(hodnoceni>=0)) {
    		Hodnoceni h = new Hodnoceni(hodnoceni, komentar);
    		film.hodnoceni.add(h);
    		} else if ((film instanceof animovanyFilm) && (hodnoceni<=10)&&(hodnoceni>=0)){
    			Hodnoceni h = new Hodnoceni(hodnoceni, komentar);
        		film.hodnoceni.add(h);
    		} else {
    			System.out.println("Mimo rozsah! ");
    		}

    	} else {
    		System.out.println("Film nebyl nalezen.");
    	}
    }
    
    public static void vypisFilmyJednohoHerce(String jmeno) {
        try {
			for (Film film : filmy) {					
			    if (film.getSeznamOsob().contains(jmeno)) {
			        System.out.println(film.getNazev());
			    }
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
    }
    
    
    public static void informaceOFilmu(String nazevFilmu) {
        Film film = filmyMap.get(nazevFilmu);
        
        if (film != null) {
            System.out.println("Název: " + film.getNazev());
            System.out.println("Režisér: " + film.getReziser());
            System.out.println("Rok vydání: " + film.getRokVydani());
            if (film instanceof hranyFilm) {
            	System.out.println("Herci: " + film.getSeznamOsob());
            } else {
            	System.out.println("Animátoři: " + film.getSeznamOsob());
            	System.out.println("Minimální věk: "+film.getVekovaHranice());
            }           
            System.out.println("Hodnocení diváků (sestupně): " + sestupneHodnoceni(film.getSeznamHodnoceni()));
        } else {
            System.out.println("Film s názvem '" + nazevFilmu + "' nebyl nalezen.");
        }
    }

    private static ArrayList<Hodnoceni> sestupneHodnoceni(ArrayList<Hodnoceni> hodnoceni) {
        ArrayList<Hodnoceni> sestupneHodnoceni = new ArrayList<>(hodnoceni);
        Collections.sort(sestupneHodnoceni, Collections.reverseOrder());       
        return sestupneHodnoceni;
    }
    
    public static void vypisSpolupraceViceFilmu() {
        HashMap<String, ArrayList<Film>> spolupraceViceFilmu = new HashMap<>();

       
        for (Film film : filmy) {
          
            for (String osoba : film.getSeznamOsob()) {
                String jmeno = osoba;
                
                if (spolupraceViceFilmu.containsKey(jmeno)) {
                    ArrayList<Film> filmyOsoby = spolupraceViceFilmu.get(jmeno);
                    filmyOsoby.add(film);
                    spolupraceViceFilmu.put(jmeno, filmyOsoby);
                } else {
                  
                    ArrayList<Film> filmyOsoby = new ArrayList<>();
                    filmyOsoby.add(film);
                    spolupraceViceFilmu.put(jmeno, filmyOsoby);
                }
            }
        }


        for (String jmeno : spolupraceViceFilmu.keySet()) {
            ArrayList<Film> filmyOsoby = spolupraceViceFilmu.get(jmeno);
            if (filmyOsoby.size() > 1) {
                System.out.println("\nOsoba: " + jmeno);
                System.out.println("Filmy:");
                for (Film film : filmyOsoby) {
                    System.out.println(film.getNazev() + " (" + film.getRokVydani() + ")");
                }
            }
        }
    }

    public static void vypisSeznamSpolupraceViceFilmu() {
        System.out.println("Seznam herců a animátorů, kteří se podíleli na více než jednom filmu:");
        vypisSpolupraceViceFilmu();
    }
    
    public static void ulozitDoSouboru(String nazevFilmu, String nazevSouboru) {
    	Film film = filmyMap.get(nazevFilmu);
    	if (film == null) {
    		System.out.println("Film neexistuje! ");
    	} else {
	    		        try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter(nazevSouboru+".txt"));
	            writer.write("Název: " + film.getNazev() + "\n");
	            writer.write("Režisér: " + film.getReziser() + "\n");
	            writer.write("Rok vydání: " + film.getRokVydani() + "\n");
	            writer.write("Hodnocení: " + film.getSeznamHodnoceni() + "\n");
	            writer.write("Seznam osob: " + String.join("; ", film.getSeznamOsob()) + "\n");
	            writer.close();
	            System.out.println("Informace o filmu byly úspěšně uloženy do souboru " + nazevSouboru+".txt");
	        } catch (IOException e) {
	            System.err.println("Chyba při zápisu do souboru: " + e.getMessage());
	        }
    	}

    }
    
    
    public static void serializuj(String nazev) throws IOException {
        Film film = filmyMap.get(nazev);
        if (film == null) {
    		//System.out.println("Film neexistuje! ");
    	} else {
	        FileOutputStream fos = new FileOutputStream(nazev+".dat");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(film);
	        fos.close();
    	}
    }

    public static Film deserializuj(String nazevSouboru) throws IOException, ClassNotFoundException {
    	
        FileInputStream fis = new FileInputStream(nazevSouboru+".dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
  
        Film film = (Film) ois.readObject();
        seznamFilmu.pridatFilm(film);
        fis.close();
        return film;
    }

    public static void ulozDoSQL(Connection conn) throws SQLException {
    	for (Film film : filmy) {
    		if (film instanceof hranyFilm) {
				String insert = "INSERT INTO filmy (typFilmu,nazev,reziser,rok) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, "hranyFilm");
            pstmt.setString(2, film.getNazev());
            pstmt.setString(3, film.getReziser());
            pstmt.setInt(4, film.getRokVydani());            
            pstmt.executeUpdate();
            
            for (String osoba :  film.getSeznamOsob()) {
				insert = "INSERT INTO osoby (nazevFilmu,jmeno) VALUES (?,?)";
				pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, film.getNazev());
            pstmt.setString(2, osoba);
            pstmt.executeUpdate();
			}
            for (Hodnoceni hodnoceni: film.getSeznamHodnoceni()) {
            	insert = "INSERT INTO hodnoceni (nazevFilmu,ciselneHodnoceni,komentar) VALUES (?,?,?)";
				pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, film.getNazev());
            pstmt.setInt(2, hodnoceni.getBodoveHodnoceni());
            pstmt.setString(3, hodnoceni.getKomentar());
            pstmt.executeUpdate();
            }
            
			} else {
				String insert = "INSERT INTO filmy (typFilmu,nazev,reziser,rok,vekovaHranice) " +
	                    "VALUES (?, ?, ?, ?,?)";
	            PreparedStatement pstmt = conn.prepareStatement(insert);
	            pstmt.setString(1, "animovanyFilm");
	            pstmt.setString(2, film.getNazev());
	            pstmt.setString(3, film.getReziser());
	            pstmt.setInt(4, film.getRokVydani());   
	            pstmt.setInt(5, film.getVekovaHranice()); 
	            pstmt.executeUpdate();
	            for (String osoba :  film.getSeznamOsob()) {
					insert = "INSERT INTO osoby (nazevFilmu,jmeno) VALUES (?,?)";
					pstmt = conn.prepareStatement(insert);
	            pstmt.setString(1, film.getNazev());
	            pstmt.setString(2, osoba);
	            pstmt.executeUpdate();
			}
	            for (Hodnoceni hodnoceni: film.getSeznamHodnoceni()) {
	            	insert = "INSERT INTO hodnoceni (nazevFilmu,ciselneHodnoceni,komentar) VALUES (?,?,?)";
					pstmt = conn.prepareStatement(insert);
	            pstmt.setString(1, film.getNazev());
	            pstmt.setInt(2, hodnoceni.getBodoveHodnoceni());
	            pstmt.setString(3, hodnoceni.getKomentar());
	            pstmt.executeUpdate();
	            }
			}
            
        }
    }
    
    public static void nactiZeSQL(Connection conn)throws SQLException{
    	 Statement stmt = conn.createStatement();
    	 String query = "SELECT * FROM filmy";
         ResultSet rs = stmt.executeQuery(query);         
         while (rs.next()) {
        	 if (rs.getString("typFilmu").equals("hranyFilm")) {       		
        		ArrayList<Hodnoceni>hodnoceni = new ArrayList<Hodnoceni>();
        		String nazevFilmu = rs.getString("nazev");
        		ArrayList<String> seznamHercu = getSeznamOsob(nazevFilmu,conn);       	
				pridatFilm(new hranyFilm(rs.getString("nazev"),rs.getString("reziser"),rs.getInt("rok"),seznamHercu, hodnoceni));  
		    	Statement state = conn.createStatement();
		    	ResultSet hodnocenizapis = state.executeQuery("SELECT * FROM hodnoceni WHERE nazevFilmu = '"+nazevFilmu+"'");
				while (hodnocenizapis.next()) {
					pridejHodnoceni(rs.getString("nazev"), hodnocenizapis.getInt("ciselneHodnoceni"),hodnocenizapis.getString("komentar"));
				}
			} else {
				String nazevFilmu = rs.getString("nazev");
				ArrayList<String> seznamAnimatoru = getSeznamOsob(nazevFilmu,conn);
        		ArrayList<Hodnoceni>hodnoceni = new ArrayList<Hodnoceni>();
				pridatFilm(new animovanyFilm(rs.getString("nazev"),rs.getString("reziser"),rs.getInt("rok"),seznamAnimatoru,rs.getInt("vekovaHranice"), hodnoceni));  
				Statement state = conn.createStatement();
		    	ResultSet hodnocenizapis = state.executeQuery("SELECT * FROM hodnoceni WHERE nazevFilmu = '"+nazevFilmu+"'");
				while (hodnocenizapis.next()) {
					pridejHodnoceni(rs.getString("nazev"), hodnocenizapis.getInt("ciselneHodnoceni"),hodnocenizapis.getString("komentar"));
				}
			}
        	
         }
    }
    
    private static ArrayList<String> getSeznamOsob(String nazevFilmu, Connection conn) throws SQLException {
    	ArrayList<String> seznamOsob = new ArrayList<String>();
    	Statement stmt = conn.createStatement();
    	ResultSet osoby = stmt.executeQuery("SELECT * FROM osoby WHERE nazevFilmu = '"+nazevFilmu+"'");
		while (osoby.next()) {
			seznamOsob.add(osoby.getString("jmeno"));
		}
		return seznamOsob;
    }
    
    
    
}
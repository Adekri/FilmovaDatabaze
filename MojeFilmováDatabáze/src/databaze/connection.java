package databaze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connection {
	private static Connection conn; 
	public static boolean connect() { 
	       conn= null; 
	       try {
	    	   ///C:\\AdAbsurdum\\Java\\MojeFilmováDatabáze\\filmdb.db
	              conn = DriverManager.getConnection("jdbc:sqlite:filmdb.db"); 
	              Statement stmt = conn.createStatement();            
	                  String createTable = "CREATE TABLE IF NOT EXISTS filmy (" +
	                          "id INTEGER PRIMARY KEY AUTOINCREMENT," +
	                          "typFilmu TEXT," +
	                          "nazev TEXT NOT NULL," +
	                          "reziser TEXT," +
	                          "rok INTEGER," +
	                          "seznamHercu INTEGER," +
	                          "seznamHodnoceni INTEGER," +
	                          "vekovaHranice INTEGER" +
	                          ")";
	                  stmt.executeUpdate(createTable);
	                  
	                  createTable="CREATE TABLE IF NOT EXISTS osoby ("+
	                  "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
	                  "nazevFilmu TEXT,"+
	                  "jmeno TEXT)";
	                  stmt.executeUpdate(createTable);
	                  
	                  createTable="CREATE TABLE IF NOT EXISTS hodnoceni ("+
	                		  "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
	    	                  "nazevFilmu TEXT,"+
	    	                  "ciselneHodnoceni INTEGER,"+
	    	                  "komentar TEXT)";
	    	                  stmt.executeUpdate(createTable);
	              seznamFilmu.nactiZeSQL(conn);
	       } 
	      catch (SQLException e) { 
	            System.out.println(e.getMessage());
		    return false;
	      }
	      return true;
	}
	public static void disconnect() { 
		if (conn != null) {
		       try {     
		    	   conn.createStatement().execute("DROP TABLE filmy");
		    	   conn.createStatement().execute("DROP TABLE hodnoceni");
		    	   conn.createStatement().execute("DROP TABLE osoby");
		    	   Statement stmt = conn.createStatement();             
	                  String createTable = "CREATE TABLE IF NOT EXISTS filmy (" +
	                          "id INTEGER PRIMARY KEY AUTOINCREMENT," +
	                          "typFilmu TEXT," +
	                          "nazev TEXT NOT NULL," +
	                          "reziser TEXT," +
	                          "rok INTEGER," +
	                          "seznamHercu INTEGER," +
	                          "seznamHodnoceni INTEGER," +
	                          "vekovaHranice INTEGER" +
	                          ")";
	                  stmt.executeUpdate(createTable);
	                  createTable="CREATE TABLE IF NOT EXISTS osoby ("+
	    	                  "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
	    	                  "nazevFilmu TEXT,"+
	    	                  "jmeno TEXT)";
	    	          stmt.executeUpdate(createTable);
	    	                  
	    	          createTable="CREATE TABLE IF NOT EXISTS hodnoceni ("+
	                		  "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
	    	                  "nazevFilmu TEXT,"+
	    	                  "ciselneHodnoceni INTEGER,"+
	    	                  "komentar TEXT)";
	    	    	  stmt.executeUpdate(createTable);
		    	   seznamFilmu.ulozDoSQL(conn);		       
		    	   conn.close();
		       
		       }
	               catch (SQLException ex) { System.out.println(ex.getMessage()); }
		}
	}
	


}

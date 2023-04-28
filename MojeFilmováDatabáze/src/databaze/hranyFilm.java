package databaze;

import java.util.ArrayList;

public class hranyFilm extends Film{

	protected ArrayList<String> seznamHercu;
	
	public hranyFilm(String nazev, String reziser, int rokVydani, ArrayList<String> seznamHercu, ArrayList<Hodnoceni>hodnoceni) {
		super(nazev, reziser, rokVydani, hodnoceni);
		this.seznamHercu=seznamHercu;
	}

	@Override
	public String getInfo() {
		return "Hrany film - Název: " + nazev + ", Režisér: " + reziser + ", Rok vydání: " + rokVydani + ", Seznam herců: " + seznamHercu.toString();
		
	}
	
	public ArrayList<String> getSeznamOsob() {
		return seznamHercu;
	}

	@Override
	public String getType() {		
		return "hrany";
	}

	@Override
	public int getVekovaHranice() {		
		return 0;
	}
	@Override
	public void setVekovaHranice(int doporucenyVek) {

	}

	@Override
	public void setSeznamOsob(ArrayList<String> seznamOsob) {
		this.seznamHercu=seznamOsob;
		
	}



}

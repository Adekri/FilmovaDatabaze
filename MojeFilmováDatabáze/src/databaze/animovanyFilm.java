package databaze;

import java.util.ArrayList;

public class animovanyFilm extends Film{

	protected int doporucenyVek;
	protected  ArrayList<String> seznamAnimatoru;
	public animovanyFilm(String nazev, String reziser, int rokVydani, ArrayList<String> seznamAnimatoru, int doporucenyVek, ArrayList<Hodnoceni>hodnoceni) {
		super(nazev, reziser, rokVydani, hodnoceni);
		this.seznamAnimatoru=seznamAnimatoru;
		this.doporucenyVek=doporucenyVek;
	}

	@Override
	public String getInfo() {
		return "Animovaný film - Název: " + nazev + ", Režisér: " + reziser + ", Rok vydání: " + rokVydani + ", Seznam animátorů: " + seznamAnimatoru.toString() + ", doporučený věk: "+doporucenyVek;

	}

	@Override
	public ArrayList<String> getSeznamOsob() {		
		return seznamAnimatoru;
	}

	@Override
	public String getType() {		
		return "animovany";
	}
	
	@Override
	public int getVekovaHranice() {
		return doporucenyVek;
	}

	@Override
	public void setVekovaHranice(int doporucenyVek) {
	this.doporucenyVek=doporucenyVek;
	}

	@Override
	public void setSeznamOsob(ArrayList<String> seznamOsob) {
		this.seznamAnimatoru=seznamOsob;
		
	}



}

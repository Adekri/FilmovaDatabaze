package databaze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Film implements Serializable {
	protected String nazev;
    protected String reziser;
    protected int rokVydani;
    protected ArrayList<Hodnoceni>hodnoceni;

    public Film(String nazev, String reziser, int rokVydani, ArrayList<Hodnoceni>hodnoceni) {
        this.nazev = nazev;
        this.reziser = reziser;
        this.rokVydani = rokVydani;
        this.hodnoceni = hodnoceni;       
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getReziser() {
        return reziser;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public int getRokVydani() {
        return rokVydani;
    }

    public void setRokVydani(int rokVydani) {
        this.rokVydani = rokVydani;
    }


    
    public ArrayList<Hodnoceni> getSeznamHodnoceni() {
        return hodnoceni;
    }
    
    public abstract String getType();
    public abstract String getInfo();
    public abstract ArrayList<String> getSeznamOsob();
    public abstract void setSeznamOsob(ArrayList<String> seznamOsob);

    public abstract int getVekovaHranice();

	public abstract void setVekovaHranice(int doporucenyVek);
    
}


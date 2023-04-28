package databaze;

import java.io.Serializable;

public class Hodnoceni implements Comparable<Hodnoceni>,Serializable {
	private int bodoveHodnoceni;
    private String komentar;

    public Hodnoceni(int bodoveHodnoceni, String komentar) {
        this.bodoveHodnoceni = bodoveHodnoceni;
        this.komentar = komentar;
    }

    public int getBodoveHodnoceni() {
        return bodoveHodnoceni;
    }

    public String getKomentar() {
        return komentar;
    }
    
    @Override
    public String toString() {
        return "\nHodnocení: " + bodoveHodnoceni + " Komentář: " + komentar;
    }

    @Override
    public int compareTo(Hodnoceni kPorovnani) {
        return Integer.compare(this.bodoveHodnoceni, kPorovnani.bodoveHodnoceni);
    }
}

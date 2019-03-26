package at.gv.brz.entitaeten;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author roeslerz
 *
 * Produkt Entitaet
 */
@Entity
public class Produkt {
   
    @Id
    private int pid;
    private int lid;
    private String produktId;
    private String produktName;
    private int produktPreis;
    private int produktAnzahl;
    @Transient
    private String produktLieferant;
    
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public int getLid() {
        return lid;
    }
    public void setLid(int lid) {
        this.lid = lid;
    }
    public String getProduktId() {
        return produktId;
    }
    public void setProduktId(String produktId) {
        this.produktId = produktId;
    }
    public String getProduktName() {
        return produktName;
    }
    public void setProduktName(String produktName) {
        this.produktName = produktName;
    }
    public int getProduktPreis() {
        return produktPreis;
    }
    public void setProduktPreis(int produktPreis) {
        this.produktPreis = produktPreis;
    }

    public int getProduktAnzahl() {
        return produktAnzahl;
    }
    public void setProduktAnzahl(int produktAnzahlt) {
        this.produktAnzahl = produktAnzahlt;
    }
    
    
    public String getProduktLieferant() {
        return produktLieferant;
    }
    public void setProduktLieferant(String produktLieferant) {
        this.produktLieferant = produktLieferant;
    }
    @Override
    public String toString() {
        return "Produkt [pid=" + pid + ", lid=" + lid + ", produktId=" + produktId + ", produktName=" + produktName
               + ", produktPreis=" + produktPreis + ", produktAnzahl=" + produktAnzahl + ", produktLieferant="
               + produktLieferant + "]";
    }
    
    
   
    
}

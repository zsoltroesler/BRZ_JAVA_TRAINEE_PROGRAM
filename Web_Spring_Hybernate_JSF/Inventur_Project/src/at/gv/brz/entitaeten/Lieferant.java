package at.gv.brz.entitaeten;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author roeslerz
 *
 * Lieferant Entitaet
 */
@Entity
public class Lieferant {
    
    @Id
    private int lid;
    private String lieferantId;
    private String lieferantName;
    private String lieferantEmail;
    private String lieferantTel;  
 
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="lid")
    private List<Produkt> produkte;
    
    @PostConstruct
    public void postConstructLieferant() {
        produkte = new ArrayList<>();
    }
    public List<Produkt> getProdukte() {
        return produkte;
    }
    public void setProdukte(List<Produkt> produkte) {
        this.produkte = produkte;
    }
        
    public int getLid() {
        return lid;
    }
    public void setLid(int lid) {
        this.lid = lid;
    }
    public String getLieferantId() {
        return lieferantId;
    }
    public void setLieferantId(String lieferantId) {
        this.lieferantId = lieferantId;
    }
    public String getLieferantName() {
        return lieferantName;
    }
    public void setLieferantName(String lieferantName) {
        this.lieferantName = lieferantName;
    }
    public String getLieferantEmail() {
        return lieferantEmail;
    }
    public void setLieferantEmail(String lieferantEmail) {
        this.lieferantEmail = lieferantEmail;
    }
    public String getLieferantTel() {
        return lieferantTel;
    }
    public void setLieferantTel(String lieferantTel) {
        this.lieferantTel = lieferantTel;
    }
    @Override
    public String toString() {
        return "Lieferant [lid=" + lid + ", lieferantId=" + lieferantId + ", lieferantName=" + lieferantName
               + ", lieferantEmail=" + lieferantEmail + ", lieferantTel=" + lieferantTel + ", produkte=" + produkte
               + "]";
    }
}

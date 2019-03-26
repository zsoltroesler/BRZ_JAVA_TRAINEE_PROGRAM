package at.gv.brz.backingbeans;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.gv.brz.entitaeten.Produkt;
import at.gv.brz.exceptions.BRZException;
import at.gv.brz.services.LieferantService;
import at.gv.brz.services.ProduktService;

@Component
public class ProduktAendernBean {
    
    private static Logger logger = LogManager.getLogger(ProduktAendernBean.class);
    
    @Autowired
    private ProduktService produktService;
    
    @Autowired
    private LieferantService lieferantService;

    private Produkt produkt;
    
    private SelectItem[] lieferantNamen;
    
    private void init() {
        produkt = new Produkt();
    }

    public ProduktAendernBean() {
        this.init();
    }
    
    @PostConstruct
    public void initLieferantNamen() throws BRZException {
        lieferantNamen = lieferantService.mapIdtoName();
        logger.info("ProduktAendernBean: " + lieferantNamen);
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }
     
    public SelectItem[] getLieferantNamen() throws BRZException {
        return lieferantNamen = lieferantService.mapIdtoName();
    }

    public void setLieferantNamen(SelectItem[] lieferantNamen) {
        this.lieferantNamen = lieferantNamen;
    }

    /**
     * Update in der Datenbank passiert hier 
     * 
     * @return "speichernProdukt.jsf" oder "errorSeite.jsf"
     */
    public String speichern() {
        try {
            produktService.update(produkt);
            logger.info(produkt);
        } catch (BRZException e) {
            logger.error("Error in speichern()" + e);
            return "errorSeite.jsf";
        }
        return "speichernProdukt.jsf?faces-redirect=true";
    }
}

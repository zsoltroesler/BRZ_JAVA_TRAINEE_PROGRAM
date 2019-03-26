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
public class ProduktEingabeBean {
    
    private static Logger logger = LogManager.getLogger(ProduktEingabeBean.class);
    
    @Autowired
    private ProduktService produktService;

    @Autowired
    private LieferantService lieferantService;
    
    private Produkt produkt;

    private SelectItem[] lieferantNamen;
    
    private void init() {
        produkt = new Produkt();

    }

    public  ProduktEingabeBean() {
        this.init();
    }
    
    @PostConstruct
    public void initLieferantNamen() throws BRZException {
        lieferantNamen = lieferantService.mapIdtoName();
        logger.info("ProduktEingabeBean: " + lieferantNamen);
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
     * Einfuegen des neuen @Produkt in die Datenbank
     * und navigiert weiter zu speichernProdukt.jsf oder errorProdukt.jsf Seite
     * 
     * @return speichernProdukt.jsf oder errorProdukt.jsf
     */
    public String speichern() {
        String produktId = produkt.getProduktId();
        logger.info("ProduktEingabeBean.speichern(): " + produktId);
        
        try {
            if (produktService.validateProduktId(produktId)) {
                produktService.insert(produkt);
                logger.info(produkt);
                // nachdem Insert loescht alle Input-Felder
                init();
                return "speichernProdukt.jsf?faces-redirect=true";
            } else {
                init();
                // wenn die Produktnummer schon existiert
                return "errorProdukt.jsf?faces-redirect=true";
            }
        } catch (BRZException e) {
            logger.error("Error in speichern()" + e);
            return "errorSeite.jsf";
        }
    }
}

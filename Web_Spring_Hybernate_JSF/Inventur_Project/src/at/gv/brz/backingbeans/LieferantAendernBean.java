package at.gv.brz.backingbeans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.gv.brz.entitaeten.Lieferant;
import at.gv.brz.exceptions.BRZException;
import at.gv.brz.services.LieferantService;

@Component
public class LieferantAendernBean {
    
    private static Logger logger = LogManager.getLogger(LieferantAendernBean.class);
    
    @Autowired
    private LieferantService lieferantService;
    
    private Lieferant lieferant;

    private void init() {
        lieferant = new Lieferant();
    }

    public LieferantAendernBean() {
        this.init();
    }

    public Lieferant getLieferant() {
        return lieferant;
    }

    public void setLieferant(Lieferant lieferant) {
        this.lieferant = lieferant;
    }

    /**
     * Update in der Datenbank passiert hier 
     * 
     * @return "speichernLieferant.jsf"
     */
    public String speichern() {
        try {
            lieferantService.update(lieferant);
            logger.info(lieferant);
        } catch (BRZException e) {  
            logger.error("Error in speichern()" + e);
            return "errorSeite.jsf";
        }
        return "speichernLieferant.jsf?faces-redirect=true";
    }
}

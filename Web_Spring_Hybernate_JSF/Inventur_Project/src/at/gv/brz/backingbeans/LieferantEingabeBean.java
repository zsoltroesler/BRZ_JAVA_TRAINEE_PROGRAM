package at.gv.brz.backingbeans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.gv.brz.entitaeten.Lieferant;
import at.gv.brz.exceptions.BRZException;
import at.gv.brz.services.LieferantService;

@Component
public class LieferantEingabeBean {

    private static Logger logger = LogManager.getLogger(LieferantEingabeBean.class);

    @Autowired
    private LieferantService lieferantService;

    private Lieferant lieferant;

    private void init() {
        lieferant = new Lieferant();
    }

    public LieferantEingabeBean() {
        this.init();
    }

    public Lieferant getLieferant() {
        return lieferant;
    }

    public void setLieferant(Lieferant lieferant) {
        this.lieferant = lieferant;
    }

    /**
     * Einfuegen des neuen @Lieferant in die Datenbank und navigiert weiter zu speichernLieferant.jsf oder
     * errorLieferant.jsf Seite
     * 
     * @return speichernLieferant.jsf oder errorLieferant.jsf
     */
    public String speichern() {
        String lieferantId = lieferant.getLieferantId();
        logger.info("LieferantEingabeBean.speichern(): " + lieferantId);

            try {
                if (lieferantService.validateLieferantId(lieferantId)) {
                    lieferantService.insert(lieferant);
                    logger.info(lieferant);
                    // nachdem Insert loescht alle Input-Felder
                    init();
                    return "speichernLieferant.jsf?faces-redirect=true";
                } else {
                    init();
                    // wenn der Lieferantname schon existiert
                    return "errorLieferant.jsf?faces-redirect=true";
                }
            } catch (BRZException e) {
                logger.error("Error in speichern()" + e);
                return "errorSeite.jsf";
            }
    }
}

// logger.info(lieferant);
//// nachdem Insert loescht alle Input-Felder
// init();
// return "speichernLieferant.jsf?faces-redirect=true";
// } else {
// init();
//// wenn der Lieferantname schon existiert
// return "errorLieferant.jsf?faces-redirect=true";
// }

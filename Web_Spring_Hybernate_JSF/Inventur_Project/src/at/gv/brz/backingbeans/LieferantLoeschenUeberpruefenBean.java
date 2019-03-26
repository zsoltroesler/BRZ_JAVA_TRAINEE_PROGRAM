package at.gv.brz.backingbeans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.gv.brz.entitaeten.Lieferant;
import at.gv.brz.exceptions.BRZException;
import at.gv.brz.services.LieferantService;

@Component
public class LieferantLoeschenUeberpruefenBean {
    
    private static Logger logger = LogManager.getLogger(LieferantLoeschenUeberpruefenBean.class);

    @Autowired
    private LieferantService lieferantService;

    private Lieferant lieferant;

    public Lieferant getLieferant() {
        return lieferant;
    }

    public void setLieferant(Lieferant lieferant) {
        this.lieferant = lieferant;
    }

    /**
     * Die Methode loescht den Lieferant aus der Datenbank wenn er Produkte hat nachdem Ueberpruefen
     * 
     * @return "loeschenLieferant.jsf"
     */
    public String loeschen() {
        try {
            lieferantService.delete(lieferant);
        } catch (BRZException e) {
            logger.error("Error in loeschen()" + e);
            return "errorSeite.jsf";
        }
        return "loeschenLieferant.jsf";
    }

}

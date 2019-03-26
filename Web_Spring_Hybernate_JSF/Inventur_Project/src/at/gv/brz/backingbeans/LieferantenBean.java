package at.gv.brz.backingbeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.gv.brz.entitaeten.Lieferant;
import at.gv.brz.exceptions.BRZException;
import at.gv.brz.services.LieferantService;

@Component
public class LieferantenBean {
    
    private static Logger logger = LogManager.getLogger(LieferantenBean.class);
    
    @Autowired
    private LieferantService lieferantService;
    
    @Autowired
    private LieferantAendernBean lieferantAendernBean;
    
    @Autowired
    private LieferantLoeschenUeberpruefenBean lieferantLoeschenUeberpruefenBean;
    
    private List<Lieferant> lieferanten;
    
    @PostConstruct
    public void initNachErstellung() {
        init();
    }

    public void init() {
        try {
            lieferanten = lieferantService.getLieferantListe();
        } catch (BRZException e) {
          logger.error(e);
        }
        logger.info(lieferanten);
    }

    public List<Lieferant> getLieferanten() {
        init();
        return lieferanten;
    }

    public void setLieferanten(List<Lieferant> lieferanten) {
        this.lieferanten = lieferanten;
    }
    
    /**
     * Die Methode nimmt die "id" Key aus index.jsf setzt ein neues Lieferant fuer produktAendernBean
     * und leitet weiter zu lieferantAendern.jsf
     * 
     * @return "lieferantAendern.jsf"
     */
    public String aendern() {
        try {
            lieferantAendernBean.setLieferant(lieferantService.getLieferantById(getId()));
        } catch (BRZException e) {
            logger.error("Error in aendern()" + e);
            return "errorSeite.jsf";
        }  
        return "lieferantAendern.jsf";
    }
    
    /**
     * Die Methode loescht den Lieferant aus der Datenbank wenn er keine Produkte in der Datenbank hat
     * oder leitet weiter zu lieferantLoeschenUeberpruefen.jsf
     * 
     * @return "loeschenLieferant.jsf" / "lieferantLoeschenUeberpruefen.jsf"
     */
    public String loeschen() {
        Lieferant lieferant;
        try {
            lieferant = lieferantService.getLieferantById(getId());
            if (lieferant.getProdukte().size() == 0 ) {
                lieferantService.delete(lieferant);         
                return "loeschenLieferant.jsf";
            } else {
                lieferantLoeschenUeberpruefenBean.setLieferant(lieferant);
                return "lieferantLoeschenUeberpruefen.jsf";
            }
        } catch (BRZException e) {
            logger.error("Error in loeschen()" + e);
            return "errorSeite.jsf";
        }
    }
      
    /**
     * Hilfsmethode gibt den bestimmten Datenbank-ID aus lieferanten.jsf
     * 
     * @return lieferant
     */
    private int getId() {
        FacesContext facexcontext = FacesContext.getCurrentInstance();
        Map<String, String> requestParameterMap = facexcontext.getExternalContext().getRequestParameterMap();

        String lId = requestParameterMap.get("lid");
        int id = Integer.valueOf(lId);
        
        return id;
    }
    
}

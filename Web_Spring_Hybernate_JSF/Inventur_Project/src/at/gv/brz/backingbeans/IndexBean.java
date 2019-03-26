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
import at.gv.brz.entitaeten.Produkt;
import at.gv.brz.exceptions.BRZException;
import at.gv.brz.services.ProduktService;

@Component
public class IndexBean {
    
    private static Logger logger = LogManager.getLogger(IndexBean.class);
    
    @Autowired
    private ProduktService produktService;
       
    @Autowired
    private ProduktAendernBean produktAendernBean;
    
    @Autowired
    private LieferantenBean lieferantenBean;
    
    private List<Produkt> produkte;
     
    @PostConstruct
    public void initNachErstellung() {
        init();
    }

    private void init() {
        try {
            produkte = produktService.getProduktListe();
        } catch (BRZException e) {
          logger.error(e);
        }
        lieferantenBean.init();
        updateProdukteMitLieferantnamen();
        logger.info(produkte);
    }
    
    public List<Produkt> getProdukte() {
        init();
        return produkte;
    }

    public void setProdukte(List<Produkt> produkte) {
        this.produkte = produkte;
    }
    

    /**
     * Die Methode nimmt die produktId Key aus index.jsf setzt ein neues Produkt fuer produktAendernBean
     * und leitet weiter zu produktAendern.jsf
     * 
     * @return "produktAendern.jsf"
     */
    public String aendern() {
        try {
            produktAendernBean.setProdukt(produktService.getProduktById(getId()));
        } catch (BRZException e) {
            logger.error("Error in aendern()" + e);
            return "errorSeite.jsf";
        }   
        return "produktAendern.jsf";
    }
       
    /**
     * Die Methode loescht das Produkt aus der Datenbank.
     * 
     * @return "loeschenProdukt.jsf"
     */
    public String loeschen() {
        Produkt produkt;
        try {
            produkt = produktService.getProduktById(getId());
            produktService.delete(produkt);
        } catch (BRZException e) {
            logger.error("Error in loeschen()" + e);
            return "errorSeite.jsf";
        }        
        return "loeschenProdukt.jsf";
    }
    
    /**
     * Hilfsmethode gibt den bestimmten Datenbank-ID aus index.jsf
     * 
     * @return lieferant
     */
    private int getId() {
        FacesContext facexcontext = FacesContext.getCurrentInstance();
        Map<String, String> requestParameterMap = facexcontext.getExternalContext().getRequestParameterMap();

        String pId = requestParameterMap.get("pid");
        int id = Integer.valueOf(pId);
        
        return id;
    }
    
    
    /**
     * Diese Methode konvertiert die Lieferant-ID auf Lieferantname, der in der Produkttabelle aufgezeigt wird.
     * @return
     */
    private List<Produkt> updateProdukteMitLieferantnamen() {
        List<Lieferant> lieferantliste = lieferantenBean.getLieferanten();
        for (int i = 0; i < produkte.size(); i++) {
            for (int j = 0; j < lieferantliste.size(); j++) {
                if (produkte.get(i).getLid() == lieferantliste.get(j).getLid()) {
                    produkte.get(i).setProduktLieferant(lieferantliste.get(j).getLieferantName());
                }
            }
        }
        return produkte;
    }
}

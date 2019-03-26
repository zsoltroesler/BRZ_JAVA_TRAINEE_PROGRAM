package at.gv.brz.services;

import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.gv.brz.daos.LieferantDAO;
import at.gv.brz.entitaeten.Lieferant;
import at.gv.brz.exceptions.BRZException;

/**
 * @author roeslerz
 * 
 * Service-Klasse fuer {@LieferantDAO}}
 */
@Service
public class LieferantService {

    private static Logger logger = LogManager.getLogger(LieferantService.class);

    @Autowired
    private LieferantDAO lieferantDAO;

    private List<Lieferant> lieferantliste;

    public List<Lieferant> getLieferantListe() throws BRZException {
        List<Lieferant> lieferante = null; 
        try {
            lieferante = lieferantDAO.findAll();
        } catch (Exception e) {
            logger.error("Error in getLieferantListe()", e);
            throw new BRZException(e);
        }
        return lieferante;
    }

    public Lieferant getLieferantById(int id) throws BRZException {
        Lieferant lieferant = null;
        try {
            lieferant = lieferantDAO.findById(id); 
        } catch (Exception e) {
            logger.error("Error in getLieferantById()", e);
            throw new BRZException(e);
        }
        return lieferant;
    }

    public void insert(Lieferant lieferant) throws BRZException {
        try {
            lieferantDAO.insert(lieferant);
        } catch (Exception e) {
            logger.error("Error in insert()", e);
            throw new BRZException(e);
        }
    }

    public void update(Lieferant lieferant) throws BRZException {
        try {
            lieferantDAO.update(lieferant);
        } catch (Exception e) {
            logger.error("Error in update()", e);
            throw new BRZException(e);
        }
    }

    public void delete(Lieferant lieferant) throws BRZException {
        try {
            lieferantDAO.delete(lieferant);
        } catch (Exception e) {
            logger.error("Error in delete()", e);
            throw new BRZException(e);
        }
    }

    /**
     * Liefert ein SelectItem[] Objekt, was "lid" zu "lieferantName" mappt und 
     * in produktEingabe.xhtml und produktAendern.xhtml als Drop-down-Liste für
     * Produktlieferant verwendet wird
     * 
     * @return selectItems
     * @throws BRZException 
     */
    public SelectItem[] mapIdtoName() throws BRZException {
        lieferantliste = getLieferantListe();
        SelectItem[] selectItems = new SelectItem[lieferantliste.size()];
        for (int i = 0; i < selectItems.length; i++) {
            SelectItem item = new SelectItem();
            item.setLabel(lieferantliste.get(i).getLieferantName());
            item.setValue(lieferantliste.get(i).getLid());
            selectItems[i] = item;
        }
        return selectItems;
    }

    /**
     * Diese Methode prueft ob die Lieferant-ID schon existiert oder nicht
     * 
     * @param lieferantName
     * @return true wenn neu, false wenn sie schon existiert.
     */
    public boolean validateLieferantId(String lieferantId) {
        String lieferantIdformatiert = lieferantId.toLowerCase().trim();
        logger.info("validateLieferantId " + lieferantId + "formatiert: " + lieferantIdformatiert);

        if (lieferantliste == null || lieferantliste.size() == 0) {
            return true;
        }

        for (Lieferant l : lieferantliste) {
            if (l.getLieferantId().toLowerCase().trim().equals(lieferantIdformatiert))
                return false;
        }
        return true;
    }
}

package at.gv.brz.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.gv.brz.daos.ProduktDAO;
import at.gv.brz.entitaeten.Produkt;
import at.gv.brz.exceptions.BRZException;

/**
 * @author roeslerz
 *
 * Service-Klasse fuer {@ProduktDAO}
 */
@Service
public class ProduktService {

    private static Logger logger = LogManager.getLogger(ProduktService.class);

    @Autowired
    private ProduktDAO produktDAO;
    
    public List<Produkt> getProduktListe() throws BRZException {
        List<Produkt> produkte = null;
        try {
           produkte = produktDAO.findAll();
        } catch (Exception e) {
            logger.error("Error in getProduktListe()", e);
            throw new BRZException(e);
        }
        return produkte;
    }

    public Produkt getProduktById(int id) throws BRZException {
        Produkt produkt = null;
        try {
            produkt = produktDAO.findById(id);
        } catch (Exception e) {
            logger.error("Error in getProduktById()", e);
            throw new BRZException(e);
        }
        return produkt;
    }

    public void insert(Produkt produkt) throws BRZException {
        try {
            produktDAO.insert(produkt);
        } catch (Exception e) {
            logger.error("Error in insert()", e);
            throw new BRZException(e);
        }
    }

    public void update(Produkt produkt) throws BRZException {
        try {
            produktDAO.update(produkt);
        } catch (Exception e) {
            logger.error("Error in update()", e);
            throw new BRZException(e);
        }
    }

    public void delete(Produkt produkt) throws BRZException {
        try {
            produktDAO.delete(produkt);
        } catch (Exception e) {
            logger.error("Error in delete()", e);
            throw new BRZException(e);
        }
    }

    /**
     * Diese Methode prueft ob die ProduktId schon existiert oder nicht
     * 
     * @param produktId
     * @return true wenn neu, false wenn sie schon existiert.
     * @throws BRZException 
     */
    public boolean validateProduktId(String produktId) throws BRZException {
        String produktIdformatiert = produktId.toLowerCase().trim();
        List<Produkt> produktliste = getProduktListe();
        logger.info("validateProduktId() " + produktId + "formatiert: " + produktIdformatiert);

        if (produktliste == null || produktliste.size() == 0) {
            return true;
        }

        for (Produkt p : produktliste) {
            if (p.getProduktId().toLowerCase().trim().equals(produktIdformatiert))
                return false;
        }
        return true;
    }  
}

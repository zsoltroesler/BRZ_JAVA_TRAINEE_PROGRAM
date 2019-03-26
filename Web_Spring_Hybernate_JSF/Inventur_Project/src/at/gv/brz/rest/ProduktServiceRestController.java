package at.gv.brz.rest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.gv.brz.entitaeten.Produkt;
import at.gv.brz.exceptions.BRZException;
import at.gv.brz.services.ProduktService;

/**
 * REST API Service
 * 
 * @author roeslerz
 *
 */
@RestController
@RequestMapping
public class ProduktServiceRestController {
    
    private static Logger logger = LogManager.getLogger(ProduktServiceRestController.class);

    @Autowired
    private ProduktService produktService;
    
    // http://localhost:8080/P2_Inventur_Project/api/produkte
    @RequestMapping("/produkte")
    public List<Produkt> getProdukte() {
        try {
            return produktService.getProduktListe();
        } catch (BRZException e) {
            logger.error("getProdukte()" + e);
            return null;
        }
    }
    
    // http://localhost:8080/P2_Inventur_Project/api/produkte/1
    @RequestMapping("/produkte/{pid}")
    public Produkt getProdukt(@PathVariable("pid") Integer pid) {
        try {
            return produktService.getProduktById(pid);
        } catch (BRZException e) {
            logger.error("getProdukt()" + e);
            return null;
        }
    }
    
    // http://localhost:8080/P2_Inventur_Project/api/updateprodukt
    @PutMapping("/updateprodukt")
    public void updateProdukt(@RequestBody Produkt produkt) {
        try {
            produktService.update(produkt);
        } catch (BRZException e) {
            logger.error("updateProdukt()" + e);
        }
    }
    
    // http://localhost:8080/P2_Inventur_Project/api/insertprodukt
    @PostMapping("/insertprodukt")
    public void insertProdukt(@RequestBody Produkt produkt) {
        try {
            produktService.insert(produkt);
        } catch (BRZException e) {
            logger.error("insertProdukt()" + e);
        }
    }
    
    // http://localhost:8080/P2_Inventur_Project/api/deleteprodukt
    @DeleteMapping("/deleteprodukt")
    public void deleteProdukt(@RequestBody Produkt produkt) {
        try {
            produktService.delete(produkt);
        } catch (BRZException e) {
            // TODO Auto-generated catch block
            logger.error("deleteProdukt()" + e);
        }
    }
    
}

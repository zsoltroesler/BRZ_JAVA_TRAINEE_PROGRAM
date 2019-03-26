package at.gv.brz.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import at.gv.brz.exceptions.BRZException;
import at.gv.brz.services.ProduktService;

public class TestProduktService {
    
    private static Logger logger = LogManager.getLogger(TestProduktService.class);
    
    static ClassPathXmlApplicationContext classPathXmlApplicationContext;
    static ProduktService produktService;
    
    @BeforeAll
    static void startContext() {
        classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContextTest.xml");
        produktService = classPathXmlApplicationContext.getBean(ProduktService.class);
    }
    
    @Test
    @DisplayName("Test fuer Methode validateProduktId()")
    void validateProdukt() {  
        try {
            assertTrue(produktService.validateProduktId("Z001"));
        } catch (BRZException e) {
            logger.error("Error in validateProdukt()", e);
        }  
    }
    
    @Test
    @DisplayName("Test fuer Methode getProduktListe()")
    void testProdukte() {           
        try {
            assertNotNull(produktService.getProduktListe());
        } catch (BRZException e) {
            logger.error("Error in testProdukte()", e);
        }        
    }
    
    @Test
    @DisplayName("Test fuer Methode getProduktById()")
    void testProduktById() {          
        try {
            assertNull(produktService.getProduktById(99));
        } catch (BRZException e) {
            logger.error("Error in testProduktById()", e);
        }        
    }
}

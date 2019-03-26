package at.gv.brz.tests;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import at.gv.brz.exceptions.BRZException;
import at.gv.brz.services.LieferantService;

public class TestLieferantService {
    
    static ClassPathXmlApplicationContext classPathXmlApplicationContext;
    static LieferantService lieferantService;
    
    @BeforeAll
    static void startContext() {
        classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContextTest.xml");
        lieferantService = classPathXmlApplicationContext.getBean(LieferantService.class);
    }
    
    @Test
    @DisplayName("Test fuer Methode validateLieferantId()")
    void validateLieferant() {
        assertTrue(lieferantService.validateLieferantId("L99999"));
    }
    
    @Test
    @DisplayName("Test fuer Methode getLieferantListe()")
    void testLieferante() {           
        try {
            assertNotNull(lieferantService.getLieferantListe());
        } catch (BRZException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }
    
    @Test
    @DisplayName("Test fuer Methode getLieferantById()")
    void testLieferantById() {          
        try {
            assertNull(lieferantService.getLieferantById(99));
        } catch (BRZException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }

}

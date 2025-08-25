package org.loamok.cave_avis.entity;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.loamok.cave_avis.repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author symio
 */
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ClientTest {
    
    @Autowired
    AvisRepository avisRepository;
    
    public ClientTest() {
    }
    
    private void cleanDb() {
        avisRepository.deleteAll();
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of builder method, of class Client.
     */
    @Test
    public void testBuilder() {
        log.info("builder");
        Client expResult = new Client();
        Client result = Client.builder().build();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPseudo method, of class Client.
     */
    @Test
    public void testGetPseudo() {
        log.info("getPseudo");
        Client instance = new Client();
        String expResult = null;
        String result = instance.getPseudo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuantiteCommandee method, of class Client.
     */
    @Test
    public void testGetQuantiteCommandee() {
        System.out.println("getQuantiteCommandee");
        Client instance = new Client();
        int expResult = 0;
        int result = instance.getQuantiteCommandee();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPseudo method, of class Client.
     */
    @Test
    public void testSetPseudo() {
        System.out.println("setPseudo");
        String pseudo = "";
        Client instance = new Client();
        instance.setPseudo(pseudo);
    }

    /**
     * Test of setQuantiteCommandee method, of class Client.
     */
    @Test
    public void testSetQuantiteCommandee() {
        System.out.println("setQuantiteCommandee");
        int quantiteCommandee = 0;
        Client instance = new Client();
        instance.setQuantiteCommandee(quantiteCommandee);
    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        Client instance = new Client();
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of canEqual method, of class Client.
     */
    @Test
    public void testCanEqual() {
        System.out.println("canEqual");
        Object other = null;
        Client instance = new Client();
        boolean expResult = false;
        boolean result = instance.canEqual(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Client.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Client instance = new Client();
        String expResult = "Client(pseudo=null, quantiteCommandee=0)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    @Test
    void test01_save_avis() {
        cleanDb();
        
        Avis avis = Avis
                .builder()
                .note(4)
                .commentaire("UBY BIO N°21, IGP Côtes de Gascogne excellent Sauvignon !")
                .date(LocalDateTime.now())
                .client(Client.builder()
                        .pseudo("Plop")
                        .quantiteCommandee(2)
                        .build())
                .build();
        Avis avisDB = avisRepository.save(avis);

        //Vérifier que l'identifiant n'est pas null
        assertThat(avisDB.getId()).isNotNull();
        assertThat(avisDB.getNote()).isEqualTo(4);
        assertThat(avisDB.getCommentaire()).isNotNull();
        assertThat(avisDB.getCommentaire()).isNotBlank();
        assertThat(avisDB.getDate()).isNotNull();
        assertThat(avisDB.getClient()).isNotNull();
        assertThat(avisDB.getClient().getPseudo()).isEqualTo("Plop");
        assertThat(avisDB.getClient().getQuantiteCommandee()).isEqualTo(2);

        log.info(avisDB.getClient().toString());
    }
    
}

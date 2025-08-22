package org.loamok.cave_avis.entity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
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
public class AvisTest {
    
    @Autowired
    AvisRepository avisRepository;
    
    public AvisTest() {
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
    
    @Test
    void test01_save_avis() {
        cleanDb();
        
        Avis avis = Avis
                .builder()
                .note(4)
                .commentaire("UBY BIO N°21, IGP Côtes de Gascogne excellent Sauvignon !")
                .date(LocalDateTime.now())
                .build();
        Avis avisDB = avisRepository.save(avis);

        //Vérifier que l'identifiant n'est pas null
        assertThat(avisDB.getId()).isNotNull();
        assertThat(avisDB.getNote()).isEqualTo(4);
        assertThat(avisDB.getCommentaire()).isNotNull();
        assertThat(avisDB.getCommentaire()).isNotBlank();
        assertThat(avisDB.getDate()).isNotNull();

        log.info(avisDB.toString());
    }

    @Test
    void test02_findAll_avis() {
        List<Avis> listeAvis = avisRepository.findAll();
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isEqualTo(1);
        log.info(listeAvis.toString());
    }
    
}

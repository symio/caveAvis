package org.loamok.cave_avis.entity.vin;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;


import org.loamok.cave_avis.repository.vin.BouteilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author symio
 */
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class BouteilleTest {

    @Autowired
    BouteilleRepository bouteilleRepository;
    
    public BouteilleTest() {
    }
    
    private void cleanDb() {
        bouteilleRepository.deleteAll();
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
    
    private void runTheTests(Bouteille b) {
        assertThat(b.getIdCouleur()).isNotNull();
        assertThat(b.getIdCouleur()).isEqualTo(1);
        assertThat(b.getIdRegion()).isNotNull();
        assertThat(b.getIdRegion()).isEqualTo(5);
        assertThat(b.getNom()).isEqualTo("UBY BIO N°21");

        log.info(b.toString());
    
    }
    
    @Test
    public void insertOneBottleTest() {
        cleanDb();
        final Bouteille bottle = Bouteille.builder()
            .idBouteille(2298)
            .idCouleur(1)
            .idRegion(5)
            .nom("UBY BIO N°21")
            .build()
        ;
        
        final Bouteille bDb = bouteilleRepository.save(bottle);
        runTheTests(bDb);
    }
    
    @Test
    public void insertOneBottleSecondMethodTest() {
        cleanDb();
        final Integer bid = 2298;
        
        final Bouteille bottle = Bouteille.builder()
            .idBouteille(bid)
            .idCouleur(1)
            .idRegion(5)
            .nom("UBY BIO N°21")
            .build()
        ;
        
        bouteilleRepository.save(bottle);
        
        Optional<Bouteille> bDb2 = bouteilleRepository.findById(bid);
        assertThat(bDb2).isNotNull();
        assertThat(bDb2.isPresent()).isTrue();
        
        runTheTests(bDb2.get());
        
    }
    
}

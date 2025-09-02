package org.loamok.cave_avis.entity;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.loamok.cave_avis.entity.vin.Bouteille;
import org.loamok.cave_avis.repository.AvisRepository;
import org.loamok.cave_avis.repository.vin.BouteilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author symio
 */
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class AvisBouteilleTest {

    @Autowired
    AvisRepository avisRepository;
    @Autowired
    BouteilleRepository bouteilleRepository;

    public AvisBouteilleTest() {
    }

    private void cleanDb() {
        avisRepository.deleteAll();
    }

    @Test
    void test01_save_avis() {
        cleanDb();
        
        final int bid = 2298;
        
        Optional<Bouteille> bDb2 = bouteilleRepository.findById(bid);
        assertThat(bDb2).isNotNull();
        assertThat(bDb2.isPresent()).isTrue();
        
        final Bouteille b1 = bDb2.get();

        Avis avis = Avis
                .builder()
                .note(4)
                .commentaire("UBY BIO N°21, IGP Côtes de Gascogne excellent Sauvignon !")
                .date(LocalDateTime.now())
                .client(Client.builder()
                        .pseudo("Plop")
                        .quantiteCommandee(2)
                        .build())
                .bouteille(b1)
                .build();
        Avis avisDB = avisRepository.save(avis);

        final Optional<Avis> aDB2 = avisRepository.findById(avisDB.getId());
        assertThat(aDB2).isNotNull();
        assertThat(aDB2.isPresent()).isTrue();
        
        final Avis a = aDB2.get();
        //Vérifier que l'identifiant n'est pas null
        assertThat(a.getId()).isNotNull();
        assertThat(a.getNote()).isEqualTo(4);
        assertThat(a.getCommentaire()).isNotNull();
        assertThat(a.getCommentaire()).isNotBlank();
        assertThat(a.getDate()).isNotNull();
        assertThat(a.getClient()).isNotNull();
        assertThat(a.getClient().getPseudo()).isEqualTo("Plop");
        assertThat(a.getClient().getQuantiteCommandee()).isEqualTo(2);

        log.info(a.getClient().toString());
        // Bouteille
        assertThat(a.getBouteille()).isNotNull();
        assertThat(a.getBouteille().getIdBouteille()).isNotNull();
        
        final Bouteille b = a.getBouteille();
        
        assertThat(b.getIdCouleur()).isNotNull();
        assertThat(b.getIdCouleur()).isEqualTo(1);
        assertThat(b.getIdRegion()).isNotNull();
        assertThat(b.getIdRegion()).isEqualTo(5);
        assertThat(b.getNom()).isEqualTo("UBY BIO N°21");

        log.info(b.toString());

    }

}

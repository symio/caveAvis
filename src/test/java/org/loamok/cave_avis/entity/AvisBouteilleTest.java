package org.loamok.cave_avis.entity;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.loamok.cave_avis.entity.vin.Bouteille;
import org.loamok.cave_avis.entity.vin.BouteilleId;
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
        
        final BouteilleId bid = BouteilleId.builder()
                .idBouteille(2298)
                .idCouleur(1)
                .idRegion(5)
                .build();
        
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
        assertThat(a.getBouteille().getId()).isNotNull();
        
        final Bouteille b = a.getBouteille();
        
        assertThat(b.getId()).isExactlyInstanceOf(BouteilleId.class);
        assertThat(b.getId().getIdCouleur()).isNotNull();
        assertThat(b.getId().getIdCouleur()).isEqualTo(1);
        assertThat(b.getId().getIdRegion()).isNotNull();
        assertThat(b.getId().getIdRegion()).isEqualTo(5);
        assertThat(b.getNom()).isEqualTo("UBY BIO N°21");

        log.info(b.toString());

    }

}

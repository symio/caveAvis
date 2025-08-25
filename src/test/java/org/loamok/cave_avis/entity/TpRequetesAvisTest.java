package org.loamok.cave_avis.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
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
public class TpRequetesAvisTest {

    public TpRequetesAvisTest() {
    }

    @Autowired
    private AvisRepository avisR;
    @Autowired
    private BouteilleRepository bouteilleR;

    void insertion_Bouteille_DB() {
        final List<Bouteille> listeBouteilles = new ArrayList<>();
        // Création de 3 Bouteille

        listeBouteilles.add(Bouteille
                .builder()
                .id(BouteilleId
                        .builder()
                        .idBouteille(18298)
                        .idRegion(3)
                        .idCouleur(1)
                        .build())
                .nom("Vin ENI Edition")
                .build());

        listeBouteilles.add(Bouteille
                .builder()
                .id(BouteilleId
                        .builder()
                        .idBouteille(1298)
                        .idRegion(3)
                        .idCouleur(2)
                        .build())
                .nom("Vin ENI Service")
                .build());

        listeBouteilles.add(Bouteille
                .builder()
                .id(BouteilleId
                        .builder()
                        .idBouteille(1999)
                        .idRegion(2)
                        .idCouleur(3)
                        .build())
                .nom("Vin ENI Ecole")
                .build());

        listeBouteilles.forEach(b -> {
            bouteilleR.save(b);
        });
    }

    void insertion_Avis_DB() {
        // Récupération depuis la base des Bouteille
        final List<Bouteille> listeBouteilles = bouteilleR.findAll();
        assertThat(listeBouteilles).isNotNull();
        assertThat(listeBouteilles).isNotEmpty();
        assertThat(listeBouteilles.size()).isEqualTo(3);

        // Liste de Client
        final List<Client> listeClients = new ArrayList<>();
        // Création de 3 Client
        listeClients.add(Client
                .builder()
                .pseudo("bobeponge@email.fr")
                .quantiteCommandee(11)
                .build());
        listeClients.add(Client
                .builder()
                .pseudo("patricketoile@email.fr")
                .quantiteCommandee(12)
                .build());
        listeClients.add(Client
                .builder()
                .pseudo("carlotentacule@email.fr")
                .quantiteCommandee(25)
                .build());

        // Ajout d'Avis par Client sur chaque Bouteille
        // Faire varier la note
        int note = 2;

        for (Client c : listeClients) {
            //Faire varier la date :
            LocalDateTime ldf = LocalDateTime.of(2023, 7, 13, 15, 28);
            //Attention, en base l'heure sera en GMT (Heure Française - 2)

            for (int i = 0; i < listeBouteilles.size(); i++) {
                final Bouteille b = listeBouteilles.get(i);

                // Faire varier la quantite du Client selon la note
                c.setQuantiteCommandee(c.getQuantiteCommandee() * note);
                final Avis avis = Avis
                        .builder()
                        .note(note)
                        .commentaire("Commentaire (" + note + ")")
                        .bouteille(b)
                        .client(c)
                        .date(ldf)
                        .build();
                // Sauvegarde de Avis
                avisR.save(avis);
                // incrémenter la date
                ldf = ldf.plusDays(10);
            }
            // incrémenter la note
            note++;
        }
    }

    @Test
    public void a00initDBTest() {
        avisR.deleteAll();
        bouteilleR.deleteAll();
        insertion_Bouteille_DB();
        insertion_Avis_DB();
    }

    @Test
    public void a01FindByNoteLessThanTest() {
        final List<Avis> avis = avisR.findByNoteLessThan(3);
        log.info("Nb Avis : " + avis.size());

        assertThat(avis).isNotNull();
        assertThat(avis).isNotEmpty();
        assertThat(avis.size()).isEqualTo(3);
        log.info(avis.toString());

    }

    @Test
    public void a02FindByNoteGreaterThanEqualTest() {
        final List<Avis> avis = avisR.findByNoteGreaterThanEqual(3);
        log.info("Nb Avis : " + avis.size());

        assertThat(avis).isNotNull();
        assertThat(avis).isNotEmpty();
        assertThat(avis.size()).isEqualTo(6);
        log.info(avis.toString());

    }

    @Test
    public void a04FindByBouteilleTest() {
        Bouteille b = Bouteille.builder()
                .id(BouteilleId
                        .builder()
                        .idBouteille(18298)
                        .idRegion(3)
                        .idCouleur(1)
                        .build())
                .build();

        final List<Avis> avis = avisR.findByBouteille(b);
        log.info("Nb Avis : " + avis.size());

        assertThat(avis).isNotNull();
        assertThat(avis).isNotEmpty();
        assertThat(avis.size()).isEqualTo(3);
        log.info(avis.toString());

    }

    @Test
    public void a04FindByClientTest() {
        final List<Avis> avis = avisR.findByClientPseudo("bobeponge@email.fr");
        log.info("Nb Avis : " + avis.size());

        assertThat(avis).isNotNull();
        assertThat(avis).isNotEmpty();
        assertThat(avis.size()).isEqualTo(3);
        log.info(avis.toString());

    }

    @Test
    public void a05FindByClientQuantiteCommandeeTest() {
        final List<Avis> avis = avisR.findByClientQuantiteCommandee(100);
        log.info("Nb Avis : " + avis.size());

        assertThat(avis).isNotNull();
        assertThat(avis).isNotEmpty();
        assertThat(avis.size()).isEqualTo(1);
        log.info(avis.toString());

    }

    @Test
    void a06FindByDateBetween() {
        LocalDateTime debut = LocalDateTime.of(2023, 7, 13, 12, 28);
        LocalDateTime fin = LocalDateTime.of(2023, 7, 31, 15, 28);
        List<Avis> avis = avisR.findByDateBetween(debut, fin);
        log.info("Nb Avis : " + avis.size());
        
        assertThat(avis).isNotNull();
        assertThat(avis).isNotEmpty();
        assertThat(avis.size()).isEqualTo(6);
        log.info(avis.toString());
    }
}

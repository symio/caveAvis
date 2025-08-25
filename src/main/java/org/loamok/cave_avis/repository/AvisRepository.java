package org.loamok.cave_avis.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.loamok.cave_avis.entity.Avis;
import org.loamok.cave_avis.entity.Client;
import org.loamok.cave_avis.entity.vin.Bouteille;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author symio
 */
public interface AvisRepository extends MongoRepository<Avis, String> {
    List<Avis> findByNoteLessThan(@Param("note") int note);
    List<Avis> findByNoteGreaterThanEqual(@Param("note") int note);
    List<Avis> findByBouteille(@Param("bouteille") Bouteille bouteille);
    List<Avis> findByClientPseudo(@Param("pseudo") String pseudo);
    List<Avis> findByClientQuantiteCommandee(@Param("quantiteCommandee") int quantiteCommandee);
    List<Avis> findByDateBetween(@Param("debut") LocalDateTime debut, @Param("fin") LocalDateTime fin);
}

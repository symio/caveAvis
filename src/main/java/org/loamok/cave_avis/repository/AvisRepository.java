package org.loamok.cave_avis.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.loamok.cave_avis.entity.Avis;
import org.loamok.cave_avis.entity.vin.Bouteille;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author symio
 */
@RepositoryRestResource(collectionResourceRel = "avis", path = "avis")
public interface AvisRepository extends MongoRepository<Avis, String> {
    List<Avis> findByNoteLessThan(@Param("note") int note);
    List<Avis> findByNoteGreaterThanEqual(@Param("note") int note);
    List<Avis> findByBouteille(@Param("bouteille") Bouteille bouteille);
    List<Avis> findByClientPseudo(@Param("pseudo") String pseudo);
    List<Avis> findByClientQuantiteCommandee(@Param("quantiteCommandee") int quantiteCommandee);
//    List<Avis> findByDateBetween(@Param("debut") LocalDateTime debut, @Param("fin") LocalDateTime fin);
    List<Avis> findByDateBetween(
        @Param("deb") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deb,
        @Param("fin")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin);
}

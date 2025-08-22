package org.loamok.cave_avis.repository.vin;

import org.loamok.cave_avis.entity.vin.Bouteille;
import org.loamok.cave_avis.entity.vin.BouteilleId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author symio
 */
public interface BouteilleRepository extends MongoRepository<Bouteille, BouteilleId> {}

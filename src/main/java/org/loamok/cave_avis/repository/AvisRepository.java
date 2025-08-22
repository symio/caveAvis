package org.loamok.cave_avis.repository;

import org.loamok.cave_avis.entity.Avis;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author symio
 */
public interface AvisRepository extends MongoRepository<Avis, String> {}

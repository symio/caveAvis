package org.loamok.cave_avis.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.loamok.cave_avis.entity.vin.Bouteille;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author symio
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "reviews")
public class Avis {
    @Id
    private String id;
    @Field(name = "note")
    private int note;
    @Field(name = "commentary")
    private String commentaire;
    @Field(name = "date")
    private LocalDateTime date;
    
    private Client client;
    @DBRef
    @Field(name = "bottle_id")
    private Bouteille bouteille;
}

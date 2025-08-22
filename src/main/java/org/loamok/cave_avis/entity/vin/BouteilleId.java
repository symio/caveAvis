package org.loamok.cave_avis.entity.vin;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author symio
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

public class BouteilleId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Field(name = "bottle_id")
    private int idBouteille;
    @Field(name = "region_id")
    private int idRegion;
    @Field(name = "color_id")
    private int idCouleur;
    
}

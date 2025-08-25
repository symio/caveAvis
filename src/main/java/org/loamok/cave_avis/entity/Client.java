package org.loamok.cave_avis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author symio
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Client {
    @Field
    private String pseudo;
    @Field
    private int quantiteCommandee;
}

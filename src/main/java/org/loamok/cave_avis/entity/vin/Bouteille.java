/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.loamok.cave_avis.entity.vin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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

@Document(collection = "bottles")
public class Bouteille {
    @Id
    @Field(name = "bottle_id")
    private int idBouteille;
    @Field(name = "region_id")
    private int idRegion;
    @Field(name = "color_id")
    private int idCouleur;
    
    @Field(name = "name")
    private String nom;
}

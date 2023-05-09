/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pz.motomoto.Klasy.Ogloszenie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pz.motomoto.Klasy.RegularUser.RegularUser;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 *
 * @author Majkel
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ogloszenie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Date data_dodania;
    private boolean aktywne;
    private String nazwa;
    private String vin;
    private String nrRej;
    private String marka;
    private String model;
    private String kolor;
    private String typ;
    private String paliwo;
    private Long przebieg;
    private Integer moc;
    private Integer pojemnosc;
    private double cena;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("ogloszenia") 
    private RegularUser uzytkownik;

    @Override
public String toString() {
    return "Ogloszenie{" +
            "id=" + id +
            ", data_dodania=" + data_dodania +
            ", aktywne=" + aktywne +
            ", nazwa='" + nazwa + '\'' +
            ", vin='" + vin + '\'' +
            ", nrRej='" + nrRej + '\'' +
            ", marka='" + marka + '\'' +
            ", model='" + model + '\'' +
            ", kolor='" + kolor + '\'' +
            ", typ='" + typ + '\'' +
            ", paliwo='" + paliwo + '\'' +
            ", przebieg=" + przebieg +
            ", moc=" + moc +
            ", pojemnosc=" + pojemnosc +
            ", cena=" + cena +
            '}';
}

}



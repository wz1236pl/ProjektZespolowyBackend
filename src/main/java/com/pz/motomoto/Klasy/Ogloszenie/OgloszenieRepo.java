/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pz.motomoto.Klasy.Ogloszenie;
import com.pz.motomoto.Klasy.RegularUser.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OgloszenieRepo extends JpaRepository<Ogloszenie, Long>{
    RegularUser findByNazwa(String Nazwa);
}
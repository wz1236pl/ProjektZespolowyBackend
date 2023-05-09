/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pz.motomoto.Klasy.RegularUser;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Majkel
 */
public interface RegularUserRepo extends JpaRepository<RegularUser, Long>{
    RegularUser findByEmail(String email);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pz.motomoto.Klasy.RegularUser;

import com.pz.motomoto.Klasy.Ogloszenie.Ogloszenie;
import com.pz.motomoto.Klasy.User.Role;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Majkel
 */

@Data
@EqualsAndHashCode(callSuper = true, exclude="ogloszenia")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegularUser extends com.pz.motomoto.Klasy.User.User {
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Ogloszenie> ulubione = new ArrayList<>();;
    @OneToMany(mappedBy="uzytkownik", fetch = FetchType.EAGER)
    private Set<Ogloszenie> ogloszenia = new HashSet<>();;
    
    public RegularUser(Long id, String email, String nick, String password, Role role, String phone, List<Ogloszenie> ulubione) {
        super(id, email, nick, password, role, phone);
        this.ulubione = ulubione != null ? ulubione : new ArrayList<>();
    } 
    
    
    
}

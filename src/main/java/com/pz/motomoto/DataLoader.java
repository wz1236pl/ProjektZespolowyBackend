/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pz.motomoto;

import com.pz.motomoto.Klasy.Ogloszenie.Ogloszenie;
import com.pz.motomoto.Klasy.Ogloszenie.OgloszenieRepo;
import com.pz.motomoto.Klasy.RegularUser.RegularUser;
import static com.pz.motomoto.Klasy.User.Role.USER;
import com.pz.motomoto.Klasy.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import com.pz.motomoto.Klasy.RegularUser.RegularUserRepo;
import com.pz.motomoto.Klasy.RegularUser.RegularUserRepo;
import java.util.Set;
import org.hibernate.Hibernate;

/**
 *
 * @author Majkel
 */
@Component
public class DataLoader implements ApplicationRunner{
    
    private RegularUserRepo regularUserRepo;
    private OgloszenieRepo ogloszenieRepo;
    
    @Autowired
    public DataLoader(RegularUserRepo userRepo, OgloszenieRepo ogloszenieRepo){
        this.ogloszenieRepo = ogloszenieRepo;
        this.regularUserRepo = userRepo;
    }
    @Override
    
public void run(ApplicationArguments args) {
    RegularUser szczepanski = new RegularUser();
    szczepanski.setEmail("szczepanskiamikolaj@gmail.com");
    szczepanski.setRole(USER);
    
    Ogloszenie audi = Ogloszenie.builder()
            .nazwa("Audi 80 B4 dobry stan")
            .build();
    
    Ogloszenie fiat = Ogloszenie.builder()
            .nazwa("Fiat 125")
            .build();
    regularUserRepo.save(szczepanski); 
    ogloszenieRepo.save(audi); 
    ogloszenieRepo.save(fiat);
    
    szczepanski.getOgloszenia().add(audi);
    szczepanski.getUlubione().add(fiat);
    audi.setUzytkownik(szczepanski); 
    
    regularUserRepo.save(szczepanski); 
    ogloszenieRepo.save(audi); 


    
    System.out.println("User: " + regularUserRepo.findByEmail("szczepanskiamikolaj@gmail.com"));
    System.out.println("Favorites: " + regularUserRepo.findByEmail("szczepanskiamikolaj@gmail.com").getUlubione());
    System.out.println("Ogloszenia: " + regularUserRepo.findByEmail("szczepanskiamikolaj@gmail.com").getOgloszenia());
}


}

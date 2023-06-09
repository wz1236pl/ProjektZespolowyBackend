package com.pz.motomoto.Controlers;

import java.time.OffsetDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pz.motomoto.Klasy.Ogloszenie.Ogloszenie;
import com.pz.motomoto.Klasy.Ogloszenie.OgloszenieRepo;
import com.pz.motomoto.Klasy.User.User;
import com.pz.motomoto.Klasy.User.UserRepo;
import com.pz.motomoto.Security.JwtService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class OgloszenieController {
    
    @Autowired
    UserRepo userRepo;
    @Autowired
    OgloszenieRepo ogloszenieRepo;
    @Autowired
    JwtService jwtService;

    @PostMapping("/addCar")
    public void addCar(Ogloszenie ogloszenie, HttpServletRequest servletRequest){
        final User user = getUser(servletRequest);
        if (user.isEnabled()){
            ogloszenie.setUzytkownik(user);
            ogloszenie.setDataDodania(OffsetDateTime.now());
            ogloszenieRepo.save(ogloszenie);
        }
    }

    @PutMapping("/updateCar")
    public void updateCar(Ogloszenie ogloszenie){
            ogloszenieRepo.save(ogloszenie);
    }

    @DeleteMapping("/deleteCar")
    public void deleteCar(Long id, HttpServletRequest servletRequest) {
        final User user = getUser(servletRequest);
        final Ogloszenie ogloszenie = ogloszenieRepo.findById(id).orElse(null);
        if(Objects.nonNull(user) && user.getId().equals(ogloszenie.getUzytkownik().getId())){
            ogloszenieRepo.delete(ogloszenie);
        }
    }

    public User getUser(HttpServletRequest servletRequest){
        final String authHeader = servletRequest.getHeader("Authorization");
        final String jwt = authHeader.substring(7);
        return userRepo.findByEmail(jwtService.extractUsername(jwt)).orElse(null);
    }

}

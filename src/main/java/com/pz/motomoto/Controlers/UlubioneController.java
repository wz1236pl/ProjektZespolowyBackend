package com.pz.motomoto.Controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pz.motomoto.Services.UserService;
import com.pz.motomoto.Klasy.Ogloszenie.Ogloszenie;
import com.pz.motomoto.Klasy.Ogloszenie.OgloszenieRepo;
import com.pz.motomoto.Klasy.User.User;
import com.pz.motomoto.Klasy.User.UserRepo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UlubioneController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    OgloszenieRepo ogloszenieRepo;
    
    @GetMapping("/getFavourites")
    public List<Ogloszenie> getFavourites (HttpServletRequest servletRequest){
        User user = userService.getUserFromJwt(servletRequest);
        return user.getUlubione();
    }

    @PostMapping("/addFavourites")
    public void addFavourites (HttpServletRequest servletRequest, Long id){
        User user = userService.getUserFromJwt(servletRequest);
        List<Ogloszenie> ulubione = user.getUlubione();
        ulubione.add(ogloszenieRepo.findById(id).orElse(null));
        user.setUlubione(ulubione);
        userRepo.save(user);
    }

    @DeleteMapping("/deleteFavourites")
    public void deleteFavourites (HttpServletRequest servletRequest, Long id){
        User user = userService.getUserFromJwt(servletRequest);
        List<Ogloszenie> ulubione = user.getUlubione();
        Ogloszenie ogloszenie = ogloszenieRepo.findById(id).orElse(null);
        ulubione.remove(ogloszenie);
        user.setUlubione(ulubione);
        userRepo.save(user);
    }
}

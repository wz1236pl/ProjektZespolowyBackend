package com.pz.motomoto.Controlers;

import org.springframework.web.bind.annotation.RestController;

import com.pz.motomoto.Klasy.Ogloszenie.Ogloszenie;
import com.pz.motomoto.Klasy.Ogloszenie.OgloszenieRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class GlownyController {
    @Autowired
    OgloszenieRepo ogloszenieRepo;

    @GetMapping("/all")
    public List<Ogloszenie> getAll() {
        return ogloszenieRepo.findAllByOrderByDataDodaniaAsc();
    }

}

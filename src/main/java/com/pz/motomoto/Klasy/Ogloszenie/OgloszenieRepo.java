package com.pz.motomoto.Klasy.Ogloszenie;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pz.motomoto.Klasy.User.User;

import java.util.List;

public interface OgloszenieRepo extends JpaRepository<Ogloszenie, Long>{
    List<Ogloszenie> findAllByUzytkownik(User uzytkownik);
    List<Ogloszenie> findAllByOrderByDataDodaniaAsc();
}
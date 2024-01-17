package com.pz.motomoto.Controlers;

import com.pz.motomoto.Klasy.Ogloszenie.Ogloszenie;
import com.pz.motomoto.Klasy.Ogloszenie.OgloszenieRepo;
import com.pz.motomoto.Klasy.User.User;
import com.pz.motomoto.Klasy.User.UserRepo;
import com.pz.motomoto.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class OgloszenieControllerIT {
    @Autowired
    OgloszenieController ogloszenieController;
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    OgloszenieRepo ogloszenieRepo;

    @BeforeEach
    void doBefore() {
        userRepo.deleteAll();
        ogloszenieRepo.deleteAll();
    }

    @Test
    void testAddCar() {
        userRepo.save(User.builder().email("aaa@aaa.aa").enabled(true).build());
        var ogloszenie = Ogloszenie.builder().id(1L).marka("BMW").model("x5").build();
        var token = "bearer:eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWFAYWFhLmFhIiwiaWF0IjoxNzA1NTE4NDI2LCJleHAiOjE3MDY3MjgwMjZ9.I7mJZ4YBO5UxGE-OUF9zDPBsd9_iZ-3vQg9k3Kwh9sE";
        var servlet = Mockito.mock(HttpServletRequest.class);
        Mockito.when(servlet.getHeader("Authorization")).thenReturn(token);

        ogloszenieController.addCar(ogloszenie, servlet);

        var noweOgloszenie = ogloszenieRepo.findById(1L).get();

        Assertions.assertEquals(ogloszenie.getId(), noweOgloszenie.getId());
        Assertions.assertEquals(ogloszenie.getMarka(), noweOgloszenie.getMarka());
        Assertions.assertEquals(ogloszenie.getModel(), noweOgloszenie.getModel());
    }
    @Test
    void testGetCar() {
        Ogloszenie ogloszenie = Ogloszenie.builder().id(1L).marka("BMW").model("x5").build();
        ogloszenieRepo.save(ogloszenie);

        Ogloszenie noweOgloszenie = ogloszenieController.getCar(1L);

        Assertions.assertEquals(ogloszenie.getId(), noweOgloszenie.getId());
        Assertions.assertEquals(ogloszenie.getMarka(), noweOgloszenie.getMarka());
    }

}

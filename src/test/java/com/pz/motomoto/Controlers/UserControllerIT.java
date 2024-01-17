package com.pz.motomoto.Controlers;

import com.pz.motomoto.Klasy.User.User;
import com.pz.motomoto.Klasy.User.UserRepo;
import com.pz.motomoto.Requests.EditPasswordRequest;
import com.pz.motomoto.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserControllerIT {
    @Autowired
    UserController userController;
    @Autowired
    private UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void doBefore() {
        userRepo.deleteAll();
    }

    @Test
    public void testEditUserPassword() {
        var email = "aaa@aaa.aa";
        var pass = "aaa";
        var newPass = "bbb";
        var token = "bearer:eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWFAYWFhLmFhIiwiaWF0IjoxNzA1NTE4NDI2LCJleHAiOjE3MDY3MjgwMjZ9.I7mJZ4YBO5UxGE-OUF9zDPBsd9_iZ-3vQg9k3Kwh9sE";
        var user = userRepo.save(User.builder().email(email).password(passwordEncoder.encode(pass)).build());

        var passwordRequest = EditPasswordRequest.builder()
                .email(email).oldPassword(pass).newPassword(newPass).confirmNewPassword(newPass).build();
        var servlet = Mockito.mock(HttpServletRequest.class);
        Mockito.when(servlet.getHeader("Authorization")).thenReturn(token);

        var response = userController.editUserPassword(passwordRequest, servlet);
        var editedUser = userRepo.findByEmail(email).get();

        Assertions.assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        Assertions.assertEquals(user.getEmail(), editedUser.getEmail());
        Assertions.assertTrue(passwordEncoder.matches(newPass, editedUser.getPassword()));
    }

    @Test
    public void testUserEnabledChange() {
        var email = "aaa@aaa.aa";
        var pass = "aaa";
        var token = "bearer:eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWFAYWFhLmFhIiwiaWF0IjoxNzA1NTE4NDI2LCJleHAiOjE3MDY3MjgwMjZ9.I7mJZ4YBO5UxGE-OUF9zDPBsd9_iZ-3vQg9k3Kwh9sE";
        var user = userRepo.save(User.builder().email(email).password(passwordEncoder.encode(pass)).enabled(true).build());

        var servlet = Mockito.mock(HttpServletRequest.class);
        Mockito.when(servlet.getHeader("Authorization")).thenReturn(token);

        userController.userEnabledChange(false, servlet);
        var editedUser = userRepo.findByEmail(email).get();

        Assertions.assertNotEquals(user.isEnabled(), editedUser.isEnabled());
        Assertions.assertFalse(editedUser.isEnabled());
    }


}
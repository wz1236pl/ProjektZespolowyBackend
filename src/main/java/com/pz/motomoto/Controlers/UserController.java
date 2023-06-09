package com.pz.motomoto.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pz.motomoto.Requests.EditPasswordRequest;
import com.pz.motomoto.Services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/password")
    public <T> ResponseEntity editUserPassword(@RequestBody EditPasswordRequest passwordRequest) {
        return ResponseEntity.ok(userService.changePassword(passwordRequest));
    }
    @PutMapping("/enabled")
    public void userEnabledChange(boolean enabled ) {

    }
}
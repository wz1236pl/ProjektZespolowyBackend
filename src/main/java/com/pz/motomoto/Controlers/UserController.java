package com.pz.motomoto.Controlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pz.motomoto.Requests.EditPasswordRequest;
import com.pz.motomoto.Responses.ResponseMessage;
import com.pz.motomoto.Services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/password")
    public ResponseEntity<ResponseMessage> editUserPassword(EditPasswordRequest passwordRequest) {
        return ResponseEntity.badRequest(userService.changePassword(passwordRequest));
    }
}
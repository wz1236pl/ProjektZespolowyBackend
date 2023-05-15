package com.pz.motomoto.Services;

import java.util.Optional;

import org.apache.catalina.User;
import org.apache.catalina.connector.Response;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pz.motomoto.Klasy.User.UserRepo;
import com.pz.motomoto.Requests.EditPasswordRequest;
import com.pz.motomoto.Responses.ResponseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public ResponseMessage changePassword(EditPasswordRequest passwordRequest) {
        if(passwordRequest.passwordMatch()) {
            if(passwordRequest.getOldPassword()!=null && !passwordRequest.getOldPassword().isEmpty()){
                final User user = userRepo.findByEmail(passwordRequest.getEmail());
                passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword());
                return ResponseMessage.builder().customResponseMessage("Pomyślnie zmieniono hasło").build();
            }
                
        }
        
    }

}

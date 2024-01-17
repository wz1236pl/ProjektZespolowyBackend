package com.pz.motomoto.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EditPasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    public boolean passwordMatch() {
        if (newPassword.matches(confirmNewPassword)) {
            return true;
        }
        return false;
    } 
}

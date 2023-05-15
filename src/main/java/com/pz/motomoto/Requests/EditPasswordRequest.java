package com.pz.motomoto.Requests;

import lombok.Data;

@Data
public class EditPasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    public boolean passwordMatch() {
        if (newPassword.equals(confirmNewPassword)) {
            return true;
        }
        return false;
    } 
}

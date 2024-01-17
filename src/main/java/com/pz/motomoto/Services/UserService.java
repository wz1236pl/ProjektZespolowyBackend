package com.pz.motomoto.Services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pz.motomoto.Klasy.User.User;
import com.pz.motomoto.Klasy.User.UserRepo;
import com.pz.motomoto.Requests.EditPasswordRequest;
import com.pz.motomoto.Security.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private static final HttpStatusCode HTTP_OK = HttpStatusCode.valueOf(200);
    private static final HttpStatusCode HTTP_BAD = HttpStatusCode.valueOf(400);


    /** 
     * @param passwordRequest
     * @return ResponseEntity
     */
    public <T> ResponseEntity changePassword(EditPasswordRequest passwordRequest) {
        if(Strings.isBlank(passwordRequest.getOldPassword()) || Strings.isBlank(passwordRequest.getNewPassword())){
            return createResponseEntity("Puste hasła!", HTTP_BAD);
        }
        if(!passwordRequest.passwordMatch()) {
            return createResponseEntity("Podane hasła są niepoprawne!", HTTP_BAD);
        }
        final User user = userRepo.findByEmail(passwordRequest.getEmail()).orElse(null);
        if(Objects.isNull(user)){
            return createResponseEntity("Nie znaleziono użytkownika w bazie!", HTTP_BAD);
        }
        if(passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())){
            return createResponseEntity("Podane hasło jest niepoprawne!", HTTP_BAD);
        }
        user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        userRepo.save(user);
        return createResponseEntity("Zmieniono hasło użytkownika: ".concat(user.getNick()), HTTP_OK);
    }


    /** 
     * @param msg
     * @param httpCode
     * @return ResponseEntity
     */
    private <T> ResponseEntity createResponseEntity(String msg, HttpStatusCode httpCode) {
        log.info(msg);
        return new ResponseEntity<T>((T) msg, httpCode);
    }

    public User getUserFromJwt(HttpServletRequest servletRequest){
        final String authHeader = servletRequest.getHeader("Authorization");
        System.out.println(authHeader);
        final String jwt = authHeader.substring(7);
        System.out.println(jwt);
        return userRepo.findByEmail(jwtService.extractUsername(jwt)).orElse(null);
    }

    public File generateUserListPdf() throws IOException{
        File resultFile = new File("UserList.pdf");
        ByteArrayOutputStream byteArrayOutputStream = createPDF();
        try(OutputStream outputStream = new FileOutputStream(resultFile)) {
            byteArrayOutputStream.writeTo(outputStream);
        }
        return resultFile;
    }

    private ByteArrayOutputStream createPDF() throws IOException {
        int x = 10;
        int y = 770;
        PDFont font = PDType1Font.COURIER;
        PDPageContentStream contentStream;
        ByteArrayOutputStream output =new ByteArrayOutputStream();
        PDDocument document =new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(font, 14);
        List<User> userList = userRepo.findAll();
        for(User u : userList){
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y-=20);
            contentStream.showText(u.userToPdf());
            contentStream.endText();

        }

        contentStream.close();

        document.save(output);
        document.close();
        return output;
    }

}
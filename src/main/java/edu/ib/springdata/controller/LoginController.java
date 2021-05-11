package edu.ib.springdata.controller;

import edu.ib.springdata.LoginUser;
import edu.ib.springdata.service.UserDtoService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class LoginController {

    private UserDtoService userDtoService;

    @Autowired
    public LoginController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    @PostMapping("/getToken")
    public String login(@RequestBody LoginUser loginUser) throws Exception {
        String role = userDtoService.findUser(loginUser.getName(), loginUser.getPassword());

            long currentTimeMillis = System.currentTimeMillis();
            return Jwts.builder()
                    .setSubject(loginUser.getName())
                    .claim("roles", role)
                    .setIssuedAt(new Date(currentTimeMillis))
                    .setExpiration(new Date(currentTimeMillis + 60000))
                    .signWith(SignatureAlgorithm.HS512, "abcj516s")
                    .compact();
    }
}

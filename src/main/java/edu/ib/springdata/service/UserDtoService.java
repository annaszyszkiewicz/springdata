package edu.ib.springdata.service;

import edu.ib.springdata.PasswordEncoderConfig;
import edu.ib.springdata.repository.UserDtoRepository;
import edu.ib.springdata.repository.entity.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class UserDtoService {

    private UserDtoRepository userDtoRepository;

    @Autowired
    public UserDtoService(UserDtoRepository userDtoRepository) {
        this.userDtoRepository = userDtoRepository;
    }

    public String findUser(String login, String pass) throws Exception {
        PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
        PasswordEncoder passwordEncoder = passwordEncoderConfig.passwordEncoder();
        Iterable<UserDto> iterable = userDtoRepository.findAll();
        Iterator<UserDto> iterator = iterable.iterator();
        String string = null;
        while (iterator.hasNext()) {
            UserDto userDto = iterator.next();
            if (userDto.getName().equals(login) && passwordEncoder.matches(pass, userDto.getPasswordHash()))
                string = userDto.getRole();
        }
        if (string == null)
            throw new Exception("Wrong login or password");
        return string;
    }
}

package edu.ib.springdata;


import edu.ib.springdata.repository.entity.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDtoBuilder {

    public UserDto setUser(User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        userDto.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        userDto.setRole(user.getRole());

        return userDto;
    }

}

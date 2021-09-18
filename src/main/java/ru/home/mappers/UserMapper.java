package ru.home.mappers;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.dto.UserDTO;
import ru.home.models.User;

@Component
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    public UserDTO EntityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setRole(user.getRole().toString());
        return userDTO;
    }

    public List<UserDTO> toUserDTOS(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setRole(String.valueOf(user.getRole()));
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
}

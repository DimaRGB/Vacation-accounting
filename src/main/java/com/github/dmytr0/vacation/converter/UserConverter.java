package com.github.dmytr0.vacation.converter;

import com.github.dmytr0.vacation.domain.User;
import com.github.dmytr0.vacation.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}

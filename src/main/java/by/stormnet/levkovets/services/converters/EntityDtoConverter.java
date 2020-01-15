package by.stormnet.levkovets.services.converters;

import by.stormnet.levkovets.domain.impl.User;
import by.stormnet.levkovets.dto.UserDto;

public class EntityDtoConverter {

    public EntityDtoConverter() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static UserDto transformToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public static User transformToUser(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());

        return user;
    }


}

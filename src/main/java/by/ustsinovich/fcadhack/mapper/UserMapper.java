package by.ustsinovich.fcadhack.mapper;

import by.ustsinovich.fcadhack.dto.UserDto;
import by.ustsinovich.fcadhack.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User user);

}

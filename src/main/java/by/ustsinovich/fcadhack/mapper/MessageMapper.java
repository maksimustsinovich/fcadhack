package by.ustsinovich.fcadhack.mapper;

import by.ustsinovich.fcadhack.dto.MessageDto;
import by.ustsinovich.fcadhack.entity.Message;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {
    Message toEntity(MessageDto messageDto);

    MessageDto toDto(Message message);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Message partialUpdate(MessageDto messageDto, @MappingTarget Message message);

    Message updateWithNull(MessageDto messageDto, @MappingTarget Message message);
}
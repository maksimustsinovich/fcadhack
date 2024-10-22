package by.ustsinovich.fcadhack.mapper;

import by.ustsinovich.fcadhack.dto.DataSourceDto;
import by.ustsinovich.fcadhack.entity.DataSourceEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DataSourceMapper {
    DataSourceEntity toEntity(DataSourceDto dataSourceDto);

    DataSourceDto toDto(DataSourceEntity dataSourceEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DataSourceEntity partialUpdate(DataSourceDto dataSourceDto, @MappingTarget DataSourceEntity dataSourceEntity);

    DataSourceEntity updateWithNull(DataSourceDto dataSourceDto, @MappingTarget DataSourceEntity dataSourceEntity);
}
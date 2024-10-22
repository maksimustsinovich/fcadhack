package by.ustsinovich.fcadhack.mapper;

import by.ustsinovich.fcadhack.dto.FilteringRuleDto;
import by.ustsinovich.fcadhack.entity.FilteringRuleEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FilteringRuleMapper {
    FilteringRuleEntity toEntity(FilteringRuleDto filteringRuleDto);

    FilteringRuleDto toDto(FilteringRuleEntity filteringRuleEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FilteringRuleEntity partialUpdate(FilteringRuleDto filteringRuleDto, @MappingTarget FilteringRuleEntity filteringRuleEntity);

    FilteringRuleEntity updateWithNull(FilteringRuleDto filteringRuleDto, @MappingTarget FilteringRuleEntity filteringRuleEntity);
}
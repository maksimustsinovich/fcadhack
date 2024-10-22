package by.ustsinovich.fcadhack.dto;

import lombok.Value;

/**
 * DTO for {@link by.ustsinovich.fcadhack.entity.FilteringRuleEntity}
 */
@Value
public class FilteringRuleDto {
    Long id;
    String regex;
    boolean status;
}
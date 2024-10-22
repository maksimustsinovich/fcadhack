package by.ustsinovich.fcadhack.dto;

import lombok.Value;

/**
 * DTO for {@link by.ustsinovich.fcadhack.entity.DataSourceEntity}
 */
@Value
public class DataSourceDto {
    Long id;
    String source;
    boolean status;
}
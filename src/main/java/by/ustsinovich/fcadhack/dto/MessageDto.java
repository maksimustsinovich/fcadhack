package by.ustsinovich.fcadhack.dto;

import lombok.*;

/**
 * DTO for {@link by.ustsinovich.fcadhack.entity.Message}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    Long id;
    String body;
}
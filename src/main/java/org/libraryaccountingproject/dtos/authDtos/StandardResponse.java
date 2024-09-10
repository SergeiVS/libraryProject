package org.libraryaccountingproject.dtos.authDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Message", description = "Server response")
public class StandardResponse {
    @Schema(description = "Possible error message, state changes and so on", example = "User not found")
    private String message;
}

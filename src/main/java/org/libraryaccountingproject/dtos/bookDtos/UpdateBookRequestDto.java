package org.libraryaccountingproject.dtos.bookDtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequestDto {

    @Positive
    @NotNull
    private Integer id;

    private String bookSubject;

    private String status;


}

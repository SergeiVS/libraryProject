package org.libraryaccountingproject.dtos.bookDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.libraryaccountingproject.dtos.authorDtos.AuthorDataResponseDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private Integer bookId;
    private String title;
    private Set<AuthorDataResponseDto> authors;
    private String codeISBN;
    private String subject;
    private String status;
    private String coverImageUrl;


}

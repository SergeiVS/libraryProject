package org.libraryaccountingproject.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.libraryaccountingproject.entities.BookSubject;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequestDto {

    private String bookTitle;
    private List<Integer> authorsIds;
    private String codeISBN;
    private String bookSubject;

}

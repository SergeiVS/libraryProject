package org.libraryaccountingproject.services.utils.converters;

import org.libraryaccountingproject.dtos.authorsJsonData.AuthorDataFromWebResponseDto;
import org.libraryaccountingproject.dtos.authorsJsonData.AuthorJSONData;
import org.springframework.stereotype.Component;

@Component
public class AuthorDataJsonToDto {

    public AuthorDataFromWebResponseDto fromAuthorJsonToDto(AuthorJSONData authorJSONData) {
        AuthorDataFromWebResponseDto authorData = new AuthorDataFromWebResponseDto();
        authorData.setKey(authorJSONData.getKey());
        authorData.setName(authorJSONData.getName());
        authorData.setText(authorJSONData.getText());
        authorData.setAlternateNames(authorJSONData.alternate_names);
        authorData.setTopSubjects(authorJSONData.top_subjects);
        authorData.setType(authorJSONData.getType());
        authorData.setBirthDate(authorJSONData.getBirth_date());
        authorData.setTopWork(authorJSONData.getTop_work());
        authorData.setWork_count(authorJSONData.getWork_count());
        return authorData;
    }
}

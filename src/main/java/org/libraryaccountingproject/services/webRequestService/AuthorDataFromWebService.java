package org.libraryaccountingproject.services.webRequestService;

import lombok.AllArgsConstructor;
import org.libraryaccountingproject.dtos.authorsJsonData.AuthorDataFromWebResponseDto;
import org.libraryaccountingproject.exeptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorDataFromWebService {
    private final AuthorDataWebAPI authorDataWebAPI;

    public List<AuthorDataFromWebResponseDto> getExtraAuthorDataFromWeb(String authorName) throws MalformedURLException, URISyntaxException {
        System.out.println(authorName);
        if(authorName.isBlank()){
            throw new IllegalArgumentException("Author name cannot be blank");
        }else {
            List<AuthorDataFromWebResponseDto> authorsReceived = authorDataWebAPI.getAuthorsDataFromWebAPI(authorName);
            System.out.println(authorsReceived);
            if(authorsReceived.isEmpty()){
                throw new NotFoundException("Authors not found");
            }else {
                return authorsReceived;
            }
        }
    }
}

package org.libraryaccountingproject.services.webRequestService;

import lombok.AllArgsConstructor;
import org.libraryaccountingproject.dtos.authorsJsonData.AuthorDataFromWebResponseDto;
import org.libraryaccountingproject.dtos.authorsJsonData.AuthorJSON;
import org.libraryaccountingproject.services.utils.converters.AuthorDataJsonToDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class AuthorDataWebAPI {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final String webUrl = "https://openlibrary.org/search/authors.json";
    private final RestTemplate restTemplate = new RestTemplate();
    private final AuthorDataJsonToDto converter = new AuthorDataJsonToDto();

    public List<AuthorDataFromWebResponseDto> getAuthorsDataFromWebAPI(String name) throws MalformedURLException, URISyntaxException {

        String urlRequest = parseRequestUrl(name);
        URL url = new URL(urlRequest);

        log.info("start request on url: " + url.toString());
        ResponseEntity<AuthorJSON> receivedAuthorJson = restTemplate.getForEntity(url.toString(), AuthorJSON.class);
        log.info("end request on url: " + url.toString());

        if (receivedAuthorJson.getBody() != null) {
            AuthorJSON authorJSON = receivedAuthorJson.getBody();

            return getListAuthorsFromWebDtos(authorJSON);

        } else {
            throw new IllegalArgumentException("No body found");
        }

    }

    private List<AuthorDataFromWebResponseDto> getListAuthorsFromWebDtos(AuthorJSON authorJson) {
        return authorJson.data.stream().map(converter::fromAuthorJsonToDto).toList();
    }

    private String parseRequestUrl(String name) {
        return UriComponentsBuilder.fromHttpUrl(webUrl)
                .path("?q={name}")
                .buildAndExpand(name).toUriString();
    }

}

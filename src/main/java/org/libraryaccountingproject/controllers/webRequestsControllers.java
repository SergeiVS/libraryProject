package org.libraryaccountingproject.controllers;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.authorsJsonData.AuthorDataFromWebResponseDto;
import org.libraryaccountingproject.services.AuthorServices;
import org.libraryaccountingproject.services.webRequestService.AuthorDataFromWebService;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/web-requests")
@RequiredArgsConstructor
public class webRequestsControllers {

    private final AuthorDataFromWebService service;
    private final AuthorServices authorServices;

    @GetMapping("/authors/{q}")
    public List<AuthorDataFromWebResponseDto> getAuthorsByNameFromWeb(@PathVariable String q)
            throws MalformedURLException, URISyntaxException {
        return service.getExtraAuthorDataFromWeb(q);
    }
}

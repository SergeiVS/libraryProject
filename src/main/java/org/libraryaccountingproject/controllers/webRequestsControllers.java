package org.libraryaccountingproject.controllers;

import lombok.RequiredArgsConstructor;
import org.libraryaccountingproject.dtos.authorsJsonData.AuthorDataFromWebResponseDto;
import org.libraryaccountingproject.services.webRequestService.AuthorDataFromWebService;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/public/requests")
@RequiredArgsConstructor
public class webRequestsControllers {

    private final AuthorDataFromWebService service;

    @GetMapping("/authors/{name}")
    public List<AuthorDataFromWebResponseDto> getAuthorsByNameFromWeb(@PathVariable String name)
            throws MalformedURLException, URISyntaxException {
        return service.getExtraAuthorDataFromWeb(name);
    }
}

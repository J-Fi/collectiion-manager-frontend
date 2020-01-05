package com.kodilla.collectionmanagerfrontend.backendapi.client;

import com.kodilla.collectionmanagerfrontend.backendapi.config.BackendApiConfig;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class BackendApiClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BackendApiConfig backendApiConfig;

    public List<BookDto> getBooksCollection(Long id) {
        try {
            BookDto[] booksCollection = restTemplate.getForObject(backendApiConfig.getBackendApiEndpoints()
                    + "/v1/book/list/" + id, BookDto[].class);
            return Arrays.asList(Optional.ofNullable(booksCollection).orElse(new BookDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

}

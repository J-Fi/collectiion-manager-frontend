package com.kodilla.collectionmanagerfrontend.backendapi.client;

import com.kodilla.collectionmanagerfrontend.backendapi.config.BackendApiConfig;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.*;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@UIScope
public class BackendApiClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BackendApiConfig backendApiConfig;

    public List<BookDto> getBooksCollection(Long id) {
        try {
            BookDto[] booksCollection = restTemplate.getForObject(backendApiConfig.getBackendApiEndpoints() + "/v1/book/list" + "/" + id, BookDto[].class);
            return Arrays.asList(Optional.ofNullable(booksCollection).orElse(new BookDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public BookToBackendDtoConverted getBookByIsbn (String isbn) {
        try {
            BookToBackendDtoConverted bookToBackendDtoConverted = restTemplate.getForObject(backendApiConfig.getBackendApiEndpoints() + "/v1/isbndb" + "/" + isbn, BookToBackendDtoConverted.class);
            return Optional.ofNullable(bookToBackendDtoConverted).orElse(new BookToBackendDtoConverted());
        } catch (RestClientException e) {
            return new BookToBackendDtoConverted();
        }
    }

    public BookToBackendDtoConverted getBookById (Long bookId) {
        try {
            BookToBackendDtoConverted bookToBackendDtoConverted = restTemplate.getForObject(backendApiConfig.getBackendApiEndpoints() + "/v1/book" + "/" + bookId, BookToBackendDtoConverted.class);
            return Optional.ofNullable(bookToBackendDtoConverted).orElse(new BookToBackendDtoConverted());
        } catch (RestClientException e) {
            return new BookToBackendDtoConverted();
        }
    }

    public void saveBook(Long id, BookToBackendDtoConverted bookToBackendDtoConverted) {
        restTemplate.postForObject(backendApiConfig.getBackendApiEndpoints() + "/v1/book" + "/" + id, bookToBackendDtoConverted, BookToBackendDtoConverted.class);
    }

    public void deleteBook(Long id) {
        restTemplate.delete(backendApiConfig.getBackendApiEndpoints() + "/v1/book" + "/" + id);
    }

    public void updateBook(Long bookCollectionId, Long bookId, BookToBackendDtoConverted bookToBackendDtoConverted) {
        restTemplate.put(backendApiConfig.getBackendApiEndpoints() + "/v1/book" + "/" + bookCollectionId + "/" + bookId, bookToBackendDtoConverted, BookToBackendDtoConverted.class);
    }

    public void createUser (UserToBackendDto userToBackendDto) {
        restTemplate.postForObject(backendApiConfig.getBackendApiEndpoints() + "/v1/user/create", userToBackendDto, UserToBackendDto.class);
    }

    public UserDto getUserFromEmail(String userEmail) {
        return restTemplate.getForObject(backendApiConfig.getBackendApiEndpoints() + "/v1/user/email" + "/" + userEmail, UserDto.class);
    }

    public void createBooksCollection(Long userId, BooksCollectionToBackendDto booksCollectionToBackendDto) {
        restTemplate.postForObject(backendApiConfig.getBackendApiEndpoints() + "/v1/books-collection" + "/" + userId, booksCollectionToBackendDto, BooksCollectionToBackendDto.class);
    }

    public BooksCollectionDto getBooksCollectionIdByUserId (Long userId) {
        return restTemplate.getForObject(backendApiConfig.getBackendApiEndpoints() + "/v1/books-collection/collectionid" + "/" + userId, BooksCollectionDto.class);
    }
}

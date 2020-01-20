package com.kodilla.collectionmanagerfrontend.service;

import com.kodilla.collectionmanagerfrontend.backendapi.client.BackendApiClient;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.Book;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookToBackendDto;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BooksCollectionToBackendDto;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.UserToBackendDto;
import com.kodilla.collectionmanagerfrontend.mapper.BookMapper;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@UIScope
public class BackendApiService {

    private BackendApiClient backendApiClient;
    private BookMapper bookMapper;

    public BackendApiService(BackendApiClient backendApiClient, BookMapper bookMapper) {
        this.backendApiClient = backendApiClient;
        this.bookMapper = bookMapper;
    }

    public List<Book> getBooksCollection(Long id) {
        return bookMapper.mapToBookList(backendApiClient.getBooksCollection(id));
    }

    public List<Book> findByTitle(Long id, String title) {
        return bookMapper.mapToBookList(backendApiClient.getBooksCollection(id).stream()
                .filter(book -> book.getTitle().contains(title))
                .collect(Collectors.toList()));
    }

    public void addBook(Long id, BookToBackendDto bookToBackendDto) {
        backendApiClient.saveBook(id, bookMapper.mappingConverterIsbndb(bookToBackendDto));
    }

    public BookToBackendDto getBookByIsbn(String isbn) {
        return bookMapper.mappingConverterIsbndb(backendApiClient.getBookByIsbn(isbn));
    }

    public BookToBackendDto getBookById(Long id) {
        return bookMapper.mappingConverter(backendApiClient.getBookById(id));
    }

    public void deleteBook(Long id) {
        backendApiClient.deleteBook(id);
    }

    public void updateBook(Long booksCollectionId, Long bookId, BookToBackendDto bookToBackendDto) {
        backendApiClient.updateBook(booksCollectionId, bookId, bookMapper.mappingConverter(bookToBackendDto));
    }

    public void createUser(UserToBackendDto userToBackendDto) {
        backendApiClient.createUser(userToBackendDto);
    }

    public Long getUserIdFromEmail(String userEmail) {
        return backendApiClient.getUserFromEmail(userEmail).getUserId();
    }

    public void createBooksCollection(Long userId, BooksCollectionToBackendDto booksCollectionToBackendDto) {
        backendApiClient.createBooksCollection(userId, booksCollectionToBackendDto);
    }

    public Long getBooksCollectionId(Long userId) {
        return backendApiClient.getBooksCollectionIdByUserId(userId).getBooksCollectionId();
    }
}

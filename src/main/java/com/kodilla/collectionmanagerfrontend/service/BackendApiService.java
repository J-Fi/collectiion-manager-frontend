package com.kodilla.collectionmanagerfrontend.service;

import com.kodilla.collectionmanagerfrontend.backendapi.client.BackendApiClient;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.Book;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookDto;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookToBackendDto;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookToBackendDtoConverted;
import com.kodilla.collectionmanagerfrontend.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
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
        backendApiClient.saveBook(id, bookMapper.mappingConverter(bookToBackendDto));
    }

    public BookToBackendDto getBookByIsbn(String isbn) {
        return bookMapper.mappingConverter(backendApiClient.getBookByIsbn(isbn));
    }
}

package com.kodilla.collectionmanagerfrontend.service;

import com.kodilla.collectionmanagerfrontend.backendapi.client.BackendApiClient;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.Book;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookDto;
import com.kodilla.collectionmanagerfrontend.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Scope("singleton")
public class BackendApiService {

    //private static BackendApiService backendApiService;

    @Autowired
    private BackendApiClient backendApiClient;

    @Autowired
    private BookMapper bookMapper;


/*    public static BackendApiService getInstance() {
        if (backendApiService == null) {
            backendApiService = new BackendApiService();
        }
        return backendApiService;
    }*/

    public List<Book> getBooksCollection(Long id) {
        return bookMapper.mapToBookList(backendApiClient.getBooksCollection(id));
    }
}

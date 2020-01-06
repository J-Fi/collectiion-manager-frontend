package com.kodilla.collectionmanagerfrontend.backendapi.client;

import com.kodilla.collectionmanagerfrontend.backendapi.config.BackendApiConfig;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BackendApiClientTest {

    @InjectMocks
    private BackendApiClient backendApiClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BackendApiConfig backendApiConfig;

    @Before
    public void init() {
        when(backendApiConfig.getBackendApiEndpoints()).thenReturn("http://localhost:8080");
    }

    @Test
    public void shouldGetBooksCollection() throws URISyntaxException {
        //Given
        BookDto[] booksCollection = new BookDto[2];
        booksCollection[0] = new BookDto(1L, "isbn", "isbn13",
                "title", "publisher",
                "synopsys", "image",
                "authors", "subjects",
                2020, 3L);
        booksCollection[1] = new BookDto(2L, "isbn", "isbn13",
                "title", "publisher",
                "synopsys", "image",
                "authors", "subjects",
                2020, 3L);

        URI uri = new URI("http://localhost:8080/v1/book/list/3");

        when(restTemplate.getForObject(uri, BookDto[].class)).thenReturn(booksCollection);
        //When
        List<BookDto> fetchedBooksCollection = backendApiClient.getBooksCollection(3L);
        //Then
        assertEquals(2,fetchedBooksCollection.size());
        assertEquals(1, fetchedBooksCollection.get(0).getBookId());
        assertEquals("isbn", fetchedBooksCollection.get(0).getIsbn());
    }

}
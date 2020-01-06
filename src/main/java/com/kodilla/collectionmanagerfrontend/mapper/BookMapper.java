package com.kodilla.collectionmanagerfrontend.mapper;

import com.kodilla.collectionmanagerfrontend.bookscollectionview.Book;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public List<Book> mapToBookList(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(bookDto -> new Book(
                    bookDto.getBookId(), bookDto.getIsbn(),
                    bookDto.getIsbn13(), bookDto.getTitle(),
                    bookDto.getPublisher(), bookDto.getSynopsys(),
                    bookDto.getImage(), bookDto.getAuthors(),
                    bookDto.getSubjects(), bookDto.getPublishDate(),
                    bookDto.getBooksCollectionId()))
                .collect(Collectors.toList());
    }
}

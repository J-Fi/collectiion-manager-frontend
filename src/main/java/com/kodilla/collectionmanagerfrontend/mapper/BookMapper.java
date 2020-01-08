package com.kodilla.collectionmanagerfrontend.mapper;

import com.kodilla.collectionmanagerfrontend.bookscollectionview.Book;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookDto;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookToBackendDto;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BookToBackendDtoConverted;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.valueOf;

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

    public BookToBackendDto mapToBookToBackendDto(Book book) {
        return new BookToBackendDto(
                book.getIsbn(),
                book.getIsbn13(), book.getTitle(),
                book.getPublisher(), book.getSynopsys(),
                book.getImage(), book.getAuthors(),
                book.getSubjects(), Integer.toString(book.getPublishDate()));
    }

    public BookToBackendDtoConverted mappingConverter (BookToBackendDto bookToBackendDto) {
        return new BookToBackendDtoConverted(bookToBackendDto.getIsbn(), bookToBackendDto.getIsbn13(),
                bookToBackendDto.getTitle(), bookToBackendDto.getPublisher(),
                bookToBackendDto.getSynopsys(), bookToBackendDto.getImage(),
                bookToBackendDto.getAuthors(), bookToBackendDto.getSubjects(),
                valueOf(bookToBackendDto.getPublishDate()));
    }
}

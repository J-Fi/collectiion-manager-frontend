package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookToBackendDtoConverted {
    private Long bookId;
    private String isbn;
    private String isbn13;
    private String title;
    private String publisher;
    private String synopsys;
    private String image;
    private String authors;
    private String subjects;
    private Integer publishDate;
    private Long booksCollectionId;

    public BookToBackendDtoConverted(String isbn, String isbn13, String title, String publisher, String synopsys,
                                     String image, String authors, String subjects, Integer publishDate) {
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.title = title;
        this.publisher = publisher;
        this.synopsys = synopsys;
        this.image = image;
        this.authors = authors;
        this.subjects = subjects;
        this.publishDate = publishDate;
    }
}

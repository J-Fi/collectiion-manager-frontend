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
    private String isbn;
    private String isbn13;
    private String title;
    private String publisher;
    private String synopsys;
    private String image;
    private String authors;
    private String subjects;
    private Integer publishDate;
}

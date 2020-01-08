package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookToBackendDto {
    private String isbn;
    private String isbn13;
    private String title;
    private String publisher;
    private String synopsys;
    private String image;
    private String authors;
    private String subjects;
    private String publishDate;
}

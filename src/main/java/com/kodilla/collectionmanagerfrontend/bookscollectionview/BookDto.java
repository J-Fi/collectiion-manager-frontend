package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {
    @JsonProperty("bookId")
    private Long bookId;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("isbn13")
    private String isbn13;

    @JsonProperty("title")
    private String title;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("synopsys")
    private String synopsys;

    @JsonProperty("image")
    private String image;

    @JsonProperty("authors")
    private String authors;

    @JsonProperty("subjects")
    private String subjects;

    @JsonProperty("publishDate")
    private Integer publishDate;

    @JsonProperty("booksCollectionId")
    private Long booksCollectionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto)) return false;

        BookDto bookDto = (BookDto) o;

        if (!getBookId().equals(bookDto.getBookId())) return false;
        if (getIsbn() != null ? !getIsbn().equals(bookDto.getIsbn()) : bookDto.getIsbn() != null) return false;
        if (getIsbn13() != null ? !getIsbn13().equals(bookDto.getIsbn13()) : bookDto.getIsbn13() != null) return false;
        if (getTitle() != null ? !getTitle().equals(bookDto.getTitle()) : bookDto.getTitle() != null) return false;
        if (getPublisher() != null ? !getPublisher().equals(bookDto.getPublisher()) : bookDto.getPublisher() != null)
            return false;
        if (getSynopsys() != null ? !getSynopsys().equals(bookDto.getSynopsys()) : bookDto.getSynopsys() != null)
            return false;
        if (getImage() != null ? !getImage().equals(bookDto.getImage()) : bookDto.getImage() != null) return false;
        if (getAuthors() != null ? !getAuthors().equals(bookDto.getAuthors()) : bookDto.getAuthors() != null)
            return false;
        if (getSubjects() != null ? !getSubjects().equals(bookDto.getSubjects()) : bookDto.getSubjects() != null)
            return false;
        if (getPublishDate() != null ? !getPublishDate().equals(bookDto.getPublishDate()) : bookDto.getPublishDate() != null)
            return false;
        return getBooksCollectionId().equals(bookDto.getBooksCollectionId());
    }

    @Override
    public int hashCode() {
        int result = getBookId().hashCode();
        result = 31 * result + (getIsbn() != null ? getIsbn().hashCode() : 0);
        result = 31 * result + (getIsbn13() != null ? getIsbn13().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getPublisher() != null ? getPublisher().hashCode() : 0);
        result = 31 * result + (getSynopsys() != null ? getSynopsys().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getAuthors() != null ? getAuthors().hashCode() : 0);
        result = 31 * result + (getSubjects() != null ? getSubjects().hashCode() : 0);
        result = 31 * result + (getPublishDate() != null ? getPublishDate().hashCode() : 0);
        result = 31 * result + getBooksCollectionId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", synopsys='" + synopsys + '\'' +
                ", image='" + image + '\'' +
                ", authors='" + authors + '\'' +
                ", subjects='" + subjects + '\'' +
                ", publishDate=" + publishDate +
                ", booksCollectionId=" + booksCollectionId +
                '}';
    }
}

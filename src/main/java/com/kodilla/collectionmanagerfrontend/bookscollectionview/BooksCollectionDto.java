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
public class BooksCollectionDto {
    @JsonProperty("booksCollectionId")
    private Long booksCollectionId;

    @JsonProperty("collectionName")
    private String collectionName;

    @JsonProperty("userId")
    private Long userId;
}

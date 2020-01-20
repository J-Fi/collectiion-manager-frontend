package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private String firstName;
    private String lastName;
    private String birthday;
    private String email;
    private String login;
    private String password;
}

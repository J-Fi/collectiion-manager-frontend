package com.kodilla.collectionmanagerfrontend.login;

import com.kodilla.collectionmanagerfrontend.bookscollectionview.BooksCollectionForm;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.BooksCollectionToBackendDto;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.RegisterForm;
import com.kodilla.collectionmanagerfrontend.bookscollectionview.UserToBackendDto;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@UIScope
@Component
@Route
public class Login extends VerticalLayout {

    private RegisterForm registerForm;
    private BooksCollectionForm booksCollectionForm;

    @Autowired
    public Login(RegisterForm registerForm, BooksCollectionForm booksCollectionForm) {
        this.registerForm = registerForm;
        this.booksCollectionForm = booksCollectionForm;

        HorizontalLayout registerField = new HorizontalLayout(registerForm);
        HorizontalLayout booksCollectionField = new HorizontalLayout(booksCollectionForm);
        add(registerField, booksCollectionField);
        registerForm.setUserToBackendDto(new UserToBackendDto());
        booksCollectionForm.setBooksCollectionToBackendDto(new BooksCollectionToBackendDto());
    }
}

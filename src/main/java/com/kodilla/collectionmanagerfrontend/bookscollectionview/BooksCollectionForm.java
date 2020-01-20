package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.kodilla.collectionmanagerfrontend.service.BackendApiService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Route
@Component
@UIScope
@Getter
@Setter
public class BooksCollectionForm extends FormLayout {

    private GetUserIdForm getUserIdForm;
    private BackendApiService backendApiService;
    private BooksCollectionToBackendDto booksCollectionToBackendDto;

    private final TextField collectionName = new TextField("Books Collection Name");

    private Button addCollection = new Button("Create a New Collection");
    private Button showLinkToCollection = new Button("Show link to my collection");
    private Button cancel = new Button("Cancel");

    private Anchor anchor = new Anchor();

    private Binder<BooksCollectionToBackendDto> binder;

    @Autowired
    public BooksCollectionForm(BackendApiService backendApiService, GetUserIdForm getUserIdForm) {
        this.backendApiService = backendApiService;
        this.getUserIdForm = getUserIdForm;

        this.binder = new Binder<>(BooksCollectionToBackendDto.class);
        HorizontalLayout buttons = new HorizontalLayout(addCollection, showLinkToCollection, cancel);
        HorizontalLayout formLayout = new HorizontalLayout(collectionName);
        VerticalLayout mainLayout = new VerticalLayout(getUserIdForm, formLayout, buttons, anchor);
        addCollection.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(mainLayout);
        binder.bindInstanceFields(this);

        addCollection.addClickListener(e -> {
            Long userId = getUserIdForm.getUserIdFromEmail();
            createCollection(userId);
        });

        showLinkToCollection.addClickListener(e -> {
            Long userId = getUserIdForm.getUserIdFromEmail();
            Long booksCollectionId = backendApiService.getBooksCollectionId(userId);
            anchor.setHref("v1/collectionview" + "/" + booksCollectionId);
            anchor.setText("Show me my collection");
        });
    }

    public void setBooksCollectionToBackendDto(BooksCollectionToBackendDto booksCollectionToBackendDto) {
        binder.setBean(booksCollectionToBackendDto);
    }

    public void createCollection(Long userId) {
        booksCollectionToBackendDto = binder.getBean();
        backendApiService.createBooksCollection(userId, booksCollectionToBackendDto);
    }
}

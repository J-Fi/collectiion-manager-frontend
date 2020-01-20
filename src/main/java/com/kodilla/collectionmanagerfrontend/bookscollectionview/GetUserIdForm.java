package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.kodilla.collectionmanagerfrontend.service.BackendApiService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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
public class GetUserIdForm extends FormLayout {
    private BackendApiService backendApiService;

    private final TextField email = new TextField("email");

    @Autowired
    public GetUserIdForm(BackendApiService backendApiService) {
        this.backendApiService = backendApiService;

        Label label = new Label("KROK 2: Tworzenie kolekcji - Proszę najpierw wpisać adres email podany podczas rejestracji, " +
                "następnie wpisać nazwę kolekcji i kliknąć przycisk 'Create a new collection.");
        Label labelCd = new Label("KROK 3: aby wyświetlić kolekcję, w polu 'email' poniżej wpisać swój adrtes e-mail i kliknąć" +
                " przycisk 'Show link to my collection' a następnie kliknąć na link 'Show me my collection");
        HorizontalLayout formLayout = new HorizontalLayout(email);
        VerticalLayout mainLayout = new VerticalLayout(label, labelCd, formLayout);

        add(mainLayout);
    }

    public Long getUserIdFromEmail() {
        String emailAddress = email.getValue();
        return backendApiService.getUserIdFromEmail(emailAddress);
    }
}

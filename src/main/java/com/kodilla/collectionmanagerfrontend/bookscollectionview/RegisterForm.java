package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.kodilla.collectionmanagerfrontend.login.Login;
import com.kodilla.collectionmanagerfrontend.service.BackendApiService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
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
public class RegisterForm extends FormLayout {
    private Login loginView;
    private BackendApiService backendApiService;
    private UserToBackendDto userToBackendDto;

    private final TextField firstName = new TextField("firstName");
    private final TextField lastName = new TextField("lastName");
    private final TextField birthday = new TextField("birthday");
    private final TextField email = new TextField("email");
    private final TextField login = new TextField("login");
    private final TextField password = new TextField("password");

    private Button addUser = new Button("Register");
    private Button cancel = new Button("Cancel");

    private Button createCollection = new Button("Create a new Collection");

    private Binder<UserToBackendDto> binder;

    @Autowired
    public RegisterForm(BackendApiService backendApiService) {
        this.backendApiService = backendApiService;

        this.binder = new Binder<>(UserToBackendDto.class);
        Label label = new Label("KROK 1: Proszę wypełnić formularz rejestracyjny i kliknąć przycisk 'Register'.");
        HorizontalLayout buttons = new HorizontalLayout(addUser, cancel);
        HorizontalLayout formLayout = new HorizontalLayout(firstName, lastName, birthday, email, login, password);
        VerticalLayout mainLayout = new VerticalLayout(label, formLayout, buttons);
        addUser.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(mainLayout);
        binder.bindInstanceFields(this);

        addUser.addClickListener(e -> createUser());
    }

    public void setUserToBackendDto(UserToBackendDto userToBackendDto) {
        binder.setBean(userToBackendDto);
    }

    public void createUser() {
        userToBackendDto = binder.getBean();
        backendApiService.createUser(userToBackendDto);
    }
}

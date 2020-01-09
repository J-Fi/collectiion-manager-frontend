package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.kodilla.collectionmanagerfrontend.service.BackendApiService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
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


@Component
@Getter
@Setter
public class BookForm extends FormLayout {
    private MainView mainView;
    private BackendApiService backendApiService;
    private BookToBackendDto bookToBackendDto;
    //private IsbnBookSearch isbnBookSearch;

    private final TextField isbn = new TextField("ISBN");
    private final TextField isbn13 = new TextField("ISBN13");
    private final TextField title = new TextField("Tytuł");
    private final TextField publisher = new TextField("Wydawnictwo");
    private final TextField synopsys = new TextField("Streszczenie");
    private final TextField image = new TextField("Link");
    private final TextField authors = new TextField("Autorzy");
    private final TextField subjects = new TextField("Kategorie");
    private final TextField publishDate = new TextField("Rok wydania");

    private Button update = new Button("Upate");
    private Button delete = new Button("Delete");

    private Binder<BookToBackendDto> binder;

    @Autowired
    //private BackendApiService backendApiService;
    public BookForm(BackendApiService backendApiService) {
        this.backendApiService = backendApiService;
        //this.isbnBookSearch = isbnBookSearch;
        //this.mainView = mainView;
        //this.backendApiService = backendApiService;
        //this.mainView = mainView;

        this.binder = new Binder<>(BookToBackendDto.class);
        HorizontalLayout buttons = new HorizontalLayout(update, delete);
        VerticalLayout formLayout = new VerticalLayout(isbn, isbn13, title, publisher, synopsys, image, authors, subjects, publishDate);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //add(isbn13, title, publisher, synopsys, image, authors, subjects, publishDate, buttons);
        add(formLayout, buttons);
        binder.bindInstanceFields(this);
        /*binder.forField(isbn).bind(BookToBackendDto::getIsbn, BookToBackendDto::setIsbn);
        binder.forField(isbn13).bind(BookToBackendDto::getIsbn13, BookToBackendDto::setIsbn13);
        binder.forField(title).bind(BookToBackendDto::getTitle, BookToBackendDto::setTitle);
        binder.forField(publisher).bind(BookToBackendDto::getPublisher, BookToBackendDto::setPublishDate);
        binder.forField(synopsys).bind(BookToBackendDto::getSynopsys, BookToBackendDto::setSynopsys);
        binder.forField(image).bind(BookToBackendDto::getImage, BookToBackendDto::setImage);
        binder.forField(authors).bind(BookToBackendDto::getAuthors, BookToBackendDto::setAuthors);
        binder.forField(subjects).bind(BookToBackendDto::getSubjects, BookToBackendDto::setSubjects);
        binder.forField(publishDate).bind(BookToBackendDto::getPublishDate, BookToBackendDto::setPublishDate);*/

        System.out.println("Zaraz wprowadzimy nr kolekcji 3 na twardo w konstruktorze BookFOrm...");
        update.addClickListener(e -> addBook());
        System.out.println("....uff, udało się.");
        //delete.addClickListener(e -> delete());
    }

    @Autowired
    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

/*    @Autowired
    public void setIsbnBookSearch(IsbnBookSearch isbnBookSearch) {
        this.isbnBookSearch = isbnBookSearch;
    }*/

    public void setBookToBackendDto(BookToBackendDto bookToBackendDto) {
        binder.setBean(bookToBackendDto);

        if (bookToBackendDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            isbn.focus();
        }
    }

    public void addBook() {
        bookToBackendDto = binder.getBean();
        System.out.println("id z addBook(id) w BookFOrm = " + binder + " " + binder.getBean() + " " + bookToBackendDto + " " + backendApiService);
        backendApiService.addBook(mainView.getBookCollectionid(), bookToBackendDto);
        System.out.println("addBook(id) w BookFOrm: WYkonana metoda");
        mainView.refresh(mainView.getBookCollectionid());
        //set
    }
}

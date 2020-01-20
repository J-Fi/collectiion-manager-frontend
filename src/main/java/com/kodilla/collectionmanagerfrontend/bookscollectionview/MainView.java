package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.kodilla.collectionmanagerfrontend.mapper.BookMapper;
import com.kodilla.collectionmanagerfrontend.service.BackendApiService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
@UIScope
@Route("v1/collectionview")
public class MainView extends VerticalLayout implements HasUrlParameter<Long>, AfterNavigationObserver {

    private BookForm bookForm;
    private BookFormIsbndb bookFormIsbndb;
    private BackendApiService backendApiService;
    private Grid<Book> grid = new Grid<>(Book.class);
    private TextField filter = new TextField();
    private TextField isbnSearch = new TextField();
    private Button addNewBookButton = new Button("Add a new book");
    private Button isbnSearchButton = new Button("Wyszukaj książkę");
    private NativeButton home = new NativeButton("Home");
    private Button clear = new Button("Clear the form");
    public Long booksCollectionId;

    @Autowired
    private BookMapper bookMapper;

    public MainView(@Autowired BackendApiService backendApiService, @Autowired BookForm bookForm,
                    @Autowired BookFormIsbndb bookFormIsbndb) {
        this.backendApiService = backendApiService;
        this.bookForm = bookForm;
        this.bookFormIsbndb = bookFormIsbndb;

        filter.setPlaceholder("Search by title");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update(booksCollectionId));

        isbnSearch.setPlaceholder("Tu wpisz nr isbn");
        isbnSearch.setClearButtonVisible(true);

        HorizontalLayout isbnSearchComponent = new HorizontalLayout(isbnSearch, isbnSearchButton);
        isbnSearchButton.addClickListener(e -> searchAndFillTheForm());

        grid.setColumns("bookId", "title",
                "authors",
                "subjects", "publishDate",
                "booksCollectionId");

        addNewBookButton.addClickListener(e -> {
            bookForm.setBookToBackendDto(null);
            bookFormIsbndb.setBookToBackendDto(new BookToBackendDto());
            bookFormIsbndb.setBooksCollectionId(booksCollectionId);
            isbnSearchComponent.setVisible(true);
            isbnSearchComponent.setSizeFull();
        });


        home.addClickListener(e ->
                home.getUI().ifPresent(ui ->
                        ui.navigate("login"))
        );

        HorizontalLayout toolBar = new HorizontalLayout(filter, addNewBookButton, isbnSearchComponent, clear, home);
        HorizontalLayout mainLayout = new HorizontalLayout(grid, bookForm, bookFormIsbndb);

        mainLayout.setSizeFull();
        grid.setSizeFull();

        add(toolBar, mainLayout);
        bookForm.setBookToBackendDto(null);
        bookFormIsbndb.setBookToBackendDto(null);
        isbnSearchComponent.setVisible(false);
        setSizeFull();

        grid.asSingleSelect().addValueChangeListener(event -> {
            bookFormIsbndb.setBookToBackendDto(null);
            isbnSearchComponent.setVisible(false);
            bookForm.setBookToBackendDto(bookMapper.mapToBookToBackendDto(grid.asSingleSelect().getValue()));
            });

        clear.addClickListener(e -> bookFormIsbndb.setBookToBackendDto(new BookToBackendDto()));
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) { //booksCollectionId
        this.booksCollectionId = parameter;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        refresh(booksCollectionId);
    }

    public void refresh(Long booksCollectionId) {
        grid.setItems(backendApiService.getBooksCollection(booksCollectionId));
    }

    private void update(Long booksCollectionId) {
        grid.setItems(backendApiService.findByTitle(booksCollectionId, filter.getValue()));
    }

    public void searchAndFillTheForm() {
        String isbn = getIsbnSearch().getValue();
        BookToBackendDto bookToBackendDto = backendApiService.getBookByIsbn(isbn);
        bookFormIsbndb.setBookToBackendDto(bookToBackendDto);
    }
}

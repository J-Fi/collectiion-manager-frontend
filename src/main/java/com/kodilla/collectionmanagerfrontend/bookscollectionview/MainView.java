package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.kodilla.collectionmanagerfrontend.mapper.BookMapper;
import com.kodilla.collectionmanagerfrontend.service.BackendApiService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Getter
@Component
//@VaadinSessionScope
@Route("v1/collectionview")
public class MainView extends VerticalLayout {
    private BookForm bookForm;
    private BackendApiService backendApiService;
    private Grid<Book> grid = new Grid<>(Book.class);
    private TextField filter = new TextField();
    private Button addNewBookButton = new Button("Add a new book");
    //private BookForm form = new BookForm(this);
    private final Long bookCollectionid = 3L;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    public MainView(BackendApiService backendApiService, BookForm bookForm) {
        this.backendApiService = backendApiService;
        this.bookForm = bookForm;

        filter.setPlaceholder("Search by title");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update(bookCollectionid));

        grid.setColumns("bookId", "title",
                "authors",
                "subjects", "publishDate",
                "booksCollectionId");

        addNewBookButton.addClickListener(e -> {
            grid.asSingleSelect().clear(); //"czyścimy" zaznaczenie
            bookForm.setBookToBackendDto(new BookToBackendDto()); //dodajemy nowy obiekt do formularza
        });
        HorizontalLayout toolBar = new HorizontalLayout(filter, addNewBookButton);
        HorizontalLayout mainLayout = new HorizontalLayout(grid, bookForm);

        mainLayout.setSizeFull();
        grid.setSizeFull();

        add(toolBar, mainLayout);
        bookForm.setBookToBackendDto(null);
        setSizeFull();
        refresh(bookCollectionid);

        grid.asSingleSelect().addValueChangeListener(event -> bookForm.setBookToBackendDto(bookMapper.mapToBookToBackendDto(grid.asSingleSelect().getValue())));
    }

    public void refresh(Long id) {
        grid.setItems(backendApiService.getBooksCollection(id));
    }

    private void update(Long id) {
        grid.setItems(backendApiService.findByTitle(id, filter.getValue()));
    }
}

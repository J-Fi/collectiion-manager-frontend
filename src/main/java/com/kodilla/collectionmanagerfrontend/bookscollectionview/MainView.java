package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.kodilla.collectionmanagerfrontend.service.BackendApiService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Route("v1/collectionview")
public class MainView extends VerticalLayout {

    //@Autowired
    private BackendApiService backendApiService;

    //private BackendApiService backendApiService = BackendApiService.getInstance();

    private Grid<Book> grid = new Grid<>(Book.class);
    private Long id;

    public MainView(BackendApiService backendApiService) {
        this.backendApiService = backendApiService;
        id = 3L;
        //add(new Button("Click me!", e -> Notification.show("Hello!")));

        grid.setColumns("bookId", "isbn",
                "isbn13","title",
                "publisher", "synopsys",
                "image", "authors",
                "subjects", "publishDate",
                "booksCollectionId");
        add(grid);
        setSizeFull();
        refresh(id);
    }

    private void refresh(Long id) {
        grid.setItems(backendApiService.getBooksCollection(id));
    }
}

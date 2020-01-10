package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

//import java.awt.*;

@Component
@Route("v1/bookview")
public class BookView extends VerticalLayout {
    private TextField websiteName = new TextField();
    private Image bookCoverImage = new Image();
    private TextField bookTitle = new TextField("Tytuł");
    private TextField bookAuthors = new TextField("Autor");
    private TextArea bookSynopsys = new TextArea("Streszczenie książki");
    private TextField bookPrice = new TextField("Obecna cena książki");
    private Grid<UserCommentOnBook> personalCommentsOnBook = new Grid<>(UserCommentOnBook.class);
    private TextArea userComment = new TextArea();
    private BookToBackendDto bookToBackendDto;

    public BookView() {
        websiteName.setValue("Teraz oglądasz kartę rekordu z Twojej kolekcji książek");

        bookCoverImage.setSrc("https://images.isbndb.com/covers/95/95/9788324019595.jpg");
        bookCoverImage.setHeightFull();
        bookCoverImage.setWidthFull();
        bookTitle.setValue("Zamek");
        bookAuthors.setValue("Franz Kafka");
        bookSynopsys.getStyle().set("minHeight", "200px");
        bookPrice.setValue("35 zł.");

        personalCommentsOnBook.setColumns("date", "content");
        userComment.setPlaceholder("Wpisz swój komentarz tutaj");

        HorizontalLayout topHeader = new HorizontalLayout(websiteName);
        VerticalLayout bookInfoLayout = new VerticalLayout(bookTitle, bookAuthors, bookSynopsys);
        HorizontalLayout mainLayout = new HorizontalLayout(bookCoverImage, bookInfoLayout,bookPrice);
        HorizontalLayout userComments = new HorizontalLayout(personalCommentsOnBook, userComment);

        add(topHeader, mainLayout, userComments);
        topHeader.setSizeFull();
        websiteName.setSizeFull();
        mainLayout.setSizeFull();
        bookInfoLayout.setSizeFull();
        userComments.setSizeFull();
        setSizeFull();
    }
}

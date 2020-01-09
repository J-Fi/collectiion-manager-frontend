/*
package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.kodilla.collectionmanagerfrontend.service.BackendApiService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
//@Setter
@Component
public class IsbnBookSearch extends FormLayout {
    //private MainView mainView;
    private BackendApiService backendApiService;
    //private BookToBackendDto bookToBackendDto;
    private Button isbnSearchButton = new Button("Wyszukaj książkę");

*/
/*    @Autowired
    private BookForm bookForm;*//*


    private TextField isbnSearch = new TextField("Wyszukiwarka książek po numerze ISBN");

    public IsbnBookSearch (BackendApiService backendApiService) {
        this.backendApiService = backendApiService;
        isbnSearch.setPlaceholder("Tutaj wprowadź numer isbn szukanej książki");
        isbnSearch.setClearButtonVisible(true);

        HorizontalLayout isbnSearchComponent = new HorizontalLayout(isbnSearch, isbnSearchButton);

        add(isbnSearchComponent);

        isbnSearchButton.addClickListener(e -> searchAndFillTheForm());
    }

*/
/*    @Autowired
    public void setBookForm(BookForm bookForm) {
        this.bookForm = bookForm;
    }*//*


*/
/*    @Autowired
    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }*//*


    public BookToBackendDto searchAndFillTheForm() {
        String isbn = getIsbnSearch().getValue();
        return backendApiService.getBookByIsbn(isbn);
        //bookForm.setBookToBackendDto(bookToBackendDto);
    }
}
*/

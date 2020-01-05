package com.kodilla.collectionmanagerfrontend.bookscollectionview;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("v1/collectionview")
public class BooksCollectionView extends VerticalLayout {

    public BooksCollectionView() {
        add(new Button("Click me!", e -> Notification.show("Let's start!")));
    }
}

package com.shennarwp.m900.views.other;

import com.shennarwp.m900.data.service.LinkService;
import com.shennarwp.m900.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value = "other", layout = MainView.class)
@PageTitle("Other Bookmarks")
@CssImport("./styles/views/other-view.css")
public class OtherView extends HorizontalLayout {

    private final LinkService linkService;

    /** constructor, injected by spring */
    @Autowired
    public OtherView(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostConstruct
    public void init() {
        setId("otherview");
        setClassName("otherview");

        H3 caption = new H3("Add new bookmark:");
        caption.setWidthFull();
        add(caption);


        TextField titleField = new TextField();
        titleField.setLabel("Title");
        TextField categoryField = new TextField();
        categoryField.setLabel("Category");

        TextField urlField = new TextField();
        urlField.setLabel("URL");
        urlField.setClassName("otherview-urlbox");

        Button saveButton = new Button("Save");
        saveButton.setHeight("35px");
        saveButton.setWidth("85px");


        add(titleField, categoryField, urlField, saveButton);
        setVerticalComponentAlignment(Alignment.END, saveButton);


    }

}

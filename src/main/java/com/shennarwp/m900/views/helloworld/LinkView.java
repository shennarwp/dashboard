package com.shennarwp.m900.views.helloworld;

import com.shennarwp.m900.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.vaadin.olli.BrowserOpener;

@Route(value = "hello", layout = MainView.class)
@PageTitle("Hello World")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class LinkView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public LinkView() {
//        setId("hello-world-view");
//        name = new TextField("Your name");
//        sayHello = new Button("Say hello");
//
//        Anchor anchor = new Anchor("https://vaadin.com", "Doing it right with...");
//        add(anchor);
//
//        add(name, sayHello);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
//        sayHello.addClickListener(e -> {
//            Notification.show("Hello " + name.getValue());
//        });
//        Anchor anchor = new Anchor("https://vaadin.com", "Doing it right with...");
//        Anchor anchor2 = new Anchor("https://vaadin.com", "Doing it right with...");
//        Anchor anchor3 = new Anchor("https://vaadin.com", "Doing it right with...");
        setAlignItems(Alignment.CENTER);
        setAlignSelf(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
//        add(anchor,anchor2,anchor3);

        Button githubButton = new Button("GitHub");
        Image githubIcon = new Image("icons/GitHub-Mark-Light-120px-plus.png", "githubIcon");
        githubIcon.setHeight("40px");
        githubIcon.setWidth("40px");
        githubButton.setIcon(githubIcon);
        githubButton.setHeight("60px");
        githubButton.setWidth("200px");
        add(githubButton);

        BrowserOpener bo = new BrowserOpener();
        bo.setContent(githubButton);
        bo.setUrl("http://github.com/shennarwp");
        add(bo);

        Button linkedinButton = new Button("LinkedIn");
        Image linkedinIcon = new Image("icons/LI-In-Bug.png", "linkedInIcon");
        linkedinIcon.setHeight("40px");
        linkedinIcon.setWidth("40px");
        linkedinButton.setIcon(linkedinIcon);
        linkedinButton.setHeight("60px");
        linkedinButton.setWidth("200px");
        add(linkedinButton);

        BrowserOpener bo2 = new BrowserOpener();
        bo2.setContent(githubButton);
        bo2.setUrl("https://www.linkedin.com/in/shennarwp/");
        add(bo2);


        setPadding(false);
        setMargin(false);

        setSpacing(true);
    }

}

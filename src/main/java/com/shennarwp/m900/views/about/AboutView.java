package com.shennarwp.m900.views.about;

import com.shennarwp.m900.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
public class AboutView extends Div {

    public AboutView() {
        setId("about-view");
        setWidth("60%");
        add(new Text("Content placeholder"));
    }

}

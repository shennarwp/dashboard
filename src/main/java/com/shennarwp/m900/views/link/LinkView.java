package com.shennarwp.m900.views.link;

import com.shennarwp.m900.data.entity.LinkEntity;
import com.shennarwp.m900.data.service.LinkService;
import com.shennarwp.m900.util.WeatherClient;
import com.shennarwp.m900.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * this view contains links / shortcuts to various sites
 * similar to how bookmark page works
 */
@Route(value = "link", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Links - M900 Dashboard")
@CssImport("./styles/views/link-view.css")
public class LinkView extends FlexLayout
{

    private final LinkService linkService;

    /**
     * constructor, create two flex layouts, left and right, spaced evenly
     */
    @Autowired
    public LinkView(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostConstruct
    public void init() {
        setId("linkview");
        setClassName("linkview");
        setJustifyContentMode(JustifyContentMode.AROUND);
        setFlexWrap(FlexWrap.WRAP);

        /* left portion of the dashboard */
        FlexLayout left = new FlexLayout();
        left.setFlexWrap(FlexWrap.WRAP);
        left.add(createGrafanaLayout());
        left.add(createDevToolsLayout());
        left.add(createAdminLayout());
        left.setClassName("linkview-left");         /* class .linkview-left in link-view.css */
        add(left);

        /* right portion of the dashboard */
        FlexLayout right = new FlexLayout();
        right.setFlexWrap(FlexWrap.WRAP);
        right.add(createOthersLayout());
        right.add(createWeatherLayout());
        right.setClassName("linkview-right");       /* class .linkview-right in link-view.css */
        add(right);
    }

    /**
     * create vertical weather layout
     */
    private VerticalLayout createWeatherLayout() {
        /* get current weather from openweathermap.org */
        WeatherClient wc = new WeatherClient();
        List<String> currentWeather =  wc.getWeatherDetail();

        /* fetch current weather image, 2nd element in the list is the image code */
        Image icon = new Image(currentWeather.get(2), "weather icon");
        icon.setWidth("100px");
        icon.setHeight("100px");

        /* assemble the layout, 0th element in the list is the temperature, 1st element is the description */
        VerticalLayout vl = createVerticalLayout("Weather - Saarbrücken");
        vl.add(icon);
        vl.add(new H3(currentWeather.get(0) + " °C"));
        vl.add(new H4(currentWeather.get(1)));
        return vl;
    }

    /**
     * create layout for other links (non categorized)
     */
    private VerticalLayout createOthersLayout() {
        /* so that it wraps */
        FlexLayout fl = new FlexLayout();
        fl.setJustifyContentMode(JustifyContentMode.BETWEEN);
        fl.setFlexWrap(FlexWrap.WRAP);
        fl.setWidthFull();

        linkService.getLinkEntityByCategory("Others")
                .stream()
                .map(this::createAnchor)
                .forEach(fl::add);

        VerticalLayout vl = createVerticalLayout("Others");
        vl.add(fl);
        return vl;
    }

    /**
     * create layout for grafana dashboards
     */
    private VerticalLayout createGrafanaLayout() {
        /* so that it wraps */
        FlexLayout fl = new FlexLayout();
        fl.setJustifyContentMode(JustifyContentMode.BETWEEN);
        fl.setFlexWrap(FlexWrap.WRAP);
        fl.setWidthFull();

        linkService.getLinkEntityByCategory("Grafana Dashboard")
                    .stream()
                    .map(this::createAnchor)
                    .forEach(fl::add);

        VerticalLayout vl = createVerticalLayout("Grafana Dashboards");
        vl.add(fl);
        return vl;
    }

    /**
     * create layout for development tools / software links
     */
    private VerticalLayout createDevToolsLayout() {
        /* so that it wraps */
        FlexLayout fl = new FlexLayout();
        fl.setJustifyContentMode(JustifyContentMode.BETWEEN);
        fl.setFlexWrap(FlexWrap.WRAP);
        fl.setWidthFull();

        linkService.getLinkEntityByCategory("Dev Tools")
                    .stream()
                    .map(this::createAnchor)
                    .forEach(fl::add);

        VerticalLayout vl = createVerticalLayout("Dev Tools");
        vl.add(fl);
        return vl;
    }

    /**
     * create layout for server administration stuffs
     */
    private VerticalLayout createAdminLayout() {
        /* so that it wraps */
        FlexLayout fl = new FlexLayout();
        fl.setJustifyContentMode(JustifyContentMode.BETWEEN);
        fl.setFlexWrap(FlexWrap.WRAP);
        fl.setWidthFull();

        linkService.getLinkEntityByCategory("Server Admin")
                .stream()
                .map(this::createAnchor)
                .forEach(fl::add);

        VerticalLayout vl = createVerticalLayout("Server Admin");
        vl.add(fl);
        return vl;
    }

    /**
     * create a button that open a link in a new tab when clicked
     * @param caption text inside the button
     * @param url which link the button should open to
     * @param icon icon of the button
     * @return button which acts as a link (Anchor)
     */
    private Anchor button(String caption, String url, Component icon) {
        Button button = new Button(caption, icon);
        button.setHeight("35px");
        button.setWidth("200px");
        Anchor anchor = new Anchor(url, button);
        anchor.setTarget("_blank");                 /* open in new tab */
        return anchor;
    }

    /**
     * create generic vertical layout, with no padding and no spacing
     * @param caption caption as H2
     * @return created vertical layout
     */
    private VerticalLayout createVerticalLayout(String caption) {
        VerticalLayout vl = new VerticalLayout();
        vl.add(new H2(caption));
        //vl.getStyle().set("background-color", "#dddddd");
        vl.setPadding(false);
        vl.setSpacing(false);
        vl.setMargin(true);
        return vl;
    }

    private Anchor createAnchor(LinkEntity linkEntity) {
        Image icon = new Image(linkEntity.getImageName(), "");
        icon.setHeight("25px");
        return button(linkEntity.getTitle(), linkEntity.getUrl(), icon);
    }
}

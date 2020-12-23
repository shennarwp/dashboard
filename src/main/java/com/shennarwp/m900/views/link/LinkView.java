package com.shennarwp.m900.views.link;

import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
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

import java.util.List;

/**
 * this view contains links / shortcuts to various sites
 * similar to how bookmark page works
 */
@Route(value = "hello", layout = MainView.class)
@PageTitle("Links - M900 Dashboard")
@RouteAlias(value = "", layout = MainView.class)
@CssImport("./styles/views/link-view.css")
public class LinkView extends FlexLayout {

    /**
     * constructor, create two flex layouts, left and right, spaced evenly
     */
    public LinkView() {
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

        /* links to be added */
        Anchor element = button("Element Client", "https://m900.shennarwp.com/element/", FontAwesome.Regular.COMMENTS.create());
        Anchor bitwarden = button("Bitwarden", "https://m900.shennarwp.com/bw/", FontAwesome.Solid.KEY.create());
        fl.add(element, bitwarden);

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

        /* links to be added */
        Anchor gfDockerContainer = button("Docker Containers", "https://m900.shennarwp.com/grafana/d/dc/docker-containers", FontAwesome.Brands.DOCKER.create());
        Anchor gfDockerHost = button("Docker Host", "https://m900.shennarwp.com/grafana/d/doh/docker-host", FontAwesome.Solid.SERVER.create());
        Anchor gfHostProcess = button("Host Processes", "https://m900.shennarwp.com/grafana/d/prh/host-processes", FontAwesome.Solid.LIST.create());
        Anchor gfMonitorServices = button("Monitor Services", "https://m900.shennarwp.com/grafana/d/mon/monitor-services", FontAwesome.Brands.WATCHMAN_MONITORING.create());

        Anchor gfNginx = button("Nginx","https://m900.shennarwp.com/grafana/d/ngx/nginx", FontAwesome.Solid.CLOUD.create());
        Anchor gfPihole = button("Pihole", "https://m900.shennarwp.com/grafana/d/pih/pihole", FontAwesome.Solid.AD.create());
        Anchor gfSynapse = button("Synapse", "https://m900.shennarwp.com/grafana/d/syn/synapse", FontAwesome.Solid.CERTIFICATE.create());
        Anchor gfTransmission = button("Transmission", "https://m900.shennarwp.com/grafana/d/tra/transmission", FontAwesome.Solid.COMPACT_DISC.create());

        Anchor gfF2b = button("Fail2ban", "https://m900.shennarwp.com/grafana/d/f2b/fail2ban-banned-locations", FontAwesome.Solid.BAN.create());

        fl.add(gfDockerContainer, gfDockerHost, gfHostProcess, gfMonitorServices);
        fl.add(gfNginx, gfPihole, gfSynapse, gfTransmission);
        fl.add(gfF2b);

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

        /* links to be added */
        Anchor jenkinsM900 = button("Jenkins M900", "https://m900.shennarwp.com/jenkins/", FontAwesome.Brands.JENKINS.create());
        Anchor jenkinsAlpinesky = button("Jenkins Alpinesky", "https://shennarwp.com/jenkins/", FontAwesome.Brands.JENKINS.create());
        Anchor gitea = button("Gitea", "https://m900.shennarwp.com/git/shennarwp", FontAwesome.Brands.GIT_ALT.create());
        Anchor vscode = button("VSCode", "https://m900.shennarwp.com/vscode/", FontAwesome.Solid.CODE.create());

        fl.add(jenkinsM900, jenkinsAlpinesky, gitea, vscode);

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

        /* links to be added */
        Anchor portainerM900 = button("Portainer M900", "https://m900.shennarwp.com/portainer/", FontAwesome.Solid.CHESS_ROOK.create());
        Anchor portainerAlpineSky = button("Portainer Alpinesky", "https://shennarwp.com/portainer/", FontAwesome.Solid.CHESS_ROOK.create());
        Anchor kibana = button("Kibana", "https://m900.shennarwp.com/kibana/", FontAwesome.Solid.RECEIPT.create());
        Anchor prometheus = button("Prometheus", "https://m900.shennarwp.com/prometheus/", FontAwesome.Solid.FIRE.create());

        fl.add(portainerM900, portainerAlpineSky, kibana, prometheus);

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
}

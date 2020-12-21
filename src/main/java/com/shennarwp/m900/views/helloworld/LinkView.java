package com.shennarwp.m900.views.helloworld;

import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
import com.shennarwp.m900.util.WeatherClient;
import com.shennarwp.m900.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;

@Route(value = "hello", layout = MainView.class)
@PageTitle("Links - M900 Dashboard")
//@JsModule("./styles/shared-styles.js")
@RouteAlias(value = "", layout = MainView.class)
public class LinkView extends FlexLayout {

    public LinkView() {
        setId("linkview");

        FlexLayout layout = new FlexLayout();
        layout.setFlexWrap(FlexWrap.WRAP);
        layout.add(createGrafanaLayout());
        layout.add(createDevToolsLayout());
        layout.add(createAdminLayout());
        add(layout);

        VerticalLayout layout2 = new VerticalLayout();
        layout2.add(createOthersLayout());
        layout2.add(createWeatherLayout());
        add(layout2);
    }

    private VerticalLayout createWeatherLayout() {
        WeatherClient wc = new WeatherClient();
        List<String> currentWeather =  wc.getWeatherDetail();

        Image icon = new Image(currentWeather.get(2), "weather icon");
        icon.setWidth("100px");
        icon.setHeight("100px");

        VerticalLayout ver1 = createVerticalLayout("Weather - Saarbrücken");
        ver1.add(icon);
        ver1.add(new H3(currentWeather.get(0) + " °C"));
        ver1.add(new H4(currentWeather.get(1)));

        //ver1.setWidth("35%");
        return ver1;
    }

    private VerticalLayout createOthersLayout() {
        FlexLayout hor1 = new FlexLayout();
        hor1.setJustifyContentMode(JustifyContentMode.BETWEEN);
        hor1.setFlexWrap(FlexWrap.WRAP);

        Anchor element = button("Element Client", "https://m900.shennarwp.com/element/", FontAwesome.Regular.COMMENTS.create());
        Anchor bitwarden = button("Bitwarden", "https://m900.shennarwp.com/bw/", FontAwesome.Solid.KEY.create());
        hor1.add(element, bitwarden);

        VerticalLayout ver1 = createVerticalLayout("Others");
        //ver1.setWidth("35%");
        ver1.add(hor1);
        return ver1;
    }

    private VerticalLayout createGrafanaLayout() {
        FlexLayout hor1 = new FlexLayout();
        hor1.setJustifyContentMode(JustifyContentMode.BETWEEN);
        hor1.setFlexWrap(FlexWrap.WRAP);

        Anchor gfDockerContainer = button("Docker Containers", "https://m900.shennarwp.com/grafana/d/dc/docker-containers", FontAwesome.Brands.DOCKER.create());
        Anchor gfDockerHost = button("Docker Host", "https://m900.shennarwp.com/grafana/d/doh/docker-host", FontAwesome.Solid.SERVER.create());
        Anchor gfHostProcess = button("Host Processes", "https://m900.shennarwp.com/grafana/d/prh/host-processes", FontAwesome.Solid.LIST.create());
        Anchor gfMonitorServices = button("Monitor Services", "https://m900.shennarwp.com/grafana/d/mon/monitor-services", FontAwesome.Brands.WATCHMAN_MONITORING.create());

        Anchor gfNginx = button("Nginx","https://m900.shennarwp.com/grafana/d/ngx/nginx", FontAwesome.Solid.CLOUD.create());
        Anchor gfPihole = button("Pihole", "https://m900.shennarwp.com/grafana/d/pih/pihole", FontAwesome.Solid.AD.create());
        Anchor gfSynapse = button("Synapse", "https://m900.shennarwp.com/grafana/d/syn/synapse", FontAwesome.Solid.CERTIFICATE.create());
        Anchor gfTransmission = button("Transmission", "https://m900.shennarwp.com/grafana/d/tra/transmission", FontAwesome.Solid.COMPACT_DISC.create());

        Anchor gfF2b = button("Fail2ban", "https://m900.shennarwp.com/grafana/d/f2b/fail2ban-banned-locations", FontAwesome.Solid.BAN.create());

        hor1.add(gfDockerContainer, gfDockerHost, gfHostProcess, gfMonitorServices);
        hor1.add(gfNginx, gfPihole, gfSynapse, gfTransmission);
        hor1.add(gfF2b);

        VerticalLayout ver1 = createVerticalLayout("Grafana Dashboards");
        ver1.setWidth("80%");
        ver1.add(hor1);
        return ver1;
    }

    private VerticalLayout createDevToolsLayout() {
        FlexLayout hor1 = new FlexLayout();
        hor1.setJustifyContentMode(JustifyContentMode.BETWEEN);
        hor1.setFlexWrap(FlexWrap.WRAP);

        Anchor jenkinsM900 = button("Jenkins M900", "https://m900.shennarwp.com/jenkins/", FontAwesome.Brands.JENKINS.create());
        Anchor jenkinsAlpinesky = button("Jenkins Alpinesky", "https://shennarwp.com/jenkins/", FontAwesome.Brands.JENKINS.create());
        Anchor gitea = button("Gitea", "https://m900.shennarwp.com/git/shennarwp", FontAwesome.Brands.GIT_ALT.create());
        Anchor vscode = button("VSCode", "https://m900.shennarwp.com/vscode/", FontAwesome.Solid.CODE.create());

        hor1.add(jenkinsM900, jenkinsAlpinesky, gitea, vscode);

        VerticalLayout ver1 = createVerticalLayout("Dev Tools");
        ver1.setWidth("80%");
        ver1.add(hor1);
        return ver1;
    }

    private VerticalLayout createAdminLayout() {
        FlexLayout hor1 = new FlexLayout();
        hor1.setJustifyContentMode(JustifyContentMode.BETWEEN);
        hor1.setFlexWrap(FlexWrap.WRAP);

        Anchor portainerM900 = button("Portainer M900", "https://m900.shennarwp.com/portainer/", FontAwesome.Solid.CHESS_ROOK.create());
        Anchor portainerAlpineSky = button("Portainer Alpinesky", "https://shennarwp.com/portainer/", FontAwesome.Solid.CHESS_ROOK.create());
        Anchor kibana = button("Kibana", "https://m900.shennarwp.com/kibana/", FontAwesome.Solid.RECEIPT.create());
        Anchor prometheus = button("Prometheus", "https://m900.shennarwp.com/prometheus/", FontAwesome.Solid.FIRE.create());

        hor1.add(portainerM900, portainerAlpineSky, kibana, prometheus);

        VerticalLayout ver1 = createVerticalLayout("Server Admin");
        ver1.setWidth("80%");
        ver1.add(hor1);
        return ver1;
    }

    private Anchor button(String caption, String url, Component icon) {
        Button button = new Button(caption, icon);
        button.setHeight("35px");
        button.setWidth("220px");
        Anchor anchor = new Anchor(url, button);
        anchor.setTarget("_blank");
        return anchor;
    }

    private HorizontalLayout createLayout() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setPadding(false);
        hl.setSpacing(true);
        return hl;
    }

    private VerticalLayout createVerticalLayout(String caption) {
        VerticalLayout vl = new VerticalLayout();
        vl.add(new H2(caption));
        //vl.getStyle().set("background-color", "#dddddd");
        vl.setPadding(true);
        vl.setSpacing(false);
        vl.setMargin(true);
        return vl;
    }

}

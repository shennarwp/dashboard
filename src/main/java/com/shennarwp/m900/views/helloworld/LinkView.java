package com.shennarwp.m900.views.helloworld;

import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
import com.shennarwp.m900.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "hello", layout = MainView.class)
@PageTitle("Links - M900 Dashboard")
//@CssImport("./styles/views/linkview/hello-world-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class LinkView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public LinkView() {
        setId("linkview");

        //setWidth("1300px");
        //setWidthFull();

        setSpacing(true);
        setPadding(true);
        setMargin(true);

        VerticalLayout layout = new VerticalLayout();

        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setMargin(false);

        layout.add(createGrafanaLayout());
        layout.add(createDevToolsLayout());
        layout.add(createAdminLayout());
        add(layout);

        VerticalLayout layout2 = new VerticalLayout();
        layout2.setPadding(false);
        layout2.setSpacing(false);
        layout2.setMargin(false);
        //layout2.setAlignSelf(Alignment.END);
        //layout2.setAlignItems(Alignment.END);
        //layout2.setJustifyContentMode(JustifyContentMode.END);
        //layout2.getStyle().set("margin-left", "auto");

        layout2.add(createOthersLayout());
        add(layout2);



    }

    private VerticalLayout createOthersLayout() {
        Anchor element = button("Element Client", "https://m900.shennarwp.com/element/", FontAwesome.Regular.COMMENTS.create());
        Anchor bitwarden = button("Bitwarden", "https://m900.shennarwp.com/bw/", FontAwesome.Solid.KEY.create());
        VerticalLayout ver1 = createVerticalLayout("Others");
        ver1.add(element, bitwarden);
        return ver1;
    }

    private VerticalLayout createGrafanaLayout() {
        Anchor gfDockerContainer = button("Docker Containers", "https://m900.shennarwp.com/grafana/d/dc/docker-containers", FontAwesome.Brands.DOCKER.create());
        Anchor gfDockerHost = button("Docker Host", "https://m900.shennarwp.com/grafana/d/doh/docker-host", FontAwesome.Solid.SERVER.create());
        Anchor gfHostProcess = button("Host Processes", "https://m900.shennarwp.com/grafana/d/prh/host-processes", FontAwesome.Solid.LIST.create());
        Anchor gfMonitorServices = button("Monitor Services", "https://m900.shennarwp.com/grafana/d/mon/monitor-services", FontAwesome.Brands.WATCHMAN_MONITORING.create());

        HorizontalLayout hor1 = createLayout();
        hor1.add(gfDockerContainer, gfDockerHost, gfHostProcess, gfMonitorServices);

        Anchor gfNginx = button("Nginx","https://m900.shennarwp.com/grafana/d/ngx/nginx", FontAwesome.Solid.CLOUD.create());
        Anchor gfPihole = button("Pihole", "https://m900.shennarwp.com/grafana/d/pih/pihole", FontAwesome.Solid.AD.create());
        Anchor gfSynapse = button("Synapse", "https://m900.shennarwp.com/grafana/d/syn/synapse", FontAwesome.Solid.CERTIFICATE.create());
        Anchor gfTransmission = button("Transmission", "https://m900.shennarwp.com/grafana/d/tra/transmission", FontAwesome.Solid.COMPACT_DISC.create());

        HorizontalLayout hor2 = createLayout();
        hor2.add(gfNginx, gfPihole, gfSynapse, gfTransmission);

        Anchor gfF2b = button("Fail2ban", "https://m900.shennarwp.com/grafana/d/f2b/fail2ban-banned-locations", FontAwesome.Solid.BAN.create());

        HorizontalLayout hor3 = createLayout();
        hor3.add(gfF2b);

        VerticalLayout ver1 = createVerticalLayout("Grafana Dashboards");
        ver1.add(hor1, hor2, hor3);

        return ver1;
    }

    private VerticalLayout createDevToolsLayout() {
        Anchor jenkinsM900 = button("Jenkins M900", "https://m900.shennarwp.com/jenkins/", FontAwesome.Brands.JENKINS.create());
        Anchor jenkinsAlpinesky = button("Jenkins Alpinesky", "https://shennarwp.com/jenkins/", FontAwesome.Brands.JENKINS.create());
        Anchor gitea = button("Gitea", "https://m900.shennarwp.com/git/shennarwp", FontAwesome.Brands.GIT_ALT.create());
        Anchor vscode = button("VSCode", "https://m900.shennarwp.com/vscode/", FontAwesome.Solid.CODE.create());

        HorizontalLayout hor1 = createLayout();
        hor1.add(jenkinsM900, jenkinsAlpinesky, gitea, vscode);

        VerticalLayout ver1 = createVerticalLayout("Dev Tools");
        ver1.add(hor1);
        return ver1;
    }

    private VerticalLayout createAdminLayout() {
        Anchor portainerM900 = button("Portainer M900", "https://m900.shennarwp.com/portainer/", FontAwesome.Solid.CHESS_ROOK.create());
        Anchor portainerAlpineSky = button("Portainer Alpinesky", "https://shennarwp.com/portainer/", FontAwesome.Solid.CHESS_ROOK.create());
        Anchor kibana = button("Kibana", "https://m900.shennarwp.com/kibana/", FontAwesome.Solid.RECEIPT.create());
        Anchor prometheus = button("Prometheus", "https://m900.shennarwp.com/prometheus/", FontAwesome.Solid.FIRE.create());

        HorizontalLayout hor1 = createLayout();
        hor1.add(portainerM900, portainerAlpineSky, kibana, prometheus);

        VerticalLayout ver1 = createVerticalLayout("Server Admin");
        ver1.add(hor1);
        return ver1;
    }

    private Anchor button(String caption, String url, Component icon) {
        Button button = new Button(caption, icon);
        button.setClassName("button");
        button.setHeight("45px");
        button.setWidth("220px");
        Anchor anchor = new Anchor(url, button);
        anchor.setTarget("_blank");
        return anchor;
    }

    private HorizontalLayout createLayout() {
        HorizontalLayout hl = new HorizontalLayout();
        //hl.setWidthFull();
        //add(hl);
        hl.setPadding(false);
        hl.setSpacing(true);
        return hl;
    }

    private VerticalLayout createVerticalLayout(String caption) {
        VerticalLayout vl = new VerticalLayout();
        //vl.setWidthFull();
        vl.add(new H3(caption));
        //add(vl);
        //vl.getStyle().set("background-color", "#dddddd");
        vl.setPadding(true);
        vl.setSpacing(false);
        vl.setMargin(true);
        return vl;
    }

}

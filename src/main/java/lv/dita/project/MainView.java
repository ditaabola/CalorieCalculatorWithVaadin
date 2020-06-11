
package lv.dita.project;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
import lv.dita.project.data.SessionHandler;
import lv.dita.project.layouts.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("")
@PageTitle("Login | Calorie calculator")
@PreserveOnRefresh
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@HtmlImport("styles/shared-styles.html")
public class MainView extends VerticalLayout {

    public MainView() {

        Icon logo = new Icon(VaadinIcon.CALC);
        logo.setSize("80px");
        logo.setColor("#27496d");
        logo.getStyle().set("padding", "0");
        add(logo);

        Button close = new Button("Close session", event -> {
            VaadinSession.getCurrent().getSession().invalidate();
            UI.getCurrent().getPage().reload();
            SessionHandler.setUserEEr(0);
            SessionHandler.setActivitiesCalories(0);
            SessionHandler.setFoodCalories(0);
        });
        close.getStyle().set("margin-left", "91%");
        add(close);

        H1 header = new H1("ENJOY OUR FIRST VAADIN CALCULATOR APP");
        header.getStyle().set("margin-top", "10px");
        add(header);

        Tab tab1 = new Tab("CALCULATE BMI & EER");
        CalculateBMILayout page1 = new CalculateBMILayout();
        page1.setVisible(true);

        Tab tab2 = new Tab("CALCULATE CONSUMED CALORIES");
        CaloriesEatenLayout page2 = new CaloriesEatenLayout();
        page2.setVisible(false);
        page2.setSizeFull();
        page2.getAlignItems();

        Tab tab3 = new Tab("CALCULATE BURNT CALORIES");
        CaloriesBurnedLayout page3 = new CaloriesBurnedLayout();
        page3.setVisible(false);

        Tab tab4 = new Tab ("YOUR DATA SUMMARY");
        UsersResultsSummaryLayoutv2 page4 = new UsersResultsSummaryLayoutv2();
        page4.setVisible(false);
        page4.setSizeFull();
        page4.getAlignItems();

        Tab tab5 = new Tab("FOOD DATABASE");
        FoodLayout page5 = new FoodLayout();
        page5.setVisible(false);

        Tab tab6 = new Tab("ACTIVITIES DATABASE");
        ActivitiesLayout page6 = new ActivitiesLayout();
        page6.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();

        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        tabsToPages.put(tab4, page4);
        tabsToPages.put(tab5, page5);
        tabsToPages.put(tab6, page6);

        Tabs tabs = new Tabs(tab1, tab2, tab3, tab4, tab5, tab6);
        tabs.setSizeFull();
        tabs.getStyle().set("margin-top", "-20px");
        Div pages = new Div(page1, page2, page3, page4, page5, page6);
        pages.setWidthFull();

        Set<Component> pagesShown = Stream.of(page1)
                .collect(Collectors.toSet());

        tabs.addSelectedChangeListener(event -> {
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();

            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);

            pagesShown.add(selectedPage);
        });

        add(tabs, pages);

        tabs.setFlexGrowForEnclosedTabs(1);
        setClassName("main-layout");
    }
}
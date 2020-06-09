
package lv.dita.project;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import lv.dita.project.layouts.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route("")
@PreserveOnRefresh
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@HtmlImport("styles/shared-styles.html")
public class MainView extends VerticalLayout {

    public MainView() {

        Tab tab1 = new Tab("ACTIVITIES");
        ActivitiesLayout page1 = new ActivitiesLayout();
        page1.setVisible(true);

        Tab tab2 = new Tab("FOOD");
        FoodLayout page2 = new FoodLayout();
        page2.setVisible(false);

        Tab tab3 = new Tab("CALCULATE BMI & EER");
        CalculateBMIv3Layout page3 = new CalculateBMIv3Layout();
        page3.setVisible(false);

        Tab tab4 = new Tab("CALCULATE CALORIES BURNED");
        CaloriesBurnedLayout2 page4 = new CaloriesBurnedLayout2();
        page4.setVisible(false);

        Tab tab5 = new Tab("CALCULATE CALORIES EATEN");
        CaloriesEatenLayout page5 = new CaloriesEatenLayout();
        page5.setVisible(false);
        page5.setSizeFull();
        page5.getAlignItems();

        Tab tab6 = new Tab ("YOUR ALL RESULTS");
        UserResultsSummaryLayout page6 = new UserResultsSummaryLayout();
        page6.setVisible(false);
        page6.setSizeFull();
        page6.getAlignItems();

        Map<Tab, Component> tabsToPages = new HashMap<>();

        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        tabsToPages.put(tab4, page4);
        tabsToPages.put(tab5, page5);
        tabsToPages.put(tab6, page6);

        Tabs tabs = new Tabs(tab1, tab2, tab3, tab4, tab5, tab6);
        tabs.setSizeFull();
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

//        });
    }
}
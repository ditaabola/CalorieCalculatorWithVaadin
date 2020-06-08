
package lv.dita.project;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import lv.dita.project.data.ActivityLogger;
import lv.dita.project.layouts.ActivitiesLayout;
import lv.dita.project.layouts.CalculateBMIv3Layout;
import lv.dita.project.layouts.CaloriesBurnedLayout;
import lv.dita.project.layouts.FoodLayout;
import org.springframework.beans.factory.annotation.Autowired;

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
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@HtmlImport("styles/shared-styles.html")
public class MainView extends VerticalLayout {


    public MainView() {

        Tab tab1 = new Tab("CALCULATE BMI");
        CalculateBMIv3Layout page1 = new CalculateBMIv3Layout();
        page1.setVisible(true);

        Tab tab2 = new Tab("ACTIVITIES");
        ActivitiesLayout page2 = new ActivitiesLayout();
        page2.setVisible(false);

        Tab tab3 = new Tab("FOOD");
        FoodLayout page3 = new FoodLayout();
        page3.setVisible(false);

        Tab tab4 = new Tab("CALCULATE CALORIES BURNED");
        CaloriesBurnedLayout page4 = new CaloriesBurnedLayout();
        page4.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();

        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        tabsToPages.put(tab4, page4);

        Tabs tabs = new Tabs(tab1, tab2, tab3, tab4);
        Div pages = new Div(page1, page2, page3, page4);

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
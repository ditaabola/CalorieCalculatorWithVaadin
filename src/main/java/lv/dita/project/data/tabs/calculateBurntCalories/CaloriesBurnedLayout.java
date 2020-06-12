package lv.dita.project.data.tabs.calculateBurntCalories;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CaloriesBurnedLayout extends VerticalLayout {
    private ActivityLoggerList form;

    public CaloriesBurnedLayout() {
        form = new ActivityLoggerList();
        Div content = new Div(form);
        content.addClassName("content");
        content.setSizeFull();

        add(content);
    }
}

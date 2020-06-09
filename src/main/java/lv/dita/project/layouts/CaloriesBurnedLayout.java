package lv.dita.project.layouts;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lv.dita.project.data.ActivityLogger;

public class CaloriesBurnedLayout extends VerticalLayout {
    private ActivityLogger form;

    public CaloriesBurnedLayout() {
        form = new ActivityLogger();
        Div content = new Div(form);
        content.addClassName("content");
        content.setSizeFull();

        add(content);
    }
}

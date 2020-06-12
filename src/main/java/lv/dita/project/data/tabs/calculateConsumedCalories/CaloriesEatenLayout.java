package lv.dita.project.data.tabs.calculateConsumedCalories;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CaloriesEatenLayout extends VerticalLayout {
    private FoodLogger form;

    public CaloriesEatenLayout(){
        form = new FoodLogger();
        Div content = new Div(form);
        content.addClassName("content");
        content.setSizeFull();

        add(content);
    }
}

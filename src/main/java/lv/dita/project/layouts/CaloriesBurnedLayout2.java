package lv.dita.project.layouts;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lv.dita.project.data.ActivityLogger2;

public class CaloriesBurnedLayout2 extends VerticalLayout {
    private ActivityLogger2 form;

    public CaloriesBurnedLayout2(){
        form = new ActivityLogger2();
        Div content = new Div(form);
        content.addClassName("content");
        content.setSizeFull();

        add(content);
    }
}

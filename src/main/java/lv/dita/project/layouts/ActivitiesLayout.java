package lv.dita.project.layouts;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;


public class ActivitiesLayout extends Div {


    public ActivitiesLayout() {

        Label lblProof = new Label("Tests, vai ir redzams activities");
        add(lblProof);

    }
}
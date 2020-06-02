package lv.dita.project.views;

import com.vaadin.flow.component.html.Label;
import lv.dita.project.MainView;
import org.jetbrains.annotations.NotNull;

public class AddLabel {

    private MainView mainView;
    public AddLabel(@NotNull MainView mainView) {

        Label lblProof = new Label("Tests, vai ir apvienojies");
        mainView.add(lblProof);
    }
}

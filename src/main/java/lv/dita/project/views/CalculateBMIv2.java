package lv.dita.project.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.MainView;
import lv.dita.project.data.Calculator2;
import org.jetbrains.annotations.NotNull;

public class CalculateBMIv2 {

    private NumberField height;
    private NumberField weight;
    private Button calculateBMI;
    private MainView mainView;
    private Label lblCalculatedBmi;
    private Label lblCommentBmi;

    public CalculateBMIv2(@NotNull MainView mainView) {

        this.mainView = mainView;

        height = new NumberField("Height in cm");
        mainView.add(height);
        height.setRequiredIndicatorVisible(true);

        weight = new NumberField("Weight in kg");
        mainView.add(weight);
        weight.setRequiredIndicatorVisible(true);

        lblCalculatedBmi = new Label();
        lblCommentBmi = new Label();

        //Te aprēķinus nedrīkst veikt!!!

        calculateBMI = new Button("Calculate BMI", new ComponentEventListener<ClickEvent<Button>>() {

            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {

                if (height.isEmpty() || weight.isEmpty()) {
                    lblCalculatedBmi.setText("Please enter data!");
                } else {

                    double heightUser = height.getValue();
                    double weightUser = weight.getValue();

                    String bmi = Calculator2.calculateBMI(weightUser, heightUser);
                    lblCalculatedBmi.setText("BMI: " + bmi);

                    String commentBmi = Calculator2.commentAboutUsersBmi(weightUser, heightUser);
                    lblCommentBmi.setText(commentBmi);
                }
            }
        });

        mainView.add(calculateBMI);
        mainView.add(lblCalculatedBmi);
        mainView.add(lblCommentBmi);

    }
}

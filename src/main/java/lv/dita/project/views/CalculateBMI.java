package lv.dita.project.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import lv.dita.project.MainView;
import lv.dita.project.data.Calculator2;
import org.jetbrains.annotations.NotNull;

public class CalculateBMI {

    private NumberField height;
    private NumberField weight;
    private Button calculateBMI;
    private MainView mainView;
    private TextField txtCalculatedBmi;

    public CalculateBMI(@NotNull MainView mainView) {
        this.mainView = mainView;

        height = new NumberField("Height in cm");
        mainView.add(height);

        weight = new NumberField("Weight in kg");
        mainView.add(weight);

        //Ja parādās šis, tad viss aiz tā vienkārši nerādās. Tāpēc ieliku metodē zem Click Listener
        //double heightUser = height.getValue();
        //double weightUser = weight.getValue();


        calculateBMI = new Button("Calculate BMI", new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {

                double heightUser = height.getValue();
                double weightUser = weight.getValue();
                String bmi = Calculator2.calculateBMI(weightUser, heightUser);
                txtCalculatedBmi.setValue(bmi);

            }
        });
        mainView.add(calculateBMI);
        txtCalculatedBmi = new TextField("IBM");
        mainView.add(txtCalculatedBmi);

        //calculateBMI.addClickListener(event -> Objects.requireNonNull(mainView).add(lblCalculatedBmi) );
        //calculateBMI.addClickListener(clickEvent -> Notification.show("Do not press this button again"));


    }

}

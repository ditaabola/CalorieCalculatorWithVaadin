package lv.dita.project.layouts;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.Calculator2;


public class CalculateBMIv3Layout extends VerticalLayout {

    private NumberField height;
    private NumberField weight;
    private Button calculateBMI;
    private Label lblCalculatedBmi;
    private Label lblCommentBmi;
    private Label lblCommentIbw;
    private RadioButtonGroup<String> gender;

    public CalculateBMIv3Layout(){

        try {

            height = new NumberField("Height in cm");
            add(height);
            height.setRequiredIndicatorVisible(true);

            weight = new NumberField("Weight in kg");
            add(weight);
            weight.setRequiredIndicatorVisible(true);

            gender = new RadioButtonGroup<>();

            gender.setLabel("Select gender");
            gender.setItems("Female", "Male");
            gender.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
            gender.setRequired(true);
            gender.setValue("Female");
            add(gender);

            lblCalculatedBmi = new Label();
            lblCommentBmi = new Label();
            lblCommentIbw = new Label();

            //Te aprēķinus nedrīkst veikt!!!

            calculateBMI = new Button("Calculate BMI", new ComponentEventListener<ClickEvent<Button>>() {

                @Override
                public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {

                    if (height.isEmpty() || weight.isEmpty()) {
                        lblCalculatedBmi.setText("Please enter data!");
                    } else {

                        double heightUser = height.getValue();
                        double weightUser = weight.getValue();
                        String genderUser = gender.getValue();

                        String bmi = Calculator2.calculateBMI(weightUser, heightUser);
                        lblCalculatedBmi.setText("BMI: " + bmi);

                        String commentBmi = Calculator2.commentAboutUsersBmi(weightUser, heightUser);
                        lblCommentBmi.setText(commentBmi);

                        String commentIbw = Calculator2.calculateIBW(genderUser, heightUser);
                        lblCommentIbw.setText(commentIbw);
                    }
                }
            });

            add(calculateBMI);
            add(lblCalculatedBmi);
            add(lblCommentBmi);
            add(lblCommentIbw);
        }catch (Exception e){
        }

    }
}

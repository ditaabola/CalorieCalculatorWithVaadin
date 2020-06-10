package lv.dita.project.layouts;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.Calculator;
import lv.dita.project.data.enums.DailyActivityLevel;
import lv.dita.project.data.enums.PersonsGender;

public class CalculateBMILayout extends VerticalLayout {

    private NumberField height;
    private NumberField weight;
    private IntegerField age;
    private Button calculate;
    private Label lblCalculatedBmi;
    private Label lblCommentBmi;
    private Label lblCommentIbw;
    private Label lblCommentEer;
    private RadioButtonGroup<PersonsGender> gender;
    private ComboBox<DailyActivityLevel> dailyActivityLevel;

    public CalculateBMILayout() {

        try {

            height = new NumberField("Height in cm");
            add(height);
            height.setRequiredIndicatorVisible(true);

            weight = new NumberField("Weight in kg");
            add(weight);
            weight.setRequiredIndicatorVisible(true);

            age = new IntegerField("Age");
            add(age);
            age.setRequiredIndicatorVisible(true);

            gender = new RadioButtonGroup<>();
            gender.setLabel("Select gender");
            gender.setItems(PersonsGender.values());
            gender.setRequired(true);
            gender.setValue(PersonsGender.FEMALE);
            add(gender);

            dailyActivityLevel = new ComboBox<>();
            dailyActivityLevel.setPlaceholder("Select activity level");
            dailyActivityLevel.setItems(DailyActivityLevel.values());
            dailyActivityLevel.setRequired(true);
            add(dailyActivityLevel);

            lblCalculatedBmi = new Label();
            lblCommentBmi = new Label();
            lblCommentIbw = new Label();
            lblCommentEer = new Label();

            calculate = new Button("Calculate", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {

                if (height.isEmpty() || weight.isEmpty() || age.isEmpty() || dailyActivityLevel.isEmpty()) {
                    lblCalculatedBmi.setText("Please enter data!");
                } else {

                    double heightUser = height.getValue();
                    double weightUser = weight.getValue();
                    PersonsGender genderUser = gender.getValue();
                    int ageUser = age.getValue();
                    DailyActivityLevel actLevUser = dailyActivityLevel.getValue();

                    String bmi = Calculator.calculateBMI(weightUser, heightUser);
                    lblCalculatedBmi.setText("BMI: " + bmi);

                    String commentBmi = Calculator.commentAboutUsersBmi(weightUser, heightUser);
                    lblCommentBmi.setText(commentBmi);

                    String commentIbw = Calculator.calculateIBW(genderUser, heightUser);
                    lblCommentIbw.setText(commentIbw);

                    String commentEer = Calculator.calculateEER(genderUser, ageUser, weightUser,
                            heightUser, actLevUser);
                    lblCommentEer.setText(commentEer);
                }
            });
            add(calculate);
            add(lblCalculatedBmi);
            add(lblCommentBmi);
            add(lblCommentIbw);
            add(lblCommentEer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

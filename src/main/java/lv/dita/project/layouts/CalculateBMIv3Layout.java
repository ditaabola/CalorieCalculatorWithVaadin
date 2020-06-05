package lv.dita.project.layouts;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.Calculator2;
import lv.dita.project.data.enums.DailyActivityLevel;
import lv.dita.project.data.enums.PersonsGender;


public class CalculateBMIv3Layout extends VerticalLayout {

    private NumberField height;
    private NumberField weight;
    private IntegerField age;
    private Button calculate;
    private Label lblCalculatedBmi;
    private Label lblCommentBmi;
    private Label lblCommentIbw;
    private Label lblCommentEer;
    private RadioButtonGroup<PersonsGender> gender;
//    private RadioButtonGroup<DailyActivityLevel> activityLevel;

    private ComboBox<DailyActivityLevel> dailyActivityLevel;

    public CalculateBMIv3Layout() {

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
            gender.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
            gender.setRequired(true);
            gender.setValue(PersonsGender.FEMALE);
            add(gender);

//            activityLevel = new RadioButtonGroup<>();
//            activityLevel.setLabel("Select activity level");
//            activityLevel.setItems(DailyActivityLevel.values());
//            activityLevel.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
//            activityLevel.setRequired(true);
//            activityLevel.setValue(DailyActivityLevel.SEDENTARY);
//            add(activityLevel);

            dailyActivityLevel = new ComboBox<>();
            dailyActivityLevel.setPlaceholder("Select activity level");
            dailyActivityLevel.setItems(DailyActivityLevel.values());
            dailyActivityLevel.setRequired(true);
            add(dailyActivityLevel);

            lblCalculatedBmi = new Label();
            lblCommentBmi = new Label();
            lblCommentIbw = new Label();
            lblCommentEer = new Label();

            //Te aprēķinus nedrīkst veikt!!!

            calculate = new Button("Calculate", new ComponentEventListener<ClickEvent<Button>>() {

                @Override
                public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {

                    if (height.isEmpty() || weight.isEmpty()) {
                        lblCalculatedBmi.setText("Please enter data!");
                    } else {

                        double heightUser = height.getValue();
                        double weightUser = weight.getValue();
                        PersonsGender genderUser = gender.getValue();
                        int ageUser = age.getValue();
                        DailyActivityLevel actLevUser = dailyActivityLevel.getValue();

                        String bmi = Calculator2.calculateBMI(weightUser, heightUser);
                        lblCalculatedBmi.setText("BMI: " + bmi);

                        String commentBmi = Calculator2.commentAboutUsersBmi(weightUser, heightUser);
                        lblCommentBmi.setText(commentBmi);

                        String commentIbw = Calculator2.calculateIBW(genderUser, heightUser);
                        lblCommentIbw.setText(commentIbw);

                        String commentEer = Calculator2.calculateEER(genderUser, ageUser, weightUser,
                                heightUser, actLevUser);
                        lblCommentEer.setText(commentEer);
                    }
                }
            });
            add(calculate);
            add(lblCalculatedBmi);
            add(lblCommentBmi);
            add(lblCommentIbw);
            add(lblCommentEer);
        } catch (Exception e) {
        }
    }
}

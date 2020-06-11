package lv.dita.project.layouts;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.Calculator;
import lv.dita.project.data.enums.DailyActivityLevel;
import lv.dita.project.data.enums.PersonsGender;
import lv.dita.project.data.enums.WeightGoal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

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
    private ComboBox<WeightGoal> weightGoal;

    public CalculateBMILayout(){

        Div calculatorBlock = new Div();
        calculatorBlock.add(calculatorLayout());
        calculatorBlock.getStyle().set("margin-left", "160px");
        add(calculatorBlock);

        Div commentBlock = new Div();
        commentBlock.add(displayComments());
        commentBlock.getStyle().set("margin-left", "500px");
        commentBlock.getStyle().set("padding", "30px");
        commentBlock.getStyle().set("font-weight", "bold");
        add(commentBlock);
    }

    @Contract(" ->new")
    public @NotNull Component calculatorLayout() {

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
            dailyActivityLevel.getStyle().set("margin-top", "30px");
            add(dailyActivityLevel);

            weightGoal = new ComboBox<>();
            weightGoal.setPlaceholder("Select your weight goal");
            weightGoal.setItems(WeightGoal.values());
            weightGoal.setRequired(true);
            weightGoal.getStyle().set("margin-top", "30px");
            add(weightGoal);

            lblCalculatedBmi = new Label();
            lblCommentBmi = new Label();
            lblCommentIbw = new Label();
            lblCommentEer = new Label();

            calculate = new Button("Calculate", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                if (height.isEmpty() || weight.isEmpty() || age.isEmpty() || dailyActivityLevel.isEmpty() || weightGoal.isEmpty()) {
                    lblCalculatedBmi.setText("Please enter data!");
                } else {

                    double heightUser = height.getValue();
                    double weightUser = weight.getValue();
                    PersonsGender genderUser = gender.getValue();
                    int ageUser = age.getValue();
                    DailyActivityLevel actLevUser = dailyActivityLevel.getValue();
                    WeightGoal goalUser = weightGoal.getValue();

                    String bmi = Calculator.calculateBMI(weightUser, heightUser);
                    lblCalculatedBmi.setText("BMI: " + bmi + ".");

                    String commentBmi = Calculator.commentAboutUsersBmi(weightUser, heightUser);
                    lblCommentBmi.setText(commentBmi);

                    String commentIbw = Calculator.calculateIBW(genderUser, heightUser);
                    lblCommentIbw.setText(commentIbw);

                    String commentEer = Calculator.calculateEER(genderUser, ageUser, weightUser,
                            heightUser, actLevUser, goalUser);
                    DecimalFormat df = new DecimalFormat("##.##");

                    lblCommentEer.setText(commentEer);
                }
            });
            calculate.addClickShortcut(Key.ENTER);
            calculate.getStyle().set("margin-top", "34px");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HorizontalLayout(height, weight, age, gender,dailyActivityLevel, weightGoal, calculate);
    }

    @Contract(" ->new")
    private @NotNull Component displayComments() {
        return new VerticalLayout(lblCalculatedBmi, lblCommentBmi, lblCommentEer, lblCommentIbw);
    }
}

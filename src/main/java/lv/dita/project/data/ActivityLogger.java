package lv.dita.project.data;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import lv.dita.project.data.interfaces.DataRepository;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

public class ActivityLogger extends VerticalLayout {

    private Activity activity;
    private DataRepository repo = new MySqlDataRepository();
    private Select<String> activityTypes = new Select<String>();
    private Select<String> activitiesByType = new Select<String>();
    private IntegerField hours = new IntegerField();
    private IntegerField minutes = new IntegerField();
    private NumberField userWeight = new NumberField();
    private Button addToSelect = new Button();
    private Button calculateCaloriesBurned = new Button();
    private Button cancel = new Button();
    private Label lblCommentResult = new Label();
    private Label lblCalorieCalculation = new Label();

    public ActivityLogger() {
        addClassName("activity-logger");

        Div addOptions = new Div();
        addOptions.add(createSelectOptionLayout());
        addOptions.setWidthFull();

        Div addButtons = new Div();
        addButtons.add(createButtonsLayout());
        addButtons.setWidthFull();

        add(addOptions);
        add(addButtons);


        createCaloriesCalculationButton();
        add(calculateCaloriesBurned);
        add(lblCalorieCalculation);
    }

    @Contract(" -> new")
    private @NotNull Component createSelectOptionLayout() {
        creatingTypeSelectOption();
        creatingActivityNameSelectFromTypeOption();
        createWeightField();
        createHoursField();
        createMinutesField();
        //calories.setVisible(false);
        return new HorizontalLayout( activityTypes, activitiesByType, userWeight, hours,  minutes);
    }

    private  void createWeightField(){
        userWeight.setLabel("Weight in kg");
        userWeight.setRequiredIndicatorVisible(true);
        userWeight.setWidth("100px");
        userWeight.setMin(2d);
    }

    private void createMinutesField() {
        minutes.setLabel("Minutes");
        minutes.setRequiredIndicatorVisible(false);
        minutes.setWidth("100px");
        minutes.setMin(0);
    }

    private void createHoursField() {
        hours.setLabel("Hours");
        hours.setRequiredIndicatorVisible(false);
        hours.setWidth("100px");
        hours.setMin(0);
    }

    // izvēlas tipu
    private void creatingTypeSelectOption() {
        activityTypes.setLabel("Select activity type");
        List<ActivityType> result = repo.getList(ActivityType.class);
        activityTypes.setItems(result.stream().map(s -> s.getType()));
        activityTypes.setRequiredIndicatorVisible(true);
        activityTypes.addValueChangeListener(e -> {
            List<Activity> items = repo.getActivityByType(e.getValue());
            activitiesByType.setItems(items.stream().map(s -> s.getName()));
        });
    }

    // izvēlas aktivitāti (atkarigs no tipa)
    private void creatingActivityNameSelectFromTypeOption(){
        activitiesByType.setLabel("Select activity");
        List<Activity> res = repo.getList(Activity.class);
        activitiesByType.setItems(res.stream().map(s -> s.getName()));
        activitiesByType.setRequiredIndicatorVisible(true);
//        activitiesByType.addValueChangeListener(e->{
//
//        })
    }


    @Contract(" -> new")
    private @NotNull Component createButtonsLayout() {
        createAddActivityToGridButton();
        createResetChoiceButton();

        return new HorizontalLayout(addToSelect, cancel);
    }

    private void createAddActivityToGridButton(){
        addToSelect.setText("Add activity");
        addToSelect.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        addToSelect.addClickShortcut(Key.ENTER);
        addToSelect.addClickListener(e->{
            if (hours.isEmpty() & minutes.isEmpty() || userWeight.isEmpty()){
                Notification.show("Please enter data!").setDuration(1000);
            } else {
                int time = hours.getValue()*60 + minutes.getValue();
                double met = repo.getMetValueByName1(activitiesByType.getValue());

                repo.addActivityPerformed(new ActivityPerformed(0,
                        activitiesByType.getValue(),
                        met,
                        userWeight.getValue(),
                        time ));
                Notification.show("Activity added").setDuration(1000);
            }});
        add(addToSelect);
    }

    private void createResetChoiceButton() {
        cancel.setText("Reset the choice");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.addClickShortcut(Key.ESCAPE);
//        cancel.addClickListener(e -> {
//            activityTypes.clear();
//            activitiesByType.clear();
//            minutes.clear();
//            userWeight.clear();
//        });
        add(cancel);
    }

    //Pogai vajag citu vietu
    public void createCaloriesCalculationButton() {
        calculateCaloriesBurned.setText("Calculate burned calories");
        calculateCaloriesBurned.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        calculateCaloriesBurned.addClickListener(e -> {

            String res = calculateCaloriesBurned();
            lblCalorieCalculation.setText("The total of the calories you have burned is: " + res);
            lblCalorieCalculation.setVisible(true);
        });
    }
    //
    public String calculateCaloriesBurned() {
        double userWeight;
        int minutes;
        double activityMetValue;
        double caloriesBurned=0;
        //String activity = ""; not necessary
        DecimalFormat df = new DecimalFormat("##.##");
        List<ActivityPerformed> activityPerformedList = repo.getList(ActivityPerformed.class);
        for (ActivityPerformed activityPerformed : activityPerformedList) {
            userWeight = activityPerformed.getUser_weight();
            minutes = activityPerformed.getMinutes();
            activityMetValue = activityPerformed.getMet_value();
            double caloriesPerActivityBurned = (activityMetValue * 3.5 * userWeight) / 200 * minutes;
            caloriesBurned += caloriesPerActivityBurned;
        }
        return df.format(caloriesBurned);
    }
}

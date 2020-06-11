package lv.dita.project.layouts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;
import lv.dita.project.data.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class UsersResultsSummaryLayoutv2 extends VerticalLayout {
    private Component returnThings = new VerticalLayout();
    private Label lblCommentDataMissingWarning;
    private Label lblCommentEer;
    private Label lblCommentFood;
    private Label lblCommentActivities;
    private Label lblCommentBudget;
    private Div base;
    private Button generateUserData = new Button();
    private double userEer;
    private double eatenCalories;
    private double burntCalories;
    private double bd;

    public UsersResultsSummaryLayoutv2() {

        base = new Div();
        base.add(createButton());
        base.add(oneTimeThing());
        add(base);
        setHorizontalComponentAlignment(Alignment.CENTER);

    }

    @Contract(" -> new")
    private @NotNull Component createButton() {

        generateUserData = new Button("Generate my calorie data");
        generateUserData.getStyle().set("margin-left", "620px");
        generateUserData.getStyle().set("margin-bottom", "30px");
        generateUserData.addClickShortcut(Key.ENTER);
        generateUserData.addClickListener(e -> {

            getData();
            if (userEer == 0 && (eatenCalories == 0 || burntCalories == 0)) {
                base.add(createWarningLabel());
            } else {
                base.add(createInfoLabels());
            }

        });
        return new VerticalLayout(generateUserData, returnThings);

    }

    public double calculateCalorieBudget(double eer, double eaten, double burnt) {
        double budget = 0;
        budget = eer + burnt - eaten;
        return budget;
    }

    @Contract(" -> new")
    private @NotNull Component createWarningLabel() {
        lblCommentDataMissingWarning = new Label();
        lblCommentDataMissingWarning.setText("Please enter the data and do calculations first");
        lblCommentDataMissingWarning.getStyle().set("margin-left", "540px");
        lblCommentDataMissingWarning.getStyle().set("font-weight", "bold");
        base.remove(returnThings);
        returnThings = new VerticalLayout(lblCommentDataMissingWarning);
        return returnThings;
    }

    private void getData() {
        userEer = SessionHandler.getUserEEr();
        eatenCalories = SessionHandler.getFoodCalories();
        burntCalories = SessionHandler.getActivitiesCalories();
        bd = (calculateCalorieBudget(userEer, eatenCalories, burntCalories));
    }

    @Contract(" -> new")
    private @NotNull Component createInfoLabels() {
        lblCommentEer = new Label();
        lblCommentFood = new Label();
        lblCommentActivities = new Label();
        lblCommentBudget = new Label();

        getData();

        lblCommentEer.setText("Your calorie budget for a day is " + userEer + " calories");
        lblCommentEer.getStyle().set("margin-left", "550px");
        lblCommentEer.getStyle().set("font-weight", "bold");

        lblCommentFood.setText("You have eaten " + eatenCalories + " calories");
        lblCommentFood.getStyle().set("margin-left", "550px");
        lblCommentFood.getStyle().set("font-weight", "bold");

        lblCommentActivities.setText("You have burnt " + burntCalories + " calories");
        lblCommentActivities.getStyle().set("margin-left", "550px");
        lblCommentActivities.getStyle().set("font-weight", "bold");

        if (bd > 0) {
            lblCommentBudget.setText("You have " + bd + " calories left");
        } else {
            lblCommentBudget.setText("Better burn " + bd + " more calories today ");
        }
        lblCommentBudget.getStyle().set("margin-left", "550px");
        lblCommentBudget.getStyle().set("font-weight", "bold");

        base.remove(returnThings);
        returnThings = new VerticalLayout(lblCommentEer, lblCommentFood, lblCommentActivities, lblCommentBudget);
        return returnThings;
    }

    @Contract(" -> new")
    private @NotNull Component oneTimeThing() {
        lblCommentDataMissingWarning = new Label();
        lblCommentDataMissingWarning.setText("");
        returnThings = new VerticalLayout(lblCommentDataMissingWarning);
        return returnThings;
    }

}












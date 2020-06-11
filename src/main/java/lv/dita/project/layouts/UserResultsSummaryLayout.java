package lv.dita.project.layouts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;
import lv.dita.project.data.*;
import org.jetbrains.annotations.NotNull;

public class UserResultsSummaryLayout extends VerticalLayout {

    public UserResultsSummaryLayout() {
        add(generateDataForCurrentSession());
        setHorizontalComponentAlignment(Alignment.CENTER);
    }

    private @NotNull Component generateDataForCurrentSession() {
        Button generateUserData = new Button("Generate my calorie data", event -> {

            Label lblCommentEer = new Label();
            Label lblCommentFood = new Label();
            Label lblCommentActivities = new Label();
            Label lblCommentBudget = new Label();
            Label lblCommentDataMissingWarning = new Label();

            double userEer = SessionHandler.getUserEEr();
            double eatenCalories = SessionHandler.getFoodCalories();
            double burntCalories = SessionHandler.getActivitiesCalories();
            double bd = (calculateCalorieBudget(userEer, eatenCalories, burntCalories));

            if (userEer == 0 && (eatenCalories == 0 || burntCalories == 0)) {
                lblCommentDataMissingWarning.setText("Please enter the data and do calculations first!");
                lblCommentDataMissingWarning.setVisible(true);
                lblCommentDataMissingWarning.getStyle().set("margin-left", "540px");
                lblCommentDataMissingWarning.getStyle().set("font-weight", "bold");
                add(lblCommentDataMissingWarning);

            } else {
                lblCommentDataMissingWarning.setVisible(false);
                remove(lblCommentDataMissingWarning);
                lblCommentEer.setText("Your calorie budget for a day is " + userEer + " calories.");
                lblCommentFood.setText("You have eaten " + eatenCalories + " calories.");
                lblCommentActivities.setText("You have burnt " + burntCalories + " calories.");

                lblCommentEer.setVisible(true);
                lblCommentFood.setVisible(true);
                lblCommentActivities.setVisible(true);

                lblCommentEer.getStyle().set("margin-left", "550px");
                lblCommentEer.getStyle().set("font-weight", "bold");
                add(lblCommentEer);

                lblCommentFood.getStyle().set("margin-left", "550px");
                lblCommentFood.getStyle().set("font-weight", "bold");
                add(lblCommentFood);

                lblCommentActivities.getStyle().set("margin-left", "550px");
                lblCommentActivities.getStyle().set("font-weight", "bold");
                add(lblCommentActivities);

                if (bd > 0) {
                    lblCommentBudget.setText("You have " + bd + " calories left.");
                } else {
                    lblCommentBudget.setText("Better burn " + bd + " more calories today.");
                }

                lblCommentBudget.setVisible(true);
                lblCommentBudget.getStyle().set("margin-left", "550px");
                lblCommentBudget.getStyle().set("font-weight", "bold");
                add(lblCommentBudget);
            }
        });

        generateUserData.getStyle().set("margin-left", "620px");
        generateUserData.getStyle().set("margin-bottom", "30px");
        return generateUserData;
    }

    public double calculateCalorieBudget(double eer, double eaten, double burnt) {
        double budget = 0;
        budget = eer + burnt - eaten;

        return budget;
    }
}












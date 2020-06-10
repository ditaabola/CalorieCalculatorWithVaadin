package lv.dita.project.layouts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;
import lv.dita.project.data.*;
import java.text.DecimalFormat;


public class UserResultsSummaryLayout extends VerticalLayout {

    private DecimalFormat df = new DecimalFormat("##.##");

    public UserResultsSummaryLayout() {
        add(generateDataForCurrentSession());
        setHorizontalComponentAlignment(Alignment.CENTER);
    }

    private Component generateDataForCurrentSession() {
        Button generateUserData = new Button("Generate my calorie data", event -> {

            Span lblCommentEer = new Span();
            lblCommentEer.getStyle();
            double userEer = SessionHandler.getUserEEr();
            lblCommentEer.setText("The calorie budget suggested  for a day is " + df.format(userEer) + " calories");
            lblCommentEer.setVisible(true);
            add(lblCommentEer);
            Label lblCommentFood = new Label();
            double eatenCalories = SessionHandler.getFoodCalories();
            lblCommentFood.setText("You have eaten " + eatenCalories + " calories");
            lblCommentFood.setVisible(true);
            add(lblCommentFood);

            Label lblCommentActivities = new Label();
            double burntCalories = SessionHandler.getActivitiesCalories();
            lblCommentActivities.setText("You have burnt " + burntCalories + " calories");
            lblCommentActivities.setVisible(true);
            add(lblCommentActivities);

            Label lblCommentBudget = new Label();
            double bd = (calculateCalorieBudget(1500, eatenCalories, burntCalories));
            lblCommentBudget.setText("Your calorie budget for the rest of the day is " + df.format(bd) + " calories");
            lblCommentBudget.setVisible(true);
            lblCommentBudget.setSizeFull();
            add(lblCommentBudget);
        });

        return generateUserData;

    }

    public double calculateCalorieBudget(double eer, double eaten, double burnt) {
        double budget = 0;
        budget = eer + burnt - eaten;

        return budget;
    }
}












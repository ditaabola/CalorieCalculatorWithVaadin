package lv.dita.project.layouts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.server.VaadinSession;
import lv.dita.project.data.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class UsersResultsSummaryLayout extends VerticalLayout {
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
    DecimalFormat df = new DecimalFormat("##.##");

    public UsersResultsSummaryLayout() {

        base = new Div();
        base.add(createButton());
        base.add(oneTimeThing());
        add(base);
        setHorizontalComponentAlignment(Alignment.CENTER);
    }

    @Contract(" -> new")
    private @NotNull Component createButton() {

        generateUserData = new Button("Generate my calorie data");
        generateUserData.getStyle().set("margin-left", "560px");
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
        returnThings.setVisible(false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        returnThings.setVisible(true);
        returnThings = new VerticalLayout(lblCommentDataMissingWarning);
        return returnThings;
    }

    private void getData() {
        VaadinSession.getCurrent().getSession();
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

        lblCommentEer.setText("Your calorie budget for a day is " + df.format(userEer) + " calories");
        lblCommentEer.getStyle().set("margin-left", "550px");
        lblCommentEer.getStyle().set("font-weight", "bold");

        lblCommentFood.setText("You have eaten " + df.format(eatenCalories) + " calories");
        lblCommentFood.getStyle().set("margin-left", "550px");
        lblCommentFood.getStyle().set("font-weight", "bold");

        lblCommentActivities.setText("You have burnt " + df.format(burntCalories) + " calories");
        lblCommentActivities.getStyle().set("margin-left", "550px");
        lblCommentActivities.getStyle().set("font-weight", "bold");

        if (bd > 0) {
            lblCommentBudget.setText("You have " + df.format(bd) + " calories left");
        } else {
            lblCommentBudget.setText("Better burn " + df.format(bd) + " more calories today ");
        }
        lblCommentBudget.getStyle().set("margin-left", "550px");
        lblCommentBudget.getStyle().set("font-weight", "bold");

        returnThings.setVisible(false);
        base.remove(returnThings);
        returnThings.setVisible(false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        returnThings.setVisible(true);
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












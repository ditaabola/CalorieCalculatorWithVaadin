package lv.dita.project.layouts;

import com.vaadin.flow.component.Component;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.server.VaadinSession;
import lv.dita.project.data.*;
import lv.dita.project.data.activityWithMysql.ActivityLogger;

public class UserResultsSummaryLayout extends VerticalLayout {

    private Label lblCommentFood = new Label();
    private Label lblCommentActivities = new Label();
    private String resultFood = "";
    private String resultActivities = "";
    FoodLogger activeUserFood = new FoodLogger();
    ActivityLoggerList activeUserActivities = new ActivityLoggerList();

    public UserResultsSummaryLayout() {

        Div div = new Div();
        div.add(createLayout());
        add(div);

        Button generateUserData = new Button("Generate my calorie data", event -> {
            VaadinSession.getCurrent().getSession();

            resultFood = activeUserFood.calculateCalories();
            lblCommentFood.setText("You have eaten " + resultFood + " calories");
            lblCommentFood.setVisible(true);

            resultActivities = activeUserActivities.calculateCaloriesBurnedFromList();
            lblCommentActivities.setText("You have burned " + resultActivities + " calories");
            lblCommentActivities.setVisible(true);

        });

        add(generateUserData);

    }

    public Component createLayout(){

        return new HorizontalLayout(lblCommentFood, lblCommentActivities);
    }








}

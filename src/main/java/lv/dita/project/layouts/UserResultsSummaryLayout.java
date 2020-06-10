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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UserResultsSummaryLayout extends VerticalLayout {

    private String resultFood;
    private String resultActivities;
    FoodLogger activeUserFood = new FoodLogger();
    ActivityLoggerList activeUserActivities = new ActivityLoggerList();
    VaadinSession v = VaadinSession.getCurrent();
    List<ActivityPerformed2> list = new ArrayList<>();

    public UserResultsSummaryLayout() {
        add(generateDataForCurrentSession());
    }

    private Component generateDataForCurrentSession() {
            Button generateUserData = new Button("Generate my calorie data", event -> {
            v.getSession();
                Label lblCommentFood = new Label();
                resultFood = activeUserFood.calculateCalories();
                lblCommentFood.setText("You have eaten " + resultFood + " calories");
                lblCommentFood.setVisible(true);
                add(lblCommentFood);

                list = activeUserActivities.getActivitiesPerformedList();
                double calories = 0;
                for(ActivityPerformed2 act: list){
                    calories += act.getCalories();
                }
                Label lblCommentActivities = new Label();
                DecimalFormat df = new DecimalFormat("##.##");
                String resultActivities = df.format(calories);
                lblCommentActivities.setText("You have burned " + resultActivities + " calories");
                lblCommentActivities.setVisible(true);
                add(lblCommentActivities);

            });

        return generateUserData;

        }
    }












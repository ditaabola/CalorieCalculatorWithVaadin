package lv.dita.project.layouts;

import com.vaadin.flow.component.Component;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;
import lv.dita.project.data.*;
import lv.dita.project.data.activityWithMysql.ActivityLogger;

public class UserResultsSummaryLayout extends VerticalLayout {

    private Label lblCommentFood = new Label();
    private Label lblCommentActivities = new Label();

    public UserResultsSummaryLayout() {

        Div div = new Div();
        div.add(createLayout());
        add(div);

        FoodLogger activeUserFood = new FoodLogger();
        Calculator activeUserCalculation = new Calculator();
        ActivityLogger activeUserActivities = new ActivityLogger();
        String result = activeUserFood.totalCaloriesCalculated;

//        double calories = activeUserFood.getCaloriesEaten();

        lblCommentFood.setText("You have eaten " + result + " calories");
        lblCommentFood.setVisible(true);
//        lblCommentFood.setVerticalAlign(VerticalAlign.HIGH);


//        String activities = activeUserActivities.calculateCaloriesBurned();
//        lblCommentActivities.setText(activities);
//        lblCommentActivities.setVisible(true);
//        lblCommentActivities.setVerticalAlign(VerticalAlign.HIGH);

//        String calculation = activeUserCalculation.calculateEER();


    }

    public Component createLayout(){

        return new HorizontalLayout(lblCommentFood);
    }








}

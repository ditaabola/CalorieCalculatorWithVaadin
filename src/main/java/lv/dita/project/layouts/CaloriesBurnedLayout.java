package lv.dita.project.layouts;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lv.dita.project.data.*;

import javax.swing.*;
import java.util.List;

public class CaloriesBurnedLayout extends VerticalLayout {

    private MySqlDataRepository repo = new MySqlDataRepository();
    private ActivityLogger form;
    private Grid<ActivityPerformed> activitiesPerformed;

    public CaloriesBurnedLayout() {

        //pievienojas ievades lauki un pogas
        form = new ActivityLogger();

        //pievienojas tabula, kurā ievadīsies dienas aktivitātes
        createGridWithSelectedValues();
        Div content = new Div(form, activitiesPerformed);
        content.addClassName("content");
        content.setSizeFull();
        add(content);

//te vajag, ka pievieno aprēķinu pogas

    }


    public void createGridWithSelectedValues() {

        activitiesPerformed = new Grid<ActivityPerformed>();
        Grid.Column<ActivityPerformed> colName = activitiesPerformed.addColumn(ActivityPerformed::getName).setHeader("Name");
        Grid.Column<ActivityPerformed> colQuantity = activitiesPerformed.addColumn(ActivityPerformed::getMinutes).setHeader("Time in min");
        List result = repo.getList(ActivityPerformed.class);
        activitiesPerformed.setItems(result);
        activitiesPerformed.setWidth("400px");
        activitiesPerformed.setHeightByRows(true);
        activitiesPerformed.setVisible(true);
        activitiesPerformed.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

        add(activitiesPerformed);

    }


}

package lv.dita.project.layouts;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import lv.dita.project.data.Activity;
import lv.dita.project.data.MySqlDataRepository;

import java.util.List;


public class ActivitiesLayout extends Div {

    Grid<Activity> grid;

    public ActivitiesLayout() {

        Label lblProof = new Label("Tests, vai ir redzams activities");
        add(lblProof);

       //Taisām tabulu
        grid = new Grid<>();
        grid.addColumn(Activity::getName).setHeader("Name");
        grid.addColumn(Activity::getLevel).setHeader("Level");
        grid.addColumn(Activity::getMetValue).setHeader("MetValue");
        grid.addColumn(Activity::getType).setHeader("Type");

        MySqlDataRepository repo = new MySqlDataRepository();
        List result = repo.getList(Activity.class);
        grid.setItems(result);
        grid.setWidth("600px");
        //Jāizdomā, kā var sakārtot...esot iebūvēta funkcija, tik nemāku palaist
        //grid.getColumnByKey("Name").setSortable(true);
        add(grid);
    }


}
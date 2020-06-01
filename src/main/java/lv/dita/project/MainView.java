
package lv.dita.project;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    public MainView(@Autowired GreetService service) {

        NumberField height = new NumberField("Your height");
        add(height);
        double userHeight = height.getValue();
        NumberField weight = new NumberField("Your weight");
        add(weight);
        NumberField age = new NumberField("Your age");
        add(age);
        ComboBox gender = new ComboBox("Your gender");
        gender.setItems("Male", "Female", "Non-Binary");
        add(gender);
        ComboBox activityLevel = new ComboBox("Your usual activity level");
        activityLevel.setItems("Sedentary", "Light", "Moderate", "Active");
        add(activityLevel);
        Button submitButton = new Button("Calculate!");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(submitButton);
       // submitButton.addClickListener(buttonClickEvent -> add(new Label("eeee"+ userHeight*2)));

    }


}
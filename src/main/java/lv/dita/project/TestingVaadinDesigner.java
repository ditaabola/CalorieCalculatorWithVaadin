package lv.dita.project;

import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the testing-vaadin-designer template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("testing-vaadin-designer")
@JsModule("./testing-vaadin-designer.js")
public class TestingVaadinDesigner extends PolymerTemplate<TestingVaadinDesigner.TestingVaadinDesignerModel> {


    @Id("age")
    private Input age;
    @Id("height")
    private Input height;
    @Id("user-weight")
    private Input userWeight;
    @Id("gender")
    private Select gender;


//    @Id("vaadinSelect")
//    private Select vaadinSelect;
//    @Id("vaadinListBox")
//    private Element vaadinListBox;
//    @Id("vaadinItem1")
//    private Element vaadinItem1;
//    @Id("vaadinItem")
//    private Element vaadinItem;
//    @Id("vaadinItem2")
//    private Element vaadinItem2;

    /**
     * Creates a new TestingVaadinDesigner.
     */
    public TestingVaadinDesigner() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between TestingVaadinDesigner and testing-vaadin-designer
     */
    public interface TestingVaadinDesignerModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}

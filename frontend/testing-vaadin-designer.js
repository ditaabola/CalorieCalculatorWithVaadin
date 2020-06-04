import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-charts/src/vaadin-chart.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-grid/src/vaadin-grid-column.js';
import '@vaadin/vaadin-grid/src/vaadin-grid-column-group.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-radio-button/src/vaadin-radio-group.js';
import '@vaadin/vaadin-radio-button/src/vaadin-radio-button.js';

class TestingVaadinDesigner extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<h1 
id="header" class="header" 
style="margin-left: 40%; padding: 10px; width: 100%; color: #00909e;"
>Calorie calculator</h1>

`;
    }

    static get is() {
        return 'testing-vaadin-designer';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(TestingVaadinDesigner.is, TestingVaadinDesigner);

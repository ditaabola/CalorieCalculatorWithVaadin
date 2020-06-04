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
import '@vaadin/vaadin-text-field/src/vaadin-text-area.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@polymer/iron-icon/iron-icon.js';

class TestingVaadinDesigner extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<h1 id="header" class="header" style="margin-left: 40%; padding: 10px; width: 100%; color: #00909e;">Calorie calculator</h1>
<div>
 <p style="margin-left: 50px; margin-bottom: 10px; font-weight: bold;">Please select your gender: </p>
 <vaadin-radio-group theme="vertical" id="gender" class="gender" style="margin-left: 50px;" required value="on">
  <vaadin-radio-button id="male" class="male" checked>
   <b>Male</b>
   <div></div>
  </vaadin-radio-button>
  <vaadin-radio-button id="female" class="gender" checked>
   <b>Female</b>
   <div></div>
  </vaadin-radio-button>
 </vaadin-radio-group>
</div>
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

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
import '@vaadin/vaadin-crud/src/vaadin-crud-form.js';
import '@vaadin/vaadin-login/src/vaadin-login-form-wrapper.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-item.js';
import '@vaadin/vaadin-select/src/vaadin-select.js';
import '@vaadin/vaadin-list-box/src/vaadin-list-box.js';
import '@vaadin/vaadin-item/src/vaadin-item.js';
import '@vaadin/vaadin-grid/src/vaadin-grid-selection-column.js';
import '@vaadin/flow-frontend/vaadin-grid-flow-selection-column.js';
import '@vaadin/vaadin-grid-pro/src/vaadin-grid-pro-edit-select.js';
import '@vaadin/vaadin-select/src/vaadin-select-text-field.js';

class TestingVaadinDesigner extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
 
            </style>
            
<!-- HEADER ---------->
<div id="header">
 <h1 id="header" class="header" style="margin-left: 40%; padding: 10px; width: 100%; color: #00909e;">Calorie calculator</h1>
</div>

<!-- EER calculator form -------------------------->
<div style="margin-left: 100px; width: 400px;font-weight: bold; text-align: center; padding: 10px">
 <div id="eer-top" style="color: #00909e; padding-bottom: 10px">
  <h2 id="header-eer" class="header-eer" style="width: auto; color: #00909e">EER calculator</h2>
  <p id="eer-description" style="width: auto; padding:0;"><i>Calories/day to maintain the current weight</i></p>
 </div>
 <div id="user-input">
 
  <!-- Age --------------------------->
  <div id="user-age" style="margin-bottom: 20px; margin-top: 20px">
   <label for="age">Age:</label>
   <input type="number" id="age" name="age" placeholder="30" min="1" max="130" style="margin-left:200px; height: 22px;">
  </div>
  
  <!-- Height -------------------------->
  <div id="user-height" style="margin-bottom: 20px;">
   <label for="height">Height in centimeters:</label>
   <input type="number" id="height" name="height" placeholder="170" min="1" max="300" style="margin-left:67px; height: 22px;">
  </div>
  
  <!-- Weight -------------------------->
  <div id="user-weight" style="margin-bottom: 20px;">
   <label for="user-weight">Weight in kilograms:</label>
   <input type="number" id="user-weight" name="user-weight" placeholder="70" min="1" max="700" style="margin-left:80px; height: 22px;">
  </div>
  
  <!-- Gender -------------------------->
  <div id="user-gender" style="margin-bottom: 20px;">
   <div id="gender-selector" style="width: 50%; margin: 0 auto">
    <vaadin-radio-group theme="horizontal" id="gender" class="gender" required value="on">
     <vaadin-radio-button id="male" class="male">
      <b>Male</b>
      <div></div>
     </vaadin-radio-button>
     <vaadin-radio-button id="female" class="gender" checked>
      <b>Female</b>
      <div></div>
     </vaadin-radio-button>
    </vaadin-radio-group>
   </div>
   
        <!-- Activity level------------------------>
   <div id="activity-level"style="margin-bottom: 20px;">
      <label for="activity-level">Select activity level</label>
      <select id="activity-level" name="activity-level" style="font-weight: bold; ">
      <option value="sedentary">Sedentary</option>
      <option value="light">Light</option>
      <option value="moderate">Moderate</option>
      <option value="active">Active</option>
      </select>
   </div>
      
        <!-- Calculate EER ------------------------>
   <div id="calculate-eer-button" style="margin: 20px">
    <vaadin-button id="calculate-eer" style="color: #00909e; font-weight: bold">
      Calculate EER 
    </vaadin-button>
   </div>
   
        <!-- EER calculation result ------------------>
   <div id="eer result" style="color: #00909e; font-weight: bold; margin: 20px">
    <label for="eer-result">EER result</label>
    <input type="number" id="eer-result" name="eer-result" style="margin-left:100px; height: 22px;">
   </div>
  </div>
 </div>
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

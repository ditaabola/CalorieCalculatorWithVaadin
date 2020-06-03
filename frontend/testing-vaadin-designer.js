import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-charts/src/vaadin-chart.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-grid/src/vaadin-grid-column.js';
import '@vaadin/vaadin-grid/src/vaadin-grid-column-group.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';

class TestingVaadinDesigner extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;"></vaadin-vertical-layout>
<vaadin-vertical-layout style="width: 100%; height: 100%;"></vaadin-vertical-layout>
<vaadin-vertical-layout style="width: 100%; height: 100%;"></vaadin-vertical-layout>
<vaadin-button theme="primary success">
  Primary Success 
</vaadin-button>
<vaadin-chart type="area" title="Historic and Estimated Worldwide Population Growth by Region" subtitle="Source: Wikipedia.org" categories="[1750, 1800, 1850, 1900, 1950, 1999, 2050]" stacking="normal" no-legend="" tooltip="">
 <vaadin-chart-series title="Asia" values="[502, 635, 809, 947, 1402, 3634, 5268]" unit="Millions"></vaadin-chart-series>
 <vaadin-chart-series title="Africa" values="[106, 107, 111, 133, 221, 767, 1766]" unit="Millions"></vaadin-chart-series>
 <vaadin-chart-series title="Europe" values="[163, 203, 276, 408, 547, 729, 628]" unit="Millions"></vaadin-chart-series>
 <vaadin-chart-series title="America" values="[18, 31, 54, 156, 339, 818, 1201]" unit="Millions"></vaadin-chart-series>
 <vaadin-chart-series title="Oceania" values="[2, 2, 2, 6, 13, 30, 46]" unit="Millions"></vaadin-chart-series>
</vaadin-chart>
<vaadin-grid items="[[items]]">
 <vaadin-grid-column width="50px" flex-grow="0">
  <template class="header">
    # 
  </template>
  <template>
    [[index]] 
  </template>
  <template class="footer">
    # 
  </template>
 </vaadin-grid-column>
 <vaadin-grid-column-group>
  <template class="header">
    Group header 1 
  </template>
  <vaadin-grid-column>
   <template class="header">
     Value1 
   </template>
   <template>
     [[item.value1]] 
   </template>
   <template class="footer">
     Value1 
   </template>
  </vaadin-grid-column>
  <vaadin-grid-column>
   <template class="header">
     Value2 
   </template>
   <template>
     [[item.value2]] 
   </template>
   <template class="footer">
     Value2 
   </template>
  </vaadin-grid-column>
 </vaadin-grid-column-group>
 <vaadin-grid-column-group>
  <template class="header">
    Group header 2 
  </template>
  <vaadin-grid-column>
   <template class="header">
     Value3 
   </template>
   <template>
     [[item.value3]] 
   </template>
   <template class="footer">
     Value3 
   </template>
  </vaadin-grid-column>
  <vaadin-grid-column>
   <template class="header">
     Value4 
   </template>
   <template>
     [[item.value4]] 
   </template>
   <template class="footer">
     Value4 
   </template>
  </vaadin-grid-column>
 </vaadin-grid-column-group>
</vaadin-grid>
<vaadin-horizontal-layout style="width: 100%; height: 100%;">
 <vaadin-vertical-layout class="sidebar" style="flex-basis: calc(7*var(--lumo-size-s)); flex-shrink: 0; background-color: var(--lumo-contrast-5pct);"></vaadin-vertical-layout>
 <vaadin-vertical-layout class="content" style="flex-grow: 1; flex-shrink: 1; flex-basis: auto;"></vaadin-vertical-layout>
</vaadin-horizontal-layout>
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <vaadin-horizontal-layout class="header" style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 0; background-color: var(--lumo-contrast-10pct);"></vaadin-horizontal-layout>
 <vaadin-vertical-layout class="content" style="width: 100%; flex-grow: 1; flex-shrink: 1; flex-basis: auto;"></vaadin-vertical-layout>
 <vaadin-horizontal-layout class="footer" style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 0; background-color: var(--lumo-contrast-10pct);"></vaadin-horizontal-layout>
</vaadin-vertical-layout>
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <vaadin-horizontal-layout class="header" style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 0; background-color: var(--lumo-contrast-10pct);"></vaadin-horizontal-layout>
 <vaadin-vertical-layout class="content" style="width: 100%; flex-grow: 1; flex-shrink: 1; flex-basis: auto;"></vaadin-vertical-layout>
 <vaadin-horizontal-layout class="footer" style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 0; background-color: var(--lumo-contrast-10pct);"></vaadin-horizontal-layout>
</vaadin-vertical-layout>
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <vaadin-horizontal-layout class="header" style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 0; background-color: var(--lumo-contrast-10pct);"></vaadin-horizontal-layout>
 <vaadin-vertical-layout class="content" style="width: 100%; flex-grow: 1; flex-shrink: 1; flex-basis: auto;"></vaadin-vertical-layout>
 <vaadin-horizontal-layout class="footer" style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 0; background-color: var(--lumo-contrast-10pct);"></vaadin-horizontal-layout>
</vaadin-vertical-layout>
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

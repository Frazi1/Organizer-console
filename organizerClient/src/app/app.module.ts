import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {RoutingModule} from "./modules/routing/routing.module";
import {HttpModule} from "@angular/http";
import {EventsComponent} from './components/events/events.component';
import {EventsService} from "./services/events-service/events.service";
import {ModelConverterService} from "./services/model-converter-service/model-converter-service.service";
import {EditMeetingEventComponent} from './components/edit-event/edit-event.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {EventEditorComponent} from '../partial/components/event-editor/event-editor.component';

@NgModule({
  declarations: [
    AppComponent,
    EventsComponent,
    EditMeetingEventComponent,
    EventEditorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RoutingModule,
    HttpModule,
    NgbModule.forRoot()
  ],
  providers: [
    EventsService,
    ModelConverterService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

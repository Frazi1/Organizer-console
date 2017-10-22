import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {HeroDetailComponent} from './components/hero-detail/hero-detail.component';
import {HeroService} from "./services/hero-service/hero.service";
import {HeroesComponent} from "./components/heroes/heroes.component";
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {RoutingModule} from "./modules/routing/routing.module";
import {HttpModule} from "@angular/http";
import {EventsComponent} from './components/events/events.component';
import {EventsService} from "./services/events-service/events.service";
import {ModelConverterService} from "./services/model-converter-service/model-converter-service.service";
import {EditMeetingEventComponent} from './components/edit-meeting-event/edit-meeting-event.component';


@NgModule({
  declarations: [
    AppComponent,
    HeroDetailComponent,
    HeroesComponent,
    DashboardComponent,
    EventsComponent,
    EditMeetingEventComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RoutingModule,
    HttpModule
  ],
  providers: [
    HeroService,
    EventsService,
    ModelConverterService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

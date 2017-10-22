import {NgModule} from '@angular/core';
import {RouterModule} from "@angular/router";
import {HeroDetailComponent} from "../../components/hero-detail/hero-detail.component";
import {DashboardComponent} from "../../components/dashboard/dashboard.component";
import {HeroesComponent} from "../../components/heroes/heroes.component";
import {EventsComponent} from "../../components/events/events.component";
import {EditMeetingEventComponent} from "../../components/edit-meeting-event/edit-meeting-event.component";
import {STATES} from "./states";

const routes = [{
  path: STATES.HEROES,
  component: HeroesComponent
},
  {
    path: STATES.DASH_BOARD,
    component: DashboardComponent
  },
  {
    path: '',
    redirectTo: STATES.DASH_BOARD,
    pathMatch: 'full'
  },
  {
    path: STATES.DETAIL + '/:id',
    component: HeroDetailComponent
  },
  {
    path: STATES.EVENTS,
    component: EventsComponent
  },
  {
    path: STATES.MEETING + '/:id',
    component: EditMeetingEventComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})

export class RoutingModule {
}

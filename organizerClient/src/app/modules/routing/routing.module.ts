import {NgModule} from '@angular/core';
import {RouterModule} from "@angular/router";
import {STATES} from "./states";
import {EventsComponent} from "../../components/events/events.component";
import {EditMeetingEventComponent} from "../../components/edit-event/edit-event.component";

const routes = [
  {
    path: '',
    redirectTo: STATES.EVENTS,
    pathMatch: 'full'
  },
  {
    path: STATES.EVENTS,
    component: EventsComponent
  }
  // {
  //   path: STATES.EVENTS + '/:id',
  //   component: EditMeetingEventComponent
  // }
  ,
  {
    path: STATES.EVENTS_CREATE,
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

import {NgModule} from '@angular/core';
import {RouterModule} from "@angular/router";
import {HeroDetailComponent} from "../../components/hero-detail/hero-detail.component";
import {DashboardComponent} from "../../components/dashboard/dashboard.component";
import {HeroesComponent} from "../../components/heroes/heroes.component";
import {EventsComponent} from "../../components/events/events.component";

const routes = [{
  path: 'heroes',
  component: HeroesComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
  {
    path: 'detail/:id',
    component: HeroDetailComponent
  },
  {
    path: 'events',
    component: EventsComponent
  }
  ];

@NgModule({
  imports:[
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})

export class RoutingModule {
}

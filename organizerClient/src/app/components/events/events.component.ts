import { Component, Input, OnInit } from '@angular/core';
import { EventsService } from '../../services/events-service/events.service';
import { Person } from '../../services/events-service/Model/Person';
import { OrganizerEvent } from '../../services/events-service/Model/OrganizerEvent';
import { Router } from '@angular/router';
import { STATES } from '../../modules/routing/states';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  public events: OrganizerEvent[];

  @Input()
  public person: Person = Person.getEmptyPerson();

  @Input()
  public event: OrganizerEvent = OrganizerEvent.getEmptyEvent();

  constructor(private eventsService: EventsService,
    private router: Router) {
  }
  removeEvent(event, organizerEvent: OrganizerEvent) {
    event.stopPropagation();
    this.eventsService.removeEvent(organizerEvent)
      .then(value => this.update());
  }

  ngOnInit() {
    this.update();
  }

  public editEvent(event, organizerEvent: OrganizerEvent) {
    event.stopPropagation();
    this.router.navigate([STATES.EVENTS, organizerEvent.id]);
  }

  private update() {
    this.eventsService.getEvents()
      .then(events => this.events = events)
      .then(value => {
        if (this.events[0]) {
          console.log(this.events[0].getDate());
        }
      });
  }
}

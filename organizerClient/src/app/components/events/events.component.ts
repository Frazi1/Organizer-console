import {Component, Input, OnInit} from '@angular/core';
import {EventsService} from "../../services/events-service/events.service";
import {Person} from "../../services/events-service/Model/Person";
import {OrganizerEvent} from "../../services/events-service/Model/OrganizerEvent";
import {Router} from "@angular/router";
import {STATES} from "../../modules/routing/states";

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  public events: OrganizerEvent[];

  @Input()
  public person: Person = new Person;

  @Input()
  public event: OrganizerEvent = {
    id: null,
    person: {
      name: ""
    },
    description: "",
    date: new Date(Date.now()),
    birthHour: null,
    present: "",
    eventType: "null"
  };

  constructor(private eventsService: EventsService,
              private router: Router) {
  }

  addMeeting() {
    this.event.eventType = "Birthday";
    this.eventsService.addEvent(this.event)
      .then(value => this.update());
  }

  removeEvent(organizerEvent: OrganizerEvent) {
    this.eventsService.removeEvent(organizerEvent)
      .then(value => this.update());
  }

  ngOnInit() {
    this.update();
  }

  public goToEditPage(event: OrganizerEvent) {
    this.router.navigate([STATES.EVENTS, event.id]);
  }

  private update() {
    this.eventsService.getEvents()
      .then(events => this.events = events);
  }
}

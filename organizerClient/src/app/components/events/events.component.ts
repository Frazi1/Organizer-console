import {Component, Input, OnInit} from '@angular/core';
import {EventsService} from "../../services/events-service/events.service";
import {Person} from "../../services/events-service/Model/Person";
import {OrganizerEvent} from "../../services/events-service/Model/OrganizerEvent";
import {MeetingEvent} from "../../services/events-service/Model/MeetingEvent";
import {Router} from "@angular/router";

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
  public meeting: MeetingEvent = {
    id: null,
    person: {
      name: ""
    },
    description: "",
    date: new Date(Date.now())
  };

  constructor(private eventsService: EventsService,
              private router: Router) {
  }

  onAddMeeting() {
    this.eventsService.addMeeting(this.meeting)
      .then(value => this.update());

  }

  removeEvent(organizerEvent: OrganizerEvent){
    this.eventsService.removeEvent(organizerEvent)
      .then(value => this.update());
  }

  ngOnInit() {
    this.update();
  }

  private update() {
    this.eventsService.getMeetings().then(events => this.events = events);
  }

  public goToEditPage(meetingEvent: MeetingEvent) {
    this.router.navigate(['/meeting', meetingEvent.id]);
  }
}

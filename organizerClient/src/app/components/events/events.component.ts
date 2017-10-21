import {Component, Input, OnInit} from '@angular/core';
import {EventsService} from "../../services/events-service/events.service";
import {Person} from "../../services/events-service/Model/Person";
import {MeetingEventModel} from "../../services/events-service/Model/EventModel/MeetingEventModel";
import {OrganizerEvent} from "../../services/events-service/Model/OrganizerEvent";
import {MeetingEvent} from "../../services/events-service/Model/MeetingEvent";

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

  constructor(private eventsService: EventsService) {
  }

  onAddMeeting() {
    this.eventsService.addMeeting(this.meeting);
    this.update()
  }

  removeEvent(organizerEvent: OrganizerEvent){
    this.eventsService.removeEvent(organizerEvent);
    this.update()
  }

  ngOnInit() {
    this.update();
  }

  private update() {
    this.eventsService.getMeeting().then(events => this.events = events);
  }
}

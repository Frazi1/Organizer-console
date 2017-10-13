import {Component, Input, OnInit} from '@angular/core';
import {EventsService, MeetingEvent, OrganizerEvent, Person} from "../../services/events.service";

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  @Input() meeting: MeetingEvent = new MeetingEvent;
  @Input() person: Person = new Person;
  events: OrganizerEvent[];

  constructor(private eventsService: EventsService) { }

  onAddMeeting(){
    this.eventsService.addMeeting(this.meeting);
  }
  ngOnInit() {
    this.eventsService.getMeeting().then(events => {
      this.events = events;
      // console.log(events[0].person.name);
    });
  }

}

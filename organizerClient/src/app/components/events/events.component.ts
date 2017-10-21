import {Component, Input, OnInit} from '@angular/core';
import {EventsService} from "../../services/events-service/events.service";
import {Person} from "../../services/events-service/Model/Person";
import {MeetingEventModel} from "../../services/events-service/Model/EventModel/MeetingEventModel";
import {OrganizerEventModel} from "../../services/events-service/Model/EventModel/OrganizerEventModel";
import {MeetingEvent} from "../../services/events-service/Model/MeetingEvent";
import {OrganizerEvent} from "../../services/events-service/Model/OrganizerEvent";

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {
  @Input()
  person: Person = new Person;
  private events: OrganizerEvent[];
  @Input()
  private meeting: MeetingEventModel = {
    id: null,
    person: {
      name: ""
    },
    description: "",
    date: Date.now()
  };

  constructor(private eventsService: EventsService) {
  }

  onAddMeeting() {
    this.eventsService.addMeeting(this.meeting);
  }

  ngOnInit() {
    this.eventsService.getMeeting().then(events => {
      this.events = [];
      events.forEach(value => {
        return this.events.push(this.getMeetingEvent(value));
      })
    });
  }

  private getMeetingEvent(meetingEventModel: MeetingEventModel): MeetingEvent {
    return {
      id: meetingEventModel.id,
      person: {
        name: meetingEventModel.person.name
      },
      description: meetingEventModel.description,
      date: new Date(meetingEventModel.date)
    };
  }
}

import {Component, Input, OnInit} from '@angular/core';
import {EventsService, OrganizerEvent} from "../../services/events.service";

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  @Input() name: string;
  @Input() description: string;

  events: OrganizerEvent[];
  constructor(private eventsService: EventsService) { }

  onAddMeeting(){
    this.eventsService.addMeeting(this.name, this.description);
  }
  ngOnInit() {
    this.eventsService.getMeeting().then(events => {
      this.events = events;
      // console.log(events[0].person.name);
    });
  }

}

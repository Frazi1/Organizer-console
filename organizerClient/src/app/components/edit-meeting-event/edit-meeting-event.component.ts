import {Component, Input, OnInit} from '@angular/core';
import {EventsService} from "../../services/events-service/events.service";
import {MeetingEvent} from "../../services/events-service/Model/MeetingEvent";
import {Location} from "@angular/common";
import {ActivatedRoute, ParamMap} from "@angular/router";

@Component({
  selector: 'app-edit-meeting-event',
  templateUrl: './edit-meeting-event.component.html',
  styleUrls: ['./edit-meeting-event.component.css']
})
export class EditMeetingEventComponent implements OnInit {

  @Input()
  public meetingEvent: MeetingEvent;

  constructor(private eventsService: EventsService,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.switchMap((params: ParamMap) =>
      this.eventsService.getMeeting(+params.get('id')))
      .subscribe(result => {
        console.log(result);
        this.meetingEvent = result
      });
  }

  public updateEvent(): void {
    this.eventsService.updateMeeting(this.meetingEvent);
    this.location.back();
  }

}

import {Component, Input, OnInit} from '@angular/core';
import {EventsService} from "../../services/events-service/events.service";
import {Location} from "@angular/common";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {OrganizerEvent} from "../../services/events-service/Model/OrganizerEvent";

@Component({
  selector: 'app-edit-meeting-event',
  templateUrl: './edit-meeting-event.component.html',
  styleUrls: ['./edit-meeting-event.component.css']
})
export class EditMeetingEventComponent implements OnInit {

  @Input()
  public event: OrganizerEvent;

  constructor(private eventsService: EventsService,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.switchMap((params: ParamMap) =>
      this.eventsService.getEvent(+params.get('id')))
      .subscribe(result => {
        console.log(result);
        this.event = result
      });
  }

  public updateEvent(): void {
    this.eventsService.updateEvent(this.event);
    this.goBack();
  }

  public goBack(): void {
    this.location.back();
  }

}

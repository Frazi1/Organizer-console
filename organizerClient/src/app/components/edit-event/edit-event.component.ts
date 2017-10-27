import {Component, Input, OnInit} from '@angular/core';
import {EventsService} from "../../services/events-service/events.service";
import {Location} from "@angular/common";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {OrganizerEvent} from "../../services/events-service/Model/OrganizerEvent";
import "rxjs/add/operator/switchMap";

@Component({
  selector: 'app-edit-meeting-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.css']
})
export class EditMeetingEventComponent implements OnInit {

  public isEdit: boolean;
  @Input()
  public event: OrganizerEvent;

  constructor(private eventsService: EventsService,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap
      .subscribe((params: ParamMap) => this.isEdit = params.has('id'));

    if (!this.isEdit) {
      this.event = OrganizerEvent.getEmptyEvent();
      return;
    }
    this.route.paramMap.switchMap((params: ParamMap) => {
      return this.eventsService.getEvent(+params.get('id'));
    })
      .subscribe(result => {
        this.event = result
      });
  }

  public updateEvent(): void {
    this.eventsService.updateEvent(this.event)
      .then(value => this.goBack());
  }

  public addEvent(): void {
    this.eventsService.addEvent(this.event)
      .then(value => this.goBack());
  }

  public goBack(): void {
    this.location.back();
  }
}

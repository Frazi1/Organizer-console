import {Component, Input, OnInit} from '@angular/core';
import {EventsService} from "../../services/events-service/events.service";
import {Location} from "@angular/common";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {OrganizerEvent} from "../../services/events-service/Model/OrganizerEvent";
import {STATES} from "../../modules/routing/states";
import "rxjs/add/operator/switchMap";

@Component({
  selector: 'app-edit-meeting-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.css']
})
export class EditMeetingEventComponent implements OnInit {

  @Input()
  public event: OrganizerEvent;
  constructor(private eventsService: EventsService,
              private location: Location,
              private route: ActivatedRoute,
              private router: Router) {
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
    this.goToEvents();
  }

  public goToEvents(): void {
    this.router.navigate([STATES.EVENTS])
  }

  public goBack(): void {
    this.location.back();
    // console.log(this.dateModel);
    // console.log(this.timeModel);
  }

}

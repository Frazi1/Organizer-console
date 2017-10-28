import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {OrganizerEvent} from "../../../app/services/events-service/Model/OrganizerEvent";
import {NgbDateStruct, NgbTimeStruct} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-event-editor',
  templateUrl: './event-editor.component.html',
  styleUrls: ['./event-editor.component.css']
})
export class EventEditorComponent implements OnInit {

  @Input() public event: OrganizerEvent;
  @Output() public eventChange: EventEmitter<OrganizerEvent> = new EventEmitter();
  public constructor() { }

  ngOnInit() {
  }

}

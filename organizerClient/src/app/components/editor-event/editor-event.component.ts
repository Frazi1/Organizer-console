import {Component, EventEmitter, Input, OnInit, Output, OnChanges, SimpleChange} from '@angular/core';
import {OrganizerEvent} from "../../services/events-service/Model/OrganizerEvent";
import {NgbDateStruct, NgbTimeStruct} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-editor-event',
  templateUrl: './editor-event.component.html',
  styleUrls: ['./editor-event.component.css']
})
export class EditorEventComponent implements OnInit, OnChanges {

  @Input()
  public event: OrganizerEvent = OrganizerEvent.getEmptyEvent();
  @Output()
  public eventChanged: EventEmitter<OrganizerEvent> = new EventEmitter<OrganizerEvent>();
  @Input()
  public dateModel: NgbDateStruct;
  @Output()
  public dateModelChanged: EventEmitter<NgbDateStruct> = new EventEmitter<NgbDateStruct>();
  @Input()
  public timeModel: NgbTimeStruct;
  @Output()
  public timeModelChanged: EventEmitter<NgbTimeStruct> = new EventEmitter<NgbTimeStruct>();

  constructor() { }

  ngOnChanges(changes: {[propKey: string]: SimpleChange}){
    this.eventChanged.emit(this.event);
    this.timeModelChanged.emit(this.timeModel);
    this.dateModelChanged.emit(this.dateModel);
  }
  ngOnInit() {
    console.log(this.event);
  }

}

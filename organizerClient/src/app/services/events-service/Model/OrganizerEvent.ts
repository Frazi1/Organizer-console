import {OrganizerEventBase} from "./OrganizerEventBase";
import {NgbDateStruct, NgbTimeStruct} from "@ng-bootstrap/ng-bootstrap";


export class OrganizerEvent extends OrganizerEventBase{
  public dateModel: NgbDateStruct;
  public timeModel: NgbTimeStruct;
}

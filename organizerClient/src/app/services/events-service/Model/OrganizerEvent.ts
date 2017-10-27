import {OrganizerEventBase} from "./OrganizerEventBase";
import {NgbDateStruct, NgbTimeStruct} from "@ng-bootstrap/ng-bootstrap";
import {Person} from "./Person";
import {Helper} from "./Helper";


export class OrganizerEvent extends OrganizerEventBase {
  public dateModel: NgbDateStruct;
  public timeModel: NgbTimeStruct;


  constructor(id: number,
              eventType: string,
              person: Person,
              description: string,
              birthHour: number,
              present: string,
              dateModel: NgbDateStruct,
              timeModel: NgbTimeStruct) {
    super(id, eventType, person, description, birthHour, present);
    this.dateModel = dateModel;
    this.timeModel = timeModel;
  }


  public static getEmptyEvent(): OrganizerEvent {
    return new OrganizerEvent(null, "", Person.getEmptyPerson(), "", -1, "", Helper.getEmptyDateStruct(), Helper.getEmptyTimeStruct())
  }

  public getDate(): Date {
    return Helper.getDateFromDateTimeStructs(this.dateModel, this.timeModel);
  }
}

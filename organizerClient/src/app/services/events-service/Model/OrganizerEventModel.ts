import {OrganizerEventBase} from "./OrganizerEventBase";
import {Person} from "./Person";

export class OrganizerEventModel extends OrganizerEventBase {
  public date: number;

  constructor(id: number,
              eventType: string,
              person: Person,
              description: string,
              birthHour: number,
              present: string,
              date: number) {
    super(id, eventType, person, description, birthHour, present);
    this.date = date;
  }
}

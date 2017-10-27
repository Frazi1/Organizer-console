import {Person} from "./Person";

export class OrganizerEventBase {
 public id: number;
 public eventType: string;
 public person: Person;
 public description: string;
 public birthHour: number;
 public present: string;

  constructor(id: number,
              eventType: string,
              person: Person,
              description: string,
              birthHour: number,
              present: string) {
    this.id = id;
    this.eventType = eventType;
    this.person = person;
    this.description = description;
    this.birthHour = birthHour;
    this.present = present;
  }
}

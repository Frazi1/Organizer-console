import {Person} from "./Person";

export class OrganizerEventBase {
  id: number;
  eventType: string;
  person: Person;
  description: string;
  birthHour: number;
  present: string;
}

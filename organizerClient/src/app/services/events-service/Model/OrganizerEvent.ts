import {Person} from "./Person";


export abstract class OrganizerEvent {
  id: number;
  person: Person;
  description: string;
  date: Date
}

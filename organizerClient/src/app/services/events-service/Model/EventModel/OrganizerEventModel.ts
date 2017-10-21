import {Person} from "../Person";

export abstract class OrganizerEventModel {
  id: number;
  person: Person;
  description: string;
  date: number;
}

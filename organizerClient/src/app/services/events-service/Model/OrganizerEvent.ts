import {OrganizerEventBase} from "./OrganizerEventBase";


export class OrganizerEvent extends OrganizerEventBase {
  date: Date;

  public static getEmptyEvent(): OrganizerEvent {
    return {
      id: null,
      person: {
        name: ""
      },
      description: "",
      date: new Date(Date.now()),
      birthHour: null,
      present: "",
      eventType: "null"
    };
  }
}

import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";

const baseUrl = 'http://localhost:8080/api/events/';

export class Person {
  name: string;
}

export abstract class OrganizerEvent {
  id: number;
  person: Person;
  description: string;
}

export class MeetingEvent extends OrganizerEvent{

}

export class BirthdayEvent extends OrganizerEvent{
  birthHour: number;
  present: string;
}

@Injectable()
export class EventsService {
  private getMeetingUrl = baseUrl + 'getMeeting';
  private addMeetingUrl = baseUrl + 'addMeeting';

  constructor(private http: Http) { }

  getMeeting(): Promise<MeetingEvent[]>{
    return this.http.get(this.getMeetingUrl)
      .toPromise()
      .then(response => response.json() as MeetingEvent[])
      .catch(this.handleError)
  }

  // addMeeting(name: string, description: string) {
  //   let meeting : MeetingEvent = new MeetingEvent;
  //   meeting.description = description;
  //   let person : Person = new Person;
  //   person.name = name;
  //   meeting.person = person;
  //   //TODO: do something
  //   // let result = this.http.get(this.addMeetingUrl, meeting.).toPromise();
  //   console.log(result);
  //   return result;
  // }
    addMeeting(meeting: MeetingEvent) {
    return this.http.post(this.addMeetingUrl, meeting)
      .toPromise()
      .then(response => response.json())
      .catch(this.handleError)
    }

  private handleError(error: any): Promise<any> {
    console.error('Error occured: ', error);
    return Promise.reject(error.message | error);
  }
}

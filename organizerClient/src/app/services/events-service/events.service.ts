import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {MeetingEvent} from "./Model/MeetingEvent";
import {MeetingEventModel} from "./Model/EventModel/MeetingEventModel";

const baseUrl = 'http://localhost:8080/api/events/';

@Injectable()
export class EventsService {
  private meetingUrl = baseUrl + 'meeting';

  constructor(private http: Http) { }

  getMeeting(): Promise<MeetingEventModel[]>{
    return this.http.get(this.meetingUrl)
      .toPromise()
      .then(response => (response.json() as MeetingEventModel[]))
      .catch(this.handleError)
  }

    addMeeting(meeting: MeetingEventModel)   {
    console.log(meeting);
    return this.http.post(this.meetingUrl, meeting)
      .toPromise()
      .then(response => {console.log(response.json()); return response.json();})
      .catch(this.handleError)
    }

  private handleError(error: any): Promise<any> {
    console.error('Error occured: ', error);
    return Promise.reject(error.message | error);
  }
}

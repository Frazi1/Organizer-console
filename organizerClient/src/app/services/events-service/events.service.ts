import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {MeetingEventModel} from "./Model/EventModel/MeetingEventModel";
import {ModelConverterService} from "../model-converter-service/model-converter-service.service";
import {MeetingEvent} from "./Model/MeetingEvent";
import {OrganizerEvent} from "./Model/OrganizerEvent";

const baseUrl = 'http://localhost:8080/api/events/';

@Injectable()
export class EventsService {
  private meetingUrl = baseUrl + 'meeting';

  constructor(private http: Http,
              private converter: ModelConverterService) {
  }


  public getMeeting(id: number): Promise<MeetingEvent> {
    return this.http.get(this.meetingUrl + `/${id}`)
      .toPromise()
      .then(response => this.converter.getMeetingEvent(response.json()))
      .catch(this.handleError);
  }

  public getMeetings(): Promise<MeetingEvent[]> {
    return this.http.get(this.meetingUrl)
      .toPromise()
      .then(response =>
        this.converter.getMeetingEventsArray(response.json() as MeetingEventModel[]))
      .catch(this.handleError)
  }

  public addMeeting(meeting: MeetingEvent) {
    console.log(meeting);
    return this.http.post(this.meetingUrl,
                          this.converter.getMeetingEventModel(meeting))
      .toPromise()
      .then(response => {
        console.log(response.json());
        return response.json();
      })
      .catch(this.handleError)
  }

  private handleError(error: any): Promise<any> {
    console.error('Error occured: ', error);
    return Promise.reject(error.message | error);
  }

  public removeEvent(organizerEvent: OrganizerEvent) : Promise<OrganizerEvent> {
    return this.http.delete(this.meetingUrl + `/${organizerEvent.id}`)
      .toPromise()
      .catch(this.handleError);
  }

  updateMeeting(meetingEvent: MeetingEvent) {
    return this.http.put(this.meetingUrl + `/${meetingEvent.id}`, this.converter.getMeetingEventModel(meetingEvent))
      .toPromise()
      .catch(this.handleError);
  }
}

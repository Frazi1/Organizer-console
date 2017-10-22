import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {ModelConverterService} from "../model-converter-service/model-converter-service.service";
import {OrganizerEvent} from "./Model/OrganizerEvent";
import {OrganizerEventModel} from "./Model/OrganizerEventModel";

const END_POINT = 'http://localhost:8080/'
const EVENTS_URL = END_POINT + 'api/events/';

@Injectable()
export class EventsService {

  constructor(private http: Http,
              private converter: ModelConverterService) {
  }

  public getEvent(id: number): Promise<OrganizerEvent> {
    return this.http.get(EVENTS_URL + id)
      .toPromise()
      .then(response => this.converter.getEvent(response.json()))
      .catch(this.handleError);
  }

  public getEvents(): Promise<OrganizerEvent[]> {
    return this.http.get(EVENTS_URL)
      .toPromise()
      .then(response =>
        this.converter.getEventArray(response.json() as OrganizerEventModel[]))
      .catch(this.handleError)
  }

  public addEvent(event: OrganizerEvent) {
    console.log(event);
    return this.http.post(EVENTS_URL,
                          this.converter.getEventModel(event))
      .toPromise()
      .then(response => {
        console.log(response.json());
        return response.json();
      })
      .catch(this.handleError)
  }

  public removeEvent(event: OrganizerEvent) : Promise<OrganizerEvent> {
    return this.http.delete(EVENTS_URL + event.id)
      .toPromise()
      .catch(this.handleError);
  }

  updateEvent(event: OrganizerEvent) {
    return this.http.put(EVENTS_URL + event.id, this.converter.getEventModel(event))
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error occured: ', error);
    return Promise.reject(error.message | error);
  }
}

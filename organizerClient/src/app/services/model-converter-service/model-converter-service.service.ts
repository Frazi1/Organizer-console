import {Injectable} from '@angular/core';
import {OrganizerEventModel} from '../events-service/Model/OrganizerEventModel';
import {OrganizerEvent} from '../events-service/Model/OrganizerEvent';
import {Person} from '../events-service/Model/Person';
import {Helper} from '../events-service/Model/Helper';

@Injectable()
export class ModelConverterService {

  constructor() {
  }

  public getEvent(eventModel: OrganizerEventModel): OrganizerEvent {
    const date = new Date(eventModel.date);
    return new OrganizerEvent(eventModel.id,
    eventModel.eventType,
    new Person(eventModel.person.name),
    eventModel.description, eventModel.birthHour,
    eventModel.present,
    Helper.getDateStructFromDate(date),
    Helper.getTimeStructFromDate(date));
  }

  public getEventModel(event: OrganizerEvent): OrganizerEventModel {
    const date = Helper.getDateFromDateTimeStructs(event.dateModel, event.timeModel);
    return new OrganizerEventModel(event.id, event.eventType, new Person(event.person.name,), event.description,
      event.birthHour, event.present, date.getTime());
  }

  public getEventArray(meetingEventModels: OrganizerEventModel[]): OrganizerEvent[] {
    const meetingEvents: OrganizerEvent[] = [];
    meetingEventModels.forEach(value => meetingEvents.push(this.getEvent(value)));
    return meetingEvents;
  }

  public getEventModelsArray(events: OrganizerEvent[]): OrganizerEventModel[] {
    const eventsModels: OrganizerEventModel[] = [];
    events.forEach(value => eventsModels.push(this.getEventModel(value)));
    return eventsModels;
  }
}

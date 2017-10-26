import { Injectable } from '@angular/core';
import {OrganizerEventModel} from "../events-service/Model/OrganizerEventModel";
import {OrganizerEvent} from "../events-service/Model/OrganizerEvent";

@Injectable()
export class ModelConverterService {

  constructor() { }

  public getEvent(eventModel: OrganizerEventModel): OrganizerEvent {
    let date = new Date(eventModel.date);
    return {
      id: eventModel.id,
      person: {
        name: eventModel.person.name
      },
      description: eventModel.description,
      birthHour: eventModel.birthHour,
      present: eventModel.present,
      eventType: eventModel.eventType,
      timeModel: {
        hour: date.getHours(),
        minute: date.getMinutes(),
        second: date.getSeconds()
      },
      dateModel: {
        year: date.getFullYear(),
        month: date.getMonth() + 1,
        day: date.getDay()
      }
    };
  }

  public getEventModel(event: OrganizerEvent) : OrganizerEventModel {
    let date = new Date(event.dateModel.year,
      event.dateModel.month - 1,
      event.dateModel.day,
      event.timeModel.hour,
      event.timeModel.minute);
    return {
      id: event.id,
      person: {
        name: event.person.name
      },
      description: event.description,
      birthHour: event.birthHour,
      present: event.present,
      eventType: event.eventType,
      date: date.getTime()
    };
  }

  public getEventArray(meetingEventModels: OrganizerEventModel[]) : OrganizerEvent[] {
    let meetingEvents : OrganizerEvent[] = [];
    meetingEventModels.forEach(value => meetingEvents.push(this.getEvent(value)));
    return meetingEvents;
  }

  public getEventModelsArray(events: OrganizerEvent[]) : OrganizerEventModel[] {
    let eventsModels : OrganizerEventModel[] = [];
    events.forEach(value => eventsModels.push(this.getEventModel(value)));
    return eventsModels;
  }
}

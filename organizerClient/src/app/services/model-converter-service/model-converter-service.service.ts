import { Injectable } from '@angular/core';
import {MeetingEventModel} from "../events-service/Model/EventModel/MeetingEventModel";
import {MeetingEvent} from "../events-service/Model/MeetingEvent";

@Injectable()
export class ModelConverterService {

  constructor() { }

  public getMeetingEvent(meetingEventModel: MeetingEventModel): MeetingEvent {
    return {
      id: meetingEventModel.id,
      person: {
        name: meetingEventModel.person.name
      },
      description: meetingEventModel.description,
      date: new Date(meetingEventModel.date)
    };
  }

  public getMeetingEventModel(meeting: MeetingEvent) {
    return {
      id: meeting.id,
      person: {
        name: meeting.person.name
      },
      description: meeting.description,
      date: new Date(meeting.date).getTime()
    };
  }

  public getMeetingEventsArray(meetingEventModels: MeetingEventModel[]) : MeetingEvent[] {
    let meetingEvents : MeetingEvent[] = [];
    meetingEventModels.forEach(value => meetingEvents.push(this.getMeetingEvent(value)));
    return meetingEvents;
  }

  public getMeetingEventModelsArray(meetingEvents: MeetingEvent[]) : MeetingEventModel[] {
    let meetingEventsModels : MeetingEventModel[] = [];
    meetingEvents.forEach(value => meetingEventsModels.push(this.getMeetingEventModel(value)));
    return meetingEventsModels;
  }


}

import {NgbDateStruct, NgbTimeStruct} from "@ng-bootstrap/ng-bootstrap";

export class Helper {
  public static getDateFromDateTimeStructs(dateStruct: NgbDateStruct,
                                           timeStruct: NgbTimeStruct): Date {
    return new Date(dateStruct.year,
      dateStruct.month - 1,
      dateStruct.day,
      timeStruct.hour,
      timeStruct.minute);
  }

  public static getTimeStructFromDate(date: Date): NgbTimeStruct {
    return {
      hour: date.getHours(),
      minute: date.getMinutes(),
      second: date.getSeconds()
    };
  }

  public static getDateStructFromDate(date: Date): NgbDateStruct {
    return {
      year: date.getFullYear(),
      month: date.getMonth(),
      day: date.getDay()
    };
  }

  public static getEmptyDateStruct(): NgbDateStruct {
    return {year: null, month: null, day: null}
  }

  public static getEmptyTimeStruct(): NgbTimeStruct {
    return { hour: null, minute: null, second: null }
  }
}

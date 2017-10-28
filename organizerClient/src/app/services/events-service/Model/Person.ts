export class Person {
  public name: string;

  public constructor(name: string) {
    this.name = name;
  }

  public static getEmptyPerson(): Person {
    return new Person("");
  }
}

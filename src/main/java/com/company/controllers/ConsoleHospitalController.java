package com.company.controllers;

import com.company.models.Hospital;
import com.company.models.Patient;
import com.company.models.Sex;
import com.company.repositories.FileHospitalRepository;
import com.company.repositories.FilePatientRepository;

import static com.company.Helper.*;
import static com.company.models.Constants.*;
import static java.lang.System.out;

public class ConsoleHospitalController {
    private final FileHospitalRepository hospitalRepository;
    private final FilePatientRepository patientRepository;

    private static String hospitalFilePath = "hospital.json";
    private static String patientsFilePath = "patient.json";

    private static ConsoleHospitalController instance;

    private ConsoleHospitalController() {
        patientRepository = new FilePatientRepository(patientsFilePath);
        hospitalRepository = new FileHospitalRepository(hospitalFilePath);
    }

    public static ConsoleHospitalController getInstance() {
        if(instance == null) {
            instance = new ConsoleHospitalController();
        }
        return instance;
    }

    public void addHospital() {
        Hospital hospital = new Hospital();

        printValue(NAME);
        hospital.setName(readString());
        hospitalRepository.add(hospital);
        out.println("Added: " + hospital.toString());
    }

    public void addPatient() {
        Patient patient = new Patient();

        printValue(FIRST_NAME);
        patient.setFirstName(readString());

        printValue(MIDDLE_NAME);
        patient.setMiddleName(readString());

        printValue(LAST_NAME);
        patient.setLastName(readString());

        printValue(SEX, "1: Male, 2: Female");
        Integer input = readInt();
        Sex sex = input.equals(1)
                ? Sex.MALE
                : Sex.FEMALE;
        patient.setSex(sex);

        printValue(AGE);
        patient.setAge(readInt());

        printValue(HOSPITAL_ID);
        printList(hospitalRepository.getItems());
        patient.setHospitalId(readInt());

        patientRepository.add(patient);
        hospitalRepository
                .getItem(hospital -> hospital.getId() == patient.getHospitalId())
                .acceptPatient(patient.getId());
        out.println("Added: " + patient.toString());
    }

    public void removePatient() {
        printList(this.patientRepository.getItems());
        out.println("Select id");
        int id = readInt();
        boolean removedFromPatientsRepository = this.patientRepository.remove(patient -> patient.getId() == id);
        boolean removedFromHospital = this.hospitalRepository.removePatient(id);
        if(removedFromPatientsRepository && removedFromHospital) System.out.println("Removed: " + id + "\n");
        else System.out.println("Id " + id + " doesn't exist\n");
    }

    public void removeHospital() {
        printList(this.hospitalRepository.getItems());
        out.println("Select id");
        int id = readInt();
        if(this.patientRepository.getItems(patient -> patient.getHospitalId() == id).size() > 0) {
            out.println("Hospital is not empty");
            return;
        }
        boolean removed = this.hospitalRepository.remove(hospital -> hospital.getId() == id);
        if(removed){
            out.println("Removed: " + id);
        }
    }

    public void printPatients() {
        printList(this.patientRepository.getItems());
    }

    public void printHospitals(){
        printList(this.hospitalRepository.getItems());
    }

    public void saveChanges() {
        this.hospitalRepository.saveChanges();
        this.patientRepository.saveChanges();
    }
}

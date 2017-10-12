package com.company.repositories;

import com.company.models.Hospital;
import com.company.models.json.Json;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.company.Helper.readFromFile;

public class FileHospitalRepository extends FileRepository<Hospital> {

    public FileHospitalRepository(String filePath) {
        super(filePath);
    }

    @Override
    protected void SetId(Integer id, Hospital item) {
        item.setId(id);
    }

    @Override
    protected Collection<Hospital> loadData() {
        Gson gson = Json.getGson();
        try {
            String json = readFromFile(filePath);
            Hospital[] hospitals = gson.fromJson(json, Hospital[].class);
            return Arrays.asList(hospitals);
        }
        catch (IOException ignored) {
        }
        return new ArrayList<>();
    }

    public boolean removePatient(Integer id){
        return this.getItem(hospital -> hospital
                .getPatientIdList()
                .stream()
                .anyMatch(patientId -> patientId == id))
        .removePatient(id);
    }

}

package com.company.repositories;

import com.company.models.Patient;
import com.company.models.json.Json;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.company.Helper.readFromFile;

public class FilePatientRepository extends FileRepository<Patient> {

    public FilePatientRepository(String filePath) {
        super(filePath);
    }

    @Override
    protected void SetId(Integer id, Patient item) {
        item.setId(id);
    }

    @Override
    protected Collection<Patient> loadData() {
        Gson gson = Json.getGson();
        try {
            String json = readFromFile(filePath);
            Patient[] Patients = gson.fromJson(json, Patient[].class);
            return Arrays.asList(Patients);
        }
        catch (IOException ignored) {
        }
        return new ArrayList<>();
    }
}

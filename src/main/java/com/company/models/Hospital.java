package com.company.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hospital {
    private Integer id;
    private String name;
    private List<Integer> patientIdList;

    public Hospital() {
        patientIdList = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPatientIdList() {
        return Collections.unmodifiableList(patientIdList);
    }

    public void acceptPatient(Integer patientId){
        patientIdList.add(patientId);
    }

    public boolean removePatient(Integer id) {
        return patientIdList.removeIf(pId -> pId == id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital)) return false;

        Hospital hospital = (Hospital) o;

        if (!getId().equals(hospital.getId())) return false;
        if (!getName().equals(hospital.getName())) return false;
        return getPatientIdList().equals(hospital.getPatientIdList());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getPatientIdList().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hospital{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

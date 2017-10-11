package com.company.models;

public class Patient {
    private Integer id;
    private Integer hospitalId;
    private String firstName;
    private String middleName;

    private String lastName;
    private Sex sex;
    private Integer age;

    public Patient() {
    }


    public Integer getId() {
        return id;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;

        Patient patient = (Patient) o;

        if (!id.equals(patient.id)) return false;
        if (!getHospitalId().equals(patient.getHospitalId())) return false;
        if (!getFirstName().equals(patient.getFirstName())) return false;
        if (getMiddleName() != null ? !getMiddleName().equals(patient.getMiddleName()) : patient.getMiddleName() != null)
            return false;
        if (!getLastName().equals(patient.getLastName())) return false;
        if (getSex() != patient.getSex()) return false;
        return getAge().equals(patient.getAge());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + getHospitalId().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + (getMiddleName() != null ? getMiddleName().hashCode() : 0);
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getSex().hashCode();
        result = 31 * result + getAge().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Patient{");
        sb.append("id=").append(id);
        sb.append("fullName=").append(getFirstName());
        sb.append(", hospitalId=").append(hospitalId);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

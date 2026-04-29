package Abrakadabra.streams.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PatientsMapping {
    public static void main(String[] args) throws IOException {
        List<Patient> patients = Files.lines(Path.of("data/patients.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .map(tokens -> new Patient(
                        Integer.parseInt(tokens[0]),
                        tokens[1],
                        Integer.parseInt(tokens[2]),
                        tokens[3].equals("Female")
                ))
                .toList();

        List<PatientRecord> records = Files.lines(Path.of("data/patients_records.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .map(t -> new PatientRecord(
                        Integer.parseInt(t[0]),
                        Integer.parseInt(t[1]),
                        LocalDate.parse(t[2]),
                        t[3],
                        t[4],
                        Integer.parseInt(t[5]),
                        Double.parseDouble(t[6]),
                        t[7]
                ))
                .toList();



        HashMap<Integer, Patient> refMap = new HashMap<>();
        for (Patient p : patients){
            refMap.put(p.id, p);
        }

        records.stream()
                .forEach(record -> refMap.get(record.getPatientId()).getRecords().add(record));

        System.out.println(refMap.get(17).getName());
        for (PatientRecord r : refMap.get(17).getRecords()){
            System.out.println(r.getDiagnosis());
        }

        List<Patient> oldPatients = patients.stream()
                .filter(patient -> {
                    boolean toReturn = false;
                    for (PatientRecord r : patient.getRecords()){
                        if (r.getDiagnosis().equals("Back pain")) return true;
                    }
                    return false;
                })
                .toList();

        oldPatients.forEach(System.out::println);


    }

}

class Patient{
    int id;
    String name;
    int age;
    boolean isWomen;
    List<PatientRecord> records;

    public Patient(int id, String name, int age, boolean isWomen) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isWomen = isWomen;
        this.records = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isWomen=" + isWomen +
                '}';
    }

    public List<PatientRecord> getRecords() {
        return records;
    }

    public void setRecords(List<PatientRecord> records) {
        this.records = records;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWomen() {
        return isWomen;
    }

    public void setWomen(boolean women) {
        isWomen = women;
    }
}

class PatientRecord{
    int RecordId;
    int PatientId;
    LocalDate visitDate;
    String bloodPressure;
    String bloodType;
    int heartRate;
    double temp;
    String diagnosis;

    public PatientRecord(int recordId, int patientId, LocalDate visitDate, String bloodPressure, String bloodType, int heartRate, double temp, String diagnosis) {
        RecordId = recordId;
        PatientId = patientId;
        this.visitDate = visitDate;
        this.bloodPressure = bloodPressure;
        this.bloodType = bloodType;
        this.heartRate = heartRate;
        this.temp = temp;
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "PatientRecord{" +
                "RecordId=" + RecordId +
                ", PatientId=" + PatientId +
                ", visitDate=" + visitDate +
                ", bloodPressure='" + bloodPressure + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", heartRate=" + heartRate +
                ", temp=" + temp +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }

    public int getRecordId() {
        return RecordId;
    }

    public void setRecordId(int recordId) {
        RecordId = recordId;
    }

    public int getPatientId() {
        return PatientId;
    }

    public void setPatientId(int patientId) {
        PatientId = patientId;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
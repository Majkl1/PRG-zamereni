package Abrakadabra.filing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SensorTask {
    static ArrayList<Reading> validReadings = new ArrayList<>();
    static ArrayList<InvalidReading> invalidReadings = new ArrayList<>();

    static void reportReadings(String dirPath) throws IOException {
        File f = new File(dirPath);

        if (!f.exists()){
            System.out.println("Soubor neexistuje");
        } else {
            File[] files = f.listFiles();
            List<InvalidReading> invalidReadings = new ArrayList<>();
            List<Reading> readings = new ArrayList<>();
            for (File file : files){
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                int lineNum = 0;
                while ((line = br.readLine()) != null){
                    lineNum++;
                    String[] parts = line.split(" ");
                    Reading r = new Reading(parts[0], Double.parseDouble(parts[1]));
                    if (r.measuredValue > -200.0 && r.measuredValue < 500.0){
                        readings.add(r);
                    } else {
                        InvalidReading ir = new InvalidReading(file.getName(), lineNum, r);
                        invalidReadings.add(ir);
                    }
                }
                br.close();
            }

            System.out.println("Invalid readings: " + invalidReadings.size());
            System.out.println("Valid readings: " + readings.size());


        }
    }

    public static void main(String[] args) {
        try {
            reportReadings("dataSensors");
        } catch (IOException e){
            System.out.println("Problem reading the directory:");
            System.out.println(e.getMessage());
        }
    }

}
class Reading{
    String sensorID;
    double measuredValue;

    public Reading(String sensorID, double measuredValue) {
        this.sensorID = sensorID;
        this.measuredValue = measuredValue;
    }

    @Override
    public String toString() {
        return sensorID + ": " + measuredValue;
    }
}
class InvalidReading{
    String fileName;
    int lineNumber;
    Reading reading;

    public InvalidReading(String fileName, int lineNumber, Reading reading) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.reading = reading;
    }
}


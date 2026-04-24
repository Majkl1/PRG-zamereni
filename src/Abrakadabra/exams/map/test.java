package Abrakadabra.exams.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {

        String dataPath = "data/scores.csv";
        try {
            List<TestResult> results = Files.lines(Path.of(dataPath))
                    .skip(1)
                    .map(line -> line.trim().split(","))
                    .map(parts -> new TestResult(parts[0],
                            parts[1],
                            Double.parseDouble(parts[2]),
                            Integer.parseInt(parts[3])
                    ))
                    .toList();


            //a
            Map<String, Long> countBySubject = results.stream()
                    .collect(Collectors.groupingBy(TestResult::getSubject, Collectors.counting()));

            for (String subject : countBySubject.keySet()){
                System.out.println(subject + " --- " + countBySubject.get(subject));
            }

            //b
            results.stream()
                    .distinct()
                    .forEach(System.out::println);


            //c
            Map<String, List<TestResult>> resultsBySubject = results.stream()
                    .collect(Collectors.groupingBy(TestResult::getSubject));


            resultsBySubject.get("IT").stream()
                    .filter(TestResult -> TestResult.score > 70);

            resultsBySubject.get("Biology").stream()
                    .filter(TestResult -> TestResult.score > 70);




            //d
            Map<String, Double> avgScore = results.stream()
                    .collect(Collectors.groupingBy(TestResult::getSubject, Collectors.averagingDouble(TestResult::getScore)));

            for (String result : avgScore.keySet()){
                System.out.println(result + " --- " + avgScore.get(result));
            }

            //e
            Map<String, List<TestResult>> goodStudents = results.stream()
                    .filter(TestResult -> TestResult.score > 80)
                    .collect(Collectors.groupingBy(TestResult::getSubject));

            for (String result : goodStudents.keySet()){
                System.out.println(result + ": ");
                for (TestResult student : goodStudents.get(result)){
                    System.out.println("  --- " + student);
                }
            }

            //f
            Map<String, Long> timeByStudent = results.stream()
                    .collect(Collectors.groupingBy(TestResult::getStudent_name, Collectors.summingLong(TestResult::getTimeSpent)));

            for (String result : timeByStudent.keySet()){
                System.out.println(result + " --- " + timeByStudent.get(result));
            }




        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}


class TestResult{
    String student_name;
    String subject;
    double score;
    int timeSpent;

    public TestResult(String student_name, String subject, double score, int timeSpent) {
        this.student_name = student_name;
        this.subject = subject;
        this.score = score;
        this.timeSpent = timeSpent;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "student_name='" + student_name + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                ", timeSpent=" + timeSpent +
                '}' + "\n";
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }
}
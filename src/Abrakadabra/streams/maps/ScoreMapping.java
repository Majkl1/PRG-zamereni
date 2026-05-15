package Abrakadabra.streams.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreMapping {

    static double getAvarageScore(List<TestResult> results, String name){
        return results.stream()
                .filter(test -> test.getName().equals(name))
                .mapToDouble(TestResult::getScore)
                .average()
                .orElse(0);
    }

    public static void main(String[] args) throws IOException {
        List<TestResult> results = Files.lines(Path.of("data/scores.csv"))
                .skip(1)
                .map(line -> line.trim().split(","))
                .map(t -> new TestResult(
                        t[0],
                        t[1],
                        Double.parseDouble(t[2]),
                        Integer.parseInt(t[3])
                ))
                .toList();

        //mapa studentů dle kategorií -
        //1.good = průměr všech testů >= 50
        //2. bad = průměr všech testů >= 30
        //3. really bad = jinak

        Map<String, List<String>> studentsCategories = results.stream()
                .map(test -> test.getName())
                .distinct()
                .collect(Collectors.groupingBy(
                        name -> {
                            double avg = getAvarageScore(results,name);
                            if (avg >= 50) return "good";
                            if (avg >= 30) return "Bad";
                            return "Really bad";

                        },
                        Collectors.toList()));
        studentsCategories.forEach((category,students) -> System.out.println(category + "\n\t" + students));

    }
}

class TestResult{
    String name, subject;
    double score;
    int time;

    public TestResult(String name, String subject, double score, int time) {
        this.name = name;
        this.subject = subject;
        this.score = score;
        this.time = time;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                ", time=" + time +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
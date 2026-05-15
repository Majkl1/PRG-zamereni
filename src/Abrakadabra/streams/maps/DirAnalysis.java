package Abrakadabra.streams.maps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirAnalysis {

    static String getExtension(String fileName){
        int dotIndex = fileName.lastIndexOf(".");
        return (dotIndex == -1) ? "NO_TYPE" : fileName.substring(dotIndex+1).toLowerCase();
    }

    static void analyzeDirectory(String path) throws IOException{
        Path p = Paths.get(path);

        try (Stream<Path> fileStream = Files.walk(p)){
//            fileStream.forEach(System.out::println);
            List<File> files = fileStream
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .toList();
            System.out.println(files);

            //mapy dle typu souboru
            //1. mapa typ souboru : počet
            Map<String, Long> fileTypeCount = files.stream()
                    .collect(Collectors.groupingBy(file -> getExtension(file.getName()), Collectors.counting()));
            System.out.println(fileTypeCount);

            //2. mapa typSouboru : celkova velikost všech souboru toho typu
            Map<String, Long> fileSizeType = files.stream()
                    .collect(Collectors.groupingBy(file -> getExtension(file.getName()),
                            Collectors.summingLong(File::length)));

        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args) {
        try {
            analyzeDirectory("data");

        } catch (IOException ex){
            System.out.println("Chyba při práci se souborem: " + ex);
        }
    }
}

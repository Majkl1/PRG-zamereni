package Abrakadabra.exams.map;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomsMapping {
    static Room parseLine(String[] token) {
        if (token.length == 2) return new Room(token[0], Integer.parseInt(token[1]));
        if (token.length == 3)
            return new Room(token[0], Integer.parseInt(token[1]), Integer.parseInt(token[2]));
        return null;
    }

    public static void main(String[] args) throws IOException {
        Map<String, Room> roomMap = Files.lines(Path.of("data/rooms.csv"))
                .map(lines -> lines.trim().split(";"))
                .map(RoomsMapping::parseLine)
                .collect(Collectors.toMap(
                        Room::getID,
                        room -> room
                ));


        Files.lines(Path.of("data/bookings.csv"))
                .map(line -> line.trim().split(";"))
                .forEach(parts -> {
                            Room room = roomMap.get(parts[0]);
                            if (room == null) return;
                            room.processBooking(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
                        }
                );

        roomMap.forEach((id, room) -> System.out.println(id + " --- pronajaté dny: " + room.getDays()));
    }
}

class Room{
    String ID;
    int maxGuest;
    int days;

    public Room(String ID, int maxGuest, int days) {
        this.ID = ID;
        this.maxGuest = maxGuest;
        this.days = days;
    }

    public Room(String ID, int days) {
        this.ID = ID;
        this.days = days;
        this.maxGuest = Integer.MAX_VALUE;
    }

    public void processBooking(int people, int days){
        if(people < 0 || days < 0) {
            return;
        }
        if (people < maxGuest){
            return;
        }
        this.days += days;
    }


    @Override
    public String toString() {
        return "Room{" +
                "ID='" + ID + '\'' +
                ", maxGuest=" + maxGuest +
                ", days=" + days +
                '}';
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}


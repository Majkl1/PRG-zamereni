package moje.OOP.inheritance.Hospital;

public class Doctor {
    String name;
    int salary;

    public Doctor(String name) {
        this.name = name;
        this.salary = 25000;
    }

    void diagnose(){
        System.out.println("Figuring whats wrong...");
        salary += 10000;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

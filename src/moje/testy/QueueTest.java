package moje.testy;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        /*
        TODO:
         1. Vytvořte třídy
            1.1 Employee
                - atributy Name, freeTime, shoeSize
                - konstruktor
                . toString, který vypíše jméno a volný čas
            1.2 Task
                - atributy taskName, completeTime
                - konsturktor

         2. Vytvořte frontu employees a tasks
           - vezměte zaměstnance z fronty a přidělte mu task
           - pokud task může vypracovat, tak to udělá,
             jinak zaměstnance odebereme s fronty
           - jakmile jsou všechny tasky hotové, nebo zaměstnanci již nemají volný čas
           - směna končí a program vypíše, který zaměstnanec udělal kolik tasku
           - Pro potřeby úkolu si můžete třídu Employee/Task upravit dle potřeb
        */

        Queue<Employee> employees = new LinkedList<>();
        Queue<Task> tasks = new LinkedList<>();
        Queue<Employee> done = new LinkedList<>();


        employees.add(new Employee("Jarmil", 600, 42));
        employees.add(new Employee("Boris", 480, 42));
        employees.add(new Employee("Lukas", 360, 42));

        tasks.add(new Task("Exchange cables", 60));
        tasks.add(new Task("Update OS", 45));
        tasks.add(new Task("Install antivirus", 30));
        tasks.add(new Task("Replace hard drive", 120));
        tasks.add(new Task("Clean workstation", 20));
        tasks.add(new Task("Configure network", 90));
        tasks.add(new Task("Test hardware", 50));
        tasks.add(new Task("Set up printer", 25));
        tasks.add(new Task("Troubleshoot Wi-Fi", 35));
        tasks.add(new Task("Backup files", 75));
        tasks.add(new Task("Assemble PC", 150));
        tasks.add(new Task("Optimize performance", 40));
        tasks.add(new Task("Create user accounts", 15));
        tasks.add(new Task("Train new staff", 180));
        tasks.add(new Task("Remove malware", 55));
        tasks.add(new Task("Upgrade RAM", 25));
        tasks.add(new Task("Set up email client", 20));
        tasks.add(new Task("Reset passwords", 10));
        tasks.add(new Task("Patch security vulnerabilities", 80));
        tasks.add(new Task("Calibrate display", 30));



        while (!employees.isEmpty()){
            if (!tasks.isEmpty()){
                if (employees.peek().freeTime >= tasks.peek().completeTime){
                    employees.peek().tasksComplete += 1;
                    employees.peek().freeTime -= tasks.poll().completeTime;
                } else {
                    done.add(employees.poll());
                }
            } else {
                done.add(employees.poll());
                System.out.println("Všechny úkoly jsou hotové");
                break;
            }
        }
        while (!done.isEmpty()){
            System.out.println(done.peek().Name + " " +  done.poll().tasksComplete);
        }
    }
}



class Employee{
    String Name;
    int freeTime;
    int shoeSize;
    int tasksComplete;

    public Employee(String name, int freeTime, int shoeSize) {
        Name = name;
        this.freeTime = freeTime;
        this.shoeSize = shoeSize;
    }

    @Override
    public String toString() {
        return this.Name + "; " + freeTime;
    }
}

class Task{
    String taskName;
    int completeTime;

    public Task(String taskName, int completeTime) {
        this.taskName = taskName;
        this.completeTime = completeTime;
    }
}

package salary;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.io.*;
import java.util.*;

public class Worker {
    private String name;
    private String surname;
    private double salary;
    private int numberOfDaysMissed;

    public String getName() {
        return name;
    }

    public Worker() {
    }

    public Worker(String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getNumberOfDaysMissed() {
        return numberOfDaysMissed;
    }

    public void setNumberOfDaysMissed(int numberOfDaysMissed) {
        this.numberOfDaysMissed = numberOfDaysMissed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Double.compare(worker.salary, salary) == 0 && name.equals(worker.name) && surname.equals(worker.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, salary);
    }

    public void addAnWorker(Worker w, File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true));
        String s = objectMapper.writeValueAsString(w);
        printWriter.println(s);
        printWriter.close();

        Scanner scanner = new Scanner(new FileInputStream(file));
        Set<Worker> workerSet = new HashSet<>();
        while (scanner.hasNextLine()) {
            String s2 = scanner.nextLine();
            try {
                Worker worker = objectMapper.readValue(s2, Worker.class);
                workerSet.add(worker);
            } catch (JsonParseException | MismatchedInputException ignored) {
                System.out.println("JsonParseException or MismatchedInputException");
            }
        }

        PrintWriter printWriter2 = new PrintWriter(new FileOutputStream(file));
        for (Worker worker : workerSet) {
            String s3 = objectMapper.writeValueAsString(worker);
            printWriter2.println(s3);
        }
        printWriter2.close();
    }

    public static void deleteAnWorker(Worker w, File file) throws FileNotFoundException
            , JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Worker> workerSet = new HashSet<>();
        Scanner scanner = new Scanner(new FileInputStream(file));
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            try{
                Worker worker = objectMapper.readValue(s, Worker.class);
                if (!w.equals(worker)) {
                    workerSet.add(worker);
                }
            } catch (Exception e){
                System.out.println("Exception!!!");
            }
        }
        scanner.close();
        PrintWriter printWriter = new PrintWriter(file);
        for (Worker worker : workerSet){
            printWriter.println(objectMapper.writeValueAsString(worker));
        }
        printWriter.close();
    }
}

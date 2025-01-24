import java.util.*;
import java.util.stream.Collectors;

public class StudentManagementSystem {


    private final HashMap<String, List<Double>> students = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);


    public void run() {
        while (true) {

            System.out.println("1. Add a student");
            System.out.println("2. Record grades for a student");
            System.out.println("3. Display all students and their average grades");
            System.out.println("4. Retrieve students with grades above a threshold");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> recordGrades();
                case 3 -> displayStudentsAndAverages();
                case 4 -> retrieveStudentsAboveThreshold();
                case 5 -> {
                    System.out.println("Exiting system.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }


    private void addStudent() {
        try {
            System.out.print("Enter name: ");
            String name = scanner.next();

            if (!name.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Name cannot contain numbers.");
            }

        if (students.containsKey(name)) {
            System.out.println("Student already exists.");
        } else {
            students.put(name, new ArrayList<>());
            System.out.println("Student added successfully.");
        }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }


    private void recordGrades() {
        try{
        System.out.print("Enter the student's name: ");
        String name = scanner.next();
            if (!name.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Name cannot contain numbers.");
            }
        if (!students.containsKey(name)) {
            System.out.println("Student not found.");
        } else {
            System.out.print("Enter the amount of grades: ");
            int numGrades = scanner.nextInt();
            List<Double> grades = students.get(name);
            for (int i = 0; i < numGrades; i++) {
                System.out.print("Enter grade: ");
                double grade = scanner.nextDouble();
                if (grade < 0 || grade > 10) {
                    System.out.println("Invalid grade.");
                    i--;
                } else {
                    grades.add(grade);
                }
            }
            System.out.println("Grades added successfully.");
        }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }


    private void displayStudentsAndAverages() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (String student : students.keySet()) {
                List<Double> grades = students.get(student);
                double sum = 0;
                for (double grade : grades) {
                    sum += grade;
                }
                double average = (!grades.isEmpty()) ? sum / grades.size() : 0;
                System.out.println(student + ": " + grades + " (Average: " + average + ")");
            }
        }
    }


    private void retrieveStudentsAboveThreshold() {
        System.out.print("Enter the threshold: ");
        double threshold = scanner.nextDouble();
        boolean found = false;

        for (String student : students.keySet()) {
            List<Double> grades = students.get(student);
            for (double grade : grades) {
                if (grade > threshold) {
                    System.out.println(student + " has a grade above " + threshold);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No students found with grades above " + threshold + ".");
        }}
}

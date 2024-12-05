import java.util.ArrayList;
import java.util.Scanner;

// Student Class
class Student {
    int id;
    String name;
    double marks;
    String rank;

    // Constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.rank = assignRank(marks);
    }

    // Method to assign rank based on marks
    public String assignRank(double marks) {
        if (marks >= 0 && marks < 5.0) return "Fail";
        if (marks >= 5.0 && marks < 6.5) return "Medium";
        if (marks >= 6.5 && marks < 7.5) return "Good";
        if (marks >= 7.5 && marks < 9.0) return "Very Good";
        if (marks >= 9.0 && marks <= 10.0) return "Excellent";
        return "Invalid Marks";
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + rank;
    }
}

// Student Manager Class
class StudentManager {
    ArrayList<Student> students = new ArrayList<>();

    // Add Student
    public void addStudent(int id, String name, double marks) {
        Student newStudent = new Student(id, name, marks);
        students.add(newStudent);
    }

    // Edit Student
    public void editStudent(int id, String newName, double newMarks) {
        for (Student student : students) {
            if (student.id == id) {
                student.name = newName;
                student.marks = newMarks;
                student.rank = student.assignRank(newMarks); // Reassign rank
                break;
            }
        }
    }

    // Delete Student
    public void deleteStudent(int id) {
        students.removeIf(student -> student.id == id);
    }

    // Sort Students using Bubble Sort
    public void bubbleSortStudents() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).marks > students.get(j + 1).marks) {
                    // Swap the students
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    // Search for a student by ID
    public Student searchStudent(int id) {
        for (Student student : students) {
            if (student.id == id) {
                return student;
            }
        }
        return null;  // Not found
    }

    // Display all students
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}

// Main Application Class
public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\n----- Student Management System -----");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students by Marks");
            System.out.println("5. Search Student");
            System.out.println("6. Display All Students");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Add Student
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Marks: ");
                    double marks = scanner.nextDouble();
                    manager.addStudent(id, name, marks);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    // Edit Student
                    System.out.print("Enter Student ID to Edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Marks: ");
                    double newMarks = scanner.nextDouble();
                    manager.editStudent(editId, newName, newMarks);
                    System.out.println("Student updated successfully!");
                    break;

                case 3:
                    // Delete Student
                    System.out.print("Enter Student ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteStudent(deleteId);
                    System.out.println("Student deleted successfully!");
                    break;

                case 4:
                    // Sort Students
                    manager.bubbleSortStudents();
                    System.out.println("Students sorted by marks.");
                    break;

                case 5:
                    // Search for a Student
                    System.out.print("Enter Student ID to Search: ");
                    int searchId = scanner.nextInt();
                    Student student = manager.searchStudent(searchId);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    // Display All Students
                    manager.displayStudents();
                    break;

                case 7:
                    // Exit
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

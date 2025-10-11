package io.github.darlene;

import java.util.Scanner;

public class StudentGradeSystem {
    //Instance variables for Data Storage
    final int MAX_STUDENTS = 50;
    String[] studentNames = new String[MAX_STUDENTS];
    int[] studentGrades = new int[MAX_STUDENTS];
    int studentCount = 0;

    //=====FEATURE 1: ADD STUDENT =======
    boolean addStudent(String name, int grade){
        if (studentCount >= MAX_STUDENTS){
            System.out.println("Cannot add more students");
            return false;
        }

        if (grade < 0 || grade > 100){
            System.out.println("Invalid grade range: Must be between 0-100");
            return false;
        }

        studentNames[studentCount] = name;
        studentGrades[studentCount] = grade;
        studentCount++;
        System.out.println("Student added successfully");
        return true;
    }

    //====FEATURE 2: CALCULATE AVERAGE
    static double calculateAverage(int []grades, int count){
        if (count ==0){
            return 0;
        }

        int sum = 0;

        for(int i = 0; i < count-1; i++){
            sum += grades[i];
        }
        double average = (double) sum/count;

        return average;
    }

    //===FEATURE 3: FIND THE HIGHEST GRADE=======
    void findHighestGrade(){
        if (studentCount == 0){
            System.out.println("There are no students in the system.");
            return;
        }

        int highest = studentGrades[0];
        int highestIndex = 0;

        for (int i = 1; i < studentCount; i++){
            if (studentGrades[i] > highest){
                highest = studentGrades[i];
                highestIndex = i;
            }
        }

        System.out.println("Highest Grade: " + highest);
        System.out.println("Student: " + studentNames[highestIndex]);

    }

    // FEATURE====3B: FIND THE LOWEST GRADE
    void findLowestGrade(){
        if (studentCount == 0){
            System.out.println("There are no students in the system yet");
            return;
        }

        int lowest = studentGrades[0];
        int lowestIndex = 0;

        for(int i = 1; i < studentCount; i++){
            if(studentGrades[i] < lowest){
                lowest = studentGrades[i];
                lowestIndex = i;
            }
        }

        System.out.println("The Lowest Grade: " + lowest);
        System.out.println("Student: " + studentNames[lowestIndex]);

    }

    //===FEATURE 4: GRADE DISTRIBUTION =======
    void displayGradeDistribution(){
        if (studentCount == 0){
            System.out.println("There are no students in the system yet.");
            return;
        }

        int countA=0, countB=0, countC= 0, countD = 0, countE=0;

        for (int i = 0; i <= studentCount-1; i++){
            int grade = studentGrades[i];

            if (grade >= 90){
                countA += 1;
            } else if(grade >= 80){
                countB +=1;
            } else if(grade >= 70){
                countC +=1;
            } else if(grade >= 60){
                countD +=1;
            } else{
                countE +=1;
            }
        }

        System.out.println("Grade Distribution: ");
        System.out.println("A (90 - 100) : " + countA);
        System.out.println("B (80 - 89) : " + countB);
        System.out.println("C (70 - 79) : " + countC);
        System.out.println("D (60 - 69) : " + countD);
        System.out.println("E (0 - 59) : " + countE);

    }
    //===FEATURE 5: SEARCH STUDENT =======
    boolean searchStudent(String name){
        for (int i = 0; i <= studentCount-1; i++){
            if (studentNames[i].equalsIgnoreCase(name)){
                System.out.println("Student: " + studentNames[i]);
                System.out.println("Grade: " + studentGrades[i]);
                return true;
            }
        }

        System.out.println("Student not found");
        return false;
    }

    //==== FEATURE 6: ADD MULTIPLE STUDENTS (VARARGS) =======
    void addMultipleStudents(Object... studentData) {
        // studentData in pairs: name1, grade1, name2, grade2...
        if (studentData.length % 2 != 0) {
            System.out.println("Invalid data: must provide name-grade pairs");
            return;
        }

        for (int i = 0; i < studentData.length; i += 2) {
            // Cast name
            String name;
            if (studentData[i] instanceof String) {
                name = (String) studentData[i];
            } else {
                System.out.println("Invalid name at position " + i);
                continue;
            }

            // Cast grade safely
            int grade;
            if (studentData[i + 1] instanceof Integer) {
                grade = (Integer) studentData[i + 1]; // auto-unboxing to int
            } else {
                System.out.println("Invalid grade at position " + (i + 1));
                continue;
            }

            // Add student
            addStudent(name, grade);
        }
    }

    //===DISPLAY ALL THE STUDENTS =======
    void displayAllStudents(){
        if(studentCount == 0){
            System.out.println("There are no students in the system");
            return;
        }
        System.out.println("List of Students:");
        for (int i = 0; i <= studentCount-1; i++){
            System.out.println((i+1) + ". " + studentNames[i] + " - Grade: " + studentGrades[i]);
        }

        double average = calculateAverage(studentGrades, studentCount);
        System.out.println("Class Average: " + String.format("%.2f", average));
    }
    void displayMenu(){
        System.out.println("======= Student Grade Management System =========");
        System.out.println("1. Add Student");
        System.out.println("2.Display all students");
        System.out.println("3. Find Highest Grade");
        System.out.println("4. Find lowest Grade.");
        System.out.println("5. Display Grade Distribution");
        System.out.println("6. Search Student");
        System.out.println("7. Add Multiple Students");
        System.out.println("8. Exit");
        System.out.println("Enter choice");
    }

    //=====MAIN METHOD========
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        StudentGradeSystem obj = new StudentGradeSystem();
        int choice;

        do{
            obj.displayMenu();
            System.out.println("Enter your choice");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter the student's name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter the student's grade: ");
                    int grade = sc.nextInt();
                    sc.nextLine();
                    obj.addStudent(name, grade);
                    break;

                case 2:
                    obj.displayAllStudents();
                    break;

                case 3:
                    obj.findHighestGrade();
                    break;

                case 4:
                    obj.findLowestGrade();
                    break;

                case 5:
                    obj.displayGradeDistribution();
                    break;
                case 6:
                    System.out.print("Enter the student's name to search");
                    String searchName = sc.nextLine();
                    obj.searchStudent(searchName);
                    break;

                case 7:
                    System.out.println("How many students to add?");
                    int count = sc.nextInt();
                    sc.nextLine();
                    // An array to hold the data in name-grade pairs
                    Object [] data = new Object[count];
                    for (int i = 0; i < count; i++){
                        System.out.println("Enter name for student " + (i+1) + ": ");
                        String studentName = sc.nextLine();
                        System.out.println("Enter grade for student " + (i+1) + ": ");
                        int studentGrade = sc.nextInt();
                        sc.nextLine();

                        data[i*2] = studentName; // name at even index
                        data[i*2 + 1] = studentGrade; // grade at odd index
                    }
                    // Call varargs method with array
                    obj.addMultipleStudents(data);
                    break;

                case 8:
                    System.out.println("Good bye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
        sc.close();
    }
}

import java.util.Scanner;

public class GradeEvaluator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(colorize("---- Student Grade Calculator ----", Color.GREEN));

        System.out.print("\nEnter the student's ID: ");
        int studentId = scanner.nextInt();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        System.out.println("Enter the marks (out of 100)");
        int[] subjectMarks = new int[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Subject  " + (i + 1) + ": ");
            subjectMarks[i] = scanner.nextInt();
        }
         System.out.println("______________________");
         System.out.print(colorize("\nResults\n", Color.MAGENTA));
       
        int totalMarks = calculateTotalMarks(subjectMarks);

        double averagePercentage = calculateAveragePercentage(totalMarks, numSubjects);

        char grade = determineGrade(averagePercentage);

        displayResults(totalMarks, averagePercentage, grade);

       

        scanner.close();
    }

    private static int calculateTotalMarks(int[] subjectMarks) {
        int totalMarks = 0;
        for (int mark : subjectMarks) {
            totalMarks += mark;
        }
        return totalMarks;
    }

    private static double calculateAveragePercentage(int totalMarks, int numSubjects) {
        return (double) totalMarks / numSubjects;
    }

    private static char determineGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else if (averagePercentage >= 50) {
            return 'E';
        } else {
            return 'F';
        }
    }
     
    private static void displayResults( int totalMarks, double averagePercentage, char grade) {
        
        System.out.println("Total Marks: " + colorize(Integer.toString(totalMarks), Color.YELLOW));
        System.out.println("Average Percentage: " + colorize(String.format("%.2f", averagePercentage) + "%", Color.BLUE));
        System.out.println("Grade: " + colorize(Character.toString(grade), getGradeColor(grade)));
    }

    private static String colorize(String text, Color color) {
        return color.code + text + Color.RESET.code;
    }

    private static Color getGradeColor(char grade) {
        switch (grade) {
            case 'A':
                return Color.GREEN;
            case 'B':
                return Color.YELLOW;
            case 'C':
                return Color.BLUE;
            case 'D':
                return Color.RED;
            case 'E':
                return Color.MAGENTA; 
            default:
                return Color.RED;
        }
    }

    enum Color {
        RESET("\u001B[0m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        MAGENTA("\u001B[35m"); 

        private final String code;

        Color(String code) {
            this.code = code;
        }
        public String colorize(String text) {
            return this.code + text + Color.RESET.code;
        }
    }
}

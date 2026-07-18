import java.util.*;

public class PecoGradeAnalysisSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean replay = true;
        while (replay) {
            System.out.print("Enter the number of students: ");
            int n = readInt(sc, 1, Integer.MAX_VALUE);

            String[] names = new String[n];
            int[] scores = new int[n];

            for (int i = 0; i < n; i++) {
                System.out.println("\nStudent " + (i + 1));
                names[i] = readName(sc);

                System.out.print("Final Score (0-100): ");
                scores[i] = readInt(sc, 0, 100);
            }

            int total = 0, passed = 0, failed = 0, highestScore = -1;
            ArrayList<String> topStudents = new ArrayList<>();

            int maxNameLength = "Name".length();
            for (String name : names) {
                if (name.length() > maxNameLength) {
                    maxNameLength = name.length();
                }
            }

            System.out.println("\n* * * * * * * * * * STUDENT GRADES * * * * * * * * * *");
            System.out.printf("%-" + (maxNameLength + 2) + "s %-10s %-10s%n", "Name", "Score", "Grade");
            System.out.println("-".repeat(maxNameLength + 2 + 10 + 10));

            for (int i = 0; i < n; i++) {
                String grade;
                if (scores[i] >= 98) grade = "A+";
                else if (scores[i] >= 92) grade = "A";
                else if (scores[i] >= 87) grade = "B+";
                else if (scores[i] >= 81) grade = "B";
                else if (scores[i] >= 77) grade = "C+";
                else if (scores[i] >= 71) grade = "C";
                else if (scores[i] >= 60) grade = "D";
                else grade = "F";

                System.out.printf("%-" + (maxNameLength + 2) + "s %-10d %-10s%n", names[i], scores[i], grade);

                total += scores[i];
                if (scores[i] >= 60) passed++;
                else failed++;

                if (scores[i] > highestScore) {
                    highestScore = scores[i];
                    topStudents.clear();
                    topStudents.add(names[i]);
                } else if (scores[i] == highestScore) {
                    topStudents.add(names[i]);
                }
            }

            double average = (double) total / n;
            boolean below70 = average < 70;

            System.out.println("\n* * * * * * * * * * CLASS SUMMARY * * * * * * * * * *");
            System.out.println("Top Student(s):");
            for (String student : topStudents) {
                System.out.println("  " + student + " - Score: " + highestScore);
            }
            System.out.println("");
            System.out.printf("%-20s %-10.2f%n", "Class Average:", average);
            System.out.printf("%-20s %-10d%n", "Students Passed:", passed);
            System.out.printf("%-20s %-10d%n", "Students Failed:", failed);
            System.out.printf("%-20s %-10b%n", "Average Below 70?", below70);

            System.out.print("\nAgain? Y/N: ");
            replay = askYN(sc);
        }

        sc.close();
    }

    public static int readInt(Scanner sc, int min, int max) {
        while (true) {
            String input = sc.nextLine().replaceAll("\\s+", "");
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.print("Invalid range. Enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter an integer: ");
            }
        }
    }

    public static String readName(Scanner sc) {
        while (true) {
            String name = sc.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.print("Name can't be empty. Enter again: ");
            }
        }
    }

    public static boolean askYN(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.print("Invalid input. Enter Y or N: ");
            }
        }
    }
}

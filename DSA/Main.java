import java.util.Scanner;
import java.util.List;

public class Main {
    private static PatientQueue queue = new PatientQueue();
    private static long sequenceCounter = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("  HOSPITAL PATIENT PRIORITY HANDLING SYSTEM");
        System.out.println("=================================================");

        while (running) {
            System.out.println("\nMenu Options:");
            System.out.println("1 Register Patient");
            System.out.println("2 Display All Patients");
            System.out.println("3 Search Patient by ID");
            System.out.println("4 Serve Next Patient");
            System.out.println("5 Delete Patient");
            System.out.println("6 Exit");
            System.out.print("Enter your choice: ");

            String choiceStr = scanner.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                // Ignore invalid format, default switch case will handle
            }

            switch (choice) {
                case 1:
                    registerPatient(scanner);
                    break;
                case 2:
                    displayPatients();
                    break;
                case 3:
                    searchPatientById(scanner);
                    break;
                case 4:
                    servePatient();
                    break;
                case 5:
                    deletePatient(scanner);
                    break;
                case 6:
                    System.out.println("Exiting application.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void registerPatient(Scanner scanner) {
        System.out.println("\n--- Register Patient ---");
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();
        
        if (queue.searchById(id) != null) {
            System.out.println("Error: A patient with this ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = 0;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age.");
            return;
        }

        System.out.print("Enter Symptoms: ");
        String symptoms = scanner.nextLine();

        System.out.print("Enter Priority Level (Critical, High, Moderate, Mild, Low): ");
        String priorityStr = scanner.nextLine();
        
        int priorityScore = PriorityUtils.getPriorityScore(priorityStr);
        if (priorityScore == 0) {
            System.out.println("Invalid priority, defaulting to 'Low'.");
            priorityStr = "Low";
            priorityScore = 1;
        }

        sequenceCounter++;
        Patient p = new Patient(id, name, age, symptoms, priorityStr, priorityScore, sequenceCounter);
        
        // CO2 – Abstract Data Types (ADTs)
        // Insert operation
        queue.enqueue(p);

        System.out.println("Patient registered successfully!");
    }

    private static void displayPatients() {
        System.out.println("\n--- Display All Patients ---");
        
        // CO2 – Abstract Data Types (ADTs)
        // Traverse operation
        List<Patient> list = queue.getAllPatients();
        if (list.isEmpty()) {
            System.out.println("No patients records.");
        } else {
            System.out.format("%-10s | %-20s | %-5s | %-12s | %-30s\n", "ID", "Name", "Age", "Priority", "Symptoms");
            System.out.println("-----------------------------------------------------------------------------------------");
            for (Patient p : list) {
                System.out.format("%-10s | %-20s | %-5d | %-12s | %-30s\n", 
                    p.getId(), p.getName(), p.getAge(), p.getPriorityLevel(), p.getSymptoms());
            }
        }
    }

    private static void servePatient() {
        System.out.println("\n--- Serve Next Patient ---");
        
        // CO3 – Stack / Queue Concepts
        // Priority-based processing
        // CO4 – Java Collections
        // Queue behaviour using List operations
        Patient p = queue.dequeue();
        
        if (p == null) {
            System.out.println("No patients to serve.");
        } else {
            System.out.println("Serving Patient:");
            System.out.println("ID: " + p.getId());
            System.out.println("Name: " + p.getName());
            System.out.println("Priority: " + p.getPriorityLevel());
            System.out.println("Symptoms: " + p.getSymptoms());
        }
    }

    private static void searchPatientById(Scanner scanner) {
        System.out.println("\n--- Search Patient ---");
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        // CO1 – Algorithm Analysis, Searching and Sorting
        // Linear Search
        Patient p = queue.searchById(id);
        
        if (p != null) {
            System.out.println("Patient Found:");
            System.out.println("ID: " + p.getId() + ", Name: " + p.getName() + ", Priority: " + p.getPriorityLevel());
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void deletePatient(Scanner scanner) {
        System.out.println("\n--- Delete Patient ---");
        System.out.print("Enter Patient ID to delete: ");
        String id = scanner.nextLine();

        // CO2 – Abstract Data Types (ADTs)
        // Delete operation
        boolean removed = queue.deleteById(id);
        
        if (removed) {
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }
}

/*
CO ATTAINMENT SUMMARY

CO1 – Algorithm Analysis, Searching and Sorting
CO2 – Abstract Data Types (ADTs)
CO3 – Stack / Queue Concepts
CO4 – Java Collections
*/

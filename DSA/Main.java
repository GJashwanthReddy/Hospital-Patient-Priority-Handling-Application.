import java.util.Scanner;
import java.util.List;

public class Main {
    private static PatientQueue queue = new PatientQueue();
    private static long sequenceCounter = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("  Hospital Patient Priority Handling System");
        System.out.println("=================================================");

        while (running) {
            System.out.println("\nMenu Options:");
            System.out.println("1. Register Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Serve Next Patient");
            System.out.println("4. Search Patient by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choiceStr = scanner.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                // Invalid input gets handled by default case
            }

            switch (choice) {
                case 1:
                    registerPatient(scanner);
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    serveNextPatient();
                    break;
                case 4:
                    searchPatientById(scanner);
                    break;
                case 5:
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
    }

    private static void registerPatient(Scanner scanner) {
        System.out.println("\n--- Register New Patient ---");
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();
        
        // Prevent duplicate IDs
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
            System.out.println("Invalid age. Registration failed.");
            return;
        }

        System.out.print("Enter Symptoms: ");
        String symptoms = scanner.nextLine();

        System.out.print("Enter Priority Level (Critical, High, Moderate, Mild, Low): ");
        String priorityStr = scanner.nextLine();
        
        // Validate priority
        int priorityScore = PriorityUtils.getPriorityScore(priorityStr);
        if (priorityScore == 0) {
            System.out.println("Invalid priority level. Defaulting to 'Low'.");
            priorityStr = "Low";
            priorityScore = 1;
        }

        sequenceCounter++;
        Patient p = new Patient(id, name, age, symptoms, priorityStr, priorityScore, sequenceCounter);
        
        // Queue handles Enqueue and Sorting automatically
        queue.enqueue(p);

        System.out.println("Patient registered successfully!");
    }

    private static void viewAllPatients() {
        System.out.println("\n--- All Patients ---");
        List<Patient> list = queue.getAllPatients();
        if (list.isEmpty()) {
            System.out.println("No patients currently in the queue.");
        } else {
            printTable(list);
        }
    }

    private static void serveNextPatient() {
        System.out.println("\n--- Serve Next Patient ---");
        Patient p = queue.dequeue();
        if (p == null) {
            System.out.println("No patients to serve.");
        } else {
            System.out.println("Serving highest priority Patient:");
            System.out.println("ID: " + p.getId());
            System.out.println("Name: " + p.getName());
            System.out.println("Priority: " + p.getPriorityLevel());
            System.out.println("Symptoms: " + p.getSymptoms());
            System.out.println("Patient successfully served and removed from queue.");
        }
    }

    private static void searchPatientById(Scanner scanner) {
        System.out.println("\n--- Search Patient ---");
        System.out.print("Enter Patient ID to search: ");
        String id = scanner.nextLine();

        Patient p = queue.searchById(id);
        if (p != null) {
            System.out.println("Patient Found:");
            System.out.format("%-10s | %-20s | %-5s | %-12s | %-30s\n", "ID", "Name", "Age", "Priority", "Symptoms");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.format("%-10s | %-20s | %-5d | %-12s | %-30s\n", 
                p.getId(), p.getName(), p.getAge(), p.getPriorityLevel(), p.getSymptoms());
        } else {
            System.out.println("Patient with ID '" + id + "' not found.");
        }
    }

    private static void printTable(List<Patient> list) {
        System.out.format("%-10s | %-20s | %-5s | %-12s | %-30s\n", "ID", "Name", "Age", "Priority", "Symptoms");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Patient p : list) {
            System.out.format("%-10s | %-20s | %-5d | %-12s | %-30s\n", 
                p.getId(), p.getName(), p.getAge(), p.getPriorityLevel(), p.getSymptoms());
        }
    }
}

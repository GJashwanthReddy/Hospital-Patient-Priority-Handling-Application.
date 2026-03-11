import java.util.List;

public class PriorityUtils {
    
    public static int getPriorityScore(String priorityLevel) {
        // Higher score corresponds to higher priority
        switch(priorityLevel.toUpperCase()) {
            case "CRITICAL": return 5;
            case "HIGH": return 4;
            case "MODERATE": return 3;
            case "MILD": return 2;
            case "LOW": return 1;
            default: return 0;
        }
    }

    // DSA Concept: Sorting (Using Bubble Sort to arrange patients by descending priority)
    public static void sortPatients(List<Patient> patients) {
        int n = patients.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Patient p1 = patients.get(j);
                Patient p2 = patients.get(j + 1);
                
                // Compare by priority score (descending order)
                if (p1.getPriorityScore() < p2.getPriorityScore()) {
                    // Swap
                    patients.set(j, p2);
                    patients.set(j + 1, p1);
                } 
                // If priorities are equal, maintain FIFO based on arrival sequence
                else if (p1.getPriorityScore() == p2.getPriorityScore()) {
                    if (p1.getArrivalSequence() > p2.getArrivalSequence()) {
                        // Swap
                        patients.set(j, p2);
                        patients.set(j + 1, p1);
                    }
                }
            }
        }
    }
}

import java.util.List;

public class PriorityUtils {
    
    public static int getPriorityScore(String priorityLevel) {
        switch(priorityLevel.toUpperCase()) {
            case "CRITICAL": return 5;
            case "HIGH": return 4;
            case "MODERATE": return 3;
            case "MILD": return 2;
            case "LOW": return 1;
            default: return 0;
        }
    }

    // CO1 – Algorithm Analysis, Searching and Sorting
    // Sorting algorithm to arrange patients by priority
    public static void sortPatients(List<Patient> patients) {
        int n = patients.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Patient p1 = patients.get(j);
                Patient p2 = patients.get(j + 1);
                
                if (p1.getPriorityScore() < p2.getPriorityScore()) {
                    patients.set(j, p2);
                    patients.set(j + 1, p1);
                } 
                else if (p1.getPriorityScore() == p2.getPriorityScore()) {
                    if (p1.getArrivalSequence() > p2.getArrivalSequence()) {
                        patients.set(j, p2);
                        patients.set(j + 1, p1);
                    }
                }
            }
        }
    }
}

/*
CO ATTAINMENT SUMMARY

CO1 – Algorithm Analysis, Searching and Sorting
CO2 – Abstract Data Types (ADTs)
CO3 – Stack / Queue Concepts
CO4 – Java Collections

COs Not Used: CO5, CO6
*/

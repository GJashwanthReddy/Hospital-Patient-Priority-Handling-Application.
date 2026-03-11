import java.util.ArrayList;
import java.util.List;

public class PatientQueue {
    
    // CO2 – Abstract Data Types (ADTs)
    // ArrayList to store patient records
    // CO4 – Java Collections
    // List (ArrayList)
    private List<Patient> queue;

    public PatientQueue() {
        queue = new ArrayList<>();
    }

    // CO2 – Abstract Data Types (ADTs)
    // Insert operation
    public void enqueue(Patient p) {
        queue.add(p);
        PriorityUtils.sortPatients(queue);
    }

    // CO3 – Stack / Queue Concepts
    // Queue behaviour for serving patients
    // Dequeue operation
    // CO4 – Java Collections
    // Queue behaviour using List operations
    public Patient dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.remove(0); 
    }

    // CO2 – Abstract Data Types (ADTs)
    // Traverse operation
    public List<Patient> getAllPatients() {
        return queue;
    }

    // CO1 – Algorithm Analysis, Searching and Sorting
    // Linear Search
    // CO2 – Abstract Data Types (ADTs)
    // Search operation
    public Patient searchById(String id) {
        for (Patient p : queue) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
    
    // CO2 – Abstract Data Types (ADTs)
    // Delete operation
    public boolean deleteById(String id) {
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).getId().equalsIgnoreCase(id)) {
                queue.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

/*
CO ATTAINMENT SUMMARY

CO1 – Algorithm Analysis, Searching and Sorting
CO2 – Abstract Data Types (ADTs)
CO3 – Stack / Queue Concepts
CO4 – Java Collections
*/

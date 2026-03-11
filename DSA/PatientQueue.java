import java.util.ArrayList;
import java.util.List;

public class PatientQueue {
    // DSA Concept: Array / List (Using ArrayList to store queue elements)
    private List<Patient> queue;

    public PatientQueue() {
        queue = new ArrayList<>();
    }

    // DSA Concept: Queue (Enqueue operation using ArrayList add)
    public void enqueue(Patient p) {
        queue.add(p);
        // Ensure the queue is sorted so that highest priority comes first
        PriorityUtils.sortPatients(queue);
    }

    // DSA Concept: Queue (Dequeue operation retrieving and removing the first element)
    public Patient dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        // Serve the highest priority which is at index 0 after sorting
        return queue.remove(0); 
    }

    public List<Patient> getAllPatients() {
        return queue;
    }

    // DSA Concept: Searching (Linear Search through the ArrayList)
    public Patient searchById(String id) {
        for (Patient p : queue) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

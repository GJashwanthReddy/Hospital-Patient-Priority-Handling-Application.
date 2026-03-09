/**
 * Hospital Patient Priority Handling Application - DSA Demonstration
 * Concepts covered: Class structure, Array, Sorting, Queue (FIFO), Linear Search
 * 
 * Note: This class uses LocalStorage to ensure data persists across multiple
 * HTML pages (home.html, register.html, patients.html, serve.html).
 */

class PatientQueue {
    constructor() {
        // 1. Array / List Implementation
        // Used to store patient records. We fetch from localStorage if data exists.
        this.patients = JSON.parse(localStorage.getItem('hospital_patients_queue')) || [];
        
        // Priority map for use in sorting
        this.priorityMap = {
            "Critical": 1,
            "High": 2,
            "Moderate": 3,
            "Mild": 4,
            "Low": 5
        };
    }

    // Helper method to sync array to localStorage for multi-page integration
    _saveData() {
        localStorage.setItem('hospital_patients_queue', JSON.stringify(this.patients));
    }

    /**
     * Integrates with Register Form
     * @param {number} id 
     * @param {string} name 
     * @param {number} age 
     * @param {string} symptoms 
     * @param {string} priority 
     */
    addPatient(id, name, age, symptoms, priority) {
        // Create an object and push to the array
        const newPatient = { id, name, age, symptoms, priority };
        this.patients.push(newPatient); // Array Insertion
        
        this._saveData();
        console.log(`Patient added successfully: ID ${id} | Name: ${name}`);
    }

    /**
     * 2. Priority Sorting Algorithm
     * Sorts patients array using a custom mapping.
     */
    sortByPriority() {
        // JavaScript's sort function uses an in-place algorithm calculation.
        // We evaluate strings to numerical priority to arrange correctly.
        this.patients.sort((a, b) => {
            return this.priorityMap[a.priority] - this.priorityMap[b.priority];
        });
        
        this._saveData();
        console.log("Queue has been successfully sorted by priority levels.");
        return this.patients; // Returns sorted array to update UI tables
    }

    /**
     * 3. Queue Concept (Dequeue)
     * Simulates dequeue behavior. 
     * Ensures highest priority is served (FIFO relative to sorting).
     */
    servePatient() {
        if (this.patients.length === 0) {
            console.log("No patients in the queue to serve.");
            return null;
        }

        // We sort before serving to ensure the highest priority is always in front (index 0)
        this.sortByPriority();

        // Queue Operation: Dequeue
        // shift() removes and returns the first element of an array
        const servedPatient = this.patients.shift(); 
        
        this._saveData();
        console.log(`Now Serving -> [ID: ${servedPatient.id}] ${servedPatient.name}`);
        return servedPatient; // Returning patient allows UI to display who is being served
    }

    /**
     * 4. Searching Technique (Linear Search)
     * Implements a standard linear search algorithm over the array.
     * @param {number} searchId 
     */
    searchPatient(searchId) {
        // Iterating through the array iteratively
        for (let i = 0; i < this.patients.length; i++) {
            // Check if current element's ID matches the argument
            if (this.patients[i].id == searchId) {
                console.log(`Search Hit: Found ${this.patients[i].name} with Priority ${this.patients[i].priority}`);
                return this.patients[i]; // Return data to UI populate view
            }
        }
        
        // Iteration complete without finding match
        console.log(`Search Miss: Patient with ID ${searchId} was not found in the system.`);
        return null;
    }

    /**
     * Helper to get list of patients (e.g., to generate table rows)
     */
    getPatients() {
        return this.patients;
    }
}

// Instantiate it globally so your HTML button onClick events and script.js can access it directly.
// Example Usage in UI: hospitalData.addPatient(101, 'John', 40, 'Flu', 'Mild');
const hospitalData = new PatientQueue();

class PatientQueue {

    constructor() {

        // CO2 – Abstract Data Type using Arrays
        // Topic: Array used to store patient records
        // Operation: Initialization of patient storage
        this.patients = JSON.parse(localStorage.getItem('hospital_patients_queue')) || [];

        // Priority mapping used for sorting algorithm
        this.priorityMap = {
            "Critical": 1,
            "High": 2,
            "Moderate": 3,
            "Mild": 4,
            "Low": 5
        };
    }

    // CO6 – Development of DS based application
    // Topic: Integration of data structures with browser storage
    _saveData() {
        localStorage.setItem('hospital_patients_queue', JSON.stringify(this.patients));
    }

    /**
     * Integrates with Register Form
     */
    addPatient(id, name, age, symptoms, priority) {

        const newPatient = { id, name, age, symptoms, priority };

        // CO2 – Array ADT operation
        // Topic: Insert operation using push()
        this.patients.push(newPatient);

        this._saveData();
        console.log(`Patient added successfully: ID ${id} | Name: ${name}`);
    }

    /**
     * Priority Sorting Algorithm
     */

    // CO5 – Practical application of linear data structures
    // Topic: Sorting algorithm used to arrange patients based on priority
    sortByPriority() {

        this.patients.sort((a, b) => {
            return this.priorityMap[a.priority] - this.priorityMap[b.priority];
        });

        this._saveData();
        console.log("Queue has been successfully sorted by priority levels.");

        return this.patients;
    }

    /**
     * Queue Concept (Dequeue)
     */

    // CO3 – Queue data structure implementation
    // Topic: FIFO queue behavior
    servePatient() {

        if (this.patients.length === 0) {
            console.log("No patients in the queue to serve.");
            return null;
        }

        // Sorting ensures highest priority patient is served first
        this.sortByPriority();

        // CO3 – Queue Dequeue Operation
        // Topic: Removing first element using shift()
        const servedPatient = this.patients.shift();

        this._saveData();
        console.log(`Now Serving -> [ID: ${servedPatient.id}] ${servedPatient.name}`);

        return servedPatient;
    }

    /**
     * Searching Technique (Linear Search)
     */

    // CO2 – Array traversal and searching
    // Topic: Linear search algorithm
    searchPatient(searchId) {

        for (let i = 0; i < this.patients.length; i++) {

            if (this.patients[i].id == searchId) {

                console.log(`Search Hit: Found ${this.patients[i].name} with Priority ${this.patients[i].priority}`);

                return this.patients[i];
            }
        }

        console.log(`Search Miss: Patient with ID ${searchId} was not found in the system.`);

        return null;
    }

    /**
     * Helper to return patient list
     */

    // CO6 – Development of DS application
    // Topic: Integration with frontend UI for displaying data
    getPatients() {
        return this.patients;
    }
}


// Global object accessible across the application
// CO6 – Data structure based application development
const hospitalData = new PatientQueue();


/*
-------------------------------------
DSA CO ATTAINMENT SUMMARY
-------------------------------------

Attained COs:
CO2 – Abstract Data Types using Arrays
CO3 – Queue data structure
CO5 – Linear Data Structure Application
CO6 – Development of DS based program

Not Attained COs:
CO1 – Algorithm complexity analysis (Big-O)
CO4 – Hash based data structures

-------------------------------------
*/
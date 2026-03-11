public class Patient {
    private String id;
    private String name;
    private int age;
    private String symptoms;
    private String priorityLevel;
    private int priorityScore; 
    private long arrivalSequence;

    public Patient(String id, String name, int age, String symptoms, String priorityLevel, int priorityScore, long arrivalSequence) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
        this.priorityLevel = priorityLevel;
        this.priorityScore = priorityScore;
        this.arrivalSequence = arrivalSequence;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getSymptoms() { return symptoms; }
    public String getPriorityLevel() { return priorityLevel; }
    public int getPriorityScore() { return priorityScore; }
    public long getArrivalSequence() { return arrivalSequence; }
}

/*
CO ATTAINMENT SUMMARY

CO1 – Algorithm Analysis, Searching and Sorting
CO2 – Abstract Data Types (ADTs)
CO3 – Stack / Queue Concepts
CO4 – Java Collections
*/

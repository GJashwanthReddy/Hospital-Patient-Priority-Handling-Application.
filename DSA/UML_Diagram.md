# DSA – Hospital Patient Priority Handling System
## UML Class Diagram + System Architecture

---

## 1. Project Directory Structure

```
FWD/DSA/
├── src/                        ← Java source files
│   ├── Main.java               ← Entry point & controller
│   ├── Patient.java            ← Data model / entity
│   ├── PatientQueue.java       ← ADT – Priority Queue
│   └── PriorityUtils.java      ← Utility / helper
│
├── bin/                        ← Compiled .class output (javac)
│   ├── Main.class
│   ├── Patient.class
│   ├── PatientQueue.class
│   └── PriorityUtils.class
│
├── lib/                        ← External dependencies (JARs)
│   └── README.md               ← (No JARs needed – uses java.util)
│
├── Main.java                   ← Root copies (direct compile fallback)
├── Patient.java
├── PatientQueue.java
├── PriorityUtils.java
└── UML_Diagram.md / .png
```

---

## 2. System Architecture (Layered View)

```
╔══════════════════════════════════════════════════════════════╗
║              [ lib/ ]  External Libraries                    ║
║    java.util.Scanner  │  java.util.ArrayList │ java.util.List║
║        (No external JARs — Standard Library only)            ║
╚══════════════════════════════════════════════════════════════╝
                              ▲ imports
╔══════════════════════════════════════════════════════════════╗
║              [ src/ ]  Java Source Files                     ║
╠════════════════╦══════════════════╦═══════════╦═════════════╣
║   Main.java    ║ PatientQueue.java ║Patient.java║PriorityUtils║
║ <<Controller>> ║  <<ADT / PQ>>    ║ <<Entity>> ║ <<Utility>> ║
║ Entry point    ║ ArrayList-backed ║ 7 fields   ║ Bubble Sort ║
║ Menu I/O       ║ enqueue/dequeue  ║ getters    ║ getScore    ║
╚════════════════╩══════════════════╩═══════════╩═════════════╝
                    │  javac -d bin/ src/*.java
                    ▼
╔══════════════════════════════════════════════════════════════╗
║              [ bin/ ]  Compiled .class Output                ║
║  Main.class │ PatientQueue.class │ Patient.class │ PriorityUtils.class ║
╚══════════════════════════════════════════════════════════════╝
                    │  java -cp bin/ Main
                    ▼
╔══════════════════════════════════════════════════════════════╗
║              [ Terminal / Console ]                          ║
║         Menu-driven CLI – User interacts at runtime          ║
╚══════════════════════════════════════════════════════════════╝
```

---

## 3. UML Class Diagram

```mermaid
classDiagram
    direction TB

    note "📁 src/   → FWD/DSA/src/*.java  (source)\n📁 bin/   → FWD/DSA/bin/*.class (compiled)\n📁 lib/   → java.util.* (standard library)"

    %% ── lib layer ─────────────────────────────
    class ArrayList {
        <<lib · java.util>>
    }
    class Scanner {
        <<lib · java.util>>
    }

    %% ── src: Entity ───────────────────────────
    class Patient {
        <<src · Entity>>
        - String id
        - String name
        - int age
        - String symptoms
        - String priorityLevel
        - int priorityScore
        - long arrivalSequence
        + getId() String
        + getName() String
        + getAge() int
        + getSymptoms() String
        + getPriorityLevel() String
        + getPriorityScore() int
        + getArrivalSequence() long
    }

    %% ── src: ADT ──────────────────────────────
    class PatientQueue {
        <<src · ADT Priority Queue>>
        - List~Patient~ queue
        + enqueue(p : Patient) void
        + dequeue() Patient
        + getAllPatients() List~Patient~
        + searchById(id : String) Patient
        + deleteById(id : String) boolean
        + isEmpty() boolean
    }

    %% ── src: Utility ──────────────────────────
    class PriorityUtils {
        <<src · Utility>>
        + getPriorityScore(level : String)$ int
        + sortPatients(patients : List~Patient~)$ void
    }

    %% ── src: Controller ───────────────────────
    class Main {
        <<src · Controller Entry Point>>
        - PatientQueue queue$
        - long sequenceCounter$
        + main(args : String[])$ void
        - registerPatient(scanner : Scanner)$ void
        - displayPatients()$ void
        - servePatient()$ void
        - searchPatientById(scanner : Scanner)$ void
        - deletePatient(scanner : Scanner)$ void
    }

    %% ── Relationships ─────────────────────────
    Main --> PatientQueue : uses
    Main ..> Patient : creates
    Main ..> PriorityUtils : uses getPriorityScore
    Main ..> Scanner : uses (lib)

    PatientQueue "1" *-- "0..*" Patient : contains
    PatientQueue ..> PriorityUtils : uses sortPatients
    PatientQueue ..> ArrayList : backed by (lib)
```

---

## 4. Build & Run Commands

```bash
# Compile src → bin
javac -d bin/ src/*.java

# Run from bin
java -cp bin/ Main
```

---

## 5. DSA Concepts Mapped

| Layer | DSA Concept | Class / Method |
|-------|------------|----------------|
| src   | **Bubble Sort** | `PriorityUtils.sortPatients()` |
| src   | **Linear Search** | `PatientQueue.searchById()` |
| src   | **Priority Queue (ADT)** | `PatientQueue` – enqueue / dequeue |
| src   | **ArrayList (dynamic array)** | `PatientQueue.queue` |
| src   | **Encapsulation (OOP)** | `Patient` – all fields private |
| lib   | **Java Collections** | `java.util.List`, `java.util.ArrayList` |
| lib   | **I/O** | `java.util.Scanner` |
| bin   | **Bytecode** | Compiled `.class` files |

---

## 6. CO Attainment

| CO | Description | Files (src/) |
|----|-------------|-------------|
| **CO1** | Algorithm Analysis, Searching & Sorting | `PriorityUtils`, `PatientQueue`, `Main` |
| **CO2** | Abstract Data Types (ADTs) | `PatientQueue`, `Main` |
| **CO3** | Stack / Queue Concepts | `PatientQueue`, `Main` |
| **CO4** | Java Collections (lib/java.util) | `PatientQueue` |
| CO5 | Not attained | — |
| CO6 | Not attained | — |

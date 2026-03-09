// CO3 – JavaScript Object used for mapping priority values
// Topic: Object and variable declaration
const priorityValue = {
    Critical: 1,
    High: 2,
    Moderate: 3,
    Mild: 4,
    Low: 5
};


// CO3 – Array used to store patient records
// CO5 – LocalStorage used to persist data
let patients = JSON.parse(localStorage.getItem('hospitalPatients')) || [];


// CO5 – Function to store data in browser storage
function savePatients() {
    localStorage.setItem('hospitalPatients', JSON.stringify(patients));
}


// -----------------------------------------
// Page: register.html Logic
// -----------------------------------------

// CO4 – DOM event listener used to detect page load
document.addEventListener('DOMContentLoaded', () => {

    // CO4 – Access HTML element using DOM
    const registerForm = document.getElementById("patientForm");

    if (registerForm) {

        // CO4 – Event handling for form submission
        registerForm.addEventListener("submit", function (e) {

            e.preventDefault();

            // CO3 – JavaScript object creation
            const patient = {
                id: document.getElementById("patientId").value,
                name: document.getElementById("name").value,
                age: document.getElementById("age").value,
                symptoms: document.getElementById("symptoms").value,
                priority: document.getElementById("priority").value
            };

            // CO3 – Array insertion operation
            patients.push(patient);

            savePatients();

            alert("Patient Registered Successfully!");

            registerForm.reset();

            // CO5 – Page navigation to another UI page
            window.location.href = "patients.html";
        });
    }

    // -----------------------------------------
    // Page: patients.html Logic
    // -----------------------------------------

    // CO4 – DOM selection using querySelector
    const tableBody = document.querySelector("#patientTable tbody");

    if (tableBody) {
        displayPatients();
    }

    // -----------------------------------------
    // Page: serve.html Logic
    // -----------------------------------------

    // CO4 – DOM selection for button interaction
    const serveBtn = document.getElementById("serveBtn");

    if (serveBtn) {

        // CO5 – Dynamic UI update
        updateQueueInfo();
    }
});


// CO3 – Helper function returning dynamic CSS class
function getPriorityClass(priority) {
    return 'priority-' + priority.toLowerCase();
}


// CO4 – DOM manipulation to update table content
function displayPatients() {

    const tableBody = document.querySelector("#patientTable tbody");

    if (!tableBody) return;

    tableBody.innerHTML = "";

    // CO3 – Sorting algorithm using array sort()
    let sortedPatients = [...patients].sort((a, b) =>
        priorityValue[a.priority] - priorityValue[b.priority]
    );

    if (sortedPatients.length === 0) {

        // CO4 – DOM content update
        tableBody.innerHTML =
            `<tr><td colspan="5" style="text-align:center;">No patients currently in the queue.</td></tr>`;

        return;
    }

    // CO3 – Array iteration using forEach
    sortedPatients.forEach(p => {

        const row = `
            <tr>
                <td>${p.id}</td>
                <td><strong>${p.name}</strong></td>
                <td>${p.age}</td>
                <td>${p.symptoms}</td>
                <td><span class="priority-badge ${getPriorityClass(p.priority)}">${p.priority}</span></td>
            </tr>
        `;

        // CO4 – DOM dynamic content insertion
        tableBody.innerHTML += row;

    });
}


// CO4 – DOM update to display queue size
function updateQueueInfo() {

    const queueCount = document.getElementById('queueCount');

    if (queueCount) {

        // CO4 – Updating UI text content
        queueCount.textContent = patients.length;

    }
}


// CO4 – Event-driven function triggered by button click
function serveNextPatient() {

    if (patients.length === 0) {
        alert("No patients available in the queue.");
        return;
    }

    // CO3 – Sorting array based on priority
    patients.sort((a, b) => priorityValue[a.priority] - priorityValue[b.priority]);

    // CO3 – Array deletion operation using shift()
    let served = patients.shift();

    // CO5 – Persist updated data
    savePatients();

    updateQueueInfo();

    // CO4 – DOM elements for displaying served patient
    const infoDiv = document.getElementById("servedInfo");
    const detailsDiv = document.getElementById("servedDetails");

    if (infoDiv && detailsDiv) {

        // CO4 – Dynamic UI update
        infoDiv.style.display = "block";

        detailsDiv.innerHTML = `
            <p><strong>ID:</strong> ${served.id}</p>
            <p><strong>Name:</strong> ${served.name}</p>
            <p><strong>Age:</strong> ${served.age}</p>
            <p><strong>Symptoms:</strong> ${served.symptoms}</p>
            <p><strong>Priority:</strong> <span class="priority-badge ${getPriorityClass(served.priority)}">${served.priority}</span></p>
        `;

        // CO4 – UI animation using JavaScript timing
        infoDiv.style.opacity = 0;

        setTimeout(() => infoDiv.style.opacity = 1, 100);

    } else {

        alert("Serving Patient: " + served.name + " (" + served.priority + ")");

    }
}


/*
---------------------------------
FWD CO ATTAINMENT SUMMARY
---------------------------------

Attained COs:
CO3 – JavaScript programming fundamentals
CO4 – DOM manipulation and event handling
CO5 – Advanced web development concepts

Not Attained COs:
CO1 – HTML concepts implemented in HTML files
CO2 – HTML forms and layout implemented in HTML files

---------------------------------
*/
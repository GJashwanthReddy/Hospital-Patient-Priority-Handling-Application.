// Priority mapping for sorting logic
const priorityValue = {
    Critical: 1,
    High: 2,
    Moderate: 3,
    Mild: 4,
    Low: 5
};

// Initialize patients array from localStorage or empty array if none exists
let patients = JSON.parse(localStorage.getItem('hospitalPatients')) || [];

// Function to save to localStorage
function savePatients() {
    localStorage.setItem('hospitalPatients', JSON.stringify(patients));
}

// -----------------------------------------
// Page: register.html Logic
// -----------------------------------------
document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById("patientForm");

    if (registerForm) {
        registerForm.addEventListener("submit", function (e) {
            e.preventDefault();

            const patient = {
                id: document.getElementById("patientId").value,
                name: document.getElementById("name").value,
                age: document.getElementById("age").value,
                symptoms: document.getElementById("symptoms").value,
                priority: document.getElementById("priority").value
            };

            patients.push(patient);
            savePatients();

            alert("Patient Registered Successfully!");
            registerForm.reset();

            // Optional: Redirect to patients list
            window.location.href = "patients.html";
        });
    }

    // -----------------------------------------
    // Page: patients.html Logic
    // -----------------------------------------
    const tableBody = document.querySelector("#patientTable tbody");

    if (tableBody) {
        displayPatients();
    }

    // -----------------------------------------
    // Page: serve.html Logic
    // -----------------------------------------
    const serveBtn = document.getElementById("serveBtn");

    if (serveBtn) {
        // Display queue size on load
        updateQueueInfo();
    }
});

// Helper function to get styled badge class
function getPriorityClass(priority) {
    return 'priority-' + priority.toLowerCase();
}

function displayPatients() {
    const tableBody = document.querySelector("#patientTable tbody");
    if (!tableBody) return;

    tableBody.innerHTML = "";

    // Sort patients based on priority values
    let sortedPatients = [...patients].sort((a, b) =>
        priorityValue[a.priority] - priorityValue[b.priority]
    );

    if (sortedPatients.length === 0) {
        tableBody.innerHTML = `<tr><td colspan="5" style="text-align:center;">No patients currently in the queue.</td></tr>`;
        return;
    }

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
        tableBody.innerHTML += row;
    });
}

function updateQueueInfo() {
    const queueCount = document.getElementById('queueCount');
    if (queueCount) {
        queueCount.textContent = patients.length;
    }
}

function serveNextPatient() {
    if (patients.length === 0) {
        alert("No patients available in the queue.");
        return;
    }

    // Sort to ensure highest priority is at the front
    patients.sort((a, b) => priorityValue[a.priority] - priorityValue[b.priority]);

    // Remove the highest priority patient from the array
    let served = patients.shift();

    // Save updated array
    savePatients();
    updateQueueInfo();

    // Display served patient info
    const infoDiv = document.getElementById("servedInfo");
    const detailsDiv = document.getElementById("servedDetails");

    if (infoDiv && detailsDiv) {
        infoDiv.style.display = "block";
        detailsDiv.innerHTML = `
            <p><strong>ID:</strong> ${served.id}</p>
            <p><strong>Name:</strong> ${served.name}</p>
            <p><strong>Age:</strong> ${served.age}</p>
            <p><strong>Symptoms:</strong> ${served.symptoms}</p>
            <p><strong>Priority:</strong> <span class="priority-badge ${getPriorityClass(served.priority)}">${served.priority}</span></p>
        `;

        // Add a small animation effect
        infoDiv.style.opacity = 0;
        setTimeout(() => infoDiv.style.opacity = 1, 100);
    } else {
        alert("Serving Patient: " + served.name + " (" + served.priority + ")");
    }
}

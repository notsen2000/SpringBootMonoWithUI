// Example data
const records = [
    { id: 1, name: "John Doe", email: "john@example.com" },
    { id: 2, name: "Jane Smith", email: "jane@example.com" },
      { id: 3, name: "John Doe", email: "john@example.com" },
        { id: 4, name: "Jane Smith", email: "jane@example.com" },
          { id: 5, name: "John Doe", email: "john@example.com" },
            { id: 6, name: "Jane Smith", email: "jane@example.com" }

    // Add more records as needed
];

// Pagination constants
const pageSize = 5; // Number of records per page
let currentPage = 1;

// Populate table with data for the current page
function populateTable() {
    const tableBody = document.getElementById('tableBody');
    tableBody.innerHTML = '';

    const startIndex = (currentPage - 1) * pageSize;
    const endIndex = Math.min(startIndex + pageSize, records.length);

    for (let i = startIndex; i < endIndex; i++) {
        const record = records[i];
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${record.id}</td>
            <td>${record.name}</td>
            <td>${record.email}</td>
            <td>
                <button onclick="editRow(${record.id})">Edit</button>
                <button onclick="deleteRow(${record.id})">Delete</button>
            </td>
        `;
        tableBody.appendChild(row);
    }

    renderPagination();
}

// Render pagination controls
function renderPagination() {
    const totalPages = Math.ceil(records.length / pageSize);
    const pagination = document.getElementById('pagination');
    pagination.innerHTML = '';

    for (let i = 1; i <= totalPages; i++) {
        const button = document.createElement('button');
        button.textContent = i;
        button.addEventListener('click', () => {
            currentPage = i;
            populateTable();
        });
        pagination.appendChild(button);
    }
}

// Handle row edit
// Handle row edit
function editRow(id) {
    const row = document.querySelector(`tr[data-id="${id}"]`);
    //const cells = row.querySelectorAll('td:not(:last-child)'); // Exclude the last cell with edit/delete buttons

    // Specify the row number you want to select
    const rowNumber = 2; // Example row number

    // Select the row and then select all cells within that row
    const cellsInRow = document.querySelectorAll(`#dataTable tbody tr:nth-child(${rowNumber}) td`);


    cellsInRow.forEach(cell => {
        const text = cell.textContent;
        cell.innerHTML = `<input type="text" value="${text}">`;
    });
    // Change the button to "Save"
    const editButton = row.querySelector('button:first-of-type');
    editButton.textContent = 'Save';
    editButton.setAttribute('onclick', `saveChanges(${id})`);
}

// Save changes from the row
function saveChanges(id) {
    const row = document.querySelector(`tr[data-id="${id}"]`);
    const cells = row.querySelectorAll('td:not(:last-child)');
    cells.forEach(cell => {
        const input = cell.querySelector('input');
        cell.textContent = input.value;
    });
    // Change the button back to "Edit"
   /* const editButton = row.querySelector('button:first-of-type');
    editButton.textContent = 'Edit';
    editButton.setAttribute('onclick', `editRow(${id})`);*/
}


// Handle row deletion
function deleteRow(id) {
    // Find index of record with given id
    const index = records.findIndex(record => record.id === id);
    // Remove record from records array
    if (index !== -1) {
        records.splice(index, 1);
        // Repopulate table
        populateTable();
    }
}

// Initialize table and pagination
populateTable();

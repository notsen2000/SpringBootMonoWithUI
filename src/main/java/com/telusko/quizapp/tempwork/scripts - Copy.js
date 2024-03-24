const url = 'http://localhost:8080/question/allQuestions';

async function fetchEmployees() {
    const response = await fetch(url);
    const employees = await response.json();
    console.log(employees)
    displayEmployees(employees);
}

function displayEmployees(employees) {
    const container = document.getElementById('employees');
    container.innerHTML = '';
    employees.forEach(employee => {
        const div = document.createElement('div');
        div.classList.add('employee');
        div.innerHTML = `
            <p><strong>Question:</strong> ${employee.question_title}</p>
            <p><strong>Category:</strong> ${employee.category}</p>
            <p><strong>Difficulty Level:</strong> ${employee.difficultylevel}</p>
            <p><strong>Option1:</strong> ${employee.option1}</p>
            <p><strong>Option2:</strong> ${employee.option2}</p>
            <p><strong>Option3:</strong> ${employee.option3}</p>
            <p><strong>Option4:</strong> ${employee.option4}</p>
            <p><strong>Right Answer:</strong> ${employee.right_answer}</p>
            <button onclick="editEmployee(${employee.id})">Edit</button>
            <button onclick="deleteEmployee(${employee.id})">Delete</button>
        `;
        container.appendChild(div);
    });
}

async function addEmployee() {
    const name = prompt('Enter employee name:');
    const position = prompt('Enter employee position:');
    const response = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ category, position })
    });
    fetchEmployees();
}

async function editEmployee(id) {
    const question_title = prompt('Enter new question_title:');
    const category = prompt('Enter new category:');
    const question = document.getElementById('employees');
    var urlForEdit = 'http://localhost:8080/question/update';
    //const response = await fetch(`${url}/${id}`, {
    const response = await fetch(urlForEdit,{
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ category: category, question_title: question_title,id : id })
    });
    fetchEmployees();
}

async function deleteEmployee(id) {
    const response = await fetch(`${url}/${id}`, { method: 'DELETE' });
    fetchEmployees();
}

fetchEmployees();

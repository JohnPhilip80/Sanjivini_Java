
import './style.css';

// ======================================
// 1. TASK INTERFACE
// ======================================

interface Task {
    id: number;
    title: string;
    completed: boolean;
}

// ======================================
// 2. FILTER TYPE
// ======================================

type FilterType = 'all' | 'pending' | 'completed';

// ======================================
// 3. GET HTML ELEMENTS
// ======================================

const taskInput = document.getElementById(
    'taskInput'
) as HTMLInputElement;

const addTaskBtn = document.getElementById(
    'addTaskBtn'
) as HTMLButtonElement;

const searchInput = document.getElementById(
    'searchInput'
) as HTMLInputElement;

const taskList = document.getElementById(
    'taskList'
) as HTMLUListElement;

const taskCount = document.getElementById(
    'taskCount'
) as HTMLParagraphElement;

const pendingCount = document.getElementById(
    'pendingCount'
) as HTMLParagraphElement;

const completedCount = document.getElementById(
    'completedCount'
) as HTMLParagraphElement;

const emptyMessage = document.getElementById(
    'emptyMessage'
) as HTMLParagraphElement;

const filterButtons = document.querySelectorAll(
    '.filter-btn'
) as NodeListOf<HTMLButtonElement>;

// ======================================
// 4. INITIALIZE TASK ARRAY
// ======================================

let tasks: Task[] = [];

try {
    const savedTasks = localStorage.getItem('tasks');

    if (savedTasks) {
        const parsed: unknown = JSON.parse(savedTasks);

        if (Array.isArray(parsed)) {
            tasks = parsed.filter((task: unknown): task is Task => {
                if (typeof task !== 'object' || task === null) {
                    return false;
                }

                const item = task as Record<string, unknown>;

                return (
                    typeof item.id === 'number' &&
                    typeof item.title === 'string' &&
                    typeof item.completed === 'boolean'
                );
            });
        }
    }
} catch {
    console.error('Unable to load saved tasks.');
}

// ======================================
// 5. CURRENT FILTER
// ======================================

let currentFilter: FilterType = 'all';

// ======================================
// 6. SAVE TASKS IN LOCAL STORAGE
// ======================================

function saveTasks(): void {

    localStorage.setItem(
        'tasks',
        JSON.stringify(tasks)
    );

}

// ======================================
// 7. ADD NEW TASK
// ======================================

function addTask(): void {

    const title = taskInput.value.trim();

    if (title === '') {

        alert('Please enter a task!');

        return;
    }

    const newTask: Task = {

        id: Date.now(),

        title: title,

        completed: false

    };

    tasks.push(newTask);

    saveTasks();

    taskInput.value = '';

    displayTasks();

}

// ======================================
// 8. DISPLAY TASKS
// ======================================

function displayTasks(): void {

    taskList.innerHTML = '';

    // Apply search and filter

    const filteredTasks = tasks.filter((task: Task) => {

        const matchesSearch = task.title
            .toLowerCase()
            .includes(searchInput.value.toLowerCase().trim());

        const matchesFilter =
            currentFilter === 'all' ||
            (currentFilter === 'pending' && !task.completed) ||
            (currentFilter === 'completed' && task.completed);

        return matchesSearch && matchesFilter;

    });

    // Display each task

    filteredTasks.forEach((task: Task) => {

        const li = document.createElement('li');

        li.className = 'task-item';

        if (task.completed) {
            li.classList.add('completed');
        }

        // Left section

        const leftDiv = document.createElement('div');

        leftDiv.className = 'task-left';

        // Checkbox

        const checkbox = document.createElement('input');

        checkbox.type = 'checkbox';

        checkbox.checked = task.completed;

        checkbox.addEventListener('change', () => {

            toggleTask(task.id);

        });

        // Task title

        const span = document.createElement('span');

        span.textContent = task.title;

        leftDiv.appendChild(checkbox);

        leftDiv.appendChild(span);

        // Action buttons

        const actionDiv = document.createElement('div');

        actionDiv.className = 'task-actions';

        // Edit button

        const editBtn = document.createElement('button');

        editBtn.textContent = 'Edit';

        editBtn.className = 'edit-btn';

        editBtn.addEventListener('click', () => {

            editTask(task.id);

        });

        // Delete button

        const deleteBtn = document.createElement('button');

        deleteBtn.textContent = 'Delete';

        deleteBtn.className = 'delete-btn';

        deleteBtn.addEventListener('click', () => {

            deleteTask(task.id);

        });

        actionDiv.appendChild(editBtn);

        actionDiv.appendChild(deleteBtn);

        // Add everything to list item

        li.appendChild(leftDiv);

        li.appendChild(actionDiv);

        taskList.appendChild(li);

    });

    updateStatistics(filteredTasks.length);

}

// ======================================
// 9. UPDATE TASK STATISTICS
// ======================================

function updateStatistics(visibleCount: number): void {

    taskCount.textContent = `Total: ${tasks.length}`;

    pendingCount.textContent =
        `Pending: ${tasks.filter(task => !task.completed).length}`;

    completedCount.textContent =
        `Completed: ${tasks.filter(task => task.completed).length}`;

    emptyMessage.style.display =
        visibleCount === 0 ? 'block' : 'none';

}

// ======================================
// 10. COMPLETE / UNCOMPLETE TASK
// ======================================

function toggleTask(id: number): void {

    tasks = tasks.map((task: Task) => {

        if (task.id === id) {

            return {

                ...task,

                completed: !task.completed

            };

        }

        return task;

    });

    saveTasks();

    displayTasks();

}

// ======================================
// 11. EDIT TASK
// ======================================

function editTask(id: number): void {

    const task = tasks.find(task => task.id === id);

    if (!task) {
        return;
    }

    const updatedTitle = prompt(
        'Enter updated task:',
        task.title
    );

    if (updatedTitle === null) {
        return;
    }

    if (updatedTitle.trim() === '') {

        alert('Task cannot be empty!');

        return;
    }

    task.title = updatedTitle.trim();

    saveTasks();

    displayTasks();

}

// ======================================
// 12. DELETE TASK
// ======================================

function deleteTask(id: number): void {

    const confirmDelete = confirm(
        'Are you sure you want to delete this task?'
    );

    if (!confirmDelete) {
        return;
    }

    tasks = tasks.filter((task: Task) => {

        return task.id !== id;

    });

    saveTasks();

    displayTasks();

}

// ======================================
// 13. FILTER TASKS
// ======================================

filterButtons.forEach((button: HTMLButtonElement) => {

    button.addEventListener('click', () => {

        currentFilter = button.dataset.filter as FilterType;

        // Remove active class from all buttons

        filterButtons.forEach(btn => {

            btn.classList.remove('active');

        });

        // Add active class to selected button

        button.classList.add('active');

        displayTasks();

    });

});

// ======================================
// 14. ADD TASK BUTTON EVENT
// ======================================

addTaskBtn.addEventListener('click', () => {

    addTask();

});

// ======================================
// 15. ENTER KEY EVENT
// ======================================

taskInput.addEventListener('keydown', (event: KeyboardEvent) => {

    if (event.key === 'Enter') {

        addTask();

    }

});

// ======================================
// 16. SEARCH TASKS
// ======================================

searchInput.addEventListener('input', () => {

    displayTasks();

});

// ======================================
// 17. INITIAL DISPLAY
// ======================================

displayTasks();

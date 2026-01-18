import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static final String FILE_NAME = "tasks.txt";
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasks();
    }

    // Load tasks from file
    private void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    tasks.add(Task.fromFileString(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks file.");
        }
    }

    // Save tasks to file
    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    // Add a new task
    public void addTask(String description) {
        int id = tasks.size() + 1;
        Task task = new Task(id, description, "PENDING");
        tasks.add(task);
        saveTasks();
        System.out.println("Task added successfully.");
    }

    // List all tasks
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // Mark task as completed
    public void completeTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markDone();
                saveTasks();
                System.out.println("Task marked as completed.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    // Delete a task
    public void deleteTask(int id) {
        Task toRemove = null;

        for (Task task : tasks) {
            if (task.getId() == id) {
                toRemove = task;
                break;
            }
        }

        if (toRemove != null) {
            tasks.remove(toRemove);
            reassignIds();
            saveTasks();
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    // Reassign IDs after deletion
    private void reassignIds() {
        int id = 1;
        for (Task task : tasks) {
            task = new Task(id++, task.getDescription(), task.getStatus());
        }
    }
}

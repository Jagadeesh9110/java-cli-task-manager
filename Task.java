public class Task {

    private int id;
    private String description;
    private TaskStatus status;
    // PENDING or DONE

    public Task(int id, String description, TaskStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void markDone() {
        this.status = TaskStatus.COMPLETED;
    }

    @Override
    public String toString() {
        return id + ". " + description + " [" + status + "]";
    }

    // Convert task to file format
    public String toFileString() {
        return id + "|" + description + "|" + status;
    }

    // Create task object from file line
    public static Task fromFileString(String line) {
        String[] parts = line.split("\\|");
        int id = Integer.parseInt(parts[0]);
        String description = parts[1];
        TaskStatus status = TaskStatus.valueOf(parts[2]);
        return new Task(id, description, status);
    }
}

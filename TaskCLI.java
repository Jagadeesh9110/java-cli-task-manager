public class TaskCLI {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        int n = args.length;
        if (n == 0) {
            printUsage();
            return;
        }
        String command = args[0];
        switch (command) {
            case "add":
                if (n < 2) {
                    System.out.println("Usage: java TaskCLI add <description>");
                    return;
                }
                StringBuilder description = new StringBuilder();
                for (int i = 1; i < n; i++) {
                    description.append(args[i]).append(" ");
                }
                manager.addTask(description.toString().trim());
                break;
            case "list":
                manager.listTasks();
                break;
            case "complete":
                if (n < 2) {
                    System.out.println("Usage: java TaskCLI complete <id>");
                    return;
                }
                try {
                    int id = Integer.parseInt(args[1]);
                    manager.completeTask(id);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID.");
                }
                break;
            case "delete":
                if (n < 2) {
                    System.out.println("Usage: java TaskCLI delete <id>");
                    return;
                }
                try {
                    int id = Integer.parseInt(args[1]);
                    manager.deleteTask(id);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID.");
                }
                break;
            default:
                printUsage();
                System.out.println("Unknown command: " + command);
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java TaskCLI <command> [arguments]");
        System.out.println("Commands:");
        System.out.println("  add <description>   - Add a new task");
        System.out.println("  list                - List all tasks");
        System.out.println("  complete <id>       - Mark a task as completed");
        System.out.println("  delete <id>         - Delete a task");
    }

}
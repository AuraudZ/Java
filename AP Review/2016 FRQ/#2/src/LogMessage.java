import java.util.ArrayList;

public class LogMessage {
    private String machineId;
    private String description;

    public LogMessage(String message) {
        // Split the message based on a colon
        String[] split = message.split(":");
        machineId = split[0];
        description = split[1];
    }

    public String getMachineId() {
        return machineId;
    }

    public String getDescription() {
        return description;
    }

    public boolean contains(String word) {
        String s = description.trim();
        ArrayList<String> words = new ArrayList<String>();
        int i;
        while ((i = s.indexOf(" ")) >= 0) {
            words.add(word);
            s = s.substring(i + 1);
        }
        if (s.length() > 0) {
            words.add(s);
        }
        for (String w : words) {
            if (word.compareTo(w) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Create a log message
        LogMessage logMessage = new LogMessage("machine:hello");
        // Print the machine id
        System.out.println(logMessage.getMachineId());
        // Print the description
        System.out.println(logMessage.getDescription());
    }
}

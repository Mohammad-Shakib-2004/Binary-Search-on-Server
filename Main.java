import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // Method to perform binary search
    public static int binarySearch(String[] data, String target) {
        int left = 0;
        int right = data.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = data[mid].compareToIgnoreCase(target);

            // Check if the target is present at mid
            if (result == 0) {
                return mid;  // Target found
            }

            // If target greater, ignore left half
            if (result < 0) {
                left = mid + 1;
            } else {
                // If target is smaller, ignore right half
                right = mid - 1;
            }
        }
        return -1; // Target not found
    }

    // Method to suggest related results
    public static void suggestRelatedResults(String[] data, String target) {
        System.out.println("Related results:");
        for (String item : data) {
            if (item.toLowerCase().contains(target.toLowerCase())) {
                System.out.println(item);
            }
        }
    }

    public static void main(String[] args) {
        // Sample data on server
        String[] serverData = {"Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grape", "Honeydew"};

        // Sort the data for binary search
        Arrays.sort(serverData);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word or number to search: ");
        String userInput = scanner.nextLine();

        // Perform binary search
        int index = binarySearch(serverData, userInput);

        if (index != -1) {
            System.out.println("Found \"" + userInput + "\" in the server at index " + index);
        } else {
            System.out.println("\"" + userInput + "\" not found in the server.");
            suggestRelatedResults(serverData, userInput);
        }

        scanner.close();
    }
}

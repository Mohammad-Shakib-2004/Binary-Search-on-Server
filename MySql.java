import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MySql {

    // Database connection details
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/SearchDB";
    static final String JDBC_USER = "root";
    static final String JDBC_PASS = "password";

    // Method to fetch data from the database
    public static String[] fetchDataFromDatabase() {
        ArrayList<String> dataList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT data_item FROM server_data")) {

            while (rs.next()) {
                dataList.add(rs.getString("data_item"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return dataList.toArray(new String[0]);
    }

    // Method to perform binary search
    public static int binarySearch(String[] data, String target) {
        int left = 0;
        int right = data.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = data[mid].compareToIgnoreCase(target);

            if (result == 0) {
                return mid;  // Target found
            }
            if (result < 0) {
                left = mid + 1;
            } else {
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
        // Fetch data from the database
        String[] serverData = fetchDataFromDatabase();

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

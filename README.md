Need a server to work properly 
Database Connection Setup: JDBC_URL, JDBC_USER, and JDBC_PASS specify the MySQL connection details.

fetchDataFromDatabase: This method connects to the MySQL database, retrieves data_item entries from server_data, and returns them as an array for binary search.

Main Update: The main method now calls fetchDataFromDatabase instead of using hard-coded data, making it more flexible for real applications.

Project Name : Campus Classifieds

Team members:
Kavya Gautam
Ramya Bangaluru Gopalakrishna
Sambhavi Raghuraman


Important confessions:
Connects to Oracle DB local to my virtual machine
Images and Files are given as absolute path



How to Run:
In eclipse -> Please load the project into eclipse, Go to DataProcessing.java class
and change the database login details in this line to the database that the grader has to connect to=>

connection1 = DriverManager.getConnection(
"jdbc:oracle:thin:@//localhost:1521/orcl", "hr", "hr");


.Sql code:
The table creation codes for 4 tables is provided in the .sql file
so Need to create the tables before running the application

APIS:
Uses Email sender API and calendar API


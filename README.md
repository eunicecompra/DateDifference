# Date Difference

This is a Java implementation of the Date Difference exercise. Implementation does not use SDK-provided APIs in manipulating the dates.

The project is done using IntelliJ and the full project structure is checked in, so it is readily deployable in your local environment.

# Running the Application
To run the application, execute the main class, <b>com.app.DateDifferenceRunner</b>. This takes the input dates from the console in the following format: DD MM YYYY, DD MM YYY

Output will be: DD MM YYYY, DD MM YYYY, No. of Days Difference

Sample I/O <br>
Input> 08 01 1995, 24 12 2005 <br>
Output> 08 01 1995, 24 12 2005, 4003 <br>

Input> 15 04 1969, 12 09 1945<br>
Output> 12 09 1945, 15 04 1969, 8616<br>

# Testing the Application   
A number of test cases is created in <b>com.app.test.DateDifferenceTest</b> and it can be run using JUnit. 

Expected Results are taken from https://www.timeanddate.com/date/durationresult.html

# Getting Started

### Installation
##### Maven
This project is a Maven Project.
Please install Maven and configure your pc environment variable
* [Maven Official Website](https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip)
* [Installation Guide](https://mkyong.com/maven/how-to-install-maven-in-windows/)

Use ```mvn -version``` to verify installation success

##### Java JDK1.8
This project is using Java 8. Please install Java and configure your pc environment variable.
Use ```java -version``` to verify installation success

##### Node.js
This project uses NPM. Please install node.js and configure your pc environment variable.
Use ```npm -v``` to verify installation success

### Execute the program
#### Start the backend server
Run the executable java backend in
```
src > main > java > com.assignment.concurrent > ConcurrentApplication.java
```
First time running, to install all maven dependency
```
mvn install
```
To test run
```
mvn package
java -jar "file path of executable"
```
#### Start the React-app
Run the frontend of the application by
1. open the cmd for the project
2. enter ```cd React-app``` to navigate to the front end folder
3. enter ```npm start``` to run the application
4. open ```http://localhost:3000``` with your browser

#### Play with the Demo
1. click ```start```
2. enter the required parameters
3. observe changes in output


### Reference Documentation
To understand Spring & STOMP, it's encourage to checkout 
* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)

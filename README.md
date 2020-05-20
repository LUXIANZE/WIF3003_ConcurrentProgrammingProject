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
Navigate into Server folder root folder
```
cd ./Server
```
First time running, to install all maven dependency
```
mvn install
```
Take the compiled code and package it into a distributable format such as JAR
```
mvn package
```
Run the executable java backend in
```
src > main > java > com.assignment.concurrent > ConcurrentApplication.java
java -jar "file path of executable"
```

#### Start the React-app
Navigate into React-app from root folder
```
cd ./React-app
```
First time running, install all node dependency
```
npm install
```
Start the application
```
npm start
```
open ```http://localhost:3000``` with your browser

#### Play with the Demo
1. click ```start```
2. enter the required parameters
3. observe changes in output


### Reference Documentation
To understand Spring & STOMP, it's encourage to checkout 
* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)

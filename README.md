## Running the project locally

### Installation

Clone this repository:

```sh
$ git clone git@github.com:RehabSayedAbdelghany/Lendico-Payment-Generator.git
```

Build  using the below command to generate a war in case of in need to deploy to server

```sh
$ cd <your_git_home>/Lendico-Payment-Generator
$ mvn clean install
```

### How to Build or run the application

Start the Spring Boot application directly (port number 7070 used in case of need to change you can change in the property file)

```sh
$ cd <your_git_home>/Lendico-Payment-Generator
$ mvn clean spring-boot:run
```

### how to post Request

Using your favourite HTTP client, run a POST request to the URL:

```
http://localhost:7070/plan-generator/monthlyRepayment
```

Sample Requests using postman added to the below link
https://www.getpostman.com/collections/934b7ccbd69970a5e8ca

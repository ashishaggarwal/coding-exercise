<h1 align="center">
    Address Book
</h1>
<p align="center">
<a href="#introduction">Introduction</a> &nbsp;&bull;&nbsp;
<a href="#prerequisites">Prerequisites</a> &nbsp;&bull;&nbsp;
<a href="#building">Building</a> &nbsp;&bull;&nbsp;
<a href="#running">Running</a> &nbsp;&bull;&nbsp;
<a href="#assumptions">Assumptions</a> &nbsp;&bull;&nbsp;
</p>

# Introduction
This is a simple application to read the file (sample below) add answer following questions:

* How many males are in the file?
* Who is the oldest person in the file?
* How many days older is Bill than Paul?

Sample file contents:

Bill McKnight, Male, 16/03/77 <br />
Paul Robinson, Male, 15/01/85 <br />
Gemma Lane, Female, 20/11/91 <br />
Sarah Stone, Female, 20/09/80 <br />
Wes Jackson, Male, 14/08/74 <br />

# Prerequisites

The project would need JDK 22 and Maven to build/run


# Building

Run following command to build the application

`mvn clean install
`
# Running

The application can be run using following command from the root of the project:

`mvn spring-boot:run`

This command will use `input.csv` file located under `src/main/resources` folder

The application can be run with a different input file by running following command:

`mvn spring-boot:run -Dspring-boot.run.arguments=--addressbook.filename=<your-file-name>
`

Replace `<your-file-name>` in above command with the file you want to use.
Also make sure that file exists under `src/main/resources` folder in the project

The application can also be run from Intellij by running the main java class 

`AddressBookMain`

# **Assumptions**

* The order in file will be Name, Gender, Date of Birth
* Date of Birth will be in the format dd/MM/yy
* It is assumed that date of birth will be supplied between 1924-2024 e.g. 01/01/10 will be considered 1 January 2010 but 01/01/25 will be considered 1 January 1925
* There should be exactly 3 comma separated values in the file else an exception will be thrown
* You can enter blank values in the file. They will be treated as `null` as we don't want to break the whole application if one value is null
  e.g. if Gender is not provided other methods like getting the oldest person and calculating
  age difference between two people will still work



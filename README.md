Java Job Aggregator

A Java application that scrapes Java developer job listings from dou.ua and work.ua and displays them in an HTML file.

Features

Search jobs by city

Displays results in vacancies.html

Simple MVC + Strategy architecture

Includes a backup HTML template (backup.html)

How to Run

Compile the project:

javac -d out src/**/*.java


Run the app:

java -cp out Aggregator


Open vacancies.html in your browser to see the results.

Dependencies

JSoup
– for HTML parsing
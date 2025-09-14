# Java Job Aggregator

A Java application that scrapes Java developer job listings from **dou.ua** and **work.ua** and displays them in an HTML file.

---

## Features

- Search jobs by city
- Displays results in **vacancies.html**
- Simple MVC + Strategy architecture
- Includes a backup HTML template (**backup.html**)

---
Dependencies

JSoup – for HTML parsing
You can download JSoup from https://jsoup.org/download
and add it to your classpath.

Open the generated vacancies.html file in your browser to see the aggregated job listings.

Notes

The application scrapes job data live from external websites, so network access is required.

The HTML output is designed to be simple and easily viewable in any modern browser.

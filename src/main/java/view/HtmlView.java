package view;

import controller.Controller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import vo.Vacancy;


import java.io.*;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./src/main/java/view/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String newContent = getUpdatedFileContent(vacancies);
            updateFile(newContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Kyiv");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        try {
            Document document = getDocument();

            Element template = document.selectFirst("li.l-vacancy.template");
            if (template == null) {
                return document.html();
            }

            Element templateCopy = template.clone();

            templateCopy.removeAttr("style");
            templateCopy.removeClass("template");

            document.select("li.l-vacancy").stream()
                    .filter(e -> !e.hasClass("template"))
                    .forEach(Element::remove);

            Element vacancyList = document.selectFirst("ul.lt");
            if (vacancyList == null) {
                return document.html();
            }

            for (Vacancy vacancy : vacancies) {
                Element vacancyElement = templateCopy.clone();

                Element titleElement = vacancyElement.selectFirst("a.vt");
                if (titleElement != null) {
                    titleElement.text(vacancy.getTitle());
                    titleElement.attr("href", vacancy.getUrl());
                }

                Element companyNameElement = vacancyElement.selectFirst("span.companyName");
                if (companyNameElement != null) {
                    companyNameElement.text(vacancy.getCompanyName());
                }

                Element cityElement = vacancyElement.selectFirst("span.cities");
                if (cityElement != null) {
                    cityElement.text(vacancy.getCity());
                }

                Element salaryElement = vacancyElement.selectFirst("span.salary");
                if (salaryElement != null) {
                    salaryElement.text(vacancy.getSalary() == null ? "" : vacancy.getSalary());
                }

                template.before(vacancyElement);
            }
            return document.html();

        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
    }

    private void updateFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        File input = new File(filePath);
        return Jsoup.parse(input, "UTF-8");
    }
}

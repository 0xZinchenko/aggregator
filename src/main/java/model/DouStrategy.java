package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vo.Vacancy;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DouStrategy implements Strategy {
    private static final String URL_FORMAT = "https://jobs.dou.ua/vacancies/?city=%s&search=Java";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            Document document = getDocument(searchString);
            Elements elementVacancies = document.select("li.l-vacancy");

            for (Element elements : elementVacancies) {

                Vacancy vacancy = new Vacancy();

                Element titleElement = elements.selectFirst("a.vt");
                if (titleElement != null) {
                    vacancy.setTitle(titleElement.text());
                    vacancy.setUrl(titleElement.attr("href"));
                }

                Element cityElement = elements.selectFirst("span.cities");
                if (cityElement != null) {
                    vacancy.setCity(cityElement.text());
                }

                Element companyElement = elements.selectFirst("a.company");
                if (companyElement != null) {
                    vacancy.setCompanyName(companyElement.text().trim());
                }

                Element salaryElement = elements.selectFirst("span.salary");
                vacancy.setSalary(salaryElement != null ? salaryElement.text().trim() : "");

                vacancy.setSiteName("dou.ua");

                vacancies.add(vacancy);

            }
      //      System.out.println("Total vacancies parsed: " + vacancies.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vacancies;
    }

    protected Document getDocument(String searchString) throws IOException {
        String cityParam = searchString == null || searchString.isEmpty() ? "" : searchString;
        String url;
        if (cityParam.isEmpty()) {
            url = "https://jobs.dou.ua/vacancies/?search=Java";
        } else {
            url = String.format("https://jobs.dou.ua/vacancies/?city=%s&search=Java", cityParam);
        }
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36")
                .referrer("https://www.google.com/")
                .get();
        return doc;
    }
}

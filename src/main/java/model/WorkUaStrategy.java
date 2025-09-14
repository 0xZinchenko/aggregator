package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkUaStrategy implements Strategy{

    private static final String URL_FORMAT = "https://www.work.ua/jobs-%s-java/?page=%d";

    @Override
    public List<Vacancy> getVacancies(String city) {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 1;

        try {
            while (true) {
                Document document = getDocument(city, page);
                Elements vacancyCards = document.select("div.card.job-link");

                if (vacancyCards.isEmpty()) break;

                for (Element card : vacancyCards) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setSiteName("work.ua");


                    Element titleElement = card.selectFirst("h2 > a");
                    if (titleElement != null) {
                        vacancy.setTitle(titleElement.text());
                        String relativeUrl = titleElement.attr("href");
                        vacancy.setUrl("https://www.work.ua" + relativeUrl);
                    }


                    Elements strong600Elements = card.select("span.strong-600");
                    String companyName = "";
                    if (strong600Elements.size() >= 2) {
                        companyName = strong600Elements.get(1).text().trim();
                    }
                    vacancy.setCompanyName(companyName);


                    Element cityElement = card.selectFirst("div.mt-xs > span:last-of-type");
                    if (cityElement != null) {
                        vacancy.setCity(cityElement.text().trim());
                    }

                    vacancies.add(vacancy);
                }

                page++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return vacancies;
    }
    private Document getDocument(String city, int page) throws IOException {
        String cityUrl = city.toLowerCase().replace(" ", "-");
        String url = String.format(URL_FORMAT, cityUrl, page);
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36")
                .referrer("https://www.google.com/")
                .get();
    }
}

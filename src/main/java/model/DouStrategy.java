package model;

import vo.Vacancy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DouStrategy implements Strategy {
    private static final String URL_FORMAT = "https://jobs.dou.ua/vacancies/?search=Java+Kiev&city=%s";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        return Collections.emptyList();
    }
}

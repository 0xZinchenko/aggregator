package view;

import controller.Controller;
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

        return "";
    }

    private void updateFile(String content) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}


import controller.Controller;
import model.DouStrategy;
import model.Model;
import model.Provider;
import model.WorkUaStrategy;
import view.HtmlView;

public class Aggregator {
    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        Provider douProvider = new Provider(new DouStrategy());
       Provider workUaProvider = new Provider(new WorkUaStrategy());

        Model model = new Model(view, douProvider, workUaProvider);
        Controller controller = new Controller(model);

        view.setController(controller);
        view.userCitySelectEmulationMethod();

    }
}

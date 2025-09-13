import controller.Controller;
import model.DouStrategy;
import model.Provider;

public class Aggregator {
    public static void main(String[] args) {
        Provider provider = new Provider(new DouStrategy());
        Controller controller = new Controller(provider);
        controller.scan();
    }
}

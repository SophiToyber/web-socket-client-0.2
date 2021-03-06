package appearance.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import appearance.application.configuration.AbstractJavaFxApplicationSupport;
import appearance.application.configuration.ControllersConfiguration;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    @Value("${ui.title}")//
    private String windowTitle;

    @Qualifier("mainView")
    @Autowired	
    private ControllersConfiguration.ViewHolder view;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(false);
        stage.centerOnScreen();	
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

}

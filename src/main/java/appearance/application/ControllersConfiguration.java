package appearance.application;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import appearance.application.ui.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

@Configuration
public class ControllersConfiguration {

	@Bean(name = "mainView")
	public ViewHolder getMainView() throws IOException {
		return loadView("fxml/Start.fxml");
	}

	/**
	* Thanks to this method, we added a controller to the spring context, and
	* forced him to make all the necessary injections.
	*/
	@Bean
	public MainController getMainController() throws IOException {
		return (MainController) getMainView().getController();
	}

	/**
	* The most common way to use the FXML downloader. Just on this
	* the stage will create an object controller, all FXML injections are made and called
	* controller initialization method.
	*/
	protected ViewHolder loadView(String url) throws IOException {
		InputStream fxmlStream = null;
		try {
			fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
			FXMLLoader loader = new FXMLLoader();
			loader.load(fxmlStream);
			return new ViewHolder(loader.getRoot(), loader.getController());
		} finally {
			if (fxmlStream != null) {
				fxmlStream.close();
			}
		}
	}

	/**
	* Class - shell: we must specify the controller as a bean, and view -
	* view, we have to use at the entry point {@link Application}.
	*/
	public class ViewHolder {
		private Parent view;
		private Object controller;

		public ViewHolder(Parent view, Object controller) {
			this.view = view;
			this.controller = controller;
		}

		public Parent getView() {
			return view;
		}

		public void setView(Parent view) {
			this.view = view;
		}

		public Object getController() {
			return controller;
		}

		public void setController(Object controller) {
			this.controller = controller;
		}
	}

}

package xmlabd;


import view.MainWindow;
import model.DataAccessor;

public class Main {

	public static void main(String[] args) {
		new MainWindow(new DataAccessor());
	}

}

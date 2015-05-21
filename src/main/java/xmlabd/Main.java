package xmlabd;


import view.MainWindow;
import model.DataAccessor;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		DataAccessor da = new DataAccessor();
		MainWindow m = new MainWindow(da);
		//da.XQuery3(2008);
	}

}

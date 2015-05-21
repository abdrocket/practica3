package xmlabd;

import java.util.ArrayList;

import model.DataAccessor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataAccessor da = new DataAccessor();
		ArrayList<String[]> l = da.query2(2008);
		for(String[] s : l){
			for(String cad : s){
				System.out.print(cad);
			}
			System.out.println();
		}
		
	}

}

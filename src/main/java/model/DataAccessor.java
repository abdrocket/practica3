package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import org.w3c.dom.Element;

import net.xqj.exist.ExistXQDataSource;

public class DataAccessor {

	private XQDataSource xqs;

	public DataAccessor() {
		xqs = new ExistXQDataSource();
		try {
			xqs.setProperty("serverName", "localhost");
			xqs.setProperty("port", "8080");
			xqs.setProperty("user", "admin");
			xqs.setProperty("password", "eXist");
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Integer> query1() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		XQConnection con = null;
		XQResultSequence rs = null;
		XQExpression pe = null;
		try {
			File f = new File("src/main/resources/Eurovision1.xquery");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			br.readLine();
			String path = br.readLine();
			
			con = this.xqs.getConnection();
			pe = con.createExpression();
			rs = pe.executeQuery(path);
			while (rs.next()) {
				l.add(Integer.parseInt(rs.getItemAsString(null)));
			}
		} catch (XQException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{
				if(rs != null){
					rs.close();
				}
				if(pe != null){
					pe.close();			
				}
				if(con != null){
					con.close();
				}
			}catch(XQException e){}
		}
		return l;
	}

	public ArrayList<Object[]> query2(int anno) {
		ArrayList<Object[]> l = new ArrayList<Object[]>();
		InputStream is = getClass().getResourceAsStream("src/main/resources/Eurovision2.xquery");
		XQConnection con = null;
		XQResultSequence rs = null;
		XQPreparedExpression pe = null;
		try {
			con = this.xqs.getConnection();
			pe = con.prepareExpression(is);
			pe.bindInt(new QName("anyo"), anno, null);
			rs = pe.executeQuery();
			Element e;
			int i = 1;
			while (rs.next()) {
				e = (Element) rs.getObject();
				l.add(new Object[]{i++,
						e.getAttribute("pais"),
						e.getAttribute("artista"),
						e.getAttribute("cancion"),
						e.getAttribute("puntos")});
			}
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{
				if(rs != null){
					rs.close();
				}
				if(pe != null){
					pe.close();			
				}
				if(con != null){
					con.close();
				}
			}catch(XQException e){}
		}
		return l;
	}

	public String query3() {
		String s = new String();
		InputStream is = getClass().getResourceAsStream("../resources/Eurovision1.xquery");
		ArrayList<Integer> l = new ArrayList<Integer>();
		XQConnection con = null;
		XQResultSequence rs = null;
		XQPreparedExpression pe = null;;
		try {
			con = this.xqs.getConnection();
			pe = con.prepareExpression(is);
			rs = pe.executeQuery();
			while (rs.next()) {
				l.add(Integer.parseInt(rs.getItemAsString(null)));
			}
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{
				if(rs != null){
					rs.close();
				}
				if(pe != null){
					pe.close();			
				}
				if(con != null){
					con.close();
				}
			}catch(XQException e){}
		}
		return s;
	}
	

}

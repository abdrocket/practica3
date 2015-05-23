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

	public ArrayList<Integer> XQuery1() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		XQConnection con = null;
		XQResultSequence rs = null;
		XQExpression pe = null;
		BufferedReader br = null;
		try {
			File f = new File("src/main/resources/Eurovision1.xquery");
			FileReader fr = new FileReader(f);
			br = new BufferedReader(fr);
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
			try {
				if (rs != null) {
					rs.close();
				}
				if (pe != null) {
					pe.close();
				}
				if (con != null) {
					con.close();
				}
				if (br != null)
					br.close();
			} catch (XQException | IOException e) {
			}
		}
		return l;
	}

	public ArrayList<Clasificacion> XQuery2(int anno) {
		ArrayList<Clasificacion> l = new ArrayList<Clasificacion>();
		String source = new String("/Eurovision2.xquery");

		InputStream is = this.getClass().getResourceAsStream(source);

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
				Clasificacion c = new Clasificacion(i, e.getAttribute("pais"),
						e.getAttribute("cancion"), e.getAttribute("artista"),
						Integer.parseInt(e.getAttribute("puntos").trim()));
				l.add(c);
				i++;
			}
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pe != null) {
					pe.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (XQException e) {
			}
		}
		return l;
	}

	public String XQuery3(int anno) {
		String ed = "";
		String source = new String("/Eurovision3.xquery");
		InputStream is = this.getClass().getResourceAsStream(source);

		XQConnection con = null;
		XQPreparedExpression pe = null;
		XQResultSequence rs = null;

		try {
			con = this.xqs.getConnection();
			pe = con.prepareExpression(is);
			pe.bindInt(new QName("anyo"), anno, null);
			rs = pe.executeQuery();
			while (rs.next()) {
				ed = rs.getSequenceAsString(null);
			}
		} catch (XQException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pe != null) {
					pe.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (XQException e) { }
		}
		return ed;
	}

}

package com.sist.temp;
import java.util.*;
import java.sql.*;
public class MusicDAO {
	/*
	 MNO     int
	 CNO     int
	 TITLE      VARCHAR(500)
	 SINGER      VARCHAR(500)
	 ALBUM    VARCHAR(500)
	 POSTER       VARCHAR(260)
	 IDCREMENT    int
	 STATE  VARCHAR(20)
	KEY  VARCHAR(30)
	*/
	private Connection  conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.118:1521:xe";
	
	public MusicDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void musicInsert(Music m) {
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
			String sql = "insert into genie_music "
					+ "values((select nvl(max(mno)+1,1) from genie_music), "
					+ "?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, m.getCno());
			ps.setString(2,m.getTitle());
			ps.setString(3,m.getSinger());
			ps.setString(4,m.getAlbum());
			ps.setString(5,m.getPoster());
			ps.setInt(6, m.getIdcrement());
			ps.setString(7,m.getState());
			ps.setString(8,m.getKey());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}

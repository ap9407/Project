package jsbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SaleDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private DataSource ds;
	private Context init;

	private static SaleDAO instance = new SaleDAO();

	public static SaleDAO getInstance() {
		return instance;
	}

	public SaleDAO() {

		try {
			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
		}
	}

	public List<SaleDTO> selectAllList() {
		ArrayList<SaleDTO> list = new ArrayList<SaleDTO>();
		String sql = "select C.CLINO, C.CNAME, C.GRADE, C.BPDATE, B.BPNO, B.BPNAME, B.REGCON, B.PERIOD, B.TAXRATE, B.MAXRATE, CASE WHEN (GRADE='1등급(VIP)' OR GRADE='2등급') THEN MAXRATE ELSE TAXRATE END as rate, B.NOTE, E.EMPNO, E.ENAME from client C, employee E, bankproduct B where C.bpno = B.bpno and C.empno = E.empno";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				SaleDTO ob = new SaleDTO();
				ob.setClino(rs.getInt("clino"));
				ob.setCname(rs.getString("cname"));
				ob.setGrade(rs.getString("grade"));
				ob.setBpdate(rs.getString("bpdate"));
				ob.setBpno(rs.getInt("bpno"));
				ob.setBpname(rs.getString("bpname"));
				ob.setRegcon(rs.getString("regcon"));
				ob.setPeriod(rs.getString("period"));
				ob.setTaxrate(rs.getDouble("taxrate"));
				ob.setMaxrate(rs.getDouble("maxrate"));
				ob.setRate(rs.getDouble("rate"));
				ob.setNote(rs.getString("note"));
				ob.setEmpno(rs.getInt("empno"));
				ob.setEname(rs.getString("ename"));
				list.add(ob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

    public List<SaleDTO> selectMonthList(String year, String month) {
    	  String tmp = year + month;
	      ArrayList<SaleDTO> list = new ArrayList<SaleDTO>();
	      String sql = "select C.BPDATE, C.CLINO, C.CNAME, C.GRADE, B.BPNO, B.BPNAME, B.REGCON, B.PERIOD, B.TAXRATE,  B.MAXRATE, CASE WHEN (GRADE='1등급(VIP)' OR GRADE='2등급') THEN MAXRATE ELSE TAXRATE END as rate, B.NOTE, E.EMPNO, E.ENAME from client C, employee E, bankproduct B where C.bpno = B.bpno and C.empno = E.empno AND TO_CHAR(c.bpdate, 'YYYYMM')=? ORDER BY BPDATE";

	      try {
	         conn = ds.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, tmp);
	         rs = pstmt.executeQuery();

	         while (rs.next()) {
	            SaleDTO ob = new SaleDTO();
	            ob.setBpdate(rs.getString("bpdate"));
				ob.setClino(rs.getInt("clino"));
				ob.setCname(rs.getString("cname"));
				ob.setGrade(rs.getString("grade"));
				ob.setBpno(rs.getInt("bpno"));
				ob.setBpname(rs.getString("bpname"));
				ob.setRegcon(rs.getString("regcon"));
				ob.setPeriod(rs.getString("period"));
				ob.setTaxrate(rs.getDouble("taxrate"));
				ob.setMaxrate(rs.getDouble("maxrate"));
				ob.setRate(rs.getDouble("rate"));
				ob.setNote(rs.getString("note"));
				ob.setEmpno(rs.getInt("empno"));
				ob.setEname(rs.getString("ename"));
				list.add(ob);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      return list;
	}
    
    public List<SaleDTO> selectYearList(String year) {
	      ArrayList<SaleDTO> list = new ArrayList<SaleDTO>();
	      String sql = "select C.BPDATE, C.CLINO, C.CNAME, C.GRADE, B.BPNO, B.BPNAME, B.REGCON, B.PERIOD, B.TAXRATE,  B.MAXRATE, CASE WHEN (GRADE='1등급(VIP)' OR GRADE='2등급') THEN MAXRATE ELSE TAXRATE END as rate, B.NOTE, E.EMPNO, E.ENAME from client C, employee E, bankproduct B where C.bpno = B.bpno and C.empno = E.empno AND TO_CHAR(c.bpdate, 'YYYY')=? ORDER BY BPDATE";

	      try {
	         conn = ds.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, year);
	         rs = pstmt.executeQuery();

	         while (rs.next()) {
	            SaleDTO ob = new SaleDTO();
	            ob.setBpdate(rs.getString("bpdate"));
				ob.setClino(rs.getInt("clino"));
				ob.setCname(rs.getString("cname"));
				ob.setGrade(rs.getString("grade"));
				ob.setBpno(rs.getInt("bpno"));
				ob.setBpname(rs.getString("bpname"));
				ob.setRegcon(rs.getString("regcon"));
				ob.setPeriod(rs.getString("period"));
				ob.setTaxrate(rs.getDouble("taxrate"));
				ob.setMaxrate(rs.getDouble("maxrate"));
				ob.setRate(rs.getDouble("rate"));
				ob.setNote(rs.getString("note"));
				ob.setEmpno(rs.getInt("empno"));
				ob.setEname(rs.getString("ename"));
				list.add(ob);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      return list;
	   }

	public List<SaleDTO> selectBpList() {
		ArrayList<SaleDTO> list = new ArrayList<SaleDTO>();
		String sql = "SELECT B.BPNO, B.BPNAME, C.CLINO, C.CNAME, C.GRADE, C.BPDATE, B.REGCON, B.PERIOD, B.TAXRATE, B.MAXRATE, CASE WHEN (GRADE='1등급(VIP)' OR GRADE='2등급') THEN MAXRATE ELSE TAXRATE END AS RATE, B.NOTE, E.EMPNO, E.ENAME FROM CLIENT C, EMPLOYEE E, BANKPRODUCT B WHERE C.BPNO = B.BPNO AND C.EMPNO = E.EMPNO ORDER BY BPNO";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				SaleDTO ob = new SaleDTO();
				ob.setBpno(rs.getInt("bpno"));
				ob.setBpname(rs.getString("bpname"));
				ob.setClino(rs.getInt("clino"));
				ob.setCname(rs.getString("cname"));
				ob.setGrade(rs.getString("grade"));
				ob.setBpdate(rs.getString("bpdate"));
				ob.setRegcon(rs.getString("regcon"));
				ob.setPeriod(rs.getString("period"));
				ob.setTaxrate(rs.getDouble("taxrate"));
				ob.setMaxrate(rs.getDouble("maxrate"));
				ob.setRate(rs.getDouble("rate"));
				ob.setNote(rs.getString("note"));
				ob.setEmpno(rs.getInt("empno"));
				ob.setEname(rs.getString("ename"));
				list.add(ob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public List<SaleDTO> selectBpTotalList() {
		ArrayList<SaleDTO> list = new ArrayList<SaleDTO>();
		String sql = "SELECT B.BPNAME, COUNT(B.BPNAME) as bptotalnum FROM CLIENT C, EMPLOYEE E, BANKPRODUCT B WHERE C.BPNO = B.BPNO AND C.EMPNO = E.EMPNO GROUP BY B.BPNAME ORDER BY B.BPNAME";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				SaleDTO ob = new SaleDTO();
				ob.setBpname(rs.getString("bpname"));
				ob.setBptotalnum(rs.getInt("bptotalnum"));

				list.add(ob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public List<SaleDTO> selectMonthList2() {
		ArrayList<SaleDTO> list = new ArrayList<SaleDTO>();
		String sql = "select C.BPDATE, C.CLINO, C.CNAME, C.GRADE, B.BPNO, B.BPNAME, B.REGCON, B.PERIOD, B.TAXRATE, B.MAXRATE, CASE  WHEN (GRADE='1등급(VIP)' OR GRADE='2등급')  THEN MAXRATE ELSE TAXRATE  END as rate,  B.NOTE, E.EMPNO, E.ENAME from client C, employee E, bankproduct B where C.bpno = B.bpno and C.empno = E.empno AND ( BPDATE LIKE '?%' AND BPDATE LIKE '%?%' ) ORDER BY BPDATE";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				SaleDTO ob = new SaleDTO();
				ob.setBpdate(rs.getString("bpdate"));
				ob.setEmpno(rs.getInt("empno"));
				ob.setEname(rs.getString("ename"));
				ob.setClino(rs.getInt("clino"));
				ob.setCname(rs.getString("cname"));
				ob.setGrade(rs.getString("grade"));
				ob.setBpno(rs.getInt("bpno"));
				ob.setBpname(rs.getString("bpname"));
				ob.setRegcon(rs.getString("regcon"));
				ob.setPeriod(rs.getString("period"));
				ob.setRate(rs.getDouble("rate"));
				ob.setTaxrate(rs.getDouble("taxrate"));
				ob.setMaxrate(rs.getDouble("maxrate"));
				ob.setNote(rs.getString("note"));
				list.add(ob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	public List<SaleDTO> selectBpList2(String searchname) {
		ArrayList<SaleDTO> list = new ArrayList<SaleDTO>();
		String sql = "SELECT B.BPNAME, C.BPDATE, C.CLINO, C.CNAME, C.GRADE, B.BPNO, B.REGCON, B.PERIOD, B.TAXRATE, B.MAXRATE, CASE WHEN (GRADE='1등급(VIP)' OR GRADE='2등급') THEN MAXRATE ELSE TAXRATE END as rate, B.NOTE, E.EMPNO, E.ENAME from client C, employee E, bankproduct B where C.bpno = B.bpno and C.empno = E.empno and BPNAME LIKE '%?%' ORDER BY BPDATE";

		try {
			conn = ds.getConnection();
			pstmt.setString(1, searchname);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				SaleDTO ob = new SaleDTO();
				ob.setBpname(rs.getString("bpname"));
				ob.setBpdate(rs.getString("bpdate"));
				ob.setClino(rs.getInt("clino"));
				ob.setCname(rs.getString("cname"));
				ob.setGrade(rs.getString("grade"));
				ob.setBpno(rs.getInt("bpno"));
				ob.setRegcon(rs.getString("regcon"));
				ob.setPeriod(rs.getString("period"));
				ob.setTaxrate(rs.getDouble("taxrate"));
				ob.setMaxrate(rs.getDouble("maxrate"));
				ob.setRate(rs.getDouble("rate"));
				ob.setNote(rs.getString("note"));
				ob.setEmpno(rs.getInt("empno"));
				ob.setEname(rs.getString("ename"));
				list.add(ob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

}

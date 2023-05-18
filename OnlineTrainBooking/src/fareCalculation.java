
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class fareCalculation
 */
@WebServlet("/fareCalculation")
public class fareCalculation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public fareCalculation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		String s = "100";
		/*
		 * Connection c = null; Statement s = null; ResultSet rs = null; CallableStatement cstmt=null;; String a =
		 * request.getParameter("to"); String b = request.getParameter("from"); String
		 * tno=request.getParameter("train_no"); String classType=request.getParameter("train_class"); try {
		 * Class.forName("org.postgresql.Driver"); c = DriverManager.getConnection(
		 * "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123"); cstmt =
		 * c.prepareCall("CALL  trainFare('"+a+"','"+b+"',"+tno+",'"+classType+"')");
		 * 
		 * cstmt.executeUpdate();
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		pw.print(s);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}

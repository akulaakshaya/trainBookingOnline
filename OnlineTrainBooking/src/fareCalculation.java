import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fareCalculation")
public class fareCalculation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public fareCalculation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("This servlet only supports POST requests.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection c = null;
		CallableStatement cstmt = null;

		String a = request.getParameter("to");
		String b = request.getParameter("from");
		String tno = request.getParameter("train_no");
		String classType = request.getParameter("train_class");

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			cstmt = c.prepareCall("CALL trainFare(?, ?, ?, ?, 0)");
			cstmt.setString(1, a);
			cstmt.setString(2, b);
			cstmt.setInt(3, Integer.parseInt(tno));
			cstmt.setString(4, classType);
			cstmt.execute();
			cstmt.registerOutParameter(1, Types.DOUBLE);
			Double x = cstmt.getDouble(1);
			System.out.println("Helloi");

			// Set the calculated fare as an attribute in the request
			request.setAttribute("total_fare", x);

			// Forward the request to the preview.jsp page
			RequestDispatcher dispatcher = request.getRequestDispatcher("preview.jsp");
			dispatcher.forward(request, response);
			System.out.println("Helloi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

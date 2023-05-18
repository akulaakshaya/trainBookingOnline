
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class finalServlet
 */
@WebServlet("/finalServlet")
public class finalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public finalServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			Statement s = c.createStatement();
			String query = "insert into gv_passengers values(";
			s.executeUpdate(query);
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		pw.print("<html>\r\n" + "<head>\r\n" + "<title>Final</title></head>\r\n" + "<body>\r\n"
				+ "<h1>TICKET SUCCESSFULLY BOOKED </h1>\r\n" + "</body></html>\r\n" + "\r\n ");
		doGet(request, response);
	}

}

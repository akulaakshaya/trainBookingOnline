
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class findTrainsServlet
 */
@WebServlet("/findTrainsServlet")
public class findTrainsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrainDAO trainDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init() {
		trainDAO = new TrainDAO();
	}

	public findTrainsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String a = request.getParameter("to");
		String b = request.getParameter("from");
		findTrainsDAO find = new findTrainsDAO();
		PrintWriter pw = response.getWriter();
		String s1 = trainDAO.getTrains(a, b);

		if (s1 == null)
			pw.print("-1");
		else
			pw.print(s1);
		// doGet(request, response);
	}

}


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TrainClasses
 */
@WebServlet("/TrainClasses")
public class TrainClasses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrainDAO trainDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init() {
		trainDAO = new TrainDAO();
	}

	public TrainClasses() {
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
		String s1 = "";
		String a = request.getParameter("train");

		s1 = trainDAO.getClasses(a);
		pw.print(s1);
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

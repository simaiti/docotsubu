package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		//		ServletContext application = this.getServletContext();
		//		List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		//
		//		if (mutterList == null) {
		//			mutterList = new ArrayList<>();
		//			application.setAttribute("mutterList", mutterList);
		//		}

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("/docoTsubu/");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Main.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		String param = request.getParameter("state");
		String[] state = {"",""};

		if(param != null) {
			state = param.split(",");
		}

        System.out.println(state[1]);

		String id = request.getParameter(state[1]);

		if(state[0].equals("delete")){
			GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
			getMutterListLogic.removeMutter(state[1]);
		}

		if (text != null && text.length() != 0) {
//			ServletContext application = this.getServletContext();
//			List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			Mutter mutter = new Mutter(loginUser.getName(), text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);

//			application.setAttribute("mutterList", mutterList);
		} else if (param == null){
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}

		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Main.jsp");
		dispatcher.forward(request, response);
	}

}

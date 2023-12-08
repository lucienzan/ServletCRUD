package Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import CRUD.UserRepository;
import Model.UserModel;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserRepository repository;
	 public void init() {
	        repository = new UserRepository();
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/create":
				CreateForm(request,response);
				break;
			case "/userCreate":
				SaveUser(request,response);
				break;
			case "/edit":
				EditForm(request,response);
				break;
			default:
				GetAll(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void GetAll(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<UserModel> listUser = repository.GetAll();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void CreateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("add-edit-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void EditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		UserModel model = repository.GetUser(id);
		request.setAttribute("user", model);
		RequestDispatcher dispatcher = request.getRequestDispatcher("add-edit-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void SaveUser(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    Random random = new Random();
	    int id = random.nextInt();
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String ageParam = request.getParameter("age");

	    if (firstName == null || firstName.trim().isEmpty()) {
	        request.setAttribute("firstNameError", "First Name field is required.");
	    }

	    if (lastName == null || lastName.trim().isEmpty()) {
	        request.setAttribute("lastNameError", "Last Name field is required.");
	    }

	    int age = 0; // Default value if age parameter is empty
	    if (!ageParam.isEmpty()) {
	        try {
	            age = Integer.parseInt(ageParam);
	            if (age <= 0 || age > 120) {
	                throw new NumberFormatException();
	            }
	        } catch (NumberFormatException e) {
	            request.setAttribute("ageError", "Age must be in the range of 1 and 120.");
	        }
	    }

	    // Check for any errors
	    if (request.getAttribute("firstNameError") != null ||
	            request.getAttribute("lastNameError") != null ||
	            request.getAttribute("ageError") != null) {
	        // If there are errors, redirect back with error messages
			RequestDispatcher dispatcher = request.getRequestDispatcher("create");
			dispatcher.forward(request, response);
	    } else {
	        UserModel newUser = new UserModel(id, firstName, lastName, age);
	        repository.Create(newUser);
	        response.sendRedirect("UserServlet");
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

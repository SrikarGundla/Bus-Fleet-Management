import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/Driver_loginServlet")

public class Driver_loginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public Driver_loginServlet() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try  {
	
		int id = Integer.parseInt(request.getParameter("id"));
		String pass = request.getParameter("password");
		
		
		request.setAttribute("driver_id", id);


		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/dbms_project";
 		String username = "dbms_project";
 		String password = "1234";
		
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password);
 		System.out.println("Printing connection object "+con);
 		
 		
 		PreparedStatement check = con.prepareStatement("select * from driver_details");
		ResultSet rset = check.executeQuery();
		
		int flag = 0;
		
		while(rset.next()) {
			
			int id1 = Integer.parseInt(rset.getString("driver_id"));
			String pass1 = rset.getString("driver_password");
			
			if(id1 == id && pass1.equals(pass))  {
				
				flag = 1 ;
				break ;
				
			}
			
		}
 		
 		

		if(flag == 1)  {
			
			RequestDispatcher rd = request.getRequestDispatcher("Driver_trips.jsp");
			rd.forward(request, response);
			
		}
		
		if(flag == 0)  {
			
			RequestDispatcher rd = request.getRequestDispatcher("Error_Driver_login.jsp");
			rd.forward(request, response);
			
		}
		

		}
		
		 catch (Exception e)  {
			 
 			e.printStackTrace();
 			
 		}

	}


}
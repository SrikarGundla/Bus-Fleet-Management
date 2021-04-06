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
@WebServlet("/Change_manager_passServlet")

public class Change_manager_passServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public Change_manager_passServlet() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try  {
	
		int id = Integer.parseInt(request.getParameter("id"));
		String pass = request.getParameter("password");
		String pass1 = request.getParameter("password1");
		String pass2 = request.getParameter("password2");


		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/dbms_project";
 		String username = "dbms_project";
 		String password = "1234";
		
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password);
 		System.out.println("Printing connection object "+con);
 		
 		
 		PreparedStatement check = con.prepareStatement("select * from manager_details");
		ResultSet rset = check.executeQuery();
		
		int flag = 0;
		
		while(rset.next()) {
			
			int id1 = Integer.parseInt(rset.getString("manager_id"));
			String passf = rset.getString("manager_password");
			
			if(id1 == id && passf.equals(pass) && pass1.equals(pass2))  {
				
				flag = 1 ;
				break ;
				
			}
			
		}
 		

		if(flag == 1)  {
			
			PreparedStatement change_pass = con.prepareStatement("update manager_details set manager_password = ? where manager_id = ?");
			
			change_pass.setString(1,pass1);
			change_pass.setInt(2,id);
			
			int res = change_pass.executeUpdate();

			RequestDispatcher rd = request.getRequestDispatcher("Result_Manager_change_pass.jsp");
			rd.forward(request, response);
			
		}
		
		if(flag == 0)  {
			
			RequestDispatcher rd = request.getRequestDispatcher("Error_Manager_change_pass.jsp");
			rd.forward(request, response);
			
		}
		

		}
		
		 catch (Exception e)  {
			 
 			e.printStackTrace();
 			
 		}

	}


}
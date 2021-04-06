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
@WebServlet("/Remove_DriverServlet")

public class Remove_DriverServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public Remove_DriverServlet() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try  {
			
	
		int id = Integer.parseInt(request.getParameter("id"));		


		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/dbms_project";
 		String username = "dbms_project";
 		String password = "1234";
		
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password);
 		System.out.println("Printing connection object "+con);
 		
 		
 		
		PreparedStatement st = con.prepareStatement("select * from driver_details");
 		
		ResultSet rset = st.executeQuery();
		
		int flag = 0;
		
		while(rset.next()) {
			
			int did = Integer.parseInt(rset.getString("driver_id"));
			
			if(did == id)  {
				
				flag = 1 ;
				break ;
				
			}
			
		}
		
		
		if(flag == 0)  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Remove_Driver.jsp");
			rd2.forward(request, response);
			
		}		
		
 		

		PreparedStatement st1 = con.prepareStatement("delete from driver_details where driver_id = ?");
		
 		st1.setInt(1, id);
 		
 		
		PreparedStatement st2 = con.prepareStatement("delete from driver_pref where driver_id = ?");
		
 		st2.setInt(1, id);
		
 		
		PreparedStatement st3 = con.prepareStatement("delete from driver_location where driver_id = ?");
		
 		st3.setInt(1, id);
		
		
		int result1 = -1 ;
		int result2 = -1 ;
		int result3 = -1 ;
		
		
		
		if(flag == 1)  {
		
			result1 = st1.executeUpdate();
			result2 = st2.executeUpdate();
			result3 = st3.executeUpdate();
		
		}
		

		if((result1 > 0) && (result2 > 0) && (result3 > 0))  {
			
			RequestDispatcher rd1 = request.getRequestDispatcher("Result_Remove_Driver.jsp");
			rd1.forward(request, response);
			
		}
		
		else  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Remove_Driver.jsp");
			rd2.forward(request, response);
			
		}
		

		}
		
		 catch (Exception e)  {
			 
			e.printStackTrace();
 			
		}

	}


}
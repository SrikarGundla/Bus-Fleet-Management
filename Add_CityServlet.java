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
@WebServlet("/Add_CityServlet")

public class Add_CityServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public Add_CityServlet() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try  {
			
	
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		


		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/dbms_project";
 		String username = "dbms_project";
 		String password = "1234";
		
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password);
 		System.out.println("Printing connection object "+con);
 		
 		
 		
 		PreparedStatement st = con.prepareStatement("select * from city_details");
 		
		ResultSet rset = st.executeQuery();
		
		int flag=1;
		
		while(rset.next()) {
			
			int ciid = Integer.parseInt(rset.getString("city_id"));
			
			if(ciid == id)  {
				
				flag = 0 ;
				break ;
				
			}
			
		}
		
		
		
		if(flag == 0)  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Add_City.jsp");
			rd2.forward(request, response);
			
		}
		

		
		
 		

		PreparedStatement st1 = con.prepareStatement("insert into city_details values(?, ?, ?)");
		
 		st1.setInt(1, id);
		st1.setString(2, name);
		st1.setString(3, state);
		
		
		int result1 = -1 ;
		
		
		
		if(flag == 1)  {
		
			result1 = st1.executeUpdate();
		
		}
		

		if(result1 > 0)  {
			
			RequestDispatcher rd1 = request.getRequestDispatcher("Result_Add_City.jsp");
			rd1.forward(request, response);
			
		}
		
		else  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Add_City.jsp");
			rd2.forward(request, response);
			
		}
		

		}
		
		 catch (Exception e)  {
			 
			e.printStackTrace();
 			
		}

	}


}
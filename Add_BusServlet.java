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
@WebServlet("/Add_BusServlet")

public class Add_BusServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public Add_BusServlet() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try  {
			
	
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int ft = Integer.parseInt(request.getParameter("ft"));
		int tf = Integer.parseInt(request.getParameter("tf"));
		String route = request.getParameter("route");
		int cid = Integer.parseInt(request.getParameter("city_id"));
		String numb = request.getParameter("numb");
		String timi = request.getParameter("timi");
		


		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/dbms_project";
 		String username = "dbms_project";
 		String password = "1234";
		
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password);
 		System.out.println("Printing connection object "+con);
 		
 		
 		
 		PreparedStatement st = con.prepareStatement("select * from city_details");
 		
		ResultSet rset = st.executeQuery();
		
		int flag=0;
		
		while(rset.next()) {
			
			int ciid = Integer.parseInt(rset.getString("city_id"));
			
			if(ciid == ft)  {
				
				flag += 1 ;
				break ;
				
			}
			
		}
		
		
		
 		st = con.prepareStatement("select * from city_details");
 		
		rset = st.executeQuery();
		
		while(rset.next()) {
			
			int ciid = Integer.parseInt(rset.getString("city_id"));
			
			if(ciid == tf)  {
				
				flag += 1 ;
				break ;
				
			}
			
		}
		
 		
 		
 		st = con.prepareStatement("select * from bus_details");
 		
		rset = st.executeQuery();
		
		while(rset.next()) {
			
			int bid = Integer.parseInt(rset.getString("bus_id"));
			
			if(bid == id)  {
				
				flag += 1 ;
				break ;
				
			}
			
		}
		
		
		if(flag != 2)  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Add_Bus.jsp");
			rd2.forward(request, response);
			
		}
		

		
		
 		

		PreparedStatement st1 = con.prepareStatement("insert into bus_details values(?, ?, ?, ?, ?, ?, ?, ?)");
		
 		st1.setInt(1, id);
		st1.setString(2, name);
		st1.setInt(3, ft);
		st1.setInt(4, tf);
		st1.setString(5, route);
		st1.setInt(6, cid);
		st1.setString(7, numb);
		st1.setString(8,  timi);
		
		
		int result1 = -1 ;
		
		
		
		if((flag == 2) && (cid == ft || cid == tf))  {
		
			result1 = st1.executeUpdate();
		
		}
		

		if(result1 > 0)  {
			
			RequestDispatcher rd1 = request.getRequestDispatcher("Result_Add_Bus.jsp");
			rd1.forward(request, response);
			
		}
		
		else  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Add_Bus.jsp");
			rd2.forward(request, response);
			
		}
		

		}
		
		 catch (Exception e)  {
			 
			e.printStackTrace();
 			
		}

	}


}
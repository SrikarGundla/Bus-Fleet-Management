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
@WebServlet("/Add_DriverServlet")

public class Add_DriverServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public Add_DriverServlet() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try  {
			
	
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String pass = request.getParameter("password");
		
		int su = Integer.parseInt(request.getParameter("Sunday"));
		int mo = Integer.parseInt(request.getParameter("Monday"));
		int tu = Integer.parseInt(request.getParameter("Tuesday"));
		int we = Integer.parseInt(request.getParameter("Wednesday"));
		int th = Integer.parseInt(request.getParameter("Thursday"));
		int fr = Integer.parseInt(request.getParameter("Friday"));
		int sa = Integer.parseInt(request.getParameter("Saturday"));
		
		int cid = Integer.parseInt(request.getParameter("city_id"));
		


		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/dbms_project";
 		String username = "dbms_project";
 		String password = "1234";
		
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password);
 		System.out.println("Printing connection object "+con);
 		
 		
 		String city_name = "" ;
 		
 		
 		
 		PreparedStatement st = con.prepareStatement("select * from city_details");
 		
		ResultSet rset = st.executeQuery();
		
		int flag=1;
		
		while(rset.next()) {
			
			int ciid = Integer.parseInt(rset.getString("city_id"));
			
			if(ciid == cid)  {
				
				flag = 0 ;
				city_name = rset.getString("city");
				break ;
				
			}
			
		}
		
 		
 		
 		st = con.prepareStatement("select * from driver_details");
 		
		rset = st.executeQuery();
		
		while(rset.next()) {
			
			int did = Integer.parseInt(rset.getString("driver_id"));
			
			if(did == id)  {
				
				flag = 1 ;
				break ;
				
			}
			
		}
		
		
		if(flag == 1)  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Add_Driver.jsp");
			rd2.forward(request, response);
			
		}
		
		

		
		
 		

		PreparedStatement st1 = con.prepareStatement("insert into driver_details values(?, ?, ?)");
		
 		st1.setInt(1, id);
		st1.setString(2, name);
		st1.setString(3, pass);		
		
		
		PreparedStatement st2 = con.prepareStatement("insert into driver_pref values(?, ?, ?, ?, ?, ?, ?, ?)");
		
 		st2.setInt(1, id);
		st2.setInt(2, su);
		st2.setInt(3, mo);
		st2.setInt(4, tu);
		st2.setInt(5, we);
		st2.setInt(6, th);
		st2.setInt(7, fr);
		st2.setInt(8, sa);
		
		PreparedStatement st3 = con.prepareStatement("insert into driver_location values(?, ?, ?, ?)");
		
 		st3.setInt(1, id);
		st3.setString(2, name);
		st3.setString(3, city_name);
		st3.setInt(4, cid);
		
		
		
		int result1 = -1 ;
		int result2 = -1 ;
		int result3 = -1 ;
		
		
		
		if((flag == 0) && (su/2 == 0) && (mo/2 == 0) && (tu/2 == 0) && (we/2 == 0) && (th/2 == 0) && (fr/2 == 0) && (sa/2 == 0))  {
		
			result1 = st1.executeUpdate();
			result2 = st2.executeUpdate();
			result3 = st3.executeUpdate();
		
		}
		

		if((result1 > 0) && (result2 > 0) && (result3 > 0))  {
			
			RequestDispatcher rd1 = request.getRequestDispatcher("Result_Add_Driver.jsp");
			rd1.forward(request, response);
			
		}
		
		else  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Add_Driver.jsp");
			rd2.forward(request, response);
			
		}
		

		}
		
		 catch (Exception e)  {
			 
			e.printStackTrace();
 			
		}

	}


}
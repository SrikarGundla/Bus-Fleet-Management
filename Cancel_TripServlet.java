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

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;



/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/Cancel_TripServlet")

public class Cancel_TripServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public Cancel_TripServlet() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try  {
			
	
		int trip_id = Integer.parseInt(request.getParameter("trip_id"));
		


		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/dbms_project";
 		String username = "dbms_project";
 		String password = "1234";
		
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password);
 		System.out.println("Printing connection object "+con);
 		
 		
 		
 		
 		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String today_date = dtf.format(now);
		
		System.out.println(today_date);
		System.out.println();
		
 		
 		
 		
 		int flag = 0 ;
 		
 		
 		PreparedStatement st = con.prepareStatement("select * from trip_details");
 		
		ResultSet rset = st.executeQuery();
		
		String trip_date = "" ;
		int trip_no = -9876 ;
		
		int fr = -7896 ;
		int to = -4567 ;
		int driver_id = -6886 ;
		int bus_id = -9345 ;
		
		while(rset.next()) {
			
			trip_date = rset.getString("trip_date");
			trip_no = rset.getInt("trip_no") ;
			
			if(trip_no == trip_id && trip_date.equals(today_date))  {
				
				flag = 1 ;
				fr = rset.getInt("from_city_id") ;
				to = rset.getInt("to_city_id") ;
				bus_id = rset.getInt("bus_id") ;
				driver_id = rset.getInt("driver_id") ;
				break ;
				
			}
			
		}
		
		
		
		
		System.out.println("Trip no:" + trip_no);
		System.out.println("Trip id:" + trip_id);
		System.out.println();
		System.out.println("Trip date:" + trip_date);
		System.out.println("Today date:" + today_date);
		System.out.println();
		System.out.println("From:" + fr);
		System.out.println("To:" + to);
		System.out.println();
		System.out.println("Bus id:" + bus_id);
		System.out.println("Driver id:" + driver_id);
		System.out.println();
		
		
		
		if(flag != 1)  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Cancel_Trip.jsp");
			rd2.forward(request, response);
			
		}
		
		
		
		
		
 		st = con.prepareStatement("select * from city_details");
 		
		rset = st.executeQuery();
		
		String city_name = "" ;
		
		while(rset.next()) {
			
			if(fr == rset.getInt("city_id"))  {
				
				city_name = rset.getString("city") ;
				break ;
				
			}
			
		}
		
		
		
 		

		PreparedStatement st1 = con.prepareStatement("delete from trip_details where trip_no = ?");
		
 		st1.setInt(1, trip_id);
 		
 		
 		
		PreparedStatement st2 = con.prepareStatement("update driver_location set current_city = ? where driver_id = ?");
		
		st2.setString(1,  city_name);
		st2.setInt(2,  driver_id);
		
		
		
		PreparedStatement st3 = con.prepareStatement("update driver_location set city_id = ? where driver_id = ?");
		
		st3.setInt(1,  fr);
		st3.setInt(2,  driver_id);
		
		
		
		PreparedStatement st4 = con.prepareStatement("update bus_details set current_city = ? where bus_id = ?");
		
		st4.setInt(1,  fr);
		st4.setInt(2,  bus_id);
		
		
		
		int result1 = -1 ;
		int result2 = -1 ;
		int result3 = -1 ;
		int result4 = -1 ;
		
		
		
		if(flag == 1)  {
		
			result1 = st1.executeUpdate();
			result2 = st2.executeUpdate();
			result3 = st3.executeUpdate();
			result4 = st4.executeUpdate();
		
		}
		

		if((result1 > 0) && (result2 > 0) && (result3 > 0) && (result4 > 0))  {
			
			RequestDispatcher rd1 = request.getRequestDispatcher("Result_Cancel_Trip.jsp");
			rd1.forward(request, response);
			
		}
		
		else  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Cancel_Trip.jsp");
			rd2.forward(request, response);
			
		}
		

		}
		
		 catch (Exception e)  {
			 
			e.printStackTrace();
 			
		}

	}


}
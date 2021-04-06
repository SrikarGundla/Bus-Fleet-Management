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
@WebServlet("/Allocate_BusServlet")

public class Allocate_BusServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public Allocate_BusServlet() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try  {
			
	
		int driver_id = Integer.parseInt(request.getParameter("driver_id"));
		int bus_id = Integer.parseInt(request.getParameter("bus_id"));
		


		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/dbms_project";
 		String username = "dbms_project";
 		String password = "1234";
		
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password);
 		System.out.println("Printing connection object "+con);
 		
 		
 		
 		int flag = 0 ;
 		
 		
 		PreparedStatement st = con.prepareStatement("select * from bus_details");
 		
		ResultSet rset = st.executeQuery();
		
		int bus_city_id = -199382;
		
		while(rset.next()) {
			
			int bid = Integer.parseInt(rset.getString("bus_id"));
			
			if(bid == bus_id)  {
				
				bus_city_id = Integer.parseInt(rset.getString("current_city"));
				break ;
				
			}
			
		}
		
		
 		st = con.prepareStatement("select * from driver_location");
 		
		rset = st.executeQuery();
		
		int driver_city_id = -448899;
		
		while(rset.next()) {
			
			int did = Integer.parseInt(rset.getString("driver_id"));
			
			if(did == driver_id)  {
				
				driver_city_id = Integer.parseInt(rset.getString("city_id"));
				break ;
				
			}
			
		}
		
		
		if(driver_city_id == bus_city_id)  {
			
			flag += 1 ;
			
		}
		
		System.out.println(flag);
		System.out.println();


		String to_day = LocalDate.now().getDayOfWeek().name();
		System.out.println(to_day);
		
		to_day = to_day.toLowerCase();
		
		
		
 		st = con.prepareStatement("select * from driver_pref");
 		
		rset = st.executeQuery();
		
		while(rset.next()) {
			
			int did = Integer.parseInt(rset.getString("driver_id"));
			
			if(did == driver_id)  {
				
				flag += Integer.parseInt(rset.getString(to_day));
				break ;
				
			}
			
		}
		
		
		System.out.println(flag);
		System.out.println();
		
		
		
		if(flag != 2)  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Allocate_Bus.jsp");
			rd2.forward(request, response);
			
		}
		
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String today_date = dtf.format(now);
		
		System.out.println(today_date);
		System.out.println();
		
		
		
 		st = con.prepareStatement("select * from trip_details");
 		
		rset = st.executeQuery();
		
		while(rset.next()) {
			
			String gotit = rset.getString("trip_date");
			
			if(gotit.equals(today_date))  {
				
				int bid = rset.getInt("bus_id");
				int did = rset.getInt("driver_id");
				
				if(bid == bus_id || did == driver_id)  {
					
					flag = flag-1 ;
					break ;
					
				}
				
				
			}
			
		}
		
		
		
		System.out.println(flag);
		System.out.println();
		
		
		
		if(flag != 2)  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Allocate_Bus.jsp");
			rd2.forward(request, response);
			
		}
		
		
		
		
		int tripno = 0;
		
 		st = con.prepareStatement("select * from trip_details");
 		
		rset = st.executeQuery();
		
		while(rset.next()) {
			
			tripno = rset.getInt("trip_no");
			
		}
		
		tripno += 1 ;
		
		
		
		int fr = -92884 ;
		int to = -29938 ;
		String bus_name = "" ;
		String route = "" ;
		String bus_number = "" ;
		String timing = "" ;
		
 		st = con.prepareStatement("select * from bus_details");
 		
		rset = st.executeQuery();
		
		while(rset.next()) {
			
			if(bus_id == rset.getInt("bus_id"))  {
				
				fr = rset.getInt("from_to_id") ;
				to = rset.getInt("to_from_id") ;
				bus_name = rset.getString("bus_name") ;
				route = rset.getString("route") ;
				bus_number = rset.getString("bus_number") ;
				timing = rset.getString("bus_timings") ;
				
			}
			
		}
		
		
		
		System.out.println(tripno);
		System.out.println(fr);
		System.out.println(to);
		System.out.println(bus_name);
		System.out.println(route);
		System.out.println(bus_number);
		System.out.println(timing);
		System.out.println();
		
		
		
		int tot = fr+to ;
		tot = tot - driver_city_id ;
		
		
		
		
		
		String city_name = "" ;
		
 		st = con.prepareStatement("select * from city_details");
 		
		rset = st.executeQuery();
		
		while(rset.next()) {
			
			if(tot == rset.getInt("city_id"))  {
				
				city_name = rset.getString("city") ;
				
			}
			
		}
		
		
		
 		

		PreparedStatement st1 = con.prepareStatement("insert into trip_details values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
 		st1.setInt(1, tripno);
 		st1.setInt(2, bus_id);
 		st1.setInt(3, driver_city_id);
 		st1.setInt(4, tot);
 		st1.setInt(5, driver_id);
 		st1.setString(6,  today_date);
 		st1.setString(7, route);
 		st1.setString(8,  bus_name);
 		st1.setString(9,  bus_number);
 		st1.setString(10,  timing);
 		
 		
 		
		PreparedStatement st2 = con.prepareStatement("update driver_location set current_city = ? where driver_id = ?");
		
		st2.setString(1,  city_name);
		st2.setInt(2,  driver_id);
		
		
		
		PreparedStatement st3 = con.prepareStatement("update driver_location set city_id = ? where driver_id = ?");
		
		st3.setInt(1,  tot);
		st3.setInt(2,  driver_id);
		
		
		
		PreparedStatement st4 = con.prepareStatement("update bus_details set current_city = ? where bus_id = ?");
		
		st4.setInt(1,  tot);
		st4.setInt(2,  bus_id);
		
		
		
		int result1 = -1 ;
		int result2 = -1 ;
		int result3 = -1 ;
		int result4 = -1 ;
		
		
		
		if(flag == 2)  {
		
			result1 = st1.executeUpdate();
			result2 = st2.executeUpdate();
			result3 = st3.executeUpdate();
			result4 = st4.executeUpdate();
		
		}
		

		if((result1 > 0) && (result2 > 0) && (result3 > 0) && (result4 > 0))  {
			
			RequestDispatcher rd1 = request.getRequestDispatcher("Result_Allocate_Bus.jsp");
			rd1.forward(request, response);
			
		}
		
		else  {
			
			RequestDispatcher rd2 = request.getRequestDispatcher("Error_Allocate_Bus.jsp");
			rd2.forward(request, response);
			
		}
		

		}
		
		 catch (Exception e)  {
			 
			e.printStackTrace();
 			
		}

	}


}
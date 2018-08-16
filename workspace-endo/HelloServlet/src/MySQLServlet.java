

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MySQLServlet")
public class MySQLServlet extends HttpServlet {

    public MySQLServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out=response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>データベーステスト</title>");
		out.println("</head>");
		out.println("<body>");

		Connection conn=null;
		String url="jdbc:mysql://localhost/testdb";
		String user="root";
		String password="mysql";

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection(url,user,password);

			Statement stmt=conn.createStatement();
			String sql="SELECT * FROM test_table";
			ResultSet rs=stmt.executeQuery(sql);

			while(rs.next()){
				int userId=rs.getInt("user_id");
				String userName=rs.getString("user_name");
				String userPassword=rs.getString("password");
				out.println("<p>");
				out.println("ユーザーID："+userId+"、ユーザー名："+userName+"　パスワード："+userPassword);
				out.println("</p>");
			}

			rs.close();
			stmt.close();
		}catch(ClassNotFoundException e){
			out.println("ClassNotFoundException:"+e.getMessage());
		}catch(SQLException e){
			out.println("SQLException:"+e.getMessage());
		}catch(Exception e){
			out.println("Exception:"+e.getMessage());
		}finally{
			try{
				if(conn !=null){
					conn.close();
				}
			}catch(SQLException e){
				out.println("SQLException:"+e.getMessage());
			}
		}

		out.println("</body>");
		out.println("</html>");
	}
}


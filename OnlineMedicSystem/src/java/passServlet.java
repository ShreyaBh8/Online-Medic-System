import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class passServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pwl = res.getWriter();

        String uid = req.getParameter("email");
        String pass = req.getParameter("password");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            String query = "SELECT * FROM doctorInfo2 WHERE email = ? AND password = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, uid);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();
               if (rs.next()) {
                HttpSession session = req.getSession();
                String username = rs.getString("name");
                
                session.setAttribute("username", username);
                pwl.println("<html><body>");
                pwl.println("<script>");
                pwl.println("localStorage.setItem('username', '" + username + "');");
                pwl.println("window.location.href = 'Doctor/doctor_patient.html';"); 
                pwl.println("</script>");
                pwl.println("</body></html>");
            } else {
                pwl.println("<html><body>Invalid Login</body></html>");
            }

            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            pwl.print("<html><body>Error: " + e.getMessage() + "</body></html>");
        }
    }
} 
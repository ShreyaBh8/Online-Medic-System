import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class signInServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pwl = res.getWriter();

        String nm1 = req.getParameter("name");
        String nm2 = req.getParameter("e_mail");
        String nm3 = req.getParameter("password");

        try {
            HttpSession ses = req.getSession();
            ses.setAttribute("email", nm2);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            String q1 = "INSERT INTO patientInfo(name, e_mail, password) VALUES(?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(q1);

            pstmt.setString(1, nm1);
            pstmt.setString(2, nm2);
            pstmt.setString(3, nm3);

            int x = pstmt.executeUpdate();

            if (x > 0) {
                res.sendRedirect(req.getContextPath() + "/Patient/index.html");
            } else {
                pwl.println("<html><body>Registration Unsuccessful</body></html>");
            }
        } catch (Exception e) {
            pwl.print("<html><body>Error: " + e.getMessage() + "</body></html>");
        }
    }
}
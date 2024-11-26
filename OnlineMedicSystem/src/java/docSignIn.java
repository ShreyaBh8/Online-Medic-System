
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class docSignIn extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pwl = res.getWriter();

        String nm1 = req.getParameter("name");
        String nm2 = req.getParameter("email");
        String nm3 = req.getParameter("password");
        String nm4 = req.getParameter("contact");
        String nm5 = req.getParameter("doctor");
        String nm6 = req.getParameter("degree");
        String nm7 = req.getParameter("field");
        String nm8 = req.getParameter("experience");
        String nm9 = req.getParameter("timing");
        String nm10 = req.getParameter("chamber");
        String nm11 = req.getParameter("gender");
        String nm12 = req.getParameter("fees");

        try {
            HttpSession ses = req.getSession();
            ses.setAttribute("e_mail", nm2);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
            String q1 = "INSERT INTO doctorInfo2(name, email, password,contact,doctor,degree,field,experience,timing,chamber,gender,fees) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
             
            PreparedStatement pstmt = con.prepareStatement(q1);

            pwl.println("<html><body>");
            pwl.println("<script>");
            pwl.println("localStorage.setItem('username', '" + nm1 + "');");
            pwl.println("window.location.href = 'Doctor/doctor_patient.html';"); 
            pwl.println("</script>");
            pwl.println("</body></html>");

            pstmt.setString(1, nm1);
            pstmt.setString(2, nm2);
            pstmt.setString(3, nm3);
            pstmt.setString(4, nm4);
            pstmt.setString(5, nm5);
            pstmt.setString(6, nm6);
            pstmt.setString(7, nm7);
            pstmt.setString(8, nm8);
            pstmt.setString(9, nm9);
            pstmt.setString(10, nm10);
            pstmt.setString(11, nm11);
            pstmt.setString(12, nm12);

            int x = pstmt.executeUpdate();

            if (x > 0) {
               
                pwl.println("<html><body>");
                pwl.println("<script>");
                pwl.println("localStorage.setItem('username', '" + nm1 + "');");
                pwl.println("window.location.href = 'Doctor/doctor_patient.html';");
                pwl.println("</script>");
                pwl.println("</body></html>");
            } else {
                pwl.println("<html><body>Registration Unsuccessful</body></html>");
            }
        } catch (Exception e) {
            pwl.print("<html><body>Error: " + e.getMessage() + "</body></html>");
        }
    }
}
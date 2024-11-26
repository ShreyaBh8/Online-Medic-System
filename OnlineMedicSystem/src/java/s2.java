
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class s2 extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw1 = res.getWriter();
//        String doc = req.getParameter("doc");
        String uid = req.getParameter("email");
        HttpSession ses = req.getSession();
        String eid = (String) ses.getAttribute("email");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
            String query1 = "SELECT * FROM doctorInfo2 WHERE email = ? ";
            PreparedStatement pstmt = con.prepareStatement(query1);
            pstmt.setString(1, uid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String query = "UPDATE patientInfo SET doctor = '" + rs.getString(1) + "' WHERE e_mail = '" + eid + "'";
                PreparedStatement stmt = con.prepareStatement(query);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                   
                    HttpSession session = req.getSession();
                     String fees = rs.getString("fees");
                    session.setAttribute("fees", fees);
                    pw1.println("<html><body>");
                    pw1.println("<script>");
                    pw1.println("localStorage.setItem('fees', '" + fees + "');");
                    pw1.println("window.location.href = 'Patient/booking.html';");
                    pw1.println("</script>");
                    pw1.println("</body></html>");
                } else {
                    pw1.println("<!DOCTYPE html>\n"
                            + "<html lang=\"en\">\n"
                            + "<head>\n"
                            + "    <meta charset=\"UTF-8\">\n"
                            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                            + "    <title>Failure</title>\n"
                            + "    <link\n"
                            + "        href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900&display=swap\"\n"
                            + "        rel=\"stylesheet\">\n"
                            + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css\"\n"
                            + "        integrity=\"sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==\"\n"
                            + "        crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\n"
                            + "    <style>\n"
                            + "        body {\n"
                            + "            font-family: poppins;\n"
                            + "            background-color: #e0dfdf;\n"
                            + "        }\n"
                            + "        .container {\n"
                            + "            width: 50%;\n"
                            + "            margin: auto;\n"
                            + "            padding: 20px;\n"
                            + "            background-color: #fff;\n"
                            + "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                            + "            border-radius: 20px;\n"
                            + "            text-align: center;\n"
                            + "            position: relative;\n"
                            + "            top: 180px;\n"
                            + "        }\n"
                            + "        i {\n"
                            + "            color: rgba(188, 25, 25, 0.897);\n"
                            + "            font-size: 80px;\n"
                            + "        }\n"
                            + "        a {\n"
                            + "            text-decoration: none;\n"
                            + "            padding: 7px 20px;\n"
                            + "            border-radius: 20px;\n"
                            + "            background-color: rgba(188, 25, 25, 0.897);\n"
                            + "            color: white;\n"
                            + "        }\n"
                            + "    </style>\n"
                            + "</head>\n"
                            + "<body>\n"
                            + "    <div class=\"container\">\n"
                            + "        <section class=\"about-us\">\n"
                            + "            <i class=\"fa-solid fa-circle-xmark\"></i>\n"
                            + "            <h1 style=\"color: rgba(223, 50, 50, 0.851);\">Sorry :(</h1>\n"
                            + "            <p style=\"margin-bottom: 20px;\">\n"
                            + "                Something went wrong, please try again!!\n"
                            + "            </p>\n"
                            + "            <a href=\"#\">Go to home</a>\n"
                            + "        </section>\n"
                            + "    </div>\n"
                            + "</body>\n"
                            + "</html>");
                }

                stmt.close();
                con.close();
            }
        } catch (Exception e) {
            pw1.println("Error: " + e.getMessage());
        }
    }
}

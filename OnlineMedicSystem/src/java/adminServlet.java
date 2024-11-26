import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class adminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pwl = res.getWriter();

        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String securityQuestion = req.getParameter("security_question");

        try {
            HttpSession session = req.getSession();
            session.setAttribute("email", email);
           Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            String checkQuery = "SELECT * FROM admin";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(checkQuery);

            if (!rs.next()) {
                if ("register".equalsIgnoreCase(action)) {
                    String registerQuery = "INSERT INTO admin (name, email, password, security_question) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = con.prepareStatement(registerQuery);
                    pstmt.setString(1, name);
                    pstmt.setString(2, email);
                    pstmt.setString(3, password);
                    pstmt.setString(4, securityQuestion);

                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        res.sendRedirect("admin/index.html");
                    } else {
                        pwl.println("<html><body><h3>Registration Unsuccessful. Please try again.</h3></body></html>");
                    }
                    pstmt.close();
                }
            } else {
                if ("login".equalsIgnoreCase(action)) {
                    String loginQuery = "SELECT * FROM admin WHERE email = ? AND password = ? AND security_question = ?";
                    PreparedStatement loginStmt = con.prepareStatement(loginQuery);
                    loginStmt.setString(1, email);
                    loginStmt.setString(2, password);
                    loginStmt.setString(3, securityQuestion);

                    ResultSet loginResult = loginStmt.executeQuery();
                    if (loginResult.next()) {
                        res.sendRedirect("admin/index.html");
                    } else {
                        pwl.println("<!DOCTYPE html>\n"
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
                        + "                You are not the Admin, please try again!!\n"
                        + "            </p>\n"
                        + "            <a href=\"login.html\">Go to Login Page</a>\n"
                        + "        </section>\n"
                        + "    </div>\n"
                        + "</body>\n"
                        + "</html>");
                    }
                    loginStmt.close();
                } else {
                    pwl.println("<html><body><h3>Invalid action.</h3></body></html>");
                }
            }

            stmt.close();
            con.close();
        } catch (Exception e) {
            pwl.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
            e.printStackTrace();
        }
    }
}
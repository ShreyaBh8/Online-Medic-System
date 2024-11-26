import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class approveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String eid = req.getParameter("approve"); 

        if (eid != null && !eid.isEmpty()) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

                String query = "UPDATE doctorInfo2 SET approve = 'Yes' WHERE email = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, eid);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html lang=\"en\">");
                    out.println("<head>");
                    out.println("    <meta charset=\"UTF-8\">");
                    out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                    out.println("    <title>Success</title>");
                    out.println("    <link");
                    out.println("        href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900&display=swap\"");
                    out.println("        rel=\"stylesheet\">");
                    out.println("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css\"");
                    out.println("        integrity=\"sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==\"");
                    out.println("        crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />");
                    out.println("    <style>");
                    out.println("        body {");
                    out.println("            font-family: poppins;");
                    out.println("            background-color: #e0dfdf;");
                    out.println("        }");
                    out.println("        .container {");
                    out.println("            width: 50%;");
                    out.println("            margin: auto;");
                    out.println("            padding: 20px;");
                    out.println("            background-color: #fff;");
                    out.println("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
                    out.println("            border-radius: 20px;");
                    out.println("            text-align: center;");
                    out.println("            position: relative;");
                    out.println("            top: 180px;");
                    out.println("        }");
                    out.println("        i {");
                    out.println("            color: rgb(25, 188, 104);");
                    out.println("            font-size: 80px;");
                    out.println("        }");
                    out.println("        .sub {");
                    out.println("            text-decoration: none;");
                    out.println("            padding: 7px 20px;");
                    out.println("            border-radius: 20px;");
                    out.println("            background-color: rgba(6, 165, 85, 0.938);");
                    out.println("            color: white;");
                    out.println("        }");
                    out.println("    </style>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class=\"container\">");
                    out.println("<section class=\"about-us\">");
                    out.println("<i class=\"fa-solid fa-circle-check\"></i>");
                    out.println("<h1 style=\"color: rgb(21, 183, 21);\">Approved!!</h1>");
                    out.println("<p style=\"margin-bottom: 20px;\">");
                    out.println("</p>");
                    out.println("<form method=\"POST\" action=\"admin_doctor\">");
                    out.println("<button class=\"sub\">Back</button>");
                    out.println("</form>");
                    out.println("</section>");
                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    out.println("<!DOCTYPE html>\n"
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
                            + "        .sub {\n"
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
                            + "            <form method=\"POST\" action=\"admin_doctor\">"
                            + "<button class=\"sub\">Back</button>"
                            +"</form>"
                            + "        </section>\n"
                            + "    </div>\n"
                            + "</body>\n"
                            + "</html>");
                }

                con.close();

            } catch (SQLException e) {
                out.println("<html><body><h3>SQL Error: " + e.getMessage() + "</h3></body></html>");
            } catch (Exception e) {
                out.println("<html><body><h3>General Error: " + e.getMessage() + "</h3></body></html>");
            }
        } else {
            out.println("<html><body><h3>Error: Invalid doctor name.</h3></body></html>");
        }
    }
}
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class s1 extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw1 = res.getWriter();
        String nm2 = req.getParameter("Dept");
        String nm3 = req.getParameter("myAge");
        HttpSession ses = req.getSession();
        String eid = (String) ses.getAttribute("email"); // patient email
        String uid = (String) ses.getAttribute("e_mail"); // doctor email

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
            String updateQuery = "UPDATE patientInfo SET age = ?, department = ? WHERE e_mail = ?";
            PreparedStatement pstmt = con.prepareStatement(updateQuery);
            pstmt.setString(1, nm3);
            pstmt.setString(2, nm2);
            pstmt.setString(3, eid);
            int x = pstmt.executeUpdate();

            String doctorQuery = "SELECT name, email, password, contact, doctor, degree, field, experience, timing, chamber, gender,fees, approve FROM doctorInfo2 WHERE doctor = ?";
            PreparedStatement doctorStmt = con.prepareStatement(doctorQuery);
            doctorStmt.setString(1, nm2);
            ResultSet rs1 = doctorStmt.executeQuery();
            if (x > 0) {

                boolean found = false;

                pw1.println("<!DOCTYPE html>");
                pw1.println("<html lang=\"en\">");
                pw1.println("<head>");
                pw1.println("<meta charset=\"UTF-8\">");
                pw1.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                pw1.println("<title>Heart Specialist</title>");
                pw1.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css\">");
                pw1.println("<style>");
                pw1.println("/* Your CSS style from the design you provided */");
                pw1.println("/* Google Fonts - Poppins */");
                pw1.println("@import url(\"https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap\");");
                pw1.println("* {");
                pw1.println("    margin: 0;");
                pw1.println("    padding: 0;");
                pw1.println("    box-sizing: border-box;");
                pw1.println("    font-family: \"Poppins\", sans-serif;");
                pw1.println("}");
                pw1.println("h1 {");
                pw1.println("    text-align: center;");
                pw1.println("    margin: 20px 0;");
                pw1.println("}");
                pw1.println(".main-container {"
                        + "display: flex; gap:50px;"
                        + "}");
                pw1.println(".profile-container {");
                pw1.println("    display: grid;");
                pw1.println("    grid-template-columns: repeat(auto-fit, minmax(330px, 1fr));");
                pw1.println("    gap: 30px;");
                pw1.println("    padding: 20px;");
                pw1.println("    width: 21%;");
                pw1.println("}");
                pw1.println(".profile-card {");
                pw1.println("    display: flex;");
                pw1.println("    flex-direction: column;");
                pw1.println("    align-items: center;");
                pw1.println("    background: #fff;");
                pw1.println("    border-radius: 24px;");
                pw1.println("    padding: 20px;");
                pw1.println("    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);");
                pw1.println("    position: relative;");
                pw1.println("    box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.451);");
                pw1.println("}");
                pw1.println(".profile-card::before {");
                pw1.println("    content: \"\";");
                pw1.println("    position: absolute;");
                pw1.println("    top: 0;");
                pw1.println("    left: 0;");
                pw1.println("    height: 28%;");
                pw1.println("    width: 100%;");
                pw1.println("    border-radius: 24px 24px 0 0;");
                pw1.println("    background-color:  #40f4cccc;");
                pw1.println("}");
                pw1.println(".image {");
                pw1.println("    position: relative;");
                pw1.println("    height: 150px;");
                pw1.println("    width: 150px;");
                pw1.println("    border-radius: 50%;");
                pw1.println("    background-color:  #2ab091f2;");
                pw1.println("    padding: 3px;");
                pw1.println("    margin-bottom: 10px;");
                pw1.println("}");
                pw1.println(".image .profile-img {");
                pw1.println("    height: 100%;");
                pw1.println("    width: 100%;");
                pw1.println("    object-fit: cover;");
                pw1.println("    border-radius: 50%;");
                pw1.println("    border: 3px solid  white;");
                pw1.println("}");
                pw1.println(".text-data .name {");
                pw1.println("    font-size: 22px;");
                pw1.println("    font-weight: 500;");
                pw1.println("}");
                pw1.println(".sub {");
                pw1.println("    color: #fff;");
                pw1.println("    font-size: 14px;");
                pw1.println("    font-weight: 400;");
                pw1.println("    border: none;");
                pw1.println("    border-radius: 24px;");
                pw1.println("    margin-top: 10px;");
                pw1.println("    padding: 8px 24px;");
                pw1.println("    background-color:   #2ab091f2;");
                pw1.println("    cursor: pointer;");
                pw1.println("    transition: all 0.3s ease;");
                pw1.println("}");
                pw1.println(".container {");
                pw1.println("    width: 100%;");
                pw1.println("    display: flex;");
                pw1.println("    gap: 10px;");
                pw1.println("    flex-wrap: wrap;");
                pw1.println("}");
                pw1.println(".box {");
                pw1.println("    flex: 1;");
                pw1.println("    font-size: 12.5px;");
                pw1.println("    text-align: center;");
                pw1.println("    font-weight: 500;");
                pw1.println("}");
                pw1.println(".star {");
                pw1.println("    margin-top: 10px;");
                pw1.println("}");
                pw1.println(".box p {");
                pw1.println("    margin: 10px 0;");
                pw1.println("    color: #0b2a80;");
                pw1.println("}");
                pw1.println(".btm {");
                pw1.println("    padding: 0 10px;");
                pw1.println("    border-left: 2px solid rgba(0, 0, 0, 0.148);");
                pw1.println("}");
                pw1.println(".box span {");
                pw1.println("    color: #0e1527;");
                pw1.println("}");
                pw1.println(".rupee {");
                pw1.println("    color: #0a34a5;");
                pw1.println("    font-weight: 800;");
                pw1.println("    margin-top: 10px;");
                pw1.println("}");
                pw1.println("</style>");
                pw1.println("</head>");
                pw1.println("<body>");
                pw1.println("<h1>" + nm2 + "</h1>");
                pw1.println("<div class=\"main-container\">");

                while (rs1.next()) {
                    if (rs1.getString("approve").equals("Yes")) {

                        String doctor = rs1.getString("doctor");
                        if (nm2.equals(doctor)) {
                            found = true;
                            String doctorName = rs1.getString("name");
                            String email = rs1.getString("email");
                            String password = rs1.getString("password");
                            String contact = rs1.getString("contact");
                            String degree = rs1.getString("degree");
                            String field = rs1.getString("field");
                            String experience = rs1.getString("experience");
                            String timing = rs1.getString("timing");
                            String chamber = rs1.getString("chamber");
                            String gender = rs1.getString("gender");
                            String fees = rs1.getString("fees");
                          

                            pw1.println("<div class=\"profile-container\">");
                            pw1.println("<div class=\"profile-card\">");
                            pw1.println("<div class=\"image\">");
                            if(gender.equals("male")){
                                pw1.println("<img src=\"https://static.vecteezy.com/system/resources/thumbnails/008/957/222/small/male-doctor-avatar-occupation-clipart-icon-in-flat-design-vector.jpg\" alt=\"" + doctorName + "\" class=\"profile-img\" />");
                            }
                            else{
                                pw1.println("<img src=\"https://static.vecteezy.com/system/resources/thumbnails/008/957/225/small/female-doctor-avatar-clipart-icon-in-flat-design-vector.jpg\" alt=\"" + doctorName + "\" class=\"profile-img\" />");
                            }
                            
                            pw1.println("</div>");
                            pw1.println("<div class=\"text-data\">");
                            pw1.println("<span class=\"name\">" + doctorName + "</span>");
                            pw1.println("</div>");
                            pw1.println("<div class=\"container\">");
                            pw1.println("<div class=\"box\">");
                            pw1.println("<p><span>Degree: </span>" + degree + "</p>");
                            pw1.println("<p><span>Field: </span>" + field + "</p>");
                            pw1.println("<p><span>Experience: </span>" + experience + "</p>");
                            pw1.println("<p><span>Available: </span>" + timing + "</p>");
                            pw1.println("</div>");
                            pw1.println("<div class=\"box btm\">");
                            pw1.println("<p><span>Phone:</span> " + contact + "</p>");
                            pw1.println("<p><span>Email:</span> " + email + "</p>");
                            pw1.println("<p><span>Chamber:</span> " + chamber + "</p>");
                            pw1.println("</div>");
                            pw1.println("</div>");
                            pw1.println("<div class=\"rupee\">");
                            pw1.println("<p><span>Fees:</span> Rs." + fees + "</p>");
                            pw1.println("</div>");
                            pw1.println("<form method=\"POST\" action=\"s2\">");
                            pw1.println("<button class=\"sub\" value=\"" + email + "\" name=\"email\">Book</button>");
                            pw1.println("</form>");
                            pw1.println("</div>");
                            pw1.println("</div>");
                        }
                        if (!found) {
                            pw1.println("<!DOCTYPE html>\n"
                                    + "<html lang=\"en\">\n"
                                    + "<head>\n"
                                    + "    <meta charset=\"UTF-8\">\n"
                                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                                    + "    <title>Failure</title>\n"
                                    + "    <link\n"
                                    + "        href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap\"\n"
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
                                    + "\n"
                                    + "<body>\n"
                                    + "    <div class=\"container\">\n"
                                    + "        <section class=\"about-us\">\n"
                                    + "            <i class=\"fa-solid fa-circle-xmark\"></i>\n"
                                    + "\n"
                                    + "            <h1 style=\"color: rgba(223, 50, 50, 0.851);\">Sorry :(</h1>\n"
                                    + "            <p style=\"margin-bottom: 20px;\">\n"
                                    + "                Something went wrong please try again!!\n"
                                    + "            </p>\n"
                                    + "            <a href=\"#\">Go to home</a>\n"
                                    + "\n"
                                    + "\n"
                                    + "        </section>\n"
                                    + "    </div>\n"
                                    + "</body>\n"
                                    + "\n"
                                    + "</html>");
                        }
                    }
                }
                pw1.println("</div>");
                pw1.println("</body>");
                pw1.println("</html>");
            } else {
                // Failure message
                pw1.println("<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "    <meta charset=\"UTF-8\">\n"
                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "    <title>Failure</title>\n"
                        + "    <link\n"
                        + "        href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap\"\n"
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
                        + "\n"
                        + "<body>\n"
                        + "    <div class=\"container\">\n"
                        + "        <section class=\"about-us\">\n"
                        + "            <i class=\"fa-solid fa-circle-xmark\"></i>\n"
                        + "\n"
                        + "            <h1 style=\"color: rgba(223, 50, 50, 0.851);\">Sorry :(</h1>\n"
                        + "            <p style=\"margin-bottom: 20px;\">\n"
                        + "                Something went wrong please try again!!\n"
                        + "            </p>\n"
                        + "            <a href=\"#\">Go to home</a>\n"
                        + "\n"
                        + "\n"
                        + "        </section>\n"
                        + "    </div>\n"
                        + "</body>\n"
                        + "\n"
                        + "</html>");
            }
            con.close();
        } catch (SQLException e) {
            pw1.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            pw1.println("");
        }
    }
}
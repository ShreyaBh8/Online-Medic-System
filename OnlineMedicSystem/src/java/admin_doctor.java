import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class admin_doctor extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw1 = res.getWriter();

        try {
             Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
            String query = "SELECT name, email, password, contact, doctor, degree, field, experience, timing, chamber, gender, fees, approve FROM doctorInfo2";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

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
            pw1.println("    background-color:  #40f4cca3;");
            pw1.println("}");
            pw1.println(".image {");
            pw1.println("    position: relative;");
            pw1.println("    height: 150px;");
            pw1.println("    width: 150px;");
            pw1.println("    border-radius: 50%;");
            pw1.println("    background-color: #2ab091f2;");
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
            pw1.println("    background-color: #2ab091f2;");
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
            pw1.println("<h1>Registered Doctors</h1>");
            pw1.println("<div class=\"main-container\">");
            while (rs.next()) {
                String doctorName = rs.getString("name");
                String email = rs.getString("email");
                String contact = rs.getString("contact");
                String doctor = rs.getString("doctor");
                String degree = rs.getString("degree");
                String field = rs.getString("field");
                String experience = rs.getString("experience");
                String timing = rs.getString("timing");
                String chamber = rs.getString("chamber");
                String gender = rs.getString("gender");
                String fees = rs.getString("fees");
                String approve = rs.getString("approve");

                pw1.println("<div class=\"profile-container\">");
                pw1.println("<div class=\"profile-card\">");
                pw1.println("<div class=\"image\">");
                pw1.println("<img src=\"https://static.vecteezy.com/system/resources/thumbnails/008/957/225/small/female-doctor-avatar-clipart-icon-in-flat-design-vector.jpg\" alt=\"" + doctorName + "\" class=\"profile-img\" />");
                pw1.println("</div>");
                pw1.println("<div class=\"text-data\">");
                pw1.println("<span class=\"name\">" + doctorName + "</span>");
                pw1.println("</div>");
                pw1.println("<div class=\"container\">");
                pw1.println("<div class=\"box\">");
                pw1.println("<p><span>Specialization: </span>" + doctor + "</p>");
                pw1.println("<p><span>Degree: </span>" + degree + "</p>");
                pw1.println("<p><span>Field: </span>" + field + "</p>");
                pw1.println("<p><span>Experience: </span>" + experience + "</p>");
                pw1.println("<p><span>Timing: </span>" + timing + "</p>");
                pw1.println("</div>");
                pw1.println("<div class=\"box\">");
                pw1.println("<p><span>Contact: </span>" + contact + "</p>");
                pw1.println("<p><span>Email: </span>" + email + "</p>");
                pw1.println("<p><span>Chamber: </span>" + chamber + "</p>");
                pw1.println("<p><span>Gender: </span>" + gender + "</p>");
                 pw1.println("<p><span>Fees: </span>" + fees + "</p>");

                pw1.println("</div>");
                pw1.println("</div>");

                String approveButtonText = "Approve";
                boolean isApproved = "Yes".equalsIgnoreCase(approve);
                if (isApproved) {
                    approveButtonText = "Approved";
                }

                pw1.println("<form method=\"POST\" action=\"approveServlet\">");
                pw1.println("<button type=\"submit\" class=\"sub\" name=\"approve\" value=\"" + email + "\" " + (isApproved ? "disabled" : "") + ">" + approveButtonText + "</button>");
                pw1.println("</form>");

                pw1.println("</div>");
                pw1.println("</div>");
            }

            pw1.println("</div>");
            pw1.println("</body>");
            pw1.println("</html>");

            con.close();
        } catch (SQLException e) {
            pw1.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            pw1.println("General Error: " + e.getMessage());
        }
    }
}
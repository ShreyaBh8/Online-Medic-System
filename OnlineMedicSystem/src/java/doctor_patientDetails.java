import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class doctor_patientDetails extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw1 = res.getWriter();

        String add = req.getParameter("add").toUpperCase();
        String doc = req.getParameter("doc").toUpperCase();
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
            String q1 = "SELECT * FROM patientInfo WHERE UPPER(address) = '" + add + "' AND UPPER(doctor) = '" + doc + "' AND UPPER(booking) = 'CONFIRM'";
            String q2 = "SELECT * FROM patientInfo WHERE UPPER(address) = '" + add + "' AND UPPER(doctor) = '" + doc + "' AND UPPER(booking) = 'CANCEL'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(q1);
            Statement stmt1 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(q2);
            boolean found = false;
            boolean found1 = false;

            pw1.println("<html>");
            pw1.println("<head>");
            pw1.println("<link\n"
                    + "        href=\"https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap\"\n"
                    + "        rel=\"stylesheet\">");
            pw1.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css\"\n" +
"        integrity=\"sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==\"\n" +
"        crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />");
            pw1.println("<style>");
            pw1.println("body { background-color: #f0f0f0; font-family:poppins}");
            pw1.println("table { border-collapse: collapse; width: 80%; margin: auto; }");
            pw1.println("th, td { padding: 10px; border: 1px solid #ddd; text-align:center;}");
            pw1.println("th { background-color: #337ab7; color: #fff; }");
            pw1.println(".btn {background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 20px; cursor: pointer; text-decoration: none;}");
            pw1.println(".btn:hover {background-color: #0056b3;}");
            pw1.println("i {margin-left:10px; color: rgb(21, 183, 21);}");
            pw1.println(".cross {color: rgba(188, 25, 25, 0.897);}");
            pw1.println(".cancel {color: rgba(188, 25, 25, 0.897);}");
            pw1.println("</style>");
            pw1.println("</head>");
            pw1.println("<body>");

            while (rs.next()) {

                if (!found) {
                    pw1.println("<h2>Patient Information (" + add + ")</h2>");
                    pw1.println("<table border='1' cellpadding='5' cellspacing='0'>");
                    pw1.println("<tr>");
                    pw1.println("<th>Name</th>");
                    pw1.println("<th>Email Id</th>");
                    pw1.println("<th>Age</th>");
                    pw1.println("<th>Gender</th>");
                    pw1.println("<th>Address</th>");
                    pw1.println("<th>Contact</th>");
                    pw1.println("<th>Department</th>");
                    pw1.println("<th>Doctor</th>");
                    pw1.println("<th>Payment</th>");
                    pw1.println("<th>Appointment Day</th>");
                    pw1.println("<th>Booking</th>");
                    pw1.println("</tr>");
                }
                found = true;
                pw1.println("<tr>");
                pw1.println("<td>" + rs.getString(1) + "</td>");
                pw1.println("<td>" + rs.getString(2) + "</td>");
                pw1.println("<td>" + rs.getString(4) + "</td>");
                pw1.println("<td>" + rs.getString(5) + "</td>");
                pw1.println("<td>" + rs.getString(6) + "</td>");
                pw1.println("<td>" + rs.getString(7) + "</td>");
                pw1.println("<td>" + rs.getString(8) + "</td>");
                pw1.println("<td>" + rs.getString(9) + "</td>");
                pw1.println("<td>" + rs.getString(10) + "</td>");
                pw1.println("<td>" + rs.getString(12) + "</td>");
                pw1.println("<td>" + rs.getString(11) + "<i class=\"fa-solid fa-check\"></i>" + "</td>");

                pw1.println("</tr>");

            }
            pw1.println("</table>");

            while (rs1.next()) {
                if (!found1) {
                    pw1.println("<h2>Canceled patient Information (" + add + ")</h2>");
                    pw1.println("<table border='1' cellpadding='5' cellspacing='0'>");
                    pw1.println("<tr>");
                    pw1.println("<th>Name</th>");
                    pw1.println("<th>Email Id</th>");
                    pw1.println("<th>Age</th>");
                    pw1.println("<th>Gender</th>");
                    pw1.println("<th>Address</th>");
                    pw1.println("<th>Contact</th>");
                    pw1.println("<th>Department</th>");
                    pw1.println("<th>Doctor</th>");
                    pw1.println("<th>Payment</th>");
                    pw1.println("<th>Appointment Day</th>");
                    pw1.println("<th>Booking</th>");
                    pw1.println("</tr>");
                }
                found1 = true;
                pw1.println("<tr class=\"cancel\">");
                pw1.println("<td>" + rs1.getString(1) + "</td>");
                pw1.println("<td>" + rs1.getString(2) + "</td>");
                pw1.println("<td>" + rs1.getString(4) + "</td>");
                pw1.println("<td>" + rs1.getString(5) + "</td>");
                pw1.println("<td>" + rs1.getString(6) + "</td>");
                pw1.println("<td>" + rs1.getString(7) + "</td>");
                pw1.println("<td>" + rs1.getString(8) + "</td>");
                pw1.println("<td>" + rs1.getString(9) + "</td>");
                pw1.println("<td>" + rs1.getString(10) + "</td>");
                pw1.println("<td>" + rs1.getString(12) + "</td>");
                pw1.println("<td>" + rs1.getString(11) + "<i class=\"fa-solid fa-xmark cross\"></i>" + "</td>");
                pw1.println("</tr>");

            }
            pw1.println("</table>");
            if (found || found1) {
                pw1.println("<a href=\"Doctor/doctor_patient.html\" class=\"btn\"><< Back</a>");
            }
            
            if (!found && !found1) {
                pw1.println("<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "    <meta charset=\"UTF-8\">\n"
                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "    <title>Info not found</title>\n"
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
                        + "       \n"
                        + "        a {\n"
                        + "            text-decoration: none;\n"
                        + "            padding: 7px 20px;\n"
                        + "            border-radius: 20px;\n"
                        + "            background-color: #3278a3;\n"
                        + "            color: white;\n"
                        + "        }\n"
                        + "    </style>\n"
                        + "</head>\n"
                        + "\n"
                        + "<body>\n"
                        + "    <div class=\"container\">\n"
                        + "        <section class=\"about-us\">\n"
                        + "            <h1 style=\"color: #3278a3;\">No appointments booked!!</h1>  \n"
                        + "            <a href=\"doctor.html\"class=\"btn\"><< Back</a>\n"
                        + "        </section>\n"
                        + "    </div>\n"
                        + "</body>\n"
                        + "\n"
                        + "</html>");
            }
            
            rs.close();
            rs1.close();
            stmt.close();
            stmt1.close();
            con.close();
        } catch (Exception e) {
            pw1.println("Error: " + e.getMessage());
        }
    }
}

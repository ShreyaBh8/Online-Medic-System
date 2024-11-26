
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class account extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw1 = res.getWriter();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            String doctorQuery = "SELECT name, email, fees, gender FROM doctorInfo2";

            PreparedStatement pstmt = con.prepareStatement(doctorQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet doctorRs = pstmt.executeQuery();
            String patientQuery = "SELECT doctor, COUNT(*) AS patient_count "
                    + "FROM patientInfo "
                    + "WHERE booking = 'confirm' "
                    + "GROUP BY doctor "
                    + "ORDER BY doctor";
            PreparedStatement pstmt1 = con.prepareStatement(patientQuery);
            ResultSet patientRs = pstmt1.executeQuery();

            pw1.println("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Online Medical System - Patient List</title>\n"
                    + "    <style>\n"
                    + "        /* General Reset */\n"
                    + "        * {\n"
                    + "            margin: 0;\n"
                    + "            padding: 0;\n"
                    + "            box-sizing: border-box;\n"
                    + "        }\n"
                    + "        /* Container and Basic Layout */\n"
                    + "        body {\n"
                    + "            font-family: Arial, sans-serif;\n"
                    + "            background-color: #f4f6f8;\n"
                    + "            display: flex;\n"
                    + "            justify-content: center;\n"
                    + "            padding: 20px;\n"
                    + "            color: #333;\n"
                    + "        }\n"
                    + "        .container {\n"
                    + "            max-width: 1400px;\n"
                    + "            width: 100%;\n"
                    + "            background-color: #fff;\n"
                    + "            padding: 20px;\n"
                    + "            border-radius: 10px;\n"
                    + "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);\n"
                    + "            overflow: hidden;\n"
                    + "        }\n"
                    + "        /* Header Section */\n"
                    + "        header {\n"
                    + "            text-align: center;\n"
                    + "            margin-bottom: 30px;\n"
                    + "        }\n"
                    + "        header h1 {\n"
                    + "            font-size: 32px;\n"
                    + "            color: #007bff;\n"
                    + "        }\n"
                    + "        header p {\n"
                    + "            font-size: 16px;\n"
                    + "            color: #666;\n"
                    + "        }\n"
                    + "        .patient-section h2 {\n"
                    + "            font-size: 24px;\n"
                    + "            color: #333;\n"
                    + "            border-bottom: 2px solid #007bff;\n"
                    + "            padding-bottom: 10px;\n"
                    + "            margin-bottom: 20px;\n"
                    + "        }\n"
                    + "        .doctor-row {\n"
                    + "            display: flex;\n"
                    + "            gap: 20px;\n"
                    + "            flex-wrap: wrap;\n"
                    + "        }\n"
                    + "        .patient-card {\n"
                    + "            background-color: #e0f7fa;\n"
                    + "            border: 1px solid #b3e5fc;\n"
                    + "            border-radius: 10px;\n"
                    + "            padding: 20px;\n"
                    + "            width: 300px;\n"
                    + "            flex-shrink: 0;\n"
                    + "            display: flex;\n"
                    + "            flex-direction: column;\n"
                    + "            align-items: flex-start;\n"
                    + "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
                    + "        }\n"
                    + "        .patient-card h3 {\n"
                    + "            font-size: 20px;\n"
                    + "            color: #007bff;\n"
                    + "            margin-bottom: 10px;\n"
                    + "        }\n"
                    + "        .patient-info {\n"
                    + "            font-size: 14px;\n"
                    + "            color: #333;\n"
                    + "        }\n"
                    + "        .patient-info p {\n"
                    + "            margin: 5px 0;\n"
                    + "        }\n"
                    + "strong{color:#007bff;}"
                    + ".container1{display:flex; gap:30px;}"
                    + "        @media screen and (max-width: 768px) {\n"
                    + "            .doctor-row {\n"
                    + "                flex-direction: column;\n"
                    + "            }\n"
                    + "            .patient-card {\n"
                    + "                width: 100%;\n"
                    + "            }\n"
                    + "        }\n"
                    + "    </style>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "    <div class=\"container\">\n"
                    + "        <header>\n"
                    + "            <h1>Accounts Summary</h1>\n"
                    + "            <p>Commission Details from Doctors</p>\n"
                    + "        </header>\n"
                    + "        <div class=\"patient-section\">\n"
                    + "        <h2>Doctors Commissions</h2>\n"
                    + "<div class=\"container1\">\n");

      
            while (patientRs.next()) {
                String doctorName = patientRs.getString("doctor");
                int patientCount = patientRs.getInt("patient_count");

               
                doctorRs.beforeFirst();

                
                while (doctorRs.next()) {
                    String doctor = doctorRs.getString("name");
                    double doctorFees = doctorRs.getDouble("fees");  
                    double totalFees = doctorFees * patientCount;
                    double commission = totalFees * 0.05;
                    if (doctor.equals(doctorName)) {
                        
                        pw1.println("<div class=\"doctor-row\">");
                        pw1.println("    <div class=\"patient-card\">");
                        pw1.println("        <div class=\"patient-info\">");
                        pw1.println("            <p><strong>Doctor:</strong> " + doctor + "</p>");
                        pw1.println("            <p><strong>Total Fees recieved:</strong> ₹" + totalFees + "</p>");
                        pw1.println("            <p><strong>Commission Rate:</strong> 5%</p>");
                        pw1.println("            <p><strong>Commisson Earned:</strong> ₹" + commission + "</p>");
                        pw1.println("        </div>");
                        pw1.println("    </div>");
                        pw1.println("</div>");
                    }
                }
            }
            pw1.println("        </div>");
            pw1.println("        </div>");
            pw1.println("    </div>");
            pw1.println("</body>\n</html>");

            con.close();
        } catch (SQLException e) {
            pw1.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            pw1.println("Error: " + e.getMessage());
        }
    }
}

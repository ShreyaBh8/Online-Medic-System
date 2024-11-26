import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class adminPatient extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw1 = res.getWriter();

        try {
             Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            String query = "SELECT name, e_mail, password, age, gender, address, contact, department, doctor, payment, booking, appointment_day FROM patientInfo";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            pw1.println("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "    <head>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <title>Online Medical System - Patient List</title>\n"
                    + "        <style>\n"
                    + "            /* General Reset */\n"
                    + "            * {\n"
                    + "                margin: 0;\n"
                    + "                padding: 0;\n"
                    + "                box-sizing: border-box;\n"
                    + "            }\n"
                    + "\n"
                    + "            /* Container and Basic Layout */\n"
                    + "            body {\n"
                    + "                font-family: Arial, sans-serif;\n"
                    + "                background-color: #f4f6f8;\n"
                    + "                display: flex;\n"
                    + "                justify-content: center;\n"
                    + "                padding: 20px;\n"
                    + "                color: #333;\n"
                    + "            }\n"
                    + "\n"
                    + "            .container {\n"
                    + "                max-width: 1400px;\n"
                    + "                width: 100%;\n"
                    + "                background-color: #fff;\n"
                    + "                padding: 10px;\n"
                    + "                border-radius: 10px;\n"
                    + "                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);\n"
                    + "                overflow: hidden;\n"
                    + "            }\n"
                    + "\n"
                    + "            /* Header Section */\n"
                    + "            header {\n"
                    + "                text-align: center;\n"
                    + "                margin-bottom: 30px;\n"
                    + "            }\n"
                    + "\n"
                    + "            header h1 {\n"
                    + "                font-size: 32px;\n"
                    + "                color: #007bff;\n"
                    + "            }\n"
                    + "\n"
                    + "            header p {\n"
                    + "                font-size: 16px;\n"
                    + "                color: #666;\n"
                    + "            }\n"
                    + "\n"
                    + "            /* Patient Section Titles */\n"
                    + "            .patient-section {\n"
                    + "                margin-top: 30px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .patient-section h2 {\n"
                    + "                font-size: 24px;\n"
                    + "                color: #333;\n"
                    + "                border-bottom: 2px solid #007bff;\n"
                    + "                padding-bottom: 10px;\n"
                    + "                margin-bottom: 20px;\n"
                    + "            }\n"
                    + "\n"
                    + "            /* Patient Row */\n"
                    + "            .doctor-row {\n"
                    + "                display: flex;\n"
                    + "                gap: 20px;\n"
                    + "                overflow-x: auto; /* Enable horizontal scrolling */\n"
                    + "                padding-bottom: 10px;\n"
                    + "                padding-top: 10px;\n"
                    + "                scrollbar-width: none;\n"
                    + "            }\n"
                    + "\n"
                    + "            .doctor-row::-webkit-scrollbar {\n"
                    + "                display: none;\n"
                    + "            }\n"
                    + "\n"
                    + "            .doctor-row::-webkit-scrollbar-thumb {\n"
                    + "                background-color: #007bff;\n"
                    + "                border-radius: 10px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .doctor-row::-webkit-scrollbar-track {\n"
                    + "                background-color: #e0f7fa;\n"
                    + "            }\n"
                    + "\n"
                    + "            /* Patient Card */\n"
                    + "            .patient-card {\n"
                    + "                background-color: #e0f7fa; /* Light blue background for each patient */\n"
                    + "                border: 1px solid #b3e5fc;\n"
                    + "                border-radius: 10px;\n"
                    + "                padding: 20px;\n"
                    + "                width: 300px; /* Fixed width for all cards */\n"
                    + "                flex-shrink: 0; /* Prevent cards from shrinking */\n"
                    + "                display: flex;\n"
                    + "                flex-direction: column;\n"
                    + "                align-items: flex-start;\n"
                    + "                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
                    + "                transition: transform 0.2s ease;\n"
                    + "            }\n"
                    + "\n"
                    + "            .patient-card:hover {\n"
                    + "                transform: translateY(-5px);\n"
                    + "            }\n"
                    + "\n"
                    + "            .patient-card h3 {\n"
                    + "                font-size: 20px;\n"
                    + "                color: #007bff;\n"
                    + "                margin-bottom: 10px;\n"
                    + "            }\n"
                    + "\n"
                    + "            .patient-info {\n"
                    + "                font-size: 14px;\n"
                    + "                color: #333;\n"
                    + "            }\n"
                    + "\n"
                    + "            .patient-info p {\n"
                    + "                margin: 5px 0;\n"
                    + "            }\n"
                    + "\n"
                    + "            /* Responsive Design for smaller screens */\n"
                    + "            @media screen and (max-width: 768px) {\n"
                    + "                .doctor-row {\n"
                    + "                    flex-direction: column; /* Stack doctor cards vertically on smaller screens */\n"
                    + "                }\n"
                    + "\n"
                    + "                .patient-card {\n"
                    + "                    width: 100%; /* Full width on smaller screens */\n"
                    + "                }\n"
                    + "            }\n"
                    + "\n"
                    + "        </style>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <div class=\"container\">\n"
                    + "            <!-- Header Section -->\n"
                    + "            <header>\n"
                    + "                <h1>Patients Appointed to Doctors</h1>\n"
                    + "                <p>View details of patients under each doctor's care, including appointment information and patient details.</p>\n"
                    + "            </header>");

            String currentDoctor = null;
            String currentDepartment = null;
            boolean isFirstGroup = true;

            while (rs.next()) {
                String doctor = rs.getString("doctor");
                String department = rs.getString("department");

                if (!doctor.equals(currentDoctor) || !department.equals(currentDepartment)) {
                    if (!isFirstGroup) {
                        pw1.println("        </div>"); 
                        pw1.println("    </div>");
                        pw1.println("<hr>");
                    }

                    pw1.println("<div class=\"doctor-container\">");
                    pw1.println("    <h2>" + doctor + " - " + department + "</h2>");
                    pw1.println("    <div class=\"doctor-row\">");

                    currentDoctor = doctor;
                    currentDepartment = department;
                    isFirstGroup = false;
                }

                pw1.println("        <div class=\"patient-card\">");
                pw1.println("            <h3>" + rs.getString("name") + "</h3>");
                pw1.println("            <div class=\"patient-info\">");
                pw1.println("                <p><strong>Email:</strong> " + rs.getString("e_mail") + "</p>");
                pw1.println("                <p><strong>Age:</strong> " + rs.getString("age") + "</p>");
                pw1.println("                <p><strong>Gender:</strong> " + rs.getString("gender") + "</p>");
                pw1.println("                <p><strong>Address:</strong> " + rs.getString("address") + "</p>");
                pw1.println("                <p><strong>Contact:</strong> " + rs.getString("contact") + "</p>");
                pw1.println("                <p><strong>Fees Paid:</strong> " + rs.getString("payment") + "</p>");
                pw1.println("                <p><strong>Booking:</strong> " + rs.getString("booking") + "</p>");
                pw1.println("                <p><strong>Appointment Day:</strong> " + rs.getString("appointment_day") + "</p>");
                pw1.println("            </div>");
                pw1.println("        </div>");
            }

            if (!isFirstGroup) {
                pw1.println("        </div>"); 
                pw1.println("    </div>"); 
            }

            pw1.println("</body>\n</html>");

            con.close();
        } catch (SQLException e) {
            pw1.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            pw1.println("");
        }
    }
}
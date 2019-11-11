import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;

public class DoctorSpec extends JPanel{

	// this should not run again 
	public DoctorSpec()
	{

        
 	   try {
 		
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create database if not exists ManagementDb");
			stmt.executeUpdate("Use ManagementDb");
			stmt.execute("create table if not exists  DoctorspecTb(specilisation varchar(150) primary key)");
			  String s[] = {"Addiction psychiatrist",
					 "Adolescent medicine specialist",
					  "Allergist (immunologist)",
					  "Anesthesiologist",
					  "Cardiac electrophysiologist",
					  "Cardiologist",
					  "Cardiovascular surgeon",
					  "Colon and rectal surgeon",
					  "Critical care medicine specialist",
					  "Dermatologist",
					  "Developmental pediatrician",
					  "Emergency medicine specialist",
					  "Endocrinologist",
					  "Family medicine physician",
					  "Forensic pathologist",
					  "Gastroenterologist",
					  "Geriatric medicine specialist",
					  "Gynecologist",
					  "Gynecologic oncologist",
					  "Hand surgeon",
					  "Hematologist",
					  "Hepatologist",
					  "Hospitalist",
					  "Hospice and palliative medicine specialist",
					  "Hyperbaric physician",
					  "Infectious disease specialist",
					  "Internist",
					  "Interventional cardiologist",
					  "Medical examiner",
					  "Medical geneticist",
					  "Neonatologist",
					  "Nephrologist",
					  "Neurological surgeon",
					  "Neurologist",
					  "Nuclear medicine specialist",
					  "Obstetrician",
					  "Occupational medicine specialist",
					  "Oncologist",
					  "Ophthalmologist",
					  "Oral surgeon",
					  "Orthopedic surgeon",
					  "Otolaryngologist (ENT specialist)",
					  "Pain management specialist",
					  "Pathologist",
					  "Pediatrician",
					  "Perinatologist",
					  "Physiatrist",
					  "Plastic surgeon",
					  "Psychiatrist",
					  "Pulmonologist",
					  "Radiation oncologist",
					  "Radiologist",
					  "Reproductive endocrinologist",
					  "Rheumatologist",
					  "Sleep disorders specialist",
					  "Spinal cord injury specialist",
					  "Sports medicine specialist",
					  "Surgeon",
					  "Thoracic surgeon",
					  "Urologist",
					  "Vascular surgeon"};
			for(int i=0;i<s.length;i++)
					{
						PreparedStatement pre = con.prepareStatement("insert into DoctorspecTb(specilisation) values(?)");
						pre.setString(1,s[i].toString());			
						pre.executeUpdate();
						System.out.println(s[i]);
					}
			
       	 
					con.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
		
	}
}

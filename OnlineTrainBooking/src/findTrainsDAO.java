
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class findTrainsDAO {
	public String getTrains(String a, String b) {

		String s1 = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(
					"select trn_name,trn_no from gv_trains where trn_start='" + a + "' and trn_end='" + b + "';");
			while (rs.next()) {
				s1 += "<option value=" + rs.getString(2) + ">" + rs.getString(1) + "(" + rs.getString(2) + ")"
						+ "</option>";

			}
			rs.close();
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s1;
	}

}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class classesDAO {
	public String getClasses(String a) {

		String s1 = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select trn_travelclass from gv_traintravelClasses where trn_no=" + a);
			while (rs.next()) {
				s1 += "<option value=" + rs.getString(1) + ">" + rs.getString(1) + "</option>";
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

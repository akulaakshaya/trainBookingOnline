
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainDAO {
	public String TrainOrigins() {
		List<String> origins = new ArrayList<>();
		String s1 = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(
					"select distinct stn_code,stn_name from (select trn_start from gv_trains union all select trn_end from gv_trains) as st,gv_stations as gv where gv.stn_code=st.trn_start  ");
			while (rs.next()) {
				s1 += "<option value=" + rs.getString(1) + ">" + rs.getString(2) + "(" + rs.getString(1) + ")"
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

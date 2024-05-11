package AtmSimulator;
import java.sql.*;

public class conn {
	Connection C;
	Statement s, s1, s2, s3;
	public conn()
	{
		try
		{
			String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=AccountInfo;encrypt=true;trustServerCertificate=true;"+"";
			C = DriverManager.getConnection(url,"admin", "123");
			s = C.createStatement();
			s1= C.createStatement();
			s3= C.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			s2= C.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			
			
		}
		catch (Exception e)
		{
		    
			System.out.println(e);
		}
	}
    
}

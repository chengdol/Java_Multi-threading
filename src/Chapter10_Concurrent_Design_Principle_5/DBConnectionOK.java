package Chapter10_Concurrent_Design_Principle_5;

public class DBConnectionOK
{
	// why wrap class outside?
	private static class DBConnectionLazy
	{
		private static final DBConnectionOK INSTANCE = new DBConnectionOK();
	}
	
	private DBConnectionOK()
	{
		System.out.printf("%s, connection object created\n"
				, Thread.currentThread().getName());
	}
	
	
	public static DBConnectionOK getConnection()
	{
		return DBConnectionLazy.INSTANCE;
	}
}

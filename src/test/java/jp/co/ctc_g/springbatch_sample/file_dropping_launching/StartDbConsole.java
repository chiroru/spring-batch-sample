package jp.co.ctc_g.springbatch_sample.file_dropping_launching;

import java.sql.SQLException;

import org.h2.tools.Console;

/**
 * @author acogoluegnes
 *
 */
public class StartDbConsole {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		Console.main("-web","-browser");
	}

}

package eu.bankersen.kevin.ql;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;

import com.esotericsoftware.minlog.Log.Logger;

public class CustomLogger extends Logger {

    private PrintWriter writer;

    public CustomLogger() {
	try {
	    writer = new PrintWriter(Paths.get("Log.txt").toString(), "UTF-8");
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /*
     * Custom Log Handler Default behavior prints to system.out. We need to save
     * our log-files to track error-messages.
     */
    @Override
    protected final void print(final String message) {
	writer.println(message);
	writer.flush();
	System.out.println(message);
    }

}

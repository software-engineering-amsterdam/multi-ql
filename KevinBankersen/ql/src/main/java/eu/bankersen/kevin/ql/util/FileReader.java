package eu.bankersen.kevin.ql.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.esotericsoftware.minlog.Log;

public class FileReader {

    public final String read(final String filePath) throws IOException {

	BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));

	Log.info("Reading " + filePath.toString());

	StringBuilder out = new StringBuilder();
	String line;

	while ((line = reader.readLine()) != null) {
	    out.append(line + "\n");
	}

	reader.close();

	Log.debug(out.toString());

	return out.toString();
    }

}

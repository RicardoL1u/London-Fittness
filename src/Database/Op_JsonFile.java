package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Op_JsonFile {
	public String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            //return null;
            return "";
        }
    }
	public void writeJsonFile(String fileName, String JsonString) {
		try {

            File filewrite = new File(fileName);
            if (!filewrite.getParentFile().exists()) {
            	filewrite.getParentFile().mkdirs();
            }
            if (filewrite.exists()) {
            	filewrite.delete();
            }
            filewrite.createNewFile();


            Writer write = new OutputStreamWriter(new FileOutputStream(filewrite), "UTF-8");
            write.write(JsonString);
            write.flush();
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

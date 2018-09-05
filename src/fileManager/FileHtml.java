package fileManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FileHtml extends Files {
Map<String, Integer> dict = new HashMap<String, Integer>();

	public FileHtml(String fileName) {
		super.readFile(fileName);
	}
	@Override
	public Map<String, Integer> parse() {
		try {
			FileReader fr = new FileReader(super.getName());
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			String text = br.readLine();
			while((line = br.readLine()) != null) {
				text = text + line;
			}
			Document doc = Jsoup.parse(text);
			String html = doc.text();
			dict = super.filDict(html);
			br.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + super.getName() + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + super.getName() + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		return dict;
	}
	public String getName() {
		return super.getName();
	}
	public String getPath() {
		return super.getPath();
	}

}

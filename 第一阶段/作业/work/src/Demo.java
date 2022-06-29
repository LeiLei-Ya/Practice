import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Demo {
	public static void main(String[] args) throws IOException {
		Document document = Jsoup.connect("https://www.baidu.com").get();
		System.out.println(document);	
	}

}

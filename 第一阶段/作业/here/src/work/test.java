package work;

import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		//语法
		//alt + / 语法提示
		
		//发送一个get类型的请求，并将结果转换成Docment对象
		Document document = Jsoup.connect("https://pvp.qq.com/web201605/herolist.shtml").get();
		
		//从docment对象中获取class名为herolist的标签集合
		Element element = document.getElementsByClass("herolist").get(0);
		
		//从element对象中获取标签名为img的标签集合
		Elements tags = element.getElementsByTag("img");
		
		//循环img标签集合,element2就是每次循环出来的img标签对象
		for (Element element2 : tags) {
			
			//获取element2标签的src属性和alt属性值
			String src = element2.attr("src");
			String name = element2.attr("alt");
			
			System.out.println("正在下载——" + name);
			
			//再发请求获取头像信息,使用execute，因为这个对象才能处理文件
			Response response = Jsoup.connect("https:" + src).ignoreContentType(true).execute();
			
			byte[] data = response.bodyAsBytes();
			
			//使用IO流将byte[]转成具体文件
			FileOutputStream fileOutputStream = new FileOutputStream("D:/wangzhe/" + name + ".jpg");
			fileOutputStream.write(data);
			fileOutputStream.close();
		}
	}
}

package com.houpu;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Demo {

	public static void main(String[] args) throws IOException {
		//向www.baidu.com发起了请求，并返回Docment对象
		Document document = Jsoup.connect("https://www.baidu.com").get();
		System.out.println(document);
		
		
	}

}

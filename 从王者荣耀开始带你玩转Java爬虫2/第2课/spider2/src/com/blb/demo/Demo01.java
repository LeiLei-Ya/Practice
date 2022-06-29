package com.blb.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class Demo01 {
	
	//用来装cookies
	private static Map<String, String> cookies = new HashMap<String, String>();

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		//访问首页，用于获取cookies
		Response response = Jsoup.connect("https://www.douban.com/").execute();
		cookies.putAll(response.cookies());
		
		//登录
		Map<String, String> data = new HashMap<String, String>();
		data.put("ck", "");
		data.put("remember", "true");
		data.put("name", "3094759846@qq.com");
		data.put("password", "520xiao707789");
		
		response = Jsoup.connect("https://accounts.douban.com/j/mobile/login/basic")
				.method(Method.POST)		//设置当前请求类型为post请求
				.ignoreContentType(true)	//忽略内容类型错误
				.data(data)					//设置请求参数
				.cookies(cookies)			//设置cookies				
				.execute();
		cookies.putAll(response.cookies());

		//如果响应的内容中包含用户名，表示登录成功
		if (response.body().contains("默非默")) {
			System.out.println("登录成功");
		}else {
			System.out.println("登录失败");
		}
		
		
		//获取cookie
		response = Jsoup.connect("https://www.douban.com/").method(Method.GET).cookies(cookies).execute();
		cookies.putAll(response.cookies());
		
		//发表说说
		System.out.println("输入要发表的说说：");
		Scanner scanner = new Scanner(System.in);
		String msg = scanner.next();
		
		data.clear();	//清空
		data.put("ck", cookies.get("ck"));
		data.put("comment", msg);
		data.put("privacy_and_reply_limit", "P,");
		
		
		Jsoup.connect("https://www.douban.com/")
			.method(Method.POST)		//设置当前请求类型为post请求
			.ignoreContentType(true)	//忽略内容类型错误
			.data(data)					//设置请求参数
			.cookies(cookies)			//设置cookies				
			.execute();
		
		
		
	}
}

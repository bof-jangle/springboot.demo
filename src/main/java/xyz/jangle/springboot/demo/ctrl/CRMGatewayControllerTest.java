package xyz.jangle.springboot.demo.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 测试代码，
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年7月8日 上午10:50:13
 * 
 */
@RestController
@RequestMapping("/CRMGateway")
public class CRMGatewayControllerTest {
	// 网关地址
	public static String netUrl = "http://127.0.0.1/CRMGateway/CRMAction";
	// 网关的方法CODE (服务名)
	public static String CRMAction = "CRMAction";
	// token
	public static String token = "token";

	@RequestMapping("/test")
	public String trans(HttpServletRequest request) {
		System.out.println("2收到请求");
		String result; // 返回给请求的字符串数据
		// 给CRM的请求参数
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		// 获取请求信息、转换直接参数体
		Map<String, String[]> paramMap = request.getParameterMap();
		// 将JSON参数解析给网关
		for (String key : paramMap.keySet()) {
//			parameters.add(new BasicNameValuePair(key, paramMap.get(key)[0]));
			for(String value:paramMap.get(key)) {
				parameters.add(new BasicNameValuePair(key, value));
				System.out.println("key:"+key+",value:"+value);
			}
		}
		// 获取header中的CRMAction类型转发给网关
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put(CRMAction, request.getHeader(CRMAction));
		headerMap.put(token, request.getHeader(token));
		try {
			// 调用网关
			result = httpPost(netUrl, parameters, headerMap);
		} catch (ClientProtocolException e) {
			result = "请求网关时异常";
			System.out.println(result);
		} catch (IOException e) {
			result = "请求网关时IO异常";
			System.out.println(result);
		}
		return result;
	}

	/**
	 * httpClient
	 * 
	 * @author jangle
	 * @time 2020年7月8日 上午11:44:12
	 * @param url        网关地址
	 * @param parameters 参数
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String httpPost(String url, List<NameValuePair> parameters, Map<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPostRequest = new HttpPost(url);
		// 添加请求头
		for (String key : headerMap.keySet()) {
			httpPostRequest.addHeader(key, headerMap.get(key));
		}
		// 添加参数列表 存入参数
		HttpEntity entity = new UrlEncodedFormEntity(parameters, "UTF-8");
		httpPostRequest.setEntity(entity);
		CloseableHttpResponse response = client.execute(httpPostRequest);
//		System.out.println(response.getStatusLine()); // 响应状态
		// 响应实体
		HttpEntity responseEntity = response.getEntity();
		// 字符串实体
		String string = EntityUtils.toString(responseEntity);
//		System.out.println(string);
		client.close();
		response.close();
		return string;
	}
	/*
	 * 旧方案备份
	 * 	Object htmlParam = request.getAttribute("param"); // 参数体 JSON
		if(htmlParam != null) {
			// param的JSON参数体
			@SuppressWarnings("unchecked")
			Map<String, String> param = (Map<String, String>) htmlParam;
			for (String key : param.keySet()) {
				parameters.add(new BasicNameValuePair(key, param.get(key))); // 将JSON参数解析给网关
			}
		} else {
		} 
	 */
}

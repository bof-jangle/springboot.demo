package xyz.jangle.springboot.demo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.jangle.springboot.demo.service.AService;

@RestController
@RequestMapping("/testCtrl")
public class TestCtrl {

	@Autowired
	private AService aService;

	@RequestMapping("/hello.ctrl")
	public String hello() {
		System.out.println("just so so ...");
		aService.testMethod("这是方法的输出");
		return "hello";
	}

	public static void main(String[] args) {
//		Workbook

	}

}

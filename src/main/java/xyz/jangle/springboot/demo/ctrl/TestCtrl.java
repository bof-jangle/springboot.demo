package xyz.jangle.springboot.demo.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testCtrl")
public class TestCtrl {

	@RequestMapping("/hello.ctrl")
	public String hello() {
		System.out.println("just so so ...");
		return "hello";
	}
}

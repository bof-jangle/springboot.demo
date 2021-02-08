package xyz.jangle.springboot.demo.service.impl;

import org.springframework.stereotype.Service;

import xyz.jangle.springboot.demo.service.AService;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2021年2月8日 下午4:49:06
 * 
 */
@Service
public class AServiceImpl implements AService {

	@Override
	public void testMethod(String str) {

		System.out.println(str);

	}

}

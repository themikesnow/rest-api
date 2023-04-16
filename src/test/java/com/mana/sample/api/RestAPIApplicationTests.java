package com.mana.sample.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestAPIApplication.class)
public class RestAPIApplicationTests {

	@Test
	public void test() {
		RestAPIApplication.main(new String[] {});
	}
}

package com.example.worknet;

import com.example.worknet.core.utils.string.RandomString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class worknetTests {

	@Test
	public void contextLoads() {
		System.out.println(RandomString.getRandomString(32));
	}

}

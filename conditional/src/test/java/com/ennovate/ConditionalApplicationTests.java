package com.ennovate;

import com.ennovate.service.ConditionalBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConditionalApplicationTests {

	@Autowired
	Environment environment;

	@Autowired
	ConditionalBean conditionalBean;

	@Test
	public void generatesDefaultConditionalBean_whenNoProfileIsActive() {

		String[] activeProfiles = environment.getActiveProfiles();
		assertThat(activeProfiles.length, is(0));
		assertThat(conditionalBean.getValue(), equalTo("Default"));

	}

}

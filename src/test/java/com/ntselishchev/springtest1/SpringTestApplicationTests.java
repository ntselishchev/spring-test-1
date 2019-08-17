package com.ntselishchev.springtest1;

import com.ntselishchev.springapptest.SpringTestApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringTestApplication.class, SpringTestApplicationTests.class})
@EnableConfigurationProperties
public class SpringTestApplicationTests {

}

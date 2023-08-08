package io.java.learning.rmi.test;

import io.java.learning.ForestApiMain;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForestApiMain.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void testHello(){
        String res = template.getForEntity("http://localhost:8081/demo/hello", String.class).getBody();
        System.out.println(res);
    }

}

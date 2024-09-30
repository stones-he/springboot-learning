package io.java.learning;

import io.java.learning.reactor.pojo.TUser;
import io.java.learning.reactor.repositories.UserRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        ConnectionFactory connectionFactory = context.getBean(ConnectionFactory.class);

        R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);
        template.getDatabaseClient().sql("CREATE TABLE T_User" +
                "(Tid VARCHAR(255) PRIMARY KEY," +
                "name VARCHAR(255)," +
                "age INT)").then().subscribe(System.out::println);
        Mono<TUser> userMono = template.insert(TUser.class)
                .using(new TUser("joe", "Joe", 34));
        userMono.subscribe(System.out::println);
        Flux.range(1, 50).subscribe(i -> {
            template.insert(TUser.class).into("T_User").using(new TUser("joe"+i, "Joe"+i, 35+i)).subscribe();
        });
        //
        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.findByEmailAddress("Joe").subscribe(System.out::println);
        //
        Flux.from(userRepository.findAll()).subscribe(System.out::println);
    }

    public static void main0(String[] args) {
        System.out.println("Hello world!");
        try {
            DelayQueue<MyDelayObject> delayQueue = new DelayQueue<MyDelayObject>();
            delayQueue.put(new MyDelayObject("A", 1000));
            delayQueue.put(new MyDelayObject("B", 2000));
            delayQueue.put(new MyDelayObject("C", 30000));
            delayQueue.forEach(System.out::println);
            while (!delayQueue.isEmpty()) {
                MyDelayObject obj = delayQueue.take();
                System.out.println("Retrieved: " + obj.getName() + ": time:" + System.currentTimeMillis());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class MyDelayObject implements Delayed {
        private String name;
        private long delay;

        public MyDelayObject(String name, long delay) {
            this.name = name;
            this.delay = System.currentTimeMillis() + delay;
            ;
        }

        public String getName() {
            return name;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "MyDelayObject{" + "name='" + name + '\'' + ", delay=" + delay + '}';
        }
    }
}
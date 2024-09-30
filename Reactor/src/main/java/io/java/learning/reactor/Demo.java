package io.java.learning.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

public class Demo {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("hello world", "abc").map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        });
        System.out.println("starting...");
        flux.subscribe(System.out::println);
//        System.out.println("hello world");
        Flux.just("a", "b", "c").error(new RuntimeException()).doOnError(System.out::println).onErrorResume((e) -> {
            e.printStackTrace();
            return Flux.just("E");
        }).subscribe(System.out::println);
        //
        Flux.interval(Duration.ofSeconds(1)).subscribe(System.out::println);
        //
        Flux.generate(AtomicLong::new, (stat, sink) -> {
            long i = stat.getAndIncrement();
            sink.next(i);
            if (i == 10) sink.complete();
            return stat;
        }, stat -> System.out.println("Flux::generate::state->$stat," + stat)).subscribe(System.out::println);
        //
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next("Flux::Create::Int->"+ i);
            }
            sink.complete();
        }).subscribe(System.out::println);
        //
        Flux.create(sink -> {
            sink.next("hello world");
            System.out.println("Flux::Create::"+Thread.currentThread());
//            sink.complete();
        }).subscribe(System.out::println);

        //
        Thread t = new Thread(() -> {
            while (true) try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        t.start();
    }
}

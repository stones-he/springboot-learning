package io.java.learning.config;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class DemoShutdownLifecycle implements SmartLifecycle {
    private volatile boolean running = false;

    @Override
    public void start() {
        running = true;
    }

    @Override
    public void stop() {
        System.out.println("DemoShutdownLifecycle.stop()");
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void stop(Runnable callback) {
        SmartLifecycle.super.stop(callback);
    }

    @Override
    public int getPhase() {
        return SmartLifecycle.DEFAULT_PHASE;
    }
}

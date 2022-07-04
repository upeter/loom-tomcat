package executor;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;

public class VirtualThreadExecutor
        extends org.apache.catalina.core.StandardThreadExecutor {

    private ExecutorService exec = Executors.newThreadPerTaskExecutor(
            Thread.ofVirtual()
                    .name("virtual-factory-", 1)
                    .factory());

    public void execute(Runnable command) {
        //System.out.println("Virtual");
        exec.submit(command);
    }

    public void execute(Runnable command, long timeout, java.util.concurrent.TimeUnit unit) {
        execute(command);
    }

    @Override
    public int getMaxThreads() {
        return Integer.MAX_VALUE;
    }


    public String getNamePrefix() { return "virtual-" + super.getNamePrefix(); }

}



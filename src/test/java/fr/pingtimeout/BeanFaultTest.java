package fr.pingtimeout;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class BeanFaultTest {
    @Test
    public void bean_fault_should_kill_a_process_by_id() throws Exception {
        // Given
        Process sleepProcess = new ProcessBuilder("sleep", "1m").start();
        BeanFault beanFault = new BeanFault();

        // When
        beanFault.kill(getPid(sleepProcess));

        // Then
        boolean sleepWasKilled = sleepProcess.waitFor(10, MILLISECONDS);
        assertThat(sleepWasKilled).isTrue();
    }

    @Test
    @Ignore("This test kills the current JVM, which is JUnit, it cannot succeed")
    public void bean_fault_should_self_destruct() throws Exception {
        new BeanFault().segfault();
    }

    public static String getPid(Process process) {
        try {
            Class<?> processClass = process.getClass();
            Field pidField = processClass.getDeclaredField("pid");
            pidField.setAccessible(true);
            return String.valueOf(pidField.getInt(process));
        } catch (Exception e) {
            throw new IllegalStateException("Cannot get PID from " + process);
        }
    }
}
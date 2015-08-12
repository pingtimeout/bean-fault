/**
 * This file is part of BeanFault.
 *
 * BeanFault is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BeanFault is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BeanFault.  If not, see <http://www.gnu.org/licenses/>.
 */
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
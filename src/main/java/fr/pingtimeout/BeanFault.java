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

import java.io.IOException;

import com.jezhumble.javasysmon.JavaSysMon;

public class BeanFault {
    /**
     * Print "Segmentation fault" and then kill the JVM.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void segfault() throws IOException, InterruptedException {
        System.out.println("FATAL: BeanFault called to kill the current JVM");
        System.out.println("Segmentation fault");
        String pid = String.valueOf(new JavaSysMon().currentPid());
        kill(pid);
    }

    void kill(String pid) throws InterruptedException, IOException {
        new ProcessBuilder(killCommand(pid))
            .start()
            .waitFor();
    }

    String[] killCommand(String pid) {
        String[] command;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            // Windows
            command = new String[]{ "taskkill", "/PID", pid, "/F", "/T" };
        } else {
            // Unix
            command = new String[]{ "kill", "-9", pid };
        }
        return command;
    }
}

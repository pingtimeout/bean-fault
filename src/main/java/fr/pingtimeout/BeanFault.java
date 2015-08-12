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

package unix_playground.shellScriptExecution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UsingRuntime {

    private static final String shellScriptFilePath = System.getProperty("user.dir")+"/src/test/java/Resources/unixResources/simpleShellScript.sh";

    public static void main(String[] args) {
        try {
            // Specify the path to your shell script

            // Create the runtime object
            Runtime runtime = Runtime.getRuntime();

            // Execute the script
            Process process = runtime.exec(new String[]{"/bin/bash", "-c", shellScriptFilePath});

            // Capture the output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

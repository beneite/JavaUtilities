/**
 * this will execute a shell script using process builder
 */
package unix_playground.shellScriptExecution;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UsingProcessBuilder {

    // verify the file should have execution access, otherwise run "chmod u+x simpleShellScript.sh"
    private final String shellScriptFilePath = System.getProperty("user.dir")+"/src/test/java/Resources/unixResources/simpleShellScript.sh";

    @Test
    public void executeShellScriptTest() throws IOException, InterruptedException {


        ProcessBuilder processBuilder = new ProcessBuilder(shellScriptFilePath);

        // this will start the shell script execution
        Process processStart = processBuilder.start();
        int exitCode = processStart.waitFor();

        // creating BufferedReader to read the execution
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream()));

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line).append("\n");
        }

        // displaying the output of the shellscript
        System.out.println("output:");
        System.out.println(stringBuilder);

        // exitCode should be 0 if the process is completed successfully
        Assert.assertEquals(exitCode,0," The process was not completed successfully"+exitCode);

        // closing the bufferedReader to avoid memory leaks
        bufferedReader.close();
    }
}

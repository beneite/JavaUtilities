/**
 * plexus-utils : can be added through adding dependency org.codehaus.plexus
 */
package unix_playground.shellScriptExecution;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.WriterStreamConsumer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.OutputStreamWriter;

public class UsingPlexusUtils {

    /**
     * Key Points:
     * Executable Path: Ensure that "/path/to/your/script.sh" is correct and the file has execution permissions (chmod +x script.sh).
     * Error Output: Redirect stderr to System.err for better clarity in case of errors.
     * Exception Handling: Make sure to handle potential exceptions properly in real scenarios.
     */
    private final String shellScriptFilePath = System.getProperty("user.dir")+"/src/test/java/Resources/unixResources/simpleShellScript.sh";

    @Test
    public void executeShellScriptUsingPlexusUtilTest() throws CommandLineException {

        Commandline commandline = new Commandline();

        // setExecutable will set the command or shell script file path but will not start the execution.
        commandline.setExecutable(shellScriptFilePath);

        WriterStreamConsumer systemOutput = new WriterStreamConsumer(new OutputStreamWriter(System.out));
        WriterStreamConsumer systemErrorOutput = new WriterStreamConsumer(new OutputStreamWriter(System.err));

        System.out.println("output:");
        int returnCode = CommandLineUtils.executeCommandLine(commandline, systemOutput, systemErrorOutput);

        // returnCode should be 0 if the process is completed successfully
        Assert.assertEquals(returnCode,0," The process was not completed successfully"+returnCode);

    }
}

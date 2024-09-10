package unix_playground.shellScriptExecution;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UsingJSCH {

    @Test
    public void executeShellScriptUsingJSCHTest() throws JSchException, IOException {

        JSch jSch = new JSch();

        Session session = jSch.getSession("sftpcustomservice.auimp.cusr","10.153.224.10",22);
        session.setPassword("ramr8nkB60hgY5S8eAZESkQPFedlsyCs"); // in case you have private key instead of password use jSch.addIdentity(privatekey);
        // Set SSH configuration
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        Channel channelSftp = session.openChannel("exec");

        // Set the command to be executed
        ((ChannelExec)channelSftp).setCommand("ls -l /myfile/sample");
        InputStream inputStream = channelSftp.getInputStream();
        channelSftp.connect();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while((line=bufferedReader.readLine()) != null){
            System.out.println(line);
        }


        channelSftp.disconnect();
        session.disconnect();
    }
}

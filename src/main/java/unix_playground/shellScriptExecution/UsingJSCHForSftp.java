package unix_playground.shellScriptExecution;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Vector;

public class UsingJSCHForSftp {

    @Test
    public void ftpServerConnection() throws JSchException, IOException, SftpException {

        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            // Create a session
            session = jsch.getSession("sftpcustomservice.auimp.cusr", "10.153.224.10", 22);
            session.setPassword("ramr8nkB60hgY5S8eAZESkQPFedlsyCs");

            // Set SSH configuration
            session.setConfig("StrictHostKeyChecking", "no");

            // Connect to the server
            session.connect();

            // Open an SFTP channel
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // List files in a remote directory
            @SuppressWarnings("unchecked")
            Vector<ChannelSftp.LsEntry> files = channelSftp.ls("/BridgeService/staging");
            for (ChannelSftp.LsEntry entry : files) {
                System.out.println(entry.getFilename());
            }

        } catch (JSchException | SftpException e) {
            e.printStackTrace();
            Assert.fail("Exception occurred: " + e.getMessage());
        } finally {
            // Disconnect from the server and also close the channel
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }
}

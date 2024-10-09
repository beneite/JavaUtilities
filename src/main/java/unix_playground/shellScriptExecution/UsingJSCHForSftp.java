package unix_playground.shellScriptExecution;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

public class UsingJSCHForSftp {

    public static final String SFTP_USERNAME = "sftpcustomservice.auimp.cusr";
    public static final String SFTP_PASSWORD = "ramr8nkB60hgY5S8eAZESkQPFedlsyCs";
    public static final String SFTP_IP = "10.153.224.10";
    public static final int SFTP_PORT = 22;

    private Session session;
    private ChannelSftp channelSftp;

    @BeforeTest
    public void sftpServerConnection() {
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(SFTP_USERNAME, SFTP_IP, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);

            // Set strict host key checking to no to avoid UnknownHostKey issue
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // Establish the SSH session
            session.connect();

            // Open SFTP channel
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            System.out.println("Connected to SFTP server.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listFiles() {
        try {
            System.out.println("Listing files on the SFTP server:");
            Vector<ChannelSftp.LsEntry> files = channelSftp.ls(".");
            for (ChannelSftp.LsEntry file : files) {
                System.out.println(file.getFilename());
            }
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void closeSftpConnection() {
        if (channelSftp != null) {
            channelSftp.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
        System.out.println("Disconnected from SFTP server.");
    }
}

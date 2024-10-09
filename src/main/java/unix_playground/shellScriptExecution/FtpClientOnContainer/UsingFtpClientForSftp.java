/**
 * please execute this via docker, create a docker image fot FTP server.
 */
package unix_playground.shellScriptExecution.FtpClientOnContainer;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class UsingFtpClientForSftp {

    public static final String FTP_USERNAME = "admin";
    public static final String FTP_PASSWORD = "ashish@123";
    public static final String FTP_IP = "127.0.0.1";
    public static final int FTP_PORT = 21;

    public static FTPClient ftpClient;


    @BeforeTest
    public void ftpServerConnection(){

        ftpClient = new FTPClient();
        try{
            // Connect to the FTP server
            ftpClient.connect(FTP_IP, FTP_PORT);
            boolean isLoggedIn =ftpClient.login(FTP_USERNAME, FTP_PASSWORD);

            if (isLoggedIn) {
                System.out.println("Login successful.");
            } else {
                System.out.println("Login failed.");
            }

            // Set file transfer type to binary
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listFiles(){
        try {
            System.out.println("Listing files on the FTP server:");
            FTPFile[] files = ftpClient.listFiles("bridge");
            for (FTPFile file : files) {
                System.out.println(file.getName());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @AfterTest
    public void closeFtpConnection(){
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

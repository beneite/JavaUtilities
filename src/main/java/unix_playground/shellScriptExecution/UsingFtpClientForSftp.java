/**
 * please execute this via docker, create a docker image fot FTP server.
 */
package unix_playground.shellScriptExecution;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class UsingFtpClientForSftp {

    public static final String FTP_USERNAME = "sftpcustomservice.auimp.cusr";
    public static final String FTP_PASSWORD = "ramr8nkB60hgY5S8eAZESkQPFedlsyCs";
    public static final String FTP_IP = "10.153.224.10";
    public static final int FTP_PORT = 22;

    public static FTPClient ftpClient;


    @BeforeTest
    public void ftpServerConnection(){

        ftpClient = new FTPClient();
        try{
            // Connect to the FTP server
            ftpClient.connect(FTP_IP, FTP_PORT);
            ftpClient.login(FTP_USERNAME, FTP_PASSWORD);

            // Set file transfer type to binary
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listFiles(){
        try {
            System.out.println("Listing files on the FTP server:");
            FTPFile[] files = ftpClient.listFiles();
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

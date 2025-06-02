package CI_CD.jenkins.p01_simpleJenkinsFile_pipeline;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleJavaProgram {

    public static void main(String[] args){
        System.out.println("Hello From Java Program!!!");
        System.out.println("Starting the Java program");
        try {
            System.out.println("Running Program....");
            Thread.sleep(5000);
            System.out.println("Program executed successfully");
        } catch (InterruptedException e) {
            System.out.println("Program encountered error");
            throw new RuntimeException(e);
        }
        System.out.println("Closing the Java program");
    }
}

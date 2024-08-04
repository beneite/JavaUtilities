/**
 * Since we have already seen how to fetch data from DB and store it into an excel. in DataBaseConnection/FetchDataFromDbToJsonFile
 * So here we will directly show how to convert  json into excel
 */
package jsonParsing.ConvertJsonFileToExcel;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConvertJsonToExcelTest {

    /**
     * 1.json file to String, 2. Converting the String to .csv file format
     * @throws IOException
     */
    @Test
    public void storeDbDataIntoExcel() throws IOException {
        byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/java/jsonParsing/ConvertJsonFileToExcel/fullPayload.json"));
        String jsonString = new String(file);

        JSONObject jsonObject = new JSONObject(jsonString);
        // converting the JsonArray to String
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        File excelFile=new File(System.getProperty("user.dir")+"/src/main/java/jsonParsing/ConvertJsonFileToExcel/jsonToExcel.csv");
        String csv = CDL.toString(jsonArray);
        FileUtils.writeStringToFile(excelFile, csv);
    }
}

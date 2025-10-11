package jsonParsing.usingJacksonApi.p02_jacksonSpecifics;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jsonParsing.usingJacksonApi.p02_jacksonSpecifics.pojos.ArrayObjectDto;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadDataAsPojo {


    @Test
    public void readDataFromJsonFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Path jsonFilePath = Paths.get(System.getProperty("user.dir")+ "/src/main/java/jsonParsing/usingJacksonApi/p02_jacksonSpecifics/target.json");
        byte[] fileData = Files.readAllBytes(jsonFilePath);
        String fileContent = new String(fileData);

        List<ArrayObjectDto> arrayObjectDtoFromOM = objectMapper.readValue(fileContent, new TypeReference<List<ArrayObjectDto>>() {
        });

        for(ArrayObjectDto arrayObjectDto : arrayObjectDtoFromOM){
            System.out.println("\n**********************************");
            System.out.println("id:"+arrayObjectDto.getId());
            System.out.println("name:"+arrayObjectDto.getName());
            System.out.println("username:"+arrayObjectDto.getUsername());
            System.out.println("email:"+arrayObjectDto.getEmail());
            System.out.println("Address > street:"+arrayObjectDto.getAddressDto().getStreet());
            System.out.println("Address > suite:"+arrayObjectDto.getAddressDto().getSuite());
            System.out.println("Address > city:"+arrayObjectDto.getAddressDto().getCity());
            System.out.println("Address > zipcode:"+arrayObjectDto.getAddressDto().getZipcode());
            System.out.println("Address > geo > lat:"+arrayObjectDto.getAddressDto().getGeoDto().getLat());
            System.out.println("Address > geo > lng:"+arrayObjectDto.getAddressDto().getGeoDto().getLng());
            System.out.println("phone:"+arrayObjectDto.getPhone());
            System.out.println("website:"+arrayObjectDto.getWebsite());
            System.out.println("company > name:"+arrayObjectDto.getCompanyDto().getName());
            System.out.println("company > catchPhrase:"+arrayObjectDto.getCompanyDto().getCatchPhrase());
            System.out.println("company > bs:"+arrayObjectDto.getCompanyDto().getBs());
        }

    }
}

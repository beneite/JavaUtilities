# This repo contains the utilities created in java to support Automation:

> [!NOTE]
> utility hypertext mark will navigate to respective motes.md file

### Please go through the below table:

| **_Utility_**                                                                |        **_Package Name_**         |                                                                                                         **_Topics covered_**                                                                                                         |
|------------------------------------------------------------------------------|:---------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| [Json Paring](src/main/java/jsonParsing/jsonParsingNotes.md)                 |     src/main/java/jsonParsing     | - JSONObject, POJO, [serialization](https://www.geeksforgeeks.org/serialization-in-java/) <br/>- deserialization <br/>- Object Mapper<br/>- Jackson API,<br/>-  org.json.JSONObject<br/>- org.json.simple <br/>- Json to Excel <br/> | 
| [Database Connection](src/main/java/DataBaseConnection/DbCOnnectionNotes.md) | src/main/java/DataBaseConnection  |                      - how to establish DB connection [link](src/main/java/DataBaseConnection/FetchDataFromDb/DbConnectionUtil.java)<br/>-  Fetching data from DB<br/>-  Storing fetched Data into *.json<br/>                       | 
| Password Protection                                                          | src/main/java/Password_Protection |                                                                      - Password encription & decription<br/> - Use char[] instead of String to store Passwords                                                                       | 
| [Unix Commands](src/main/java/unix_commands/lynxnotes.md)              |    src/main/java/unix_commands    |                                                                                                        - Basic Unix commands                                                                                                         | 

> [!IMPORTANT]
> Points to remember:

| **_Topics_**                |                                                                                                                 **_Link_**                                                                                                                 |
|-----------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| @JsonProperty("field_name") |          -  https://stackoverflow.com/questions/53191468/no-creators-like-default-construct-exist-cannot-deserialize-from-object-valu <br/> - https://www.concretepage.com/jackson-api/jackson-jsonproperty-and-jsonalias-example          | 
| org.json.JSONObject         |                                                                                     is best in converting string to json object, json array on the fly                                                                                     | 
| Password Protection         | Never store password in String in java instead use char[] [video_link](https://www.youtube.com/watch?v=fDTbnLS5AS8), [Oracle_recommendation](https://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx) | 

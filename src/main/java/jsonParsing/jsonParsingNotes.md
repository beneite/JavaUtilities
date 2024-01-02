# Notes for Json Parsing

## **JsonObject** 
> - Over here we implemented how we can ready data from *.json file and pass it to our Java classes/methods.
> - An alternative to JSONObject/JSONArray can also je JSONPath
> - > -      json-simple dependency was used.



## **Jackson API** 
> - Jackson is a very popular and efficient java based library to serialize or map java objects to JSON and vice versa.
> - Jackson is a Java-based library.
> -      jackson-core, jackson-databind, jackson-annotations dependencies were used.


## **POJO** 
> - POJO in Java stands for Plain Old Java Object. It is an ordinary object, which is not bound by any special restriction. The POJO file does not require any special classpath. It increases the readability & re-usability of a Java program.
    Below are some properties of the POJO class:

**The POJO class must be public.**
- It must have a public default constructor.
- It may have the arguments constructor.
- All objects must have some public Getters and Setters to access the object values by other Java Programs.
- The object in the POJO Class can have any access modifies such as private, public, protected. But, all instance variables should be private for improved security of the project.
- A POJO class should not extend predefined classes.
- It should not implement prespecified interfaces.
- It should not have any prespecified annotation.
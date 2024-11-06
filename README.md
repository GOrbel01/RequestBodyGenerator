# POJO Request Body Generator

Generate a Request Body with a New-Line Seperated set of Java Fields

## Description

Copy and Paste fields from your Java POJO class into Postman or a Web Browser UI (Coming later)
and generate a JSON Request that you can use to POST for your data typeInfo. Some tweaking may be required of course.
For example meeting validation requirements, avoiding duplicates etc.

## Limitations

Cannot for obvious reasons handle Complex types and hierarchies. It will instead generate the following

For example
```
 private String testName;
 private Long testLargeNumber;
 private Object someComplexType;
```
Will produce
```
{
  "testName":"dummyName",
  "testLargeNumber":1,
  "someComplexType":{}
}
```

You can of course, generate a request for the subtype also and copy it into the parent request, but for classes making significant use of
inheritance and/or composition this will not be viable. Mainly designed just for simple cases.

Does not handle Nested Data Structs. E.g. List<List<List<Integer>>. Will automatically parse these as
a single nested version i.e. List<Integer>. May add support for this in future versions

## Getting Started

### Dependencies

* No External Requirements
* Project makes use of Spring Boot/WebMVC, Lombok and JUnit

### How to Run

* Take the following Java Class as an example (ignore Getters and Setters if not using Lombok)
```
@Data
public class DummyPojo {
    private String testName;
    private Long testLargeNumber;
    private Integer testSmallNumber;
    private int testPrimitiveNumber;
    private Object willNotProvideDummyData;
    private List<String> testStrList;
}
```
* Copy and Paste the fields as below into Postman or the Web View. Should handle any whitespace gaps, 
access modifier is optional (as it is redundant) but saves time to not have to remove them all
```
    private String testName;
    private Long testLargeNumber;
    private Integer testSmallNumber;
    private int testPrimitiveNumber;
    private Object willNotProvideDummyData;
    private List<String> testStrList;
```

```
{
  "testName": "dummyName",
  "testLargeNumber": 1,
  "testSmallNumber": 1,
  "testPrimitiveNumber": 1,
  "willNotProvideDummyData": {},
  "testStrList": [
    "dummStr1","dummyStr2"
  ]
}
```

## Help

For any help email the dev at finalsquall.dev@gmail.com

## Authors

George O

## Future Plans

* Another project that would be deployed as a library. With this it would be possible to use Reflection to actually analyze the class hierarchy
and generate a more detailed Request Body
* Adding support for other programming languages (in a perfect world would rework the code to use config for this
but that would increase the scope of this small project significantly!)

## Version History

* Development
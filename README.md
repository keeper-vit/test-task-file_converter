# test-task-file_converter
This is test task

This app converts the CSV file of the specified structure into a file with JSON.

#### Data structure
| Type | Attribute Name |
|:------:|:----------------:|
| integer | ID |
| string | First Name |
| string | Last Name |
| string [true/false] | Status |

#### Example

*CSV:*
```csv
    123,Jack,Daniels,true
    45,Johnny,Walker,false
    6,John,Jameson,true
```

*Will be converted to JSON:*
```json
    [{"id":123,"firstName":"Jack","lastName":"Daniels","status":true},
    {"id":45,"firstName":"Johnny","lastName":"Walker","status":false},
    {"id":6,"firstName":"John","lastName":"Jameson","status":true}]
```

###### Compile
./mvnw clean package

###### Run
java -jar target/file-converter-1.0-SNAPSHOT.jar --in {input-csv-file} [--out {output-json-file}]

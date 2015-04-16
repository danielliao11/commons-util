#java_util
Some useful utilities for Java.

###Usage

maven:

```
	<dependency>
    	<groupId>com.github.saintdan</groupId>
    	<artifactId>java_util</artifactId>
    	<version>0.0.6-SNAPSHOT</version>
	</dependency>
```

gradle:

```
	'com.github.saintdan:java_util:0.0.6-SNAPSHOT'
```

###Including:
- ArrayUtil: Some useful array utilities for Java's arrary[], and some of them likes Python's list.
- ByteUtil: Simple encode and decode.
- CharsetUtil: Some charset utilities. 
- ConvertUtil: Some Conversation utilities.
- DateFormat: Thread-Safe date formatter.
- EncryptionUtil: Encryption and decryption utilities.
- InetAddressUtil: Get the host's IP or domain name.
- IOUtil: Extends commons-io.
- JsonUtil: Some useful json utilities, convert xxx to json.
- MathUtil: Some math utilities of high-precision calculation and max, min calculation.
- StringUtil: Some String utilities.
- Wait for other utilities.

###Version History:
- 0.0.1-SNAPSHOT
  - Initial version: 
    - ArrayUtil : has been completed, and passed test.
    - MathUtil : has been completed, and passed test.
- 0.0.2-SNAPSHOT    
    - ConvertUtil : has been completed, and passed test.
    - DateFormat : has been completed, and passed test.
    - StringUtil : 20%.
- 0.0.3-SNAPSHOT
    - JsonUtil : has been completed, and passed test.
    - StringUtil : 40%.
- 0.0.4-SNAPSHOT
    - InetAddressUtil : has been completed, and passed test.
    - IOUtil : has been completed, and passed test.
    - StringUtil : 80%.
- 0.0.5-SNAPSHOT
    - ByteUtil : has been completed, and passed test.
    - EncryptionUtil : has been completed, and passed test.
    - StringUtil : has been completed, and passed test.
- 0.0.6-SNAPSHOT
    <br/>Some util extends org.apache.commons.lang3.
    - CharsetUtil : has been completed, and passed test.

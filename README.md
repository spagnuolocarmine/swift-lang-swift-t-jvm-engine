#C-JVM-Scripting: evaluate JVM scripting languages in C applications. 

Version Alpha 1.0 

###Software Requirements

- Autotool
- Maven
- Java Virtual Machine

###Tested Envirnment

- Unbuntu 14.04
- java version "1.8.0_45" oracle
- gcc version 4.8.4 
- autoconf (GNU Autoconf) 2.69
- automake (GNU automake) 1.14.1
- Apache Maven 3.0.5

###Installation
```
./bootstrap
./configure
make
export SWIFT_JVM_USER_LIB=. (change this with additional jar folder libraries)
export LD_LIBRARY_PATH=/usr/lib/jvm/java-8-oracle/jre/lib/amd64/server (change this with JVM home)
./src/test
```
###Configure Options

- --with-jvm-home, jvm home directory (default:/usr/lib/jvm/java-8-oracle)
- --with-swift-jvm-engine-lib, swift jvm engine lib (default: classes)
- -debug, enables the debug verbose

###Test 

```
./src/test
```

#####Output
```
Test all script engine ..

Clojure: ok
```

###TODO

- Test USER LIB in scripting
- Add other test in Java
- Add other test in C
- Add binding to all scripting in C (now there are just clojure test)

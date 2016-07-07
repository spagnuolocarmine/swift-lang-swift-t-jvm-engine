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
export SWIFT_JVM_USER_LIB=swift-jvm/swift-jvm-build/target/swift-jvm-build-0.0.1-bin/swift-jvm/classes/ (change this with additional jar folder libraries)
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

##### Authors

  - Carmine Spagnuolo* (Univeristà degli Studi di Salerno) 
  - Jonathan Ozik (Argonne National Laboratory) 
  - Nicholson Collier (Argonne National Laboratory) 
  - Justin M. Wozniak (Argonne National Laboratory) 

##### License 

The MIT License (MIT)
Copyright (c) 2016 Carmine Spagnuolo

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


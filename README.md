#C-JVM-Scripting: evaluate JVM scripting languages in C applications. 


Requirements
Autotool
Maven
Java Virtual Machine

Tested envirnment
Unbuntu 14.04
java version "1.8.0_45" oracle
gcc version 4.8.4 
autoconf (GNU Autoconf) 2.69
automake (GNU automake) 1.14.1
 
 Apache Maven 3.0.5
./bootstrap
./configure
make
export SWIFT_JVM_USER_LIB=.
export LD_LIBRARY_PATH=/usr/lib/jvm/java-8-oracle/jre/lib/amd64/server
./src/test

TODO

TEST USER LIB in scripting
add test
add binding to all scripting in C (now there are just clojure test)

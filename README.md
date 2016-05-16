#C-JVM-Scripting: evaluate JVM scripting languages in C applications. 

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

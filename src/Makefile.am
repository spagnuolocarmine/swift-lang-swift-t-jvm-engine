AM_LDFLAGS = -L@JVMHOME@/bin/java -L@JVMHOME@/jre/lib/amd64/server
AM_CFLAGS = -DJVMLIB=\"@JVMLIB@\"  -Wall -Wunused-result  -I @JVMHOME@/include -I @JVMHOME@/include/linux  
if DEBUG
AM_CFLAGS += -DDEBUG
endif
LIBS = -ljvm

lib_LTLIBRARIES = libswifttjvm.la
libswifttjvm_la_SOURCES = swift-jvm.c swift-jvm.h

bin_PROGRAMS = tests
tests_SOURCES = tests.c
# swift-jvm.c swift-jvm.h
tests_LDADD = libswifttjvm.la

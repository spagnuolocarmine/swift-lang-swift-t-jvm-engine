#include "swift-jvm.h"
#include <assert.h>
#include <string.h>
#include <stdio.h>
int main(void)
{
    printf("Test all script engine ..\n\n");

    char *groovyoutput=groovy("import java.security.MessageDigest\n def hash(text){ MessageDigest.getInstance(\"SHA-512\").digest(text.getBytes(\"UTF-8\")).encodeBase64().toString()}\n println hash(UUID.randomUUID().toString())");
    //assert(strcmp(groovyoutput,"")==0);
    printf("Groovy: ok, Output: %s \n",groovyoutput);

    char *cljoutput=clojure("(ns clojure.examples.hello\n(:gen-class))\n(defn hello-world [username]\n(str (format \"Hello, %s\" username)))\n(hello-world\"Red\")");
    assert(strcmp(cljoutput,"Hello, Red")==0);
    printf("Clojure: ok\n");

    char *jshelloutput=javashell("int c = 4+5*6;");
    assert(strcmp(jshelloutput,"34")==0);
    printf("JavaShell: ok\n");

   // char *scalaoutput=
    //scala("object HelloWorld {\ndef main(args: Array[String]) = println(\"Hello Scala!\")\n}");
    //assert(strcmp(scalaoutput,"#'cse-1/hello")==0);
    //printf("Scala: ok\n");

    //char *javascriptoutput=
    //javascript("function test() {\n var x = 2, y = 4;\n return eval(\"x + y\");}\n test();");
    //assert(strcmp(javascriptoutput,"#'cse-1/hello")==0);
    //printf("JS: ok\n");

}

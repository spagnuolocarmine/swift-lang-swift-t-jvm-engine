#include "swift-jvm.h"
#include <assert.h>
#include <string.h>
#include <stdio.h>
int main(void)
{
    printf("Test all script engine ..\n\n");
    char *cljoutput=clojure("(def hello (fn []\"Hello world\"))");
    assert(strcmp(cljoutput,"#'cse-1/hello")==0);
    printf("Clojure: ok\n");
    return 0;
//TODO 
/*
    char *groovyoutput=clojure("print \"Hello World\"");
    assert(strcmp(groovyoutput,"Hello World")==0);	

    char *scalaoutput=clojure("(def hello (fn []\"Hello world\"))");
    assert(strcmp(scalaoutput,"#'cse-1/hello")==0);	
    
    char *javascriptoutput=clojure("(def hello (fn []\"Hello world\"))");
    assert(strcmp(javascriptoutput,"#'cse-1/hello")==0);	
*/
}

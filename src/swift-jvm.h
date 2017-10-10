#ifndef SWIFT_JVM_H_   /* Include guard */

#define SWIFT_JVM_H_

//extern char path_java_code[]="classes";
/* Evaluate Groovy Code and returns a char array of the stdio*/
char * groovy(char *code);
/* Evaluate Clojure Code and returns a char array of the stdio*/
char * clojure(char *code);
/* Evaluate Scala Code and returns a char array of the stdio*/
char * scala(char *code);
/* Evaluate JavaScricpt Code and returns a char array of the stdio*/
char * javascript(char *code);
/* Evaluate JavaShell Code and returns a char array of the stdio*/
char * javashell(char *code);
#endif //SWIFT_JVM_H_

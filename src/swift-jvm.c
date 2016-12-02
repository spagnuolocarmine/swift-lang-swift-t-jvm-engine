#include <assert.h>
#include <stdio.h>
#include <jni.h>
#include <string.h>
#include <dirent.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <unistd.h>
#include <limits.h>
#include "swift-jvm.h"


/* -- Macro Definitions */
#ifdef DEBUG
#define PDEBUG(x)    pdebug("%s",  x)
#define PDEBUGN(x)   pdebug("%s\n",x)
#else
#define PDEBUG(x)
#define PDEBUGN(x)
#endif /* DEBUG */

JNIEnv * env;
JavaVM * jvm; 

static inline void pdebug(const char* fmt, const char* s)
{
  printf(fmt, s);
  fflush(stdout);
}

char * createClassPathString(char *jars_dir)
{
    if(strlen(jars_dir)==0) return NULL;
    struct dirent *pDirent;
    DIR *pDir;
    struct stat dbuf;
    if (lstat(jars_dir, &dbuf) == -1) goto error;
    if(!S_ISDIR(dbuf.st_mode)) goto error;
    pDir = opendir (jars_dir);
    if (pDir == NULL) goto error;

    int num_jars = 0;
    while ((pDirent = readdir(pDir)) != NULL) {
        if(strlen(pDirent->d_name) > 4 && !strcmp(pDirent->d_name + strlen(pDirent->d_name) - 4, ".jar"))
        num_jars++;
    }

    char *class_path = (char *) malloc((sizeof(char)*(PATH_MAX + 1)*num_jars) + (sizeof(char)*18));

    closedir (pDir);
    pDir = opendir (jars_dir);

    char buf[PATH_MAX + 1];
    char nbuf[PATH_MAX + 1];
    int first=1;
    while ((pDirent = readdir(pDir)) != NULL) {
      if(strlen(pDirent->d_name) > 4 && !strcmp(pDirent->d_name + strlen(pDirent->d_name) - 4, ".jar"))
      {
        sprintf(nbuf,"%s/%s",jars_dir,pDirent->d_name);
        
	if(realpath(nbuf, buf)==NULL) PDEBUG("Error in realpath resolving.");
         if(first)
         {
                sprintf (class_path,"%s%s",class_path,buf);
                first=0;
         }
         else
                sprintf (class_path,"%s:%s",class_path,buf);

      }
    }

    sprintf (class_path,"%s%s",class_path,"\0");
    closedir (pDir);
    return class_path;

  error:
    printf("Given bad class path directory: %s\n", jars_dir);
    fflush(stdout);
    return NULL;
}
void call_java_static_method(char *java_class_name,char *method_name,char *arg)
{
  jmethodID smfnMethod = NULL; 
  jclass clsJava=NULL;
  jstring StringArg=NULL;
  clsJava = (*env)->FindClass(env,java_class_name);
  PDEBUG("Inside call method.."); PDEBUGN(method_name);

  if(clsJava != NULL) PDEBUGN("Able to find the requested class");
  else PDEBUGN("\n Unable to find the requested class\n");

  smfnMethod = (*env)->GetStaticMethodID(env, clsJava, method_name, "(Ljava/lang/String;)V\0");

  StringArg = (*env)->NewStringUTF(env, arg);
   
   if(smfnMethod != NULL)
   {
      PDEBUG("Calling the Static Function method -->");PDEBUGN(method_name);
      ((*env)->CallStaticCharMethod(env, clsJava, smfnMethod, StringArg));
    
       if ((*env)->ExceptionOccurred(env)) {
          (*env)->ExceptionDescribe(env);
      }

   }else PDEBUGN("No method found in class");
}

char * call_java_static_char_method(char *java_class_name,char *method_name,char *sengine,char *scode)
{
  jstring tor = NULL;
  const char *strtor;
  jmethodID smfnMethod = NULL;
  jclass clsJava=NULL;
  jstring engine=NULL;
  jstring code=NULL;
  clsJava = (*env)->FindClass(env,java_class_name);
  PDEBUG("Attempting to call method: "); PDEBUGN(method_name);

  if(clsJava != NULL)
  {
	PDEBUG("Able to find the requested class ");
  }
  else
        PDEBUG("\n Unable to find the requested class \n");
  PDEBUGN(java_class_name);

  smfnMethod = (*env)->GetStaticMethodID
    (env, clsJava, method_name,
     "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;\0");

   if(smfnMethod == NULL)
   {
     printf("Could not find method: `%s'\n", method_name);
     return NULL;
   }

   engine = (*env)->NewStringUTF(env, sengine);
   code = (*env)->NewStringUTF(env, scode);
   if(smfnMethod != NULL)
   {
      PDEBUG("Calling the Static Function method -->");PDEBUGN(method_name);
       tor=(jstring)((*env)->CallStaticObjectMethod(env, clsJava, smfnMethod,engine,code));

       if ((*env)->ExceptionOccurred(env)) {
          (*env)->ExceptionDescribe(env);
      }

   }else PDEBUGN("No method found in class");
   
   strtor= (char*)((*env)->GetStringUTFChars(env, tor, NULL));
   // Caller must free this string:
   char *toStringReturn= (char*)malloc(sizeof(char)*strlen(strtor));
   sprintf(toStringReturn,"%s",strtor);
   printf("groovy result: %s\n", toStringReturn);
   (*env)->ReleaseStringUTFChars(env,tor, strtor); 
   return toStringReturn;
}

#define SWIFT_JVM_ERROR -10042 // Presumably unused JNI error code

/**
 @return JNI_OK on success; returns a suitable JNI error code (a negative number) on failure. (as JNI_CreateJavaVM)
 */
static int init_jvm() {

  JavaVMInitArgs vm_args;
  JavaVMOption options;

  PDEBUGN("init_jvm...");

  char* userlib = getenv("SWIFT_JVM_USER_LIB");
  if (userlib == NULL || strlen(userlib) == 0)
  {
    printf("SWIFT_JVM_USER_LIB is not set!\n");
    return SWIFT_JVM_ERROR;
  }
  char * generated_class_path=createClassPathString(JVMLIB);//LIB);
  // printf("GCP: %s\n", generated_class_path);
  if (generated_class_path == NULL) return SWIFT_JVM_ERROR;
  char * generated_class_path_user=createClassPathString(userlib);
  // printf("GCPU: %s\n", generated_class_path_user);
  if (generated_class_path_user == NULL) return SWIFT_JVM_ERROR;
  char cp[strlen(generated_class_path)+strlen(generated_class_path_user)+128];
  
  sprintf(cp,"-Djava.class.path=%s:%s",generated_class_path,generated_class_path_user);  
  
  options.optionString = cp;//java_class_path;
  PDEBUGN("Loaded classpath");
  PDEBUGN(options.optionString);

  vm_args.version = JNI_VERSION_1_8; //JDK version. This indicates version 1.6
  vm_args.nOptions = 1;
  vm_args.options = &options;
  vm_args.ignoreUnrecognized = 0;

  int ret = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);

  if(ret < 0 || !env) PDEBUGN("Unable to Launch JVM");   	

  PDEBUGN("JVM started.");
  return ret;
}
void destroy_jvm()
{
   (*jvm)->DestroyJavaVM(jvm);
   env=NULL;
   PDEBUGN("JVM destroyed.");
}
/* Evaluate Groovy Code and returns a char array of the stdio*/
char * groovy(char *code)
{
  // printf("swift-t-jvm: %s\n", code);

  if (jvm == NULL)
  {
    int rc = init_jvm();
    if (rc < 0)
    {
      printf("JVM FAILED\n");
      return NULL;
    }
    printf("JVM OK\n");
  }
  call_java_static_method("it/isislab/swift/interfaces/SwiftJVMScriptingEngine","setEngine","groovy");
  char* tor = call_java_static_char_method("it/isislab/swift/interfaces/SwiftJVMScriptingEngine","eval","groovy",code);
  return tor;
}
/* Evaluate Clojure Code and returns a char array of the stdio*/
char * clojure(char *code)
{
  if(jvm == NULL) init_jvm();
  //call_java_static_method("it/isislab/swift/interfaces/SwiftJVMScriptingEngine","setEngine","clojure");
  char * tor=call_java_static_char_method("it/isislab/swift/interfaces/SwiftJVMScriptingEngine","eval","clojure",code);
  return tor;
}
/* Evaluate Scala Code and returns a char array of the stdio*/
char * scala(char *code)
{
  if(jvm == NULL) init_jvm();
  //call_java_static_method("it/isislab/swift/interfaces/SwiftJVMScriptingEngine","setEngine","scala");
  char * tor=call_java_static_char_method("it/isislab/swift/interfaces/SwiftJVMScriptingEngine","eval","scala",code);
  return tor;
}
/* Evaluate JavaScricpt Code and returns a char array of the stdio*/
char * javascript(char *code)
{
  if(jvm == NULL) init_jvm();
  //call_java_static_method("it/isislab/swift/interfaces/SwiftJVMScriptingEngine","setEngine","javascript");
  char * tor=call_java_static_char_method("it/isislab/swift/interfaces/SwiftJVMScriptingEngine","eval","javascript",code);
  return tor;
}
/*int main(void)
{
  
 char path_java_code[]="classes";
  init_jvm(path_java_code);
  call_java_static_method("it/isislab/swift/interfaces/SwiftJVMScriptingEngine","setEngine","clojure");
  PDEBUGN("Output Groovy Evaluation of (def hello (fn []\"Hello world\")):");
  PDEBUGN(call_java_static_char_method("it/isislab/swift/interfaces/SwiftJVMScriptingEngine","eval","(def hello (fn []\"Hello world\"))"));
  destroy_jvm();
  PDEBUGN(clojure("(def hello (fn []\"Hello world\"))"));
  return 0;

}*/

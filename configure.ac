#                                               -*- Autoconf -*-
# Process this file with autoconf to produce a configure script.

AC_PREREQ([2.68])

AC_INIT([swift-jvm-engine], [1.0], [spagnuolocarmine@gmail.com])

AM_INIT_AUTOMAKE
AC_CONFIG_MACRO_DIRS([m4])
LT_INIT

AC_SUBST([LIBTOOL_DEPS])
AC_CONFIG_SRCDIR([src/tests.c])
AC_PROG_CC

AC_MSG_RESULT([Swift/T JVM Engine version: 1.0])

JVMHOME="/usr/lib/jvm/java-8-oracle"
AC_ARG_WITH([jvm-home],
            [AS_HELP_STRING([--with-jvm-home],
               [Set up the JVM home directory
                (default: /usr/lib/jvm/java-8-oracle)])],
            [JVMHOME=$withval]
            )
AC_MSG_RESULT([Using JVM home: $JVMHOME])
AC_SUBST(JVMHOME,$JVMHOME)

CFLAGS="$CFLAGS -I$JVMHOME/include"

AC_CHECK_HEADERS([limits.h stdlib.h string.h unistd.h jni.h dirent.h stdio.h sys/stat.h])

#JVM SWIFT-T LIBs
AC_SUBST(JVMLIB,$(pwd)"/swift-jvm/swift-jvm-build/target/swift-jvm-build-0.0.1-bin/swift-jvm/classes")
AC_ARG_WITH([swift-jvm-engine-lib],
            [AS_HELP_STRING([--with-swift-jvm-engine-lib],
               [Set up the swift jvm engine lib (default: classes)])],
            [AC_SUBST(JVMLIB,$withval)],
            )


AC_ARG_ENABLE([debug],
[  --enable-debug    Turn on debugging],
[case "${enableval}" in
  yes) debug=true ;;
  no)  debug=false ;;
  *) AC_MSG_ERROR([bad value ${enableval} for --enable-debug]) ;;
esac],[debug=false])
AM_CONDITIONAL([DEBUG], [test x$debug = xtrue])


# Checks for library functions.

AC_CHECK_FILE(${JVMHOME}/include/jni.h,
                [],
                [AC_MSG_ERROR([Could not find jni.h in ${JVMHOME}])])

AC_FUNC_LSTAT_FOLLOWS_SLASHED_SYMLINK
AC_FUNC_MALLOC
AC_CHECK_FUNCS([realpath])

AC_CONFIG_FILES([Makefile
                src/Makefile])
AC_OUTPUT

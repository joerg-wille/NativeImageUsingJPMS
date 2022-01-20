#!/bin/sh

echo "Creating native image ... "

native-image --no-fallback \
             --native-image-info `#log building environment, native-toolchain information in reports directory`\
             --verbose \
             -H:ConfigurationFileDirectories=example-app/target/config \
	     -H:IncludeResources='^META-INF/MANIFEST.MF$' `#add each module's manifest file to native image` \
             -H:+ReportExceptionStackTraces \
             -H:+ReportUnsupportedElementsAtRuntime \
             --module-path example-util/target/NativeImageUsingJPMS-Util-1.0.0-SNAPSHOT.jar:example-app/target/NativeImageUsingJPMS-App-1.0.0-SNAPSHOT.jar \
             --module net.jbw.playground.app \
	     -H:Name=example


#!/bin/sh

echo "Creating native image ... "

native-image --no-fallback \
             --native-image-info \
             --verbose \
             -H:ConfigurationFileDirectories=example-app/target/config \
             -H:+ReportExceptionStackTraces \
             -H:+ReportUnsupportedElementsAtRuntime \
             --module-path example-util/target/NativeImageUsingJPMS-Util-1.0.0-SNAPSHOT.jar:example-app/target/NativeImageUsingJPMS-App-1.0.0-SNAPSHOT.jar \
             --module example.app \
	     -H:Name=example


#!/bin/sh

echo "Generate the config files ... "

java -agentlib:native-image-agent=config-output-dir=example-app/target/config \
     --module-path example-util/target/NativeImageUsingJPMS-Util-1.0.0-SNAPSHOT.jar:example-app/target/NativeImageUsingJPMS-App-1.0.0-SNAPSHOT.jar \
     --module net.jbw.playground.app


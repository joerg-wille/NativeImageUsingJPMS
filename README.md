# NativeImageUsingJPMS
POC for GraalVM NativeImage using the Java Platform Module System


### Prerequisites  
1. [Install](https://www.graalvm.org/docs/getting-started/) [GraalVM 22 Java 17](https://www.oracle.com/downloads/graalvm-downloads.html)
2. [Install](https://www.graalvm.org/docs/getting-started/#native-images) Native Image `gu install native-image` or from [downloaded](https://www.oracle.com/downloads/graalvm-downloads.html#license-lightbox) jar, e.g. `gu -L install native-image-installable-svm-svmee-java17-darwin-amd64-22.0.0.jar`.

### Build  
1. Clone [NativeImageUsingJPMS](https://github.com/joerg-wille/NativeImageUsingJPMS) to local directory
2. `cd [local directory]`
3. Build `./mvnw`

### Run Native Image Agent
1. `./prepare.sh`

### Create Native Image
1. `./build.sh`

### Run Native Image
1. `./example`

### Output
```
Module info for "net.jbw.playground.util":
    Module descriptor:
        Packages:
            net.jbw.playground.util
        Modifiers:
        Opens:
        Provides:
        Requires:
            mandated java.base
        Uses:
    Module resource "META-INF/MANIFEST.MF":
       Manifest-Version: 1.0
       Archiver-Version: Plexus Archiver
       Created-By: Apache Maven 3.8.3
       Built-By: joerg
       Build-Jdk: 17.0.2
       
Module info for "net.jbw.playground.app":
    Module descriptor:
        Packages:
            net.jbw.playground.app
        Modifiers:
        Opens:
        Provides:
        Requires:
            net.jbw.playground.util (@1.0.0-SNAPSHOT)
            mandated java.base
        Uses:
    Module resource "META-INF/MANIFEST.MF":
       Manifest-Version: 1.0
       Created-By: Maven Jar Plugin 3.2.0
       Build-Jdk-Spec: 17
       Main-Class: net.jbw.playground.app.Main
 ```

# NativeImageUsingJPMS
POC for GraalVM NativeImage using the Java Platform Module System


### Prerequisites  
1. [Install](https://www.graalvm.org/docs/getting-started/) [GraalVM CE 21.3 Java 17](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-21.3.0)
2. [Install](https://www.graalvm.org/docs/getting-started/#native-images) Native Image `gu install native-image`

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

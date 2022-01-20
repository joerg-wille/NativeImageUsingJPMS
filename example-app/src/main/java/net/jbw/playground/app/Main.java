package net.jbw.playground.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.stream.Collectors;
import net.jbw.playground.util.Calculator;

public class Main {

    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("Command: " + ProcessHandle.current().info().commandLine());
        System.out.println("Hello from Java Module: " + Main.class.getModule().getName());
        System.out.println("Required Java Module: " + Calculator.class.getModule().getName());
        System.out.println("Use module to calculate sum of '17' and '25': " + Calculator.add(17, 25) + "\n");

        final String packageName = Main.class.getPackage().getName();
        final String moduleScope = packageName.substring(0, packageName.lastIndexOf("."));
        displayModulesManifests(moduleScope, "META-INF/MANIFEST.MF");

    }

    private static void displayModulesManifests(final String moduleScope, final String manifestPath) {
        ModuleLayer.boot().modules().stream()
                .map(Module::getName)
                .filter(name -> name.startsWith(moduleScope))
                .forEach(name -> {
                    getManifestFromModule(name, manifestPath);
                });
    }

    private static void getManifestFromModule(final String moduleName, final String manifestPath) {
        Optional<Module> aModule = ModuleLayer.boot().findModule(moduleName);
        aModule.ifPresent(module -> {
            try {
                InputStream is = module.getResourceAsStream(manifestPath);
                readManifest(moduleName, manifestPath, is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void readManifest(final String moduleName, final String manifestPath, final InputStream is) {
        String manifest = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
        System.out.println("\"" + manifestPath + "\" from module \"" +  moduleName + "\": " + "\n" + manifest);
    }
}

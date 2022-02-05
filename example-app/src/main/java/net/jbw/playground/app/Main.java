package net.jbw.playground.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.module.ModuleDescriptor;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import net.jbw.playground.util.Calculator;

public class Main {

    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("POC for GraalVM NativeImage using the Java Platform Module System");
        System.out.println("Command: " + ProcessHandle.current().info().commandLine());
        System.out.println("Hello from Java Module: " + Main.class.getModule().getName());
        System.out.println("Required Java Module: " + Calculator.class.getModule().getName());
        System.out.println("Use module to calculate sum of '17' and '25': " + Calculator.add(17, 25) + "\n");

        final String packageName = Main.class.getPackage().getName();
        final String moduleScope = packageName.substring(0, packageName.lastIndexOf("."));
        printModulesInfo(moduleScope);
    }

    private static void printModulesInfo(final String moduleScope) {
        ModuleLayer.boot().modules().stream()
                .map(Module::getName)
                .filter(name -> name.startsWith(moduleScope))
                .forEach(name -> {
                    Optional<Module> aModule = ModuleLayer.boot().findModule(name);
                    aModule.ifPresent(module -> {
                        System.out.println("Module info for \"" + name + "\":");
                        printModuleDescriptor(module.getDescriptor());
                        printModuleResource(module, "META-INF/MANIFEST.MF");
                    });
                });
    }

    private static void printModuleDescriptor(final ModuleDescriptor descriptor) {
        System.out.println("    Module descriptor:");
        printModuleDescriptorRule("Packages", descriptor.packages());
        printModuleDescriptorRule("Modifiers", descriptor.modifiers());
        printModuleDescriptorRule("Opens", descriptor.opens());
        printModuleDescriptorRule("Provides", descriptor.provides());
        printModuleDescriptorRule("Requires", descriptor.requires());
        printModuleDescriptorRule("Uses", descriptor.uses());
    }

    private static <E> void printModuleDescriptorRule(final String ruleType, final Set<E> rules) {
        System.out.println("        " + ruleType + ":");
        rules.stream().forEach(rule -> {
            System.out.println("            " + rule);
        });
    }

    private static void printModuleResource(final Module module, final String resourcePath) {
        System.out.println("    Module resource \"" + resourcePath + "\":");
        try {
            InputStream is = module.getResourceAsStream(resourcePath);
            System.out.println(new BufferedReader(new InputStreamReader(is)).lines().map(line -> "       " + line).collect(Collectors.joining("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

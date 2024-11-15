package library.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.ProtectionDomain;

public class ClassLogger implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
      try {
        if (className.startsWith("library/")) {
          // Files.write(path, classfileBuffer); 
          System.out.println("paths: " + className);
        }
        
      } catch (Throwable ignored) {
      } finally { return classfileBuffer; }
    }
  }
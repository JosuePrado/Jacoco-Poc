package library;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class InstrumentCode {
    
    public void addPrintToByCode(String classPath, String methodName) throws IOException {
        String classFilePath = classPath;
        byte[] classBytes = Files.readAllBytes(new File(classFilePath).toPath());

        ClassReader classReader = new ClassReader(classBytes);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        classReader.accept(new ClassVisitor(Opcodes.ASM9, classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                    String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                if (methodName.equals(name)) {
                    return new MethodVisitor(Opcodes.ASM9, mv) {
                        @Override
                        public void visitCode() {
                            super.visitCode();
                            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            mv.visitLdcInsn("Métod called");
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",
                                    "(Ljava/lang/String;)V", false);
                        }
                    };
                }
                return mv;
            }
        }, 0);

        byte[] modifiedClassBytes = classWriter.toByteArray();
        try (FileOutputStream fos = new FileOutputStream(classFilePath)) {
            fos.write(modifiedClassBytes);
        }

        System.out.println("Class instrumented");
    }
}

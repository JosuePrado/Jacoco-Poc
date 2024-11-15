package library;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class InstrumentCode2 {
    
    public void addCounterToByteCode(String classPath, String methodName) throws IOException {
        String classFilePath = classPath;
        byte[] classBytes = Files.readAllBytes(new File(classFilePath).toPath());

        ClassReader classReader = new ClassReader(classBytes);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        classReader.accept(new ClassVisitor(Opcodes.ASM9, classWriter) {
            private boolean counterFieldAdded = false;

            @Override
            public void visitEnd() {
                if (!counterFieldAdded) {
                    FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "methodCallCounter", "I", null, 0);
                    if (fv != null) {
                        fv.visitEnd();
                    }
                    counterFieldAdded = true;
                }

                MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "getMethodCallCount", "()I", null, null);
                if (mv != null) {
                    mv.visitCode();
                    mv.visitFieldInsn(Opcodes.GETSTATIC, "myLibrary/Calculator", "methodCallCounter", "I");
                    mv.visitInsn(Opcodes.IRETURN);
                    mv.visitMaxs(1, 0);
                    mv.visitEnd();
                }

                super.visitEnd();
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                    String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                if (methodName.equals(name)) {
                    return new MethodVisitor(Opcodes.ASM9, mv) {
                        @Override
                        public void visitCode() {
                            super.visitCode();
                            mv.visitFieldInsn(Opcodes.GETSTATIC, "myLibrary/Calculator", "methodCallCounter", "I");
                            mv.visitInsn(Opcodes.ICONST_1);
                            mv.visitInsn(Opcodes.IADD);
                            mv.visitFieldInsn(Opcodes.PUTSTATIC, "myLibrary/Calculator", "methodCallCounter", "I");
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

        System.out.println("Class instrumented with counter");
    }
}

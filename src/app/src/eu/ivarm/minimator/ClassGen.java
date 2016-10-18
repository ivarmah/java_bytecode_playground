package app.src.eu.ivarm.minimator;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Label;

import java.io.FileOutputStream;

public class ClassGen {
    public static final String outPath = "./out/production/java_bytecode_playground/app/test/eu/ivarm/minimator/Minimator.class";

    public static void main(final String... args) throws Exception {
        final byte[] byteCode = new ClassGen().generateMathClass();
        try (FileOutputStream stream = new FileOutputStream(outPath)) {
            stream.write(byteCode);
        }
    }

    private byte[] generateMathClass() {
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(51,
                Opcodes.ACC_PUBLIC,
                "Math",
                null,
                "java/lang/Object",
                null);
        generateDefaultConstructor(cw);
        generateMinMethod(cw);
        generateMin2Method(cw);
        cw.visitEnd();
        return cw.toByteArray();
    }

    private void generateDefaultConstructor(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    private void generateMinMethod(final ClassWriter cw ) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "min", // method name
                "(II)I", // method descriptor
                null,    // exceptions
                null);   // method attributes
        mv.visitCode();
        final Label elseLabel = new Label();
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, elseLabel);
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitLabel(elseLabel);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
    }

    private void generateMin2Method(final ClassWriter cw ) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "min",    // method name
                "(III)I", // method descriptor
                null,     // exceptions
                null);    // method attributes
        mv.visitCode();
        final Label elseLabel = new Label();
        final Label elseLabel2 = new Label();
        // comparing local variable 0 with 1 and saving min to the 0
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitJumpInsn(Opcodes.IF_ICMPLE, elseLabel);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ISTORE, 0);
        mv.visitLabel(elseLabel);

        // comparing local variable 0 with 2 and saving min to the 0
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitJumpInsn(Opcodes.IF_ICMPLE, elseLabel2);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ISTORE, 0);
        mv.visitLabel(elseLabel2);

        // returning local variable 0 (since it's the minimum)
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
    }

}

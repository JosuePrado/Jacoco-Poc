package library.agent;

import java.lang.instrument.Instrumentation;

public class MyAgent {
    public static void premain(String args, Instrumentation instrumentation){
        ClassLogger transformer = new ClassLogger();
        instrumentation.addTransformer(transformer);
      }
}

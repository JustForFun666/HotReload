package agent;

import hotreload.LoadUtils;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

/**
 * Created by zmm on 18-1-1.
 */
public class HotReloadAgent
{
    public static void agentmain(String agentArgs, Instrumentation inst)
    {
        try
        {
//            inst.addTransformer(new MyTransformer(),true);
//            inst.retransformClasses(HotReloadUtils.class);
            ClassDefinition cd = new ClassDefinition(HotReloadUtils.class,
                    LoadUtils.getBytesFromFils("HotReloadUtils.class"));
            inst.redefineClasses(cd);
            System.out.println("reload done.");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

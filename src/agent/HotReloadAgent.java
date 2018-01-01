package agent;

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
            inst.addTransformer(new MyTransformer());
            inst.retransformClasses(HotReloadUtils.class);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

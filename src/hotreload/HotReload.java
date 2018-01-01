package hotreload;

import com.sun.tools.attach.VirtualMachine;

/**
 * Created by zmm on 18-1-1.
 */
public class HotReload
{
    public static void main(String[] args)
    {
        String jar = "/home/zmm/GitHub/HotReload/lib/HotReloadAgent.jar";

        try
        {
            //连接JVM
            VirtualMachine vm = VirtualMachine.attach("12");
            vm.loadAgent(jar);
            vm.detach();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}

package hotreload;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.spi.AttachProvider;

import java.util.List;

/**
 * Created by zmm on 18-1-1.
 */
public class HotReload
{
    public static void main(String[] args)
    {
        String jar = "/home/zmm/GitHub/HotReload/lib/HotReloadAgent.jar";

        VirtualMachine vm = null;
        List<AttachProvider> providers = AttachProvider.providers();
        for (AttachProvider provider : providers)
        {
            try
            {
                vm = provider.attachVirtualMachine("9333");
                vm.loadAgent(jar);
                break;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                continue;
            }
        }

//        try
//        {
//            //连接JVM
//            VirtualMachine vm = VirtualMachine.attach("2422");
//            vm.loadAgent(jar);
//            vm.detach();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }

    }
}

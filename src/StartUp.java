import agent.HotReloadUtils;

/**
 * Created by zmm on 18-1-1.
 */
public class StartUp
{
    public static void main(String[] args)
    {
        while (true)
        {
            HotReloadUtils.sayHello();

            try
            {
                Thread.sleep(10000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

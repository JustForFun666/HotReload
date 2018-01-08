package agent;

import hotreload.LoadUtils;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by zmm on 18-1-1.
 */
public class MyTransformer implements ClassFileTransformer
{
    private final String RELOAD_CLASS_PATH = "/home/zmm/GitHub/HotReload/reloadclass/";

    private final String EXTENTION = ".class";

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException
    {
        System.out.println("begin reload class:" + className);
        int index = className.lastIndexOf('.');
        if (-1 == index)
        {
            return null;
        }

        String clzNameAbs = RELOAD_CLASS_PATH + className.substring(0, index) + EXTENTION;
        System.out.println("begin get class bytes:" + clzNameAbs);
        return LoadUtils.getBytesFromFils(clzNameAbs);
    }


}

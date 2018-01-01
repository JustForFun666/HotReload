package agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by zmm on 18-1-1.
 */
public class MyTransformer implements ClassFileTransformer
{
    private final String RELOAD_CLASS_PATH = "/home/zmm/GitHub/HotReload/reloadclass";

    private final String EXTENTION = ".class";

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException
    {
        int index = className.lastIndexOf('.');
        if (-1 == index)
        {
            return null;
        }

        String clzNameAbs = RELOAD_CLASS_PATH + className.substring(0, index) + EXTENTION;
        System.out.println("begin reload class:" + clzNameAbs);
        return getBytesFromFils(clzNameAbs);
    }

    /**
     * 将class文件序列化
     * @param fileName 待序列化的文件名
     * @return bytes
     */
    private static byte[] getBytesFromFils(String fileName)
    {
        File classFile = new File(fileName);

        long fileLen = classFile.length();

        try (InputStream is = new FileInputStream(classFile))
        {
            byte[] bytes = new byte[(int)fileLen];

            int offset = 0;
            int readCnt = 0;
            while(offset < fileLen && (readCnt = is.read(bytes, offset, bytes.length - offset)) > 0)
            {
                offset += readCnt;
            }

            if (offset < bytes.length)
            {
                throw new Exception("Not readfully class file.");
            }

            return bytes;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}

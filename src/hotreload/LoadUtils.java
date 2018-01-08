package hotreload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by zmm on 18-1-8.
 */
public class LoadUtils
{
    private static final String RELOAD_CLASS_PATH = "/home/zmm/GitHub/HotReload/reloadclass";

    private static final String EXTENTION = ".class";

    /**
     * 将class文件序列化
     * @param className 待序列化的文件名
     * @return bytes
     */
    public static byte[] getBytesFromFils(String className)
    {
        int index = className.lastIndexOf('.');
        if (-1 == index)
        {
            return null;
        }

//        String clzNameAbs = RELOAD_CLASS_PATH + File.separator + className;
        String clzNameAbs = "/home/zmm/GitHub/HotReload/reloadclass/HotReloadUtils.class";
        System.out.println("begin get class bytes:" + clzNameAbs);

        File classFile = new File(clzNameAbs);

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
            System.out.println("get bytes success:" + clzNameAbs);
            is.close();
            return bytes;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args)
    {
        System.out.println(LoadUtils.getBytesFromFils("HotReloadUtils.class"));
    }
}

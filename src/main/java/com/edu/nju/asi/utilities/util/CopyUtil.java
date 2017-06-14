package com.edu.nju.asi.utilities.util;

import java.io.*;
import java.util.List;

/**
 * Created by Harvey on 2017/6/14.
 */
public class CopyUtil {

    @SuppressWarnings("unchecked")
    public static <T> List<T> deepCopyList(List<T> src)
    {
        List<T> dest = null;
        try
        {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List<T>) in.readObject();
        }
        catch (IOException e)
        {

        }
        catch (ClassNotFoundException e)
        {

        }
        return dest;
    }

}

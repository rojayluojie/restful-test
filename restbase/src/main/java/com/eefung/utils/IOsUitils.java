package com.eefung.utils;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/6/28.
 * @author luojie<luojie@eefung.com>
 */
public class IOsUitils {
    public static String getStringFromInputStream(InputStream in) throws Exception {
        CachedOutputStream bos = new CachedOutputStream();
        IOUtils.copy(in, bos);
        in.close();
        bos.close();
        return bos.getOut().toString();
    }
}

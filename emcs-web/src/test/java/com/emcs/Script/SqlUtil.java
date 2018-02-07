package com.emcs.Script;

import java.io.File;

public class SqlUtil {
    public static String getDbScriptDir() {
        return new File(new File(Class.class.getClass().getResource("/").getPath()).getParent()).getParent()+"/dbScript";
    }
}

package org.yuequan.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * The type Class util.
 * @author yuequan
 */
public class ClassUtil {
    /**
     * 文件名称编码
     */
    public static final String DECODING = "UTF-8";

    /** Class文件后缀 */
    private static final String CLASS_SUFFIX = ".class";


    /**
     * Gets classes.
     *
     * @param packageNames the package names
     * @return the classes
     */
    public static Set<Class<?>> getClasses(String... packageNames) {
        Set<Class<?>> classes = new LinkedHashSet<>();
        getClasses(classes, packageNames);
        return classes;
    }

    private static void getClasses(Collection<Class<?>> classes, String... packageNames) {
        //recursive
        boolean recursive = true;
        for (String packageName : packageNames) {
            if (packageName == null || packageName.length() == 0 || packageName.trim().length() == 0)
                continue;
            String packageDirName = packageName.replace('.', '/');
            Enumeration<URL> dirs = null;
            try {
                dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
                while (dirs.hasMoreElements()) {
                    URL url = dirs.nextElement();
                    //protocol
                    String protocol = url.getProtocol();
                    //if file
                    if ("file".equals(protocol)) {
                        String filePath = URLDecoder.decode(url.getFile(), DECODING);
                        //以文件的方式扫描整个包下的文件 并添加到集合中
                        getClassesByPackageFile(packageName, filePath, recursive, classes);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void getClassesByPackageFile(String packageName, String packagePath, final boolean recursive,
                                                Collection<Class<?>> classes) {
        //package directory
        File dir = new File(packagePath);
        //not exists or not directory
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirfiles = dir.listFiles(file -> (recursive && file.isDirectory())
                || (file.getName().endsWith(CLASS_SUFFIX)));
        for (File file : dirfiles) {
            //if directory
            if (file.isDirectory()) {
                getClassesByPackageFile(packageName + '.' + file.getName(),
                        file.getAbsolutePath(), recursive, classes);
            } else {
                //remove .class suffix
                String className = file.getName().substring(0, file.getName().length() - 6);
                Class<?> cls = loadClassQuietly(packageName + '.' + className);
                if (cls != null)
                    classes.add(cls);
            }
        }
    }

    /**
     * Load class quietly class.
     *
     * @param className the class name
     * @return the class
     */
    public static Class<?> loadClassQuietly(String className) {
        try {
            return loadClass(className);
        } catch (Throwable thr) {
            //ignore
        }
        return null;
    }

    /**
     * Load class class.
     *
     * @param className the class name
     * @return the class
     * @throws ClassNotFoundException the class not found exception
     */
    public static Class<?> loadClass(String className) throws ClassNotFoundException {
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        ClassLoader loader = contextCL == null ? ClassUtil.class.getClassLoader() : contextCL;
        return loader.loadClass(className);
    }
}

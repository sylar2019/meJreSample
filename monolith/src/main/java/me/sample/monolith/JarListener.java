package me.sample.monolith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.loader.LaunchedURLClassLoader;
import org.springframework.boot.loader.jar.JarFile;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;

/**
 * File Name             :  JarListener
 *
 * @author :  sylar
 * Create                :  2020/5/18
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) allthings.vip  All Rights Reserved
 * *******************************************************************************************
 */
public class JarListener {
    private static final String BOOT_INFO_DIR = "BOOT-INF/classes/";
    private static final String CLASS_SUFFIX = ".class";
    private File jarFile;

    @Autowired
    private ApplicationContext applicationContext;

    public JarListener(File jarFile) {
        this.jarFile = jarFile;
    }

    public void printClass() {
        try {
            URL url = jarFile.toURI().toURL();
            ModuleClassLoader classLoader = new ModuleClassLoader(new URL[]{url},
                    getClass().getClassLoader());


            JarFile jar = new JarFile(jarFile);
            JarEntry entry;
            String className;

            Enumeration<JarEntry> enumFiles = jar.entries();
            while (enumFiles.hasMoreElements()) {
                entry = enumFiles.nextElement();
                if (entry.getName().startsWith(BOOT_INFO_DIR)
                        && entry.getName().endsWith(CLASS_SUFFIX)) {
                    className = entry.getName();
                    className = className.replace(BOOT_INFO_DIR, "");
                    className = className.replace(CLASS_SUFFIX, "");
                    className = className.replace("/", ".");

                    Class<?> myclass = classLoader.loadClass(className);
                    //打印类名
                    System.out.println("全类名:" + myclass.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

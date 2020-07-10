package me.sample.monolith;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 *
 * File Name             :  FileListener
 *
 * @author :  sylar
 * Create                :  2020/5/15
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
@Service
public class FileListener extends FileAlterationListenerAdaptor {
    private Logger log = Logger.getLogger(FileListener.class);
    private IOFileFilter fileFilter = FileFilterUtils.suffixFileFilter(".jar");
    private String dcsJarPath;

    public FileListener() {
        dcsJarPath = System.getProperty("user.dir") + "/dcs";

        firstScan();
        startMonitor();
    }

    private void firstScan() {
        File directory = new File(dcsJarPath);
        if (!directory.exists()) {
            return;
        }

        Collection<File> files = FileUtils.listFiles(directory,
                fileFilter,
                DirectoryFileFilter.DIRECTORY);
        files.forEach(file -> {
            JarListener jarListener = new JarListener(file);
            jarListener.printClass();

        });
    }

    private void startMonitor() {
        FileAlterationObserver observer = new FileAlterationObserver(
                new File(dcsJarPath),
                fileFilter);
        observer.addListener(this);

        FileAlterationMonitor monitor = new FileAlterationMonitor(
                TimeUnit.SECONDS.toMillis(3),
                observer);
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件创建执行
     */
    @Override
    public void onFileCreate(File file) {
        log.info("[新建]:" + file.getAbsolutePath());

//        JarListener jarListener = new JarListener(file);
    }

    /**
     * 文件创建修改
     */
    @Override
    public void onFileChange(File file) {
        log.info("[修改]:" + file.getAbsolutePath());
    }

    /**
     * 文件删除
     */
    @Override
    public void onFileDelete(File file) {
        log.info("[删除]:" + file.getAbsolutePath());
    }

    /**
     * 目录创建
     */
    @Override
    public void onDirectoryCreate(File directory) {
        log.info("[新建]:" + directory.getAbsolutePath());
    }

    /**
     * 目录修改
     */
    @Override
    public void onDirectoryChange(File directory) {
        log.info("[修改]:" + directory.getAbsolutePath());
    }

    /**
     * 目录删除
     */
    @Override
    public void onDirectoryDelete(File directory) {
        log.info("[删除]:" + directory.getAbsolutePath());
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStart(observer);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStop(observer);
    }

}

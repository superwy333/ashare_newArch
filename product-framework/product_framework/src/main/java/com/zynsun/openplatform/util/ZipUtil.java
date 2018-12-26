package com.zynsun.openplatform.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.*;

/**
 * @ClassName: ZipUtil 
 * @Description: 文件压缩/解压
 * @author liwuyang
 * @date 2016年5月19日 上午1:36:50 
 *
 */
public class ZipUtil {

    private static final int BUFFER = 8192;

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * 
     * 
     * @param srcPathName
     *            源文件(夹)路径
     * @param zipPathName
     *            压缩后文件路径
     */
    public static void compress(String srcPathName, String zipPathName) {
        compress(srcPathName, zipPathName, true);
    }

    public static void compress(String srcPathName, String zipPathName, boolean hasFirstDir) {
        File file = new File(srcPathName);
        File zipFile = new File(zipPathName);
        ZipOutputStream out = null;
        if (!file.exists()) {
            throw new RuntimeException(srcPathName + "NOT EXIST!");
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            out = new ZipOutputStream(cos);
            compress(file, out, "", hasFirstDir);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }

    private static void compress(File file, ZipOutputStream out, String basedir, boolean hasFirstDir) {
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            LOGGER.error("File compression：" + basedir + file.getName());
            compressDirectory(file, out, basedir, hasFirstDir);
        } else {
            LOGGER.error("File compression：" + basedir + file.getName());
            compressFile(file, out, basedir);
        }
    }

    /** 压缩一个目录 */
    private static void compressDirectory(File dir, ZipOutputStream out, String basedir, boolean hasFirstDir) {
        if (!dir.exists()) {
            return;
        }
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            /* 递归 */
            compress(files[i], out, basedir + (hasFirstDir ? "" : (dir.getName() + "/")), false);
        }
    }

    /** 压缩一个文件 */
    private static void compressFile(File file, ZipOutputStream out, String basedir) {
        if (!file.exists()) {
            return;
        }
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(basedir + file.getName());
            out.putNextEntry(entry);
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 解压缩
     * 
     * @param zipPathName
     * 					源zip路径
     * @param outPathName
     * 					解压文件输出路径
     */
    public static void decompressionFile(String zipPathName, String outPathName) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPathName));
            BufferedOutputStream bufferedOutputStream = null;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(zipInputStream);
            File file = null;
            ZipEntry entry = null;
            try {
                entry = zipInputStream.getNextEntry();
                while ((entry != null) && !entry.isDirectory()) {
                    file = new File(outPathName, entry.getName());
                    boolean flag = false;
                    if (!file.exists()) {
                        flag = file.mkdirs();
                        if (!flag) {
                            LOGGER.error("文件创建失败<{zipPath}>", file.getAbsolutePath());
                            return;
                        }
                    }

                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    int b;
                    while ((b = bufferedInputStream.read()) != -1) {
                        bufferedOutputStream.write(b);
                    }
                    LOGGER.error(file + "decompression success !");
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            } finally {
                if ((bufferedInputStream != null) || (bufferedOutputStream != null) || (zipInputStream != null)) {
                    try {
                        bufferedInputStream.close();
                        bufferedOutputStream.close();
                        zipInputStream.close();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static byte[] toByteArray(String filename) {

        File f = new File(filename);
        if (!f.exists()) {
            throw new RuntimeException(filename + "NOT EXIST!");
        }
        ByteArrayOutputStream bos = null;
        BufferedInputStream in = null;

        byte[] byteArray = null;
        try {
            bos = new ByteArrayOutputStream((int) f.length());
            in = new BufferedInputStream(new FileInputStream(f));
            int bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len);
            }

            byteArray = bos.toByteArray();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
        return byteArray;
    }
}
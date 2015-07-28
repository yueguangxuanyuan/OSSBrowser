// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageResource.java

package com.aliyun.oss.ossbrowser.utils;

import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageResource
{

    public ImageResource()
    {
    }

    public static final Icon logWarningIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/log_warning.png"));
    public static final Icon logErrorIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/log_error.png"));
    public static final Icon newBucketIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/new_small.png"));
    public static final Icon deleteBucketIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/delete_small.png"));
    public static final Icon refreshBucketIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/refresh.png"));
    public static final Icon loadingIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/loading.gif"));
    public static final Icon pauseIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/pause.png"));
    public static final Icon downloadIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/download.png"));
    public static final Icon uploadIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/upload.png"));
    public static final Icon uploadFileIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/upload_file.png"));
    public static final Icon deleteIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/delete.png"));
    public static final Icon newFolder = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/new.png"));
    public static final Icon refreshIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/refresh_bucket.png"));
    public static final Icon triangleIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/triangle.png"));
    public static final Icon upIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/up.png"));
    public static final Icon bucketIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/bucket4.png"));
    public static final Icon fileIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/file.png"));
    public static final Icon folderIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/folder.png"));
    public static final Icon findIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/find.png"));
    public static final Icon defaultIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/default.png"));
    public static final ImageIcon trayIcon = new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/tray.png"));
    public static HashMap sEndingToIcon;

    static 
    {
        sEndingToIcon = new HashMap();
        sEndingToIcon.put("htm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_htm.png")));
        sEndingToIcon.put("html", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_html.png")));
        sEndingToIcon.put("asc", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_asc.png")));
        sEndingToIcon.put("txt", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_txt.png")));
        sEndingToIcon.put("ini", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_ini.png")));
        sEndingToIcon.put("java", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_java.png")));
        sEndingToIcon.put("c", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_c.png")));
        sEndingToIcon.put("cc", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_cc.png")));
        sEndingToIcon.put("cpp", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_cpp.png")));
        sEndingToIcon.put("log", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_log.png")));
        sEndingToIcon.put("xsl", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xsl.png")));
        sEndingToIcon.put("xml", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xml.png")));
        sEndingToIcon.put("css", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_css.png")));
        sEndingToIcon.put("rtx", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_rtx.png")));
        sEndingToIcon.put("rtf", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_rtf.png")));
        sEndingToIcon.put("sgm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_sgm.png")));
        sEndingToIcon.put("sgml", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_sgml.png")));
        sEndingToIcon.put("chm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_chm.png")));
        sEndingToIcon.put("tsv", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_tsv.png")));
        sEndingToIcon.put("csv", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_csv.png")));
        sEndingToIcon.put("doc", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_doc.png")));
        sEndingToIcon.put("dot", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_dot.png")));
        sEndingToIcon.put("docx", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_docx.png")));
        sEndingToIcon.put("dotx", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_dotx.png")));
        sEndingToIcon.put("docm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_docm.png")));
        sEndingToIcon.put("dotm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_dotm.png")));
        sEndingToIcon.put("xls", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xls.png")));
        sEndingToIcon.put("xlt", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xlt.png")));
        sEndingToIcon.put("xla", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xla.png")));
        sEndingToIcon.put("xlsx", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xlsx.png")));
        sEndingToIcon.put("xltx", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xltx.png")));
        sEndingToIcon.put("xlsm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xlsm.png")));
        sEndingToIcon.put("xltm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xltm.png")));
        sEndingToIcon.put("xlam", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xlam.png")));
        sEndingToIcon.put("xlsb", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xlsb.png")));
        sEndingToIcon.put("ppt", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_ppt.png")));
        sEndingToIcon.put("pot", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_pot.png")));
        sEndingToIcon.put("pps", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_pps.png")));
        sEndingToIcon.put("ppa", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_ppa.png")));
        sEndingToIcon.put("pptx", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_pptx.png")));
        sEndingToIcon.put("potx", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_potx.png")));
        sEndingToIcon.put("ppsx", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_ppsx.png")));
        sEndingToIcon.put("ppam", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_ppam.png")));
        sEndingToIcon.put("pptm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_pptm.png")));
        sEndingToIcon.put("potm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_potm.png")));
        sEndingToIcon.put("ppsm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_ppsm.png")));
        sEndingToIcon.put("pdf", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_pdf.png")));
        sEndingToIcon.put("ps", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_ps.png")));
        sEndingToIcon.put("ai", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_ai.png")));
        sEndingToIcon.put("eps", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_eps.png")));
        sEndingToIcon.put("js", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_js.png")));
        sEndingToIcon.put("latex", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_latex.png")));
        sEndingToIcon.put("sh", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_sh.png")));
        sEndingToIcon.put("tex", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_tex.png")));
        sEndingToIcon.put("js", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_js.png")));
        sEndingToIcon.put("tar", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_tar.png")));
        sEndingToIcon.put("zip", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_zip.png")));
        sEndingToIcon.put("tar", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_tar.png")));
        sEndingToIcon.put("tar", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_tar.png")));
        sEndingToIcon.put("tar", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_tar.png")));
        sEndingToIcon.put("swf", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_swf.png")));
        sEndingToIcon.put("bmp", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_bmp.png")));
        sEndingToIcon.put("gif", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_gif.png")));
        sEndingToIcon.put("jpg", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_jpg.png")));
        sEndingToIcon.put("jpe", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_jpe.png")));
        sEndingToIcon.put("jpeg", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_jpeg.png")));
        sEndingToIcon.put("png", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_png.png")));
        sEndingToIcon.put("tif", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_tif.png")));
        sEndingToIcon.put("tiff", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_tiff.png")));
        sEndingToIcon.put("pnm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_pnm.png")));
        sEndingToIcon.put("pbm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_pbm.png")));
        sEndingToIcon.put("pgm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_pgm.png")));
        sEndingToIcon.put("xbm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xbm.png")));
        sEndingToIcon.put("xwd", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_xwd.png")));
        sEndingToIcon.put("wrl", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_wrl.png")));
        sEndingToIcon.put("vrml", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_vrml.png")));
        sEndingToIcon.put("mpg", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mpg.png")));
        sEndingToIcon.put("mp4", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mp4.png")));
        sEndingToIcon.put("rmvb", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_rmvb.png")));
        sEndingToIcon.put("rm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_rm.png")));
        sEndingToIcon.put("mkv", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mkv.png")));
        sEndingToIcon.put("flv", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_flv.png")));
        sEndingToIcon.put("mpe", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mpe.png")));
        sEndingToIcon.put("mpeg", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mpeg.png")));
        sEndingToIcon.put("mov", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mov.png")));
        sEndingToIcon.put("qt", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_qt.png")));
        sEndingToIcon.put("mxu", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mxu.png")));
        sEndingToIcon.put("avi", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_avi.png")));
        sEndingToIcon.put("movie", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_movie.png")));
        sEndingToIcon.put("3gp", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_3gp.png")));
        sEndingToIcon.put("au", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_au.png")));
        sEndingToIcon.put("snd", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_snd.png")));
        sEndingToIcon.put("mid", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mid.png")));
        sEndingToIcon.put("midi", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_midi.png")));
        sEndingToIcon.put("kar", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_kar.png")));
        sEndingToIcon.put("aif", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_aif.png")));
        sEndingToIcon.put("aiff", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_aiff.png")));
        sEndingToIcon.put("aifc", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_aifc.png")));
        sEndingToIcon.put("mpga", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mpga.png")));
        sEndingToIcon.put("mp2", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mp2.png")));
        sEndingToIcon.put("mp3", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mp3.png")));
        sEndingToIcon.put("m3u", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_m3u.png")));
        sEndingToIcon.put("ram", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_ram.png")));
        sEndingToIcon.put("rm", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_rm.png")));
        sEndingToIcon.put("ra", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_ra.png")));
        sEndingToIcon.put("wav", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_wav.png")));
        sEndingToIcon.put("amr", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_amr.png")));
        sEndingToIcon.put("3gpp", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_3gpp.png")));
        sEndingToIcon.put("wma", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_wma.png")));
        sEndingToIcon.put("wmv", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_wmv.png")));
        sEndingToIcon.put("url", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_url.png")));
        sEndingToIcon.put("apk", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_apk.png")));
        sEndingToIcon.put("epub", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_epub.png")));
        sEndingToIcon.put("prc", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_prc.png")));
        sEndingToIcon.put("mobi", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_mobi.png")));
        sEndingToIcon.put("cbr", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_cbr.png")));
        sEndingToIcon.put("cbz", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_cbz.png")));
        sEndingToIcon.put("cbt", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_cbt.png")));
        sEndingToIcon.put("cba", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_cba.png")));
        sEndingToIcon.put("cb7", new ImageIcon(com/aliyun/oss/ossbrowser/utils/ImageResource.getResource("/com/aliyun/oss/ossbrowser/img/icon_cb7.png")));
    }
}

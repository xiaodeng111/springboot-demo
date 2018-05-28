package com.demo.clockin.common.lang;

import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.constant.Property;
import com.demo.clockin.common.api.exception.ErrorCode;
import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.constant.Property;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: CKImageUploadUtil
 * @Description: 图片上传工具类，包括CKEditor操作
 */
public class CKImageUploadUtil {
    private static final Log logger = LogFactory.getLog(CKImageUploadUtil.class);

    // 图片类型
    private static List<String> fileTypes = new ArrayList<String>();

    static {
        fileTypes.add(".jpg");
        fileTypes.add(".jpeg");
        fileTypes.add(".bmp");
        fileTypes.add(".gif");
        fileTypes.add(".png");
    }

    /**
     * 图片上传
     *
     * @Title upload
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String upload(HttpServletRequest request) throws IllegalStateException,
            IOException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());
        // 图片名称
        String fileName = null;
        // 判断 request 是否有文件上传,即多部分请求
        try {
            if (multipartResolver.isMultipart(request)) {
                // 转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    // 记录上传过程起始时的时间，用来计算上传时间
                    // int pre = (int) System.currentTimeMillis();
                    // 取得上传文件
                    MultipartFile file = multiRequest.getFile(iter.next());
                    if (file != null) {
                        // 取得当前上传文件的文件名称
                        String myFileName = file.getOriginalFilename();
                        // 如果名称不为'',说明该文件存在，否则说明该文件不存在
                        if (myFileName.trim() != "") {
                            // 获得图片的原始名称
                            String originalFilename = file.getOriginalFilename();
                            // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                            if (!fileTypes.contains(suffix)) {
                                continue;
                            }
                            //不使用原始文件名
                            fileName = Property.FILE_IMAG_UPLOADPATH +"/"+ DateUtil.getDateString()+ "/"+  BCryptUtil.randomUUID() + ".jpg";
                            File uploadFile = new File(Property.FILE_UPLOAD_ROOTPATH + Constants.separator+  fileName);
                            if(!uploadFile.getParentFile().exists()) {
                                if(!uploadFile.getParentFile().mkdirs()) {
                                    logger.error("create directory failed, {}"+uploadFile.getPath());
                                    fileName = StringUtil.EMPTY;
                                    ErrorCode.UPLOAD_IMAGE_FAILED.issue();
                                }
                            }
                            file.transferTo(uploadFile);
                        }
                    }
                }
            }
        }catch (Exception e) {
            logger.error("upload file failed ", e);
            fileName = StringUtil.EMPTY;
            ErrorCode.UPLOAD_IMAGE_FAILED.issue();
        }finally {
            return fileName;
        }
    }

    /**
     * ckeditor文件上传功能，回调，传回图片路径，实现预览效果。
     *
     * @Title ckeditor
     * @param request
     * @param response
     * @throws IOException
     */
    public static void ckeditor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String fileName = upload(request);
        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.println("<script type=\"text/javascript\">");
        if(StringUtil.isNotEmpty(fileName)){
            // 结合ckeditor功能
            String imageContextPath = Property.FILE_UPLOAD_ROOTURL + fileName;
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");

        }else{
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'图片上传失败');");
        }
        out.println("</script>");
        out.flush();
        out.close();
    }
}

package com.demo.clockin.controller.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.clockin.common.api.ApiResult;
import com.demo.clockin.common.api.ApiResultWapper;
import com.demo.clockin.common.api.exception.ErrorCode;
import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.constant.Property;
import com.demo.clockin.common.controller.BaseController;
import com.demo.clockin.common.lang.*;
import com.demo.clockin.domain.param.ImageParam;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * 图片上传Action
 *
 * @author dengrq
 *
 */

@Controller
@RequestMapping("/upload")
public class UploadImageController extends BaseController {

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject upload(@RequestBody ImageParam imageParam, HttpServletRequest request, HttpServletResponse response) {
		int _x1 = StringUtils.toInteger(imageParam.getX1());
		int _y1 = StringUtils.toInteger(imageParam.getY1());
		int _x2 = StringUtils.toInteger(imageParam.getX2());
		int _y2 = StringUtils.toInteger(imageParam.getY2());
		JSONObject resultMap = new JSONObject();
		String relativePath = uploadRelativePath(imageParam.getImage(), _x1, _y1, _x2, _y2);
		if (relativePath != null) {
			resultMap.put("result", "1");
			resultMap.put("logoUrl", Property.FILE_UPLOAD_ROOTURL + Constants.separator + relativePath);
			resultMap.put("logo", relativePath);
		} else {
			resultMap.put("result", "0");
			resultMap.put("message", "上传失败");
		}
		logger.info("上传图片：" + JSON.toJSONString(resultMap));
		return resultMap;
	}

	/**
	 * 广告图片上传（不需要截取，直接上传即可）
	 * @param imageParam
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/adImage", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject uploadForAd(@RequestBody ImageParam imageParam, HttpServletRequest request, HttpServletResponse response) {
		JSONObject resultMap = new JSONObject();

		String dateFolder = FileUtil.genFolderName();
		String fileName = FileUtil.genFileName().concat(".jpg");
		String sourceFolder = genSourceFolder(dateFolder);
		// 判断sourceFolder是否存在，不存在创建之
		File sourceForder = new File(sourceFolder);
		// 判断头像保存目录是否存在
		try{
			if(!sourceForder.isDirectory() && !sourceForder.exists()) {
				sourceForder.mkdirs();
			}
		}catch (Exception e){
			ErrorCode.UPLOAD_IMAGE_FAILED.issue();
		}
		// 相对文件全路径，保存入库，显示时使用
		String relativePath = genRelativePath(dateFolder, fileName);
		String path = genSourcePath(sourceFolder, fileName);

		boolean isCreate = ImageUtil.base64ToImage(imageParam.getImage(), path);
		if (isCreate && relativePath != null) {
			try {
				// 广告图片，统一压缩尺寸 640*180
				ImageUtil.reduceImageByWidthHeight(path, path, 640, 180);
				resultMap.put("result", "1");
				resultMap.put("logoUrl", Property.FILE_UPLOAD_ROOTURL + Constants.separator + relativePath);
				resultMap.put("logo", relativePath);
			} catch (IOException e) {
				e.printStackTrace();
				resultMap.put("result", "0");
				resultMap.put("message", "上传失败");
			}

		} else {
			resultMap.put("result", "0");
			resultMap.put("message", "上传失败");
		}

		logger.info("上传图片：" + JSON.toJSONString(resultMap));
		return resultMap;
	}

	/**
	 * 上传不截图的图片
	 */
	@RequestMapping(value = "/image/baseStr", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult upload(@RequestParam("file") String file) {
		byte[] bytes = null;
		String locationPath = StringUtil.EMPTY;
		OutputStream outputStream = null;
		try {
			file = file.substring(22, file.length());
			bytes = Base64.decodeBase64(file);
			String uuid = BCryptUtil.randomUUID();

			//不使用原始文件名
			locationPath = Property.FILE_IMAG_UPLOADPATH +"/"+ DateUtil.getDateString()+ "/"+ uuid + ".jpg";
			File imageFile = new File(Property.FILE_UPLOAD_ROOTPATH +Constants.separator+  locationPath);

			if(!imageFile.getParentFile().exists()) {
				if(!imageFile.getParentFile().mkdirs()) {
					logger.error("create directory failed, {}"+imageFile.getPath());
					ErrorCode.UPLOAD_IMAGE_FAILED.issue();
				}
			}
			outputStream = new FileOutputStream(imageFile);
			outputStream.write(bytes);
		} catch (Exception e) {
			logger.error("upload file failed ", e);
			ErrorCode.UPLOAD_IMAGE_FAILED.issue();
		} finally {
			IOUtil.close(outputStream);
		}

		return ApiResultWapper.getInstance(locationPath);
	}


	private String uploadRelativePath(String base64Code, int x1, int y1, int x2, int y2) {
		String dateFolder = FileUtil.genFolderName();

		String fileName = FileUtil.genFileName().concat(".jpg");

		String sourceFolder = genSourceFolder(dateFolder);

		// 判断sourceFolder是否存在，不存在创建之
		File sourceForder = new File(sourceFolder);
		// 判断头像保存目录是否存在
		try{
			if(!sourceForder.isDirectory() && !sourceForder.exists()) {
				sourceForder.mkdirs();
			}
		}catch (Exception e){
			ErrorCode.UPLOAD_IMAGE_FAILED.issue();
		}
		// 相对文件全路径，保存入库，显示时使用
		String relativePath = genRelativePath(dateFolder, fileName);

		String path = genSourcePath(sourceFolder, fileName);

		try{
			//创建原始文件(先强制修改为jpg格式)
			boolean isCreate = ImageUtil.base64ToImage(base64Code, path);
			if(isCreate){
				float scale = ImageUtil.getScaleCutImage(path);//比例
				int width = (int) ((x2-x1)*scale);
				int height = width;
				int start_x = (int) (x1*scale);
				int start_y = (int) (y1*scale);
				//剪切图片
				ImageUtil.cutImage(path, path, start_x, start_y, width, height);
				//剪切以后的图片压缩到固定大小的图片
				ImageUtil.chgPic(path, path, 200, 200);//200*200大图
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return relativePath;
	}

	/**
	 * 获取文件保存目录
	 * @param dateFolder
	 * @return
	 */
	private String genSourceFolder(String dateFolder) {
		StringBuffer path = new StringBuffer(Property.FILE_UPLOAD_ROOTPATH);
		path.append(Constants.separator);
		if(null != Property.FILE_IMAG_UPLOADPATH && !"".equals(Property.FILE_IMAG_UPLOADPATH)) {
			path.append(Property.FILE_IMAG_UPLOADPATH);
			path.append(Constants.separator);
		}
		path.append(dateFolder);
		return path.toString();
	}

	/**
	 * 获取文件相对路径
	 * @param dateFolder
	 * @param fileName
	 * @return
	 */
	private String genRelativePath(String dateFolder,String fileName) {
		StringBuffer sb = new StringBuffer();
		if(null != Property.FILE_IMAG_UPLOADPATH && !"".equals(Property.FILE_IMAG_UPLOADPATH)) {
			sb.append(Property.FILE_IMAG_UPLOADPATH);
			sb.append(Constants.separator);
		}
		sb.append(dateFolder);
		sb.append(Constants.separator);
		sb.append(fileName);
		return sb.toString();
	}

	/**
	 * 获取文件物理全路径
	 * @param sourceFolder
	 * @param fileName
	 * @return
	 */
	private String genSourcePath(String sourceFolder, String fileName) {
		StringBuffer sb = new StringBuffer(sourceFolder);
		sb.append(Constants.separator);
		sb.append(fileName);
		return sb.toString();
	}

}

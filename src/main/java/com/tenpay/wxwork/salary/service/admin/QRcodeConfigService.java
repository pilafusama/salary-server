package com.tenpay.wxwork.salary.service.admin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenpay.wxwork.salary.common.utils.QRcodeUtils;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.dao.QRcodeConfigDao;
import com.tenpay.wxwork.salary.dto.admin.BankInfo;
import com.tenpay.wxwork.salary.dto.admin.QRcodeConfigParam;
import com.tenpay.wxwork.wxworklib.WxworkConfig;

@Service
public class QRcodeConfigService {
	
	public static final String QRCODE_DEFAULT_CHARSET = "UTF-8";
	
	public static final int QRCODE_DEFAULT_SIZE = 150;
	public static final int QRCODE_SMALL_SIZE = 150;
	public static final int QRCODE_MIDDLE_SIZE = 300;
	public static final int QRCODE_LARGE_SIZE = 500;
	
	public static final String QRCODE_IMAGE_TYPE = "png";

	public static final String URL_PREFIX = "salary_web/h5/#/codePage?"; // TODO 写到配置文件，与前端部署路径一致
	public static final String URL_BANK_KEY = "bankSname";
	public static final String URL_QRCODEID_KEY = "qrcodeId";
	
	@Resource
	private WxworkConfig wxworkConfig;
	
	@Autowired
	private QRcodeConfigDao qRcodeConfigDao;

	/**
	 * 二维码列表
	 * @return
	 */
	public List<QRcodeConfigParam> listQRcodes() {
		return qRcodeConfigDao.listQRcodes();
	}
	
	/**
	 * 根据id查询二维码详情
	 * @param promotion_qrcode_id
	 * @return
	 */
	public QRcodeConfigParam queryQRcodeById(int promotion_qrcode_id) {
		QRcodeConfigParam configParam = qRcodeConfigDao.queryQRcodeById(promotion_qrcode_id);
		
		String qrcode_url = generateUrl(configParam.getBank_sname(), promotion_qrcode_id);
		configParam.setQrcode_url(qrcode_url);
		
		BufferedImage logo = srcToImage(configParam.getQrcode_logo());
		String promotion_qrcode = urlToQrcodeSrc(qrcode_url, QRCODE_DEFAULT_SIZE, logo);
		configParam.setPromotion_qrcode(promotion_qrcode);
		
		return configParam;
	}

	/**
	 * 创建二维码
	 * @param request
	 * @return
	 */
	@Transactional
	public int insertQRcode(QRcodeConfigParam request) {		
		String template_id = wxworkConfig.templateId; // TODO 写入配置文件
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();		
		String create_time = sdf.format(now);
		
		qRcodeConfigDao.insertQRcode(request.getBank_sname(), request.getBank_name(), request.getQrcode_name(),
				template_id, request.getQrcode_logo(), create_time);
		
		int promotion_qrcode_id = qRcodeConfigDao.getLastInsertId();
		
		return promotion_qrcode_id;
	}

	/**
	 * 不同尺寸的二维码下载
	 * @param request
	 * @return
	 */
	public QRcodeConfigParam downloadQRcodeImage(QRcodeConfigParam request) {
		QRcodeConfigParam configParam = qRcodeConfigDao.queryQRcodeById(request.getPromotion_qrcode_id());
		
		String qrcode_url = generateUrl(configParam.getBank_sname(), request.getPromotion_qrcode_id());
		BufferedImage logo = srcToImage(configParam.getQrcode_logo());
		
		String size = request.getQrcode_size();
		String promotion_qrcode = null;
		if ("S".equals(size)) {
			promotion_qrcode = urlToQrcodeSrc(qrcode_url, QRCODE_SMALL_SIZE, logo);
		} else if ("M".equals(size)) {
			promotion_qrcode = urlToQrcodeSrc(qrcode_url, QRCODE_MIDDLE_SIZE, logo);
		} else if ("L".equals(size)) {
			promotion_qrcode = urlToQrcodeSrc(qrcode_url, QRCODE_LARGE_SIZE, logo);
		}
		
		configParam.setPromotion_qrcode(promotion_qrcode);
		return configParam;
	}
	
	/**
	 * 更新二维码配置参数
	 * @param request
	 * @return
	 */
	public String updateQRcode(QRcodeConfigParam request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();		
		String modify_time = sdf.format(now);
		qRcodeConfigDao.updateQRcode(request.getPromotion_qrcode_id(), request.getBank_sname(), request.getBank_name(), 
				request.getQrcode_name(), request.getQrcode_logo(), modify_time);
		return "0";
	}
	
	/**
	 * 生成url
	 * @param bank_sname
	 * @return
	 */
	public String generateUrl(String bank_sname, int promotion_qrcode_id) {
		StringBuffer url = new StringBuffer(ConfigUtils.getBaseUrl());
		url.append(URL_PREFIX);
		
		// url中加入银行标识
		url.append(URL_BANK_KEY);
		url.append("=");
		url.append(bank_sname);
		
		// url中加入二维码id
		url.append("&");
		url.append(URL_QRCODEID_KEY);
		url.append("=");
		url.append(promotion_qrcode_id);

		String qrcode_url = url.toString();
		return qrcode_url;
	}
	
	/**
	 * 根据url生成前端用的二维码src
	 * @param url
	 * @param size
	 * @param logo
	 * @return
	 */
	public String urlToQrcodeSrc(String url, int size, BufferedImage logo) {
		BufferedImage image = QRcodeUtils.createQRCode(url, QRCODE_DEFAULT_CHARSET, size, size);
		
		if (logo != null) {
			// 在二维码中加入logo
			Graphics2D g = image.createGraphics();		
			// 设置logo的大小,不超过二维码图片的20%,过大会盖掉二维码
			int logo_width = logo.getWidth() > image.getWidth() * 2 / 10 ? (image.getWidth() * 2 / 10) : logo.getWidth();
			int logo_height = logo.getHeight() > image.getHeight() * 2 / 10 ? (image.getHeight() * 2 / 10) : logo.getWidth();
			
			// 设置logo的位置在中心
			int x = (image.getWidth() - logo_width) / 2;
			int y = (image.getHeight() - logo_height) / 2;
			
			// 绘制logo
			g.drawImage(logo, x, y, logo_width, logo_height, null);
			g.drawRoundRect(x, y, logo_width, logo_height, 15, 15); // 圆角矩形
			g.setStroke(new BasicStroke(2)); // logo边框宽度
			g.setColor(Color.WHITE); // logo边框颜色
			g.drawRect(x, y, logo_width, logo_height);
			g.dispose();
		}
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();		
		try {
			ImageIO.write(image, QRCODE_IMAGE_TYPE, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] b = outputStream.toByteArray();
		
		return "data:image/" + QRCODE_IMAGE_TYPE + ";base64," + Base64.encodeBase64String(b);
	}
	
	/**
	 * logo图片src转BufferedImage
	 * @param b
	 * @return
	 */
	public BufferedImage srcToImage(String src) {
		if (StringUtils.isBlank(src)) {
			return null;
		}

		String base64 = src.split(",")[1];
		byte[] b = Base64.decodeBase64(base64);	
		ByteArrayInputStream bais = new ByteArrayInputStream(b);	
		BufferedImage image = null;		
		try {
			image = ImageIO.read(bais);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public List<BankInfo> queryBankList() {		
		return qRcodeConfigDao.queryBankList();
	}

}

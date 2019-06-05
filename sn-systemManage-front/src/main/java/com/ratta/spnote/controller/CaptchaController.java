package com.ratta.spnote.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ratta.spnote.util.Json;

/**
 * @author page 生成 验证码 2018-10-31
 */
@Controller
@RequestMapping("/captchaController")
public class CaptchaController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 生成验证码
	 * 
	 * @param request
	 */

	@RequestMapping("/createImage")
	public void createImage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应是图片
		response.setContentType("image/jpeg");
		int width = 100;
		int height = 25;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Random random = new Random();
		Font mFont = new Font("华文宋体", Font.BOLD, 20);
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		g.setFont(mFont);
		String sRand = "";
		int itmp = 0;
		for (int i = 0; i < 4; i++) {
			if (random.nextInt(2) == 1) {
				itmp = random.nextInt(26) + 65 + (random.nextInt(2) == 1 ? 32 : 0);
			} else {
				itmp = random.nextInt(10) + 48;
			}
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110));
			g.setColor(color);
			/**** 随机缩放文字并将文字旋转指定角度 **/
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1.1f) {
				scaleSize = 1f;
			}
			g.drawString(String.valueOf(ctmp), 15 * i + 20, 20);

		}
		String vadCode = sRand;
		logger.info("生产验证码:{}", vadCode);
		g.dispose();

		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
			session.setAttribute("validate_code", vadCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255) {
			s = 255;
		}

		if (e > 255) {
			e = 255;
		}

		int r = s + random.nextInt(e - s);
		int g = s + random.nextInt(e - s);
		int b = s + random.nextInt(e - s);
		return new Color(r, g, b);
	}

	/**
	 * 获取验证码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/code", method = RequestMethod.POST)
	@ResponseBody
	public Json code(HttpSession session) {
		String validate = String.valueOf(session.getAttribute("validate_code"));
		Json j = new Json();
		if (!"".equals(validate)) {
			j.setSuccess(true);
			j.setMsg(validate);
		} else {
			j.setMsg(CaptchaMessage.CAPTCHA_GENERATE_VERIFICATION_CODE_SUCCESS.getMessage());
		}
		return j;
	}
}

package com.sansui.servlet;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/9 23:18
 * @modified By  西西里_SanSui in 2021/5/9 23:18
 * @description AddDescriptionHere
 */

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 生成验证码，并把验证码存储在会话域中
 */
@WebServlet(urlPatterns = "/Encode")
public class EncodeServlet extends HttpServlet {

    // 写一个方法随机获取颜色
    public Color randomColor() {
        // r g b 取值范围 0到255
        Random random = new Random();
        return new Color(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建缓存图片：指定宽width=90，高 height=30
        int width = 90;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取画笔对象
        Graphics g = image.getGraphics();
        // 设置画笔颜色，并且填充矩形区域
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        // 创建可变字符串
        StringBuilder sb = new StringBuilder();
        // 从字符数组中随机得到字符，根据需求添加你需要的字符
        char[] arr = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                'A', 'B', 'C', 'D', 'N', 'E', 'W', 'b', 'o', 'y'};
        // 创建随机对象
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            // 随机产生一个索引值
            int index = r.nextInt(arr.length);
            // 根据索引获得随机字符
            char ch = arr[index];
            // 拼接字符
            sb.append(ch);
            // 设置字体，大小为 18
            g.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 18));
            // 设置字的颜色随机
            g.setColor(randomColor());

            // 绘制字符串
            // 7. 将每个字符画到图片上，位置：
            int x = 5 + (i * 20);
            int y = 20;
            g.drawString(String.valueOf(ch), x, y);
        }
        // 将验证码字符串存储会话域中
        HttpSession session = request.getSession();
        session.setAttribute("verCode", sb.toString());
        // 8. 画10条干扰线，线的位置是随机的，x 范围在 width 之中，y 的范围在 height 之中。
        for (int i = 0; i < 10; i++) {
            // 设置颜色随机
            g.setColor(randomColor());
            int x1 = r.nextInt(width + 1);
            int x2 = r.nextInt(height + 1);
            int y1 = r.nextInt(width + 1);
            int y2 = r.nextInt(height + 1);
            g.drawLine(x1, y1, x2, y2);
        }
        // 9. 将缓存的图片输出到响应输出流中
        ImageIO.write(image, "png", response.getOutputStream());
    }
}

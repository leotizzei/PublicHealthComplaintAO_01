package publichealthcomplaint.userinterface.impl.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletCaptcha extends HttpServlet {

	private void processRequest(HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException {
		
		int width = 200;
		int height = 50;
		int numberOfCaptchas = 10;
		
		char data[][] = {
				{ 'a', 'c', 'd', 'c'},
				{ 'z', 'e', 'p', 'p', 'e', 'l','i','n' },
				{ 's', 't', 'o', 'n', 'e', 's'},
				{ 'b', 'e', 'a', 't', 'l', 'e','s'},
				{ 'd', 'o', 'o', 'r', 's'},
				{ 'p', 'u', 'r', 'p', 'l', 'e'},
				{'s','a','b','b','a','t','h'},
				{'t','h','e','w','h','o'},
				{'d','y','l','a','n'},
				{'p','i','n','k','f','l','o','y','d'}
		};


		BufferedImage bufferedImage = new BufferedImage(width, height, 
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();

		Font font = new Font("Georgia", Font.BOLD, 18);
		g2d.setFont(font);

		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING, 
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		GradientPaint gp = new GradientPaint(0, 0, 
				Color.red, 0, height/2, Color.black, true);

		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);

		g2d.setColor(new Color(255, 153, 0));

		Random r = new Random();
		int index = Math.abs(r.nextInt()) % numberOfCaptchas;

		String captcha = String.copyValueOf(data[index]);
		request.getSession().setAttribute("captcha", captcha );

		int x = 0; 
		int y = 0;

		for (int i=0; i<data[index].length; i++) {
			x += 10 + (Math.abs(r.nextInt()) % 15);
			y = 20 + Math.abs(r.nextInt()) % 20;
			g2d.drawChars(data[index], i, 1, x, y);
		}

		g2d.dispose();

		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(bufferedImage, "png", os);
		os.close();
	} 


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	} 


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	       
		processRequest(request, response);
	}
}




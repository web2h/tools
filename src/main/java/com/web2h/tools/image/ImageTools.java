package com.web2h.tools.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

public class ImageTools {

	/**
	 * Resizes (and crop if necessary) an image to be square shaped.
	 * The aspect ratio is preserved
	 * 
	 * @param imageBytes The original as a byte array
	 * @param size       The expected side size
	 * @return The transformed image as a byte array
	 * @throws IOException
	 */
	public static final byte[] resizeToSquareShapedImage(byte[] imageBytes, int size) throws IOException {

		try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
			String contentType = URLConnection.guessContentTypeFromStream(inputStream);

			String imageType = contentType.substring("image/".length());

			BufferedImage bufferedImageSrc = ImageIO.read(inputStream);

			BufferedImage bufferedImageDst;
			if (bufferedImageSrc.getHeight() > bufferedImageSrc.getWidth()) {
				bufferedImageDst = Scalr.resize(bufferedImageSrc, Mode.FIT_TO_WIDTH, 300);
			} else {
				bufferedImageDst = Scalr.resize(bufferedImageSrc, Mode.FIT_TO_HEIGHT, 300);
			}

			int height = bufferedImageDst.getHeight();
			int width = bufferedImageDst.getWidth();
			if (height > width) {
				bufferedImageDst = Scalr.crop(bufferedImageDst, 0, (height - 300) / 2, 300, 300);
			} else {
				bufferedImageDst = Scalr.crop(bufferedImageDst, (width - 300) / 2, 0, 300, 300);
			}

			try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
				ImageIO.write(bufferedImageDst, imageType, byteArrayOutputStream);
				byteArrayOutputStream.flush();
				return byteArrayOutputStream.toByteArray();
			}
		}
	}

	public static final String getImageContentType(byte[] imageBytes) throws IOException {

		try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
			return URLConnection.guessContentTypeFromStream(inputStream);
		}
	}
}
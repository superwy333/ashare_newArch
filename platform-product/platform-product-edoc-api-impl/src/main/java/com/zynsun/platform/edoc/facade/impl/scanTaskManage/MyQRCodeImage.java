package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

/**
 * @description:
 * @author: Wy
 * @create: 2018-12-17 15:00
 **/
public class MyQRCodeImage implements QRCodeImage {

    BufferedImage bufferedImage;

    public MyQRCodeImage(BufferedImage bufferedImage){
        this.bufferedImage=bufferedImage;
    }

    //宽
    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    //高
    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    //像素还是颜色
    @Override
    public int getPixel(int i, int j) {
        return bufferedImage.getRGB(i,j);
    }


}

 ~~~
 // Drawing (or printing) texts on the graphics screen:
 drawString(String str, int xBaselineLeft, int yBaselineLeft);
 
 // Drawing lines:
 drawLine(int x1, int y1, int x2, int y2);
 drawPolyline(int[] xPoints, int[] yPoints, int numPoint);
 
 // Drawing primitive shapes:
 drawRect(int xTopLeft, int yTopLeft, int width, int height);
 drawOval(int xTopLeft, int yTopLeft, int width, int height);
 drawArc(int xTopLeft, int yTopLeft, int width, int height, int startAngle, int arcAngle);
 draw3DRect(int xTopLeft, int, yTopLeft, int width, int height, boolean raised);
 drawRoundRect(int xTopLeft, int yTopLeft, int width, int height, int arcWidth, int arcHeight)
 drawPolygon(int[] xPoints, int[] yPoints, int numPoint);

 // Filling primitive shapes:
 fillRect(int xTopLeft, int yTopLeft, int width, int height);
 fillOval(int xTopLeft, int yTopLeft, int width, int height);
 fillArc(int xTopLeft, int yTopLeft, int width, int height, int startAngle, int arcAngle);
 fill3DRect(int xTopLeft, int, yTopLeft, int width, int height, boolean raised);
 fillRoundRect(int xTopLeft, int yTopLeft, int width, int height, int arcWidth, int arcHeight)
 fillPolygon(int[] xPoints, int[] yPoints, int numPoint);
  
 // Drawing (or Displaying) images:
 drawImage(Image img, int xTopLeft, int yTopLeft, ImageObserver obs);  // draw image with its size
 drawImage(Image img, int xTopLeft, int yTopLeft, int width, int height, ImageObserver o);  // resize image on screen

~~~

![](https://github.com/LiiiiiZQ/witcheryMaterial/blob/master/Index/graphic.png)

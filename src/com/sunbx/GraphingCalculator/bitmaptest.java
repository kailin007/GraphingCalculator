package com.sunbx.GraphingCalculator;
import com.GraphingCalculator.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

public class bitmaptest extends Activity { 

public BitmapDrawable ZoomImage(Bitmap bitmapOrg, int newWidth ,int newHeight){

// load the origial BitMap (500 x 500 px) 
//Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),R.drawable.gray);

int width = bitmapOrg.getWidth(); 
int height = bitmapOrg.getHeight(); 

// calculate the scale - in this case = 0.4f 
float scaleWidth = ((float) newWidth) / width; 
float scaleHeight = ((float) newHeight) / height;

// createa matrix for the manipulation 
Matrix matrix = new Matrix(); 
// resize the bit map 
matrix.postScale(scaleWidth, scaleHeight);
// recreate the new Bitmap 
Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width, height, matrix, true);
BitmapDrawable zoomedBitmap = new BitmapDrawable(resizedBitmap);
return zoomedBitmap;
} 
}
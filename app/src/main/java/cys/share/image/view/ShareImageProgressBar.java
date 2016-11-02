package cys.share.image.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.io.File;

import cys.share.image.R;
import cys.share.image.view.utils.CalculationUtil;
import cys.share.image.view.utils.PercentStyle;

public class ShareImageProgressBar extends RelativeLayout {

	private ImageView imageView;
	private final ShareImageProgressView bar;
	private boolean opacity = false;
	private boolean greyscale;
	private boolean isFadingOnProgress = false;

	public ShareImageProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.progressbarview, this, true);
		bar = (ShareImageProgressView) findViewById(R.id.squareProgressBar1);
        imageView = (ImageView) findViewById(R.id.imageView1);
		bar.bringToFront();
	}

	public ShareImageProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.progressbarview, this, true);
		bar = (ShareImageProgressView) findViewById(R.id.squareProgressBar1);
        imageView = (ImageView) findViewById(R.id.imageView1);
		bar.bringToFront();
	}

	public ShareImageProgressBar(Context context) {
		super(context);
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.progressbarview, this, true);
		bar = (ShareImageProgressView) findViewById(R.id.squareProgressBar1);
        imageView = (ImageView) findViewById(R.id.imageView1);
		bar.bringToFront();
	}

	public void setImage(int image) {
		imageView.setImageResource(image);
	}

	public void setImage(String path){
		File img = new File(path);
		Picasso.with(this.getContext()).load(img).into(imageView);
	}
	public void setImageScaleType(ScaleType scale) {
		imageView.setScaleType(scale);
	}
	public void setProgress(double progress) {
		bar.setProgress(progress);
		if (opacity) {
			if (isFadingOnProgress) {
				setOpacity(100 - (int) progress);
			} else {
				setOpacity((int) progress);
			}
		} else {
			setOpacity(100);
		}
	}

	public void setHoloColor(int androidHoloColor) {
		bar.setColor(getContext().getResources().getColor(androidHoloColor));
	}

	public void setColor(String colorString) {
		bar.setColor(Color.parseColor(colorString));
	}

	public void setColorRGB(int r, int g, int b) {
		bar.setColor(Color.rgb(r, g, b));
	}

	public void setColorRGB(int rgb) {
		bar.setColor(rgb);
	}
	public void setWidth(int width) {
		int padding = CalculationUtil.convertDpToPx(width, getContext());
		imageView.setPadding(padding, padding, padding, padding);
		bar.setWidthInDp(width);
	}

	private void setOpacity(int progress) {
		if(progress <= 25){
			imageView.setAlpha((int)(2.55*25));
		}else {
			imageView.setAlpha((int) (2.55 * progress));
		}
	}

	public void setOpacity(boolean opacity) {
		this.opacity = opacity;
		setProgress(bar.getProgress());
	}

	public void setImageGrayscale(boolean greyscale) {
		this.greyscale = greyscale;
		if (greyscale) {
			ColorMatrix matrix = new ColorMatrix();
			matrix.setSaturation(0);
			imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
		} else {
			imageView.setColorFilter(null);
		}
	}

	public boolean isOpacity() {
		return opacity;
	}

	public boolean isGreyscale() {
		return greyscale;
	}

	public void drawOutline(boolean drawOutline) {
		bar.setOutline(drawOutline);
	}

	public boolean isOutline() {
		return bar.isOutline();
	}

	public void drawStartline(boolean drawStartline) {
		bar.setStartline(drawStartline);
	}

	/**
	 * If the startline is enabled.
	 * 
	 * @return true if startline is enabled or not.
	 */
	public boolean isStartline() {
		return bar.isStartline();
	}

	public void showProgress(boolean showProgress) {
		bar.setShowProgress(showProgress);
	}

	/**
	 * If the progress text inside of the image is enabled.
	 * 
	 * @return true if it is or not.
	 */
	public boolean isShowProgress() {
		return bar.isShowProgress();
	}

	/**
	 * Sets a custom percent style to the text inside the image. Make sure you
	 * set {@link #showProgress(boolean)} to true. Otherwise it doesn't shows.
	 * The default settings are:</br>
	 * <table>
	 * <tr>
	 * <th>Text align</td>
	 * <td>CENTER</td>
	 * </tr>
	 * <tr>
	 * <th>Text size</td>
	 * <td>150 [dp]</td>
	 * </tr>
	 * <tr>
	 * <th>Display percentsign</td>
	 * <td>true</td>
	 * </tr>
	 * <tr>
	 * <th>Custom text</td>
	 * <td>%</td>
	 * </tr>
	 * </table>
	 * 
	 * @param percentStyle
	 */
	public void setPercentStyle(PercentStyle percentStyle) {
		bar.setPercentStyle(percentStyle);
	}
	public PercentStyle getPercentStyle() {
		return bar.getPercentStyle();
	}

	public void setClearOnHundred(boolean clearOnHundred) {
		bar.setClearOnHundred(clearOnHundred);
	}
	
	public boolean isClearOnHundred() {
		return bar.isClearOnHundred();
	}


    /**
     * Set an image resource directly to the ImageView.
     *
     * @param bitmap the {@link Bitmap} to set.
     */
    public void setImageBitmap(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }

    /**
     * Set the status of the indeterminate mode. The default is false. You can
     * still configure colour, width and so on.
     *
     * @param indeterminate true to enable the indeterminate mode (default true)
     * @since 1.6.0
     */
    public void setIndeterminate(boolean indeterminate) {
        bar.setIndeterminate(indeterminate);
    }

    /**
     * Returns the status of the indeterminate mode. The default status is false.
     *
     * @since 1.6.0
     */
    public boolean isIndeterminate() {
        return bar.isIndeterminate();
    }

    /**
     * Draws a line in the center of the way the progressbar has to go.
     *
     * @param drawCenterline
     *            true if it should or not.
     * @since 1.6.0
     */
    public void drawCenterline(boolean drawCenterline) {
        bar.setCenterline(drawCenterline);
    }

    /**
     * If the centerline is enabled or not.
     *
     * @return true if centerline is enabled.
     * @since 1.6.0
     */
    public boolean isCenterline() {
        return bar.isCenterline();
    }

	public ImageView getImageView(){
		return imageView;
	}

}

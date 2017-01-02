package game.ivan.ashancalculator.items.controller;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.bluelinelabs.conductor.Controller;
import com.mindorks.paracamera.Camera;
import com.mindorks.paracamera.Utils;

import java.io.File;

/**
 * Created by ivan on 01.01.17.
 */

public class ConductorCamera extends Camera {

    /**
     * public variables to be used in the builder
     */
    public static int REQUEST_TAKE_PHOTO = 1234;
    public static final String IMAGE_JPG = "jpg";
    public static final String IMAGE_JPEG = "jpeg";
    public static final String IMAGE_PNG = "png";

    /**
     * default values used by camera
     */
    private static final String IMAGE_FORMAT_JPG = ".jpg";
    private static final String IMAGE_FORMAT_JPEG = ".jpeg";
    private static final String IMAGE_FORMAT_PNG = ".png";
    private static final int IMAGE_HEIGHT = 1000;
    private static final int IMAGE_COMPRESSION = 75;
    private static final String IMAGE_DEFAULT_DIR = "capture";
    private static final String IMAGE_DEFAULT_NAME = "img_";

    private enum MODE{ACTIVITY, FRAGMENT,CONTROLLER}

    /**
     *  Private variables
     */
    private Context context;
    private Activity activity;
    private Controller controller;
    private Fragment fragment;
    private String cameraBitmapPath = null;
    private Bitmap cameraBitmap = null;
    private String dirName;
    private String imageName;
    private String imageType;
    private int imageHeight;
    private int compression;
    private boolean isCorrectOrientationRequired;
    private ConductorCamera.MODE mode;

    public ConductorCamera(Controller controller){
        super(controller.getActivity());
        this.controller = controller;
        context = controller.getApplicationContext();
        mode = ConductorCamera.MODE.CONTROLLER;
        init();
    }

    /**
     *
     * @param activity to return the camera results
     */
    public ConductorCamera(Activity activity) {
        super(activity);
        this.activity = activity;
        context = activity.getApplicationContext();
        mode = ConductorCamera.MODE.ACTIVITY;
        init();
    }

    /**
     *
     * @param fragment to return the camera results
     */
    public ConductorCamera(Fragment fragment) {
        super(fragment);
        this.fragment = fragment;
        context = fragment.getActivity().getApplicationContext();
        mode = ConductorCamera.MODE.FRAGMENT;
        init();
    }

    private void init(){
        dirName = IMAGE_DEFAULT_DIR;
        imageName = IMAGE_DEFAULT_NAME + System.currentTimeMillis();
        imageHeight = IMAGE_HEIGHT;
        compression = IMAGE_COMPRESSION;
        imageType = IMAGE_FORMAT_JPG;
    }

    /**
     *
     * @return create camera builder
     */
    public Camera.CameraBuilder builder(){
        return new Camera.CameraBuilder();
    }

    /**
     * Initiate the existing camera apps
     * @throws NullPointerException
     */
    public void takePicture() throws NullPointerException, IllegalAccessException{
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        switch (mode){
            case ACTIVITY:
                if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                    File photoFile = Utils.createImageFile(context, dirName, imageName, imageType);
                    if (photoFile != null) {
                        cameraBitmapPath = photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    } else {
                        throw new NullPointerException("Image file could not be created");
                    }
                } else {
                    throw new IllegalAccessException("Unable to open camera");
                }
                break;
            case FRAGMENT:
                if (takePictureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
                    File photoFile = Utils.createImageFile(context, dirName, imageName, imageType);
                    if (photoFile != null) {
                        cameraBitmapPath = photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        fragment.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    } else {
                        throw new NullPointerException("Image file could not be created");
                    }
                } else {
                    throw new IllegalAccessException("Unable to open camera");
                }
                break;
            case CONTROLLER:
                if (takePictureIntent.resolveActivity(controller.getActivity().getPackageManager()) != null) {
                    File photoFile = Utils.createImageFile(context, dirName, imageName, imageType);
                    if (photoFile != null) {
                        cameraBitmapPath = photoFile.getAbsolutePath();
                        if (cameraBitmapPath == null){
                            Log.d("Test","cameraPath is null");
                        } else Log.d("Test",cameraBitmapPath);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        controller.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    } else {
                        throw new NullPointerException("Image file could not be created");
                    }
                } else {
                    throw new IllegalAccessException("Unable to open camera");
                }
                break;
            default:
                cameraBitmapPath="";
        }
    }

    /**
     * Initiate the existing camera apps
     * @throws NullPointerException
     */
    public String takePicture(boolean flag) throws NullPointerException, IllegalAccessException{
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        switch (mode){
            case ACTIVITY:
                if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                    File photoFile = Utils.createImageFile(context, dirName, imageName, imageType);
                    if (photoFile != null) {
                        cameraBitmapPath = photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    } else {
                        throw new NullPointerException("Image file could not be created");
                    }
                } else {
                    throw new IllegalAccessException("Unable to open camera");
                }
                break;
            case FRAGMENT:
                if (takePictureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
                    File photoFile = Utils.createImageFile(context, dirName, imageName, imageType);
                    if (photoFile != null) {
                        cameraBitmapPath = photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        fragment.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    } else {
                        throw new NullPointerException("Image file could not be created");
                    }
                } else {
                    throw new IllegalAccessException("Unable to open camera");
                }
                break;
            case CONTROLLER:
                if (takePictureIntent.resolveActivity(controller.getActivity().getPackageManager()) != null) {
                    File photoFile = Utils.createImageFile(context, dirName, imageName, imageType);
                    if(photoFile.getAbsolutePath().equals("")){
                        Log.d("Test","fuckig empty");
                    }

                    if (photoFile != null) {
                        cameraBitmapPath = photoFile.getAbsolutePath();
                        if (cameraBitmapPath == null){
                            Log.d("Test","cameraPath is null");
                        } else Log.d("Test",cameraBitmapPath);
                    //    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        controller.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    } else {
                        throw new NullPointerException("Image file could not be created");
                    }
                } else {
                    throw new IllegalAccessException("Unable to open camera");
                }
                break;
            default:
                cameraBitmapPath="";
        }
        return  cameraBitmapPath;
    }

    /**
     *
     * @return the saved bitmap path but scaling bitmap as per builder
     */
    public String getCameraBitmapPath() {
        Bitmap bitmap = getCameraBitmap();
        bitmap.recycle();
        return cameraBitmapPath;
    }

    /**
     *
     * @return The scaled bitmap as per builder
     */
    public Bitmap getCameraBitmap() {
        return resizeAndGetCameraBitmap(imageHeight);
    }

    /**
     *
     * @return The scaled bitmap as per builder
     */
    public Bitmap getCameraBitmap(String path) {
        return resizeAndGetCameraBitmap(imageHeight,path);
    }

    /**
     *
     * @param imageHeight
     * @return Bitmap path with approx desired height
     */
    public String resizeAndGetCameraBitmapPath(int imageHeight) {
        Bitmap bitmap = resizeAndGetCameraBitmap(imageHeight);
        bitmap.recycle();
        return cameraBitmapPath;
    }

    /**
     *
     * @param imageHeight
     * @return Bitmap with approx desired height
     */
    public Bitmap resizeAndGetCameraBitmap(int imageHeight) {
        try {
            if (cameraBitmap != null) {
                cameraBitmap.recycle();
            }
            Log.d("Test","after recycler - "+cameraBitmapPath);
            cameraBitmap = Utils.decodeFile(new File(cameraBitmapPath), imageHeight);
            Log.d("Test","after decode");
            if (cameraBitmap != null) {
                if(isCorrectOrientationRequired){
                    cameraBitmap = Utils.rotateBitmap(cameraBitmap, Utils.getImageRotation(cameraBitmapPath));
                }
                Utils.saveBitmap(cameraBitmap, cameraBitmapPath, imageType, compression);
            }

            if (cameraBitmap == null){
                Log.d("Test","cameraBitmap == null ");
            }

            return cameraBitmap;
        }catch (Exception e){

            Log.d("Test",""+e.getMessage()+"  sssss = "+e.toString());
            return null;
        }
    }

    /**
     *
     * @param imageHeight
     * @return Bitmap with approx desired height
     */
    public Bitmap resizeAndGetCameraBitmap(int imageHeight,String path) {
        try {
            if (cameraBitmap != null) {
                cameraBitmap.recycle();
            }
            Log.d("Test","after recycler - "+cameraBitmapPath);
            cameraBitmap = Utils.decodeFile(new File(path), imageHeight);
            Log.d("Test","after decode");
            if (cameraBitmap != null) {
                if(isCorrectOrientationRequired){
                    cameraBitmap = Utils.rotateBitmap(cameraBitmap, Utils.getImageRotation(cameraBitmapPath));
                }
                Utils.saveBitmap(cameraBitmap, path, imageType, compression);
            }

            if (cameraBitmap == null){
                Log.d("Test","cameraBitmap == null ");
            }

            return cameraBitmap;
        }catch (Exception e){

            Log.d("Test",""+e.getMessage()+"  sssss = "+e.toString());
            return null;
        }
    }
    /**
     * Deletes the saved camera image
     */
    public void deleteImage(){
        if(cameraBitmapPath != null){
            File image = new File(cameraBitmapPath);
            if(image.exists()){
                image.delete();
            }
        }
    }

    public void saveBitmap(String path,Bitmap bitmap){
        Utils.saveBitmap(bitmap, path, imageType, compression);
    }

    /**
     * Camera builder declaration
     */
    public class CameraBuilder {
        public ConductorCamera.CameraBuilder setDirectory(String dirName){
            ConductorCamera.this.dirName = dirName;
            return this;
        }

        public ConductorCamera.CameraBuilder setTakePhotoRequestCode(int requestCode){
            ConductorCamera.REQUEST_TAKE_PHOTO = requestCode;
            return this;
        }

        public ConductorCamera.CameraBuilder setName(String imageName){
            ConductorCamera.this.imageName = imageName;
            return this;
        }

        public ConductorCamera.CameraBuilder resetToCorrectOrientation(boolean reset){
            ConductorCamera.this.isCorrectOrientationRequired = reset;
            return this;
        }

        public ConductorCamera.CameraBuilder setImageFormat(String imageFormat){
            if(imageFormat == null){
                ConductorCamera.this.imageType = IMAGE_FORMAT_JPG;
            }
            if(imageFormat.equals("png") || imageFormat.equals("PNG") || imageFormat.equals(".png")){
                ConductorCamera.this.imageType = IMAGE_FORMAT_PNG;
            }
            else if(imageFormat.equals("jpg") || imageFormat.equals("JPG") || imageFormat.equals(".jpg")){
                ConductorCamera.this.imageType = IMAGE_FORMAT_JPG;
            }
            else if(imageFormat.equals("jpeg") || imageFormat.equals("JPEG") || imageFormat.equals(".jpeg")){
                ConductorCamera.this.imageType = IMAGE_FORMAT_JPEG;
            }else{
                ConductorCamera.this.imageType = IMAGE_FORMAT_JPG;
            }
            return this;
        }

        public ConductorCamera.CameraBuilder setImageHeight(int imageHeight){
            ConductorCamera.this.imageHeight = imageHeight;
            return this;
        }

        public ConductorCamera.CameraBuilder setCompression(int compression){
            if(compression > 100){
                compression = 100;
            }else if(compression < 0){
                compression = 0;
            }
            ConductorCamera.this.compression = compression;
            return this;
        }
    }
}

package presentation;

/**
 * Created by 61990 on 2017/3/5.
 */
class WindowData {
    private static WindowData windowData;
    private static int width;
    private static int height;

    public static void setWindowData(int width , int height){
        windowData.height = height;
        windowData.width = width;
    }
    public WindowData(){

    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }


    public static WindowData getInstance() {
        if(windowData==null){
            windowData=new WindowData();
        }
        return windowData;
    }
}

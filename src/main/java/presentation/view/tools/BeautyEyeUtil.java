package presentation.view.tools;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

/**
 * Created by Harvey on 2017/3/25.
 */
public class BeautyEyeUtil {

    public static void beautyEye(){
        BeautyEyeLNFHelper.frameBorderStyle= BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
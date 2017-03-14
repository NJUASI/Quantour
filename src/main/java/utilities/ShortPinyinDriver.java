package utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/3/14.
 */
public class ShortPinyinDriver {

    public static void main(String[] args) {

        List<String> names = new ArrayList<String>();
        names.add("高铁Y");
        names.add("飞机Z");

        ShortPinyinUtil.convertToShortPinyin(names);

    }
}

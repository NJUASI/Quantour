package utilities;

import dataHelper.SearchDataHelper;
import dataHelper.dataHelperImpl.SearchDataHelperImpl;
import service.StockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/3/14.
 */
public class ShortPinyinDriver {

    public static void main(String[] args) {

        SearchDataHelper searchDataHelper = new SearchDataHelperImpl();
        Map<String,String> map = searchDataHelper.getAllStocksCode();
        List<String> names = new ArrayList<String>();

        for(Map.Entry<String,String> entry:map.entrySet()){
            names.add(entry.getValue());
        }

        new ShortPinyinUtil().convertToShortPinyin(names);

    }
}

package org.nazymko.moneygraph.testing;

import com.google.gson.Gson;

import java.util.*;

/**
 * Created by a.nazimko on 15.03.2017.
 */
public class JsonFileReader {

    public static List<Map> filtered(String file, String adress) {

        final String s = ReadFileUtil.read(file);

        final List<Map> list = new Gson().fromJson(s, List.class);
        Set adresses = new HashSet();
        List filtered = new ArrayList();
        List<Map> filteredMap = new ArrayList<>();
        for (Map hashMap : list) {
            final Map sms = (Map) hashMap.get("sms_body");

            final String sms_body = (String) sms.get("body");
            final String address = (String) sms.get("address");


            adresses.add(address);

            if (adress.equals(address)) {
                filtered.add(sms_body);
                filteredMap.add(sms);
            }
        }

        return filteredMap;

    }
}

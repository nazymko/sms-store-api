package org.nazymko.moneygraph.storage.web;

import com.google.gson.Gson;
import org.jooq.util.derby.sys.Sys;
import org.nazymko.moneygraph.storage.dao.autodao.tables.daos.JooqRawsmsDao;
import org.nazymko.moneygraph.storage.dao.autodao.tables.pojos.Rawsms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nazymko on 13.03.2017.
 */
@RestController
@RequestMapping("storage")
public class StoreController {
    @Autowired
    JooqRawsmsDao rawsmsDao;

    @ResponseBody
    @RequestMapping(value = "put", consumes = "application/json", method = RequestMethod.POST)
    public String put(@RequestBody HashMap<Object, Object> json) {
        System.out.println("json = " + json);

        rawsmsDao.insert(createPojo(json));

        return "saved";
    }


    private List<Rawsms> createPojo(HashMap<Object, Object> json) {


        String identification = String.valueOf(json.get("identification"));

        List<HashMap<String, Object>> allSms = (List<HashMap<String, Object>>) json.get("sms");


        List<Rawsms> pojos = new ArrayList<>();

        Gson gson = new Gson();
        for (HashMap<String, Object> allSm : allSms) {

            Rawsms rawsms = new Rawsms();

            rawsms.setSmsBody(gson.toJson(allSm));
            rawsms.setSmsDate(System.currentTimeMillis());
            rawsms.setDeviceId(identification);
            pojos.add(rawsms);
        }


        return pojos;
    }

}

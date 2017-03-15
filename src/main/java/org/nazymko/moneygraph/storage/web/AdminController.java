package org.nazymko.moneygraph.storage.web;

import com.google.gson.Gson;
import org.nazymko.moneygraph.storage.dao.autodao.tables.daos.JooqRawsmsDao;
import org.nazymko.moneygraph.storage.dao.autodao.tables.pojos.Rawsms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nazymko on 13.03.2017.
 */
@Controller()
@RequestMapping("admin")
public class AdminController {

    Gson gson = new Gson();

    @Autowired
    private JooqRawsmsDao dao;

    @RequestMapping(value = "by/sender/phone/{phone}", method = RequestMethod.GET)
    public String byPhone(@PathVariable("phone") String phone) {

        return "";
    }


    @ResponseBody
    @RequestMapping(value = "all", produces = "application/json")
    public List<HashMap<String, Object>> all() {

        List<Rawsms> all = dao.findAll();
        List<HashMap<String, Object>> ret = new ArrayList<>();
        for (Rawsms rawsms : all) {

            HashMap<String, Object> map = new HashMap<>();

            map.put("id", rawsms.getId());
            map.put("device_id", rawsms.getDeviceId());
            map.put("sms_date", rawsms.getSmsDate());
            map.put("sms_body", toMap(rawsms.getSmsBody()));


            ret.add(map);
        }


        return ret;
    }

    private HashMap toMap(String smsBody) {
        return gson.fromJson(smsBody, HashMap.class);
    }

}

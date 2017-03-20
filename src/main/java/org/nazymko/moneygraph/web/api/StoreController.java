package org.nazymko.moneygraph.web.api;

import com.google.gson.Gson;
import org.nazymko.moneygraph.storage.dao.autodao.tables.daos.JooqParsedsmsDao;
import org.nazymko.moneygraph.storage.dao.autodao.tables.daos.JooqRawsmsDao;
import org.nazymko.moneygraph.storage.dao.autodao.tables.pojos.Parsedsms;
import org.nazymko.moneygraph.storage.dao.autodao.tables.pojos.Rawsms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by org.nazymko.moneygraph.org.nazymko on 13.03.2017.
 */
@RestController
@RequestMapping("storage")
public class StoreController {
    @Autowired
    JooqRawsmsDao rawsmsDao;
    @Autowired
    private JooqParsedsmsDao parsedsmsDao;

    @ResponseBody
    @RequestMapping(value = "cleanup/by/identification/{identification}", method = RequestMethod.GET)
    public String cleanup(@PathVariable("identification") String identification) {

        List<Rawsms> objects = rawsmsDao.fetchByDeviceId(identification);
        rawsmsDao.delete(objects);

        List<Parsedsms> parsedsms = parsedsmsDao.fetchByIdentification(identification);
        parsedsmsDao.delete(parsedsms);

        return "done : " + objects.size();
    }

    @ResponseBody
    @RequestMapping(value = "put", consumes = "application/json", method = RequestMethod.POST)
    public String put(@RequestBody final HashMap<Object, Object> json) {
        System.out.println("json = " + json);

        rawsmsDao.insert(createPojo(json));

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Rawsms> identfication = rawsmsDao.fetchByDeviceId(String.valueOf(json.get("identification")));

                List<org.nazymko.moneygraph.storage.dao.autodao.tables.pojos.Parsedsms> parsedsms = new ArrayList<>();
                Gson gson = new Gson();

                for (Rawsms rawsms : identfication) {
                    parsedsms.add(convert(rawsms, gson));
                }

                parsedsmsDao.insert(parsedsms);
            }

            private Parsedsms convert(Rawsms rawsms, Gson gson) {
                Map sms = gson.fromJson(rawsms.getSmsBody(), Map.class);
                Parsedsms parsedsms = new Parsedsms();

                parsedsms.setBody((String) sms.get("body"));
                parsedsms.setDate(new Timestamp(
                                Double.valueOf(
                                        String.valueOf(
                                                sms.get("date")
                                        )
                                ).longValue()
                        )
                );
                parsedsms.setRawsmsId(rawsms.getId());
                parsedsms.setAddressFrom((String) sms.get("address"));
                parsedsms.setIdentification(rawsms.getDeviceId());

                return parsedsms;
            }
        }).start();


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

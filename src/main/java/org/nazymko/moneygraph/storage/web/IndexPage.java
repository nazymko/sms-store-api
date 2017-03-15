package org.nazymko.moneygraph.storage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nazymko on 13.03.2017.
 */

@Controller()
@RequestMapping("/")
public class IndexPage {
    @RequestMapping("")
    @ResponseBody
    public String index() {
        return "UP";
    }
}

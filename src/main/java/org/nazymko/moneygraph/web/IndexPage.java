package org.nazymko.moneygraph.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by org.nazymko.moneygraph.org.nazymko on 13.03.2017.
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

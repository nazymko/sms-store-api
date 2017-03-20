package org.nazymko.moneygraph.web.ui;

import org.apache.maven.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by a.nazimko on 16.03.2017.
 */
@Controller
@RequestMapping("stats")
public class PhoneFilterController {

    @RequestMapping(value = "dofilter", method = RequestMethod.GET)
    public String filterPage(Model model) {


        return "phonefilterpage";
    }

}

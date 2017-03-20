package org.nazymko.moneygraph.web.ui;

import org.nazymko.moneygraph.analytics.Executor;
import org.nazymko.moneygraph.analytics.PipeReport;
import org.nazymko.moneygraph.analytics.Report;
import org.nazymko.moneygraph.analytics.model.MoneyType;
import org.nazymko.moneygraph.analytics.model.Processed;
import org.nazymko.moneygraph.analytics.model.Rule;
import org.nazymko.moneygraph.analytics.utils.ResultFilter;
import org.nazymko.moneygraph.testing.SmsConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * Created by a.nazimko on 17.03.2017.
 */
@Controller
public class BalanceController {
    @RequestMapping("test/balance")
    public String balance(Model model) throws IOException {
        final List<Processed> balance = new Executor().balance(new SmsConverter().open());

        final List<Processed> elements = ResultFilter.removeSingleSms(balance);
        final Report report1 = ResultFilter.convertIntoLegend(elements, 0);
        final Report report2 = ResultFilter.convertIntoLegend(elements, 1);


        model.addAttribute("money1", report1);
        model.addAttribute("money1", report2);

        return "balance";
    }


    @RequestMapping("test/pipe")
    public String pipe(Model model) throws IOException {
        final List<Processed> balance = new Executor().balance(new SmsConverter().open());

        final List<Processed> elements = ResultFilter.removeSingleSms(balance);
        final PipeReport pipeReport = ResultFilter.convertIntoPipeByWeek(elements, new Rule(1, MoneyType.OUTCOME));


        model.addAttribute("pipe", pipeReport);

        return "dounats";
    }
}

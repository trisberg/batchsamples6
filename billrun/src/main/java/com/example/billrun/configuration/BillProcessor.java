package com.example.billrun.configuration;

import com.example.billrun.model.Bill;
import com.example.billrun.model.Usage;
import org.springframework.batch.infrastructure.item.ItemProcessor;

public class BillProcessor implements ItemProcessor<Usage, Bill> {

    @Override
    public Bill process(Usage usage) {
        Double billAmount = usage.dataUsage() * .001 + usage.minutes() * .01;
        return new Bill(usage.id(), usage.firstName(), usage.lastName(),
                usage.dataUsage(), usage.minutes(), billAmount);
    }
}
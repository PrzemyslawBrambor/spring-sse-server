package twicecode.com.springsseserver.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import twicecode.com.springsseserver.services.ExchangeRateService;

import java.text.DecimalFormat;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private static Logger logger = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    @Override
    public String getFakeExchangeRate() {
        double min = 0.7;
        double max = 1.10;
        double diff = max - min;

        DecimalFormat formatter = new DecimalFormat("#0.00");  // edited here.
        double randomValue = min + Math.random() * diff;
        String formattedValue = formatter.format(randomValue);
        logger.info("1 US Dollar equals to {} Euro", formattedValue);
        return formattedValue;
    }
}

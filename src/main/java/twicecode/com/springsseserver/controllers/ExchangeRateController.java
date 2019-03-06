package twicecode.com.springsseserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;
import twicecode.com.springsseserver.services.impl.ExchangeRateServiceImpl;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Controller
public class ExchangeRateController {

    @Autowired
    ExchangeRateServiceImpl exchangeRateService;

    @GetMapping("/exchange-rate-stream")
    public SseEmitter streamSseMvc() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; true; i++) {
                    SseEventBuilder event = SseEmitter.event()
                            .data("1 US Dollar equals to " + exchangeRateService.getFakeExchangeRate() + " Euro")
                            .id(String.valueOf(i))
                            .name("message");
                    emitter.send(event);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }
}

package com.microservice.lab.configuration.web;

import com.ironsoftware.ironpdf.PdfDocument;
import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@RestController
@RequestMapping
public class HomeController {
    @GetMapping
    public String home() {
        return "Server lab runing at " + new Date();
    }

    @SneakyThrows
    @GetMapping("/{blNumber}")
    public CommonResponse<Object> printTrackingResponse(@PathVariable("blNumber") String blNumber) {
        String webPage = "https://silverial-express-tracking.project.jovalab.com/track?blNumber="+blNumber;
        String html = Jsoup.connect(webPage).get().html();
        PdfDocument myPdf = PdfDocument.renderHtmlAsPdf(html);
        try {
            myPdf.saveAs(Paths.get("html_saved.pdf"));
            return ResponseHelper.ok(Files.readAllBytes(Paths.get("html_saved.pdf")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

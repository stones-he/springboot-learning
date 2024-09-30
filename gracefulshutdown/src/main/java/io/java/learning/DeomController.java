package io.java.learning;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeomController {

    @PostMapping("/mailSendStatusCallback")
    public String getMessage(@RequestParam("msg") String statusMsg, String send_time, @RequestParam("james_server") String jamesServer) {
        System.out.println(send_time + ":" + statusMsg + ":" + jamesServer);
        return "OK";
    }
}

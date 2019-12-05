package net.atos.springquartzexample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SayGoodbyeService {


    public void sayGoodbye(){
        log.info("-----------> Goodbye <----------------");
    }
}

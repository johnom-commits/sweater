package org.example.sweater;

import org.example.sweater.domain.Message;
import org.example.sweater.repos.MessageRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    private final MessageRepo messageRepo;

    public GreetingController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        model.put("message", messageRepo.findAll());
        return "main";
    }

    @PostMapping("/")
    public String add(@RequestParam String text, @RequestParam String tag,
                      Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepo.save(message);
        model.put("message", messageRepo.findAll());
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("message", messages);
        return "main";
    }
}

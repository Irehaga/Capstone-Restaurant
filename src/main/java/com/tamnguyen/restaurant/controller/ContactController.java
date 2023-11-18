package com.tamnguyen.restaurant.controller;

import com.tamnguyen.restaurant.dto.ContactDTO;
import com.tamnguyen.restaurant.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.tamnguyen.restaurant.utility.PageTemaplate.CUSTOMER_SEND_CONTACT;


/**
 * @author Tam Nguyen
 */


@Controller
public class ContactController {

    private final EmailService emailService;
    @Autowired
    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/contact")
    public String showContactForm(Model model) {
//        if(!model.containsAttribute("contactDTO")){
//            model.addAttribute("contactDTO", new ContactDTO());
//        }
        model.addAttribute("contactDTO", new ContactDTO());
        return CUSTOMER_SEND_CONTACT;
    }


    @PostMapping("/contact")
    public String sendContactEmail(@ModelAttribute("contactDTO") ContactDTO contactDTO, RedirectAttributes redirectAttributes){
        String content  = buildEmailContact(contactDTO);
        String to = "tam@gmail.com";
        emailService.sendEmail(to, contactDTO.getSubject(), content, contactDTO.getEmail());
        redirectAttributes.addFlashAttribute("message", "Thank you for contacting us.");
        return "redirect:/contact";

    }

    private String buildEmailContact(ContactDTO contactDTO){
        return "A message from " + contactDTO.getName() +
                "(" + contactDTO.getEmail() +
                "): \n\n" + contactDTO.getMessage();
    }

}// end of class

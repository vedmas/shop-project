package ru.tokarev.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.tokarev.shop.controller.repr.ProducerRepr;
import ru.tokarev.shop.repository.entity.Producers;
import ru.tokarev.shop.service.producer.ProducerService;

@Controller
@RequestMapping("/admin")
public class ProducerAdminController {

    private ProducerService producerService;

    @Autowired
    public void setProducerService(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/producers")
    public String producersPage(Model model) {
        model.addAttribute("activePage", "Producers");
        model.addAttribute("producers", producerService.findAll());
        return "/admin/producers";
    }

    @GetMapping("/producer/{id}/edit")
    public String producerEdit(@PathVariable Long id, Model model) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Producers");
        model.addAttribute("producer", producerService.get(id));
        return "admin/producer_form";
    }

    @GetMapping("/producer/create")
    public String producerCreate(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Producers");
        model.addAttribute("producer", new Producers());
        return "admin/producer_form";
    }

    @PostMapping("/producer")
    public String actionOnTheProducer(ProducerRepr producerRepr, RedirectAttributes redirectAttributes) {
        try {
            producerService.saveProducer(producerRepr);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", true);
            if (producerRepr.getId() == null) {
                return "redirect:/admin/producer/create";
            }
            return "redirect:/admin/producer/" + producerRepr.getId() + "/edit";
        }
        return "redirect:/admin/producers";
    }

    @GetMapping("/producer/{id}/delete")
    public String producerDelete(@PathVariable Long id, Model model) {
        producerService.deleteById(id);
        model.addAttribute("activePage", "Producers");
        return "redirect:/admin/producers";
    }
}

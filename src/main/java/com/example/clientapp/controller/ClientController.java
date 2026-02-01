package com.example.clientapp.controller;

import com.example.clientapp.model.Client;
import com.example.clientapp.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // ================= LIST ALL CLIENTS =================
    @GetMapping("/list")
    public String listClients(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "client-list"; // the page we just built
    }

    // ================= UPDATE CLIENT =================
    @PostMapping("/update")
    public String updateClient(@ModelAttribute Client client, RedirectAttributes redirectAttributes) {
        // Fetch existing client from DB
        Client existingClient = clientRepository.findById(client.getId())
                .orElse(null);

        if (existingClient != null) {
            existingClient.setName(client.getName());
            existingClient.setEmail(client.getEmail());
            existingClient.setPhone(client.getPhone());
            existingClient.setAddress(client.getAddress());

            clientRepository.save(existingClient);

            redirectAttributes.addFlashAttribute("successMessage", "Client updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Client not found");
        }

        return "redirect:/client/list";
    }

    // ================= DELETE CLIENT =================
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Client deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Client not found");
        }
        return "redirect:/client/list";
    }
}

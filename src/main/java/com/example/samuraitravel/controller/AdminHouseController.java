package com.example.samuraitravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.service.HouseService;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.form.HouseRegisterForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.form.HouseEditForm;

@Controller
@RequestMapping("/admin/houses")
public class AdminHouseController {
    private final HouseRepository houseRepository;
    private final HouseService houseService;

    public AdminHouseController(HouseRepository houseRepository,HouseService houseService) {
        this.houseRepository = houseRepository;
        this.houseService = houseService;
    }

    @GetMapping
    public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
        Page<House> housePage;

        if (keyword != null && !keyword.isEmpty()) {
            housePage = houseRepository.findByNameLike("%" + keyword + "%", pageable);
        } else {
            housePage = houseRepository.findAll(pageable);
        }

        model.addAttribute("housePage", housePage);
        model.addAttribute("keyword", keyword);

        return "admin/houses/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        House house = houseRepository.getReferenceById(id);

        model.addAttribute("house", house);

        return "admin/houses/show";
    }

    @GetMapping("/register")
    public  String register(Model model) {
        model.addAttribute("houseRegisterForm", new HouseRegisterForm());
        return "admin/houses/register";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Validated HouseRegisterForm houseRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/houses/register";
        }

        houseService.create(houseRegisterForm);
        redirectAttributes.addFlashAttribute("successMessage","民宿を登録しました。");

        return "redirect:/admin/houses";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable(name = "id") Integer id, Model model) {
        House house = houseRepository.getReferenceById(id);
        String imageName = house.getImageName();
        HouseEditForm houseEditForm = new HouseEditForm(house.getId(),house.getName(),null,house.getDescription(),house.getPrice(),house.getCapacity(),house.getPostalCode(),house.getAddress(),house.getPhoneNumber());

        model.addAttribute("imageName", imageName);
        model.addAttribute("houseEditForm", houseEditForm);

        return "admin/houses/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@ModelAttribute @Validated HouseEditForm houseEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/houses/edit";
        }

        houseService.update(houseEditForm);
        redirectAttributes.addFlashAttribute("successMessage","民宿情報を編集しました。");

        return "redirect:/admin/houses";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        houseRepository.deleteById(id);

        redirectAttributes.addFlashAttribute("successMessage","民宿を削除しました。");

        return "redirect:/admin/houses";
    }
}

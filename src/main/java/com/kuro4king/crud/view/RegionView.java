package com.kuro4king.crud.view;

import com.kuro4king.crud.controller.RegionController;
import com.kuro4king.crud.model.Region;

import java.io.IOException;

public class RegionView extends ViewClass {
    private RegionController regionController = new RegionController();

    protected void print() {
        System.out.println("Введите номер действия, которые желаете произвести:");
        System.out.println("1. Вывести список всех регионов.");
        System.out.println("2. Вывести регион по заданному id.");
        System.out.println("3. Добавить новый регион.");
        System.out.println("4. Удалить регион.");
        System.out.println("5. Изменить наименование региона.");
        System.out.println("6. Вернуться к выбору файла");
        System.out.println("7. Завершить программу.");
    }

    protected void choose() throws IOException {
        int choice = scanner().nextInt();
        switch (choice) {
            case 1 -> this.getAll();
            case 2 -> this.getById();
            case 3 -> this.create();
            case 4 -> this.delete();
            case 5 -> this.update();
            case 6 -> super.exit = true;
            case 7 -> System.exit(0);
        }
    }


    public void create() throws IOException {
        System.out.println("Введите название региона:");
        String regionName = scanner().nextLine();
        Region region = regionController.createRegion(regionName);
        System.out.println("Вы добавили новый регион: " + region);
    }

    public void getAll() throws IOException {
        System.out.println("Список регионов:");
        System.out.println(regionController.getAll());
    }

    public void delete() throws IOException {
        this.getAll();
        System.out.println("Введите id региона, который желаете удалить:");
        Long id = scanner().nextLong();
        Region deletedRegion = regionController.getRegionById(id);
        regionController.deleteRegion(id);
        System.out.println("Удалён регион " + deletedRegion);
        this.getAll();
    }

    public void getById() throws IOException {
        System.out.println("Введите id региона, который желаете получить:");
        Long id = scanner().nextLong();
        Region region = regionController.getRegionById(id);
        System.out.println("Запрашиваемый регион: " + region);
    }

    public void update() throws IOException {
        System.out.println("Введите id региона, который хотите изменить:");
        Long id = scanner().nextLong();
        System.out.println("Введите новое название региона:");
        String name = scanner().nextLine();
        regionController.updateRegion(id, name);
        this.getAll();
    }
}

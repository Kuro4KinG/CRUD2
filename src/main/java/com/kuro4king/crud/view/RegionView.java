package com.kuro4king.crud.view;

import com.kuro4king.crud.controller.RegionController;
import com.kuro4king.crud.model.Region;

import java.io.IOException;

public class RegionView extends ViewClass{
    private RegionController regionController = new RegionController();

    public void start() throws IOException {
        boolean exit = false;
        do {
            System.out.println("Введите номер действия, которые желаете произвести:");
            System.out.println("1. Вывести список всех регионов.");
            System.out.println("2. Вывести регион по заданному id.");
            System.out.println("3. Добавить новый регион.");
            System.out.println("4. Удалить регион.");
            System.out.println("5. Изменить наименование региона.");
            System.out.println("6. Вернуться к выбору файла");
            System.out.println("7. Завершить программу.");

            int choice = scanner().nextInt();
            switch (choice) {
                case 1 -> this.getAllRegions();
                case 2 -> this.getRegionById();
                case 3 -> this.createRegion();
                case 4 -> this.deleteRegion();
                case 5 -> this.updateRegion();
                case 6 -> exit = true;
                case 7 -> System.exit(0);
            }
        } while (!exit);
    }



    public void createRegion() throws IOException {
        System.out.println("Введите название региона:");
        String regionName = scanner().nextLine();
        Region region = regionController.createRegion(regionName);
        System.out.println("Вы добавили новый регион: " + region);
    }

    public void getAllRegions() throws IOException {
        System.out.println("Список регионов:");
        System.out.println(regionController.getAll());
    }

    public void deleteRegion() throws IOException {
        this.getAllRegions();
        System.out.println("Введите id региона, который желаете удалить:");
        Long id = scanner().nextLong();
        Region deletedRegion = regionController.getRegionById(id);
        regionController.deleteRegion(id);
        System.out.println("Удалён регион " + deletedRegion);
        this.getAllRegions();
    }

    public void getRegionById() throws IOException {
        System.out.println("Введите id региона, который желаете получить:");
        Long id = scanner().nextLong();
        Region region = regionController.getRegionById(id);
        System.out.println("Запрашиваемый регион: " + region);
    }

    public void updateRegion() throws IOException {
        System.out.println("Введите id региона, который хотите изменить:");
        Long id = scanner().nextLong();
        System.out.println("Введите новое название региона:");
        String name = scanner().nextLine();
        regionController.updateRegion(id, name);
        this.getAllRegions();
    }
}

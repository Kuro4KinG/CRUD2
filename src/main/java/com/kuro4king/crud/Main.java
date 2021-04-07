package com.kuro4king.crud;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.kuro4king.crud.implementation.ioimpl.JavaIORegionRepositoryImpl;
import com.kuro4king.crud.implementation.jsonimpl.JsonRegionRepositoryImpl;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.view.FIleVIew;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
//        FIleVIew mainView = new FIleVIew();
//        mainView.start();
//        final String regionPath = "src/main/resources/files/json/regions.json";
//
//        Gson gson = new Gson();
////        FileReader fileReader = new FileReader(regionPath);
////        List<Region> regions = gson.fromJson(fileReader, new TypeToken<ArrayList<Region>>() {
////        }.getType());
////        fileReader.close();
//        List<Region> regions = new ArrayList<>();
//        JsonReader jsonReader = new JsonReader(new FileReader(regionPath));
//        jsonReader.beginArray();
//        while (jsonReader.hasNext()) {
//            regions.add(gson.fromJson(jsonReader, Region.class));
//        }
//        System.out.println(regions);

        new FIleVIew().start();
    }
}

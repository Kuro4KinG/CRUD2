package com.kuro4king.crud;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.kuro4king.crud.controller.PostController;
import com.kuro4king.crud.controller.RegionController;
import com.kuro4king.crud.controller.UserController;
import com.kuro4king.crud.implementation.ioimpl.JavaIOPostRepositoryImpl;
import com.kuro4king.crud.implementation.ioimpl.JavaIORegionRepositoryImpl;
import com.kuro4king.crud.implementation.ioimpl.JavaIOUserRepositoryImpl;
import com.kuro4king.crud.implementation.jsonimpl.JsonPostRepositoryImpl;
import com.kuro4king.crud.implementation.jsonimpl.JsonRegionRepositoryImpl;
import com.kuro4king.crud.implementation.jsonimpl.JsonUserRepositoryImpl;
import com.kuro4king.crud.model.Post;
import com.kuro4king.crud.model.Region;
import com.kuro4king.crud.model.Role;
import com.kuro4king.crud.model.User;
import com.kuro4king.crud.view.FIleVIew;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
//        new FIleVIew().start();
        //       System.out.println(new JsonPostRepositoryImpl().getAll());
        //new JsonUserRepositoryImpl().update()
          System.out.print(new JsonUserRepositoryImpl().getAll());


    }
}

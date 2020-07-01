package com.app.motion.ui.data;


import com.app.motion.ui.model.HomeModel;

import java.util.ArrayList;

public class DummyDataGenerator {

    public static ArrayList<HomeModel> generateDataHome() {
        ArrayList<HomeModel> list = new ArrayList<>();
        list.add(new HomeModel("One", "Two"));
        list.add(new HomeModel("One", "Two"));
        return list;
    }

}
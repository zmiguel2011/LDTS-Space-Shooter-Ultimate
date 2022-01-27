package com.group605.spaceshooterultimate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public List<String> readFile(String path) throws IOException {

        List<String> ascii = new ArrayList<>();

        File file = new File(path);
        Scanner reader = new Scanner(file);

        while(reader.hasNext()){
            ascii.add(reader.nextLine());
        }

        reader.close();

        return ascii;
    }
}

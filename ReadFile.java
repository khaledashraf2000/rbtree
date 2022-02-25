package com.company;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class ReadFile {
    public static RedBlackTree File(String filename) {
        try{
            File myfile= new File(filename);
            Scanner myReader = new Scanner(myfile);
            RedBlackTree redBlackTree = new RedBlackTree();
            while(myReader.hasNextLine())
            {
                redBlackTree.insert(myReader.nextLine());
            }
            myReader.close();
            System.out.println("File loaded successfully!");
            return redBlackTree;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return null;
    }

}

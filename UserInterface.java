package com.company;

import java.util.Scanner;

public class UserInterface {
    public static void Interface()
    {
        Scanner scanner = new Scanner(System.in);
        String filename = new String();
        RedBlackTree redBlackTree = new RedBlackTree();

        while (true)
        {
            System.out.println("1.Load dictionary\n2.Print dictionary size\n3.Insert word\n4.Look-up a word\n5.Exit\nChoose from the previous operations: ");
            String ans = new String(scanner.nextLine());
            String word = new String();

            if (ans.equals("1"))
            {
                System.out.println("Enter the file name: ");
                filename= scanner.nextLine();
                redBlackTree = ReadFile.File(filename);
            }
            else if (ans.equals("2") && redBlackTree.getSize() != 0)
            {
                System.out.println("Dictionary size is: "+ redBlackTree.getSize()+" word");
            }
            else if(ans.equals("3")&& redBlackTree.getSize() != 0)
            {
                System.out.println("Insert word: ");
                word=scanner.next();
                redBlackTree.insert(word);
                System.out.println("Tree size is: "+redBlackTree.getSize()+"word");
                System.out.println("Tree height is: "+redBlackTree.height());
            }
            else if (ans.equals("4")  && redBlackTree.getSize() != 0)
            {
                System.out.println("Enter the word: ");
                word=scanner.nextLine();

                if (redBlackTree.contains(word))
                {
                    System.out.println("Word has been found!");
                }
                else
                {
                    System.out.println("Word is not found!");

                }
            }
            else if (ans.equals("5"))
            {
                System.out.println("Thanks for using our application.");
                break;
            }
            else
            {
                System.out.println("Enter Valid choice!\n");

            }
        }
    }
}

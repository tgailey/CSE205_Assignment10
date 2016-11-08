// Assignment #: 10
//         Name: Trenton Gailey
//    StudentID: 1211386693
//      Lecture: Monday Wednesday Friday 9:40 - 10:30
//  Description: The Assignment 10 class displays a menu of choices to a user
//               and performs the chosen task. It will keep asking a user to
//               enter the next choice until the choice of 'Q' (Quit) is
//               entered.

import java.io.*;

public class Assignment10
{
   public static void main(String[] args)
   {
      char input1;
       String inputInfo = new String();
       int operation2;
       String line = new String();

       //create a linked list to be used in this method.
       LinkedList list1 = new LinkedList();

       try
        {
         // print out the menu
         printMenu();

         // create a BufferedReader object to read input from a keyboard
         InputStreamReader isr = new InputStreamReader (System.in);
         BufferedReader stdin = new BufferedReader (isr);

         do
          {
           System.out.print("What action would you like to perform?\n");
           line = stdin.readLine().trim();  //read a line
           input1 = line.charAt(0);
           input1 = Character.toUpperCase(input1);

           if (line.length() == 1)   //check if a user entered only one character
            {
             switch (input1)
              {
               case 'A':   //Add String
                 System.out.print("Please enter a string to add:\n");
                 String str1 = stdin.readLine().trim();
                 list1.addElement(str1);
                 break;
               case 'G':   //Get a String at a certain index
                 System.out.print("Please enter an index of a string to get:\n");
                 inputInfo = stdin.readLine().trim();
                 int getIndex = Integer.parseInt(inputInfo);
                 try
                  {
                   String getString = (String)list1.getElement(getIndex);
                   System.out.print("The string at the index " + getIndex + " is " 
                                    + getString + "\n");    
                  }
                 catch(IndexOutOfBoundsException ex)
                  {
                   System.out.print("The index is not valid\n");
                  }
                 break;
               case 'E':   //Check if the linked list is empty
                 boolean empty = list1.isEmpty();
                 if (empty)
                  System.out.print("The linked list is empty\n");
                 else
                  System.out.print("The linked list is not empty\n");
                 break;
               case 'I':   //Get the last index of a given String
                 System.out.print("Please enter a string to search:\n");
                 String str2 = stdin.readLine().trim();
                 int searchIndex = list1.indexOfLast(str2);
                 if (searchIndex != -1)
                  System.out.print("The index of the last " + str2 + " is " + searchIndex
                                   + "\n");
                 else
                  System.out.print("The string " + str2 
                                 + " does not exist in the linked list\n");
                 break;
               case 'L':   //List Strings
                 System.out.print(list1.toString());
                 break;
               case 'Q':   //Quit
                 break;
               case 'R':  //Remove a String
                 System.out.print("Please enter an index of a string to remove:\n");
                 inputInfo = stdin.readLine().trim();
                 int removeIndex = Integer.parseInt(inputInfo);
                 try
                  {
                   String removeString = (String) list1.removeElement(removeIndex);
                   System.out.print("The string at the index " + removeIndex + " was "   
                                    + removeString + " and was removed\n");
                  }
                 catch(IndexOutOfBoundsException ex)
                  {
                   System.out.print("The index is not valid\n");
                  }
                 break;
               case 'S':   //Search and Replace
                 System.out.print("Please enter a string to replace:\n");
                 String original = stdin.readLine().trim();
                 System.out.print("Please enter a string used to replace with:\n");
                 String newStr = stdin.readLine().trim();
                 list1.searchAndReplace(original, newStr);
                 break;
               case '?':   //Display Menu
                 printMenu();
                 break;
               default:
                 System.out.print("Unknown action\n");
                 break;
              }
           }
          else
           {
             System.out.print("Unknown action\n");
            }
          } while (input1 != 'Q' || line.length() != 1);
        }
       catch (IOException exception)
        {
          System.out.print("IO Exception\n");
        }
    }

    /** The method printMenu displays the menu to a user **/
    public static void printMenu()
     {
       System.out.print("Choice\t\tAction\n" +
                        "------\t\t------\n" +
                        "A\t\tAdd String\n" +
                        "G\t\tGet String\n" +
                        "E\t\tCheck if Empty\n" +
                        "I\t\tIndex of Last String\n" +
                        "L\t\tList Strings\n" +
                        "Q\t\tQuit\n" +
                        "R\t\tRemove String\n" +
                        "S\t\tSearch and Replace\n" +
                        "?\t\tDisplay Help\n\n");
    } //end of printMenu()
}
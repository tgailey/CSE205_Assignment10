// Assignment #: 10
//         Name: Trenton Gailey
//    StudentID: 1211386693
//      Lecture: Monday Wednesday Friday 9:40 - 10:30
//  Description: A linked list is a sequence of nodes with efficient
// 				 element insertion and removal.
// 				 This class contains a subset of the methods of the
// 				 standard java.util.LinkedList class.
//				 This class will allow the user to addElements to list in alphabetical order
//				 remove an element at a specific index, return an element at given index
//				 Search for a certain element and replace it with another
//				 Print out a list in string format, Check if list is empty
//				 And search for given element and return its index

import java.util.NoSuchElementException;

public class LinkedList
{
   //nested class to represent a node
   private class Node
   {
          public Object data;
          public Node next;
   }

   //only instance variable that points to the first node.
   private Node first;

   // Constructs an empty linked list.
   public LinkedList()
   {
      first = null;
   }


   // Returns the first element in the linked list.
   public Object getFirst()
   {
      if (first == null)
       {
         NoSuchElementException ex
             = new NoSuchElementException();
         throw ex;
       }
      else
         return first.data;
   }

   // Removes the first element in the linked list.
   public Object removeFirst()
   {
      if (first == null)
       {
         NoSuchElementException ex = new NoSuchElementException();
         throw ex;
       }
      else
       {
         Object element = first.data;
         first = first.next;  //change the reference since it's removed.
         return element;
       }
   }

   // Adds an element to the front of the linked list.
   public void addFirst(Object element)
   {
      //create a new node
      Node newNode = new Node();
      newNode.data = element;
      newNode.next = first;
      //change the first reference to the new node.
      first = newNode;
   }

   // Returns an iterator for iterating through this list.
   public ListIterator listIterator()
   {
      return new LinkedListIterator();
   }

   //Transverses the iterator and prints out the string for each node
   public String toString() {
	   String temp = "{ ";
	   ListIterator iterator = this.listIterator();
	   //While there is still things in the list, continue printing out the next one
	   while (iterator.hasNext()) {
		   temp += (String)iterator.next() + " ";
	   }
	   temp += "}\n";
	   return temp; 
   }
   public boolean isEmpty() {
	   //If the first value is null, then return true for it being empty
	   if (first == null)
		   return true;
	   //Else, there is something in the first value and it is not empty
	   else
		   return false;
   }
   public void addElement(Object element) {
	   ListIterator iterator = this.listIterator();
	   int count = 0;
	   //For each element in the list that precedes the element, add one to count.
	   while (iterator.hasNext() &&((String)iterator.next()).compareTo((String)element) < 0) {
		   count++;
	   }
	   //reset iterator
	   iterator = this.listIterator();
	   //If count is 0, add the element to the front of the list
	   if (count == 0) {
		   iterator.add(element);
	   }
	   else {
		   //Create local int upTo that will move the iterator to the spot BEFORE
		   		//(before the value that is greater lexigraphically to the given string)
		   		//And then add it after that point
		   int upTo = 0;
		   while (upTo < count) {
			   iterator.next();
			   upTo++;
		   }
		   iterator.add(element);
	   }
   }
   public Object removeElement(int index) {
	   ListIterator iterator = this.listIterator();
	   int count = 0;
	   //Variable to hold the item that will be removed
	   Object removedItem = null;
	   //Transverse the list to the index
	   while (count <= index) {
		   //Making sure that the iterator can move forward (index is not > length)
		   if (iterator.hasNext()) {
			   //Once the count gets to the index, set removedItem to value and remove it
			   if (count == index) {
				   removedItem = iterator.next();
				   iterator.remove();
			   }
			   else {
				   //Else move to the next element
				   iterator.next();
			   }
		   }
		   //Add one to count after each run
		   count++;
	   }
	   //If the index is too big or small, throw exception
	   if (removedItem == null) {
		   throw new IndexOutOfBoundsException();
	   }
	   //Otherwise, return the removed item
	   else {
		   return removedItem;
	   }
   }
   public Object getElement(int index) {
	   //Variable to hold the element that is gotten
	   Object theElement = null;
	   int count = 0;
	   ListIterator iterator = this.listIterator();
	   
	   //Transverse the list to the index
	   while (count <= index) {
		   //Make sure list can move forward/index is not too big
		   if (iterator.hasNext()) {
			   //Once the count gets to the index, set theElement to value
			   if (count == index) {
				   theElement = iterator.next();
			   }
			   else {
				   //Else move forward in list
				   iterator.next();
			   }
		   }
		   //Increase the count after each run
		   count++;
	   }
	   //If index is too high/low, throw out of bounds exception
	   if (theElement == null) {
		   throw new IndexOutOfBoundsException();
	   }
	   //Else return the "got"ten element
	   else {
		   return theElement;
	   }
   }
   public void searchAndReplace(Object oldString, Object newString) {
	   ListIterator iterator = this.listIterator();
	   //Transverse the list, if a value equals the string given
	   		//and sets that value to new value
	   while (iterator.hasNext()) {
		   if (((String)iterator.next()).compareTo((String)oldString) == 0) {
			   iterator.set(newString);
		   }
	   }
   }
   public int indexOfLast(Object searchString) {
	   ListIterator iterator = this.listIterator();
	   int count = 0;
	   int index = -1;
	   //transverse the list, if the given string is found, set it to the index
	   		//of the value in the list and return it at the end
	   while (iterator.hasNext()) {
		   if (((String)iterator.next()).compareTo((String)searchString) == 0) {
			   index = count;
		   }
		   count++;
	   }
	   return index;
   }
   //nested class to define its iterator
   private class LinkedListIterator implements ListIterator
   {
      private Node position; //current position
      private Node previous; //it is used for remove() method

      // Constructs an iterator that points to the front
      // of the linked list.

      public LinkedListIterator()
      {
         position = null;
         previous = null;
      }

     // Tests if there is an element after the iterator position.
     public boolean hasNext()
      {
         if (position == null) //not traversed yet
          {
             if (first != null)
                return true;
             else
                return false;
          }
         else
           {
              if (position.next != null)
                 return true;
              else
                 return false;
           }
      }

      // Moves the iterator past the next element, and returns
      // the traversed element's data.
      public Object next()
      {
         if (!hasNext())
          {
           NoSuchElementException ex = new NoSuchElementException();
           throw ex;
          }
         else
          {
            previous = position; // Remember for remove

            if (position == null)
               position = first;
            else
               position = position.next;

            return position.data;
          }
      }

      // Adds an element before the iterator position
      // and moves the iterator past the inserted element.
      public void add(Object element)
      {
         if (position == null) //never traversed yet
         {
            addFirst(element);
            position = first;
         }
         else
         {
            //making a new node to add
            Node newNode = new Node();
            newNode.data = element;
            newNode.next = position.next;
            //change the link to insert the new node
            position.next = newNode;
            //move the position forward to the new node
            position = newNode;
         }
         //this means that we cannot call remove() right after add()
         previous = position;
      }

      // Removes the last traversed element. This method may
      // only be called after a call to the next() method.
      public void remove()
      {
         if (previous == position)  //not after next() is called
          {
            IllegalStateException ex = new IllegalStateException();
            throw ex;
          }
         else
          {
           if (position == first)
            {
              removeFirst();
            }
           else
            {
              previous.next = position.next; //removing
            }
           //stepping back
           //this also means that remove() cannot be called twice in a row.
           position = previous;
      }
      }

      // Sets the last traversed element to a different value.
      public void set(Object element)
      {
         if (position == null)
          {
            NoSuchElementException ex = new NoSuchElementException();
            throw ex;
          }
         else
          position.data = element;
      }
   } //end of LinkedListIterator class
} //end of LinkedList class
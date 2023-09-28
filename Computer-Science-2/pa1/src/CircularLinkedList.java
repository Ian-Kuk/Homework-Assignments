import java.util.Scanner;

public class CircularLinkedList <E> {
    // Instance variables. You cannot add or remove these starting variables
    private Node<E> tail;
    private Node<E> head;
    private int size;

    // Default Constructor sets the tail-->null and size-->0
    public CircularLinkedList() {
        tail = null;
        head = null;
        size = 0;
    }
 
 
    /** Access Methods */
    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public E getFirst() {
        if (isEmpty()){
            return null;
        }
        else{
            return head.getNext().getElement();
        }
    }
    public E getLast() {
        if (isEmpty()){
            return null;
        }
        else{
            return tail.getElement();
        }
    }
    // Returns true if it's in the list, otherwise return false
    public boolean containValue(E e) {
        if(tail.getNext() !=null ) {
            return tail.getNext().getElement() == e;
        }
        else{
            return false;
        }
    }
    //Returns new list
    // list = [1,2,3,4,5]
    // list.delete(4) => [1,2,3,5,6]
    // Some cases to consider:
        //The circular linked list has only one element, and we want to remove the element
        //The element to delete is the head node
        //The element to delete is the tail node
    public void deleteNode(E e) {
        if(isEmpty()) {
            return;
        }

        if (containValue(e))
        {
            Node<E> temp;
            if (size == 1)
            {
                head = null;
                tail.setNext(null);
                size = 0;
            }
            else{
                temp = head.getNext();
                if(temp == e && temp.getNext() != null){//&& temp != head){
                    head.setNext(temp.getNext().getNext());
                    //tail.setNext(head);
                }
                /*if(temp == e && temp == head){
                    tail.setNext(temp.getNext());
                }
                if(temp == e && temp == tail){
                    head.setNext(temp.getNext());
                    tail.setNext(head);
                }*/
            }
        }

    }
    /** Update methods */
    // Left Rotate the elements in the list. (Hint: Tail becomes tail.getNext())
    // list                 --> [1,2,3,4,5]
    // list_after_rotate_1  --> [2,3,4,5,1]
    // list_after_rotate_2  --> [3,4,5,1,2]
    public void rotate()
    {
        Node<E> temp = head.getNext();
        if(head != null){
            head = tail.getNext();
            tail = temp;

        }


    }
    // Add an element at the start of the list. (Hint: The first element of a circular linked list is tail.getNext())
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e,null);

        if(head==null)
        {
            head = newNode;
            tail = newNode;
            tail.setNext(head);
        }
        else
        {
            newNode.setNext(tail.getNext());
            head.setNext(newNode);
        }
        size++;
    }
    // Adding the element at the tail. Question: Can you implement this function using addFirst and rotate?
    // list = [1,2,3,4,5]
    // list.addLast(6) => [1,2,3,4,5,6]
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e,null);
        if(head == null) {
            head = newNode;
            tail = newNode;
            tail.setNext(head);
        }
        else
        {
            newNode.setNext(tail.getNext());
            head.setNext(newNode);

        }
        rotate();

        size++;
    }
    // Remove the first element of the list and return the removed element.
    public E removeFirst()
    {
        if(head != null) {
            final Node<E> val = head.getNext();
            //if(head.getNext())
            if (this.size > 1) {

                Node<E> temp = tail.getNext();
                head.setNext(temp.getNext());
                size--;
            }
            return val.getElement();
        }
        else{
            size = 0;
            return null;
        }
    }

    // Prints out the list elements.
    // IF these are the elements of the linked list, then they will be matched with the corresponding output
    // 1)--> "prius", "rav4", "subaru", "crv", "pilot"
    // 2)--> 1,2,3,4,5
    // 3)--> []
    // Outputs
    // 1)-->[prius, rav4, subaru, crv, pilot, prius, rav4, subaru, crv, pilot]
    // 2)-->[1, 2, 3, 4, 5, 1, 2, 3, 4, 5]
    // 3)-->[empty list]
    public String toString(){
        String string;
        Node<E> node;
        int counter =0;
        if ( tail == null )
            return "null";
        string = "[";
        node = tail.getNext();
        if (node==null)
        {
            return string+ "empty list]";
        }

        while (node!=null&&counter<(2*size))
        {
            counter++;
            string = string+ node.getElement();
            if (node.getNext()!=null) string = string + ", ";
            node = node.getNext();
        }
        return string+"]";
    }


    public static void main(String args[]){
        String[] cars = { "prius", "rav4", "subaru", "crv", "pilot"};

        CircularLinkedList<String> carsList = new CircularLinkedList<String>();
        for (String i: cars)
            carsList.addLast(i);

        System.out.println("linkedList:"+ carsList.toString());
        // Output for this should be --> linkedList:[prius, rav4, subaru, crv, pilot, prius, rav4, subaru, crv, pilot]
    }
}

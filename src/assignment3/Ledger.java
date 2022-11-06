//47382481， Hongting Su
//Put x inside [] below:
//[x] 	This assignment is entirely my own work and 
// 		I have not seen anyone else's code or design
package assignment3;

//Students must ensure they complete both the addPayment and size() method to pass the other test cases.
public class Ledger {

  public Payment head;
  public int id;
  public Ledger next;

  // add attributes as needed
  // needed to implement efficient code
  // tested for `advanced' parts
  public int ledgerSize;
  public Payment tail;

  /**
   * Default constructor
   * 
   * You may modify this constructor if you need to (e.g.
   * if you want to initialise extra attributes in the class)
   */
  public Ledger() {
    head = null;
    id = 0;
    next = null;
    //
    // add initialisation of added attributes, if needed
    ledgerSize = 0;
  }

  /**
   * Assign an id to the current Ledger
   * 
   * @param id
   */
  public void setId(int id) {
    //
    // Provided - not to be changed
    this.id = id;
  }

  /**
   * 
   * @return the number of payments in the Ledger
   */
  public int size() {
    return ledgerSize;
  }

/**
 * Check if the ledger is empty
 * 
 * @return True if the ledger is empty, false otherwise
 */
  public boolean isEmpty(){
    if (head == null) {
      return true;
    }
    return false;
  }

  /**
   * Add a payment at the end of the Ledger list
   * 
   * @param payment
   */
  public void addPayment(Payment payment) {

    // if Payment is null, do nothing
    if (payment == null) {
      return ;
    }
    
    Payment temp = payment;
    
    //if ledger is empty, add the payment to the head
    if (isEmpty()) {
      head = temp;
      temp.next = null;
      
      //set the tail node
      tail = temp;

      // increment ledger size
      ledgerSize ++ ;

      //terminate the method
      return ;
    }

    //add the new payment at the end of list
    tail.next = temp;

    //update the tail node
    tail = temp;

    //not adding the entire payment chain
    tail.next = null;

    ledgerSize ++;
  }

  /**
   * A Ledger is valid if all its payments are valid:
   * i.e. their are all done between registered persons provided in the array
   * persons
   * 
   * You may assume that the input array (String[] persons) will
   * not be null or empty.
   * 
   * @param persons - array of persons who can make and receive payment
   * 
   * @return true if the Ledger is valid based on the criteria listed above,
   *         false otherwise
   */
  public boolean isValid(String[] persons) {

    if (isEmpty()) {
      return true;
    }

    Payment current = head;
    while (current != null) {
      if (!current.isValid(persons)) {
        return false;
      }
      current = current.next;
    }
    return true;
  }

  /**
   * Check whether a payment involves a particular person
   * 
   * @param person
   * @return true if person is one of the persons involved in one of the payments
   */
  public boolean contains(String person) {

    if (isEmpty()) {
      return false;
    }

    Payment current = head;
    while (current != null) {
      if (current.fromPerson.equalsIgnoreCase(person) || current.toPerson.equalsIgnoreCase(person)) {
        return true;
      }
      current = current.next;
    }
    return false;
  }

  /**
   * Provide the net payment or profit made by the person in this ledger
   * 
   * @param person involved in payment
   * @return net balance paid or received
   * 
   *         return 0 if the ledger is empty or the person not present in this
   *         ledger
   */
  public double balance(String person) {
    
    if (isEmpty() || !contains(person)) {
      return 0;
    }

    //the initial balance of the person
    double accBalance = 0.0;

    Payment current = head;
    while (current != null) {

      //if the person is the to person
      if (person.equalsIgnoreCase(current.toPerson)) {
        accBalance += current.amount;
      }

      //if the person is the from person
      if (person.equalsIgnoreCase(current.fromPerson)) {
        accBalance -= current.amount;
      }

      current = current.next;

    }
    return accBalance;    
  }

  /**
   * Reverse a payment by creating and adding a payment that reverse the transfer
   * 
   * @param paymentToReverse is the Payment node with transfer to reverse
   *                         i.e., there is an additional payment inversing the
   *                         transfer to reverse
   */
  public void reversePayment(Payment paymentToReverse) {

    // do nothing if paymentToReverse is null
    if (paymentToReverse == null) {
      return ;
    }

    //add the payment to the ledger as a counter-payment
    addPayment(paymentToReverse);

    //reverse the payment
    Payment current = head;
    while (current != null) {
      if (paymentToReverse.equals(current)) {
        String temp = current.toPerson;
        current.toPerson = current.fromPerson;
        current.fromPerson = temp; 
      }
      current = current.next;
    }

  }

  /**
   * 5 marks - Pass level
   * 
   * Remove the first Payment from the Ledger
   * 
   * @return the removed payment if there is one, null otherwise
   */
  public Payment remove() {

    if (isEmpty()) {
      return null;
    }

    //back-up the payment to be remvoed
    Payment removed = head;

    //remove the payment
    head = head.next;

    //update the size after removal
    ledgerSize --;
    
    return removed;
  }

}

//47382481, Hongting Su
//Put x inside [] below:
//[x] 	This assignment is entirely my own work and 
// 		I have not seen anyone else's code or design
package assignment3;

/*
 * class representing a bank transfert or payment 
 * 
 * is also a node in a Ledger Linked List
 * 
 * 
 */

public class Payment {

  public String fromPerson; // name of Person paying
  public String toPerson; // name of Person receiving payment
  public double amount; // amount payed
  public Timestamp timestamp; // unique time stamp (provided at the time of creation)
  public Payment next; // reference to next payment in appropriate Ledger Linked List

  /**
   * Basic empty constructor for a payment
   */

  public Payment() {
    // Provided - not to be changed
    this.fromPerson = null;
    this.toPerson = null;
    this.amount = 0;
    this.timestamp = null;
    this.next = null;
  }

  /**
   * Payment constructor with all necessary fields to be inserted in Ledger
   * 
   * @param fromPerson is the person paying
   * @param toPerson   is the person receiving the payment
   * @param amount     is the amount payed
   * @param timestamp  is time at creation of payment
   * @param next       is the next payment
   */

  public Payment(String fromPerson, String toPerson, double amount, Payment next) {
    // Provided - not to be changed
    this.fromPerson = fromPerson;
    this.toPerson = toPerson;
    this.amount = amount;
    this.timestamp = new Timestamp(); // time stamp is automatically set at creation
    this.next = next;
  }

  /**
   * A Payment is valid if it is done between registered persons provided in the
   * array persons
   * 
   * You may assume that the input array (String[] persons) will
   * not be null or empty.
   * 
   * @param persons - array of persons who can make and receive payment
   * 
   * @return true if the Payment is valid based on the criteria listed above,
   *         false otherwise
   */
  public boolean isValid(String[] persons) {

    boolean fromPersonChecked = false;
    boolean toPersonChecked = false;

    //check if from person exist
    for (int i=0; i<persons.length; i++) {
      if (this.fromPerson.equalsIgnoreCase(persons[i])) {
        fromPersonChecked = true;
        break;
      }
    }
    
    //check if to person exist
    for (int k=0; k<persons.length; k++) {
      if (this.toPerson.equalsIgnoreCase(persons[k])) {
        toPersonChecked = true;
        break;
      }
    }

    if (fromPersonChecked && toPersonChecked) {
      return true;
    }
    return false;
  }

}

//47382481, Hongting Su
//Put x inside [] below:
//[x] 	This assignment is entirely my own work and 
// 		I have not seen anyone else's code or design
package assignment3;

public class Blockchain {
  public Ledger head;
  public int curr_id;
  // add attributes as needed
  // needed to implement efficient code
  // tested for 'advanced' parts

  public int initial_id;

  public Ledger tail;
  
  /**
   * Default constructor
   * Create a new Blockchain which first ledger will start with the id = 0
   * 
   * You may modify this constructor if you need to (e.g.
   * if you want to initialise extra attributes in the class)
   */
  public Blockchain() {
    head = null;
    curr_id = 0;

    // add initialisation of added attributes, if needed
    
  }

  /**
   * Default constructor
   * 
   * @param id is an integer identity number
   *           Create a new Blockchain which first ledger will start with the
   *           given id
   * 
   *           You may modify this constructor if you need to (e.g.
   *           if you want to initialise extra attributes in the class)
   */
  public Blockchain(int id) {
    head = null;
    curr_id = id;
    // add initialisation of added attributes, if needed
   initial_id = id;
    
  }

/**
 * Check if BlockChain is empty 
 * 
 * @return True if the BlockChain is empty, false otherwise
 */
  public boolean isEmpty() {
    if (head == null) {
      return true;
    }
    return false;
  }

  /**
   * 
   * @return the number of Ledgers in the Blockchain
   */
  int size() {
    return curr_id - initial_id;
  }

  /**
   * Add a Ledger at the end of the Blockchain list
   * 
   * @param ledger
   */
  public void addLedger(Ledger ledger) {
    
    // do nothing if the parameter is null
    if (ledger == null) {
      return ;
    }

    Ledger temp = ledger;
    temp.next = null;

    // if BlockChain is empty, add to the head
    if (isEmpty()) {
                    
      head = temp;
      //temp.next = null;

      //set the first ledger id = 1
      temp.id = 1;

      //update the curr_id
      curr_id ++;

      //set the tail node
      tail = temp;

      //terminate method
      return ;
    }

    /* since ledgere1 is connected with itself (circular linked list)
     * the size() method is no longer work for this case
     * and the wile loop in size() will turn into a infinite loop
     * 
     * the expeced outcome for l1.next = l1 
     * the number of ledgers in the block chain will be 2
     * b/c there are two l1 linked (l1 linked to l1)
     * 
     * to break the infinite loop the possible solutions are as follow:
     * [1] make use of id to uniquely identify each ledger, once loop through
     *     a same id number, cease the loop
     * [2] instead of loop, use the tail_node.next to add new node
    */

    // the ledger to be added assign its id with the current_id + 1
    temp.id = curr_id + 1;

    //update the curr_id 
    curr_id ++;
    
    tail.next = temp;

    //update the tail
    tail = temp;
  }


  /**
   * Provide the net payment or profit made by the person in this Blockchain
   * 
   * @param person involved in payment
   * @return net balance paid or received
   * 
   *         return 0 if the blockchain is empty or the person not present in this
   *         blockchain
   */
  public double balance(String person) {
    if (isEmpty()) {
      return 0;
    }
    
    double balance = 0;
    Ledger current = head;
    
    for (int i=0; i<size(); i++) {
      balance += current.balance(person);
      current = current.next;
    }
    return balance;
  }

  /**
   * Count the number of transactions(i.e., payments) in all ledgers from the
   * blockchain
   * 
   * @return an integer as the total number of transactions/payments,
   *         zero if there are none.
   */
  public int transactionCount() {
    if (isEmpty()) {
      return 0;
    }

    int count = 0;
    Ledger current = head;

    while (current != null) {
      count += current.size();
      current = current.next;
    }
    return count;
  }

  /**
   * Constructor - creates a new Blockchain by combining two Blockchains
   * in order of their id
   * 
   * For this task, you need to write a constructor that creates a new
   * Blockchain object by combining two other Blockchain object,
   * b1 and b2. You must not make new ballots in the process
   * (i.e. you should transfer the Blockchain from both lists to the new one).
   * 
   * You can assume that b1 and list2 are valid Blockchains (that is,
   * all Blockchain objects in both lists are valid as defined in the assignment
   * specification), and that they are sorted according to the ledgers'ids in each
   * Blockchain's.
   * 
   * That is, the resulting Blockchain must be sorted according to the ledgers'
   * id.
   * 
   * 
   * If both b1 and b2 are empty, then construct an empty Blockchain.
   * Do the same if both lists are null. If only one of the lists are empty
   * or null, then you should return the other.
   * 
   * 
   * @param b1 - the first Blockchain
   * @param b2 - the second Blockchain
   */

  public Blockchain(Blockchain b1, Blockchain b2) {
    boolean b1_abandoned = b1 == null || b1.head == null;
    boolean b2_abandoned = b2 == null || b2.head == null;

    // construct empty blockchain
    if (b1_abandoned && b2_abandoned) {
      head = null;
      curr_id = 0;
      initial_id = 0;
      tail = null;
      return ;
    }
    //only add b2
    if (b1_abandoned) {
      addLedger(b2.head);
      return ;
    }
    //only add b1
    if (b2_abandoned) {
      addLedger(b1.head);
      return ;
    }
    // case when b1 is prioritised
    if (b1.head.id <= b2.head.id) {
      addLedger(b1.head);
      addLedger(b2.head);
    }
    else {
      addLedger(b2.head);
      addLedger(b1.head);
    }
    
  }

  /**
   * Constructor - creates a new Blockchain by combining all Blockchains in the
   * array of Blockchains in order of their id
   * 
   * That is, same as previous method for two blockchains
   * 
   * @param blocks is an array of valid blockchains.
   */
  public Blockchain(Blockchain[] blocks) {
    //
    // TODO - 8 marks + 5 marks for efficiency

    
  }

}

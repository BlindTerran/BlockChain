package assignment3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.util.*;
//import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Stream;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable; // new import
//import java.nio.file.Files;
//import java.nio.file.Path;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UnitTest {

  // these variables are used together with Graded.java to track the number
  // of passed tests and the score awarded
  public static int score = 0;
  public static String result = "";
  public static String currentMethodName = null;
  ArrayList<String> methodsPassed = new ArrayList<String>();

  // random seed (seed change should not affect the tests)
  public static int seed = 1010;
  // the size of medium and large test cases
  private static final int mLimit = 800;
  private static final int xLimit = 1_600_000;

  // the valid candidates in the payments
  private static final String[] candidates = { "a", "b", "c", "d", "e", "f", "g", "h" };

  /*
   * Here is the list of test cases that are used in the above unit tests.
   * Please note that some were constructed in the setUpBeforeClass() method
   */

  private static String[] testcase1;
  private static String[] testcase2;
  private static String[] testcase3;
  private static String[] testcase4;
  private static String[] testcase5;
  private static String[] testcase6;
  private static String[] testcase7;
  private static String[] testcase8;
  private static String[] testcase9;

  @SuppressWarnings("rawtypes")
  public static void checkArrayUsage() { // test only
    Class c = Blockchain.class;
    Method[] methods = c.getMethods();
    for (Method m : methods) {
      TypeVariable[] types = m.getTypeParameters();
      for (TypeVariable t : types) {
        System.out.println(m + ", " + t);
      }
    }
  }
 
  public static void readArray() throws IOException { // test only
    InputStream fs = UnitTest.class.getResourceAsStream("Blockchain.java");
    int data = fs.read();
    int count = 2;
    while (data != -1) {
      if (data == 91 && count > 2) {
        count++;
      }
    }
    fs.close();
  }

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    Random rgen = new Random(seed);
    // testcase1: only one candidate
    testcase1 = new String[mLimit];
    for (int i = 0; i < mLimit; i++) {
      testcase1[i] = candidates[2];
    }

    // testcase2: only two candidates, all votes at start (less than half)
    // goes to one
    testcase2 = new String[mLimit];
    for (int i = 0; i < (mLimit / 2) - 2; i++) {
      testcase2[i] = candidates[0];
    }
    for (int i = (mLimit / 2) - 2; i < mLimit; i++) {
      testcase2[i] = candidates[4];
    }

    // testcase3: no majority
    testcase3 = new String[mLimit];
    for (int i = 0; i < mLimit; i++) {
      int nextRandom = rgen.nextInt(100);
      if (nextRandom <= 8)
        testcase3[i] = candidates[0];
      else if (nextRandom <= 13)
        testcase3[i] = candidates[1];
      else if (nextRandom <= 35)
        testcase3[i] = candidates[2];
      else if (nextRandom <= 50)
        testcase3[i] = candidates[3];
      else if (nextRandom <= 54)
        testcase3[i] = candidates[4];
      else if (nextRandom <= 68)
        testcase3[i] = candidates[5];
      else if (nextRandom <= 86)
        testcase3[i] = candidates[6];
      else
        testcase3[i] = candidates[7];
    }

    // testcase4: last candidate is the majority
    testcase4 = new String[mLimit];
    for (int i = 0; i < mLimit; i++) {
      int nextRandom = rgen.nextInt(100);
      if (nextRandom <= 6)
        testcase4[i] = candidates[0];
      else if (nextRandom <= 10)
        testcase4[i] = candidates[1];
      else if (nextRandom <= 23)
        testcase4[i] = candidates[5];
      else if (nextRandom <= 36)
        testcase4[i] = candidates[6];
      else
        testcase4[i] = candidates[7];
    }

    // testcase5: list with majority
    testcase5 = new String[mLimit];
    for (int i = 0; i < mLimit; i++) {
      int nextRandom = rgen.nextInt(100);
      if (nextRandom <= 6)
        testcase5[i] = candidates[0];
      else if (nextRandom <= 11)
        testcase5[i] = candidates[1];
      else if (nextRandom <= 72)
        testcase5[i] = candidates[2];
      else if (nextRandom <= 78)
        testcase5[i] = candidates[3];
      else if (nextRandom <= 81)
        testcase5[i] = candidates[4];
      else if (nextRandom <= 92)
        testcase5[i] = candidates[5];
      else if (nextRandom <= 95)
        testcase5[i] = candidates[6];
      else
        testcase5[i] = candidates[7];
    }

    // testcase6: large case, no majority
    testcase6 = new String[xLimit];
    for (int i = 0; i < xLimit; i++) {
      int nextRandom = rgen.nextInt(100);
      if (nextRandom <= 17)
        testcase6[i] = candidates[0];
      else if (nextRandom <= 20)
        testcase6[i] = candidates[1];
      else if (nextRandom <= 32)
        testcase6[i] = candidates[2];
      else if (nextRandom <= 44)
        testcase6[i] = candidates[3];
      else if (nextRandom <= 50)
        testcase6[i] = candidates[4];
      else if (nextRandom <= 66)
        testcase6[i] = candidates[5];
      else if (nextRandom <= 78)
        testcase6[i] = candidates[6];
      else
        testcase6[i] = candidates[7];
    }

    // testcase7: large case, with majority
    testcase7 = new String[xLimit];
    for (int i = 0; i < xLimit; i++) {
      int nextRandom = rgen.nextInt(100);
      if (nextRandom <= 7)
        testcase7[i] = candidates[0];
      else if (nextRandom <= 12)
        testcase7[i] = candidates[1];
      else if (nextRandom <= 15)
        testcase7[i] = candidates[2];
      else if (nextRandom <= 20)
        testcase7[i] = candidates[3];
      else if (nextRandom <= 28)
        testcase7[i] = candidates[4];
      else if (nextRandom <= 32)
        testcase7[i] = candidates[5];
      else if (nextRandom <= 96)
        testcase7[i] = candidates[6];
      else
        testcase7[i] = candidates[7];
    }

    // testcase8: large case, majority at end
    testcase8 = new String[xLimit];
    for (int i = 0; i < xLimit; i++) {
      int nextRandom = rgen.nextInt(100);
      if (nextRandom <= 9)
        testcase8[i] = candidates[0];
      else if (nextRandom <= 24)
        testcase8[i] = candidates[2];
      else
        testcase8[i] = candidates[4];
    }

    // testcase9: large case, majority at start
    testcase9 = new String[xLimit];
    for (int i = 0; i < xLimit; i++) {
      int nextRandom = rgen.nextInt(100);
      if (nextRandom <= 72)
        testcase9[i] = candidates[0];
      else if (nextRandom <= 92)
        testcase9[i] = candidates[2];
      else
        testcase9[i] = candidates[4];
    }
  }

  @BeforeEach
  public void setUp() throws Exception {
    currentMethodName = null;
    Timestamp.reset();
  }

  @Test
  @Order(1)
  @Timeout(1)
  @Graded(description = "Payment.isValid()", marks = 5)
  public void testPaymentIsValid() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] registeredPeopleV1 = { "a", "b", "c", "d", "e", "f", "g" };
      String[] registeredPeopleV2 = { "Brian", "Thomas", "Elizabeth", "Sakura", "Lucy", "Tyson" };

      // Case 1: Payment from unregistered person

      Payment invalid1 = new Payment("t", "a", 10.0, null);
      assertFalse(invalid1.isValid(registeredPeopleV1));

      Payment invalid2 = new Payment("Gray", "Brian", 4.0, null);
      assertFalse(invalid2.isValid(registeredPeopleV2));

      // Case 2: Payment to unregistered person
      Payment invalid3 = new Payment("a", "t", 10.0, null);
      assertFalse(invalid3.isValid(registeredPeopleV1));

      Payment invalid4 = new Payment("Brian", "Gray", 4.0, null);
      assertFalse(invalid4.isValid(registeredPeopleV2));

      // Case 3: Unregistered people involved in the payment
      Payment invalid5 = new Payment("Sai", "Mei", 22.1, null);
      assertFalse(invalid5.isValid(registeredPeopleV2));

      // Case 4: Valid payment between registered people
      Payment valid1 = new Payment("a", "b", 100.0, null);
      assertTrue(valid1.isValid(registeredPeopleV1));

      Payment valid2 = new Payment("Sakura", "Thomas", 20.0, null);
      assertTrue(valid2.isValid(registeredPeopleV2));

      Payment validCase = new Payment("sakura", "thomas", 220, null);
      assertTrue(validCase.isValid(registeredPeopleV2)); // The names should not be case-sensitive

      // Case 5: Payment whose next is NOT null (To ensure it only checks current
      // payment and NOT the next one)
      Payment valid3 = new Payment("Lucy", "Thomas", 20.0, valid2);
      assertTrue(valid3.isValid(registeredPeopleV2));

      Payment valid4 = new Payment("Lucy", "Thomas", 20.0, invalid2); // doesn't matter even if next payment is invalid
      assertTrue(valid4.isValid(registeredPeopleV2));

      Payment invalid6 = new Payment("t", "a", 10.0, valid1); // doesn't matter even if next payment is valid
      assertFalse(invalid6.isValid(registeredPeopleV1));

      Payment invalid7 = new Payment("t", "a", 10.0, invalid1);
      assertFalse(invalid7.isValid(registeredPeopleV1));

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @Test
  @Order(1)
  @Timeout(1)
  @Graded(description = "Ledger.size()", marks = 5)
  public void testLedgerSize() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      // String[] registeredPeople = {"Eren", "Levi", "Mikasa", "Armin", "Erwin",
      // "Jean", "Annie", "Reiner", "Hanji", "Sasha"};

      // Case 1: empty Ledger
      Ledger empty = new Ledger();
      assertEquals(0, empty.size());

      // Case 2: Non empty Ledger size computed accurately
      Ledger nonEmpty = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        nonEmpty.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      assertEquals(toPerson.length, nonEmpty.size());

      // Case 3: Ledger with one Payment only
      Ledger singlePayment = new Ledger();
      singlePayment.addPayment(new Payment("Eren", "Levi", 100, null));

      assertEquals(1, singlePayment.size());

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @SuppressWarnings("unused")
  @Test
  @Order(1)
  @Timeout(1)
  @Graded(description = "Ledger.AddPayment()", marks = 5)
  public void testLedgerAddPaymentPayment() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      String[] registeredPeople = { "Eren", "Levi", "Mikasa", "Armin", "Erwin", "Jean", "Annie", "Reiner", "Hanji",
          "Sasha" };

      // Case 1: empty Ledger size is 0
      Ledger empty = new Ledger();
      assertEquals(0, empty.size());
      assertNull(empty.head);

      // Case 2: Single Item ledger created accurately
      Ledger singlePayment = new Ledger();
      Payment p1 = new Payment("Eren", "Levi", 100, null);
      singlePayment.addPayment(p1);
      assertNotNull(singlePayment.head);
      assertEquals("Eren", singlePayment.head.fromPerson);
      assertEquals("Levi", singlePayment.head.toPerson);
      assertTrue(singlePayment.head == p1); // ensuring they point to the same instance

      Ledger singlePaymentV2 = new Ledger();
      Payment p2 = new Payment("Mikasa", "Hanji", 120, p1);
      singlePaymentV2.addPayment(p2);

      assertEquals(1, singlePaymentV2.size()); // must not add the entire Payment chain and ONLY single payment
      assertEquals(p2, singlePaymentV2.head);
      assertEquals("Mikasa", singlePaymentV2.head.fromPerson);
      assertEquals("Hanji", singlePaymentV2.head.toPerson);

      // Case 3: More than one item added to the Ledger accurately
      Ledger nonEmpty = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        nonEmpty.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      // ensuring null and non null checks
      assertNull(nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNotNull(nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNotNull(nonEmpty.head);

      // ensuring from and two person alongside the payment is accurate for each
      // payment object.
      assertEquals(fromPerson[fromPerson.length - 1], nonEmpty.head.fromPerson);
      assertEquals(fromPerson[fromPerson.length - 2], nonEmpty.head.next.fromPerson);
      assertEquals(fromPerson[fromPerson.length - 3], nonEmpty.head.next.next.fromPerson);
      assertEquals(fromPerson[0],
          nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(toPerson[0],
          nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.toPerson);
      assertEquals(fromPerson[3], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(toPerson[4], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.toPerson);
      assertEquals(payment[4], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.amount);
      assertEquals(toPerson.length, nonEmpty.size());

      // case 3: Payment is null
      // do nothing if PaymentToReverse is null

      int oldSize = nonEmpty.size();
      nonEmpty.addPayment(null);
      assertEquals(oldSize, nonEmpty.size());
    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @Test
  @Order(4)
  @Timeout(1)
  @Graded(description = "Ledger.isValid()", marks = 5)
  public void testLedgerIsValid() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Eren", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      String[] registeredPeople = { "Eren", "Levi", "Mikasa", "Armin", "Erwin", "Jean", "Annie", "Reiner", "Hanji",
          "Sasha" };

      // Case 1: empty Ledger
      Ledger empty = new Ledger();
      assertTrue(empty.isValid(registeredPeople));

      // For the next test cases, you must pass the addPayment method first before
      // attempting this question.

      // Case 2: Single Item ledger
      Ledger singlePayment = new Ledger();
      Payment p1 = new Payment("Eren", "Levi", 100, null);
      singlePayment.addPayment(p1);
      assertTrue(singlePayment.isValid(registeredPeople));

      Ledger singlePaymentV2 = new Ledger();
      Payment p2 = new Payment("Mikasa", "Sasuke", 120, p1);
      singlePaymentV2.addPayment(p2);
      assertFalse(singlePaymentV2.isValid(registeredPeople));

      // Case 3: More than one item added to the Ledger accurately
      Ledger nonEmpty = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        nonEmpty.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      // ensuring null and non null checks
      assertNull(nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNotNull(nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNotNull(nonEmpty.head);

      // ensuring from and two person alongside the payment is accurate for each
      // payment object.
      assertEquals(fromPerson[fromPerson.length - 1], nonEmpty.head.fromPerson);
      assertEquals(fromPerson[fromPerson.length - 2], nonEmpty.head.next.fromPerson);
      assertEquals(fromPerson[fromPerson.length - 3], nonEmpty.head.next.next.fromPerson);
      assertEquals(fromPerson[0],
          nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(toPerson[0],
          nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.toPerson);
      assertEquals(fromPerson[3], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(toPerson[4], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.toPerson);
      assertEquals(payment[4], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.amount);
      assertEquals(toPerson.length, nonEmpty.size());

      assertFalse(nonEmpty.isValid(registeredPeople));

      Ledger nonEmptyValid = new Ledger();
      nonEmptyValid.addPayment(new Payment(fromPerson[0], toPerson[0], payment[0], null));
      nonEmptyValid.addPayment(new Payment(fromPerson[6], toPerson[4], payment[8], null));
      nonEmptyValid.addPayment(new Payment(fromPerson[5], toPerson[2], payment[10], null));
      nonEmptyValid.addPayment(new Payment(fromPerson[9], toPerson[0], payment[9], null));
      nonEmptyValid.addPayment(new Payment(fromPerson[1], toPerson[7], payment[1], null));

      assertTrue(nonEmptyValid.isValid(registeredPeople));

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @Test
  @Order(5)
  @Timeout(1)
  @Graded(description = "Ledger.ContainsString()", marks = 5)
  public void testLedgerContainsString() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Eren", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      String[] registeredPeople = { "Eren", "Levi", "Mikasa", "Armin", "Erwin", "Jean", "Annie", "Reiner", "Hanji",
          "Sasha" };

      // Case 1: empty Ledger
      Ledger empty = new Ledger();
      assertFalse(empty.contains(registeredPeople[0])); // empty ledger contains no person

      // For the next test cases, you must pass the addPayment method first before
      // attempting this question.

      // Case 2: Single Item ledger
      Ledger singlePayment = new Ledger();
      Payment p1 = new Payment("Eren", "Levi", 100, null);
      singlePayment.addPayment(p1);
      assertTrue(singlePayment.contains(registeredPeople[0]));

      Ledger singlePaymentV2 = new Ledger();
      Payment p2 = new Payment("Mikasa", "Sasuke", 120, p1);
      singlePaymentV2.addPayment(p2);
      assertFalse(singlePaymentV2.contains(registeredPeople[3]));

      // Case 3: n items in ledger
      Ledger nonEmpty = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        nonEmpty.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      // ensuring null and non null checks
      assertNull(nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNotNull(nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNotNull(nonEmpty.head);

      // ensuring from and two person alongside the payment is accurate for each
      // payment object.
      assertEquals(fromPerson[fromPerson.length - 1], nonEmpty.head.fromPerson);
      assertEquals(fromPerson[fromPerson.length - 2], nonEmpty.head.next.fromPerson);
      assertEquals(fromPerson[fromPerson.length - 3], nonEmpty.head.next.next.fromPerson);
      assertEquals(fromPerson[0],
          nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(toPerson[0],
          nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.toPerson);
      assertEquals(fromPerson[3], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(toPerson[4], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.toPerson);
      assertEquals(payment[4], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.amount);
      assertEquals(toPerson.length, nonEmpty.size());

      for (int i = 0; i < fromPerson.length; i++) {
        assertTrue(nonEmpty.contains(fromPerson[i].toUpperCase())); // to ensure names are NOT case sensitive
        assertTrue(nonEmpty.contains(toPerson[i]));
      }

      for (int i = 0; i < registeredPeople.length; i++) {
        boolean result = true;
        if (i >= 6) {
          result = false;
        }
        assertEquals(result, nonEmpty.contains(registeredPeople[i].toUpperCase())); // to ensure names are NOT case
                                                                                    // sensitive

      }

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @Test
  @Order(6)
  @Timeout(1)
  @Graded(description = "Ledger.BalanceString()", marks = 8)
  public void testLedgerBalanceString() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Eren", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      String[] registeredPeople = { "Eren", "Levi", "Mikasa", "Armin", "Erwin", "Jean", "Annie", "Reiner", "Hanji",
          "Sasha" };

      // Case 1: empty Ledger
      Ledger empty = new Ledger();
      assertEquals(0, empty.balance("Eren")); // empty ledger contains no person

      // For the next test cases, you must pass the addPayment method first before
      // attempting this question.

      // Case 2: Single Item ledger
      Ledger singlePayment = new Ledger();
      Payment p1 = new Payment("Eren", "Levi", 100, null);
      singlePayment.addPayment(p1);

      assertEquals(100, singlePayment.balance("levi"), 0.01); // not case sensitive
      assertEquals(-100, singlePayment.balance("eren"), 0.01);

      singlePayment.addPayment(new Payment("Levi", "Eren", 20, null));
      singlePayment.addPayment(new Payment("Eren", "Mikasa", 22.25, null));

      // More payment involving Eren
      assertEquals(-102.25, singlePayment.balance("eren"), 0.01);

      Ledger singlePaymentV2 = new Ledger();
      Payment p2 = new Payment("Mikasa", "Sasuke", 12000, p1);

      singlePaymentV2.addPayment(p2);

      assertEquals(12000, singlePaymentV2.balance("Sasuke"));

      // Case 3: n items in ledger
      Ledger nonEmpty = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        nonEmpty.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      double[] balance = new double[] { 19.99, -90.0, -79.1, 70.0, 0.0 };
      for (int i = 0; i < balance.length; i++) {
        assertEquals(balance[i], nonEmpty.balance(registeredPeople[i]), 0.01);

      }

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @Test
  @Order(7)
  @Timeout(1)
  @Graded(description = "Ledger.ReversePaymentPayment()", marks = 5)
  public void testReversePaymentPayment() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Eren", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      // String[] registeredPeople = {"Eren", "Levi", "Mikasa", "Armin", "Erwin",
      // "Jean", "Annie", "Reiner", "Hanji", "Sasha"};

      // Case 1: add item to empty Ledger
      Ledger empty = new Ledger();
      Payment p1ToReverse = new Payment("Eren", "Levi", 100, null);
      empty.reversePayment(p1ToReverse);

      assertEquals("Levi", empty.head.fromPerson);
      assertEquals("Eren", empty.head.toPerson);
      assertEquals(100, empty.head.amount); // amount remains unchanged

      // For the next test cases, you must pass the addPayment method first before
      // attempting this question.

      // Case 2: n items in ledger
      Ledger nonEmpty = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        nonEmpty.reversePayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      // ensuring null and non null checks
      assertNull(nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNotNull(nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNotNull(nonEmpty.head);

      // ensuring from and two person alongside the payment is accurate for each
      // payment object.
      assertEquals(fromPerson[fromPerson.length - 1], nonEmpty.head.toPerson);
      assertEquals(fromPerson[fromPerson.length - 2], nonEmpty.head.next.toPerson);
      assertEquals(fromPerson[fromPerson.length - 3], nonEmpty.head.next.next.toPerson);
      assertEquals(toPerson[toPerson.length - 3], nonEmpty.head.next.next.fromPerson);
      assertEquals(toPerson[toPerson.length - 2], nonEmpty.head.next.fromPerson);
      assertEquals(fromPerson[0],
          nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.toPerson);
      assertEquals(toPerson[0],
          nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(fromPerson[3], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.toPerson);
      assertEquals(toPerson[4], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(payment[4], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.amount);
      assertEquals(toPerson.length, nonEmpty.size());

      // case 3: PaymentToReverse is null
      // do nothing if PaymentToReverse is null

      int oldSize = nonEmpty.size();
      nonEmpty.reversePayment(null);
      assertEquals(oldSize, nonEmpty.size());

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @SuppressWarnings("unused")
  @Test
  @Order(8)
  @Timeout(1)
  @Graded(description = "Ledger.Remove()", marks = 5)
  public void testRemove() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Eren", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      String[] registeredPeople = { "Eren", "Levi", "Mikasa", "Armin", "Erwin", "Jean", "Annie", "Reiner", "Hanji",
          "Sasha" };

      // Case 1: empty Ledger
      Ledger empty = new Ledger();
      assertNull(empty.head);
      assertNull(empty.remove());
      assertEquals(0, empty.size(), 0.01);

      // For the next test cases, you must pass the addPayment method first before
      // attempting this question.

      // Case 2: Single Item ledger
      Ledger singlePayment = new Ledger();
      Payment p1 = new Payment("Eren", "Levi", 100, null);
      singlePayment.addPayment(p1);
      assertEquals(1, singlePayment.size()); // size 1 before removal
      assertEquals(p1, singlePayment.remove());
      assertNull(singlePayment.head);
      assertEquals(0, singlePayment.size()); // size 0 after removal

      Ledger singlePaymentV2 = new Ledger();
      Payment p2 = new Payment("Mikasa", "Sasuke", 120, p1);
      singlePaymentV2.addPayment(p2);
      assertEquals(p2, singlePaymentV2.remove());
      assertNull(singlePaymentV2.head);

      // Case 3: n items in ledger
      Ledger nonEmpty = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        nonEmpty.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      // ensuring null and non null checks
      assertNull(nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNotNull(nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNotNull(nonEmpty.head);

      // ensuring from and two person alongside the payment is accurate for each
      // payment object.
      assertEquals(fromPerson[fromPerson.length - 1], nonEmpty.head.fromPerson);
      assertEquals(fromPerson[fromPerson.length - 2], nonEmpty.head.next.fromPerson);
      assertEquals(fromPerson[fromPerson.length - 3], nonEmpty.head.next.next.fromPerson);
      assertEquals(fromPerson[0],
          nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(toPerson[0],
          nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.toPerson);
      assertEquals(fromPerson[3], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(toPerson[4], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.toPerson);
      assertEquals(payment[4], nonEmpty.head.next.next.next.next.next.next.next.next.next.next.amount);
      assertEquals(toPerson.length, nonEmpty.size());

      for (int i = fromPerson.length - 1; i >= 0; i--) {
        Payment p = nonEmpty.remove();
        assertEquals(fromPerson[i], p.fromPerson);
        assertEquals(toPerson[i], p.toPerson);
        assertEquals(payment[i], p.amount);
        assertEquals(i, nonEmpty.size());
      }

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @SuppressWarnings("unused")
  @Test
  @Order(9)
  @Timeout(1)
  @Graded(description = "Blockchain.size()", marks = 5)
  public void testBlockchainSize() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      // String[] registeredPeople = {"Eren", "Levi", "Mikasa", "Armin", "Erwin",
      // "Jean", "Annie", "Reiner", "Hanji", "Sasha"};

      // Case 0: Null Ledger
      Blockchain b0 = new Blockchain();

      // Case 1: empty Blockchain
      Blockchain b1 = new Blockchain();
      assertEquals(0, b1.size());

      // Case 2: Non empty Ledger size computed accurately
      Ledger L1 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        L1.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger L2 = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        L2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger L3 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        L3.addPayment(new Payment(toPerson[i], fromPerson[i], payment[i], null));
      }

      int currSize = 0;
      b1.addLedger(L1);
      b1.addLedger(L2);
      b1.addLedger(L3);
      currSize += 3;
      assertEquals(currSize, b1.size());

      // Case 3: Null Ledger added. Size remains unchanged
      b1.addLedger(null);
      assertEquals(currSize, b1.size());

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @Test
  @Order(10)
  @Timeout(1)
  @Graded(description = "Blockchain.addLedger()", marks = 5)
  public void testBlockchainAddLedger() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      String[] notRegistered = { "Ricciardo", "Vettel", "Prost", "Alonso", "Hamilton", "Verstappen", "Hill",
          "Senna", "Stewart", "Lauda", "Clark", "Moss", "Mansell", "Button", "Raikkonen" };

      // Case 1: empty Blockchain
      Blockchain b1 = new Blockchain();
      assertNull(b1.head);
      assertEquals(0, b1.curr_id);

      // Case 2: Adding a null Ledger to the blockchain
      b1.addLedger(null);
      assertEquals(0, b1.curr_id); // remains 0 as the item cannot be added
      assertNull(b1.head);
      assertEquals(0, b1.size());

      // Case 3: Adding a proper ledger
      Ledger l1 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        l1.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      b1.addLedger(l1);
      assertEquals(l1, b1.head);
      assertEquals(1, b1.curr_id);
      assertEquals(1, b1.size());

      // Case 4: Ledger with next which is NOT null
      l1.next = l1; // be careful, this may cause an infinite loop

      b1.addLedger(l1);
      assertEquals(2, b1.curr_id); // Must not be any number above 2
      assertEquals(l1, b1.head.next);


      // Case 5: More ledgers added to new Blockchain

      Blockchain b2 = new Blockchain(99);

      Ledger l2 = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        l2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger l0 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        l0.addPayment(new Payment(toPerson[i], fromPerson[i], payment[i], null));
      }

      b2.addLedger(l1);
      b2.addLedger(l2);
      b2.addLedger(l0);

      assertEquals(102, b2.curr_id);
      assertEquals(l1, b2.head);
      assertEquals(l2, b2.head.next);
      assertNotNull(b2.head.next.next);

      Blockchain b3 = new Blockchain(50); // smaller Blockchina

      Ledger l3 = new Ledger();
      for (int i = 0; i < b3.size(); i++) {
        l3.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger l4 = new Ledger();
      for (int i = b3.size() - 1; i >= 0; i--) {
        l4.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger l5 = new Ledger();
      for (int i = 0; i < b3.size(); i++) {
        l5.addPayment(new Payment(notRegistered[i], toPerson[i], payment[i], null));
      }

      b3.addLedger(l3);
      b3.addLedger(l4);
      b3.addLedger(l5);

      assertEquals(53, b3.curr_id);
      assertEquals(l3, b3.head);
      assertEquals(l4, b3.head.next);
      assertNotNull(b3.head.next.next);

      // Case 2: Non empty Ledger size computed accurately

      Blockchain b4 = new Blockchain();

      Ledger L1 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        L1.addPayment(new Payment(fromPerson[i], notRegistered[i], payment[i], null));
      }

      Ledger L2 = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        L2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      int currSize = 0;
      b4.addLedger(L1);
      b4.addLedger(L2);
      currSize += 2;
      assertEquals(currSize, b4.size()); // LogChange v2 changed b1 to b4

      // Case 3: Null Ledger added. Size remains unchanged
    //  assertEquals(2, b1.size());   // size of 3
      b1.addLedger(null);
      assertEquals(currSize, b1.size());

      // Case 4:
      int rnd = (int) (Math.random() * 10);
      for (int i = 0; i <= rnd; i++) {
        b1.addLedger(new Ledger());
        currSize++;
      }
      assertEquals(currSize, b1.size());

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

//  @Test
//  @Order(11)
//  @Timeout(1)
//  @Graded(description = "Blockchain.isValid()", marks = 0)
//  public void testBlockchainIsValid() {
//
//    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
//
//      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
//          "Thomas",
//          "Sasuke", "Ichigo", "Luffy", "Scarlet"
//      };
//
//      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
//          "Naruto", "Rukia", "Ichigo", "Thomas"
//      };
//
//      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
//          400 };
//
//      double[] sortedPayment = { 0, 21.00, 24.95, 29.90, 29.99, 30.00, 34.49, 35.00, 50.00, 55.50, 99.90, 99.95, 100.00,
//          100.10, 400 };
//
//      String[] registeredPeople = { "Eren", "Levi", "Mikasa", "Armin", "Erwin", "Jean", "Annie", "Reiner", "Hanji",
//          "Sasha" };
//
//      String[] notRegistered = { "Ricciardo", "Vettel", "Prost", "Alonso", "Hamilton", "Verstappen", "Hill",
//          "Senna", "Stewart", "Lauda", "Clark", "Moss", "Mansell", "Button", "Raikkonen" };
//
//      Blockchain b1 = new Blockchain();
//      Blockchain b2 = new Blockchain();
//      Blockchain b3 = new Blockchain();
//
//      Ledger l1 = new Ledger();
//      for (int i = 0; i < fromPerson.length; i++) {
//        l1.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
//      }
//
//      Ledger l2 = new Ledger();
//      for (int i = fromPerson.length - 1; i >= 0; i--) {
//        l2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
//      }
//
//      Ledger single = new Ledger();
//      single.addPayment(new Payment("a", "c", payment[payment.length - 1], null));
//
//      // Case 1: String[] empty person array
//      b1.addLedger(single);
////      assertFalse(b1.isValid(new String[] {}));
//
//      // Case 2:
//      b1.addLedger(l1);
//      b1.addLedger(l2);
//      assertTrue(b1.isValid(registeredPeople));
//      assertFalse(b1.isValid(fromPerson));
//
////      
////      Ledger l3 = new Ledger();
////      for(int i=0; i < fromPerson.length; i++) {
////        l3.addPayment(new Payment(toPerson[i], fromPerson[i],payment[(int)(Math.random()*fromPerson.length-1)], null));
////      }
//
//      // Invalid
//
//      Ledger l4 = new Ledger();
//      for (int i = fromPerson.length - 1; i >= 0; i--) {
//        l4.addPayment(
//            new Payment(notRegistered[i], toPerson[i], payment[(int) (Math.random() * fromPerson.length - 1)], null));
//      }
//
//      Ledger l5 = new Ledger();
//      for (int i = 0; i < fromPerson.length; i++) {
//        int idx = new Random().nextInt(fromPerson.length - 1);
//        l5.addPayment(new Payment(fromPerson[idx], notRegistered[idx], payment[idx], null));
//      }
//
//      // Case 1: String[] empty person array
//      assertFalse(b1.isValid(new String[] {}));
//
////    Ledger l2 = new Ledger();
////    for (int i = fromPerson.length - 1; i >= 0; i--) {
////      l2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
////    }
////
////    Ledger l3 = new Ledger();
////    for (int i = 0; i < fromPerson.length; i++) {
////      l3.addPayment(
////          new Payment(toPerson[i], fromPerson[i], payment[(int) (Math.random() * fromPerson.length - 1)], null));
////    }
//
//      // Case 3:
////    b2.addLedger(l2);
////    assertTrue(b1.isValid(registeredPeople));
////    assertTrue(b1.isValid(registeredPeople));
////    
////    b2.addLedger(l3);
////    assertTrue(b2.isValid(registeredPeople));
////    assertTrue(b2.isValid(registeredPeople));
//
//    });
//    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
//  }

  @Test
  @Order(12)
  @Timeout(1)
  @Graded(description = "Blockchain.balance()", marks = 8)
  public void testBlockchainBalance() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      double[] b = { 60.010000000000005, -79.1, 79.1, 0.0, 70.0, -70.0, -70.01, 60.010000000000005, 4.490000000000002,
          0.0, 365.0, -4.490000000000002, -99.95, 49.95, -365.0 };

      String[] registeredPeople = { "Eren", "Levi", "Mikasa", "Armin", "Erwin", "Jean", "Annie", "Reiner", "Hanji",
          "Sasha" };

      double[] rb = { -20.0, -120.02000000000001, -158.2, 140.0, 0.0, -140.0, 0.0, 0.0, 0.0, 0.0 };

      String[] notRegistered = { "Ricciardo", "Vettel", "Prost", "Alonso", "Hamilton", "Verstappen", "Hill",
          "Senna", "Stewart", "Lauda", "Clark", "Moss", "Mansell", "Button", "Raikkonen" };

      Blockchain b1 = new Blockchain();
      Blockchain b2 = new Blockchain();
      Blockchain b3 = new Blockchain();

      Ledger l1 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        l1.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger l2 = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        l2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      // Case 1:
      Blockchain bEmpty = new Blockchain();

      assertEquals(0, b1.balance(null)); // null string
      assertEquals(0, b1.balance("")); // empty string
      assertEquals(0, bEmpty.balance("empty")); // empty blockchain

      // Case 2:
      b1.addLedger(l1);
      assertEquals(-79.1, b1.balance("Mikasa"));
      assertEquals(0, b2.balance("c"));
      assertEquals(4.490000000000002, b1.balance("Naruto"));
      assertEquals(-10.0, b1.balance(b1.head.head.fromPerson));
      assertEquals(70.01, b1.balance(b1.head.head.next.next.next.next.next.next.next.fromPerson));
      assertEquals(-365.0, b1.balance(b1.head.tail.fromPerson));

      b2.addLedger(l2);
      assertEquals(-365.0, b2.balance(b2.head.head.fromPerson));
      assertEquals(-4.490000000000002, b2.balance(b2.head.head.next.next.next.fromPerson));
      assertEquals(0.0, b2.balance(b2.head.head.next.next.next.next.next.fromPerson));
      assertEquals(-10.0, b2.balance(b2.head.tail.fromPerson));

      // Case 3:
      Ledger l3 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        l3.addPayment(new Payment(toPerson[i], fromPerson[i], payment[i], null));
      }

      b3.addLedger(l3);
      assertEquals(-365, b3.balance(toPerson[toPerson.length - 1]));

      for (int i = toPerson.length - 1; i >= 0; i--) {
        assertEquals(b[i], b3.balance(toPerson[i]));
        assertEquals(0, b3.balance(notRegistered[i]));
      }

      assertEquals(70.0, b3.balance(b3.head.head.next.next.next.next.fromPerson));
      assertEquals(60.010000000000005, b3.balance(b3.tail.head.fromPerson));
      assertEquals(0.0, b3.balance(b3.head.head.next.next.next.toPerson));
      assertNotNull(b3.head.head.next.next.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertNull(b3.head.head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next);
      assertNull(b3.head.tail.next);
      assertNotNull(b3.tail.head.next);

      // Case 4;
      b1.addLedger(l2);

      for (int i = 0; i < registeredPeople.length; i++) {
        assertEquals(rb[i], b1.balance(registeredPeople[i]));
      }

      assertEquals(-158.2, b1.balance(b1.head.head.next.fromPerson));
      assertEquals(-120.02000000000001, b1.balance(b2.head.tail.toPerson));
      assertEquals(-730.0, b1.balance(b1.head.tail.fromPerson));

      b1.addLedger(l3);
      assertEquals(-365.0, b1.balance(b1.head.tail.fromPerson));
      assertEquals(365.0, b1.balance(b1.head.tail.toPerson));
      assertEquals(-60.010000000000005, b1.balance(b2.head.tail.toPerson));
      assertEquals(-10.0, b1.balance(b2.head.tail.fromPerson));

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @Test
  @Order(13)
  @Timeout(1)
  @Graded(description = "Blockchain.transactionCount()", marks = 8)
  public void testBlockchainTransactionCount() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 }; //15 payments

      Blockchain b1 = new Blockchain();
      Blockchain b2 = new Blockchain();
      Blockchain b3 = new Blockchain();

      Ledger l1 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        l1.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null)); //size 15 for each ledger
      }

      Ledger l2 = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        l2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger l3 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        l3.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
        l3.addPayment(new Payment(toPerson[i], fromPerson[i], payment[i], null));
      }

      // Case 1;

      assertEquals(0, b1.transactionCount());
      b1.addLedger(new Ledger());
      assertEquals(0, b1.transactionCount());

      b1.addLedger(l1);
      assertEquals(l1.size(), b1.transactionCount());
      b1.addLedger(l2);
      assertEquals(l1.size() + l2.size(), b1.transactionCount());
      b1.addLedger(l3);
      assertEquals(l1.size() + l2.size() + l3.size(), b1.transactionCount());
      b1.addLedger(new Ledger());
      assertEquals(l1.size() + l2.size() + l3.size(), b1.transactionCount());
      l3.addPayment(null);
      b1.addLedger(l3);
      assertEquals(60, b1.transactionCount());

      // Case 2:

      b2.addLedger(l1);
      b2.addLedger(l2);

      for (int i = l3.size() / 2; i < l3.size() - 1; i++) {
        l3.remove();
      }
      b2.addLedger(l3);
      assertEquals(l1.size() + l2.size() + l3.size(), b2.transactionCount());

      for (int i = 0; i < l2.size() / 2; i++) {
        l2.remove();
      }
      b2.addLedger(l2);
      assertEquals(25, b2.transactionCount());

      Ledger single = new Ledger();
      single.addPayment(new Payment("a", "c", payment[payment.length - 1], null));
      b2.addLedger(single);
      assertEquals(25 + single.size(), b2.transactionCount());

      // Case 3:
      Ledger l4 = new Ledger();
      String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
      for (int i = 0; i < alphabet.length; i++) {
        int idx = (int) Math.random() * payment.length;
        l4.addPayment(new Payment(alphabet[i], alphabet[alphabet.length - 1 - i], payment[idx], null));
      }

      b3.addLedger(l4);
      assertEquals(alphabet.length, b3.transactionCount());

      Ledger l5 = new Ledger();
      for (int i = 0; i < alphabet.length; i++) {
        int idx = (int) Math.random() * payment.length;
        l5.addPayment(new Payment(alphabet[alphabet.length - 1 - i], alphabet[i], payment[idx], null));
      }
      b3.addLedger(l5);
      assertEquals(alphabet.length * 2, b3.transactionCount());

      b3.addLedger(l1);
      b3.addLedger(l2);
      b3.addLedger(l3);
      b3.addLedger(l4);
      b3.addLedger(l5);
      assertEquals(52, b3.transactionCount());

      Blockchain b5 = new Blockchain();
      for (int i = 0; i < 15; i++) {
        Ledger t = new Ledger();
        t.addPayment(
            new Payment(fromPerson[fromPerson.length - i - 1], toPerson[i], payment[payment.length - i - 1], null));
        b5.addLedger(t);
      }
      assertEquals(toPerson.length, b5.transactionCount());

    });
    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @Test
  @Order(14)
  @Timeout(1)
  @Graded(description = "Blockchain.blockchainBlockchain", marks = 8)
  public void testBlockchainBlockchainBlockchain() {

    String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
        "Thomas",
        "Sasuke", "Ichigo", "Luffy", "Scarlet"
    };

    String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
        "Naruto", "Rukia", "Ichigo", "Thomas"
    };

    double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
        400 };

    Blockchain b1 = new Blockchain();
    Blockchain b2 = new Blockchain();

    assertNotNull(new Blockchain(b1, null));
    assertNotNull(new Blockchain(null, null));
    assertNotNull(new Blockchain(b1, b2));

//    assertEquals(b1, new Blockchain(b1, new Blockchain()));
//    assertEquals(b2, new Blockchain(b2, new Blockchain()));
//    assertEquals(b1, new Blockchain(null, b1));
//    assertEquals(b2, new Blockchain(null, b2));
//    assertEquals(b2, new Blockchain(b2, new Blockchain()));
//    assertEquals(b2, new Blockchain(new Blockchain(), b2));
//    assertEquals(b1, new Blockchain(new Blockchain(), b1));

    Ledger l1 = new Ledger();
    for (int i = 0; i < fromPerson.length; i++) {
      l1.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
    }

    Ledger l2 = new Ledger();
    for (int i = fromPerson.length - 1; i >= 0; i--) {
      l2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
    }

    b1.addLedger(l1);
    b2.addLedger(l2);

    Blockchain b3 = new Blockchain(b1, b2);

    assertEquals(b1.head.head.fromPerson, b3.head.head.fromPerson);
    assertEquals(b1.head.tail.fromPerson, b3.head.tail.fromPerson);
    assertNull(b3.head.tail.next);
    assertEquals(b1.head.head.next.fromPerson, b3.head.head.next.fromPerson);
    assertEquals(b2.head.head.next.next.next.next.next.next.next.next.next.next.next.fromPerson,
        b3.head.head.next.next.next.fromPerson);
    assertEquals(b1.head.head.next.next.fromPerson, b3.head.head.next.next.fromPerson);
    assertEquals(b2.head.head.fromPerson, b3.head.tail.fromPerson);

    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @Test
  @Order(15)
  @Timeout(1)
  @Graded(description = "Blockchain.blockchainBlockchainFast", marks = 7)
  public void testBlockchainBlockchainBlockchainFast() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      Ledger l4 = new Ledger();
      String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
      for (int i = 0; i < alphabet.length; i++) {
        int idx = (int) Math.random() * payment.length;
        l4.addPayment(new Payment(alphabet[i], alphabet[alphabet.length - 1 - i], payment[idx], null));
      }

      Ledger l5 = new Ledger();
      for (int i = 0; i < alphabet.length; i++) {
        int idx = (int) Math.random() * payment.length;
        l5.addPayment(new Payment(alphabet[alphabet.length - 1 - i], alphabet[i], payment[idx], null));
      }

      Blockchain b4 = new Blockchain();
      Blockchain b5 = new Blockchain();
      b4.addLedger(l4);
      b5.addLedger(l5);

      Blockchain b6 = new Blockchain(b4, b5);
      Payment temp = b6.head.head;

      for (int i = 0; i < alphabet.length; i++) {
        if (temp != null) {
          assertEquals(alphabet[i], temp.fromPerson);
          assertEquals(alphabet[alphabet.length - i - 1], temp.toPerson);
          temp = temp.next;
        }
      }

      Ledger l1 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        l1.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger l2 = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        l2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger l3 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        l3.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
        l3.addPayment(new Payment(toPerson[i], fromPerson[i], payment[i], null));
      }

      Blockchain b7 = new Blockchain();
      Blockchain b8 = new Blockchain();

      b7.addLedger(l1);
      b8.addLedger(l2);

      Blockchain b9 = new Blockchain(b8, b7);

      assertEquals(b7.head.tail.fromPerson, b9.head.head.fromPerson);
      assertEquals(b8.head.tail.fromPerson, b9.head.tail.fromPerson);
      assertEquals(b8.head.head.next.next.next.next.next.next.next.next.next.next.next.next.fromPerson,
          b9.head.head.next.next.next.next.next.next.next.next.next.next.next.next.fromPerson);
      assertEquals(b7.head.head.next.next.next.next.next.next.next.next.next.next.fromPerson,
          b9.head.head.next.next.next.next.fromPerson);
      assertEquals(b7.head.head.next.next.next.next.next.next.fromPerson, b9.head.tail.toPerson);

    });

    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @SuppressWarnings("unused")
  @Test
  @Order(16)
  @Timeout(1)
  @Graded(description = "Blockchain.blockchainArray", marks = 8)
  public void testBlockchainBlockchainArray() {

    String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
        "Thomas",
        "Sasuke", "Ichigo", "Luffy", "Scarlet"
    };

    String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
        "Naruto", "Rukia", "Ichigo", "Thomas"
    };

    double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
        400 };

    Ledger l1 = new Ledger();
    for (int i = 0; i < fromPerson.length; i++) {
      l1.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
    }

    Ledger l2 = new Ledger();
    for (int i = fromPerson.length - 1; i >= 0; i--) {
      l2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
    }

    Blockchain b1 = new Blockchain();
    Blockchain b2 = new Blockchain();

    Ledger t = new Ledger();
    t.addPayment(new Payment(fromPerson[0], toPerson[0], payment[0], null));
    b1.addLedger(t);
    t.addPayment(new Payment(fromPerson[fromPerson.length - 1], toPerson[toPerson.length - 1],
        payment[payment.length - 1], null));
    b1.addLedger(t);
    t.addPayment(new Payment(fromPerson[fromPerson.length - 2], toPerson[toPerson.length - 2],
        payment[payment.length - 2], null));
    b2.addLedger(t);

    Blockchain[] bNums = { b1, b2 };

    Blockchain b4 = new Blockchain(new Blockchain[] { b1 });

    assertNotNull(b4);
    assertEquals(b1.head.head.fromPerson, b4.head.head.fromPerson);

    b4 = new Blockchain(bNums);

    assertNotNull(b4);
    assertEquals(b1.head.tail.fromPerson, b4.head.tail.fromPerson);
    assertEquals(b1.head.head.next.fromPerson, b4.head.head.next.fromPerson);

    Ledger l4 = new Ledger();
    String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
    for (int i = 0; i < alphabet.length / 2; i++) {
      int idx = (int) Math.random() * payment.length;
      l4.addPayment(new Payment(alphabet[i], alphabet[alphabet.length - 1 - i], payment[idx], null));
    }

    Ledger l5 = new Ledger();
    for (int i = alphabet.length / 2; i < alphabet.length; i++) {
      int idx = (int) Math.random() * payment.length;
      l5.addPayment(new Payment(alphabet[i], alphabet[alphabet.length - 1 - i], payment[idx], null));
    }

    Blockchain[] b = new Blockchain[3];
    for (int i = 0; i < b.length; i++) {
      b[i] = new Blockchain();
      if (i % 2 == 0)
        b[i].addLedger(l4);
      else
        b[i].addLedger(l2);
    }

    // Blockchain mega = new Blockchain(b);

    Blockchain b9 = new Blockchain();
    Blockchain b10 = new Blockchain();
    b9.addLedger(l5);
    b10.addLedger(l4);

    Blockchain[] BcArr = { b9, b10 };
    Blockchain mega2 = new Blockchain(BcArr);

    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @Test
  @Order(17)
  @Timeout(1)
  @Graded(description = "Blockchain.blockchainArrayFast", marks = 8)
  public void testBlockchainBlockchainArrayFast() {

    assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {

      String[] fromPerson = { "Eren", "Mikasa", "a", "c", "Armin", "Jean", "Levi", "Sakura", "Naruto", "Erwin",
          "Thomas",
          "Sasuke", "Ichigo", "Luffy", "Scarlet"
      };

      String[] toPerson = { "Levi", "a", "Mikasa", "c", "Jean", "Armin", "Sakura", "Levi", "Sasuke", "Erwin", "Scarlet",
          "Naruto", "Rukia", "Ichigo", "Thomas"
      };

      double[] payment = { 10.0, 100.1, 21.0, 55.5, 29.9, 99.9, 100.0, 29.99, 30.0, 24.95, 35.0, 34.49, 99.95, 50,
          400 };

      Ledger l1 = new Ledger();
      for (int i = 0; i < fromPerson.length; i++) {
        l1.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger l2 = new Ledger();
      for (int i = fromPerson.length - 1; i >= 0; i--) {
        l2.addPayment(new Payment(fromPerson[i], toPerson[i], payment[i], null));
      }

      Ledger single = new Ledger();
      single.addPayment(new Payment("a", "c", payment[payment.length - 1], null));

      Blockchain b1 = new Blockchain();
      Blockchain b2 = new Blockchain();
      Blockchain b3 = new Blockchain();

      b1.addLedger(l1);
      b2.addLedger(l2);
      b3.addLedger(single);

      Blockchain[] b = { b1, b2 };

      Blockchain mega = new Blockchain(b);

      assertEquals(b1.head.head.fromPerson, mega.head.head.fromPerson);
      assertEquals(b1.head.head.next.next.next.next.next.next.fromPerson,
          mega.head.head.next.next.next.next.next.next.fromPerson);

      assertEquals(b2.head.tail.fromPerson, mega.head.head.fromPerson);
      assertEquals(b2.head.head.next.next.next.next.next.next.next.next.next.next.fromPerson,
          mega.head.head.next.next.next.next.fromPerson);

      assertNotEquals(b2.head.head.next.next.fromPerson, mega.head.tail.toPerson);

      Ledger l4 = new Ledger();
      String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
      for (int i = 0; i < alphabet.length / 2; i++) {
        int idx = (int) Math.random() * payment.length;
        l4.addPayment(new Payment(alphabet[i], alphabet[alphabet.length - 1 - i], payment[idx], null));
      }

      Ledger l5 = new Ledger();
      for (int i = alphabet.length / 2; i < alphabet.length; i++) {
        int idx = (int) Math.random() * payment.length;
        l5.addPayment(new Payment(alphabet[i], alphabet[alphabet.length - 1 - i], payment[idx], null));
      }

      Blockchain[] bArr2 = new Blockchain[3];
      for (int i = 0; i < bArr2.length; i++) { // LogChange v2 changed b.length to bArr2.length
        bArr2[i] = new Blockchain();
        if (i % 2 == 0)
          bArr2[i].addLedger(l4);
        else
          bArr2[i].addLedger(l2);
      }

      Blockchain mega2 = new Blockchain(bArr2);

      assertEquals(bArr2[0].head.head.next.next.fromPerson, mega.head.head.next.next.next.fromPerson);
      assertNotEquals(bArr2[1].head.head.fromPerson, mega2.head.tail.toPerson);
      assertEquals(bArr2[2].head.head.next.fromPerson, mega2.head.head.next.fromPerson);// LogChange v2 changed bArr2[1] to bArr2[2]

    });

    currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
  }

  @AfterEach
  public void logSuccess() throws NoSuchMethodException, SecurityException {
    if (currentMethodName != null && !methodsPassed.contains(currentMethodName)) {
      methodsPassed.add(currentMethodName);
      Method method = getClass().getMethod(currentMethodName);
      Graded graded = method.getAnnotation(Graded.class);
      score += graded.marks();
      result += graded.description() + " passed. Marks awarded: " + graded.marks() + "\n";
    }
  }

  @AfterAll
  public static void wrapUp() throws IOException {
    if (result.length() != 0) {
      result = result.substring(0, result.length() - 1); // remove the last "\n"
    }
    System.out.println(result);
    System.out.println("\nIndicative mark: " + score + " out of 100");
    System.out.println();
  }

}

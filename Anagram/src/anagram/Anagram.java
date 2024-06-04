package anagram;

import java.util.*;

/**
 * 2024-06-01
 * @author Klemens Wilke
 */
public class Anagram
{
  private static List<String> inputs = new ArrayList<>();


  public static void main(String[] args)
  {
    System.out.println("Welcome to Anagram.");
    boolean running = true;
    while (running)
    {
      System.out.println("\nPlease choose between:\n function #1 'Check if two words are anagrams', please enter '1'"
          + "\n function #2 'Get all previous entered anagrams for your inputs', please enter '2'"
          + "\n Please enter 'exit' to exit the program.");
      Scanner scanner = new Scanner(System.in);
      String choise = scanner.nextLine();
      switch (choise)
      {
        case "1":
          System.out.println("Please enter your first word: ");
          String input1 = scanner.nextLine();
          inputs.add(input1);
          System.out.println("Please enter your second word: ");
          String input2 = scanner.nextLine();
          inputs.add(input2);
          boolean isAnagram = isAnagram(input1, input2);
          System.out.println("'" + input1 + "' and '" + input2 + "' are " + (isAnagram ? "" : "no ") + "anagrams.");
          break;
        case "2":
          System.out.println("Please enter your word: ");
          String input3 = scanner.nextLine();
          findAnagrams(input3.toLowerCase());
          break;
        case "exit":
          System.out.println("Bye");
          running = false;
          break;
        default:
          System.out.println("'" + choise + "' is an invalid input");
          break;
      }
    }
  }


  /**
   * Checks whether the provided strings are anagrams
   * 
   * @param string1
   * @param string2
   * @return
   */
  private static boolean isAnagram(String string1, String string2)
  {
    // Strings must be the same length to be an anagram
    if (string1.length() != string2.length())
    {
      return false;
    }

    // Capitalization does not matter, so we standardize this
    String lowerCaseString1 = string1.toLowerCase();
    String lowerCaseString2 = string2.toLowerCase();

    // Count characters
    int max = 256;
    int count[] = new int[max];
    for (int i = 0; i < lowerCaseString1.length(); i++)
    {
      count[lowerCaseString1.charAt(i)]++;
      count[lowerCaseString2.charAt(i)]--;
    }

    // If a character is different, it is no longer an anagram
    for (int i = 0; i < max; i++)
    {
      if (count[i] != 0)
      {
        return false;
      }
    }
    return true;
  }


  /**
   * Checks whether the provided string has anagrams among the previous inputs and outputs them
   * 
   * @param input
   */
  private static void findAnagrams(String input)
  {
    Set<String> results = new HashSet<>();
    int j = inputs.size();
    for (int i = 0; i < j; i++)
    {
      String s = inputs.get(i);
      if (isAnagram(s, input) && !s.equals(input))
      {
        results.add(s);
      }

    }
    System.out.println("Your results:");
    if (results.isEmpty())
    {
      System.out.println("None of your previous inputs matched '" + input + "'");
    }
    else
    {
      for (String result : results)
      {
        System.out.println(result);
      }
    }
  }
}

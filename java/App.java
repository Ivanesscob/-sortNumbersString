
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class App {
    
    private static boolean addWrite = false;
    private static String pathString = "./";
    // false - s    true - f
    private static boolean choosenStat = false;

    //Statistics
    private static int quantInteger = 0;
    private static int quantFloat = 0;
    private static int quantString = 0;

    // full Statistics
    private static int maxInteger; 
    private static int minInteger;
    private static int avgInteger;
    private static int sumInteger;

    private static int maxFloat; 
    private static int minFloat;
    private static int avgFloat;
    private static int sumFloat;

    private static int maxSizeString;
    private static int minSizeString;

    //Name
    private static String integerFileName = "integers.txt";
    private static String floatsFileName = "floats.txt";
    private static String stringsFileName = "strings.txt";

    private static List<String> fileList = new LinkedList<>();

    //Lists of elements
    private static List<String> intList = new LinkedList<>();
    private static List<String> floatList = new LinkedList<>();
    private static List<String> stringList = new LinkedList<>();

    public static void main(String[] args) {    
        for (String arg : args) {
            switch (arg) {
                case "-p" -> {
                    try {

                        char firstChar = args[Arrays.asList(args).indexOf("-p") + 1].charAt(0);

                        if (Character.isDigit(firstChar)) {
                            System.out.println("Первый символ в назавнии файла не может быть числом.");
                        } else if (!isValidChar(firstChar)) {
                            System.out.println("Первый символ в назавнии файла содержит недопустимые символы для имени файла.");
                        } 
                        else{
                            integerFileName = args[Arrays.asList(args).indexOf("-p") + 1] + integerFileName;
                            floatsFileName = args[Arrays.asList(args).indexOf("-p") + 1] + floatsFileName;
                            stringsFileName = args[Arrays.asList(args).indexOf("-p") + 1] + stringsFileName;
                        }

                    } catch (Exception e) {
                        System.out.println("Неизвестная ошибка!");
                    }
                }
                case "-s" -> choosenStat = false;
                case "-f" -> choosenStat = true;
                case "-o" -> {
                    try {
                        
                        
                        char firstChar = args[Arrays.asList(args).indexOf("-p") + 1].charAt(0);
                        
                        if (Character.isDigit(firstChar)) {
                            System.out.println("Первый символ в назавнии пути не может быть числом.");
                        } else if (!isValidChar(firstChar)) {
                            System.out.println("Первый символ в назавнии пути содержит недопустимые символы для имени файла.");
                        }
                        else{
                            pathString = pathString + args[Arrays.asList(args).indexOf("-p") + 1];
                        }
                    } catch (Exception e) {
                        System.out.println("Неизвестная ошибка!");
                    }
                }
                case "-a" -> addWrite = true;
                
            }
            if (arg.endsWith(".txt")){
                fileList.add(arg);
            }
        }
        for (String string : fileList) {
            listSorter(string);
        }
        

        
    }
    private static void listSorter(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
            try {

                Long.parseLong(line);
                intList.add(line);
                quantInteger++;

            } catch (Exception e) {
                try {

                Double.parseDouble(line);
                floatList.add(line);
                quantFloat++;

                } catch (Exception ex) {
                    try {

                        stringList.add(line);
                        quantString++;

                    } catch (Exception exc) {
                        System.out.println("Неизвестная ошибка");
                    } 
                }
            }
        }
        reader.close();
        } catch (Exception e) {
            System.out.println("Файлы для считывания не найдены");
        }

    }


    private static boolean isValidChar(char c) {
        char[] invalidChars = {'\\', '/', ':', '*', '?', '"', '<', '>', '|', '-'};
        
        for (char invalidChar : invalidChars) {
            if (c == invalidChar) {
                return false;
            }
        }
        return true;
    }
    
    
}

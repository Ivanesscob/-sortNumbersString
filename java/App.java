import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class App {
    
    private static boolean addWrite = false;
    private static boolean needStatistics = false;
    private static String pathString = "";
    // false - s    true - f
    private static boolean choosenStat = false;

    //Statistics
    private static int quantInteger = 0;
    private static int quantFloat = 0;
    private static int quantString = 0;

    // full Statistics
    private static Long maxInteger; 
    private static Long minInteger;
    private static int sumInteger = 0;

    private static Double maxFloat;
    private static Double minFloat;
    private static int sumFloat = 0;

    private static int maxSizeString;
    private static int minSizeString;
    

    //Name
    private static String integerFileName = "integers.txt";
    private static String floatsFileName = "floats.txt";
    private static String stringsFileName = "strings.txt";

    private static List<String> fileList = new LinkedList<>();

    //Lists of elements
    private static List<Long> intList = new LinkedList<>();
    private static List<Double> floatList = new LinkedList<>();
    private static List<String> stringList = new LinkedList<>();

    public static void main(String[] args) {    
        for (String arg : args) {
            switch (arg) {
                case "-p" -> {
                    try {

                        char firstChar = args[Arrays.asList(args).indexOf("-p") + 1].charAt(0);

                        if (Character.isDigit(firstChar)) {
                            System.out.println("Первый символ в назавнии файла не может быть числом.");
                            return;
                        } else if (!isValidChar(firstChar)) {
                            System.out.println("Первый символ в назавнии файла содержит недопустимые символы для имени файла.");
                            return;
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
                case "-s" -> {choosenStat = false; needStatistics = true;}
                case "-f" -> {choosenStat = true;needStatistics = true;}
                case "-o" -> {
                    try {
                        
                        
                        char firstChar = args[Arrays.asList(args).indexOf("-o") + 1].charAt(0);
                        
                        if (Character.isDigit(firstChar)) {
                            System.out.println("Первый символ в назавнии пути не может быть числом.");
                            return;
                        } else if (!isValidChar(firstChar)) {
                            System.out.println("Первый символ в назавнии пути содержит недопустимые символы для имени файла.");
                            return;
                        }
                        else{
                            pathString = pathString + args[Arrays.asList(args).indexOf("-o") + 1];
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
        try {
            String longest = "";
        String shortes = stringList.get(0);
        for (String word : stringList) {
            if (word.length() > longest.length()) {
                longest = word;
            }
            else if (word.length() < shortes.length()) {
                shortes = word;
            }
        }
        maxSizeString = longest.length();
        minSizeString = shortes.length();
        maxInteger = Collections.max(intList);
        minInteger = Collections.min(intList);
        maxFloat = Collections.max(floatList);
        minFloat = Collections.min(floatList);
        } catch (Exception e) {
        }
        


        try {
            if(!(pathString.lastIndexOf('/') == pathString.length()-1)){
                pathString += "/";
            }
            File directory = new File(pathString);
            directory.mkdirs();
            File fileCreate;
            if(quantInteger != 0){
                
                fileCreate = new File(pathString+integerFileName);
                fileCreate.createNewFile();
                printInFile(pathString+integerFileName, intList);
                if(needStatistics){
                    printStatistics("целых чисел", quantInteger, (double)sumInteger, maxInteger,minInteger);
                }
                
            }
            if(quantFloat != 0){
                fileCreate = new File(pathString+floatsFileName);
                fileCreate.createNewFile();
                printInFile(pathString+floatsFileName, floatList);
                if(needStatistics){
                    printStatistics("дробных чисел", quantFloat, (double)sumFloat,maxFloat, minFloat);
                }
            }
            if(quantString != 0){
                fileCreate = new File(pathString+stringsFileName);
                fileCreate.createNewFile();
                printInFile(pathString+stringsFileName, stringList);
                if(needStatistics){
                    printStatistics();
                }
                
            }
            
            
        } catch (Exception e) {
            System.out.println("Невозможно создать файлы");
        }

        
        
        
        
    }
    private static void listSorter(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
            try {
                intList.add(Long.parseLong(line));
                //statistic
                quantInteger++;
                sumInteger += Long.parseLong(line);

            } catch (Exception e) {
                try {
                floatList.add(Double.parseDouble(line));
                quantFloat++;
                sumFloat +=Double.parseDouble(line);

                } catch (Exception ex) {
                    try {

                        stringList.add(line);
                        quantString++;

                    } catch (Exception exc) {
                        System.out.println("Ошибка синтаксиса вызова программы");
                    } 
                }
            }
        }
        reader.close();
        } catch (Exception e) {
            System.out.println("Файлы для считывания не найдены");
        }

    }

    private static void printStatistics(){
        System.out.println("\n");
        System.out.println("Статистика записи строк: \n");
        System.out.println("Всего строк: " + quantString);
        if(choosenStat){
            System.out.println("Максимальное количество символов: " + maxSizeString);
        System.out.println("Минимальное количество символов: " + minSizeString);
        }
        
    }

    private static <T> void  printStatistics(String item, int quantity, Double sum, T maxItem, T minItem){
        System.out.println("\n");
            System.out.println("Статистика записи " + item+":\n");
            System.out.println("Всего чисел: " + quantity);
        if (choosenStat){
            
            System.out.println("Сумма элементов: "+  sum);
            System.out.println("Максимальны элемент: " + maxItem);
            System.out.println("Минимальный элемент: " + minItem);
            double avgItem = sum/quantity;
            System.out.println("Среднее значение: " + avgItem);
        }
        
    }

    private static void printInFile(String filePath,List<?> stringList){
        try (FileWriter writer = new FileWriter(filePath, addWrite)) {
            for (Object string : stringList) {
                writer.write(string.toString()  + System.lineSeparator());
            }
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл.");
        }
        
    }


    private static boolean isValidChar(char c) {
        char[] invalidChars = { '\\', ':', '*', '?', '"', '<', '>', '|', '-', '.', '_' };
        
        for (char invalidChar : invalidChars) {
            if (c == invalidChar) {
                return false;
            }
        }
        return true;
    }
}

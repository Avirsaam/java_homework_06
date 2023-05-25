/*
 * Подумать над структурой класса Ноутбук для магазина техники — выделить поля и методы. Реализовать в Java.
 * Создать множество ноутбуков.
 * Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру.
 * Критерии фильтрации можно хранить в Map. Например:
 *      “Введите цифру, соответствующую необходимому критерию:
 *          1 - ОЗУ
 *          2 - Объём ЖД
 *          3 - Операционная система
 *          4 - Цвет …
 * Далее нужно запросить минимальные значения для указанных критериев — сохранить параметры фильтрации можно также в Map.
 * Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/*
 * включены файлы:
 * main.java - непосредственно этот файл
 * Laptop.java - файл описывающий класс ноутбук
 * LaptopFilter.java - файл описывающий параметры пользовательского фильтра.
 * pricelist.csv - текстовый файл с параметрами ноутбуков для демонстраци работы программы
 */
public class main {
    
    private static Set<Laptop> laptops = new HashSet<>();
    
    public static void main(String[] args) {
        
        readCsvPriceList();        
        
        LaptopFilter filter = new LaptopFilter();
        
        //в данном объекте класса будут хранится допустимые максимальные и минимальные
        //значения параметров ноутбука (для удобства при редактированни фильтра)
        LaptopFilter templateFilter = fillTemplateFilter(); 
                
        Scanner scanner = new Scanner(System.in);
                
        String option = "";        

        while (!option.startsWith("q")){
            List <String> userInput;
            List <String> allOptions;

            printMainMenu();            
            
            option = scanner.next();
            switch (option) {
                case "0":
                    printFilteredResults(filter, scanner);
                    break;
                case "1":
                    userInput = new ArrayList<>(filter.getLaptopMakers());
                    allOptions = new ArrayList<>(templateFilter.getLaptopMakers());
                    inputCheckboxMenuSelection(userInput, allOptions, scanner);                    
                    
                    for (String item : allOptions) {
                        if (filter.getLaptopMakers().contains(item)){
                            if(!userInput.contains(item)){ filter.excludeMaker(item); }
                        } else {
                            if (userInput.contains(item)){ filter.includeMaker(item); }
                        }
                    }
                    break;
                
                case "2":
                    userInput = new ArrayList<>(filter.getCpuModels());
                    allOptions = new ArrayList<>(templateFilter.getCpuModels());
                    inputCheckboxMenuSelection(userInput, allOptions, scanner);
                    
                    for (String item : allOptions) {
                        if (filter.getCpuModels().contains(item)){
                            if(!userInput.contains(item)){ filter.excludeCpuModel(item); }
                        } else {
                            if (userInput.contains(item)){ filter.includeCpuModel(item); }
                        }
                    }
                    break;

                case "3":
                    userInput = new ArrayList<>(filter.getCpuMakers());
                    allOptions = new ArrayList<>(templateFilter.getCpuMakers());
                    inputCheckboxMenuSelection(userInput, allOptions, scanner);
                    
                    for (String item : allOptions) {
                        if (filter.getCpuMakers().contains(item)){
                            if(!userInput.contains(item)){ filter.excludeCpuMaker(item); }
                        } else {
                            if (userInput.contains(item)){ filter.includeCpuMaker(item); }
                        }
                    }
                    break;

                case "4":
                    clearTerminal();
                    System.out.println("Количество оперативной память:");                    
                    filter.setRamMin(rangeLimitInput("нижнего", templateFilter.getRamMin(), "Gb", scanner));                    
                    filter.setRamMax(rangeLimitInput("верхнего", templateFilter.getRamMax(), "Gb", scanner));              
                    break;
                
                case "5":
                    clearTerminal();
                    System.out.println("Объём накопителя:");
                    filter.setRamMin(rangeLimitInput("нижнего", templateFilter.getStorageMin(), "Gb", scanner));
                    filter.setRamMax(rangeLimitInput("верхнего",templateFilter.getStorageMax(), "Gb", scanner));
                    break;

                case "6":
                    userInput = new ArrayList<>(filter.getOsNames());
                    allOptions = new ArrayList<>(templateFilter.getOsNames());
                    inputCheckboxMenuSelection(userInput, allOptions, scanner);
                    
                    for (String item : allOptions) {
                        if (filter.getOsNames().contains(item)){
                            if(!userInput.contains(item)){ filter.excludeOsName(item); }
                        } else {
                            if (userInput.contains(item)){ filter.includeOsName(item); }
                        }
                    }
                    break;
                
                case "7":
                    userInput = new ArrayList<>(filter.getColors());
                    allOptions = new ArrayList<>(templateFilter.getColors());
                    inputCheckboxMenuSelection(userInput, allOptions, scanner);
                    
                    for (String item : allOptions) {
                        if (filter.getColors().contains(item)){
                            if(!userInput.contains(item)){ filter.excludeColor(item); }
                        } else {
                            if (userInput.contains(item)){ filter.includeColor(item); }
                        }
                    }
                    break;

                case "8":
                    clearTerminal();
                    System.out.println("Цена:");
                    filter.setPriceMin(rangeLimitInput("нижнего", templateFilter.getPriceMin(), "$", scanner));
                    filter.setPriceMax(rangeLimitInput("верхнего", templateFilter.getPriceMax(), "$", scanner));
                    break;
                
                case "9":
                    clearTerminal();
                    System.out.println("Вес:");
                    filter.setWeightMin(rangeLimitInput("нижнего", templateFilter.getWeightMin(), "kg", scanner));
                    filter.setWeightMax(rangeLimitInput("верхнего", templateFilter.getWeightMax(), "kg", scanner));
                    break;    
                
                case "10":
                    clearTerminal();
                    System.out.println(filter);
                    System.out.println("Нажмите [q] и клавишу ввода для возврата к меню");
                    while (!scanner.next().startsWith("q"));
                    break;
                
                case "11":
                    filter = new LaptopFilter();
                    break;
            }
        }
        scanner.close();
    }

    /**
     * Выводит на экран меню для ввода пользователем числового значения параметра фильтра
     * @param limitName - Заголовок меню
     * @param minMaxValue - максимальное/минимальное значение существующее в текущей базе
     * @param unitName - строка, еденицы измерение вводимого параметра
     * @param s - сканнер клавиатуры для обработки пользовательского ввода
     * @return
     */
    private static Float rangeLimitInput (String limitName, Float minMaxValue, String unitName, Scanner s){        
        boolean repeat = true;
        Float result = null;
        while (repeat){
            System.out.print("\nВедите " + limitName + " границу диапазона поиска или число 0 для сброса фильтра [ " + minMaxValue + " " + unitName + " ]: ");
            String input = s.next();                                
            try{
                result = Float.parseFloat(input);
                if (result == 0) result = null;
                break;                
            }
            catch (Exception e){
                System.out.println("ошибка ввода, повторите,");                
            }            
        }
        return result;
    }


    /**
     * Выводит на экран меню для выбора пользователем пунктов из доступно возможных
     * @param listOfItemsSelected - список, состоящий из значений уже имеющихся в активном фильтре
     * @param listOfAllPossibleItems - список всех возможных опций
     * @param s - сканнер клавиатуры для обработки пользовательского ввода
     */
    private static void inputCheckboxMenuSelection (List<String> listOfItemsSelected, List<String> listOfAllPossibleItems, Scanner s){
        String input="";
        
        while (!input.startsWith("q")){
            clearTerminal();
            int count = 0;            
            for (String thisItem : listOfAllPossibleItems){
                char optionIsSelected = ' ';
                if (listOfItemsSelected.contains(thisItem)) optionIsSelected = 'x';
                System.out.printf("%3d -> [%c] %s: \n",count++, optionIsSelected, thisItem);
            }
            System.out.println("Введите номер пункта меню, чтобы\nвключить/исключить пункт из параметров фильтрации\nили нажмите [q] и клавишу ввода для возврата к предыдущему меню");
            
            input = s.next();
            try {
                String optionSelected = listOfAllPossibleItems.get(Integer.parseInt(input));
                
                if (listOfItemsSelected.contains(optionSelected)){
                    listOfItemsSelected.remove(optionSelected);
                } else {
                    listOfItemsSelected.add(optionSelected);
                }
            }
            catch (Exception NumberFormatException){                
                continue;
            }
        }        
    }
    
    
    /**
     * Выводит на экран список ноутбуков согласно активного фильтра
     * @param filter - активный фильтр
     * @param s - сканнер клавиатуры для обработки пользовательского ввода
     */
    private static void printFilteredResults(LaptopFilter filter, Scanner s) {                
        boolean hasResults = false;
        for (Laptop laptop : laptops) {
            if (filter.isMatch(laptop)){
                System.out.println(laptop);
                hasResults = true;
            } else {
                //System.out.println("---------" + laptop);
            }
        }
        if (!hasResults) System.out.println("Нет резултатов удовлетворяющих условиям фильтра");
        System.out.println("Нажмите [q] и клавишу ввода для возврата к меню");
        while (!s.next().startsWith("q"));
    }

    /**
     * Выводит на экран главное меню программы
     */
    private static void printMainMenu(){     
        clearTerminal();   
        System.out.println("[0] - вывести результаты поиска");
        System.out.println("------Редактировать параметры фильтра--------");
        System.out.println("[1] - Производитель ноутбука");
        System.out.println("[2] - Линейка процессора");
        System.out.println("[3] - Производитель процессора");
        System.out.println("[4] - Размер оперативной памяти");
        System.out.println("[5] - Объем встроенного накопителя");
        System.out.println("[6] - Операционная система");
        System.out.println("[7] - Цвет корпуса");
        System.out.println("[8] - Цена");
        System.out.println("[9] - Вес");
        System.out.println("[10] - Показать параметры активного фильтра");
        System.out.println("[11] - Сбросить все фильтры");
        System.out.println("Нажмите клавишу [q] и клавишу ввода для выхода из программы");                        
    }

    /**     
     * @return - возвращает объект класса фильтр заполненный возможными значениями
     */
    private static LaptopFilter fillTemplateFilter(){
        LaptopFilter result = new LaptopFilter();
    
        for (Laptop laptop : laptops) {
            result.includeMaker(laptop.getMakerName());
            result.includeColor(laptop.getColor());
            result.includeCpuMaker(laptop.getCpuMaker());
            result.includeOsName(laptop.getOperationalSystem());
            result.includeCpuModel(laptop.getCpuModel());

            if (result.getPriceMax() == null
            || laptop.getPrice() > result.getPriceMax())
                result.setPriceMax(laptop.getPrice());

            if (result.getPriceMin() == null
            || laptop.getPrice() < result.getPriceMin())
                result.setPriceMin(laptop.getPrice());


            if (result.getWeightMax() == null
            || laptop.getWeight() > result.getWeightMax())
                result.setWeightMax(laptop.getWeight());

            if (result.getWeightMin() == null
            || laptop.getWeight() < result.getWeightMin())
                result.setWeightMin(laptop.getWeight());


            if (result.getRamMax() == null
            || laptop.getRamGigabytes() > result.getRamMax())
                result.setRamMax(laptop.getRamGigabytes());

            if (result.getRamMin() == null
            || laptop.getRamGigabytes() < result.getRamMin())
                result.setRamMin(laptop.getRamGigabytes());


            if (result.getStorageMax() == null
            || laptop.getTotalStorageInGigabytes() > result.getStorageMax())
                result.setStorageMax(laptop.getTotalStorageInGigabytes());

            if (result.getStorageMin() == null
            || laptop.getTotalStorageInGigabytes() < result.getStorageMin())
                result.setStorageMin(laptop.getTotalStorageInGigabytes());
        }
        
        return result;
    }

    /**
     * заполняет коллекцию ноутбуков из данных, находящихся в текстовом файле
     */
    private static void readCsvPriceList (){

        try (BufferedReader br = new BufferedReader(new FileReader("pricelist.csv"))) {
            
            String line = "";
            int lineNumber = 1;

            while ((line = br.readLine()) != null){
                if (lineNumber++ == 1) continue; //пропускаем заголовок в прайслисте
                laptops.add(new Laptop(line));
            }

        } 
        catch (IOException e) {            
            System.out.println("Ошибка чтения csv файла с базой данных ноутбуков");
        }
    }

    private static void clearTerminal(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    
}

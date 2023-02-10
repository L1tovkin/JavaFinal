import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class JavaFinal {
    
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        
        // создаю БД ноутов
        Set<Notebook>BD = createBD();
        
        // вывожу все на экран (чтоб можно было управлять параметрами при отборе)
        printResult(BD);
        System.out.println();
        
        // реализую выборку по заданным критериям и вывожу результат на экран
        Set<Notebook> result = implementationFilter(createFilter(),BD);
        if (result.isEmpty())
            System.out.println("There is nothing of your choice");
        else
        {
            if(result.size() == BD.size()) 
                System.out.println("All products:");
            else
                System.out.println("Products according to your choice:");    
            printResult(result);
        }    
    }

    // метод создания создания БД ноутбуков
    static Set<Notebook> createBD()
    {
        String[] CPUList = {"Celeron", "Pentium", "Core i3", "Core i5", "A6", "A8", "A10", "Core i5"};
        String[] brandList = {"HP", "Acer", "Xiaomi", "Dell", "Lenovo", "Asus", "Apple"};
        String[] systemList = {"Windows", "MacOS", "Linux"};
        String[] colorList = {"silver", "black", "white", "blue", "red"};
        int[] driveList = {64, 128, 256, 512, 1024, 2048, 4096};    // GByte
        int[] RAMList = {4, 8, 12, 16, 32};                         // GByte
        
        Set<Notebook> laptops = new HashSet<>();
        for(int i = 0; i < 20; i++)
        {
            Notebook notebook = new Notebook();
            notebook.serialNumber = (int)(Math.random()*(1000000 - 100000)) + 100000;   // серийник - лучайное шестизначное число
            notebook.screenSize = (int)(Math.random()*(20-10)) + 10;                    // размер - от 10" до 19"
            notebook.CPU = CPUList[(int)(Math.random()*CPUList.length)];                
            notebook.CPUfrequencies = Math.round(Math.random()*(4.1-1.6) * 10) / 10.0;  // тактовая частота - от 10.0 до 4.0 ГГц
            notebook.drive = driveList[(int)(Math.random()*driveList.length)];
            notebook.brand = brandList[(int)(Math.random()*brandList.length)];
            notebook.RAM = RAMList[(int)(Math.random()*RAMList.length)];
            notebook.operatingSystem = systemList[(int)(Math.random()*systemList.length)];
            notebook.color = colorList[(int)(Math.random()*colorList.length)];
            notebook.price = (int)(Math.random()*(99999-20999)) + 20999;                // цена - случайное число от 20999 до 99998 руб.
            laptops.add(notebook);
        }
        return laptops;
    }
    
    // метод вывода характиристик каждой модели в отдельной строке
    static void printResult(Set<Notebook> laptops)
    {
        for (Notebook item: laptops) 
            System.out.println(item);
    }

    // метод создания запроса
    static Map<Integer, Set<Integer>> createFilter()
    {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        System.out.println("Enter the numbers corresponding to the required criterion separated by a space:");
        System.out.println("  1 - preferred size, inch");
        System.out.println("  2 - RAM Value, GB");
        System.out.println("  3 - Drive Value, GB");
        System.out.println("  4 - preferred price, rub");
        System.out.println("  5 - operating system");
        System.out.println("  6 - preferred color");
        System.out.println("  0 - the whole list");
        String choice = scan.nextLine().trim();
        if (choice.equals("0"))
            return null;
        String[] result = correctChoice(choice).split(" ");
        for (String item: result)
        {
            Integer number = Integer.parseInt(item);
            switch (number) 
            {
                case 1:
                    System.out.print("  enter the minimum laptop size (in inch)  ");
                    try
                      { 
                        Integer minValue = Integer.parseInt(scan.nextLine());
                        Set<Integer> value = new HashSet<>();
                        value.add(minValue); 
                        map.put(1, value);
                      }
                    catch (NumberFormatException e) {continue;}
                    break;

                case 2:
                    System.out.print("  enter the minimum RAM value (in GB)  ");
                    try
                      { 
                        Integer minValue = Integer.parseInt(scan.nextLine());
                        Set<Integer> value = new HashSet<>();
                        value.add(minValue); 
                        map.put(2, value);
                      }
                    catch (NumberFormatException e) {continue;}
                    break; 
                    
                case 3:
                    System.out.print("  enter the minimum Drive value (in GB)  ");
                    try
                      { 
                        Integer minValue = Integer.parseInt(scan.nextLine());
                        Set<Integer> value = new HashSet<>();
                        value.add(minValue); 
                        map.put(3, value);
                      }
                    catch (NumberFormatException e) {continue;}
                    break;
                    
                case 4:
                    System.out.print("  enter the minimum price (in rub)  ");
                    try
                      { 
                        Integer minValue = Integer.parseInt(scan.nextLine());
                        Set<Integer> value = new HashSet<>();
                        value.add(minValue); 
                        map.put(4, value);
                      }
                    catch (NumberFormatException e) {continue;}
                    break;       

                case 5:
                    System.out.println("  specify one or more OS numbers:");
                    System.out.println("    1 - Windows\n    2 - MacOS\n    3 - Linux");
                    String[] numOS = correctChoice(scan.nextLine()).split(" ");
                    Set<Integer> valueOS = new HashSet<>();
                    for(String os: numOS)
                        {
                            try
                                {                                
                                    Integer numberOS = Integer.parseInt(os);
                                    if (numberOS>=1 && numberOS <=3)
                                        valueOS.add(numberOS); 
                                }
                            catch (NumberFormatException e) {continue;}
                        }
                    if (!valueOS.isEmpty()) map.put(5, valueOS);
                    break; 
                    
                case 6:
                    System.out.println("  specify one or more color numbers:");
                    System.out.println("    1 - silver\n    2 - black\n    3 - white\n    4 - blue\n    5 - red");
                    String[] numColor = correctChoice(scan.nextLine()).split(" ");
                    Set<Integer> valueColor = new HashSet<>();
                    for(String color: numColor)
                        {
                            try
                                {                                
                                    Integer numberColor = Integer.parseInt(color);
                                    if (numberColor>=1 && numberColor <=5)
                                        valueColor.add(numberColor); 
                                }
                            catch (NumberFormatException e) {continue;}
                        }
                    if (!valueColor.isEmpty())map.put(6, valueColor);
                    break;                     
            }
        }
        return map;
    }

    // метод отбора моделей из БД ноутов по указанным критериям
    static Set<Notebook> implementationFilter(Map<Integer, Set<Integer>> map, Set<Notebook> laptops)
    {
        if (map == null)
            return laptops;
            
        Integer minSize = -1;        
        if (map.containsKey(1))
        {
            Set<Integer> set = map.get(1);
            Iterator<Integer> it = set.iterator();
            minSize = it.next();
        }
        
        Integer minRam = -1;    
        if (map.containsKey(2))
        {
            Set<Integer> set = map.get(2);
            Iterator<Integer> it = set.iterator();
            minRam = it.next();
        }

        Integer minDrive = -1;    
        if (map.containsKey(3))
        {
            Set<Integer> set = map.get(3);
            Iterator<Integer> it = set.iterator();
            minDrive = it.next();
        }

        Integer minPrice = -1;    
        if (map.containsKey(4))
        {
            Set<Integer> set = map.get(4);
            Iterator<Integer> it = set.iterator();
            minPrice = it.next();
        }

        Set<String> os = new HashSet<>();    
        if (map.containsKey(5))
        {
            Set<Integer> set = map.get(5);
            Iterator<Integer> it = set.iterator();
            while(it.hasNext())
            {
                Integer num = it.next();
                //  1 - Windows, 2 - MacOS, 3 - Linux;
                switch (num) 
                {
                    case 1: os.add("Windows");break;
                    case 2: os.add("MacOS");break;
                    case 3: os.add("Linux");
                }
             }
        }

        Set<String> clr = new HashSet<>();    
        if (map.containsKey(6))
        {
            Set<Integer> set = map.get(6);
            Iterator<Integer> it = set.iterator();
            while(it.hasNext())
            {
                Integer num = it.next();
                //  1 - silver, 2 - black, 3 - white, 4 - blue, 5 - red
                switch (num) 
                {
                    case 1: clr.add("silver");break;
                    case 2: clr.add("black");break;
                    case 3: clr.add("white");
                    case 4: clr.add("blue");
                    case 5: clr.add("red");
                }
             }
        }

        Set<Notebook> query = new HashSet<>();
        for (Notebook item: laptops) 
        {
            boolean fullCompliance = (minSize == -1 || item.screenSize >= minSize) && 
                                     (minRam == -1 || item.RAM >= minRam) &&
                                     (minDrive == -1 || item.drive >= minDrive) &&
                                     (os.isEmpty() || os.contains(item.operatingSystem)) &&
                                     (clr.isEmpty() || clr.contains(item.color));
            if (fullCompliance) query.add(item);
        }
        return query;
    }

    static boolean isChoiceNumber(char c)
    {
        return Character.isDigit(c) && c >= '1' && c <= '6';
    }

    static String correctChoice(String input)
    {
        char[] chars = input.trim().toCharArray();
        StringBuilder s = new StringBuilder();
        int n = chars.length;
        for (int i = 0; i < n-1; i++) {
            char c = chars[i];
            if (isChoiceNumber(c) || c == ' ' && chars[i+1] !=' ')
                s.append(chars[i]);
        }
        if (isChoiceNumber(chars[n - 1])) s.append(chars[n - 1]);
        return s.toString().trim();
    }
}
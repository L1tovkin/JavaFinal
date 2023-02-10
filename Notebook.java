public class Notebook {

    int serialNumber;       // серийный номер ноута
    int screenSize;         // размер ноута
    String CPU;             // процессор
    double CPUfrequencies;  // тактовая частота процессора
    int drive;              // размер жёсткого диска, ГБ
    String brand;           // фирма
    int RAM;                // объём оперативки, ГБ
    String operatingSystem; // операционка
    String color;           // цвет
    int price;              // цена

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Notebook))
            return false;  
        Notebook laptop = (Notebook) obj;
        return serialNumber == laptop.serialNumber;
    }

    @Override
    public String toString() {
        String driveToPrint = (drive > 1000)? drive/1024 + " TByte, ": drive + " GByte, ";
        String result =  "NOTEBOOK " + brand + " " + screenSize + "\", " +  CPU + " " + CPUfrequencies + " Hz, " + driveToPrint + "RAM " + RAM + " GB, "  ;
        result += operatingSystem + ", " + color + ", "  + "No " + serialNumber + ", " + price + " rub.";
        return result;
    }


    
}
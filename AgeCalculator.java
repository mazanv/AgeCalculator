import java.util.Scanner;
import java.util.Calendar;
import javax.swing.SwingUtilities;

public class AgeCalculator {
    public static void main(String[] args) {

        // can choose console or gui version 
        System.out.println("=== MODE PROGRAM ===");
        System.out.println("1. Console");
        System.out.println("2. GUI");
        System.out.print("Pilih mode (1/2): ");


        Scanner modeInput = new Scanner(System.in);
        int mode = modeInput.nextInt();
        if(mode == 1){
            runConsoleVersion(); // run console ver 
        }else{
            SwingUtilities.invokeLater(() ->
                new AgeCalculatorGUI()); // run gui ver 
        }
        modeInput.close();
    }

    // console version
    public static void runConsoleVersion() {
        Scanner input = new Scanner(System.in); 

        int day, month, year; 
        System.out.println("\n=== PROGRAM HITUNG UMUR ===");

        while(true){
            System.out.print("Masukkan tanggal lahir (dd-mm-yyyy) : ");
            String tanggal = input.next(); 

            String[] parts = tanggal.split("-");

            if(parts.length != 3){
                System.out.println("Format tidak valid! Contoh : 22-12-2002");
                continue; 
            }
            try {
                day = Integer.parseInt(parts[0]);
                month = Integer.parseInt(parts[1]);
                year = Integer.parseInt(parts[2]);
            }catch (NumberFormatException e){
                System.out.println("Input harus berupa angka! Contoh: 02-12-2002");
                continue; 
            }

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            
            // cek validasi bulan 
            if(month < 1 || month > 12){
                System.out.println("Bulan harus antara (1 - 12) ");
                continue;
            }

            if(year < 1900 || year > currentYear){
                System.out.println("Tahun tidak valid!");
                continue;
            }

            // cek hari valid 
            if(!isValidDay(day, month, year)){
                System.out.println("Tanggal tidak valid untuk bulan dan tahun tersebut!");
                continue;
            }

            break;
        }

        BirthDate birthDate = new BirthDate(day, month, year);
        int age = calculateAge(birthDate);
        System.out.println("\n Umur kamus saat ini : " + age + " tahun.");

        input.close();
    }
       

    public static int calculateAge(BirthDate bd){
        Calendar today = Calendar.getInstance();

        int currentYear = today.get(Calendar.YEAR);
        int currentMonth = today.get(Calendar.MONTH) + 1; 
        int currentDay = today.get(Calendar.DAY_OF_MONTH);

        int age = currentYear - bd.getYear();

         // jika tahun ini belum berulang tahun 
        if(currentMonth < bd.getMonth()|| 
        (currentMonth == bd.getMonth()
        && currentDay < bd.getDay())){
        age--; 
        }

        return age;
    }

    // validasi tanggal (jumlah hari tiap bulan + kabisat)
    public static boolean isValidDay(int day, int month, int year){
        int[] daysInMonth = 
        {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        if(isLeapYear(year)){
            daysInMonth[1] = 29; // feb

        }
        return day >= 1 && day <= daysInMonth[month - 1];
    }

    public static boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

    }

}



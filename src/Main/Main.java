package Main;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<String, String> countries = new HashMap<>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static class IncomeDataAdapter implements Customer, Contact {

        private IncomeData incomeData;

        public IncomeDataAdapter(IncomeData incomeData){
            this.incomeData = incomeData;
        }

        @Override
        public String getName() {
            return incomeData.getContactLastName() + ", " + incomeData.getContactFirstName();
        }

        @Override
        public String getPhoneNumber() {
            String phoneNumber = String.valueOf(incomeData.getPhoneNumber());

            while (phoneNumber.length() < 10){
                phoneNumber = "0" + phoneNumber;
            }

            String newPhoneNumber = "(" + phoneNumber.substring(0, 3) + ")";
            newPhoneNumber += phoneNumber.substring(3, 6);
            newPhoneNumber += "-" + phoneNumber.substring(6, 8);
            newPhoneNumber += "-" + phoneNumber.substring(8);

            return "+" + incomeData.getCountryPhoneCode() + newPhoneNumber;
        }

        @Override
        public String getCompanyName() {
            return incomeData.getCompany();
        }

        @Override
        public String getCountryName() {
            return countries.get(incomeData.getCountryCode());
        }
    }

    public interface IncomeData {
        String getCountryCode();

        String getCompany();

        String getContactFirstName();

        String getContactLastName();

        int getCountryPhoneCode();

        int getPhoneNumber();
    }

    public interface Customer {
        String getCompanyName();

        String getCountryName();
    }

    public interface Contact {
        String getName();

        String getPhoneNumber();
    }

    public static class IncomeDataDataArt implements IncomeData{

        @Override
        public String getCountryCode() {
            return "RU";
        }

        @Override
        public String getCompany() {
            return "DataArt";
        }

        @Override
        public String getContactFirstName() {
            return "Vadim";
        }

        @Override
        public String getContactLastName() {
            return "Taratonov";
        }

        @Override
        public int getCountryPhoneCode() {
            return 7;
        }

        @Override
        public int getPhoneNumber() {
            return 99999;
        }
    }

    public static class IncomeDataRedCollar implements IncomeData{

        @Override
        public String getCountryCode() {
            return "UA";
        }

        @Override
        public String getCompany() {
            return "RedCollar";
        }

        @Override
        public String getContactFirstName() {
            return "Kirill";
        }

        @Override
        public String getContactLastName() {
            return "Taratonov";
        }

        @Override
        public int getCountryPhoneCode() {
            return 38;
        }

        @Override
        public int getPhoneNumber() {
            return 960122;
        }
    }

    public static class IncomeDataTinkoff implements IncomeData{

        @Override
        public String getCountryCode() {
            return "CA";
        }

        @Override
        public String getCompany() {
            return "Tinkoff";
        }

        @Override
        public String getContactFirstName() {
            return "Elena";
        }

        @Override
        public String getContactLastName() {
            return "Taratonova";
        }

        @Override
        public int getCountryPhoneCode() {
            return 1;
        }

        @Override
        public int getPhoneNumber() {
            return 1234567899;
        }
    }

    public static void main(String[] args) {
        IncomeDataDataArt DataArt = new IncomeDataDataArt();
        IncomeDataRedCollar RedCollar = new IncomeDataRedCollar();
        IncomeDataTinkoff Tinkoff = new IncomeDataTinkoff();

        Customer Vadim = new IncomeDataAdapter(DataArt);
        System.out.println("Покупатель: ");
        System.out.println(Vadim.getCompanyName());
        System.out.println(Vadim.getCountryName());

        Contact forVadim = new IncomeDataAdapter(DataArt);
        System.out.println("Контактные данные: ");
        System.out.println(forVadim.getName());
        System.out.println(forVadim.getPhoneNumber());

        Customer Person = new IncomeDataAdapter(Tinkoff);
        System.out.println("Покупатель: ");
        System.out.println(Person.getCompanyName());
        System.out.println(Person.getCountryName());

        Contact forPerson = new IncomeDataAdapter(Tinkoff);
        System.out.println("Контактные данные: ");
        System.out.println(forPerson.getName());
        System.out.println(forPerson.getPhoneNumber());
    }
}
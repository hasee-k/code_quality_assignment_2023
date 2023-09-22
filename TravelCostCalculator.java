//4030 Kumanayake H.P.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


//we need a way to extract details from a csv file
public class TravelCostCalculator {

    static Map<String, Double> hotelRates = new HashMap<>();
    static Map<String, Double> exchangeRates= new HashMap<>();
    static Map<String, Double> flightCosts = new HashMap<>();

    static void l(String file, Map j) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String i; 
        while ((i = reader.readLine()) != null)
        {
            String[] p = i.split(",");
            j.put(p[0].toUpperCase(), Double.parseDouble(p[1]));
        }
    }

    
   

    public static void main(String[] args) {
        try {

            
            l("data/hotel_rates.csv",hotelRates);
            l("data/exchange_rates.csv",exchangeRates);
            l("data/flight_costs.csv",flightCosts);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your destination: ");
            String destination = reader.readLine().toUpperCase();

            double flight_cost = flightCosts.getOrDefault(destination, 0.0);
            double hotel_cost = hotelRates.getOrDefault(destination, 0.0);

            System.out.print("Enter your stay duration in days: ");
            int stay_duration = Integer.parseInt(reader.readLine());
            hotel_cost *= stay_duration;//Total hotel cost is the costperday multiplied by number of days

            double total_cost_usd = flight_cost + hotel_cost;

            System.out.printf("Flight cost: USD %.2f\n", flight_cost);
            System.out.printf("Hotel cost (%d days): USD %.2f\n", stay_duration, hotel_cost);
            System.out.printf("Total: USD %.2f\n", total_cost_usd);


            //to convert the price in usd to the desired local currency, 
            //we need to know the desired currency
            String[] available_currencies = exchangeRates.keySet().toArray(new String[0]);
            System.out.print("Select your currency for final price estimation(" 
            + String.join(", ", available_currencies) + "): ");
            String selected_currency = reader.readLine();

            double final_price_local_currency = total_cost_usd * b.get(selected_currency);

            System.out.printf("Total in %s: %.2f\n", selected_currency, final_price_local_currency);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

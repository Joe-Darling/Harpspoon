package game;

/**
 * Created by Joe on 7/5/2017.
 * Reporter test class
 */
public class ReporterTest {
    public static void main(String[] args) {
        Reporter report = new Reporter();
        System.out.println("Report is empty? " + (report.getReport().size() == 0 ? "OK" : "NO"));
        System.out.println("Creating a new report will start a game, just enter the info as normal:");
        Harpspoon sample = new Harpspoon();
        report.newReport(sample);
        System.out.println("\nEnsure output is correct:");
        report.printReport();
        System.out.println("\nEnsure output is correct:");
        report.addPlayerStats(sample);
        report.printReport();
        System.out.println("\nEnsure output is correct:");
        report.addStringToReport("Testing 123");
        report.printReport();
    }
}

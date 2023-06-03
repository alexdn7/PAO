public class Main {
    public static void main(String[] args) {
        int[] domain = {-1, 9};
        Population population = new Population(20, -1,  8, 13, domain, 6, 0.25, 0.01);
        population.printPopulation();

        int numberOfSteps = 50;
        for(int i = 0; i < numberOfSteps; i++) {
            population.evolution();
        }
    }
}
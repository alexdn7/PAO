import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {

    private final int populationSize;
    private final int a;
    private final int b;
    private final int c;
    private Chromosome[] chromosomes;
    private final double crossoverProbability;
    private final double[] selectionProbability;
    private final double total;
    private final double[] selectionIntervals;
    private final Random random = new Random();
    private final DecimalFormat decimalFormat;
    private final double mutationProbability;
    private final int precision;

    public Population(int populationSize, int a, int b, int c, int[] domain, int precision, double crossoverProbability, double mutationProbability) {
        this.populationSize = populationSize;
        this.a = a;
        this.b = b;
        this.c = c;
        this.chromosomes = new Chromosome[populationSize];
        this.precision = precision;
        generatePopulation(domain);
        this.selectionProbability = new double[populationSize];
        this.total = generateTotal();
        this.selectionIntervals = new double[populationSize + 1];
        this.crossoverProbability = crossoverProbability;
        decimalFormat = new DecimalFormat("0.0" + "0".repeat(precision - 1));
        this.mutationProbability = mutationProbability;
    }

    public void generatePopulation(int[] domain) {
        for(int i = 0; i < populationSize; i++) {
            Chromosome chromosome = new Chromosome(domain, precision);
            this.chromosomes[i] = chromosome;
        }
    }


    public double getFunctionValue(Chromosome chromosome) {
        return (a * Math.pow(chromosome.getValue(), 2) + b * chromosome.getValue() + c);
    }

    public double generateTotal() {
        double temp = 0;
        for(Chromosome chromosome: chromosomes) {
            temp += getFunctionValue(chromosome);
        }
        return temp;
    }

    public void generateSelectionProbability() {
        for(int i = 0; i < populationSize; i++) {
            selectionProbability[i] = getFunctionValue(chromosomes[i]) / total;
        }
    }

    public void generateSelectionIntervals() {
        selectionIntervals[0] = 0;
        selectionIntervals[1] = selectionProbability[0];

        for(int i = 2; i <= populationSize; i++) {
            for(int j = 1; j <= i; j++) {
                selectionIntervals[i] += selectionProbability[j - 1];
            }
        }
    }

    public double[] getSelectionIntervals() {
        return selectionIntervals;
    }

    public int binarySearch(double targetValue) {
        int left = 0;
        int right = selectionIntervals.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(selectionIntervals[mid] <= targetValue && selectionIntervals[mid + 1] > targetValue) {
                return mid;
            } else if(selectionIntervals[mid] > targetValue) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return -1;
    }

    public double[] selectedChromosomesUtil() {
        double randomValue = random.nextDouble();
        return new double[]{randomValue, binarySearch(randomValue)};
    }

    public double getCrossoverProbability() {
        return crossoverProbability;
    }

    public void crossover(int firstPosition, int secondPosition, Chromosome chromosome1, Chromosome chromosome2, int crossoverPoint) {
        int[] chromosome1Representation = chromosome1.getChromosomeInBinary();
        int[] chromosome2Representation = chromosome2.getChromosomeInBinary();

        for(int i = 0; i < crossoverPoint; i++) {
            int temp = chromosome1Representation[i];
            chromosome1Representation[i] = chromosome2Representation[i];
            chromosome2Representation[i] = temp;
        }

        chromosome1.setChromosomeInBinary(chromosome1Representation);
        chromosome2.setChromosomeInBinary(chromosome2Representation);
        chromosome1.generateValue();
        chromosome2.generateValue();
        chromosomes[firstPosition] = chromosome1;
        chromosomes[secondPosition] = chromosome2;
    }

    public void mutation() {
        for(int i = 0; i < populationSize; i++) {
            Chromosome temp = chromosomes[i];
            for(int j = 0; j < temp.getLength(); j++) {
                if(Math.random() < mutationProbability) {
                    int[] chromosomeAsBinary = temp.getChromosomeInBinary();
                    if(chromosomeAsBinary[j] == 0)
                        chromosomeAsBinary[j] = 1;
                    else
                        chromosomeAsBinary[j] = 0;
                    temp.setChromosomeInBinary(chromosomeAsBinary);
                }
            }
            chromosomes[i] = new Chromosome(temp, 6);
        }
    }

    public void evolution() {
        double total = 0.0;
        double maximFunction = Double.MIN_VALUE;
        double maximValue = Double.MIN_VALUE;

        for (int i = 0; i < populationSize; i++) {
            if (maximFunction < getFunctionValue(chromosomes[i]))
                maximFunction = getFunctionValue(chromosomes[i]);

            total += getFunctionValue(chromosomes[i]);

            if (maximValue < chromosomes[i].getValue())
                maximValue = chromosomes[i].getValue();
        }

        generateSelectionProbability();
        generateSelectionIntervals();

        Chromosome[] tempChromosomes = new Chromosome[populationSize];
        for (int i = 0; i < populationSize; i++) {
            double[] temp = selectedChromosomesUtil();
            int position = (int) temp[1];
            tempChromosomes[i] = chromosomes[position];
        }

        System.arraycopy(tempChromosomes, 0, chromosomes, 0, populationSize);

        List<Integer> chromosomesForCrossover = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            double randomValue = random.nextDouble();
            if (randomValue < crossoverProbability) {
                chromosomesForCrossover.add(i);
            }
        }

        for (int i = 0; i < chromosomesForCrossover.size() - 1; i = i + 2) {
            if ((i + 1) < chromosomesForCrossover.size()) {
                int crossoverPoint = (int) (1 + Math.random() * (chromosomes[chromosomesForCrossover.get(i + 1)].getLength() - 1));
                crossover(i, (i + 1), chromosomes[chromosomesForCrossover.get(i)], chromosomes[chromosomesForCrossover.get(i + 1)], crossoverPoint);
            }
        }

        mutation();

        try {
            FileWriter writer = new FileWriter("C:\\Users\\User\\Downloads\\learning-spring\\Genetic algorithms\\src\\Evolutie.txt", true);
            writer.write("\n" +"MAX VALUE: " + decimalFormat.format(maximValue) +
                         ", MAX: " + decimalFormat.format(maximFunction) +
                         ", AVG:" + decimalFormat.format((total / populationSize)));
            writer.close();
        } catch (IOException e) {
            System.out.println("File doesn't exist!");
            e.printStackTrace();
        }
    }

    public void printPopulation() {

        try {
            FileWriter writer = new FileWriter("C:\\Users\\User\\Downloads\\learning-spring\\Genetic algorithms\\src\\Evolutie.txt");

            writer.write("Initial population for your input is: \n");
            for(int i = 0; i < populationSize; i++) {
                writer.write((i + 1) + ". " + chromosomes[i].getChromosomeAsString()
                        + ", x = " + decimalFormat.format(chromosomes[i].getValue())
                        + ", f(x) = " + decimalFormat.format(getFunctionValue(chromosomes[i])) + "\n");
            }

            writer.write("\nSelection probability for each chromosome is: \n");
            generateSelectionProbability();
            for(int i = 0; i < populationSize; i++) {
                writer.write((i + 1) + ". " + decimalFormat.format(selectionProbability[i]) + "\n");
            }

            writer.write("\nSelection intervals are: \n");
            generateSelectionIntervals();
            for(int i = 0; i <= populationSize; i++) {
                writer.write(i + ". " + decimalFormat.format(selectionIntervals[i]) + "\n");
            }

            writer.write("\nRandom generated numbers and selected chromosomes: \n");
            Chromosome[] tempChromosomes = new Chromosome[populationSize];
            for(int i = 0; i < populationSize; i++) {
                double[] temp = selectedChromosomesUtil();
                int position = (int) temp[1];
                writer.write("Generated value: " + decimalFormat.format(temp[0]) + ", corresponds to interval " +
                            (position + 1) + ", to chromosome: " +
                            chromosomes[position].getChromosomeAsString() + ", x = " +
                            decimalFormat.format(chromosomes[position].getValue()) + ", f(x) = " +
                            decimalFormat.format(this.getFunctionValue(chromosomes[position])) + "\n");

                tempChromosomes[i] = chromosomes[position];
            }
            chromosomes = tempChromosomes;

            writer.write("\nCrossover probability is: " + crossoverProbability + "\n");
            writer.write("Chromosomes that are compatible: \n");
            List<Integer> chromosomesForCrossover = new ArrayList<>();
            for(int i = 0; i < populationSize; i++) {
                double randomValue = random.nextDouble();
                writer.write((i + 1) + ". " + chromosomes[i].getChromosomeAsString() + " with value " + decimalFormat.format(randomValue));
                if(randomValue < crossoverProbability) {
                    writer.write(" -> yes");
                    chromosomesForCrossover.add(i);
                }
                writer.write("\n");
            }

            writer.write("\nCrossover:\n");
            writer.write("Chromosomes that participate: \n");
            for(int i: chromosomesForCrossover) {
                writer.write((i + 1) + " " );
            }
            writer.write("\n\n");

            for(int i = 0; i < chromosomesForCrossover.size(); i = i + 2) {
                if((i + 1) < chromosomesForCrossover.size()) {
                    writer.write("Chromosomes: " + (chromosomesForCrossover.get(i) + 1) + ". " +
                            chromosomes[chromosomesForCrossover.get(i)].getChromosomeAsString() + " and " +
                            (chromosomesForCrossover.get(i + 1) + 1) + ". " +
                            chromosomes[chromosomesForCrossover.get(i + 1)].getChromosomeAsString() + "\n");

                    int crossoverPoint = (int) (1 + Math.random() * (chromosomes[chromosomesForCrossover.get(i + 1)].getLength() - 1));
                    crossover(i, (i + 1), chromosomes[chromosomesForCrossover.get(i)], chromosomes[chromosomesForCrossover.get(i + 1)], crossoverPoint);

                    writer.write("After crossover (with point " + crossoverPoint + "): \n");
                    writer.write("Chromosomes: " + (chromosomesForCrossover.get(i) + 1) + ". " +
                            chromosomes[chromosomesForCrossover.get(i)].getChromosomeAsString() + " and " +
                            (chromosomesForCrossover.get(i + 1) + 1) + ". " +
                            chromosomes[chromosomesForCrossover.get(i + 1)].getChromosomeAsString() + "\n\n");
                }
            }

            writer.write("\n");


            writer.write( "Population after crossover: \n");
            for(int i = 0; i < populationSize; i++) {
                writer.write((i + 1) + ". " + chromosomes[i].getChromosomeAsString()
                        + ", x = " + decimalFormat.format(chromosomes[i].getValue())
                        + ", f(x) = " + decimalFormat.format(getFunctionValue(chromosomes[i])) + "\n");
            }

            mutation();
            writer.write( "\n\nPopulation after mutation: \n");
            for(int i = 0; i < populationSize; i++) {
                writer.write((i + 1) + ". " + chromosomes[i].getChromosomeAsString()
                        + ", x = " + decimalFormat.format(chromosomes[i].getValue())
                        + ", f(x) = " + decimalFormat.format(getFunctionValue(chromosomes[i])) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File doesn't exist!");
            e.printStackTrace();
        }

    }
}

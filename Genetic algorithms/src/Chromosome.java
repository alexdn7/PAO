public class Chromosome {

    private final int[] domain;
    private final int length;
    private int[] chromosomeInBinary;
    private final double value;

    public Chromosome(int[] domain, int precision) {
        this.domain = domain;
        this.length = (int) Math.ceil(Math.log((this.domain[1] - this.domain[0]) * Math.pow(10, precision)) / Math.log(2));
        chromosomeInBinary = new int[this.length];
        generateInitialChromosome();
        this.value = generateValue();
    }

    public Chromosome(Chromosome temp, int precision) {
        this.domain = temp.domain;
        this.length = (int) Math.ceil(Math.log((this.domain[1] - this.domain[0]) * Math.pow(10, precision)) / Math.log(2));
        chromosomeInBinary = new int[this.length];
        generateInitialChromosome();
        this.value = generateValue();
    }

    public int getLength() {
        return length;
    }

    public int[] getChromosomeInBinary() {
        return chromosomeInBinary;
    }

    public String getChromosomeAsString() {
        String chromosomeAsString = "";
        for (int j : chromosomeInBinary) {
            chromosomeAsString = String.valueOf(j).concat(chromosomeAsString);
        }
        return chromosomeAsString;
    }

    public void setChromosomeInBinary(int[] chromosomeInBinary) {
        this.chromosomeInBinary = chromosomeInBinary;
    }

    public void generateInitialChromosome() {
        for(int i = 0; i < this.length; i++) {
            chromosomeInBinary[i] = (int) Math.round(Math.random());
        }
    }

    public double getValue(){
        return value;
    }

    public double generateValue() {
        double temp =  (domain[1] - domain[0]) / (Math.pow(2, length) - 1);
        double answer = 0;
        for(int i = length - 1; i >= 0; i--) {
            if(chromosomeInBinary[i] == 1) {
                answer += temp;
            }
            temp *= 2;
        }
        return answer + domain[0];
    }
}

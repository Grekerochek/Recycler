package com.alexander.recycler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorkerGenerator {
    private static List<String> maleNames = new ArrayList<>(Arrays.asList("John", "Bill", "Bob", "Oliver", "Jack", "Harry", "George", "William", "Henry"));
    private static List<String> femaleNames = new ArrayList<>(Arrays.asList("Anna", "Emma", "Sophie", "Jessica", "Scarlett", "Molly", "Lucy", "Megan", "Mary"));
    private static List<String> surnames = new ArrayList<>(Arrays.asList("Green", "Smith", "Taylor", "Brown", "Wilson", "Walker", "White", "Jackson", "Wood"));
    private static List<Integer> femalePhoto = new ArrayList<>(Arrays.asList(R.drawable.ic_female_black, R.drawable.ic_female_green, R.drawable.ic_female_red, R.drawable.ic_female_magnetta));
    private static List<Integer> malePhoto = new ArrayList<>(Arrays.asList(R.drawable.ic_male_black, R.drawable.ic_male_green, R.drawable.ic_male_red, R.drawable.ic_male_magnetta));
    private static List<String> positions = new ArrayList<>(Arrays.asList("Android", "iOs", "Web", "Designer"));
    private static List<Integer> types = new ArrayList<>(Arrays.asList(0,1,2,3));

    public static Worker generateWorker() {

        Worker worker = new Worker();
        Random random = new Random();

        int index = random.nextInt(surnames.size());
        int indexPhoto = random.nextInt(malePhoto.size());
        int indexType = random.nextInt(types.size());
        int indexPosition = random.nextInt(positions.size());

        Integer randomType = types.get(indexType);
        String randomSurname = surnames.get(index);
        String randomName = index % 2 == 0 ? maleNames.get(index): femaleNames.get(index);
        Integer randomPhoto = index % 2 == 0 ? malePhoto.get(indexPhoto): femalePhoto.get(indexPhoto);
        String randomPosition = positions.get(indexPosition);

        worker.setPhoto(randomPhoto);
        worker.setName(randomName + " " + randomSurname);
        worker.setType(randomType);
        worker.setAge(Integer.toString(20 + random.nextInt(10)));
        worker.setPosition(randomPosition);
        worker.setStrings(surnames);

        return worker;
    }

    public static List<Worker> generateWorkers(int workersCount) {
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < workersCount; i++) {
            workers.add(generateWorker());
        }
        return workers;
    }
}

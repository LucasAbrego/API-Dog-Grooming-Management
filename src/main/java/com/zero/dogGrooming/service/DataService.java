package com.zero.dogGrooming.service;

import com.zero.dogGrooming.enums.PetFurTypeEnum;
import com.zero.dogGrooming.enums.PetSizeEnum;
import com.zero.dogGrooming.enums.TurnServiceTypeEnum;
import com.zero.dogGrooming.enums.TurnStatusEnum;
import com.zero.dogGrooming.exception.turn.TurnSaveException;
import com.zero.dogGrooming.model.Client;
import com.zero.dogGrooming.model.Pet;
import com.zero.dogGrooming.model.Turn;
import com.zero.dogGrooming.repository.ClientRepository;
import com.zero.dogGrooming.repository.PetRepository;
import com.zero.dogGrooming.repository.TurnRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


@Service
public class DataService implements IDataService {
    //Constantes para calcular precio
    private static final double BASE_PRICE_BATH = 20.0;
    private static final double BASE_PRICE_HAIRCUT = 30.0;
    private static final double SIZE_FACTOR_SMALL = 0.8;
    private static final double SIZE_FACTOR_MEDIUM = 1.0;
    private static final double SIZE_FACTOR_LARGE = 1.2;
    private static final double FUR_FACTOR_SHORT = 1.0;
    private static final double FUR_FACTOR_CURLY = 1.3;
    private static final double FUR_FACTOR_LONG = 1.5;
    @Autowired
    private ClientRepository repoClient;
    @Autowired
    private PetRepository repoPet;
    @Autowired
    private TurnRepository repoTurn;
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public String resetDatabase() {
        try {
            this.deleteAllData();
            System.out.println(registerClients());
            System.out.println(registerPets());
            System.out.println(registerTurns());
            return "Data reset successfully.";
        } catch (Exception e) {
            throw new RuntimeException("Error resetting the database: " + e.getMessage());
        }
    }

    private String registerClients() {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("src/main/resources/data/clients.json")));
            JSONArray jsonArray = new JSONArray(jsonContent);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Client client = new Client();
                client.setDni(jsonObject.getString("dni"));
                client.setName(jsonObject.getString("name"));
                client.setLastname(jsonObject.getString("lastname"));
                client.setAddress(jsonObject.getString("address"));
                client.setPhoneNumber(jsonObject.getString("phoneNumber"));
                client.setActive(jsonObject.getBoolean("active"));
                repoClient.save(client);
            }
        } catch (Exception e) {
            throw new TurnSaveException("Error resetting client data");
        }
        return "Successful clients upload";
    }

    private String registerPets() {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("src/main/resources/data/pets.json")));
            JSONArray jsonArray = new JSONArray(jsonContent);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Pet pet = new Pet();
                pet.setName(jsonObject.getString("name"));
                pet.setAge(jsonObject.getLong("age"));
                pet.setBreed(jsonObject.getString("breed"));
                pet.setSize(PetSizeEnum.valueOf(jsonObject.getString("size").toUpperCase()));
                pet.setFurType(PetFurTypeEnum.valueOf(jsonObject.getString("furType").toUpperCase()));
                pet.setConsiderations(jsonObject.getString("considerations"));
                pet.setOwner(repoClient.findById(jsonObject.getString("ownerDni")).orElse(null));
                pet.setActive(jsonObject.getBoolean("active"));
                repoPet.save(pet);
            }

        } catch (Exception e) {
            throw new TurnSaveException("Error resetting pet data");
        }
        return "Successful pets upload";
    }


    private String registerTurns() {
        try {
            List<Pet> listPet = repoPet.findAll();
            for (int i = 0; i <= 50; i++) {
                Pet pet = getRandomPet(listPet);
                Turn turn = new Turn();
                turn.setPet(pet);
                turn.setDateAndTime(this.generateRandomDateTime());
                turn.setStatus(this.generateRandomStatus());
                turn.setPaid(turn.getStatus() == TurnStatusEnum.PAID);
                turn.setServiceType(this.generateRandomService(pet.getFurType()));
                turn.setPrice(this.calculatePrice(pet.getSize(), pet.getFurType(), turn.getServiceType()));
                Turn turnSaved = repoTurn.save(turn);
            }
        } catch (Exception e) {
            throw new TurnSaveException("Error resetting turn data");
        }
        return "Successful appointments upload";
    }

    public Pet getRandomPet(List<Pet> listPets) {
        Random random = new Random();
        return listPets.get(random.nextInt(listPets.size()));
    }

    public TurnStatusEnum generateRandomStatus() {
        return TurnStatusEnum.values()[new Random().nextInt(TurnStatusEnum.values().length)];
    }

    public TurnServiceTypeEnum generateRandomService(PetFurTypeEnum furType) {
        if (furType == PetFurTypeEnum.SHORT) {
            return TurnServiceTypeEnum.BATH;
        } else {
            return (new Random().nextInt(2) == 0) ? TurnServiceTypeEnum.BATH : TurnServiceTypeEnum.HAIRCUT;
        }
    }

    private LocalDateTime generateRandomDateTime() {
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();

        int randomDays = random.nextInt(91);
        int randomHours = random.nextInt(24);
        int randomMinutes = random.nextInt(60);
        int randomSeconds = random.nextInt(60);

        Duration randomDuration = Duration.ofDays(randomDays)
                .plusHours(randomHours)
                .plusMinutes(randomMinutes)
                .plusSeconds(randomSeconds);

        return now.plus(randomDuration);
    }

    private double calculatePrice(PetSizeEnum size, PetFurTypeEnum furType, TurnServiceTypeEnum serviceType) {
        double basePrice = 0.0;
        double sizeFactor = 0.0;
        double furLengthFactor = 0.0;

        if (serviceType == TurnServiceTypeEnum.BATH) {
            basePrice = BASE_PRICE_BATH;
        } else if (serviceType == TurnServiceTypeEnum.HAIRCUT) {
            basePrice = BASE_PRICE_HAIRCUT;
        }

        if (size == PetSizeEnum.SMALL) {
            sizeFactor = SIZE_FACTOR_SMALL;
        } else if (size == PetSizeEnum.MEDIUM) {
            sizeFactor = SIZE_FACTOR_MEDIUM;
        } else {
            sizeFactor = SIZE_FACTOR_LARGE;
        }

        if (furType == PetFurTypeEnum.SHORT) {
            furLengthFactor = FUR_FACTOR_SHORT;
        } else if (furType == PetFurTypeEnum.CURLY) {
            furLengthFactor = FUR_FACTOR_CURLY;
        } else {
            furLengthFactor = FUR_FACTOR_LONG;
        }
        double totalPrice = basePrice * sizeFactor * furLengthFactor;
        return Math.round(totalPrice * 100.0) / 100.0;
    }


    public String deleteAllData() {
        try {
            repoTurn.deleteAll();
            repoPet.deleteAll();
            repoClient.deleteAll();
            return "Data deleted successfully.";
        } catch (Exception e) {
            return "Error deleting data: " + e.getMessage();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Random;

/**
 *
 * @author stefito
 */
public final class Generator {

    private final Random rand;

    private final String[] surnames;
    private final String[] names;
    private final String[] specialties;
    private final String[] usernames;
    private final String[] passwords;

    private final String[] streets;
    private final String[] cities;
    private final int[] numbers;

    private final char[] vigilTypes;

    private final String[] diseasesNames;

    private final String[] examinationTypes;

    private final String[] medicineNames;
    private final String[] medicineTypes;
    private final int[] medicineASCs;

    private final String[] days;
    private final String[] months;

    private final Patient[] dummyPatients;
    private final Doctor[] dummyDoctors;
    private final Nurse[] dummyNurses;
    private final AdminStaff[] dummyAdmins;
    private final InfoSysUser[] dummyInfoSysUsers;
    private final Vigil[] dummyVigils;
    private final Disease[] dummyDiseases;
    private final ChronicDisease[] dummyChronicDiseases;
    private final Medicine[] dummyMedicines;
    private final Examination[] dummyExaminations;
    private final Hospitalization[] dummyHospitalizations;
    private final Visit[] dummyVisits;

    private int get8DigitNum() {
        return this.rand.nextInt(99999999);
    }

    private void setDummies() {
        int j = 0;

        for (int i = 0; i < 5; i++) {
            this.dummyPatients[i] = new Patient(this.surnames[j], this.names[j], get8DigitNum(),
                    "Κανένας", "69" + String.valueOf(get8DigitNum()), this.streets[i], this.cities[i], this.numbers[i]);
            j++;
        }

        for (int i = 0; i < 5; i++) {
            this.dummyDoctors[i] = new Doctor(this.surnames[j], this.names[j], this.specialties[i]);
            j++;
        }

        for (int i = 0; i < 5; i++) {
            this.dummyNurses[i] = new Nurse(this.surnames[j], this.names[j]);
            j++;
        }

        for (int i = 0; i < 5; i++) {
            this.dummyAdmins[i] = new AdminStaff(this.surnames[j], this.names[j]);
            j++;
        }

        for (int i = 0; i < 5; i++) {
            this.dummyInfoSysUsers[i] = new InfoSysUser(this.usernames[i], this.passwords[i], this.surnames[(6 * i) % 20], this.names[(6 * i) % 20]);
        }

        this.dummyVigils[0] = new Vigil(this.surnames[5], this.names[5], this.vigilTypes[0]);
        this.dummyVigils[1] = new Vigil(this.surnames[19], this.names[19], this.vigilTypes[1]);
        for (int i = 0; i < 3; i++) {
            this.dummyVigils[i + 2] = new Vigil(this.surnames[12 + i], this.names[12 + i], this.vigilTypes[i]);
        }

        for (int i = 0; i < 5; i++) {
            this.dummyDiseases[i] = new Disease(this.diseasesNames[i]);
        }

        for (int i = 0; i < 5; i++) {
            this.dummyChronicDiseases[i] = new ChronicDisease(this.surnames[0], this.names[0], this.diseasesNames[i]);
        }

        for (int i = 0; i < 5; i++) {
            this.dummyMedicines[i] = new Medicine(this.medicineNames[i], this.medicineTypes[i], this.medicineASCs[i], this.diseasesNames[i]);
        }

        for (int i = 0; i < 5; i++) {
            this.dummyExaminations[i] = new Examination(this.examinationTypes[i]);
        }

        for (int i = 0; i < 5; i++) {
            this.dummyHospitalizations[i] = new Hospitalization(i, this.surnames[i],
                    this.names[i], this.days[(i * 2) % 7], this.months[(i * 2) % 12],
                    2020, this.days[(i * 5) % 7], this.months[(i * 5) % 12], 2021);
        }

        for (int i = 0; i < 5; i++) {
            this.dummyVisits[i] = new Visit(i, this.surnames[i], this.names[i], this.days[(i * 2) % 7], this.months[(i * 2) % 12],
                    2020, this.examinationTypes[i], this.medicineNames[i], i);
        }
    }

    public Generator() {
        rand = new Random();

        this.surnames = new String[]{"Bushy", "Venezia", "Clever", "Evil", "Frau", "Maid", "Old", "Queen", "Princess", "Snow",
            "Ali", "Prince", "Frog", "King", "Prigio", "First", "Second", "Third", "Fourth", "Fifth"};

        this.names = new String[]{"Bride", "Bella", "Elsie", "Queen", "Holle", "Maleen", "Witch", "Bee", "Rosette", "White",
            "Baba", "Charming", "Prince", "ThrushBeard", "Prince", "Dwarf", "Dwarf", "Dwarf", "Dwarf", "Dwarf"};

        this.specialties = new String[]{"Ειδικευόμενος", "Διευθυντής", "Επίκουρος_Διευθυντής", "Επιμελητής_Α", "Επιμελητής_Β"};

        this.usernames = new String[]{"PIPIS", "PIPOS", "PIP", "POP", "PAP"};
        this.passwords = new String[]{"PIPIS111", "PIPOS112", "PIP121", "POP122", "PAP211"};

        this.streets = new String[]{"Campus", "Coach", "Steam", "Long", "Medieval"};
        this.cities = new String[]{"Streuhstin", "Chicaster", "Vluldale", "Zostin", "Vona"};
        this.numbers = new int[]{1, 23, 3, 12, 9};

        this.vigilTypes = new char[]{'Μ', 'Χ', 'Ε'};

        this.diseasesNames = new String[]{"Κάταγμα", "Στηθάχγη", "Γρίπη", "Γαστρεντερίτιδα", "COVID"};

        this.examinationTypes = new String[]{"Ακτινογραφία", "Εξέταση_Αίματος", "τεστ_COVID", "Μαγνητική", "Καρδιογράφημα"};

        this.medicineNames = new String[]{"ABRAXANE", "ABSEAMED", "ABSEAMED_40.000IU", "ACCOFIL", "ACLASTA"};
        this.medicineTypes = new String[]{"Νοσοκομειακό", "Κανονικό", "Κανονικό", "Κανονικό", "Νοσοκομειακό"};
        this.medicineASCs = new int[]{10, 88, 50, 33, 99};

        this.days = new String[]{"Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκεύη", "Σάββατο", "Κυριακή"};
        this.months = new String[]{"Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάιος", "Ιούνιος", "Ιούλιος", "Αύγουστος", "Σεπτέμβρης",
            "Οκτώβρης", "Νοέμβρης", "Δεκέμβρης"};

        this.dummyPatients = new Patient[5];
        this.dummyDoctors = new Doctor[5];
        this.dummyNurses = new Nurse[5];
        this.dummyAdmins = new AdminStaff[5];
        this.dummyInfoSysUsers = new InfoSysUser[5];
        this.dummyDiseases = new Disease[5];
        this.dummyMedicines = new Medicine[5];
        this.dummyVigils = new Vigil[5];
        this.dummyChronicDiseases = new ChronicDisease[5];
        this.dummyExaminations = new Examination[5];
        this.dummyHospitalizations = new Hospitalization[5];
        this.dummyVisits = new Visit[5];

        setDummies();

    }

    public Patient[] getDummyPatients() {
        return this.dummyPatients;
    }

    public Doctor[] getDummyDoctors() {
        return this.dummyDoctors;
    }

    public Nurse[] getDummyNurses() {
        return this.dummyNurses;
    }

    public AdminStaff[] getDummyAdmins() {
        return this.dummyAdmins;
    }

    public Disease[] getDummyDiseases() {
        return this.dummyDiseases;
    }

    public Medicine[] getDummyMedicines() {
        return this.dummyMedicines;
    }

    public InfoSysUser[] getDummyInfoSysUsers() {
        return this.dummyInfoSysUsers;
    }

    public Vigil[] getDummyVigils() {
        return this.dummyVigils;
    }

    public ChronicDisease[] getDummyChronicDiseases() {
        return this.dummyChronicDiseases;
    }

    public Examination[] getDummyExaminations() {
        return this.dummyExaminations;
    }

    public Hospitalization[] getDummyHospitalizations() {
        return this.dummyHospitalizations;
    }

    public Visit[] getDummyVisits() {
        return this.dummyVisits;
    }

}

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
    private final int[] AMKAs;
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

    private final String[] symptoms;

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
            this.dummyPatients[i] = new Patient(this.surnames[j], this.names[j], this.AMKAs[i],
                    "Κανένας", "69" + String.valueOf(get8DigitNum()), this.streets[i], this.cities[i], this.numbers[i]);

            this.dummyInfoSysUsers[j] = new InfoSysUser(this.usernames[j], this.passwords[i], -1, this.dummyPatients[i].getAMKA());
            j++;
        }

        for (int i = 0; i < 5; i++) {
            this.dummyDoctors[i] = new Doctor(j, this.surnames[j], this.names[j], this.specialties[i]);
            this.dummyInfoSysUsers[j] = new InfoSysUser(this.usernames[j], this.passwords[i], this.dummyDoctors[i].getID(), -1);
            j++;
        }

        for (int i = 0; i < 5; i++) {
            this.dummyNurses[i] = new Nurse(j, this.surnames[j], this.names[j]);
            this.dummyInfoSysUsers[j] = new InfoSysUser(this.usernames[j], this.passwords[i], this.dummyNurses[i].getID(), -1);
            j++;
        }

        for (int i = 0; i < 5; i++) {
            this.dummyAdmins[i] = new AdminStaff(j, this.surnames[j], this.names[j]);
            this.dummyInfoSysUsers[j] = new InfoSysUser(this.usernames[j], this.passwords[i], this.dummyAdmins[i].getID(), -1);
            j++;
        }

        this.dummyVigils[0] = new Vigil(this.dummyDoctors[0].getID(), this.vigilTypes[0]);
        this.dummyVigils[1] = new Vigil(this.dummyDoctors[1].getID(), this.vigilTypes[1]);
        this.dummyVigils[2] = new Vigil(this.dummyDoctors[2].getID(), this.vigilTypes[2]);
        this.dummyVigils[3] = new Vigil(this.dummyNurses[0].getID(), this.vigilTypes[0]);
        this.dummyVigils[4] = new Vigil(this.dummyAdmins[0].getID(), this.vigilTypes[0]);

        for (int i = 0; i < 5; i++) {
            this.dummyDiseases[i] = new Disease(this.diseasesNames[i]);
        }

        for (int i = 0; i < 5; i++) {
            this.dummyChronicDiseases[i] = new ChronicDisease(this.dummyPatients[i].getAMKA(), this.dummyDiseases[i].getName());
        }

        for (int i = 0; i < 5; i++) {
            this.dummyMedicines[i] = new Medicine(this.medicineNames[i], this.medicineTypes[i], this.medicineASCs[i], this.dummyDiseases[i].getName());
        }

        for (int i = 0; i < 5; i++) {
            this.dummyExaminations[i] = new Examination(this.examinationTypes[i]);
        }

        for (int i = 0; i < 5; i++) {
            this.dummyHospitalizations[i] = new Hospitalization(i, this.dummyPatients[i].getAMKA(), this.days[(i * 2) % 7], this.months[(i * 2) % 12],
                    2020, this.days[(i * 5) % 7], this.months[(i * 5) % 12], 2021);
        }

        for (int i = 0; i < 5; i++) {
            this.dummyVisits[i] = new Visit(i, this.dummyPatients[i].getAMKA(), this.symptoms[i], this.days[(i * 2) % 7], this.months[(i * 2) % 12],
                    2020, this.dummyDoctors[i].getID(), this.dummyExaminations[i].getType(), this.dummyMedicines[i].getName(),
                    this.dummyNurses[i].getID(), this.dummyDiseases[i].getName(), this.dummyDoctors[i].getID(),
                    this.dummyHospitalizations[i].getID());
        }
    }

    public Generator() {
        rand = new Random();

        this.surnames = new String[]{"Bushy", "Venezia", "Clever", "Evil", "Frau", "Maid", "Old", "Queen", "Princess", "Snow",
            "Ali", "Prince", "Frog", "King", "Prigio", "First", "Second", "Third", "Fourth", "Fifth"};

        this.names = new String[]{"Bride", "Bella", "Elsie", "Queen", "Holle", "Maleen", "Witch", "Bee", "Rosette", "White",
            "Baba", "Charming", "Prince", "ThrushBeard", "Prince", "Dwarf", "Dwarf", "Dwarf", "Dwarf", "Dwarf"};

        this.AMKAs = new int[]{123456, 123457, 123458, 123459, 123450};

        this.specialties = new String[]{"Ειδικευόμενος", "Διευθυντής", "Επίκουρος_Διευθυντής", "Επιμελητής_Α", "Επιμελητής_Β"};

        this.usernames = new String[]{"PIPIS", "PIPOS", "PIP", "POP", "PAP", "AA", "sSS", "QQQ", "AD", "sdw",
            "PIPI", "PIPO", "P", "PP", "PA", "A", "sS", "QQ", "A", "sw"};
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

        this.symptoms = new String[]{"Στομαχόπονος, διάρροια", "Πονοκέφαλος", "Πυρετός", "Βήχας", "Κόπωση"};

        this.dummyPatients = new Patient[5];
        this.dummyDoctors = new Doctor[5];
        this.dummyNurses = new Nurse[5];
        this.dummyAdmins = new AdminStaff[5];
        this.dummyInfoSysUsers = new InfoSysUser[20];
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

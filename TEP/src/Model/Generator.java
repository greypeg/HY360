/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;
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

    private final String[] streets;
    private final String[] cities;
    private final int[] numbers;
    private final String[] symptoms;

    private final char[] vigilTypes;

    private final String[] diseasesNames;

    private final String[] examinationTypes;

    private final String[] passwords;

    private final String[] medicineNames;
    private final String[] medicineTypes;
    private final int[] medicineASCs;

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

    private LocalDateTime now;

    private int get8DigitNum() {
        return this.rand.nextInt(99999999);
    }

    private void setDummies() {
        int j = 0;

        for (int i = 0; i < 5; i++) {
            this.dummyPatients[i] = new Patient(this.surnames[j], this.names[j], this.AMKAs[i],
                    "Κανένας", "69" + String.valueOf(get8DigitNum()), this.streets[i], this.cities[i], this.numbers[i]);

            this.dummyInfoSysUsers[j] = new InfoSysUser(-1, this.dummyPatients[i].getAMKA(), this.passwords[j]);
            j++;
        }

        for (int i = 0; i < 5; i++) {
            this.dummyDoctors[i] = new Doctor(j, this.surnames[j], this.names[j], this.specialties[i]);
            this.dummyInfoSysUsers[j] = new InfoSysUser(this.dummyDoctors[i].getID(), -1, this.passwords[j]);
            j++;
        }

        for (int i = 0; i < 5; i++) {
            this.dummyNurses[i] = new Nurse(j, this.surnames[j], this.names[j]);
            this.dummyInfoSysUsers[j] = new InfoSysUser(this.dummyNurses[i].getID(), -1, this.passwords[j]);
            j++;
        }

        for (int i = 0; i < 5; i++) {
            this.dummyAdmins[i] = new AdminStaff(j, this.surnames[j], this.names[j]);
            this.dummyInfoSysUsers[j] = new InfoSysUser(this.dummyAdmins[i].getID(), -1, this.passwords[j]);
            j++;
        }

        this.dummyVigils[0] = new Vigil(0, this.dummyDoctors[0].getID(), this.vigilTypes[0]);
        this.dummyVigils[1] = new Vigil(0, this.dummyDoctors[1].getID(), this.vigilTypes[1]);
        this.dummyVigils[2] = new Vigil(0, this.dummyNurses[1].getID(), this.vigilTypes[2]);
        this.dummyVigils[3] = new Vigil(0, this.dummyNurses[0].getID(), this.vigilTypes[0]);
        this.dummyVigils[4] = new Vigil(0, this.dummyAdmins[0].getID(), this.vigilTypes[0]);
        this.dummyVigils[5] = new Vigil(1, this.dummyDoctors[2].getID(), this.vigilTypes[0]);
        this.dummyVigils[6] = new Vigil(1, this.dummyDoctors[3].getID(), this.vigilTypes[1]);
        this.dummyVigils[7] = new Vigil(1, this.dummyNurses[2].getID(), this.vigilTypes[2]);
        this.dummyVigils[8] = new Vigil(1, this.dummyNurses[3].getID(), this.vigilTypes[0]);
        this.dummyVigils[9] = new Vigil(1, this.dummyAdmins[1].getID(), this.vigilTypes[0]);

        for (int i = 0; i < 5; i++) {
            this.dummyDiseases[i] = new Disease(this.diseasesNames[i]);
        }

        for (int i = 0; i < 5; i++) {
            this.dummyChronicDiseases[i] = new ChronicDisease(this.dummyPatients[i].getAMKA(), this.dummyDiseases[i].getName());
        }
        this.dummyChronicDiseases[5] = new ChronicDisease(this.dummyPatients[4].getAMKA(), this.dummyDiseases[2].getName());

        for (int i = 0; i < 5; i++) {
            this.dummyMedicines[i] = new Medicine(this.medicineNames[i], this.medicineTypes[i], this.medicineASCs[i], this.dummyDiseases[i].getName());
        }

        for (int i = 0; i < 5; i++) {
            this.dummyExaminations[i] = new Examination(this.examinationTypes[i]);
        }
        int i;

        for (i = 0; i < 3; i++) {
            this.dummyHospitalizations[i] = new Hospitalization(i, this.dummyPatients[i].getAMKA(), this.now.getDayOfMonth() - 1,
                    this.now.getMonthValue(), this.now.getYear(), this.now.getDayOfMonth() + i, this.now.getMonthValue() + i,
                    this.now.getYear());
        }

        for (; i < 5; i++) {
            this.dummyHospitalizations[i] = new Hospitalization(i, this.dummyPatients[i].getAMKA(), this.now.getDayOfMonth(),
                    this.now.getMonthValue(), this.now.getYear(), this.now.getDayOfMonth() + i, this.now.getMonthValue() + i,
                    this.now.getYear());
        }

        for (i = 0; i < 3; i++) {
            this.dummyVisits[i] = new Visit(i, this.dummyPatients[i].getAMKA(), this.symptoms[i], this.now.getDayOfMonth() - 1,
                    this.now.getMonthValue(), this.now.getYear(), 0, this.dummyDoctors[i % 2].getID(),
                    this.dummyExaminations[i].getType(), this.dummyMedicines[i].getName(), this.dummyNurses[i % 2].getID(),
                    this.dummyDiseases[i].getName(), this.dummyDoctors[i % 2].getID(), this.dummyHospitalizations[i].getID());
        }

        for (; i < 5; ++i) {
            this.dummyVisits[i] = new Visit(i, this.dummyPatients[i].getAMKA(), this.symptoms[i], this.now.getDayOfMonth(),
                    this.now.getMonthValue(), this.now.getYear(), 1, this.dummyDoctors[i - 1].getID(),
                    this.dummyExaminations[i].getType(), this.dummyMedicines[i].getName(), this.dummyNurses[i - 1].getID(),
                    this.dummyDiseases[i].getName(), this.dummyDoctors[i - 2].getID(), this.dummyHospitalizations[i].getID());
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

        this.streets = new String[]{"Campus", "Coach", "Steam", "Long", "Medieval"};
        this.cities = new String[]{"Streuhstin", "Chicaster", "Vluldale", "Zostin", "Vona"};
        this.numbers = new int[]{1, 23, 3, 12, 9};

        this.symptoms = new String[]{"Στομαχόπονος, διάρροια", "Πονοκέφαλος", "Πυρετός", "Βήχας", "Κόπωση"};

        this.vigilTypes = new char[]{'Μ', 'Χ', 'Ε'};

        this.diseasesNames = new String[]{"Κάταγμα", "Στηθάχγη", "Γρίπη", "Γαστρεντερίτιδα", "COVID"};

        this.examinationTypes = new String[]{"Ακτινογραφία", "Εξέταση_Αίματος", "τεστ_COVID", "Μαγνητική", "Καρδιογράφημα"};

        this.medicineNames = new String[]{"ABRAXANE", "ABSEAMED", "ABSEAMED_40.000IU", "ACCOFIL", "ACLASTA"};
        this.medicineTypes = new String[]{"Νοσοκομειακό", "Κανονικό", "Κανονικό", "Κανονικό", "Νοσοκομειακό"};
        this.medicineASCs = new int[]{10, 88, 50, 33, 99};

        this.passwords = new String[]{"asds", "qssa", "xcxv", "sdas", "sdqqa", "242", "s0", "Asszc9", "sdxz", "sd23",
            "121243", "243", "234", "46", "9876", "wwww", "scxmcmx", "sdac", "0000", "xixixii"};

        now = LocalDateTime.now();

        this.dummyPatients = new Patient[5];
        this.dummyDoctors = new Doctor[5];
        this.dummyNurses = new Nurse[5];
        this.dummyAdmins = new AdminStaff[5];
        this.dummyInfoSysUsers = new InfoSysUser[20];
        this.dummyDiseases = new Disease[5];
        this.dummyMedicines = new Medicine[5];
        this.dummyVigils = new Vigil[10];
        this.dummyChronicDiseases = new ChronicDisease[6];
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

    public LocalDateTime getNow() {
        return this.now;
    }

}

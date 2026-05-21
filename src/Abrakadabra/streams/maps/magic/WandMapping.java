package Abrakadabra.streams.maps.magic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ============================================================
 *  CVIČENÍ: Kouzelné hůlky – Streams, Map a Collectors
 * ============================================================
 *
 * Máš k dispozici dvě CSV tabulky:
 *   - data/kouzelne_dilny.csv   (20 dílen)
 *   - data/kouzelne_hulky.csv   (100 hůlek)
 *
 * Tvým úkolem je doplnit tělo metody main() pomocí Stream API,
 * Collectors.groupingBy() a dalších Collectors tak, aby program
 * vypsal požadované výsledky.
 *
 * Vzorové výstupy jsou u každého úkolu napsány jako komentář.
 * Hotové pomocné metody (parseLine, cenováKategorie, …) NEMĚŇ –
 * jen je použij ve svých streamech.
 */
public class WandMapping {

    // ----------------------------------------------------------------
    //  PARSOVÁNÍ
    // ----------------------------------------------------------------

    /** Naparsuje jeden řádek z kouzelne_hulky.csv na objekt Wand. */
    public static Wand parseWandLine(String[] tokens) {
        if (tokens.length != 9) {
            throw new IllegalArgumentException("Nesprávný počet sloupců – očekáváno 9, dostáno " + tokens.length);
        }
        return new Wand(
                Integer.parseInt(tokens[0]),          // hulka_id
                Integer.parseInt(tokens[1]),          // dilna_id
                LocalDate.parse(tokens[2]),           // datum_vyroby  (formát yyyy-MM-dd)
                tokens[3],                            // drevo
                tokens[4],                            // jadro
                Integer.parseInt(tokens[5]),          // delka_cm
                tokens[6],                            // ohebnost
                Integer.parseInt(tokens[7]),          // cena_zlate
                tokens[8].trim().equalsIgnoreCase("ano") // prodana
        );
    }

    /** Naparsuje jeden řádek z kouzelne_dilny.csv na objekt Workshop. */
    public static Workshop parseWorkshopLine(String[] tokens) {
        if (tokens.length != 6) {
            throw new IllegalArgumentException("Nesprávný počet sloupců – očekáváno 6, dostáno " + tokens.length);
        }
        return new Workshop(
                Integer.parseInt(tokens[0]),  // id
                tokens[1],                    // nazev
                tokens[2],                    // mesto
                Integer.parseInt(tokens[3]),  // zalozeni
                tokens[4],                    // majitel
                tokens[5]                     // specializace
        );
    }

    // ----------------------------------------------------------------
    //  POMOCNÉ METODY  –  použij je ve svých Collectors
    // ----------------------------------------------------------------

    /**
     * Vrátí cenovou kategorii hůlky:
     *   "Levná"    … cena < 500
     *   "Střední"  … cena 500–999
     *   "Drahá"    … cena >= 1000
     */
    public static String cenovaKategorie(Wand w) {
        if (w.getCenaZlate() < 500)  return "Levná";
        if (w.getCenaZlate() < 1000) return "Střední";
        return "Drahá";
    }

    /**
     * Vrátí věkovou kategorii hůlky (počítáno od roku výroby):
     *   "Antická"    … vyrobena před rokem 1500
     *   "Historická" … 1500–1799
     *   "Moderní"    … 1800 a novější
     */
    public static String vekovaKategorie(Wand w) {
        int rok = w.getDatumVyroby().getYear();
        if (rok < 1500) return "Antická";
        if (rok < 1800) return "Historická";
        return "Moderní";
    }

    /**
     * Vrátí délkovou kategorii hůlky:
     *   "Krátká"  … délka <= 28 cm
     *   "Střední" … 29–32 cm
     *   "Dlouhá"  … >= 33 cm
     */
    public static String delkovaKategorie(Wand w) {
        if (w.getDelkaCm() <= 28) return "Krátká";
        if (w.getDelkaCm() <= 32) return "Střední";
        return "Dlouhá";
    }

    // ----------------------------------------------------------------
    //  MAIN  –  ZDE DOPLŇUJ ÚKOLY
    // ----------------------------------------------------------------

    public static void main(String[] args) {
        String wandsPath     = "data/magic/kouzelne_hulky.csv";
        String workshopsPath = "data/magic/kouzelne_dilny.csv";

        try {
            // Načtení hůlek
            List<Wand> hulky = Files.lines(Path.of(wandsPath))
                    .skip(1)
                    .map(line -> line.trim().split(","))
                    .map(WandMapping::parseWandLine)
                    .toList();

            // Načtení dílen
            List<Workshop> dilny = Files.lines(Path.of(workshopsPath))
                    .skip(1)
                    .map(line -> line.trim().split(","))
                    .map(WandMapping::parseWorkshopLine)
                    .toList();

            // ============================================================
            //  ÚKOL 1 – Seskupení hůlek podle dřeva
            // ============================================================
            // Vytvoř mapu: dřevo -> seznam hůlek z tohoto dřeva.
            // Pro každé dřevo vypiš jeho název a počet hůlek.
            //
            // Očekávaný výstup (pořadí se může lišit):
            //   Dub          --> 13 hůlek
            //   Tis          --> 11 hůlek
            //   Habr         --> 12 hůlek
            //   ...
            //
            System.out.println("=== ÚKOL 1: Počet hůlek podle dřeva ===");

            // TODO: Map<String, List<Wand>> podleDreva = ...
            // TODO: vypiš pomocí forEach

            Map<String, List<Wand>> byWood = hulky.stream()
                    .collect(Collectors.groupingBy(Wand::getDrevo));

            byWood.forEach(
                    (drevo, h) -> {
                        System.out.println(drevo + ": " + h.size());
                    }
            );


            // ============================================================
            //  ÚKOL 2 – Cenové kategorie (procentuálně)
            // ============================================================
            // Seskup hůlky podle cenové kategorie (použij metodu cenovaKategorie).
            // Vypiš každou kategorii a její procentuální zastoupení.
            //
            // Očekávaný výstup:
            //   Levná    --> 30,00 %
            //   Střední  --> 54,00 %
            //   Drahá    --> 16,00 %
            //
            System.out.println("\n=== ÚKOL 2: Cenové kategorie (%) ===");

            // TODO: Map<String, Long> podleCeny = ...
            // TODO: vypiš procentuálně

            Map<String, Long> byPrice = hulky.stream()
                    .collect(Collectors.groupingBy(WandMapping::cenovaKategorie, Collectors.counting()));

            byPrice.forEach(
                    (kategorie, pocet) -> {
                        System.out.println(kategorie + " ---> " + ((pocet * 100) / hulky.size()) + "%");
                    }
            );


            // ============================================================
            //  ÚKOL 3 – Průměrná cena podle jádra
            // ============================================================
            // Pro každé jádro (jadro) spočítej průměrnou cenu hůlky.
            // Použij Collectors.averagingInt().
            // Vypiš seřazené podle průměrné ceny sestupně.
            //
            // Očekávaný výstup (zaokrouhleno na 1 desetinné místo):
            //   Temný krystal    avg: 1400,0 zlatých
            //   Měsíční krystal  avg: 1032,5 zlatých
            //   ...
            //
            System.out.println("\n=== ÚKOL 3: Průměrná cena podle jádra ===");

            // TODO: Map<String, Double> prumernaCena = ...
            // TODO: vypiš seřazené sestupně

            Map<String, Double> byCore = hulky.stream()
                    .collect(Collectors.groupingBy(Wand::getJadro, Collectors.averagingInt(Wand::getCenaZlate)));

            byCore.forEach(
                    (jadro, avg) -> {
                        System.out.println(jadro + ": " + avg + " zlatých");
                    }
            );


            // ============================================================
            //  ÚKOL 4 – Věkové kategorie a prodejnost
            // ============================================================
            // Seskup hůlky podle věkové kategorie (použij metodu vekovaKategorie).
            // Pro každou kategorii spočítej, kolik hůlek bylo PRODÁNO (prodana == true).
            // Nápověda: Collectors.summingInt(w -> w.isProdana() ? 1 : 0)
            //
            // Očekávaný výstup:
            //   Antická     --> 12 prodaných
            //   Historická  --> 31 prodaných
            //   Moderní     --> 17 prodaných
            //
            System.out.println("\n=== ÚKOL 4: Prodané hůlky podle věkové kategorie ===");

            // TODO: Map<String, Integer> prodanePodleVeku = ...
            // TODO: vypiš pomocí forEach

            Map<String, Integer> byAge = hulky.stream()
                    .collect(Collectors.groupingBy(WandMapping::vekovaKategorie, Collectors.summingInt(w -> w.isProdana() ? 1 : 0)));

            byAge.forEach(
                    (age, pocet) -> {
                        System.out.println(age + " --> " + pocet + " prodaných");
                    }
            );

            // ============================================================
            //  ÚKOL 5 – Nejvyšší cena v každé délkové kategorii
            // ============================================================
            // Seskup hůlky podle délkové kategorie (použij metodu delkovaKategorie).
            // Pro každou kategorii najdi maximální cenu.
            // Použij Collectors.maxBy() nebo Collectors.summarizingInt().
            //
            // Očekávaný výstup:
            //   Krátká   --> max cena: 1200 zlatých
            //   Střední  --> max cena: 1500 zlatých
            //   Dlouhá   --> max cena: 1600 zlatých
            //
            System.out.println("\n=== ÚKOL 5: Maximální cena podle délkové kategorie ===");

            // TODO: Map<String, Optional<Wand>> nejdrazsiPodleDélky = ...
            // TODO: vypiš pomocí forEach

            Map<String, Optional<Wand>> byLength = hulky.stream()
                    .collect(Collectors.groupingBy(WandMapping::delkovaKategorie, Collectors.maxBy(Comparator.comparingInt(Wand::getCenaZlate))
                    ));

            byLength.forEach(
                    (length, w) -> w.ifPresent(h -> System.out.println(length + " --> " + h.getCenaZlate()))
            );


            // ============================================================
            //  ÚKOL 6 – Celkové tržby dílen (JOIN dílen a hůlek)
            // ============================================================
            // Pro každou dílnu (dilna_id) spočítej celkovou cenu PRODANÝCH hůlek.
            // Výsledek spoj s názvem dílny z listu dilny.
            // Vypiš ve formátu:  "Dílna Zlatého Draka (Praha) --> 1960 zlatých"
            //
            // Nápověda:
            //   1. Nejdřív vytvoř Map<Integer, String> idNaDilnu  (dilna_id -> "Název (Město)")
            //   2. Pak vytvoř Map<Integer, Integer> trzbyPodleDilny  pomocí filter + groupingBy + summingInt
            //   3. Nakonec spoj a vypiš
            //
            System.out.println("\n=== ÚKOL 6: Celkové tržby dílen ===");

            // TODO

            Map<Integer, String> idNaDilnu = dilny.stream()
                    .collect(Collectors.toMap(
                            Workshop::getId,
                            d -> d.getNazev() + " (" + d.getMesto() + ")"
                    ));

            Map<Integer, Integer> trzby = hulky.stream()
                    .filter(Wand::isProdana)
                    .collect(Collectors.groupingBy(
                            Wand::getDilnaId,
                            Collectors.summingInt(Wand::getCenaZlate)
                    ));

            idNaDilnu.forEach(
                    (id, name) -> trzby.forEach((id2, cena) -> {
                        if (id.equals(id2)){
                            System.out.println(name + " --> " + cena);
                        }
                    })
            );


            // ============================================================
            //  ÚKOL 7 – BONUS: Víceúrovňové groupingBy
            // ============================================================
            // Vytvoř Map<String, Map<String, Long>>:
            //   věková kategorie  ->  ohebnost  ->  počet hůlek
            //
            // Použij Collectors.groupingBy() uvnitř groupingBy (vnořené).
            //
            // Očekávaný výstup (ukázka):
            //   Antická:
            //     Poddajná  --> 3
            //     Pružná    --> 7
            //     Tvrdá     --> 9
            //     Velmi pružná --> 4
            //   Historická:
            //     ...
            //
            System.out.println("\n=== ÚKOL 7 (BONUS): Věk × ohebnost ===");

            // TODO: Map<String, Map<String, Long>> vekOhebnost = ...
            // TODO: vypiš vnořenou forEach

            Map<String, Map<String, Long>> byAgeFlexibility = hulky.stream()
                    .collect(Collectors.groupingBy(WandMapping::vekovaKategorie, Collectors.groupingBy(Wand::getOhebnost, Collectors.counting())));

            byAgeFlexibility.forEach((age, map) -> {
                System.out.println(age + ": ");
                map.forEach((flex, count) -> {
                    System.out.println("\t" + flex + " --> " + count);
                });
            });


        } catch (IOException e) {
            System.err.println("Chyba při čtení souboru: " + e);
        }
    }
}

// ----------------------------------------------------------------
//  DATOVÉ TŘÍDY  –  NEUPRAVUJ
// ----------------------------------------------------------------

class Wand {
    private int     hulkaId;
    private int     dilnaId;
    private LocalDate datumVyroby;
    private String  drevo;
    private String  jadro;
    private int     delkaCm;
    private String  ohebnost;
    private int     cenaZlate;
    private boolean prodana;

    public Wand(int hulkaId, int dilnaId, LocalDate datumVyroby,
                String drevo, String jadro, int delkaCm,
                String ohebnost, int cenaZlate, boolean prodana) {
        this.hulkaId     = hulkaId;
        this.dilnaId     = dilnaId;
        this.datumVyroby = datumVyroby;
        this.drevo       = drevo;
        this.jadro       = jadro;
        this.delkaCm     = delkaCm;
        this.ohebnost    = ohebnost;
        this.cenaZlate   = cenaZlate;
        this.prodana     = prodana;
    }

    public int       getHulkaId()     { return hulkaId; }
    public int       getDilnaId()     { return dilnaId; }
    public LocalDate getDatumVyroby() { return datumVyroby; }
    public String    getDrevo()       { return drevo; }
    public String    getJadro()       { return jadro; }
    public int       getDelkaCm()     { return delkaCm; }
    public String    getOhebnost()    { return ohebnost; }
    public int       getCenaZlate()   { return cenaZlate; }
    public boolean   isProdana()      { return prodana; }

    @Override
    public String toString() {
        return String.format("Wand{id=%d, dilna=%d, drevo='%s', jadro='%s', %dcm, %s, %d zlatých, %s}",
                hulkaId, dilnaId, drevo, jadro, delkaCm, ohebnost, cenaZlate,
                prodana ? "prodána" : "neprodána");
    }
}

class Workshop {
    private int    id;
    private String nazev;
    private String mesto;
    private int    zalozeni;
    private String majitel;
    private String specializace;

    public Workshop(int id, String nazev, String mesto,
                    int zalozeni, String majitel, String specializace) {
        this.id           = id;
        this.nazev        = nazev;
        this.mesto        = mesto;
        this.zalozeni     = zalozeni;
        this.majitel      = majitel;
        this.specializace = specializace;
    }

    public int    getId()           { return id; }
    public String getNazev()        { return nazev; }
    public String getMesto()        { return mesto; }
    public int    getZalozeni()     { return zalozeni; }
    public String getMajitel()      { return majitel; }
    public String getSpecializace() { return specializace; }

    @Override
    public String toString() {
        return String.format("Workshop{id=%d, '%s', %s, zal. %d, spec.: %s}",
                id, nazev, mesto, zalozeni, specializace);
    }
}

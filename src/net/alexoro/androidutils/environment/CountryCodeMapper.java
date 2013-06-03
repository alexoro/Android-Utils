package net.alexoro.androidutils.environment;

import java.util.ArrayList;
import java.util.List;

/**
 * User: alexoro
 * Date: 29.05.13
 * Time: 16:48
 */
public class CountryCodeMapper {

    static class Item {
        String code2;
        String code3;
        Item(String code2, String code3) {
            this.code2 = code2;
            this.code3 = code3;
        }
    }

    private static CountryCodeMapper sInstance;
    private static List<Item> sItems;

    public static CountryCodeMapper getInstance() {
        if (sInstance == null) {
            sInstance = new CountryCodeMapper();
        }
        return sInstance;
    }

    private CountryCodeMapper() {
        sItems = new ArrayList<Item>();
        fillList();
    }

    public String getIso3(String code2) {
        String lower = code2.toLowerCase();
        for (Item i: sItems) {
            if (i.code2.toLowerCase().equals(lower)) {
                return i.code3.toLowerCase();
            }
        }
        return null;
    }

    protected void fillList() {
        sItems.add(new Item("AD", "AND"));
        sItems.add(new Item("AE", "ARE"));
        sItems.add(new Item("AF", "AFG"));
        sItems.add(new Item("AG", "ATG"));
        sItems.add(new Item("AI", "AIA"));
        sItems.add(new Item("AL", "ALB"));
        sItems.add(new Item("AM", "ARM"));
        sItems.add(new Item("AO", "AGO"));
        sItems.add(new Item("AQ", "ATA"));
        sItems.add(new Item("AR", "ARG"));
        sItems.add(new Item("AS", "ASM"));
        sItems.add(new Item("AT", "AUT"));
        sItems.add(new Item("AU", "AUS"));
        sItems.add(new Item("AW", "ABW"));
        sItems.add(new Item("AX", "ALA"));
        sItems.add(new Item("AZ", "AZE"));
        sItems.add(new Item("BA", "BIH"));
        sItems.add(new Item("BB", "BRB"));
        sItems.add(new Item("BD", "BGD"));
        sItems.add(new Item("BE", "BEL"));
        sItems.add(new Item("BF", "BFA"));
        sItems.add(new Item("BG", "BGR"));
        sItems.add(new Item("BH", "BHR"));
        sItems.add(new Item("BI", "BDI"));
        sItems.add(new Item("BJ", "BEN"));
        sItems.add(new Item("BL", "BLM"));
        sItems.add(new Item("BM", "BMU"));
        sItems.add(new Item("BN", "BRN"));
        sItems.add(new Item("BO", "BOL"));
        sItems.add(new Item("BQ", "BES"));
        sItems.add(new Item("BR", "BRA"));
        sItems.add(new Item("BS", "BHS"));
        sItems.add(new Item("BT", "BTN"));
        sItems.add(new Item("BV", "BVT"));
        sItems.add(new Item("BW", "BWA"));
        sItems.add(new Item("BY", "BLR"));
        sItems.add(new Item("BZ", "BLZ"));
        sItems.add(new Item("CA", "CAN"));
        sItems.add(new Item("CC", "CCK"));
        sItems.add(new Item("CD", "COD"));
        sItems.add(new Item("CF", "CAF"));
        sItems.add(new Item("CG", "COG"));
        sItems.add(new Item("CH", "CHE"));
        sItems.add(new Item("CI", "CIV"));
        sItems.add(new Item("CK", "COK"));
        sItems.add(new Item("CL", "CHL"));
        sItems.add(new Item("CM", "CMR"));
        sItems.add(new Item("CN", "CHN"));
        sItems.add(new Item("CO", "COL"));
        sItems.add(new Item("CR", "CRI"));
        sItems.add(new Item("CU", "CUB"));
        sItems.add(new Item("CV", "CPV"));
        sItems.add(new Item("CW", "CUW"));
        sItems.add(new Item("CX", "CXR"));
        sItems.add(new Item("CY", "CYP"));
        sItems.add(new Item("CZ", "CZE"));
        sItems.add(new Item("DE", "DEU"));
        sItems.add(new Item("DJ", "DJI"));
        sItems.add(new Item("DK", "DNK"));
        sItems.add(new Item("DM", "DMA"));
        sItems.add(new Item("DO", "DOM"));
        sItems.add(new Item("DZ", "DZA"));
        sItems.add(new Item("EC", "ECU"));
        sItems.add(new Item("EE", "EST"));
        sItems.add(new Item("EG", "EGY"));
        sItems.add(new Item("EH", "ESH"));
        sItems.add(new Item("ER", "ERI"));
        sItems.add(new Item("ES", "ESP"));
        sItems.add(new Item("ET", "ETH"));
        sItems.add(new Item("EU", "EUR"));
        sItems.add(new Item("FI", "FIN"));
        sItems.add(new Item("FJ", "FJI"));
        sItems.add(new Item("FK", "FLK"));
        sItems.add(new Item("FM", "FSM"));
        sItems.add(new Item("FO", "FRO"));
        sItems.add(new Item("FR", "FRA"));
        sItems.add(new Item("GA", "GAB"));
        sItems.add(new Item("GB", "GBR"));
        sItems.add(new Item("GD", "GRD"));
        sItems.add(new Item("GE", "GEO"));
        sItems.add(new Item("GF", "GUF"));
        sItems.add(new Item("GG", "GGY"));
        sItems.add(new Item("GH", "GHA"));
        sItems.add(new Item("GI", "GIB"));
        sItems.add(new Item("GL", "GRL"));
        sItems.add(new Item("GM", "GMB"));
        sItems.add(new Item("GN", "GIN"));
        sItems.add(new Item("GP", "GLP"));
        sItems.add(new Item("GQ", "GNQ"));
        sItems.add(new Item("GR", "GRC"));
        sItems.add(new Item("GS", "SGS"));
        sItems.add(new Item("GT", "GTM"));
        sItems.add(new Item("GU", "GUM"));
        sItems.add(new Item("GW", "GNB"));
        sItems.add(new Item("GY", "GUY"));
        sItems.add(new Item("HK", "HKG"));
        sItems.add(new Item("HM", "HMD"));
        sItems.add(new Item("HN", "HND"));
        sItems.add(new Item("HR", "HRV"));
        sItems.add(new Item("HT", "HTI"));
        sItems.add(new Item("HU", "HUN"));
        sItems.add(new Item("ID", "IDN"));
        sItems.add(new Item("IE", "IRL"));
        sItems.add(new Item("IL", "ISR"));
        sItems.add(new Item("IM", "IMN"));
        sItems.add(new Item("IN", "IND"));
        sItems.add(new Item("IO", "IOT"));
        sItems.add(new Item("IQ", "IRQ"));
        sItems.add(new Item("IR", "IRN"));
        sItems.add(new Item("IS", "ISL"));
        sItems.add(new Item("IT", "ITA"));
        sItems.add(new Item("JE", "JEY"));
        sItems.add(new Item("JM", "JAM"));
        sItems.add(new Item("JO", "JOR"));
        sItems.add(new Item("JP", "JPN"));
        sItems.add(new Item("KE", "KEN"));
        sItems.add(new Item("KG", "KGZ"));
        sItems.add(new Item("KH", "KHM"));
        sItems.add(new Item("KI", "KIR"));
        sItems.add(new Item("KM", "COM"));
        sItems.add(new Item("KN", "KNA"));
        sItems.add(new Item("KP", "PRK"));
        sItems.add(new Item("KR", "KOR"));
        sItems.add(new Item("KW", "KWT"));
        sItems.add(new Item("KY", "CYM"));
        sItems.add(new Item("KZ", "KAZ"));
        sItems.add(new Item("LA", "LAO"));
        sItems.add(new Item("LB", "LBN"));
        sItems.add(new Item("LC", "LCA"));
        sItems.add(new Item("LI", "LIE"));
        sItems.add(new Item("LK", "LKA"));
        sItems.add(new Item("LR", "LBR"));
        sItems.add(new Item("LS", "LSO"));
        sItems.add(new Item("LT", "LTU"));
        sItems.add(new Item("LU", "LUX"));
        sItems.add(new Item("LV", "LVA"));
        sItems.add(new Item("LY", "LBY"));
        sItems.add(new Item("MA", "MAR"));
        sItems.add(new Item("MC", "MCO"));
        sItems.add(new Item("MD", "MDA"));
        sItems.add(new Item("ME", "MNE"));
        sItems.add(new Item("MF", "MAF"));
        sItems.add(new Item("MG", "MDG"));
        sItems.add(new Item("MH", "MHL"));
        sItems.add(new Item("MK", "MKD"));
        sItems.add(new Item("ML", "MLI"));
        sItems.add(new Item("MM", "MMR"));
        sItems.add(new Item("MN", "MNG"));
        sItems.add(new Item("MO", "MAC"));
        sItems.add(new Item("MP", "MNP"));
        sItems.add(new Item("MQ", "MTQ"));
        sItems.add(new Item("MR", "MRT"));
        sItems.add(new Item("MS", "MSR"));
        sItems.add(new Item("MT", "MLT"));
        sItems.add(new Item("MU", "MUS"));
        sItems.add(new Item("MV", "MDV"));
        sItems.add(new Item("MW", "MWI"));
        sItems.add(new Item("MX", "MEX"));
        sItems.add(new Item("MY", "MYS"));
        sItems.add(new Item("MZ", "MOZ"));
        sItems.add(new Item("NA", "NAM"));
        sItems.add(new Item("NC", "NCL"));
        sItems.add(new Item("NE", "NER"));
        sItems.add(new Item("NF", "NFK"));
        sItems.add(new Item("NG", "NGA"));
        sItems.add(new Item("NI", "NIC"));
        sItems.add(new Item("NL", "NLD"));
        sItems.add(new Item("NO", "NOR"));
        sItems.add(new Item("NP", "NPL"));
        sItems.add(new Item("NR", "NRU"));
        sItems.add(new Item("NU", "NIU"));
        sItems.add(new Item("NZ", "NZL"));
        sItems.add(new Item("OM", "OMN"));
        sItems.add(new Item("PA", "PAN"));
        sItems.add(new Item("PE", "PER"));
        sItems.add(new Item("PF", "PYF"));
        sItems.add(new Item("PG", "PNG"));
        sItems.add(new Item("PH", "PHL"));
        sItems.add(new Item("PK", "PAK"));
        sItems.add(new Item("PL", "POL"));
        sItems.add(new Item("PM", "SPM"));
        sItems.add(new Item("PN", "PCN"));
        sItems.add(new Item("PR", "PRI"));
        sItems.add(new Item("PS", "PSE"));
        sItems.add(new Item("PT", "PRT"));
        sItems.add(new Item("PW", "PLW"));
        sItems.add(new Item("PY", "PRY"));
        sItems.add(new Item("QA", "QAT"));
        sItems.add(new Item("RE", "REU"));
        sItems.add(new Item("RO", "ROU"));
        sItems.add(new Item("RS", "SRB"));
        sItems.add(new Item("RU", "RUS"));
        sItems.add(new Item("RW", "RWA"));
        sItems.add(new Item("SA", "SAU"));
        sItems.add(new Item("SB", "SLB"));
        sItems.add(new Item("SC", "SYC"));
        sItems.add(new Item("SD", "SDN"));
        sItems.add(new Item("SE", "SWE"));
        sItems.add(new Item("SG", "SGP"));
        sItems.add(new Item("SH", "SHN"));
        sItems.add(new Item("SI", "SVN"));
        sItems.add(new Item("SJ", "SJM"));
        sItems.add(new Item("SK", "SVK"));
        sItems.add(new Item("SL", "SLE"));
        sItems.add(new Item("SM", "SMR"));
        sItems.add(new Item("SN", "SEN"));
        sItems.add(new Item("SO", "SOM"));
        sItems.add(new Item("SR", "SUR"));
        sItems.add(new Item("SS", "SSD"));
        sItems.add(new Item("ST", "STP"));
        sItems.add(new Item("SU", "SUN"));
        sItems.add(new Item("SV", "SLV"));
        sItems.add(new Item("SX", "SXM"));
        sItems.add(new Item("SY", "SYR"));
        sItems.add(new Item("SZ", "SWZ"));
        sItems.add(new Item("TC", "TCA"));
        sItems.add(new Item("TD", "TCD"));
        sItems.add(new Item("TF", "ATF"));
        sItems.add(new Item("TG", "TGO"));
        sItems.add(new Item("TH", "THA"));
        sItems.add(new Item("TJ", "TJK"));
        sItems.add(new Item("TK", "TKL"));
        sItems.add(new Item("TL", "TLS"));
        sItems.add(new Item("TM", "TKM"));
        sItems.add(new Item("TN", "TUN"));
        sItems.add(new Item("TO", "TON"));
        sItems.add(new Item("TR", "TUR"));
        sItems.add(new Item("TT", "TTO"));
        sItems.add(new Item("TV", "TUV"));
        sItems.add(new Item("TW", "TWN"));
        sItems.add(new Item("TZ", "TZA"));
        sItems.add(new Item("UA", "UKR"));
        sItems.add(new Item("UG", "UGA"));
        sItems.add(new Item("UM", "UMI"));
        sItems.add(new Item("US", "USA"));
        sItems.add(new Item("UY", "URY"));
        sItems.add(new Item("UZ", "UZB"));
        sItems.add(new Item("VA", "VAT"));
        sItems.add(new Item("VC", "VCT"));
        sItems.add(new Item("VE", "VEN"));
        sItems.add(new Item("VG", "VGB"));
        sItems.add(new Item("VI", "VIR"));
        sItems.add(new Item("VN", "VNM"));
        sItems.add(new Item("VU", "VUT"));
        sItems.add(new Item("WF", "WLF"));
        sItems.add(new Item("WS", "WSM"));
        sItems.add(new Item("YE", "YEM"));
        sItems.add(new Item("YT", "MYT"));
        sItems.add(new Item("ZA", "ZAF"));
        sItems.add(new Item("ZM", "ZMB"));
        sItems.add(new Item("ZW", "ZWE"));
    }

}
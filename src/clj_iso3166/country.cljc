(ns clj-iso3166.country
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as string]))

(def countries
  [{:name "Andorra", :numeric 20, :alpha3 "AND", :alpha2 "AD"}
   {:name "United Arab Emirates", :numeric 784, :alpha3 "ARE", :alpha2 "AE"}
   {:name "Afghanistan", :numeric 4, :alpha3 "AFG", :alpha2 "AF"}
   {:name "Antigua and Barbuda", :numeric 28, :alpha3 "ATG", :alpha2 "AG"}
   {:name "Anguilla", :numeric 660, :alpha3 "AIA", :alpha2 "AI"}
   {:name "Albania", :numeric 8, :alpha3 "ALB", :alpha2 "AL"}
   {:name "Armenia", :numeric 51, :alpha3 "ARM", :alpha2 "AM"}
   {:name "Angola", :numeric 24, :alpha3 "AGO", :alpha2 "AO"}
   {:name "Antarctica", :numeric 10, :alpha3 "ATA", :alpha2 "AQ"}
   {:name "Argentina", :numeric 32, :alpha3 "ARG", :alpha2 "AR"}
   {:name "American Samoa", :numeric 16, :alpha3 "ASM", :alpha2 "AS"}
   {:name "Austria", :numeric 40, :alpha3 "AUT", :alpha2 "AT"}
   {:name "Australia", :numeric 36, :alpha3 "AUS", :alpha2 "AU"}
   {:name "Aruba", :numeric 533, :alpha3 "ABW", :alpha2 "AW"}
   {:name "Åland Islands", :numeric 248, :alpha3 "ALA", :alpha2 "AX"}
   {:name "Azerbaijan", :numeric 31, :alpha3 "AZE", :alpha2 "AZ"}
   {:name "Bosnia and Herzegovina", :numeric 70, :alpha3 "BIH", :alpha2 "BA"}
   {:name "Barbados", :numeric 52, :alpha3 "BRB", :alpha2 "BB"}
   {:name "Bangladesh", :numeric 50, :alpha3 "BGD", :alpha2 "BD"}
   {:name "Belgium", :numeric 56, :alpha3 "BEL", :alpha2 "BE"}
   {:name "Burkina Faso", :numeric 854, :alpha3 "BFA", :alpha2 "BF"}
   {:name "Bulgaria", :numeric 100, :alpha3 "BGR", :alpha2 "BG"}
   {:name "Bahrain", :numeric 48, :alpha3 "BHR", :alpha2 "BH"}
   {:name "Burundi", :numeric 108, :alpha3 "BDI", :alpha2 "BI"}
   {:name "Benin", :numeric 204, :alpha3 "BEN", :alpha2 "BJ"}
   {:name "Saint Barthélemy", :numeric 652, :alpha3 "BLM", :alpha2 "BL"}
   {:name "Bermuda", :numeric 60, :alpha3 "BMU", :alpha2 "BM"}
   {:name "Brunei Darussalam", :numeric 96, :alpha3 "BRN", :alpha2 "BN"}
   {:name "Bolivia, Plurinational State of", :numeric 68, :alpha3 "BOL", :alpha2 "BO"}
   {:name "Bonaire, Saint Eustatius and Saba", :numeric 535, :alpha3 "BES", :alpha2 "BQ"}
   {:name "Brazil", :numeric 76, :alpha3 "BRA", :alpha2 "BR"}
   {:name "Bahamas", :numeric 44, :alpha3 "BHS", :alpha2 "BS"}
   {:name "Bhutan", :numeric 64, :alpha3 "BTN", :alpha2 "BT"}
   {:name "Bouvet Island", :numeric 74, :alpha3 "BVT", :alpha2 "BV"}
   {:name "Botswana", :numeric 72, :alpha3 "BWA", :alpha2 "BW"}
   {:name "Belarus", :numeric 112, :alpha3 "BLR", :alpha2 "BY"}
   {:name "Belize", :numeric 84, :alpha3 "BLZ", :alpha2 "BZ"}
   {:name "Canada", :numeric 124, :alpha3 "CAN", :alpha2 "CA"}
   {:name "Cocos (Keeling) Islands", :numeric 166, :alpha3 "CCK", :alpha2 "CC"}
   {:name "Congo, the Democratic Republic of the", :numeric 180, :alpha3 "COD", :alpha2 "CD"}
   {:name "Central African Republic", :numeric 140, :alpha3 "CAF", :alpha2 "CF"}
   {:name "Congo", :numeric 178, :alpha3 "COG", :alpha2 "CG"}
   {:name "Switzerland", :numeric 756, :alpha3 "CHE", :alpha2 "CH"}
   {:name "Côte d'Ivoire", :numeric 384, :alpha3 "CIV", :alpha2 "CI"}
   {:name "Cook Islands", :numeric 184, :alpha3 "COK", :alpha2 "CK"}
   {:name "Chile", :numeric 152, :alpha3 "CHL", :alpha2 "CL"}
   {:name "Cameroon", :numeric 120, :alpha3 "CMR", :alpha2 "CM"}
   {:name "China", :numeric 156, :alpha3 "CHN", :alpha2 "CN"}
   {:name "Colombia", :numeric 170, :alpha3 "COL", :alpha2 "CO"}
   {:name "Costa Rica", :numeric 188, :alpha3 "CRI", :alpha2 "CR"}
   {:name "Cuba", :numeric 192, :alpha3 "CUB", :alpha2 "CU"}
   {:name "Cape Verde", :numeric 132, :alpha3 "CPV", :alpha2 "CV"}
   {:name "Curaçao", :numeric 531, :alpha3 "CUW", :alpha2 "CW"}
   {:name "Christmas Island", :numeric 162, :alpha3 "CXR", :alpha2 "CX"}
   {:name "Cyprus", :numeric 196, :alpha3 "CYP", :alpha2 "CY"}
   {:name "Czechia", :numeric 203, :alpha3 "CZE", :alpha2 "CZ"}
   {:name "Germany", :numeric 276, :alpha3 "DEU", :alpha2 "DE"}
   {:name "Djibouti", :numeric 262, :alpha3 "DJI", :alpha2 "DJ"}
   {:name "Denmark", :numeric 208, :alpha3 "DNK", :alpha2 "DK"}
   {:name "Dominica", :numeric 212, :alpha3 "DMA", :alpha2 "DM"}
   {:name "Dominican Republic", :numeric 214, :alpha3 "DOM", :alpha2 "DO"}
   {:name "Algeria", :numeric 12, :alpha3 "DZA", :alpha2 "DZ"}
   {:name "Ecuador", :numeric 218, :alpha3 "ECU", :alpha2 "EC"}
   {:name "Estonia", :numeric 233, :alpha3 "EST", :alpha2 "EE"}
   {:name "Egypt", :numeric 818, :alpha3 "EGY", :alpha2 "EG"}
   {:name "Western Sahara", :numeric 732, :alpha3 "ESH", :alpha2 "EH"}
   {:name "Eritrea", :numeric 232, :alpha3 "ERI", :alpha2 "ER"}
   {:name "Spain", :numeric 724, :alpha3 "ESP", :alpha2 "ES"}
   {:name "Ethiopia", :numeric 231, :alpha3 "ETH", :alpha2 "ET"}
   {:name "Finland", :numeric 246, :alpha3 "FIN", :alpha2 "FI"}
   {:name "Fiji", :numeric 242, :alpha3 "FJI", :alpha2 "FJ"}
   {:name "Falkland Islands (Malvinas)", :numeric 238, :alpha3 "FLK", :alpha2 "FK"}
   {:name "Micronesia, Federated States of", :numeric 583, :alpha3 "FSM", :alpha2 "FM"}
   {:name "Faroe Islands", :numeric 234, :alpha3 "FRO", :alpha2 "FO"}
   {:name "France", :numeric 250, :alpha3 "FRA", :alpha2 "FR"}
   {:name "Gabon", :numeric 266, :alpha3 "GAB", :alpha2 "GA"}
   {:name "United Kingdom", :numeric 826, :alpha3 "GBR", :alpha2 "GB"}
   {:name "Grenada", :numeric 308, :alpha3 "GRD", :alpha2 "GD"}
   {:name "Georgia", :numeric 268, :alpha3 "GEO", :alpha2 "GE"}
   {:name "French Guiana", :numeric 254, :alpha3 "GUF", :alpha2 "GF"}
   {:name "Guernsey", :numeric 831, :alpha3 "GGY", :alpha2 "GG"}
   {:name "Ghana", :numeric 288, :alpha3 "GHA", :alpha2 "GH"}
   {:name "Gibraltar", :numeric 292, :alpha3 "GIB", :alpha2 "GI"}
   {:name "Greenland", :numeric 304, :alpha3 "GRL", :alpha2 "GL"}
   {:name "Gambia", :numeric 270, :alpha3 "GMB", :alpha2 "GM"}
   {:name "Guinea", :numeric 324, :alpha3 "GIN", :alpha2 "GN"}
   {:name "Guadeloupe", :numeric 312, :alpha3 "GLP", :alpha2 "GP"}
   {:name "Equatorial Guinea", :numeric 226, :alpha3 "GNQ", :alpha2 "GQ"}
   {:name "Greece", :numeric 300, :alpha3 "GRC", :alpha2 "GR"}
   {:name "South Georgia and the South Sandwich Islands", :numeric 239, :alpha3 "SGS", :alpha2 "GS"}
   {:name "Guatemala", :numeric 320, :alpha3 "GTM", :alpha2 "GT"}
   {:name "Guam", :numeric 316, :alpha3 "GUM", :alpha2 "GU"}
   {:name "Guinea-Bissau", :numeric 624, :alpha3 "GNB", :alpha2 "GW"}
   {:name "Guyana", :numeric 328, :alpha3 "GUY", :alpha2 "GY"}
   {:name "Hong Kong", :numeric 344, :alpha3 "HKG", :alpha2 "HK"}
   {:name "Heard Island and McDonald Islands", :numeric 334, :alpha3 "HMD", :alpha2 "HM"}
   {:name "Honduras", :numeric 340, :alpha3 "HND", :alpha2 "HN"}
   {:name "Croatia", :numeric 191, :alpha3 "HRV", :alpha2 "HR"}
   {:name "Haiti", :numeric 332, :alpha3 "HTI", :alpha2 "HT"}
   {:name "Hungary", :numeric 348, :alpha3 "HUN", :alpha2 "HU"}
   {:name "Indonesia", :numeric 360, :alpha3 "IDN", :alpha2 "ID"}
   {:name "Ireland", :numeric 372, :alpha3 "IRL", :alpha2 "IE"}
   {:name "Israel", :numeric 376, :alpha3 "ISR", :alpha2 "IL"}
   {:name "Isle of Man", :numeric 833, :alpha3 "IMN", :alpha2 "IM"}
   {:name "India", :numeric 356, :alpha3 "IND", :alpha2 "IN"}
   {:name "British Indian Ocean Territory", :numeric 86, :alpha3 "IOT", :alpha2 "IO"}
   {:name "Iraq", :numeric 368, :alpha3 "IRQ", :alpha2 "IQ"}
   {:name "Iran, Islamic Republic of", :numeric 364, :alpha3 "IRN", :alpha2 "IR"}
   {:name "Iceland", :numeric 352, :alpha3 "ISL", :alpha2 "IS"}
   {:name "Italy", :numeric 380, :alpha3 "ITA", :alpha2 "IT"}
   {:name "Jersey", :numeric 832, :alpha3 "JEY", :alpha2 "JE"}
   {:name "Jamaica", :numeric 388, :alpha3 "JAM", :alpha2 "JM"}
   {:name "Jordan", :numeric 400, :alpha3 "JOR", :alpha2 "JO"}
   {:name "Japan", :numeric 392, :alpha3 "JPN", :alpha2 "JP"}
   {:name "Kenya", :numeric 404, :alpha3 "KEN", :alpha2 "KE"}
   {:name "Kyrgyzstan", :numeric 417, :alpha3 "KGZ", :alpha2 "KG"}
   {:name "Cambodia", :numeric 116, :alpha3 "KHM", :alpha2 "KH"}
   {:name "Kiribati", :numeric 296, :alpha3 "KIR", :alpha2 "KI"}
   {:name "Comoros", :numeric 174, :alpha3 "COM", :alpha2 "KM"}
   {:name "Saint Kitts and Nevis", :numeric 659, :alpha3 "KNA", :alpha2 "KN"}
   {:name "Korea, Democratic People's Republic of", :numeric 408, :alpha3 "PRK", :alpha2 "KP"}
   {:name "Korea, Republic of", :numeric 410, :alpha3 "KOR", :alpha2 "KR"}
   {:name "Kuwait", :numeric 414, :alpha3 "KWT", :alpha2 "KW"}
   {:name "Cayman Islands", :numeric 136, :alpha3 "CYM", :alpha2 "KY"}
   {:name "Kazakhstan", :numeric 398, :alpha3 "KAZ", :alpha2 "KZ"}
   {:name "Lao People's Democratic Republic", :numeric 418, :alpha3 "LAO", :alpha2 "LA"}
   {:name "Lebanon", :numeric 422, :alpha3 "LBN", :alpha2 "LB"}
   {:name "Saint Lucia", :numeric 662, :alpha3 "LCA", :alpha2 "LC"}
   {:name "Liechtenstein", :numeric 438, :alpha3 "LIE", :alpha2 "LI"}
   {:name "Sri Lanka", :numeric 144, :alpha3 "LKA", :alpha2 "LK"}
   {:name "Liberia", :numeric 430, :alpha3 "LBR", :alpha2 "LR"}
   {:name "Lesotho", :numeric 426, :alpha3 "LSO", :alpha2 "LS"}
   {:name "Lithuania", :numeric 440, :alpha3 "LTU", :alpha2 "LT"}
   {:name "Luxembourg", :numeric 442, :alpha3 "LUX", :alpha2 "LU"}
   {:name "Latvia", :numeric 428, :alpha3 "LVA", :alpha2 "LV"}
   {:name "Libya", :numeric 434, :alpha3 "LBY", :alpha2 "LY"}
   {:name "Morocco", :numeric 504, :alpha3 "MAR", :alpha2 "MA"}
   {:name "Monaco", :numeric 492, :alpha3 "MCO", :alpha2 "MC"}
   {:name "Moldova, Republic of", :numeric 498, :alpha3 "MDA", :alpha2 "MD"}
   {:name "Montenegro", :numeric 499, :alpha3 "MNE", :alpha2 "ME"}
   {:name "Saint Martin (French part)", :numeric 663, :alpha3 "MAF", :alpha2 "MF"}
   {:name "Madagascar", :numeric 450, :alpha3 "MDG", :alpha2 "MG"}
   {:name "Marshall Islands", :numeric 584, :alpha3 "MHL", :alpha2 "MH"}
   {:name "North Macedonia", :numeric 807, :alpha3 "MKD", :alpha2 "MK"}
   {:name "Mali", :numeric 466, :alpha3 "MLI", :alpha2 "ML"}
   {:name "Myanmar", :numeric 104, :alpha3 "MMR", :alpha2 "MM"}
   {:name "Mongolia", :numeric 496, :alpha3 "MNG", :alpha2 "MN"}
   {:name "Macau", :numeric 446, :alpha3 "MAC", :alpha2 "MO"}
   {:name "Northern Mariana Islands", :numeric 580, :alpha3 "MNP", :alpha2 "MP"}
   {:name "Martinique", :numeric 474, :alpha3 "MTQ", :alpha2 "MQ"}
   {:name "Mauritania", :numeric 478, :alpha3 "MRT", :alpha2 "MR"}
   {:name "Montserrat", :numeric 500, :alpha3 "MSR", :alpha2 "MS"}
   {:name "Malta", :numeric 470, :alpha3 "MLT", :alpha2 "MT"}
   {:name "Mauritius", :numeric 480, :alpha3 "MUS", :alpha2 "MU"}
   {:name "Maldives", :numeric 462, :alpha3 "MDV", :alpha2 "MV"}
   {:name "Malawi", :numeric 454, :alpha3 "MWI", :alpha2 "MW"}
   {:name "Mexico", :numeric 484, :alpha3 "MEX", :alpha2 "MX"}
   {:name "Malaysia", :numeric 458, :alpha3 "MYS", :alpha2 "MY"}
   {:name "Mozambique", :numeric 508, :alpha3 "MOZ", :alpha2 "MZ"}
   {:name "Namibia", :numeric 516, :alpha3 "NAM", :alpha2 "NA"}
   {:name "New Caledonia", :numeric 540, :alpha3 "NCL", :alpha2 "NC"}
   {:name "Niger", :numeric 562, :alpha3 "NER", :alpha2 "NE"}
   {:name "Norfolk Island", :numeric 574, :alpha3 "NFK", :alpha2 "NF"}
   {:name "Nigeria", :numeric 566, :alpha3 "NGA", :alpha2 "NG"}
   {:name "Nicaragua", :numeric 558, :alpha3 "NIC", :alpha2 "NI"}
   {:name "Netherlands", :numeric 528, :alpha3 "NLD", :alpha2 "NL"}
   {:name "Norway", :numeric 578, :alpha3 "NOR", :alpha2 "NO"}
   {:name "Nepal", :numeric 524, :alpha3 "NPL", :alpha2 "NP"}
   {:name "Nauru", :numeric 520, :alpha3 "NRU", :alpha2 "NR"}
   {:name "Niue", :numeric 570, :alpha3 "NIU", :alpha2 "NU"}
   {:name "New Zealand", :numeric 554, :alpha3 "NZL", :alpha2 "NZ"}
   {:name "Oman", :numeric 512, :alpha3 "OMN", :alpha2 "OM"}
   {:name "Panama", :numeric 591, :alpha3 "PAN", :alpha2 "PA"}
   {:name "Peru", :numeric 604, :alpha3 "PER", :alpha2 "PE"}
   {:name "French Polynesia", :numeric 258, :alpha3 "PYF", :alpha2 "PF"}
   {:name "Papua New Guinea", :numeric 598, :alpha3 "PNG", :alpha2 "PG"}
   {:name "Philippines", :numeric 608, :alpha3 "PHL", :alpha2 "PH"}
   {:name "Pakistan", :numeric 586, :alpha3 "PAK", :alpha2 "PK"}
   {:name "Poland", :numeric 616, :alpha3 "POL", :alpha2 "PL"}
   {:name "Saint Pierre and Miquelon", :numeric 666, :alpha3 "SPM", :alpha2 "PM"}
   {:name "Pitcairn", :numeric 612, :alpha3 "PCN", :alpha2 "PN"}
   {:name "Puerto Rico", :numeric 630, :alpha3 "PRI", :alpha2 "PR"}
   {:name "Palestinian Territory, Occupied", :numeric 275, :alpha3 "PSE", :alpha2 "PS"}
   {:name "Portugal", :numeric 620, :alpha3 "PRT", :alpha2 "PT"}
   {:name "Palau", :numeric 585, :alpha3 "PLW", :alpha2 "PW"}
   {:name "Paraguay", :numeric 600, :alpha3 "PRY", :alpha2 "PY"}
   {:name "Qatar", :numeric 634, :alpha3 "QAT", :alpha2 "QA"}
   {:name "Réunion", :numeric 638, :alpha3 "REU", :alpha2 "RE"}
   {:name "Romania", :numeric 642, :alpha3 "ROU", :alpha2 "RO"}
   {:name "Serbia", :numeric 688, :alpha3 "SRB", :alpha2 "RS"}
   {:name "Russian Federation", :numeric 643, :alpha3 "RUS", :alpha2 "RU"}
   {:name "Rwanda", :numeric 646, :alpha3 "RWA", :alpha2 "RW"}
   {:name "Saudi Arabia", :numeric 682, :alpha3 "SAU", :alpha2 "SA"}
   {:name "Solomon Islands", :numeric 90, :alpha3 "SLB", :alpha2 "SB"}
   {:name "Seychelles", :numeric 690, :alpha3 "SYC", :alpha2 "SC"}
   {:name "Sudan", :numeric 729, :alpha3 "SDN", :alpha2 "SD"}
   {:name "Sweden", :numeric 752, :alpha3 "SWE", :alpha2 "SE"}
   {:name "Singapore", :numeric 702, :alpha3 "SGP", :alpha2 "SG"}
   {:name "Saint Helena, Ascension and Tristan da Cunha", :numeric 654, :alpha3 "SHN", :alpha2 "SH"}
   {:name "Slovenia", :numeric 705, :alpha3 "SVN", :alpha2 "SI"}
   {:name "Svalbard and Jan Mayen", :numeric 744, :alpha3 "SJM", :alpha2 "SJ"}
   {:name "Slovakia", :numeric 703, :alpha3 "SVK", :alpha2 "SK"}
   {:name "Sierra Leone", :numeric 694, :alpha3 "SLE", :alpha2 "SL"}
   {:name "San Marino", :numeric 674, :alpha3 "SMR", :alpha2 "SM"}
   {:name "Senegal", :numeric 686, :alpha3 "SEN", :alpha2 "SN"}
   {:name "Somalia", :numeric 706, :alpha3 "SOM", :alpha2 "SO"}
   {:name "Suriname", :numeric 740, :alpha3 "SUR", :alpha2 "SR"}
   {:name "South Sudan", :numeric 728, :alpha3 "SSD", :alpha2 "SS"}
   {:name "Sao Tome and Principe", :numeric 678, :alpha3 "STP", :alpha2 "ST"}
   {:name "El Salvador", :numeric 222, :alpha3 "SLV", :alpha2 "SV"}
   {:name "Sint Maarten (Dutch part)", :numeric 534, :alpha3 "SXM", :alpha2 "SX"}
   {:name "Syrian Arab Republic", :numeric 760, :alpha3 "SYR", :alpha2 "SY"}
   {:name "Swaziland", :numeric 748, :alpha3 "SWZ", :alpha2 "SZ"}
   {:name "Turks and Caicos Islands", :numeric 796, :alpha3 "TCA", :alpha2 "TC"}
   {:name "Chad", :numeric 148, :alpha3 "TCD", :alpha2 "TD"}
   {:name "French Southern Territories", :numeric 260, :alpha3 "ATF", :alpha2 "TF"}
   {:name "Togo", :numeric 768, :alpha3 "TGO", :alpha2 "TG"}
   {:name "Thailand", :numeric 764, :alpha3 "THA", :alpha2 "TH"}
   {:name "Tajikistan", :numeric 762, :alpha3 "TJK", :alpha2 "TJ"}
   {:name "Tokelau", :numeric 772, :alpha3 "TKL", :alpha2 "TK"}
   {:name "Timor-Leste", :numeric 626, :alpha3 "TLS", :alpha2 "TL"}
   {:name "Turkmenistan", :numeric 795, :alpha3 "TKM", :alpha2 "TM"}
   {:name "Tunisia", :numeric 788, :alpha3 "TUN", :alpha2 "TN"}
   {:name "Tonga", :numeric 776, :alpha3 "TON", :alpha2 "TO"}
   {:name "Turkey", :numeric 792, :alpha3 "TUR", :alpha2 "TR"}
   {:name "Trinidad and Tobago", :numeric 780, :alpha3 "TTO", :alpha2 "TT"}
   {:name "Tuvalu", :numeric 798, :alpha3 "TUV", :alpha2 "TV"}
   {:name "Taiwan, Province of China", :numeric 158, :alpha3 "TWN", :alpha2 "TW"}
   {:name "Tanzania, United Republic of", :numeric 834, :alpha3 "TZA", :alpha2 "TZ"}
   {:name "Ukraine", :numeric 804, :alpha3 "UKR", :alpha2 "UA"}
   {:name "Uganda", :numeric 800, :alpha3 "UGA", :alpha2 "UG"}
   {:name "United States Minor Outlying Islands", :numeric 581, :alpha3 "UMI", :alpha2 "UM"}
   {:name "United States", :numeric 840, :alpha3 "USA", :alpha2 "US"}
   {:name "Uruguay", :numeric 858, :alpha3 "URY", :alpha2 "UY"}
   {:name "Uzbekistan", :numeric 860, :alpha3 "UZB", :alpha2 "UZ"}
   {:name "Holy See (Vatican City State)", :numeric 336, :alpha3 "VAT", :alpha2 "VA"}
   {:name "Saint Vincent and the Grenadines", :numeric 670, :alpha3 "VCT", :alpha2 "VC"}
   {:name "Venezuela, Bolivarian Republic of", :numeric 862, :alpha3 "VEN", :alpha2 "VE"}
   {:name "Virgin Islands, British", :numeric 92, :alpha3 "VGB", :alpha2 "VG"}
   {:name "Virgin Islands, U.S.", :numeric 850, :alpha3 "VIR", :alpha2 "VI"}
   {:name "Viet Nam", :numeric 704, :alpha3 "VNM", :alpha2 "VN"}
   {:name "Vanuatu", :numeric 548, :alpha3 "VUT", :alpha2 "VU"}
   {:name "Wallis and Futuna", :numeric 876, :alpha3 "WLF", :alpha2 "WF"}
   {:name "Samoa", :numeric 882, :alpha3 "WSM", :alpha2 "WS"}
   {:name "Yemen", :numeric 887, :alpha3 "YEM", :alpha2 "YE"}
   {:name "Mayotte", :numeric 175, :alpha3 "MYT", :alpha2 "YT"}
   {:name "South Africa", :numeric 710, :alpha3 "ZAF", :alpha2 "ZA"}
   {:name "Zambia", :numeric 894, :alpha3 "ZMB", :alpha2 "ZM"}
   {:name "Zimbabwe", :numeric 716, :alpha3 "ZWE", :alpha2 "ZW"}])

(s/def ::name (s/and string? (set (map :name countries))))

(s/def ::numeric (s/and integer?
                        #(<= 0 % 999)
                        (set (map :numeric countries))))

(s/def ::alpha3 (s/and string?
                       #(re-matches #"[A-Z]{3}" %)
                       (set (map :alpha3 countries))))

(s/def ::alpha2 (s/and string?
                       #(re-matches #"[A-Z]{2}" %)
                       (set (map :alpha2 countries))))

(s/def ::country (s/keys :req-un [::name ::numeric ::alpha3 ::alpha2]))

(def ^:private name-map
  (->> countries
       (map #(vector (string/lower-case (:name %)) %))
       (into {})))

(def ^:private numeric-map
  (->> countries
       (map #(vector (:numeric %) %))
       (into {})))

(def ^:private alpha3-map
  (->> countries
       (map #(vector (:alpha3 %) %))
       (into {})))

(def ^:private alpha2-map
  (->> countries
       (map #(vector (:alpha2 %) %))
       (into {})))

(defn name->country [s]
  (when s
    (name-map (string/lower-case s))))

(s/fdef name->country
  :args (s/cat :s (s/nilable string?))
  :ret  (s/nilable ::country))

(defn numeric->country [n]
  (numeric-map n))

(s/fdef numeric->country
  :args (s/cat :n (s/nilable integer?))
  :ret  (s/nilable ::country))

(defn alpha3->country [s]
  (when s
    (alpha3-map (string/upper-case s))))

(s/fdef alpha3->country
  :args (s/cat :s (s/nilable string?))
  :ret  (s/nilable ::country))

(defn alpha2->country [s]
  (when s
    (alpha2-map (string/upper-case s))))

(s/fdef alpha2->country
  :args (s/cat :s (s/nilable string?))
  :ret  (s/nilable ::country))

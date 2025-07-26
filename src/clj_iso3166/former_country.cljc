(ns clj-iso3166.former-country
  "The formerly used country codes defined in ISO 3166-3."
  (:require [clj-iso3166.country :as c]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.string :as string]))

(def former-countries
  [{:name "French Afar and Issas", :alpha4 "AIDJ",
    :former-code {:numeric 262, :alpha3 "AFI", :alpha2 "AI"},
    :latter-codes [{:numeric 262, :alpha3 "DJI", :alpha2 "DJ"}]}
   {:name "Netherlands Antilles", :alpha4 "ANHH",
    :former-code {:numeric 530, :alpha3 "ANT", :alpha2 "AN"},
    :latter-codes [{:numeric 535, :alpha3 "BES", :alpha2 "BQ"}
                   {:numeric 531, :alpha3 "CUW", :alpha2 "CW"}
                   {:numeric 534, :alpha3 "SXM", :alpha2 "SX"}]}
   {:name "British Antarctic Territory", :alpha4 "BQAQ",
    :former-code {:numeric 80, :alpha3 "ATB", :alpha2 "BQ"},
    :latter-codes [{:numeric 10, :alpha3 "ATA", :alpha2 "AQ"}]}
   {:name "Burma", :alpha4 "BUMM",
    :former-code {:numeric 104, :alpha3 "BUR", :alpha2 "BU"},
    :latter-codes [{:numeric 104, :alpha3 "MMR", :alpha2 "MM"}]}
   {:name "Byelorussian SSR", :alpha4 "BYAA",
    :former-code {:numeric 112, :alpha3 "BYS", :alpha2 "BY"},
    :latter-codes [{:numeric 112, :alpha3 "BLR", :alpha2 "BY"}]}
   {:name "Czechoslovakia", :alpha4 "CSHH",
    :former-code {:numeric 200, :alpha3 "CSK", :alpha2 "CS"},
    :latter-codes [{:numeric 203, :alpha3 "CZE", :alpha2 "CZ"}
                   {:numeric 703, :alpha3 "SVK", :alpha2 "SK"}]}
   {:name "Serbia and Montenegro", :alpha4 "CSXX",
    :former-code {:numeric 891, :alpha3 "SCG", :alpha2 "CS"},
    :latter-codes [{:numeric 499, :alpha3 "MNE", :alpha2 "ME"}
                   {:numeric 688, :alpha3 "SRB", :alpha2 "RS"}]}
   {:name "Canton and Enderbury Islands", :alpha4 "CTKI",
    :former-code {:numeric 128, :alpha3 "CTE", :alpha2 "CT"},
    :latter-codes [{:numeric 296, :alpha3 "KIR", :alpha2 "KI"}]}
   {:name "German Democratic Republic", :alpha4 "DDDE",
    :former-code {:numeric 278, :alpha3 "DDR", :alpha2 "DD"},
    :latter-codes [{:numeric 276, :alpha3 "DEU", :alpha2 "DE"}]}
   {:name "Dahomey", :alpha4 "DYBJ",
    :former-code {:numeric 204, :alpha3 "DHY", :alpha2 "DY"},
    :latter-codes [{:numeric 204, :alpha3 "BEN", :alpha2 "BJ"}]}
   {:name "French Southern and Antarctic Territories", :alpha4 "FQHH",
    :former-code {:numeric 260, :alpha3 "ATF", :alpha2 "FQ"},
    :latter-codes [{:numeric 10, :alpha3 "ATA", :alpha2 "AQ"}
                   {:numeric 260, :alpha3 "ATF", :alpha2 "TF"}]}
   {:name "France, Metropolitan", :alpha4 "FXFR",
    :former-code {:numeric 249, :alpha3 "FXX", :alpha2 "FX"},
    :latter-codes [{:numeric 250, :alpha3 "FRA", :alpha2 "FR"}]}
   {:name "Gilbert and Ellice Islands", :alpha4 "GEHH",
    :former-code {:numeric 296, :alpha3 "GEL", :alpha2 "GE"},
    :latter-codes [{:numeric 296, :alpha3 "KIR", :alpha2 "KI"}
                   {:numeric 798, :alpha3 "TUV", :alpha2 "TV"}]}
   {:name "Upper Volta", :alpha4 "HVBF",
    :former-code {:numeric 854, :alpha3 "HVO", :alpha2 "HV"},
    :latter-codes [{:numeric 854, :alpha3 "BFA", :alpha2 "BF"}]}
   {:name "Johnston Island", :alpha4 "JTUM",
    :former-code {:numeric 396, :alpha3 "JTN", :alpha2 "JT"},
    :latter-codes [{:numeric 581, :alpha3 "UMI", :alpha2 "UM"}]}
   {:name "Midway Islands", :alpha4 "MIUM",
    :former-code {:numeric 488, :alpha3 "UMI", :alpha2 "UM"},
    :latter-codes [{:numeric 581, :alpha3 "UMI", :alpha2 "UM"}]}
   {:name "New Hebrides", :alpha4 "NHVU",
    :former-code {:numeric 548, :alpha3 "NHB", :alpha2 "NH"},
    :latter-codes [{:numeric 548, :alpha3 "VUT", :alpha2 "VU"}]}
   {:name "Dronning Maud Land", :alpha4 "NQAQ",
    :former-code {:numeric 216, :alpha3 "ATN", :alpha2 "NQ"},
    :latter-codes [{:numeric 10, :alpha3 "ATA", :alpha2 "AQ"}]}
   {:name "Neutral Zone", :alpha4 "NTHH",
    :former-code {:numeric 536, :alpha3 "NTZ", :alpha2 "NT"},
    :latter-codes [{:numeric 368, :alpha3 "IRQ", :alpha2 "IQ"}
                   {:numeric 682, :alpha3 "SAU", :alpha2 "SA"}]}
   {:name "Trust Territory of the Pacific Islands", :alpha4 "PCHH",
    :former-code {:numeric 582, :alpha3 "PCI", :alpha2 "PC"},
    :latter-codes [{:numeric 583, :alpha3 "FSM", :alpha2 "FM"}
                   {:numeric 584, :alpha3 "MHL", :alpha2 "MH"}
                   {:numeric 580, :alpha3 "MNP", :alpha2 "MP"}
                   {:numeric 585, :alpha3 "PLW", :alpha2 "PW"}]}
   {:name "United States Miscellaneous Pacific Islands", :alpha4 "PUUM",
    :former-code {:numeric 849, :alpha3 "PUS", :alpha2 "PU"},
    :latter-codes [{:numeric 581, :alpha3 "UMI", :alpha2 "UM"}]}
   {:name "Panama Canal Zone", :alpha4 "PZPA",
    :former-code {:numeric 594, :alpha3 "PCZ", :alpha2 "PZ"},
    :latter-codes [{:numeric 591, :alpha3 "PAN", :alpha2 "PA"}]}
   {:name "Rhodesia", :alpha4 "RHZW",
    :former-code {:numeric 716, :alpha3 "RHO", :alpha2 "RH"},
    :latter-codes [{:numeric 716, :alpha3 "ZWE", :alpha2 "ZW"}]}
   {:name "Sikkim", :alpha4 "SKIN",
    :former-code {:numeric 698, :alpha3 "SKM", :alpha2 "SK"},
    :latter-codes [{:numeric 356, :alpha3 "IND", :alpha2 "IN"}]}
   {:name "Soviet Union", :alpha4 "SUHH",
    :former-code {:numeric 810, :alpha3 "SUN", :alpha2 "SU"},
    :latter-codes [{:numeric 51, :alpha3 "ARM", :alpha2 "AM"}
                   {:numeric 31, :alpha3 "AZE", :alpha2 "AZ"}
                   {:numeric 233, :alpha3 "EST", :alpha2 "EE"}
                   {:numeric 268, :alpha3 "GEO", :alpha2 "GE"}
                   {:numeric 417, :alpha3 "KGZ", :alpha2 "KG"}
                   {:numeric 398, :alpha3 "KAZ", :alpha2 "KZ"}
                   {:numeric 440, :alpha3 "LTU", :alpha2 "LT"}
                   {:numeric 428, :alpha3 "LVA", :alpha2 "LV"}
                   {:numeric 498, :alpha3 "MDA", :alpha2 "MD"}
                   {:numeric 643, :alpha3 "RUS", :alpha2 "RU"}
                   {:numeric 762, :alpha3 "TJK", :alpha2 "TJ"}
                   {:numeric 795, :alpha3 "TKM", :alpha2 "TM"}
                   {:numeric 860, :alpha3 "UZB", :alpha2 "UZ"}]}
   {:name "East Timor", :alpha4 "TPTL",
    :former-code {:numeric 626, :alpha3 "TMP", :alpha2 "TP"},
    :latter-codes [{:numeric 626, :alpha3 "TLS", :alpha2 "TL"}]}
   {:name "Democratic Republic ofViet-Nam", :alpha4 "VDVN",
    :former-code {:numeric 704, :alpha3 "VDR", :alpha2 "VD"},
    :latter-codes [{:numeric 704, :alpha3 "VNM", :alpha2 "VN"}]}
   {:name "Wake Island", :alpha4 "WKUM",
    :former-code {:numeric 872, :alpha3 "WAK", :alpha2 "WK"},
    :latter-codes [{:numeric 581, :alpha3 "UMI", :alpha2 "UM"}]}
   {:name "Democratic Yemen", :alpha4 "YDYE",
    :former-code {:numeric 720, :alpha3 "YMD", :alpha2 "YD"},
    :latter-codes [{:numeric 887, :alpha3 "YEM", :alpha2 "YE"}]}
   {:name "Yugoslavia", :alpha4 "YUCS",
    :former-code {:numeric 891, :alpha3 "YUG", :alpha2 "YU"}
    :latter-codes [{:numeric 891, :alpha3 "SCG", :alpha2 "CS"}]}
   {:name "Zaire", :alpha4 "ZRCD",
    :former-code {:numeric 180, :alpha3 "ZAR", :alpha2 "ZR"}
    :latter-codes [{:numeric 180, :alpha3 "COD", :alpha2 "CD"}]}])

(s/def ::name
  (s/with-gen (s/and string? (set (map :name former-countries)))
    #(gen/elements (map :name former-countries))))

(s/def ::alpha4
  (s/with-gen (s/and string?
                     #(re-matches #"[A-Z]{4}" %)
                     (set (map :alpha4 former-countries)))
    #(gen/elements (map :alpha4 former-countries))))

(s/def :clj-iso3166.former-country.former-code/numeric
  (s/and integer?
         #(<= 0 % 999)
         (set (map (comp :numeric :former-code) former-countries))))

(s/def :clj-iso3166.former-country.former-code/alpha3
  (s/and string?
         #(re-matches #"[A-Z]{3}" %)
         (set (map (comp :alpha3 :former-code) former-countries))))

(s/def :clj-iso3166.former-country.former-code/alpha2
  (s/and string?
         #(re-matches #"[A-Z]{2}" %)
         (set (map (comp :alpha2 :former-code) former-countries))))

(s/def ::former-code
  (s/keys :req-un [:clj-iso3166.former-country.former-code/numeric
                   :clj-iso3166.former-country.former-code/alpha3
                   :clj-iso3166.former-country.former-code/alpha2]
          :gen #(gen/elements (map :former-code former-countries))))

(s/def :clj-iso3166.former-country.latter-codes/numeric
  (s/and integer?
         #(<= 0 % 999)
         (->> (mapcat :latter-codes former-countries)
              (map :numeric)
              set)))

(s/def :clj-iso3166.former-country.latter-codes/alpha3
  (s/and string?
         #(re-matches #"[A-Z]{3}" %)
         (->> (mapcat :latter-codes former-countries)
              (map :alpha3)
              set)))

(s/def :clj-iso3166.former-country.latter-codes/alpha2
  (s/and string?
         #(re-matches #"[A-Z]{2}" %)
         (->> (mapcat :latter-codes former-countries)
              (map :alpha2)
              set)))

(s/def ::latter-codes
  (s/with-gen (s/coll-of
               (s/keys
                :req-un [:clj-iso3166.former-country.latter-codes/numeric
                         :clj-iso3166.former-country.latter-codes/alpha3
                         :clj-iso3166.former-country.latter-codes/alpha2])
               :kind vector?
               :distinct true)
    #(gen/elements (map :latter-codes former-countries))))

(s/def ::former-country
  (s/keys :req-un [::name
                   ::alpha4
                   ::former-code
                   ::latter-codes]
          :gen #(gen/elements former-countries)))

(def ^:private name-map
  (->> former-countries
       (map #(vector (string/lower-case (:name %)) %))
       (into {})))

(def ^:private alpha4-map
  (->> former-countries
       (map #(vector (:alpha4 %) %))
       (into {})))

(defn name->former-country [s]
  (when s
    (name-map (string/lower-case s))))

(s/fdef name->former-country
  :args (s/cat :s (s/nilable string?))
  :ret  (s/nilable ::former-country))

(defn alpha4->former-country [s]
  (when s
    (alpha4-map (string/upper-case s))))

(s/fdef alpha4->former-country
  :args (s/cat :s (s/nilable string?))
  :ret  (s/nilable ::former-country))

(def ^:private former-code-map
  (->> former-countries
       (map #(vector (:former-code %) %))
       (into {})))

(def ^:private current-code-map
  (->> c/countries
       (map #(vector (dissoc % :name) %))
       (into {})))

(defn migrate-country
  "Migrates a former country to current countries."
  [former-country]
  (s/assert ::former-country former-country)
  (flatten
   (for [latter-code (:latter-codes former-country)]
     (if-let [latter-country (former-code-map latter-code)]
       (migrate-country latter-country)
       (current-code-map latter-code)))))

(s/fdef migrate-country
  :args (s/cat :former-country ::former-country)
  :ret  (s/+ ::c/country))

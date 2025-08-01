# clj-iso3166

[![Clojars Project](https://img.shields.io/clojars/v/net.totakke/clj-iso3166.svg)](https://clojars.org/net.totakke/clj-iso3166)
[![build](https://github.com/totakke/clj-iso3166/actions/workflows/build.yml/badge.svg)](https://github.com/totakke/clj-iso3166/actions/workflows/build.yml)

ISO 3166 country definitions for Clojure(Script)

## Installation

Clojure CLI/deps.edn (as Git):

```clojure
io.github.totakke/clj-iso3166 {:git/tag "v1.0.0" :git/sha "e6538f2"}
```

Clojure CLI/deps.edn (as Maven):

```clojure
net.totakke/clj-iso3166 {:mvn/version "1.0.0"}
```

Leiningen/Boot:

```clojure
[net.totakke/clj-iso3166 "1.0.0"]
```

## Usage

### Basics

```clojure
(require '[clj-iso3166.country :as c])

c/countries
;;=> [{:name "Andorra", :numeric 20, :alpha3 "AND", :alpha2 "AD"}
;;    {:name "United Arab Emirates", :numeric 784, :alpha3 "ARE", :alpha2 "AE"}
;;    {:name "Afghanistan", :numeric 4, :alpha3 "AFG", :alpha2 "AF"}
;;    {:name "Antigua and Barbuda", :numeric 28, :alpha3 "ATG", :alpha2 "AG"}
;;    {:name "Anguilla", :numeric 660, :alpha3 "AIA", :alpha2 "AI"}
;;    ...]

(c/name->country "Japan")
;;=> {:name "Japan", :numeric 392, :alpha3 "JPN", :alpha2 "JP"}

(c/numeric->country 380)
;;=> {:name "Italy", :numeric 380, :alpha3 "ITA", :alpha2 "IT"}

(c/alpha3->country "USA")
;;=> {:name "United States", :numeric 840, :alpha3 "USA", :alpha2 "US"}

(c/alpha2->country "EG")
;;=> {:name "Egypt", :numeric 818, :alpha3 "EGY", :alpha2 "EG"}
```

### Former Countries

```clojure
(require '[clj-iso3166.former-country :as fc])

(fc/name->former-country "Czechoslovakia")
;;=> {:name "Czechoslovakia",
;;    :alpha4 "CSHH",
;;    :former-code {:numeric 200, :alpha3 "CSK", :alpha2 "CS"},
;;    :latter-codes [{:numeric 203, :alpha3 "CZE", :alpha2 "CZ"}
;;                   {:numeric 703, :alpha3 "SVK", :alpha2 "SK"}]}

(def yugoslavia (fc/alpha4->former-country "YUCS"))

(fc/migrate-country yugoslavia)
;;=> ({:name "Montenegro", :numeric 499, :alpha3 "MNE", :alpha2 "ME"}
;;    {:name "Serbia", :numeric 688, :alpha3 "SRB", :alpha2 "RS"})
```

### Spec

```clojure
(require '[clj-iso3166.country :as c]
         '[clojure.spec.alpha :as s]
         '[clojure.spec.gen.alpha :as gen])

(s/valid? ::c/alpha2 "JP")
;;=> true

(s/explain ::c/alpha3 "IT")
;; "IT" - failed: (re-matches #"[A-Z]{3}" %) spec: :clj-iso3166.country/alpha3

(gen/generate (s/gen ::c/country))
;;=> {:name "Albania", :numeric 8, :alpha3 "ALB", :alpha2 "AL"}
```

## License

Copyright © 2019 [Toshiki Takeuchi](https://totakke.net/)

Distributed under the MIT License.

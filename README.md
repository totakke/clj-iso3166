# clj-iso3166

[![Clojars Project](https://img.shields.io/clojars/v/net.totakke/clj-iso3166.svg)](https://clojars.org/net.totakke/clj-iso3166)
[![CircleCI](https://circleci.com/gh/totakke/clj-iso3166.svg?style=svg)](https://circleci.com/gh/totakke/clj-iso3166)

ISO 3166-1 country definitions for Clojure(Script)

## Installation

Clojure CLI/deps.edn:

```clojure
net.totakke/clj-iso3166 {:mvn/version "0.2.0"}
```

Leiningen/Boot:

```clojure
[net.totakke/clj-iso3166 "0.2.0"]
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

### Spec

```clojure
(require '[clj-iso3166.country :as c]
         '[clojure.spec.alpha :as s])

(s/valid? ::c/alpha2 "JP")
;;=> true

(s/explain ::c/alpha3 "IT")
;; "IT" - failed: (re-matches #"[A-Z]{3}" %) spec: :clj-iso3166.country/alpha3
```

## License

Copyright © 2019 Toshiki Takeuchi

Distributed under the MIT License.

# clj-iso3166

[![Clojars Project](https://img.shields.io/clojars/v/net.totakke/clj-iso3166.svg)](https://clojars.org/net.totakke/clj-iso3166)

ISO 3166-1 country definitions for Clojure(Script)

## Usage

```clj
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

(c/numeric->country 392)
;;=> {:name "Japan", :numeric 392, :alpha3 "JPN", :alpha2 "JP"}

(c/alpha3->country "JPN")
;;=> {:name "Japan", :numeric 392, :alpha3 "JPN", :alpha2 "JP"}

(c/alpha2->country "JP")
;;=> {:name "Japan", :numeric 392, :alpha3 "JPN", :alpha2 "JP"}
```

## License

Copyright © 2019 Toshiki Takeuchi

Distributed under the MIT License.


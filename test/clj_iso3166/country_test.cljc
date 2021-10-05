(ns clj-iso3166.country-test
  (:require [clj-iso3166.country :as c]
            [clojure.spec.alpha :as s]
            [clojure.test :refer [deftest is]]))

(deftest countries-test
  (is (s/valid? (s/coll-of ::c/country :kind vector? :distinct true)
                c/countries)))

(deftest name->country-test
  (is (s/valid? ::c/country (c/name->country "Japan")))
  (is (s/valid? ::c/country (c/name->country "japan")))
  (is (nil? (c/name->country "Tokyo")))
  (is (nil? (c/name->country nil))))

(deftest numeric->country-test
  (is (s/valid? ::c/country (c/numeric->country 392)))
  (is (nil? (c/numeric->country 1000)))
  (is (nil? (c/numeric->country nil))))

(deftest alpha3->country-test
  (is (s/valid? ::c/country (c/alpha3->country "JPN")))
  (is (s/valid? ::c/country (c/alpha3->country "jpn")))
  (is (nil? (c/alpha3->country "JP")))
  (is (nil? (c/alpha3->country nil))))

(deftest alpha2->country-test
  (is (s/valid? ::c/country (c/alpha2->country "JP")))
  (is (s/valid? ::c/country (c/alpha2->country "jp")))
  (is (nil? (c/alpha2->country "JPN")))
  (is (nil? (c/alpha2->country nil))))

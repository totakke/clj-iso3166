(ns clj-iso3166.former-country-test
  (:require [clj-iso3166.country :as c]
            [clj-iso3166.former-country :as fc]
            [clojure.spec.alpha :as s]
            [clojure.test :refer [deftest is]]
            [clojure.test.check :as tc]
            [clojure.test.check.properties :as prop]))

(deftest former-countries-test
  (is (s/valid? (s/coll-of ::fc/former-country :kind vector? :distinct true)
                fc/former-countries)))

(deftest name->former-country-test
  (is (s/valid? ::fc/former-country (fc/name->former-country "Czechoslovakia")))
  (is (s/valid? ::fc/former-country (fc/name->former-country "czechoslovakia")))
  (is (nil? (fc/name->former-country "Japan")))
  (is (nil? (fc/name->former-country nil))))

(deftest alpha4->former-country-test
  (is (s/valid? ::fc/former-country (fc/alpha4->former-country "CSHH")))
  (is (s/valid? ::fc/former-country (fc/alpha4->former-country "cshh")))
  (is (nil? (fc/alpha4->former-country "CS")))
  (is (nil? (fc/alpha4->former-country nil))))

(deftest generator-test
  (is (:result (tc/quick-check 100
                               (prop/for-all [x (s/gen ::fc/former-country)]
                                 ((set fc/former-countries) x)))))

  (is (:result (tc/quick-check 100
                               (prop/for-all [x (s/gen ::fc/name)]
                                 ((set (map :name fc/former-countries)) x)))))

  (is (:result (tc/quick-check 100
                               (prop/for-all [x (s/gen ::fc/alpha4)]
                                 ((set (map :alpha4 fc/former-countries)) x)))))

  (is (:result
       (tc/quick-check 100
                       (prop/for-all [x (s/gen ::fc/former-code)]
                         ((set (map :former-code fc/former-countries)) x)))))

  (is (:result
       (tc/quick-check 100
                       (prop/for-all [x (s/gen ::fc/latter-codes)]
                         ((set (map :latter-codes fc/former-countries)) x))))))

(deftest migrate-country-test
  (doseq [former-country fc/former-countries]
    (is (s/valid? (s/+ ::c/country) (fc/migrate-country former-country))))
  (is (= (fc/migrate-country (fc/name->former-country "Czechoslovakia"))
         '({:name "Czechia", :numeric 203, :alpha3 "CZE", :alpha2 "CZ"}
           {:name "Slovakia", :numeric 703, :alpha3 "SVK", :alpha2 "SK"})))
  (is (= (fc/migrate-country (fc/alpha4->former-country "YUCS"))
         '({:name "Montenegro", :numeric 499, :alpha3 "MNE", :alpha2 "ME"}
           {:name "Serbia", :numeric 688, :alpha3 "SRB", :alpha2 "RS"}))))

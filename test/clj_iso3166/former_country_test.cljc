(ns clj-iso3166.former-country-test
  (:require [clj-iso3166.former-country :as fc]
            [clojure.spec.alpha :as s]
            [clojure.test :refer [deftest is]]))

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

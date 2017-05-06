(ns appspot-training.app
  (:require [reagent.core :as r]))

;;=================================
;; Page Header
;;=================================

(defonce slide-number (r/atom 0))

(defn go-to-prev-slide! []
  (swap! slide-number (fn [n] (mod (dec n) 4))))

(defn go-to-next-slide! []
  (swap! slide-number (fn [n] (mod (inc n) 4))))

(defn pageheader-component []
  [:section#pageheader
   [:button.slide-nav {:on-click go-to-prev-slide!} "Back"]
   [:button.slide-nav {:on-click go-to-next-slide!} "Next"]
   [:h1 "Google Earth Engine/Google App Engine Internal Training Workshop"]])

;;=================================
;; Page Content
;;=================================

(def greetings
  {"English" "Welcome!"
   "Thai"    "Sawasdee khrop!"})

(defonce greeting-language (r/atom "English"))

(defn toggle-greeting-language! []
  (case @greeting-language
    "English" (reset! greeting-language "Thai")
    "Thai"    (reset! greeting-language "English")))

(defn slide0-component []
  [:section#pagecontent-slide0
   [:h2 (greetings @greeting-language)]
   [:button {:on-click toggle-greeting-language!} @greeting-language]
   [:p "Push the button to toggle the greeting language."]])

(defn slide1-component []
  [:section#pagecontent-slide1
   [:img#vermont {:src "images/vt_us_inset.png"}]])

(defn slide2-component []
  [:section#pagecontent-slide2
   [:img#worcester {:src "images/worcester_soccer_field.jpg"}]])

(defn slide3-component []
  [:section#pagecontent-slide3
   [:h2 "GAE+GEE Web Technology Stack"]
   [:div#client-stack
    [:h3 "Client"]
    [:ul
     (for [i ["HTML" "CSS" "Javascript" "Clojurescript"
              "(needs Google Maps API key)"]]
       [:li {:key i} i])]]
   [:hr]
   [:div#server-stack
    [:h3 "Server"]
    [:ul
     (for [i ["Python" "GEE Python API" "(needs GEE whitelisted account)"]]
       [:li {:key i} i])]]
   [:hr]
   [:div#gee-stack
    [:h3 "GEE"]
    [:ul
     (for [i ["(runs GEE Python API calls)"
              "(returns Google Maps API mapid and token)"]]
       [:li {:key i} i])]]])

(defn pagecontent-component []
  (case @slide-number
    0 [slide0-component]
    1 [slide1-component]
    2 [slide2-component]
    3 [slide3-component]))

;;=================================
;; Page Footer
;;=================================

(defn pagefooter-component []
  [:section#pagefooter
   [:img#adpc-logo {:src "images/adpc.jpg"}]
   [:img#servir-logo {:src "images/servir.png"}]
   [:img#sig-logo {:src "images/logosig.png"}]])

;;=================================
;; Root Component
;;=================================

(defn root-component []
  [:div#root-component
   [pageheader-component]
   [pagecontent-component]
   [pagefooter-component]])

(defn init []
  (r/render-component
   [root-component]
   (.getElementById js/document "root")))

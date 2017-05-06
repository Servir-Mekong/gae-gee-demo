(set-env!
 :source-paths    #{"src/cljs"}
 :resource-paths  #{"resources"}
 :dependencies    '[[adzerk/boot-cljs          "1.7.228-2"  :scope "test"]
                    [adzerk/boot-cljs-repl     "0.3.3"      :scope "test"]
                    [adzerk/boot-reload        "0.4.13"     :scope "test"]
                    [pandeiro/boot-http        "0.7.6"      :scope "test"]
                    [com.cemerick/piggieback   "0.2.1"      :scope "test"]
                    [org.clojure/tools.nrepl   "0.2.12"     :scope "test"]
                    [weasel                    "0.7.0"      :scope "test"]
                    [org.clojure/clojure       "1.8.0"]
                    [org.clojure/clojurescript "1.9.293"]
                    [reagent "0.6.0"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.boot-http    :refer [serve]])

(deftask development []
  (comp (serve)
        (watch)
        (cljs-repl)
        (reload :on-jsload 'appspot-training.app/init)
        (cljs :optimizations :none :source-map true)))

(deftask production []
  (comp (serve)
        (cljs :optimizations :advanced)))

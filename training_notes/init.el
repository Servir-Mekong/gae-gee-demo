;; Package management setup
(require 'package)
(add-to-list 'package-archives '("marmalade"    . "http://marmalade-repo.org/packages/") t)
(add-to-list 'package-archives '("melpa-stable" . "http://stable.melpa.org/packages/"  ) t)
(package-initialize)

;; Change annoying default settings
(setq-default tab-width 4)
(setq-default indent-tabs-mode nil)
(setq make-backup-files nil)
(set-language-environment "UTF-8")

;; Load the calculator
(require 'calc)
(global-set-key (kbd "<f9>") 'calc)

;; Load Clojure mode
(require 'clojure-mode)
(require 'cider)
(require 'company)
(require 'paredit)

(add-to-list 'auto-mode-alist '("\\.boot$" . clojure-mode))
(add-hook 'clojure-mode-hook    'paredit-mode)
(add-hook 'cider-repl-mode-hook 'paredit-mode)
(add-hook 'cider-mode-hook      'company-mode)
(add-hook 'cider-repl-mode-hook 'company-mode)
(add-hook 'cider-mode-hook      'eldoc-mode)
(add-hook 'cider-repl-mode-hook 'eldoc-mode)

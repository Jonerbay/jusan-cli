# Implementation of command lines in Java

Этот проект для работы с файлами на языке Java.

## Возможности

Данный проект имеет следующий функционал.
* Выводит список всех файлов и директорий
* Выводит права для файла в формате `rwx` для текущего пользователя
* Устанавливает права для файла
* и так далее.

## Функции
* `public static void listDirectory(String path) {...};`
 выводит список всех файлов и директорий для `path`
* `public static void listPythonFiles(String path) {...};`
 выводит список файлов с расширением `.py` в `path`
* `public static void isDirectory(String path) {...};`
 выводит `true`, если `path` это директория, в других случаях `false`
* `public static void define(String path) {...};`
выводит `директория` или `файл` в зависимости от типа `path`
* `public static void printPermissions(String path) {...};`
 выводит права для файла в формате `rwx` для текущего пользователя
* `public static void setPermissions(String path, String permissions) {...};`
 устанавливает права для файла `path`
* `public static void printContent(String path) {...};`
 выводит контент файла
* `public static void appendFooter(String path) {...};`
 добавляет строке `# Autogenerated line` в конец `path`
* `public static void createBackup(String path) {...};`
 создает копию `path` в директорию `/tmp/${date}.backup` где, date - это дата в формате `dd-mm-yyyy`. `path` может быть директорией или файлом. При директории, копируется весь контент.
* `public static void printLongestWord(String path) {...};`
 выводит самое длинное слово в файле
* `public static void help() {...};`
  выводит список команд и их описание
* `public static void exit() {...};`
  завершает работу программы


## Запуск
`javac myFile.java`
`java MyFile`
Дальше будут описание всех функции с аргументами. Также, доступна функция help для помощи.

	

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class MyFile {
    // выводит список всех файлов и директорий для `path` - ls
    public static void listDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("There is no such directory");
            return;
        }
        for (String fileName : Objects.requireNonNull(file.list()))
            System.out.print(fileName + " ");
        System.out.println();
    }

    // выводит список файлов с расширением `.py` в `path` - ls_py
    public static void listPythonFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("There is no such directory");
            return;
        }
        for (String fileName : Objects.requireNonNull(file.list()))
            if (fileName.endsWith(".py"))
                System.out.print(fileName + " ");
        System.out.println();
    }

    // выводит `true`, если `path` это директория, в других случаях `false` - id_dir
    public static void isDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("There is no such directory");
            return;
        }
        System.out.println(file.isDirectory());
    }

    // выводит `директория` или `файл` в зависимости от типа `path` - define
    public static void define(String path) {
        File file = new File(path);
        if (!file.exists())
            System.out.println("Such file or directory does not exist");
        else if (file.isFile())
            System.out.println("file");
        else if (file.isDirectory())
            System.out.println("directory");
    }

    // выводит права для файла в формате `rwx` для текущего пользователя - readmod
    public static void printPermissions(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File does not exist");
            return;
        }
        if (file.canRead())
            System.out.print("r");
        if (file.canWrite())
            System.out.print("w");
        if (file.canExecute())
            System.out.println("x");
    }

    // устанавливает права для файла `path` - setmod
    public static void setPermissions(String path, String permissions) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File does not exist");
            return;
        }
        boolean read = permissions.contains("r");
        boolean execute = permissions.contains("x");
        boolean write = permissions.contains("w");
        file.setReadable(read);
        file.setExecutable(execute);
        file.setWritable(write);
    }

    // выводит контент файла - cat
    public static void printContent(String path) {
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. Such file does not exist or can not be opened");
        }
    }

    // добавляет строке `# Autogenerated line` в конец `path` - append
    public static void appendFooter(String path) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write("# Autogenerated line\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred. Such file does not exist or can not be opened");
        }
    }

    // создает копию `path` в директорию `/tmp/${date}.backup` где, date - это дата в формате `dd-mm-yyyy`. `path` может быть директорией или файлом. При директории, копируется весь контент. - bc
    public static void createBackup(String path) {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String dir = File.separator + "tmp" + File.separator + "${" + date + "}.backup";
        File file = new File(path);
        if (!(new File(System.getProperty("user.dir") + dir)).exists()) {
            (new File(System.getProperty("user.dir") + dir)).mkdirs();
        }
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(path);
            os = new FileOutputStream(System.getProperty("user.dir") + dir + File.separator + file.getName());
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
                assert os != null;
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Successful");
//        File file = new File(path);
//        if (!file.exists()) {
//            System.out.println("File does not exist");
//            return;
//        }
//        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//        String dir = File.separator + "tmp" + File.separator + "${" + date + "}.backup";
//        if ((new File(dir)).exists()) {
//            (new File(dir)).mkdirs();
//        }
//        File copy =
//        try {
//            Files.copy(Paths.get(path), Paths.get(dir + File.separator + file.getName()), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    // выводит самое длинное слово в файле - greplong
    public static void printLongestWord(String path) {
        int max = 0;
        String maxStr = "";
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("\\s+");
                for (String s : data) {
                    if (s.length() > max) {
                        max = s.length();
                        maxStr = s;
                    }
                }
            }
            System.out.println(maxStr);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. Such file does not exist or can not be opened");
        }
    }

    // выводит список команд и их описание - help
    public static void help() {
        System.out.println("MyFS 1.0 commands:");
        System.out.println("""
                ls <path>               shows all files and directories in `path`
                ls_py <path>            shows all files ended with `.py` in `path`
                is_dir <path>           shows `true`, if `path` is directory, otherwise `false`
                define <path>           shows `directory` or `file` depending on type of `path`
                readmod <path>          shows file's permissions in format of `rwx`
                setmod <path> <perm>    sets permissions for file `path`
                cat <path>              shows contents of the file
                append <path>           appends text line `# Autogenerated line` to the end of file `path`
                bc <path>               creates a copy of `path` into directory `/tmp/${date}.backup` where, date - is data in format of `dd-mm-yyyy`
                greplong <path>         shows longest word in a file 'path'
                help                    shows list of commands and their explanations
                exit                    finishes the program""");
    }

    // завершает работу программы - exit
    public static void exit() {
        System.out.println("Goodbye");
        System.exit(0);
    }

    @SuppressWarnings("InfiniteLoopStatement")  //just for fun
    public static void main(String[] args) {
        help();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.nextLine();
            String[] arr = str.split("\\s+");
            int n = arr.length;
            if (n == 1) {
                if (arr[0].equals("exit"))
                    exit();
                if (arr[0].equals("help"))
                    help();
                else
                    System.out.println("You typed wrong command. Look for help");
            } else if (n == 2) {
                switch (arr[0]) {
                    case "ls" -> listDirectory(arr[1]);
                    case "ls_py" -> listPythonFiles(arr[1]);
                    case "is_dir" -> isDirectory(arr[1]);
                    case "define" -> define(arr[1]);
                    case "readmod" -> printPermissions(arr[1]);
                    case "cat" -> printContent(arr[1]);
                    case "append" -> appendFooter(arr[1]);
                    case "bc" -> createBackup(arr[1]);
                    case "greplong" -> printLongestWord(arr[1]);
                    default -> System.out.println("You typed wrong command. Look for help");
                }
            } else if (n == 3) {
                if (!arr[0].equals("setmod"))
                    System.out.println("You typed wrong command or too many arguments. Look for help");
                else
                    setPermissions(arr[1], arr[2]);
            } else {
                System.out.println("Too many arguments are given or nothing given at all. Look for help function");
            }
        }
    }
}

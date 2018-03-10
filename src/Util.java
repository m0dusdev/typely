
class Util {
    // Operating systems.
    public enum OS {
        WINDOWS, LINUX, MAC
    }

    private static OS os = null;

    // get user os for various tweaks at runtime
    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            }
        }
        return os;
    }


    // create and add 20 tabs to the editor
    private static void perfTest(){
        for (int i = 0; i < 20; i++) {
            MainScreen.newTab("Tab Test", Integer.toString(i));
        }

    }

    // loop and time test - display result to user
    public static void perfLooper() {

        // start time
        long startTime = System.currentTimeMillis();

        // loop tests
        for (int i = 0; i < 1; i++){
            perfTest();
        }

        // stop the clock
        long stopTime = System.currentTimeMillis();

        // calculate run time
        long diff = stopTime - startTime;
        String result = "\n Created 20 tabs in " + (stopTime - startTime) + " milliseconds - - - ";

        // some arbitrary performance rules
        if (diff <= 2000 && diff >= 1500) {
            MainScreen.getRSSyntaxarea()
                    .setText(result + "RATING -> POOR");

        } else if (diff <= 1500 && diff >= 1000) {
            MainScreen.getRSSyntaxarea()
                    .setText(result + "RATING -> OK");

        } else if (diff <= 1000 && diff >= 500) {
            MainScreen.getRSSyntaxarea()
                    .setText(result + "RATING -> GOOD");

        } else if (diff <= 500 && diff >= 200) {
            MainScreen.getRSSyntaxarea()
                    .setText(result + "RATING -> VERY GOOD");

        }else if (diff <= 200) {
            MainScreen.getRSSyntaxarea()
                    .setText(result + "RATING -> GREAT");
        }
    }
}
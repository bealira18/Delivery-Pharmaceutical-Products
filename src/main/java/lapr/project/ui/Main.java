package lapr.project.ui;

import lapr.project.data.SettingsHandler;

class Main {

    public static void main(String[] args) {    
        //load Application Settings
        SettingsHandler sh = new SettingsHandler();
        sh.loadSettings(SettingsHandler.SETTINGS_FILE);

    }
}
